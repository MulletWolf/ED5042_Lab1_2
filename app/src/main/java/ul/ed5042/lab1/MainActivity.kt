package ul.ed5042.lab1

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ul.ed5042.lab1.ui.theme.Lab1Theme
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val keyboardController= LocalSoftwareKeyboardController.current
            keyboardController?.hide()
            QcaCalculationLayout()
        }
    }
}

data class Module(//data class to hold data
    var defname: String = "Module",

    var defGrade: String = "Grade",
    var defweight: String = "ECTS",
    //val amount: Double
)




@Composable
fun QcaCalculationLayout(modifier: Modifier = Modifier) {

    val keyboardController= LocalSoftwareKeyboardController.current
    keyboardController?.hide()
    // Source - https://stackoverflow.com/a/7094191
// Posted by Nguyen  Minh Binh, modified by community. See post 'Timeline' for change history
// Retrieved 2026-02-19, License - CC BY-SA 3.0




    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.qca_calculator),
            style = MaterialTheme.typography.displaySmall
        )
        Text(//where the bttons are 1 column, 5 rows , each 3 has 3 things
            text = stringResource(R.string.enter_data),
            style = MaterialTheme.typography.bodyLarge
        )

        /**
         * var moduleInputs = remember { mutableStateListOf("Module","Module","Module","Module","Module") }
         *         var gradeInputs = remember { mutableStateListOf("Grade","Grade","Grade","Grade","Grade") }
         *         var weightInputs = remember { mutableStateListOf("ECTS","ECTS","ECTS","ECTS","ECTS") }
         *         val calcList=mutableListOf<SnapshotStateList<String>>()//creating a list of a list
         *         //adding them to calclist
         *         calcList.add(moduleInputs)
         *         calcList.add(gradeInputs)
         *         calcList.add(weightInputs)
         */


        /**
         * modules contain a list of Module objects
         * Alternative to having 3 different mutableliststateof in 1 list
         */
        var modules=remember { mutableStateListOf(
            Module(),
            Module(),
            Module(),
            Module(),
            Module()
        ) }




//        var gradeInput by remember { mutableStateOf("Grade") }
//        var weightInput by remember { mutableStateOf("ECTS") }
//        var moduleName:String
//        var moduleInput1 by remember { mutableStateOf("Module") }
//        var moduleInput2 by remember { mutableStateOf("Module") }
//        var moduleInput3 by remember { mutableStateOf("Module") }
//        var moduleInput4 by remember { mutableStateOf("Module") }
//        var moduleInput5 by remember { mutableStateOf("Module") }

//get weight and pass weight



        /**
         * This loops through each module object within the module list
         * Handles each moduleRow 1 by 1
         */
     modules.forEachIndexed { index, string ->
         //modules[0] ves 1st row etc
         //amount=modules[index].defweight.toDoubleOrNull()?:0.0
         ModuleRow(

             moduleInput = modules[index].defname,
             moduleLabel = "Module ${index + 1}",
             gradeInput = modules[index].defGrade,
             weightInput = modules[index].defweight,
             onModuleChange = { modules[index] = modules[index].copy(defname = it) },
             onGradeChange = { modules[index] =modules[index].copy(defGrade = it) },
             onWeightChange = {
                 //Used to chnage the default ECTS into the new string by using copy
                 //Since modules[index].defweight=it ,remained to be ECTS
                 modules[index] = modules[index].copy(defweight = it)},
               //  amount=it.toIntOrNull()?:0 },

             modifier = Modifier.padding(10.dp)


         )



//         amount=modules[0].defweight.toDoubleOrNull()?:0.0
//         amount=modules[1].defweight.toDoubleOrNull()?:0.0
//         amount=modules[2].defweight.toDoubleOrNull()?:0.0
//         amount=modules[3].defweight.toDoubleOrNull()?:0.0
//         amount=modules[4].defweight.toDoubleOrNull()?:0.0
      //   amount=modules[5].defweight.toDoubleOrNull()?:0.0










      }
        var qca: String
        /**
         * I got confused
         *  qca=calculateQca(
         *             calcGrade( modules[0].defweight.toIntOrNull()?:0), weight1 = modules[0].defweight.toIntOrNull()?:0,
         *             calcGrade(modules[1].defweight.toIntOrNull()?:0),modules[1].defweight.toIntOrNull()?:0,
         *             calcGrade(modules[2].defweight.toIntOrNull()?:0),modules[2].defweight.toIntOrNull()?:0,
         *             calcGrade(modules[3].defweight.toIntOrNull()?:0),modules[3].defweight.toIntOrNull()?:0,
         *             calcGrade(modules[4].defweight.toIntOrNull()?:0),modules[4].defweight.toIntOrNull()?:0,
         *         )//returns a string
         */

        qca=calculateQca(
            modules[0].defGrade, modules[0].defweight.toIntOrNull()?:0,
            modules[1].defGrade,modules[1].defweight.toIntOrNull()?:0,
            modules[2].defGrade,modules[2].defweight.toIntOrNull()?:0,
            modules[3].defGrade,modules[3].defweight.toIntOrNull()?:0,
            modules[4].defGrade,modules[4].defweight.toIntOrNull()?:0,
        )//returns a string




        //var index = 0

        /**
        ModuleRow(

            moduleInput = modules[0].defname,
            moduleLabel = "Module ${0 + 0}",
            gradeInput = modules[0].defGrade,
            weightInput = modules[0].defweight,
            onModuleChange = { modules[0] = modules[0].copy(defname = it) },
            onGradeChange = { modules[0] = modules[0].copy(defGrade = it) },
            onWeightChange = {
                modules[0]= modules[0].copy(defweight = it)
                amount=modules[0].defweight.toDoubleOrNull()?:0.0
            },

            modifier = Modifier.padding(10.dp),


            )
        ModuleRow(
            moduleInput = modules[1].defname,
            moduleLabel = "Module ${0 +1}",
            gradeInput = modules[1].defGrade,
            weightInput = modules[1].defweight,
            onModuleChange = { modules[1] = modules[1].copy(defname = it) },
            onGradeChange = { modules[1] = modules[1].copy(defGrade = it) },
            onWeightChange = {
                modules[1]= modules[1].copy(defweight = it)
                amount=modules[1].defweight.toDoubleOrNull()?:0.0
            },

            modifier = Modifier.padding(10.dp)


            )
        ModuleRow(
            moduleInput = modules[2].defname,
            moduleLabel = "Module ${0 + 2}",
            gradeInput = modules[2].defGrade,
            weightInput = modules[2].defweight,
            onModuleChange = { modules[2] = modules[2].copy(defname = it) },
            onGradeChange = { modules[2] = modules[2].copy(defGrade = it) },
            onWeightChange = {
                modules[2]= modules[2].copy(defweight = it)
                amount=modules[2].defweight.toDoubleOrNull()?:0.0
            },

            modifier = Modifier.padding(10.dp),


            )
        ModuleRow(
            moduleInput = modules[3].defname,
            moduleLabel = "Module ${0 + 3}",
            gradeInput = modules[3].defGrade,
            weightInput = modules[3].defweight,
            onModuleChange = { modules[0] = modules[0].copy(defname = it) },
            onGradeChange = { modules[0] = modules[0].copy(defGrade = it) },
            onWeightChange = {
                modules[0]= modules[0].copy(defweight = it)
                //amount = it.toDoubleOrNull() ?: 0.0
                amount=modules[3].defweight.toDoubleOrNull()?:0.0
            },

            modifier = Modifier.padding(10.dp),
            //amount=modules[0].defweight.toDoubleOrNull()?:0.0


            )
        ModuleRow(
            moduleInput = modules[4].defname,
            moduleLabel = "Module ${0 + 4}",
            gradeInput = modules[4].defGrade,
            weightInput = modules[4].defweight,
            onModuleChange = { modules[4] = modules[4].copy(defname = it) },
            onGradeChange = { modules[4] = modules[4].copy(defGrade = it) },
            onWeightChange = {
                modules[4]= modules[4].copy(defweight = it)
                amount=modules[4].defweight.toDoubleOrNull()?:0.0
            },

            modifier = Modifier.padding(10.dp),


            )*/

        //create an object

//        ModuleRow(
//            moduleInput=calcList[0][0],
//            moduleLabel="Module1",
//            gradeInput=calcList[1][0],
//            weightInput=calcList[2][0] ,
//            onModuleChange={ calcList[0][0] = it },
//            onGradeChange= {calcList[1][0] = it },
//            onWeightChange={calcList[2][0] = it },
//            modifier=Modifier.padding(10.dp)
//        )
//        ModuleRow(
//            moduleInput=calcList[0][1],
//            moduleLabel="Module2",
//            gradeInput=calcList[1][1],
//            weightInput=calcList[2][1] ,
//            onModuleChange={ calcList[0][1] = it },
//            onGradeChange= {calcList[1][1] = it },
//            onWeightChange={calcList[2][1] = it },
//            modifier=Modifier.padding(10.dp)
//        )
//
//        ModuleRow(
//            moduleInput=calcList[0][2],
//            moduleLabel="Module3",
//            gradeInput=calcList[1][2],
//            weightInput=calcList[2][2] ,
//            onModuleChange={ calcList[0][2] = it },
//            onGradeChange= {calcList[1][2] = it },
//            onWeightChange={calcList[2][2] = it },
//            modifier=Modifier.padding(10.dp)
//        )
//        ModuleRow(
//            moduleInput=calcList[0][3],
//            moduleLabel="Module4",
//            gradeInput=calcList[1][3],
//            weightInput=calcList[2][3] ,
//            onModuleChange={ calcList[0][3] = it },
//            onGradeChange= {calcList[1][3] = it },
//            onWeightChange={calcList[2][3] = it },
//            modifier=Modifier.padding(10.dp)
//        )
//        ModuleRow(
//            moduleInput=calcList[0][4],
//            moduleLabel="Module5",
//            gradeInput=calcList[1][4],
//            weightInput=calcList[2][4] ,
//            onModuleChange={ calcList[0][4] = it },
//            onGradeChange= {calcList[1][4] = it },
//            onWeightChange={calcList[2][4] = it },
//            modifier=Modifier.padding(10.dp)
//        )


        Text(
            modifier = modifier.testTag("Result"),
            text = stringResource(R.string.your_qca,qca),
            style = MaterialTheme.typography.displayMedium
        )

    }
}


/**
 * used to calculate to convert the weight to grade
 */
val calcGrade:(Int)->String={weight->
    var grade=when(weight){
        in 90..100->"A1"
        in 80..89->"A2"
        in 70..79->"B1"
        in 65..69->"B2"
        in 60..64->"B3"
        in 55..59->"C1"
        in 50..54->"C2"
        in 45..49->"C3"
        in 40..44->"D1"
        in 35..39->"D2"
        else->"F"

    }
    grade

}


/**
 * Calculates the QPV for each grade
 */
val calcQPV:(String)->Double={grade->
    var QPV=when(grade){
        "A1"->4.0
        "A2"->3.6
        "B1"->3.2
        "B2"->3.0
        "B3"->2.8
        "C1"->2.6
        "C2"->2.4
       "C3"->2.0
        "D1"->1.6
        "D2"->1.2
        "F"->0.0
        else->0.0

    }
    QPV

}

@Composable
fun calculateGrade(weight:Int):String{
//if weight is between 90 and 100
    var grade=when(weight){
        in 90..100->"A1"
        in 80..89->"A2"
        in 70..79->"B1"
        in 65..69->"B2"
        in 60..64->"B3"
        in 55..59->"C1"
        in 50..54->"C2"
        in 45..49->"C3"
        in 40..44->"D1"
        in 35..39->"D2"
        else->"F"

    }
    return grade




}
fun calculateQca(

    grade1: String, weight1: Int,
    grade2: String, weight2: Int,
    grade3: String, weight3: Int,
    grade4: String, weight4: Int,
    grade5: String, weight5: Int
): String {
    
    //takes in grade and weight
    //do calculation

    //qca=sum of all qpv*weight/sum of all weights
    var qpvSum:Double=0.0


    var mod1=calcQPV(grade1)*weight1
    var mod2=calcQPV(grade2)*weight2
    var mod3=calcQPV(grade3)*weight3
    var mod4=calcQPV(grade4)*weight4
    var mod5=calcQPV(grade5)*weight5

    qpvSum=mod1+mod2+mod3+mod4+mod5

    var weightSum=0
    weightSum=weight1+weight2+weight3+weight4+weight5
    //weightSum.toDouble()

    var qca:Double
    qca=qpvSum/weightSum.toDouble()


    qca.toString()
    var qcaString=String.format("%.2f",qca)
    if(qca==0.0||qcaString.equals("NaN")){
        qcaString="0.00"



    }







    //return empty string to prevent warnings.
    return qcaString
}


@Composable
fun ModuleRow(

    moduleInput: String,
    moduleLabel: String,
    gradeInput: String,
    weightInput: String,
    onModuleChange: (String) -> Unit,
    onGradeChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    modifier: Modifier = Modifier,
            keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number),

) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp, top = 5.dp)
            .testTag(moduleLabel),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = moduleInput,
            label = {Text(moduleLabel)},
            onValueChange = onModuleChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(5.dp))
        TextField(
            value = gradeInput,
            label = { Text(stringResource(R.string.module_grade)) },
            onValueChange = onGradeChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(5.dp))
        TextField(
            value = weightInput,
            label = { Text(stringResource(R.string.module_weight)) },
            onValueChange = onWeightChange,
            modifier = Modifier.weight(1f),
            keyboardOptions= keyboardOptions
        )
    }

}
//Diana Ogualiri-i got this from stackoverflow to hide the keyboard
//https://stackoverflow.com/questions/1109022/how-can-i-close-hide-the-android-soft-keyboard-programmatically

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QcaCalculationPreview() {
    Lab1Theme {
        QcaCalculationLayout()
    }
}
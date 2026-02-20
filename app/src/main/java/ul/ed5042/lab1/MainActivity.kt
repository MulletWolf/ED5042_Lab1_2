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

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ul.ed5042.lab1.ui.theme.Lab1Theme


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

    //used to hide the keyboard
    val keyboardController= LocalSoftwareKeyboardController.current
    keyboardController?.hide()


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



        /**
         * This loops through each module object within the modules list
         * Handles each moduleRow 1 by 1
         */
     modules.forEachIndexed { index, string ->

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

             modifier = Modifier.padding(10.dp)


         )

      }
        var qca: String


        qca=calculateQca(
            modules[0].defGrade, modules[0].defweight.toIntOrNull()?:0,
            modules[1].defGrade,modules[1].defweight.toIntOrNull()?:0,
            modules[2].defGrade,modules[2].defweight.toIntOrNull()?:0,
            modules[3].defGrade,modules[3].defweight.toIntOrNull()?:0,
            modules[4].defGrade,modules[4].defweight.toIntOrNull()?:0,
        )//returns a string

        Text(
            modifier = modifier.testTag("Result"),
            //passes the qca as a string
            text = stringResource(R.string.your_qca,qca),
            style = MaterialTheme.typography.displayMedium
        )

    }
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


    //calculates the qpv for each module

    var mod1=calcQPV(grade1)*weight1
    var mod2=calcQPV(grade2)*weight2
    var mod3=calcQPV(grade3)*weight3
    var mod4=calcQPV(grade4)*weight4
    var mod5=calcQPV(grade5)*weight5

    //calculates the total qpv
    qpvSum=mod1+mod2+mod3+mod4+mod5

    var weightSum=0
    //calculates the sum of weights
    weightSum=weight1+weight2+weight3+weight4+weight5
    //weightSum.toDouble()

    var qca:Double
    //calculates the qca
    qca=qpvSum/weightSum.toDouble()


    qca.toString()//converts qca to string
    var qcaString=String.format("%.2f",qca)//converts qca to 2 decimal places
    if(qca==0.0||qcaString.equals("NaN")){ //checks if qca is NaN
        qcaString="0.00"



    }


    //returns qca in string format
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QcaCalculationPreview() {
    Lab1Theme {
        QcaCalculationLayout()
    }
}
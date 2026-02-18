package ul.ed5042.lab1

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ul.ed5042.lab1.ui.theme.Lab1Theme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QcaCalculationLayout()
        }
    }
}

data class Module(//creating an object to make life easier
    var name:String,
    var Grade:String,
    var ECTS:String
)
var m1= Module("","","")
var m2= Module("","","")
var m3= Module("","","")
var m4= Module("","","")
var m5= Module("","","")

@Composable
fun qcaList(){
    var moduleInputs = remember { mutableStateListOf("Module","Module","Module","Module","Module") }
    var gradeInputs = remember { mutableStateListOf("Grade","Grade","Grade","Grade","Grade") }
    var weightInputs = remember { mutableStateListOf("ECTS","ECTS","ECTS","ECTS","ECTS") }
    val calcList=mutableListOf<SnapshotStateList<String>>()//creating a list of a list
    //adding them to calclist
    calcList.add(moduleInputs)
    calcList.add(gradeInputs)
    calcList.add(weightInputs)

   // val moduleRowList=remember{mutableStateListOf(Module())}


}

@Composable
fun QcaCalculationLayout(modifier: Modifier = Modifier) {


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
        var moduleInputs = remember { mutableStateListOf("Module","Module","Module","Module","Module") }
        var gradeInputs = remember { mutableStateListOf("Grade","Grade","Grade","Grade","Grade") }
        var weightInputs = remember { mutableStateListOf("ECTS","ECTS","ECTS","ECTS","ECTS") }
        val calcList=mutableListOf<SnapshotStateList<String>>()//creating a list of a list
        //adding them to calclist

        calcList.add(moduleInputs)
        calcList.add(gradeInputs)
        calcList.add(weightInputs)

//        var gradeInput by remember { mutableStateOf("Grade") }
//        var weightInput by remember { mutableStateOf("ECTS") }
//        var moduleName:String
//        var moduleInput1 by remember { mutableStateOf("Module") }
//        var moduleInput2 by remember { mutableStateOf("Module") }
//        var moduleInput3 by remember { mutableStateOf("Module") }
//        var moduleInput4 by remember { mutableStateOf("Module") }
//        var moduleInput5 by remember { mutableStateOf("Module") }

val qca=calculateQca(
    "",0,
    "",0,
    "",0,
    "",0,
    "",0,
    )

//        moduleInputs.forEachIndexed { index, string ->
//
//        }
        //create an object





        ModuleRow(
            moduleInput=calcList[0][0],
            moduleLabel="Module1",
            gradeInput=calcList[1][0],
            weightInput=calcList[2][0] ,
            onModuleChange={ calcList[0][0] = it },
            onGradeChange= {calcList[1][0] = it },
            onWeightChange={calcList[2][0] = it },
            modifier=Modifier.padding(10.dp)
        )
        ModuleRow(
            moduleInput=calcList[0][1],
            moduleLabel="Module2",
            gradeInput=calcList[1][1],
            weightInput=calcList[2][1] ,
            onModuleChange={ calcList[0][1] = it },
            onGradeChange= {calcList[1][1] = it },
            onWeightChange={calcList[2][1] = it },
            modifier=Modifier.padding(10.dp)
        )

        ModuleRow(
            moduleInput=calcList[0][2],
            moduleLabel="Module3",
            gradeInput=calcList[1][2],
            weightInput=calcList[2][2] ,
            onModuleChange={ calcList[0][2] = it },
            onGradeChange= {calcList[1][2] = it },
            onWeightChange={calcList[2][2] = it },
            modifier=Modifier.padding(10.dp)
        )
        ModuleRow(
            moduleInput=calcList[0][3],
            moduleLabel="Module4",
            gradeInput=calcList[1][3],
            weightInput=calcList[2][3] ,
            onModuleChange={ calcList[0][3] = it },
            onGradeChange= {calcList[1][3] = it },
            onWeightChange={calcList[2][3] = it },
            modifier=Modifier.padding(10.dp)
        )
        ModuleRow(
            moduleInput=calcList[0][4],
            moduleLabel="Module5",
            gradeInput=calcList[1][4],
            weightInput=calcList[2][4] ,
            onModuleChange={ calcList[0][4] = it },
            onGradeChange= {calcList[1][4] = it },
            onWeightChange={calcList[2][4] = it },
            modifier=Modifier.padding(10.dp)
        )


        Text(
            modifier = modifier.testTag("Result"),
            text = stringResource(R.string.your_qca,"0.00"),
            style = MaterialTheme.typography.displayMedium
        )

    }
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

    //return empty string to prevent warnings.
    return ""
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
    modifier: Modifier = Modifier
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
            modifier = Modifier.weight(1f)
        )
    }
}
@Composable
fun calculateGrade(weight:Int){
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




}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QcaCalculationPreview() {
    Lab1Theme {
        QcaCalculationLayout()
    }
}
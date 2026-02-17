package ul.ed5042.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
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
        Text(
            text = stringResource(R.string.enter_data),
            style = MaterialTheme.typography.bodyLarge
        )


        Text(
            modifier = modifier.testTag("Result"),
            text = "QCA should appear here",
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

@Preview(showBackground = true)
@Composable
fun QcaCalculationPreview() {
    Lab1Theme {
        QcaCalculationLayout()
    }
}
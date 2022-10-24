package com.diego.kotlin.unscramble.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diego.kotlin.unscramble.R

@Composable
fun GameScreen() {

}

@Composable
fun GameStatus(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .size(48.dp)
    ) {
        Text(
            text = stringResource(R.string.word_count, 0),
            fontSize = 18.sp
        )
        Text(text = stringResource(R.string.score, 0),
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

@Composable
fun GameLayout(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "scrambleun",
            fontSize = 45.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.instructions),
            fontSize = 17.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        OutlinedTextField(
            value = "",
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { },
            label = { Text(stringResource(R.string.enter_your_word)) },
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {  }
            ),
        )
    }
}

@Composable
fun SubmitAndSkip(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(
            onClick = {  },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(text = stringResource(R.string.skip))
        }
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(text = stringResource(R.string.submit) )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameStatusPreview() {
    GameStatus()
}

@Preview(showBackground = true)
@Composable
fun GameLayoutPreview() {
    GameLayout()
}
@Preview(showBackground = true)
@Composable
fun SubmitAndSkipPreview() {
    SubmitAndSkip()
}


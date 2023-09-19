package com.beok.compose.text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.beok.compose.text.ui.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(color = Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    var value by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                        mutableStateOf(TextFieldValue())
                    }
                    val interactionSource = remember { MutableInteractionSource() }
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth()
                            .height(height = 40.dp)
                            .padding(horizontal = 20.dp)
                            .border(border = BorderStroke(width = 1.dp, color = Color.Black)),
                        value = value,
                        onValueChange = {
                            value = it
                        },
                        interactionSource = interactionSource,
                        decorationBox = { innerTextField ->
                            TextFieldDefaults.DecorationBox(
                                value = value.text,
                                visualTransformation = VisualTransformation.None,
                                innerTextField = innerTextField,
                                singleLine = true,
                                enabled = true,
                                interactionSource = interactionSource,
                                contentPadding = PaddingValues(vertical = 8.dp),
                            )
                        }
                    )
                    if (interactionSource.collectIsPressedAsState().value) {
                        value = TextFieldValue(text = value.text, selection = value.selection)
                    }
                }
            }
        }
    }
}

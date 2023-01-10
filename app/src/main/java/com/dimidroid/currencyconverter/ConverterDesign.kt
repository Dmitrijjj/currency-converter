package com.dimidroid.currencyconverter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CurrencyConverterScreen(){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)) {
        //title Currency Converter
        Text(text = "Currency Converter", fontWeight = FontWeight.Bold, fontSize = 28.sp,
            modifier = Modifier.padding(top = 40.dp)
        )
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp, horizontal = 56.dp)) {
            //"From"
            Text(text = "From", fontSize = 16.sp)
            //1st spinner
            MakeSpinner()
            //"To"
            Text(text = "To", fontSize = 16.sp, modifier = Modifier.padding(PaddingValues(top = 32.dp)))
            //2nd spinner
            MakeSpinner()
        }
    }
    // arrows image
    Column(horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp, end = 75.dp)) {
        Image(painter = painterResource(id = R.drawable.arrows),
            contentDescription = "raws", modifier = Modifier.size(90.dp))
    }
    //amount edittext
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(top = 370.dp)) {

        MakeEditText()

        //button convert
        Button(onClick = {
            //your onclick code here
        }, modifier = Modifier
            .height(80.dp)
            .width(200.dp)
            .padding(top = 32.dp),
           colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1CC773)),
        ) {
            Text(text = "Convert", color = Color.White, fontSize = 22.sp)
        }
        //Row("Result", "USD")
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, end = 32.dp)) {
            Text(text = "Result", fontSize = 16.sp, modifier = Modifier.padding(start = 28.dp))
            Text(text = "AED", fontSize = 16.sp, modifier = Modifier.padding(start = 148.dp))
        }
        //textview(res)
        Text(text = "0.0", fontWeight = FontWeight.Bold, fontSize = 24.sp,
            textAlign = TextAlign.Center,
        modifier = Modifier
            .border(BorderStroke(2.dp, Black))
            .height(56.dp)
            .width(260.dp)
            .padding(top = 10.dp))
        //textview(dev by)
        Text(text = "Dev by @Pryimak", fontSize = 14.sp, fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 105.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ShowConverterDesign(){
    CurrencyConverterScreen()
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MakeSpinner(){
    val options = listOf("AED")

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text("Currency") },
            modifier = Modifier
                .width(128.dp)
                .height(68.dp)
                .padding(PaddingValues(top = 6.dp)),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@Composable
fun MakeEditText(){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        onValueChange = { text = it },
        label = { Text("Amount") }
    )
}



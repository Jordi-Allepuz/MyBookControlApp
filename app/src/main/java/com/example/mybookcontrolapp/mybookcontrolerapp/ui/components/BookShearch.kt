//package com.example.mybookcontrolapp.mybookcontrolerapp.ui.components
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.heightIn
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.Card
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.onGloballyPositioned
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.unit.toSize
//import androidx.navigation.NavHostController
//
//
//@Composable
//fun BookSearch() {
//    var book by rememberSaveable { mutableStateOf("") }
//    AutoComplete(planetaElegido1, onValueChange = { planetaElegido1 = it })
//}
//
//
//@Composable
//fun OverFlowMenuSol2(
//    expanded: Boolean,
//    onDismiss: () -> Unit,
//    navigationController: NavHostController
//) {
//    DropdownMenu(
//        expanded = expanded,
//        onDismissRequest = onDismiss
//    ) {
//        DropdownMenuItem(onClick = { },
//            text = { Text(text = "El Sol") }
//        )
//    }
//}
//
//
//
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AutoComplete(planetaElegido: String, onValueChange: (String) -> Unit) {
//
//    var textFieldSize by remember { mutableStateOf(Size.Zero) }
//
//    var expanded by remember { mutableStateOf(false) }
//
//    val interactionSource = remember { MutableInteractionSource() }
//
//    Column(
//        modifier = Modifier
//            .width(180.dp)
//            .clickable(
//                interactionSource = interactionSource,
//                indication = null,
//                onClick = {
//                    expanded = false
//                }
//            ), verticalArrangement = Arrangement.SpaceAround
//    ) {
//        Row(modifier = Modifier.fillMaxWidth()) {
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//                    .onGloballyPositioned { coordinates ->
//                        textFieldSize = coordinates.size.toSize()
//                    },
//                placeholder = { Text(text = "") },
//                value = planetaElegido,
//                onValueChange = {
//                    onValueChange(it)
//                    expanded = true
//                },
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color(0xFFB854C9),
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    cursorColor = Color.Black
//                ),
//                textStyle = TextStyle(
//                    color = Color.Black,
//                    fontSize = 15.sp,
//                    textAlign = TextAlign.Center,
//                    fontWeight = FontWeight.Bold
//                ),
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Text,
//                    imeAction = ImeAction.Done
//                ),
//                singleLine = true,
//            )
//        }
//
//        AnimatedVisibility(visible = expanded) {
//            Card(
//                modifier = Modifier
//                    .padding(horizontal = 5.dp)
//                    .width(textFieldSize.width.dp),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                LazyColumn(
//                    modifier = Modifier.heightIn(max = 150.dp),
//                ) {
//                    if (bookelegido.isNotEmpty()) {
//                        items(
//                            listaPlanetas.filter { planeta ->
//                                planeta.nombre.lowercase()
//                                    .contains(planetaElegido.lowercase())
//                            }
//                        ) {
//                            PlanetasItem(planeta = it.nombre) { planetaSeleccionado ->
//                                onValueChange(planetaSeleccionado)
//                                expanded = false
//                            }
//                        }
//                    } else {
//                        items(listaPlanetas) {
//                            PlanetasItem(planeta = it.nombre) { planetaSeleccionado ->
//                                onValueChange(planetaSeleccionado)
//                                expanded = false
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//

package com.example.mybookcontrolapp.mybookcontrolerapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.R
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.data.DataModalDrawer
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawer(estadoDrawer: DrawerState, coroutina: CoroutineScope, userInfoViewModel: UserInfoViewModel, navigationController: NavHostController) {

    // Estado para rastrear el ítem seleccionado en el drawer.
    var itemSeleccionado by rememberSaveable { mutableStateOf(0) }

    ModalDrawerSheet(
        drawerShape = RoundedCornerShape(topEnd = 20.dp),
        modifier = Modifier.fillMaxHeight(),
        drawerContainerColor = Color(
            0xFFD5D2D2
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.drawableimage),
                contentDescription = "modalDrawerImage"
            )
            // Itera sobre la lista de opciones del drawer para crear los ítems navegables.
            listaDrawers.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    modifier = Modifier.padding(8.dp),
                    label = { Text(text = item.titulo) },
                    colors= NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent, selectedContainerColor = Color(
                        0xFF918F8F
                    )
                    ),
                    selected = index == itemSeleccionado,
                    onClick = {
                        when(index){
                            0-> navigationController.navigate(Routes.InfoAppScreen.route)
                            1 -> userInfoViewModel.sendEmail("joralljan@alu.edu.gva.es")
                        }
                        itemSeleccionado = index
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == itemSeleccionado) {
                                item.iconoSeleccionado
                            } else {
                                item.iconoDesleccionado
                            }, contentDescription = item.titulo
                        )
                    },
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            // Botón para cerrar el drawer.
            IconButton(
                modifier = Modifier.width(100.dp),
                onClick = { coroutina.launch { estadoDrawer.close() } }) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "cerrar", tint = Color.Black)
                    Text(text = "Cerrar", color = Color.Black)
                }
            }
        }
    }
}





/*
lista de Drawers o iconos, lista que llamaremos a la hora de introducir los modaldrawerItem,
 */

val listaDrawers = listOf(
    DataModalDrawer(
        "Info",
        Icons.Filled.Info,
        Icons.Outlined.Info,

    ),
    DataModalDrawer(
        "Email", Icons.Filled.Mail,
        Icons.Outlined.Mail,

    )
)


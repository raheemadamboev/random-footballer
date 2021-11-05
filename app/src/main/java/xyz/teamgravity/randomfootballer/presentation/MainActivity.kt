package xyz.teamgravity.randomfootballer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import xyz.teamgravity.randomfootballer.presentation.theme.RandomFootballerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewmodel = hiltViewModel<MainViewModel>()
            val state = viewmodel.state.value

            RandomFootballerTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    if (state.loading) { // loading

                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))

                    } else {
                        if (state.footballer != null) {
                            Image(
                                painter = rememberImagePainter(
                                    data = state.footballer.imageUrl,
                                    builder = { crossfade(true) }),
                                contentDescription = "footballer",
                                modifier = Modifier.sizeIn(maxHeight = 200.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.footballer.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = state.footballer.description)
                        }

                        if (state.error != null) {
                            Text(text = state.error)
                        }
                    }

                    Button(
                        onClick = viewmodel::executeGetRandomFootballer,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Get footballer")
                    }
                }
            }
        }
    }
}
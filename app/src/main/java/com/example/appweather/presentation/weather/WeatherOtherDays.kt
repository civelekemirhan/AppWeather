package com.example.appweather.presentation.weather

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appweather.R
import com.example.appweather.ui.theme.TextColor
import com.example.appweather.ui.theme.secondaryWeatherBack
import com.valentinilk.shimmer.shimmer


@Composable
fun WeatherOtherDays(
    modifier: Modifier = Modifier,
    isDataAvailable: Boolean = false,
    maxTemp: Double = 30.0,
    minTemp: Double = 25.0,
    temp: Double = 27.0,
    description: String = "Clearing in the afternoon.",
    date: String = "22-01-2025",
    icon: String = "sun",
) {


        if (isDataAvailable) {
            Card(
                modifier = modifier,
                colors = CardDefaults.cardColors(
                    containerColor = secondaryWeatherBack
                ),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.TextColor)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            date,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .fillMaxHeight(0.4f)
                                .padding(10.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = temp.toInt().toString() + " C°",
                                        color = Color.White,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }

                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight(0.4f)
                                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                        ) {
                            val context = LocalContext.current

                            // Gelen iconName içindeki "-" karakterlerini "_" ile değiştir
                            val formattedIconName = icon.replace("-", "_")

                            // Değiştirilmiş isme göre drawable kaynak ID'sini al
                            val resourceId = context.resources.getIdentifier(
                                formattedIconName,
                                "drawable",
                                context.packageName
                            )

                            if (resourceId != 0) {
                                val painter = painterResource(id = resourceId)
                                Image(
                                    painter = painter,
                                    contentDescription = icon,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Fit
                                )

                            } else {
                                // Eğer kaynak bulunamazsa, placeholder bir metin veya görsel gösterilebilir
                                Image(
                                    painter = painterResource(id = R.drawable.clear_day),
                                    contentDescription = "icon"
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .wrapContentHeight()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Maximum Tempature :",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.weight(0.5f)
                        )
                        Text(
                            text = maxTemp.toInt().toString() + " C°",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .wrapContentHeight()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Minimum Tempature :",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.weight(0.5f)
                        )
                        Text(
                            text = minTemp.toInt().toString() + " C°",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(1f),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = description,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    }

                }

            }
        } else {
            Card(
                modifier = modifier,
                colors = CardDefaults.cardColors(
                    containerColor = secondaryWeatherBack
                ),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.TextColor)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(5.dp).shimmer(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .fillMaxHeight(0.05f)
                            .background(Color.LightGray)
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .fillMaxHeight(0.1f)
                                .background(Color.LightGray)
                                .padding(20.dp)
                        ) {

                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight(0.8f)
                                .background(Color.LightGray)
                                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                        ) {

                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.2f)
                            .background(Color.LightGray)
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.4f)
                            .background(Color.LightGray)
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {

                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.4f)
                            .background(Color.LightGray)
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {

                    }

                }

            }

        }

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun WeatherOtherDaysPreview() {
    WeatherOtherDays()
}
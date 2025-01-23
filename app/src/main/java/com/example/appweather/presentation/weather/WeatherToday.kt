package com.example.appweather.presentation.weather

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appweather.R
import com.example.appweather.ui.theme.mainWeatherBack
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun WeatherToday(
    isDataAvailable: Boolean = false,
    maxTemp: Double = 30.0,
    minTemp: Double = 25.0,
    temp: Double = 27.0,
    description: String = "Clearing in the afternoon.",
    date: String = "",
    location: String = "",
    icon: String = "sun"
) {
    if (isDataAvailable) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(mainWeatherBack)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Location : $location",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Text(
                    "$date",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )

                IconButton(onClick = { }, modifier = Modifier.padding(end = 20.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_map_24),
                        contentDescription = "Map",
                        tint = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(20.dp),
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
                                fontSize = 50.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
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
                                .padding(10.dp)
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
                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(modifier = Modifier.size(150.dp)) {
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
                            Image(painter = painter, contentDescription = icon, modifier = Modifier.fillMaxSize(),contentScale = ContentScale.Fit)

                        } else {
                            // Eğer kaynak bulunamazsa, placeholder bir metin veya görsel gösterilebilir
                            Image(painter = painterResource(id = R.drawable.clear_day), contentDescription = "icon")
                        }
                    }


                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = description,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(mainWeatherBack)
                .shimmer(rememberShimmer(ShimmerBounds.Window)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Location , date , map icon içeren bir topbar
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .fillMaxHeight(0.5f)
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .fillMaxHeight(0.5f)
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.5f)
                        .background(Color.LightGray)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .background(Color.LightGray)
                                .fillMaxWidth(1f)
                                .height(20.dp)
                        ) {

                        }
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .background(Color.LightGray)
                                .fillMaxWidth(1f)
                                .height(20.dp)
                        ) {

                        }
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .background(Color.LightGray)
                                .fillMaxWidth(1f)
                                .height(20.dp)
                        ) {

                        }
                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color.LightGray)
                    ) {

                    }


                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .weight(0.1f)
                    .padding(5.dp)
                    .background(Color.LightGray),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

            }
        }

    }
}


//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//fun PreviewShimmeringPlaceholder() {
//    WeatherToday()
//}
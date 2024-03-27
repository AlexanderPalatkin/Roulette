package com.example.roulette.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roulette.R
import com.example.roulette.ui.theme.Red
import com.example.roulette.utils.NumberUtils
import kotlin.math.roundToInt

@Composable
fun RuleScreen() {
    var rotationValue by remember {
        mutableFloatStateOf(0f)
    }

    var number by remember {
        mutableIntStateOf(0)
    }
    var redBlack by remember {
        mutableStateOf(Color.White)
    }

    val angle by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = 2000
        ), label = "",
        finishedListener = {
            var index = (360 - (it % 360)) / (360f / NumberUtils.list.size)
            if (index.roundToInt() == 37) {
                index = 0f
            }
            number = NumberUtils.list[index.roundToInt()]
            redBlack = if (index.roundToInt() == 0) {
                Color.White
            } else if (index.roundToInt() % 2 == 0) {
                Color.Black
            } else {
                Color.Red
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
                .wrapContentWidth(),
            text = number.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp,
            color = redBlack
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.roulette),
                contentDescription = "roulette",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(angle)
            )
            Image(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "flecha",
                modifier = Modifier.fillMaxSize()
            )
        }
        Button(
            onClick = {
                rotationValue = (720..1080).random().toFloat() + angle
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Start",
                color = Red
            )
        }
    }
}
package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csticorp.culqi.test.data.response.DataUser
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray200
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray500
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryDropdownMenu(
    modifier: Modifier = Modifier,
    list: List<DataUser>,
    onClick: (DataUser, Int) -> Unit,
    borderColor: Color = BlueGray200,
    defaultSelectedIndex: Int = 0
) {
    var isExpanded by remember { mutableStateOf(false) }

    val hasSelected = list.any { it.selected }
    val updatedList = if (!hasSelected) {
        list.mapIndexed { index, option ->
            if (index == defaultSelectedIndex) {
                val selected = option.copy(selected = true)
                onClick(selected, index)
                selected
            } else option
        }
    } else {
        list
    }

    var selectedOption by remember(updatedList) { mutableStateOf(updatedList.firstOrNull { it.selected }) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
    ) {

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
                .menuAnchor()
                .height(54.dp),
            readOnly = true,
            value = selectedOption?.email.orEmpty(),
            onValueChange = { },
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp)
                        )
                        .padding(start = 16.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    innerTextField()
                }
            },
            textStyle = TextStyle(
                color = BlueGray200,
                fontWeight = FontWeight(325),
                lineHeight = 16.sp,
                fontSize = 12.sp,
                fontFamily = PoppinsRegular
            )
        )

        DropdownMenu(
            modifier = Modifier
                .border(
                    width = 1.dp, color = BlueGray200, shape = RoundedCornerShape(8.dp)
                )
                .background(Color.White)
                .exposedDropdownSize(),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            updatedList.forEachIndexed { i, option ->
                DropdownMenuItem(
                    text = {
                        Text12Medium(
                            modifier = Modifier
                                .background(
                                    color = if (option.selected) BlueGray200 else Color.Transparent,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(bottom = 8.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp, vertical = 8.dp),
                            text = option.email,
                            color = BlueGray500,
                        )
                    },
                    onClick = {
                        selectedOption = option
                        isExpanded = false
                        onClick(option.copy(selected = true), i)
                    },
                )
            }
        }
    }
}
package com.viplearner.budgetmanager.ui.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.viplearner.budgetmanager.data.entities.TransactionType
import com.viplearner.budgetmanager.ui.component.BudgetCard
import com.viplearner.budgetmanager.ui.component.ErrorDialog
import com.viplearner.budgetmanager.ui.component.LabeledTextField
import com.viplearner.budgetmanager.ui.component.LoadingScreen
import com.viplearner.budgetmanager.ui.component.rememberCurrencyVisualTransformation
import com.viplearner.budgetmanager.ui.screens.main.state.MainScreenViewState
import com.viplearner.budgetmanager.ui.theme.base0
import com.viplearner.budgetmanager.ui.theme.base60
import com.viplearner.budgetmanager.ui.theme.primary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val viewState by viewModel.viewState.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val isExpanded = sheetState.isVisible
    var amount by remember{
        mutableStateOf("")
    }

    var transactionType by remember{
        mutableStateOf(TransactionType.FOOD)
    }

    Scaffold(
        floatingActionButton = {
            IconButton(
                modifier = Modifier.size(60.dp),
                onClick = {
                    println("Floating button clicked")
                    scope.launch {
                        sheetState.expand()
                    }
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = primary
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = base0,
                    modifier = Modifier.size(50.dp)
                )
            }
        },
        containerColor = base0
    ) {
        if(isExpanded){
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    scope.launch {
                        sheetState.hide()
                    }
                },
                containerColor = base0,
                tonalElevation = 15.dp
            ) {

                Column(modifier = Modifier.padding(20.dp)){
                    LabeledTextField(
                        text = amount,
                        label = "Amount",
                        borderColor = base60,
                        onValueChange = { str ->
                            amount = str
                        },
                        keyboardType = KeyboardType.Number,
                        placeholder = "Input Amount",
                        imeAction = ImeAction.Next,
                        visualTransformation = rememberCurrencyVisualTransformation(currency = "USD")
                    )

                    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
                        var isExpanded1 by remember { mutableStateOf(false) }
                        Card(
                            shape = RoundedCornerShape(5.dp),
                            colors = CardDefaults.cardColors(containerColor = base0),
                            border = BorderStroke(1.dp, base60),
                            onClick = {
                                isExpanded1 = true
                            }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = transactionType.value
                                )

                                IconButton(onClick = { isExpanded1 = true }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowDropDown,
                                        contentDescription = "Add",
                                        tint = primary
                                    )
                                }
                            }
                        }

                        DropdownMenu(
                            modifier = Modifier.align(Alignment.BottomStart),
                            expanded = isExpanded1,
                            onDismissRequest = { isExpanded1 = false }
                        ) {
                            for(type in TransactionType.entries){
                                TextButton(onClick = {
                                    transactionType = type
                                    isExpanded1 = false
                                }) {
                                    Text(text = type.value)
                                }
                            }
                        }
                    }

                    Box(modifier = Modifier.fillMaxWidth()) {
                        TextButton(
                            modifier = Modifier
                                .align(Alignment.BottomEnd),
                            onClick = {
                                println("Add button clicked")
                                viewModel.addTransaction(amount, transactionType)
                                amount = ""
                                scope.launch{ sheetState.hide() }
                            },
                            shape = RoundedCornerShape(10.dp),
                            enabled = amount.isNotEmpty(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = (if(amount.isNotEmpty()) primary else base60).copy(alpha = 0.15f)
                            )
                        ) {
                            Text(
                                text = "Add",
                                color = if(amount.isNotEmpty()) primary else base60,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }
        }

        when(viewState){
            MainScreenViewState.Loading -> {
                LoadingScreen()
            }
            is MainScreenViewState.Loaded -> {
                BudgetCard(
                    modifier = Modifier
                        .padding(it)
                        .padding(20.dp),
                    budgetCategoryList = (viewState as MainScreenViewState.Loaded).budgetCategoryList,
                    monthYearText = (viewState as MainScreenViewState.Loaded).monthYearText,
                    currentMonth = (viewState as MainScreenViewState.Loaded).currentMonth,
                    currentYear = (viewState as MainScreenViewState.Loaded).currentYear,
                    totalBudgetStr = (viewState as MainScreenViewState.Loaded).totalBudgetStr,
                    spentBudgetStr = (viewState as MainScreenViewState.Loaded).spentBudgetStr,
                    remainingBudgetStr = (viewState as MainScreenViewState.Loaded).remainingBudgetStr
                ) {month, year ->
                    viewModel.onMonthYearSelected(month, year)
                }
            }
            is MainScreenViewState.Error -> {
                ErrorDialog(errorMessage = (viewState as MainScreenViewState.Error).errorMessage)
            }
        }




    }

}

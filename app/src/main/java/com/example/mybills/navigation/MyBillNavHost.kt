package com.example.mybills.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mybills.ui.EditExpense.EditExpenseScreen
import com.example.mybills.ui.addEditExpense.AddExpenseScreen
import com.example.mybills.ui.expenses.ExpenseScreen

@Composable
fun MyBuillsNavHost(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ExpenseRoute,
        modifier = modifier
    ){
        composable<ExpenseRoute> {
            ExpenseScreen(
                onNewExpense = {
                    navController.navigate(NewExpenseRoute) {
                        launchSingleTop = true
                    }
                },
                onEditExpense = { expenseId ->
                    navController.navigate(EditExpenseRoute(expenseId)) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NewExpenseRoute> {
            AddExpenseScreen(
                title = "Nova despesa",
                onBack = {
                    navController.popBackStack()
                }
            )

        }

        composable<EditExpenseRoute> {
                backStackEntry ->
            val route = backStackEntry.toRoute<EditExpenseRoute>()

            EditExpenseScreen(
                title = "Editar despesa",
                expenseId = route.expenseId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        }


}
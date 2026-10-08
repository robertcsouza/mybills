package com.example.mybills.ui.expenses


data class Expense(
    val id: Long,
    val description: String,
    val amountInCents: Long,
    val category: String,
    val dateLabel: String
)
 val sampleExpenses = listOf(
    Expense(
        id = 1L,
        description = "Mercado",
        amountInCents = 18_490L,
        category = "Alimentação",
        dateLabel = "04 out"
    ),
    Expense(
        id = 2L,
        description = "Combustível",
        amountInCents = 15_000L,
        category = "Transporte",
        dateLabel = "03 out"
    ),
    Expense(
        id = 3L,
        description = "Aluguel",
        amountInCents = 85_000L,
        category = "Moradia",
        dateLabel = "02 out"
    ),
    Expense(
        id = 4L,
        description = "Cinema",
        amountInCents = 6_000L,
        category = "Lazer",
        dateLabel = "02 out"
    ),
    Expense(
        id = 5L,
        description = "Farmácia",
        amountInCents = 4_000L,
        category = "Saúde",
        dateLabel = "01 out"
    )
)

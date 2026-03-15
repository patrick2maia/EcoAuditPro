package com.example.ecoauditpro.data

public data class ComplianceItem(
    val id: Int,
    val title: String,
    val description: String,
    val weight: Int = 1,
    var isChecked: Boolean = false
)
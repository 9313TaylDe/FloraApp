package com.example.floraapp.data.dto

data class ErroResponseDto(
    val error: Boolean,
    val code: String,
    val message: String,
    val messages: String,
    val details: Map<String, Any>?
)
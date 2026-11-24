package com.example.floraapp.data.remote.dto

data class DataSetDto(
    val version: String,
    val released_at: String,
    val licence: String,
    val licence_url: String,
    val citation_url: String,
    val changelog_url: String
)

package com.petproject.snackbarfromanywhere.core.domain.model

data class SnackBarAction(
    val name: String,
    val action: suspend () -> Unit
)

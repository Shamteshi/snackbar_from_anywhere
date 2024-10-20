package com.petproject.snackbarfromanywhere.core.domain.model

data class SnackBarEvent(
    val snackBarMessage: String,
    val snackBarAction: SnackBarAction? = null
)

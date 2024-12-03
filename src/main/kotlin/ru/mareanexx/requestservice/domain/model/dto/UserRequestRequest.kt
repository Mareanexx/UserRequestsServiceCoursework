package ru.mareanexx.requestservice.domain.model.dto

data class UserRequestRequest(
    val messageTitle: String,
    val message: String,
    val idUser: Int // Foreign Key to user
)
package ru.mareanexx.requestservice.domain.model.dto

import ru.mareanexx.requestservice.domain.model.type.UserRequestStatus
import java.time.LocalDateTime

data class UserRequestResponse(
    val idRequest: Int,
    val messageTitle: String,
    val message: String,
    val createdAt: LocalDateTime,
    val status: String,
    val idUser: Int // Foreign Key to user
)
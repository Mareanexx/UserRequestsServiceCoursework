package ru.mareanexx.requestservice.domain.model.dto

import java.time.LocalDateTime
data class AdminUserRequests(
    val idUser: Int?,
    val username: String,
    val email: String,
    val phoneNumber: String?,
    val idRequest: Int?,
    val messageTitle: String?,
    val message: String?,
    val createdAt: LocalDateTime?,
    val statusDate: LocalDateTime?,
    val status: String
)

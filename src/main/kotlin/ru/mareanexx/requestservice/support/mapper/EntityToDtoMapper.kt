package ru.mareanexx.requestservice.support.mapper

import ru.mareanexx.requestservice.domain.model.dto.UserRequestResponse
import ru.mareanexx.requestservice.domain.model.entity.UserRequestEntity

fun UserRequestEntity.toDto() : UserRequestResponse = UserRequestResponse(
    idRequest = idRequest!!,
    messageTitle = messageTitle,
    message = message,
    createdAt = createdAt,
    status = status,
    idUser = idUser
)
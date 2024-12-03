package ru.mareanexx.requestservice.domain.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import ru.mareanexx.requestservice.domain.model.type.UserRequestStatus
import java.time.LocalDateTime


@Table(name = "user_request")
data class UserRequestEntity(
    @Id
    val idRequest: Int? = null,
    val messageTitle: String,
    val message: String,
    val createdAt: LocalDateTime,
    var statusDate: LocalDateTime,
    val idUser: Int, // Foreign Key to user
    var status : String = UserRequestStatus.OPEN.name // Enum: OPEN, CLOSED
)

package ru.mareanexx.requestservice.domain.service

import org.springframework.stereotype.Service
import ru.mareanexx.requestservice.domain.model.dto.AdminUserRequests
import ru.mareanexx.requestservice.domain.model.dto.UserRequestRequest
import ru.mareanexx.requestservice.domain.model.dto.UserRequestResponse
import ru.mareanexx.requestservice.domain.model.entity.UserRequestEntity
import ru.mareanexx.requestservice.domain.model.type.UserRequestStatus
import ru.mareanexx.requestservice.domain.repository.UserRequestRepository
import ru.mareanexx.requestservice.support.exceptions.WrongIDArgument
import ru.mareanexx.requestservice.support.mapper.toDto
import java.time.LocalDateTime

@Service
class UserRequestService(
    private val userRequestRepository: UserRequestRepository
) {
    /*
        Получение обращения пользователя по ID
        Пока не используется.
    */
    fun getUserRequestById(idUserRequest: Int): UserRequestResponse? {
        return userRequestRepository.findById(idUserRequest).orElse(null)?.toDto()
    }

    /*
        Получение всех обращений пользователя по ID_user
        Для пользователя в панели всех обращений.
        DTO возврат для пользователя (без status_date)
        Обыграть логику когда нет обращений пользователя - ПУСТОЙ ЛИСТ ВЕРНЕТСЯ.
    */
    fun getAllRequestsOfUser(idUser: Int) : List<UserRequestResponse> {
        return userRequestRepository.findAllRequestsOfUser(idUser).orEmpty()
    }

    /*
        Создание нового обращения в поддержку.
        Для пользователя в панели обращений.
        DTO возврат из которого берутся данные (без status_date)
    */
    fun createNewRequest(userRequest: UserRequestRequest): Boolean {
        val newUserRequest = UserRequestEntity(
            messageTitle = userRequest.messageTitle,
            message = userRequest.message,
            createdAt = LocalDateTime.now(),
            statusDate = LocalDateTime.now(),
            status = UserRequestStatus.OPEN.name,
            idUser = userRequest.idUser
        )
        println("New userEntity in service $newUserRequest")
        return try {
            userRequestRepository.save(newUserRequest)
            true
        } catch (e: Exception) {
            println("Cant save the userRequest as ${e.message}")
            println(e.printStackTrace())
            false
        }
    }

    /*
        Получение всех обращений пользователей.
        Для админ-панели.
    */
    fun getAllUsersRequestsForAdmin(): List<AdminUserRequests> {
        return userRequestRepository.findAllUserRequestsWithGrouping() ?: emptyList()
    }

    /*
        Ответить на обращение пользователя.
        (изменить статус с OPEN to CLOSED
        Для админ-панели.
     */
    fun answerToUserRequest(idUserRequest: Int) : UserRequestEntity {
        val userRequest = userRequestRepository.findById(idUserRequest).orElseThrow {
            WrongIDArgument("User with ID '$idUserRequest' is not found")
        }

        userRequest.status = UserRequestStatus.RESOLVED.name
        userRequest.statusDate = LocalDateTime.now()
        userRequestRepository.save(userRequest)
        return userRequest
    }

}
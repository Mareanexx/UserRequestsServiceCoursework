package ru.mareanexx.requestservice.api.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import ru.mareanexx.requestservice.domain.model.dto.AdminUserRequests
import ru.mareanexx.requestservice.domain.model.dto.UserRequestRequest
import ru.mareanexx.requestservice.domain.model.dto.UserRequestResponse
import ru.mareanexx.requestservice.domain.model.entity.UserRequestEntity
import ru.mareanexx.requestservice.domain.service.UserRequestService
import ru.mareanexx.requestservice.support.exceptions.WrongIDArgument

@RestController
@RequestMapping("/api/request")
class UserRequestController(private val userRequestService: UserRequestService) {

    // проверено на сущ user и не сущ user
    @GetMapping("/list")
    fun getAllUserRequests(@RequestParam idUser: Int) : ResponseEntity<List<UserRequestResponse>> {
        return ResponseEntity(userRequestService.getAllRequestsOfUser(idUser), HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createNewRequest(@RequestBody userRequest: UserRequestRequest) : ResponseEntity<String> {
        val createdRequest = userRequestService.createNewRequest(userRequest)
        return if (createdRequest) {
            ResponseEntity("User request successfully created!", HttpStatus.CREATED)
        }
        else {
            ResponseEntity("Cant create new user request!", HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/admin/answer")
    fun answerToUserRequest(@RequestParam idUserRequest: Int) : ResponseEntity<UserRequestEntity?> {
        return try {
            val userRequest = userRequestService.answerToUserRequest(idUserRequest)
            ResponseEntity(userRequest, HttpStatus.OK)
        } catch (e: WrongIDArgument) {
            println(e.message)
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/admin/list")
    fun getAllOpenUserRequests() : List<AdminUserRequests> = userRequestService.getAllUsersRequestsForAdmin()
}
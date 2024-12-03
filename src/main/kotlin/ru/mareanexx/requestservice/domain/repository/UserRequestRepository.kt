package ru.mareanexx.requestservice.domain.repository

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.mareanexx.requestservice.domain.model.dto.AdminUserRequests
import ru.mareanexx.requestservice.domain.model.dto.UserRequestResponse
import ru.mareanexx.requestservice.domain.model.entity.UserRequestEntity

@Repository
interface UserRequestRepository : CrudRepository<UserRequestEntity, Int> {

    @Query("""
        SELECT id_request, message_title, message, created_at, status, id_user
        FROM user_request
        WHERE id_user = :idUser
        ORDER BY created_at DESC;
    """)
    fun findAllRequestsOfUser(@Param("idUser") idUser: Int) : List<UserRequestResponse>?


    @Query("""
    SELECT 
        u.id_user,
        u.username,
        u.email,
        u.phone_number,
        r.id_request,
        r.message_title,
        r.message,
        r.created_at,
        r.status_date,
        r.status
    FROM "user" u
    JOIN user_request r ON u.id_user = r.id_user
    WHERE r.status = 'OPEN'
    ORDER BY u.id_user, r.created_at DESC;
    """)
    fun findAllUserRequestsWithGrouping(): List<AdminUserRequests>?
}
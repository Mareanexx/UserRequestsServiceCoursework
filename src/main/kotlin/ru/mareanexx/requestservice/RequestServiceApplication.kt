package ru.mareanexx.requestservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories

@EnableJdbcRepositories
@SpringBootApplication
class RequestServiceApplication

fun main(args: Array<String>) {
	runApplication<RequestServiceApplication>(*args)
}

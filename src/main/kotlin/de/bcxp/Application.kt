package de.bcxp

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        configureRouting()
    }.start(wait = true)
}

fun Application.configureRouting() {
    routing {
        get("/convert") {
            val input = call.request.queryParameters["input"] ?: ""
            if (input.isCamelCase()) {
                call.respondText(input.toSnakeCase())
            } else if (input.isSnakeCase()) {
                call.respondText(input.snakeToCamelCase())
            } else {
                call.respond(HttpStatusCode.BadRequest, "Input is neither camelCase nor snake_case")
            }
        }
    }
}

fun String.isCamelCase(): Boolean {
    val camelCaseRegex = "^[a-z]+(?:[A-Z][a-z]*)*$".toRegex()
    return camelCaseRegex.matches(this)
}

fun String.isSnakeCase(): Boolean {
    val snakeCaseRegex = "^[a-z]+(?:_[a-z0-9]+)*$".toRegex()
    return snakeCaseRegex.matches(this)
}

fun String.toSnakeCase(): String {
    return this.replace(Regex("([a-z])([A-Z])"), "$1_$2").lowercase()
}

fun String.snakeToCamelCase(): String {
    return this.split("_").joinToString("") { it.lowercase().replaceFirstChar { char -> char.uppercase() } }.replaceFirstChar { it.lowercase() }
}
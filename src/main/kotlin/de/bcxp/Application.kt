package de.bcxp

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.cors.routing.*

fun main() {
    embeddedServer(Netty, port = 8080){
        install(CORS) {
            allowMethod(HttpMethod.Get)
            allowHeader(HttpHeaders.ContentType)
            allowHeader(HttpHeaders.Authorization)
            anyHost()
        }
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
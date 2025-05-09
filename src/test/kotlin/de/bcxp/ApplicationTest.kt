package de.bcxp

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ApplicationTest {

    @Test
    fun `test isCamelCase`() {
        assertTrue("camelCase".isCamelCase())
        assertTrue("thisIsCamelCase".isCamelCase())
        assertFalse("snake_case".isCamelCase())
        assertFalse("PascalCase".isCamelCase())
        assertFalse("CamelCase".isCamelCase()) // Test for leading uppercase
        assertFalse("".isCamelCase()) // Test for empty string
        assertFalse("123camelCase".isCamelCase()) // Test for leading numbers
        assertTrue("a".isCamelCase()) // Test for single lowercase letter
    }

    @Test
    fun `test isSnakeCase`() {
        assertTrue("snake_case".isSnakeCase())
        assertTrue("this_is_snake_case".isSnakeCase())
        assertFalse("camelCase".isSnakeCase())
        assertFalse("PascalCase".isSnakeCase())
        assertFalse("not_snake_case_".isSnakeCase())
        assertFalse("".isSnakeCase())
        assertFalse("123_snake_case".isSnakeCase()) // Test for leading numbers
    }

    @Test
    fun `test configureRouting`() = testApplication {
        application {
            configureRouting()
        }

        // Test for camelCase input
        client.get("/convert?input=camelCase").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("camel_case", bodyAsText())
        }

        // Test for snake_case input
        client.get("/convert?input=snake_case").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("snakeCase", bodyAsText())
        }

        // Test for invalid input
        client.get("/convert?input=invalid-input").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
            assertEquals("Input is neither camelCase nor snake_case", bodyAsText())
        }

        // Test for empty input
        client.get("/convert").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
            assertEquals("Input is neither camelCase nor snake_case", bodyAsText())
        }
    }

    @Test
    fun `test toSnakeCase`() {
        assertEquals("camel_case", "camelCase".toSnakeCase())
        assertEquals("this_is_snake_case", "thisIsSnakeCase".toSnakeCase())
        assertEquals("a", "a".toSnakeCase()) // Test for single lowercase letter
        assertEquals("", "".toSnakeCase()) // Test for empty string
    }

    @Test
    fun `test snakeToCamelCase`() {
        assertEquals("snakeCase", "snake_case".snakeToCamelCase())
        assertEquals("thisIsSnakeCase", "this_is_snake_case".snakeToCamelCase())
        assertEquals("a", "a".snakeToCamelCase()) // Test for single lowercase letter
        assertEquals("", "".snakeToCamelCase()) // Test for empty string
        assertEquals("abc", "ABC".snakeToCamelCase()) // Test for empty string
        assertEquals("snakeCase123", "snake_case_123".snakeToCamelCase()) // Test with numbers
    }
}
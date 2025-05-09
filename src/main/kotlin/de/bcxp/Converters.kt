package de.bcxp

fun String.toSnakeCase(): String {
    return this.replace(Regex("([a-z])([A-Z])"), "$1_$2").lowercase()
}

fun String.snakeToCamelCase(): String {
    return this.split("_").joinToString("") { it.lowercase().replaceFirstChar { char -> char.uppercase() } }.replaceFirstChar { it.lowercase() }
}
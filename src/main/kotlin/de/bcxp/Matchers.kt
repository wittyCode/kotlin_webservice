package de.bcxp

fun String.isCamelCase(): Boolean {
    val camelCaseRegex = "^[a-z]+(?:[A-Z][a-z]*)*$".toRegex()
    return camelCaseRegex.matches(this)
}

fun String.isSnakeCase(): Boolean {
    val snakeCaseRegex = "^[a-z]+(?:_[a-z0-9]+)*$".toRegex()
    return snakeCaseRegex.matches(this)
}
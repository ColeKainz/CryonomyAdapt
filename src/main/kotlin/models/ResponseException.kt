package models

import java.lang.RuntimeException

data class ResponseException(
    val code: String,
    override val message: String
): RuntimeException(message)

package models

import java.lang.RuntimeException

data class ResponseException(
    val code: Int,
    override val message: String
): RuntimeException(message)

package exception

import kotlinx.serialization.Serializable


@Serializable
data class ErrorResponse (val message: String ) {
    companion object {
        val NOT_FOUND = ErrorResponse("Not found")
        val BAD_REQUEST = ErrorResponse("Bad request")
    }
}

class PlayerNotFoundException(message: String) : Exception(message)
class DatabaseUpdateException(message: String) : Exception(message)

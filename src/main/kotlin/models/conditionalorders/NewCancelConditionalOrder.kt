package models.conditionalorders

data class NewCancelConditionalOrder (
        val type: ConditionalOrderNewCancelType,
        val id: String
)

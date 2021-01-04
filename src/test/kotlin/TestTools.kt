import io.mockk.mockk
import io.mockk.mockkClass
import kotlin.reflect.KProperty

object TestTools {
    inline fun <reified S: Any> mockClient(adapter: Any, client: KProperty<S>): S {
        val mock = mockkClass(S::class)
        val field = adapter::class.java.getField(client.name)
        field.isAccessible = true
        field.set(adapter, mock)
        return mock
    }
}
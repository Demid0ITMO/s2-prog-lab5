package serializationStrategies

import collectionObjectsClasses.Route
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import serializationStrategies.utils.Strategy

/***
 * Обертка для json сериализации
 * @author Demid0
 * @since 1.0
 */
class JsonStrategy: Strategy {
    override fun decode(str: String): MutableCollection<Route> = Json.decodeFromString(str)
    override fun encode(collection: MutableCollection<Route>) = Json.encodeToString(collection)
    override fun toString(): String = "Json"
}
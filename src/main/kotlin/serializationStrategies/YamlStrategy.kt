package serializationStrategies

import collectionObjectsClasses.Route
import com.charleskorn.kaml.Yaml
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import serializationStrategies.utils.Strategy

/***
 * Обертка для yaml сериализации
 * @author Demid0
 * @since 1.0
 */
class YamlStrategy : Strategy {
    override fun decode(str: String) = Yaml.default.decodeFromString<MutableCollection<Route>>(str)
    override fun encode(collection: MutableCollection<Route>) = Yaml.default.encodeToString(collection)
    override fun toString() = "Yaml"
}
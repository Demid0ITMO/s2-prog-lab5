package serializationStrategies.utils

import collectionObjectsClasses.Route

/***
 * Обертка для разных типов сериализации
 * @author Demid0
 * @since 1.0
 */
interface Strategy {
    fun decode(str: String): MutableCollection<Route>
    fun encode(collection: MutableCollection<Route>): String
    override fun toString(): String

}
package serializationStrategies.utils

import collectionObjectsClasses.Route
import serializationStrategies.JsonStrategy
import serializationStrategies.YamlStrategy

/***
 * Класс, занимающийся сериализацией и десериализацией
 * @author Demid0
 * @since 1.0
 * @param strategies
 * Поддерживаемые типы стратегий
 * @param chosenStrategy
 * Выбраная стратегия (По умолчанию: JsonStrategy)
 */
class Serializator {
    private var strategies: HashMap<String, Strategy> = hashMapOf()
    private var chosenStrategy: Strategy = JsonStrategy()

    init {
        addStrategy("jsonstrategy", JsonStrategy())
        addStrategy("yamlstrategy", YamlStrategy())
    }
    fun serialize(collection: MutableCollection<Route>) = chosenStrategy.encode(collection)
    fun deserialize(string: String) = chosenStrategy.decode(string)

    fun addStrategy(name: String, strategy: Strategy) {
        strategies[name] = strategy
    }

    fun getStrategy(name: String) = strategies[name]
    fun getStrategies() = strategies

    fun changeStrategy(strategy: Strategy) {
        chosenStrategy = strategy
    }

    fun getChosenStrategy() = chosenStrategy
}
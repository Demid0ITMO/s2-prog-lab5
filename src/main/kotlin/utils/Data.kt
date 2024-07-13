package utils

import collectionObjectsClasses.Route
import kotlinx.serialization.Serializable

/***
 * Класс, работающий с коллекцией
 * @author Demid0
 * @since 1.0
 * @param collection
 * Поле, в котором хранится коллекция
 * @param supportedCollectionTypes
 * Поддерживаемые типы коллекции
 * @param fileName
 * Имя файла, в который сохраняется коллекция
 * @param infoFileName
 * Имя файла, в который сохраняется информация о коллекции
 */
class Data {
    @Serializable
    var collection: MutableCollection<Route> = ArrayDeque()
    private var supportedCollectionTypes: HashMap<String, MutableCollection<Route>> = hashMapOf()
    private var fileName = System.getenv("lab5_filename")
    private var infoFileName = System.getenv("lab5_collection_info_file")

    init {
        addSupportedCollectionType("arraylist", ArrayList())
        addSupportedCollectionType("arraydeque", ArrayDeque())
    }
    fun changeType(newType: String) {
        if (collection == getSupportedCollectionTypes()[newType]!!) return
        val old = collection
        collection = supportedCollectionTypes[newType.lowercase()]!!
        for (element in old) {
            collection.add(element)
        }
    }
    fun addSupportedCollectionType(name: String, collection: MutableCollection<Route>) {
        supportedCollectionTypes[name] = collection
    }
    fun getFileName() = fileName
    fun getInfoFileName() = infoFileName
    fun getSupportedCollectionTypes() = supportedCollectionTypes

}
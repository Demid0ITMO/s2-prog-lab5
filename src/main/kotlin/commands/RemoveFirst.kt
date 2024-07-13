package commands

import collectionObjectsClasses.Route
import commands.utils.Command
import commands.utils.CommandType
import java.util.NoSuchElementException
/***
 * remove_first : удалить первый элемент из коллекции
 * @author Demid0
 * @since 1.0
 */
class RemoveFirst: Command(CommandType.NO_ARG) {
    override fun execute(singleArg: String?, objectArg: Route?): String {
        return try {
            data.collection.remove(data.collection.first())
            "Done!"
        }
        catch (e: NoSuchElementException) { "Collection is empty" }
    }
}

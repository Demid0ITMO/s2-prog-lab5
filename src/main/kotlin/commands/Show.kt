package commands

import collectionObjectsClasses.Route
import commands.utils.Command
import commands.utils.CommandType

/***
 * show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
 * @author Demid0
 * @since 1.0
 */
class Show: Command(CommandType.NO_ARG) {
    override fun execute(singleArg: String?, objectArg: Route?): String {
        val collection = data.collection
        return if (collection.isEmpty()) "Collection is empty :("
        else {
            var out = "Collection:\n"
            for (element in collection) {
                out += element.toString() + "\n"
            }
            out.dropLast(1)
        }
    }
}

package commands

import collectionObjectsClasses.Route
import commands.utils.Command
import commands.utils.CommandType

/***
 * clear : очистить коллекцию
 * @author Demid0
 * @since 1.0
 */
class Clear: Command(CommandType.NO_ARG) {
    override fun execute(singleArg: String?, objectArg: Route?): String {
        data.collection.clear()
        return "Done!"
    }
}

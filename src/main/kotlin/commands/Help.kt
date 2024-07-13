package commands

import collectionObjectsClasses.Route
import commands.utils.Command
import commands.utils.CommandType

/***
 * help : вывести справку по доступным командам
 * @author Demid0
 * @since 1.0
 */
class Help: Command(CommandType.NO_ARG) {
    override fun execute(singleArg: String?, objectArg: Route?): String {
        val commands = commandParser.getCommands()
        return if (commands.isEmpty()) "No commands"
        else {
            var out = "You can use this commands:\n"
            for (command in commands.toSortedMap()) {
                out += command.key + "\n"
            }
            out.dropLast(1)
        }
    }
}

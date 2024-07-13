package commands.utils

import collectionObjectsClasses.Coordinates
import collectionObjectsClasses.Location
import collectionObjectsClasses.Route
import commands.*

class CommandParser {
    private var commands: HashMap<String, Command> = HashMap()
    init {
        addCommand("exit", Exit())
        addCommand("help", Help())
        addCommand("info", Info())
        addCommand("show", Show())
        addCommand("add", Add())
        addCommand("update", UpdateId())
        addCommand("remove_by_id", RemoveById())
        addCommand("clear", Clear())
        addCommand("save", Save())
        addCommand("execute_script", ExecuteScript())
        addCommand("remove_first", RemoveFirst())
        addCommand("add_if_max", AddIfMax())
        addCommand("remove_lower", RemoveLower())
        addCommand("count_by_distance", CountDistance { a: Double, b: Double -> a == b })
        addCommand("count_less_than_distance", CountDistance { a: Double, b: Double -> a < b })
        addCommand("print_field_descending_distance", PrintFieldDescendingDistance())
        addCommand("change_collection_type", ChangeCollectionType())
        addCommand("change_serialization_strategy", ChangeSerializationStrategy())
    }
    fun getCommands() = commands

    fun addCommand(name: String, command: Command) {
        commands.put(name, command)
    }

    fun parse(args: MutableList<String>): CommandPacket {
        return if (args.isEmpty()) CommandPacket(null, null, null)
        else {
            val commandName = args[0]
            val command = commands[commandName]
            if (command == null) CommandPacket(null, null, null)
            else {
                when (command.type) {
                    CommandType.NO_ARG -> CommandPacket(command, null, null)
                    CommandType.SINGLE_ARG -> CommandPacket(command, args[1], null)
                    CommandType.OBJECT_ARG -> CommandPacket(command, null,
                        Route("", Coordinates(null, null), null, Location(1, 1f, 1, ""), 4.0))

                    CommandType.MIXED_ARG -> CommandPacket(command, args[1], Route("", Coordinates(null, null), null, Location(1, 1f, 1, ""), 4.0))
                }
            }
        }
    }
}
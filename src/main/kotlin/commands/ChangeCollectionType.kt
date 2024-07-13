package commands

import collectionObjectsClasses.Route
import commands.utils.Command
import commands.utils.CommandType

/***
 * change_collection_type type : поменять тип коллекции
 * @author Demid0
 * @since 1.0
 */
class ChangeCollectionType: Command(CommandType.SINGLE_ARG) {

    override fun execute(singleArg: String?, objectArg: Route?): String {
        return try {
            val newType = singleArg!!
            data.changeType(newType)
            "Changed"
        } catch (e: NullPointerException) {
            "Unsupported collection type\n${printSupportedTypes()}"
        } catch (e: IndexOutOfBoundsException) {
            "Empty input\n${printSupportedTypes()}"
        }
    }

    private fun printSupportedTypes() : String {
        var out = "You can use this types:\n"
        for (type in data.getSupportedCollectionTypes()) {
            out += type.key +"\n"
        }
        return out.dropLast(1)
    }
}
package commands

import collectionObjectsClasses.Route
import commands.utils.Command
import commands.utils.CommandType
import java.lang.Exception
/***
 * update id {element} : обновить значение элемента коллекции, id которого равен заданному
 * @author Demid0
 * @since 1.0
 */
class UpdateId: Command(CommandType.MIXED_ARG) {
    override fun execute(singleArg: String?, objectArg: Route?): String {
        val id: Long = singleArg!!.toLong()
        val route = objectArg!!
        return try {
            var bool = false
            for (element in data.collection) {
                if (element.getId() == id) {
                    element.update(
                        name = route.getName(),
                        coordinates = route.getCoordinates(),
                        from = route.getFrom(),
                        to = route.getTo(),
                        distance = route.getDistance()
                    )
                    bool = true
                    break
                }
            }
            if (bool) "Done!"
            else "No element with this id"
        } catch (e: Exception) {
            "Wrong id format."
        }
    }

}

package commands

import collectionObjectsClasses.Route
import commands.utils.Command
import commands.utils.CommandType
import java.io.File
import java.io.PrintWriter
import kotlin.system.exitProcess

/***
 * exit : завершить программу (без сохранения в файл)
 * @author Demid0
 * @since 1.0
 */
class Exit: Command(CommandType.NO_ARG) {
    override fun execute(singleArg: String?, objectArg: Route?): String {
        val file = File(data.getInfoFileName())
        val fileWriter = PrintWriter(file)
        fileWriter.println(data.collection.javaClass.simpleName.lowercase())
        fileWriter.flush()
        fileWriter.println(serializator.getChosenStrategy().javaClass.simpleName.lowercase())
        fileWriter.flush()
        exitProcess(0)
    }
}
package commands

import collectionObjectsClasses.Route
import commands.utils.Command
import commands.utils.CommandType
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

/***
 * execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 * @author Demid0
 * @since 1.0
 */
class ExecuteScript: Command(CommandType.SINGLE_ARG) {
    override fun execute(singleArg: String?, objectArg: Route?): String {
        try {
            val file_name = singleArg!!
            if (scriptStack.contains(file_name)) return "No way! Recursion."

            val file = File(file_name)
            val fileReader = BufferedReader(InputStreamReader(file.inputStream()))

            scriptStack.add(file_name)

            try {
                app.run(fileReader, writer)
            } catch (_: NullPointerException) {
                scriptStack.remove(file_name)

                return "Script $file_name is done."
            }
        } catch (_: Exception) {
            return "File not found"
        }
        return ""
    }
}
/***
 * example
 * /home/demid/Desktop/Itmo/programming/Lab5/script*
 *
 */

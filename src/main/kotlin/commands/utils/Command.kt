package commands.utils

import collectionObjectsClasses.Route
import utils.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import serializationStrategies.utils.Serializator
import java.io.PrintWriter
import java.util.*
/***
 * Абстрактный класс команды
 * @author Demid0
 * @since 1.0
 */
abstract class Command(val type: CommandType) : KoinComponent {
    internal val app: App by inject()
    internal val data: Data by inject()
    internal val serializator: Serializator by inject()
    internal val scriptStack: Stack<String> by inject()
    internal val commandParser: CommandParser by inject()
    private val printWriterManager: WriterManager<PrintWriter> by inject()
    internal var writer = printWriterManager.get()
    abstract fun execute(singleArg: String?, objectArg: Route?) : String
}

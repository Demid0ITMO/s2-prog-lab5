import utils.*
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import java.io.BufferedReader
import java.io.PrintWriter

/***
 * Точка входа в программу
 */
fun main(args: Array<String>) {
    startKoin {
        modules(koinModule)
    }
    val utilFabric = UtilFabric()
    val app: App by utilFabric.inject()
    val starter: Starter by utilFabric.inject()
    val bufferedReaderManager: ReaderManager<BufferedReader> by utilFabric.inject()
    val reader = bufferedReaderManager.get()
    val printWriterManager: WriterManager<PrintWriter> by utilFabric.inject()
    val writer = printWriterManager.get()

    starter.downloadLastSystemCondition()
    try {
        app.run(reader, writer)
    } catch (_: NullPointerException) {
        System.err.println("Зачем вы Ctrl+D нажали?")
    }
}

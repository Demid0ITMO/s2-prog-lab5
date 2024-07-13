package utils

import commands.utils.CommandParser
import commands.utils.Invoker
import org.koin.core.component.KoinComponent
import org.koin.dsl.module
import serializationStrategies.utils.Serializator
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

/***
 * Модуль для Koin DI
 * @author Demid0
 * @since 1.0
 */
val koinModule = module {

    single { Starter() }

    single { Asker() }

    single { WriterManager(PrintWriter(System.out, true)) }

    single { ReaderManager(BufferedReader(InputStreamReader(System.`in`))) }

    single { Invoker() }

    single { Serializator() }

    single { Data() }

    single { Stack<String>() }

    single { CommandParser() }

    single { App() }

}

/***
 * Класс, экземпляр которого используется для инъекции зависимостей в main
 * @author Demid0
 * @since 1.0
 */
class UtilFabric: KoinComponent
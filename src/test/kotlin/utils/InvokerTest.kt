package utils

import commands.Info
import commands.utils.CommandParser
import commands.utils.Invoker
import org.junit.jupiter.api.Test

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import java.text.SimpleDateFormat
import java.util.*

class InvokerTest {

    @Test
    fun invoke() {
        startKoin {modules(koinModule)}
        val invoker = Invoker()
        val commandParser = CommandParser()
        val newCommand = Info()
        val newCommandName = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())
        commandParser.addCommand(newCommandName, newCommand)
        val commandPacket = commandParser.parse(arrayListOf(newCommandName))
        assert(invoker.invoke(commandPacket).isNotEmpty())
        stopKoin()
    }
}
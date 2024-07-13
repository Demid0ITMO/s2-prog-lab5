package utils

import commands.utils.CommandParser
import commands.utils.Invoker
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.BufferedReader
import java.io.PrintWriter
import java.util.*

class App: KoinComponent {
    private val invoker: Invoker by inject()
    private val asker: Asker by inject()
    private val commandParser: CommandParser by inject()
    private val scriptStack: Stack<String> by inject()
    private val nullWriter = PrintWriter("/dev/null")

    fun run(inputReader: BufferedReader, outputWriter: PrintWriter) {
        while (true) {
            var writer = outputWriter
            val reader = inputReader
            val commandReturnWriter = writer
            writer = if (scriptStack.isEmpty()) writer else nullWriter
            writer.print("> ")
            writer.flush()
            val input = reader.readLine().split(" ").toMutableList()
            input.removeAll(setOf("", { input.size }))
            val commandPacket = commandParser.parse(input)
            if (commandPacket.command == null) {
                writer.print("Command not found.\n")
                writer.flush()
                continue
            }
            if (commandPacket.objectArg != null) commandPacket.objectArg = asker.askRoute(reader, writer)
            val output = invoker.invoke(commandPacket)
            commandReturnWriter.println(output)
            commandReturnWriter.flush()
        }
    }
}
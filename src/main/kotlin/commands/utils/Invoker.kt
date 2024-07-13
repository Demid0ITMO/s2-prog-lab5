package commands.utils

import commands.*
import org.koin.core.component.KoinComponent

/***
 * Класс, вызывающий команды
 * @author Demid0
 * @since 1.0
 * @param commands
 * Поддерживаемые типы комманд
 */
class Invoker: KoinComponent {
    fun invoke(commandPacket: CommandPacket): String {
        return commandPacket.command!!.execute(commandPacket.singleArg, commandPacket.objectArg)
    }

}
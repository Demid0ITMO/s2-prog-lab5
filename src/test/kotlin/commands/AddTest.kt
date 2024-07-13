package commands

import collectionObjectsClasses.Coordinates
import collectionObjectsClasses.Location
import collectionObjectsClasses.Route
import commands.utils.CommandPacket
import commands.utils.Invoker
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import utils.Data
import utils.UtilFabric
import utils.koinModule

class AddTest {

    @Test
    fun execute() {
        startKoin { modules(koinModule) }
        val utilFabric = UtilFabric()
        val command = Add()
        val route = Route("sd", Coordinates(1.0f, 0), null, Location(1, 1.0f, 1, "f"), 5.0)
        val invoker: Invoker by utilFabric.inject()
        val data: Data by utilFabric.inject()
        val count = data.collection.size + 1
        val commandPacket = CommandPacket(command, null, route)
        val output = invoker.invoke(commandPacket)
        assertEquals(count, data.collection.size)
        stopKoin()
    }
}
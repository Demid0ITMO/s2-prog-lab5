package utils

import collectionObjectsClasses.Route
import serializationStrategies.JsonStrategy
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import serializationStrategies.utils.Serializator
import java.io.File
import java.io.InputStreamReader
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.Date

class SerializatorTest {

    @Test
    fun `check serialization and deserialization`() {
        //check
        val serializator = Serializator()
        var file = File("testData")
        var reader = InputStreamReader(file.inputStream())
        val rollback = reader.readText()
        var input: String
        var collection: MutableCollection<Route>
        var output: String
        var writer: PrintWriter
        try {
            for (strategy in serializator.getStrategies()) {
                file = File("testData")
                reader = InputStreamReader(file.inputStream())
                input = reader.readText()
                collection = serializator.deserialize(input)
                output = serializator.serialize(collection)
                assertEquals(input, output)
                serializator.changeStrategy(strategy.value)
                writer = PrintWriter("testData")
                writer.print(serializator.serialize(collection))
                writer.flush()
            }
            //check for last
            file = File("testData")
            reader = InputStreamReader(file.inputStream())
            input = reader.readText()
            collection = serializator.deserialize(input)
            output = serializator.serialize(collection)
            assertEquals(input, output)
        } catch (_: Exception) {
            System.err.println(serializator.getChosenStrategy().toString())
            assertEquals("good", "bad")
        }
        finally {//rollback
            writer = PrintWriter("testData")
            writer.print(rollback)
            writer.flush()
        }
    }

    @Test
    fun addStrategy() {
        val serializator = Serializator()
        val newStrategy = JsonStrategy()
        val newStrategyName = "${SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())}strategy"
        serializator.addStrategy(newStrategyName, newStrategy)
        assert(serializator.getStrategies()[newStrategyName] != null)
    }

    @Test
    fun getStrategy() {
        val serializator = Serializator()
        val newStrategy = JsonStrategy()
        val newStrategyName = "${SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())}strategy"
        serializator.addStrategy(newStrategyName, newStrategy)
        assertEquals(serializator.getStrategy(newStrategyName), newStrategy)
    }

    @Test
    fun changeStrategy() {
        val serializator = Serializator()
        val chosen = serializator.getChosenStrategy()
        val strategy = JsonStrategy()
        serializator.changeStrategy(strategy)
        assert(chosen != strategy)
    }
}
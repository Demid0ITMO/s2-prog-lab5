package utils

import collectionObjectsClasses.Coordinates
import collectionObjectsClasses.Location
import collectionObjectsClasses.Route
import java.io.BufferedReader
import java.io.PrintWriter
import java.util.function.Predicate

typealias ToType<T> = (input: String?) -> T

/***
 * Считывает строки из потока ввода значения полей, проверяет их на правильность и конвертирует в нужный тип
 * @author Demid0
 * @since 1.0
 */
class Asker {
    private fun <T> ask(
        reader: BufferedReader,
        writer: PrintWriter,
        printHint: String,
        converter: ToType<T>,
        checker: Predicate<T>
    ): T {
        var output: T
        writer.print("$printHint > ")
        writer.flush()
        while (true) {
           try {
               var input = reader.readLine()
               input = if (input == "") null else input
               output = converter(input)
               if (checker.test(output)) break
               else throw Exception()
           } catch (_: Exception) {
               writer.println("Incorrect input. Try again.")
               writer.flush()
           }
        }
        return output
    }

    val ToIntOrNull: ToType<Int?> = { it?.toIntOrNull() }
    val ToLong: ToType<Long> = { it!!.toLong() }
    val ToFloat: ToType<Float> = { it!!.toFloat() }
    val ToFloatOrNull: ToType<Float?> = { it?.toFloatOrNull() }
    val ToDouble: ToType<Double> = { it!!.toDouble() }
    val ToString: ToType<String> = { it!! }

    fun askRoute(reader: BufferedReader, writer: PrintWriter): Route {
        val name: String = ask(reader, writer, "name", ToString) { it != "" }
        val coordinates = askCoordinates(reader, writer)
        val from: Location? = askNullableLocation(reader, writer, "from")
        val to: Location = askLocation(reader, writer, "to")
        val distance: Double = ask(reader, writer, "distance", ToDouble) { it > 1 }
        return Route(name, coordinates, from, to, distance)
    }

    private fun askCoordinates(reader: BufferedReader, writer: PrintWriter): Coordinates {
        val x: Float? = ask(reader, writer, "Coordinates - x", ToFloatOrNull) { it == null || it <= 800 }
        val y: Int? = ask(reader, writer, "Coordinates - y", ToIntOrNull) { true }
        return Coordinates(x, y)
    }

    private fun askLocation(reader: BufferedReader, writer: PrintWriter, Lname: String): Location {
        val x: Int? = ask(reader, writer, "$Lname - x", ToIntOrNull) { true }
        val y: Float = ask(reader, writer, "$Lname - y", ToFloat) { true }
        val z: Long = ask(reader, writer, "$Lname - z", ToLong) { true }
        val name: String = ask(reader, writer, "$Lname - name", ToString) { it != "" && it.length <= 496}
        return Location(x, y, z, name)
    }

    private fun askNullableLocation(reader: BufferedReader, writer: PrintWriter, Lname: String): Location? {
        val ans = ask(reader, writer, "$Lname is nullable field. If you want to make it null, print \"yes\".", ToString) {true}
        return if (ans == "yes") null
        else askLocation(reader, writer, Lname)
    }
}
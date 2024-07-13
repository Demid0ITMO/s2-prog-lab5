package utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

class AskerTest {

    @Test
    fun askRoute() {
        val input ="""
            a
            5
            5
            yes
            5
            5
            5
            b
            5
        """.trimIndent()
        val reader = BufferedReader(InputStreamReader(input.byteInputStream()))
        val writer = PrintWriter(System.err)
        val asker = Asker()
        val route = asker.askRoute(reader, writer)
        assertEquals(
            "name: a\ncoordinates: x: 5.0, y: 5\nfrom(Location): null" +
                    "\nto(Location): \n" +
                    "\tname: b\n" +
                    "\tx: 5, y: 5.0, z: 5\ndistance: 5.0\nid: ${route.getId()}\ncreationDate: ${route.getCreationDate()}",
            route.toString()
        )
    }
}
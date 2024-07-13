package utils

import collectionObjectsClasses.Route
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayDeque

class DataTest {


    @Test
    fun addSupportedCollectionType() {
        val data = Data()
        val newType = ArrayDeque<Route>()
        val newTypeName = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())
        data.addSupportedCollectionType(newTypeName, newType)
        assertEquals(data.getSupportedCollectionTypes()[newTypeName], newType)
    }

    @Test
    fun getFileName() {
        val data = Data()
        val fileName = data.getFileName()
        assert(File(fileName).exists())
    }

    @Test
    fun getInfoFileName() {
        val data = Data()
        val fileName = data.getFileName()
        assert(File(fileName).exists())
    }
}
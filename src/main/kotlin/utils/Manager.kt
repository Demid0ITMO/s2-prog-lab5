package utils

import java.io.Reader
import java.io.Writer

/***
 * Класс для обертки Readers и Writers
 * @author Demid0
 * @since 1.0
 */
abstract class Manager<T>(private var obj: T) {
    fun get() = obj
    fun set(obj: T) {
        this.obj = obj
    }
}

/***
 * Класс для BufferedReader
 * @author Demid0
 * @since 1.0
 */
class ReaderManager<T: Reader>(obj: T) : Manager<T>(obj)

/***
 * Класс для PrintWriter
 * @author Demid0
 * @since 1.0
 */
class WriterManager<T: Writer>(obj: T) : Manager<T>(obj)

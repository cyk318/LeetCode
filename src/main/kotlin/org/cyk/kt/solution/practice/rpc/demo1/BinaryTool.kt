package org.cyk.kt.solution.practice.rpc.demo1

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object BinaryTool {

    fun anyToBytes(any: Any): ByteArray {
        val result: ByteArray
        ByteArrayOutputStream().use { byteOutput ->
            ObjectOutputStream(byteOutput).use { objectOutput ->
                objectOutput.writeObject(any)
            }
            result = byteOutput.toByteArray()
        }
        return result
    }

    fun bytesToAny(bytes: ByteArray): Any {
        val result: Any
            ByteArrayInputStream(bytes).use { byteInput ->
            ObjectInputStream(byteInput).use { objectInput ->
                result = objectInput.readObject()
            }
        }
        return result
    }

}

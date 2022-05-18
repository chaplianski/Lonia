package com.example.lonia.data.storage.database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.Base64.*
import android.view.View
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import java.util.*


class PhotoConverters {


//    @TypeConverter
//    fun fromBitmap(bitmap: Bitmap): ByteArray {
//        var encode: String? = null
//        val outputStream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
////        val qqq = outputStream.toByteArray()
//
//        encode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Base64.getEncoder().encodeToString(outputStream.toByteArray())
//        } else {
//            encodeToString(outputStream.toByteArray(), DEFAULT)
//        }
//
////        return encode
// //       val encodedString: String = Base64.getEncoder().encodeToString(qqq)
//        return outputStream.toByteArray()
//    }

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): String {
        var encode: String? = null
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        encode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getEncoder().encodeToString(outputStream.toByteArray())
        } else {
            encodeToString(outputStream.toByteArray(), DEFAULT)
        }
        return encode
    }



//    @TypeConverter
//    fun toBitmap (byteArray: ByteArray): Bitmap {
//        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
//    }

    @TypeConverter
    fun toBitmap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte: ByteArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                Base64.getDecoder().decode(encodedString)
            } else {
                decode(encodedString, android.util.Base64.DEFAULT)
            }
            val bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
            bitmap
        } catch (e: Exception) {
            e.message
            null
        }
    }

}
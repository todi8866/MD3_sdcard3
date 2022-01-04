package com.example.md3_sdcard3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import java.io.*

class MainActivity : AppCompatActivity() {

    private val logtag = "Todi"

    private val filepath = "MyFileStorage"
    internal var myExternalFile: File?=null

    private val isExternalStorageReadOnly: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        return (Environment.MEDIA_MOUNTED_READ_ONLY == extStorageState)
    }

    private val isExternalStorageAvailable: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        return (Environment.MEDIA_MOUNTED == extStorageState)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logtag, "OnCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(logtag, isExternalStorageAvailable.toString())
        Log.d(logtag, isExternalStorageReadOnly.toString())

        val filename = "modata3.txt"
        myExternalFile = File(getExternalFilesDir(filepath),filename)

        try {
            val fileOutPutStream = FileOutputStream(myExternalFile)
            fileOutPutStream.write("testdata".toByteArray())
            fileOutPutStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Toast.makeText(applicationContext,"data save",Toast.LENGTH_SHORT).show()

        var fileInputStream = FileInputStream(myExternalFile)
        var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }
        fileInputStream.close()

        Toast.makeText(applicationContext,stringBuilder.toString(),Toast.LENGTH_SHORT)
    }
}
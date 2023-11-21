import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

object Util {

    private const val FILE_NAME = "stored_text.txt"

    fun saveTextToFile(context: Context, text: String) {
        try {
            val fileOutputStream: FileOutputStream = File(context.filesDir, FILE_NAME).outputStream()
            fileOutputStream.write(text.toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getStoredText(context: Context): String {
        try {
            val fileInputStream: FileInputStream = File(context.filesDir, FILE_NAME).inputStream()
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text: String? = null

            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
            }

            fileInputStream.close()
            return stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }
}

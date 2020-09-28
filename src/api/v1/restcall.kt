package api.v1

import java.io.*
import java.lang.reflect.Method
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.net.http.HttpHeaders
import java.util.*
import javax.net.ssl.HttpsURLConnection


fun sendRequest(requestURL: String?, requestMethod: String, headers: HttpHeaders,postDataParams: HashMap<String?, String?>? ): String? {
    val url: URL
    var response: String? = ""
    try {
        url = URL(requestURL)
        val conn = url.openConnection() as HttpURLConnection
        conn.readTimeout = 15000
        conn.connectTimeout = 15000
        conn.requestMethod = requestMethod
        conn.doInput = true
        conn.doOutput = true
        conn.headerFields(headers)
        val os = conn.outputStream
        val writer = BufferedWriter(
                OutputStreamWriter(os, "UTF-8"))
        writer.write(getPostDataString(postDataParams))
        writer.flush()
        writer.close()
        os.close()
        val responseCode = conn.responseCode
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            var line: String?
            val br = BufferedReader(InputStreamReader(conn.inputStream))
            while (br.readLine().also { line = it } != null) {
                response += line
            }
        } else {
            response = ""
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return response
}

@Throws(UnsupportedEncodingException::class)
private fun getPostDataString(params: HashMap<String?, String?>?): String? {
    val result = StringBuilder()
    var first = true
    for ((key, value) in params) {
        if (first) first = false else result.append("&")
        result.append(URLEncoder.encode(key, "UTF-8"))
        result.append("=")
        result.append(URLEncoder.encode(value, "UTF-8"))
    }
    return result.toString()
}
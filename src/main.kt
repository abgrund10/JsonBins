
import api.v1.Data
import api.v1.sendRequest
import sun.jvm.hotspot.utilities.Assert


fun main(args: Array<String>) {
 """positive. create valid request"""
   val req1 = sendRequest("https://jsonbin.io/b/", "POST", Data.secretKey, Data.jsonBody1 )
   println(req1)
   req1?.equals(200)?.let { check(it) }

    """positive. send valid get request"""
   val req2 = sendRequest("https://jsonbin.io/b/1/", "GET", Data.secretKey, null )
   println(req2)
   req2?.equals(200)?.let { check(it) }

    """positive. send valid get request"""
   val req3 = sendRequest("https://jsonbin.io/b/1/latest/", "GET", Data.secretKey, null )
   println(req3)
   req3?.equals(200)?.let { check(it) }

    """positive. send valid put request"""
   val req4 = sendRequest("https://jsonbin.io/b/1/", "PUT", Data.secretKey, Data.jsonBody2 )
   println(req4)
   req4?.equals(200)?.let { check(it) }

    """positive. send valid delete request"""
   val req5 = sendRequest("https://jsonbin.io/b/1/", "DELETE", Data.secretKey, Data.jsonBody2 )
   println(req5)
   req5?.equals(200)?.let { check(it) }

    """positive. create valid request - create once again for negative testing"""
   val req6 =sendRequest("https://jsonbin.io/b/", "POST", Data.secretKey, Data.jsonBody1 )
   println(req6)
   req6?.equals(200)?.let { check(it) }

    """negative.  create POST request with empty body"""
   val req7 =sendRequest("https://jsonbin.io/b/1/", "POST",Data.secretKey, null )
   println(req7)
   req7?.equals(200)?.let { check(it) }

    """negative.  create PUT request with empty body"""
    println()
   val req8 = sendRequest("https://jsonbin.io/b/1/", "PUT",Data.secretKey, null )
   println(req8)
   req8?.equals(422)?.let { check(it) }

    """negative.  create PUT request with the invalid bin id"""
   val req9 = sendRequest("https://jsonbin.io/b/111", "PUT",Data.secretKey, Data.jsonBody2 )
   println(req9)
   req9?.equals(404)?.let { check(it) }

   """negative.  create PUT request with the invalid secret"""
   val req10 = sendRequest("https://jsonbin.io/b/111", "PUT",Data.secretKey_inv, Data.jsonBody2 )
   println(req10)
   req10?.equals(401)?.let { check(it) }

    """negative. delete  bin using wrong url"""
   val req11 = sendRequest("https://jsonbin.io/b/3/", "DELETE", Data.secretKey, Data.jsonBody1 )
   println(req11)
   req11?.equals(200)?.let { check(it) }

   """negative.  create DELETE request with the invalid bin id"""
   val req12 = sendRequest("https://jsonbin.io/b/111", "DELETE",Data.secretKey, Data.jsonBody2 )
   println(req12)
   req12?.equals(404)?.let { check(it) }

   """negative.  create DELETE request with the invalid secret"""
   val req13 = sendRequest("https://jsonbin.io/b/111", "DELETE",Data.secretKey_inv, Data.jsonBody2 )
   println(req13)
   req13?.equals(401)?.let { check(it) }

    """negative. create  request with another header"""
   val req14 = sendRequest("https://jsonbin.io/b/5f70af2365b18913fc549f4f/", "HEAD", Data.secretKey, Data.jsonBody1 )
   println(req14)
   req14?.equals(200)?.let { check(it) }
}
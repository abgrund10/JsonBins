package api.v1
import org.json.JSONObject



class Data {
    val secretkey = "$2b$10$" + "soWWOitRT8E/GbJjiVf3Y.Kc/Ac0L/095c9Khjd4mnZVzzKVsYf8K"
    val secretkey_inv = "c/Ac0L/095c9Khjd4mnZVzzKVsYf8K"
    val jsonbody1 = JSONObject("""{"Sample":"Hello World"}""").toString()
    val jsonbody2 = JSONObject("""{"Sample2":"Hello World2"}""").toString()
}
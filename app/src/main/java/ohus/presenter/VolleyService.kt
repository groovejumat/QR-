package ohus.presenter

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

//QR코드로 읽어온 String값을 Volley를 통해 body값에 담아 요청하고, 성공 유무와 함께 응답값을 반환 합니다.
object VolleyService {

    val testUrl = "https://eq0lwb7e8e.execute-api.ap-northeast-2.amazonaws.com/default/android-dev-recruit"

    //성공 실패 유무룰 post처리를 해줌과 동시에 반환 한다.
    fun testVolley(data : String, context: Context, success: (String) -> Unit) {

        val requestBody = data

        //POST방식으로 지정한 URL 요청처리 진행
        val testRequest = object : StringRequest(Request.Method.POST, testUrl , Response.Listener { response ->
            println("서버 Response 수신: $response")
            success(response)


        }, Response.ErrorListener { error ->
            Log.d("ERROR", "서버 Response 가져오기 실패: $error")
            success(error.toString())


        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(testRequest)

        return
    }
}
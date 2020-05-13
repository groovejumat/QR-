package ohus.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.hanmo.mvp_example.R
import ohus.base.BaseActivity
import ohus.presenter.SearchContract
import ohus.presenter.SearchPresenter
import ohus.presenter.VolleyService
import kotlinx.android.synthetic.main.activity_search.*



// 따로 만든 뷰를 시작 액티비티에 붙인다?? //
class SearchActivity : BaseActivity(), SearchContract.View {

    //presentor를 사용하기 위해서 초기화 시킨다//
    private lateinit var searchPresenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //현재 생성되어진 엑티비티의 뷰정보를 searchPresntor의 뷰를 가져오는 메소드에 넣는다. (뷰정보를 연결한다.)
        searchPresenter.takeView(this)

        setButton()
    }

    //버튼을 세팅한다. (리스트 값을 가지고 오는 부분)
    private fun setButton() {

        /* QR코드 정보를 스캔해 오는 값 */
        readCodeButton.setOnClickListener {
            showLoading()
            IntentIntegrator(this).initiateScan() // `this` is the current Activity

        }
    }

    //엑티비티 생명주기가 완전히 끝날 때, searchPresentor에 dropView를 실행한다. (뷰를 제거.)
    override fun onDestroy() {
        super.onDestroy()
        searchPresenter.dropView()
    }


    //SearchPresenter를 생성함.
    override fun initPresenter() {
        searchPresenter = SearchPresenter()
    }

    //toast를 통해서 에러를 송출한다. (Contract에 존재한다)
    override fun showError(error: String) {
        Toast.makeText(this@SearchActivity, error, Toast.LENGTH_SHORT).show()
    }

    //로딩바 생성 제거
    override fun showLoading() {
        searchRefresh.visibility = View.VISIBLE
    }
    override fun hideLoading() {
        searchRefresh.visibility = View.GONE
    }

    //QR코드로 가지고온 데이터를 송출합니다.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()

                //가지고 온 데이터를 세팅합니다
                qrCodeData.text=result.contents

                //volley를 통해서 읽어온 스크립트를 송신하고 응답값을 뷰에 반환합니다 .
                VolleyService.testVolley(result.contents,this){ success ->
                    httpResponse.text= "응답 값 : " + success
                    hideLoading()
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}

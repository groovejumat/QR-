package ohus.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* 기능 : 데이터를 정제하고 이 데이터를 View로 전달해주는 Presentor를 생성합니다 */
        initPresenter()
    }

    abstract fun initPresenter()

}
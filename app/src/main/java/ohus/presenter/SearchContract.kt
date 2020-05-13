package ohus.presenter

import ohus.base.BasePresenter
import ohus.base.BaseView

interface SearchContract {

    // 기본 뷰들의 기능을 담고 있는 인터페이스
    interface View : BaseView {

        fun showLoading()
        fun hideLoading()

    }

    /* 기본적으로 필요한 Presentor를 interface화 시켜 놓음. */
    interface Presenter : BasePresenter<View> {

    }
}
package ohus.presenter


class SearchPresenter : SearchContract.Presenter {

    private var searchView : SearchContract.View? = null


    /* 메소드 오버라이딩 한다. searcheView 변수에 view값을 지정해 준다.*/
    override fun takeView(view: SearchContract.View) {
        searchView = view
    }


    /* 뷰를 드롭한다. */
    override fun dropView() {
        searchView = null
    }

}
package com.example.innowiseweatherapplication.presenter.presenterImpl

import com.example.innowiseweatherapplication.model.modelImpl.MainModel
import com.example.innowiseweatherapplication.presenter.IMainPresenterInterface
import com.example.innowiseweatherapplication.view.IMainView

class MainPresenter(val view:IMainView):
    IMainPresenterInterface {
    val model = MainModel()

    override fun getData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
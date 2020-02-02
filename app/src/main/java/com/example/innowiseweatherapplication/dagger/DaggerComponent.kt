package com.example.innowiseweatherapplication.dagger

import com.example.innowiseweatherapplication.model.modelImpl.SomeTypesHelper
import com.example.innowiseweatherapplication.presenter.presenterImpl.MainPresenter
import dagger.Component

@Component(modules = [ContextModule::class,SomeTypesHelper::class])
interface DaggerComponent {
    fun getSomeHelper():SomeTypesHelper
    fun getPresenter():MainPresenter


}
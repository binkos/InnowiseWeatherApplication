package com.example.innowiseweatherapplication.dagger

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(context: Context){
    val context:Context = context
    @Provides get
}
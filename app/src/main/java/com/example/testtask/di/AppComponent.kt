package com.example.testtask.di

import android.content.Context
import androidx.core.view.KeyEventDispatcher
import com.example.testtask.presenter.ui.VesselsFragment
import dagger.BindsInstance
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {

     fun vesselsFragmentInject(vesselsFragment: VesselsFragment)


    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}
package com.example.testtask.di

import android.content.Context
import androidx.room.Room
import com.example.testtask.data.storage.VasselStorage
import com.example.testtask.data.storage.database.BriefCaseDB
import com.example.testtask.data.storage.database.VasselStorageImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule() {

    @Singleton
    @Provides
    fun provideBriefCaseDao(briefCaseDB: BriefCaseDB) = briefCaseDB.BriefCaseDao()

    @Singleton
    @Provides
    fun provideBriefCaseDB(context: Context): BriefCaseDB =
        Room.databaseBuilder(
            context,
            BriefCaseDB::class.java,
            "briefcase_db"
        ).build()

    @Provides
    fun provideVesselStorage(impl: VasselStorageImpl): VasselStorage{
        return impl
    }

//    @Binds
 //   abstract fun bindInterface (impl: InterfaceImplementation): Interface


}

package com.lucassimao.listadecompras.di

import android.app.Application
import androidx.room.Room
import com.lucassimao.listadecompras.data.PurchaseRepository
import com.lucassimao.listadecompras.data.db.PurchaseDAO
import com.lucassimao.listadecompras.data.db.PurchaseDatabase
import com.lucassimao.listadecompras.ui.PurchaseViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun providePurchaseDatabase(application: Application): PurchaseDatabase {
        return Room.databaseBuilder(
            application,
            PurchaseDatabase::class.java,
            "purchase_db"
        ).build()
    }

    fun providePurchaseDAO(database: PurchaseDatabase): PurchaseDAO {
        return database.purchaseDAO
    }

    single { providePurchaseDatabase(androidApplication()) }
    single { providePurchaseDAO(get()) }
}

val repositoryModule = module {
    factory { PurchaseRepository(get()) }
}

val viewModelModule = module {
    factory { PurchaseViewModel(get()) }
}
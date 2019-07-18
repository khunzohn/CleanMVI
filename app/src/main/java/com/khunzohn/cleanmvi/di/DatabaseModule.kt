package com.khunzohn.cleanmvi.di

import androidx.room.Room
import com.khunzohn.data.database.CleanDatabase
import org.koin.dsl.module.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), CleanDatabase::class.java, "clean-db")
            .build()
    }

    single {
        val db: CleanDatabase = get()
        db.iPhoneDao()
    }

    single {
        val db: CleanDatabase = get()
        db.macDao()
    }
}
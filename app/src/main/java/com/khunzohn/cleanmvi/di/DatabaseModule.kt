package com.khunzohn.cleanmvi.di

import androidx.room.Room
import com.khunzohn.data.database.CleanDatabase
import com.khunzohn.data.database.MIGRATION_1_2
import org.koin.dsl.module.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), CleanDatabase::class.java, "clean-db")
            .addMigrations(MIGRATION_1_2)
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
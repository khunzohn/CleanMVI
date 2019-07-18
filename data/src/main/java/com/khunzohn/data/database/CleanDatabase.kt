package com.khunzohn.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khunzohn.data.database.dao.IPhoneDao
import com.khunzohn.data.database.dao.MacDao
import com.khunzohn.data.model.entity.IPhoneEntity
import com.khunzohn.data.model.entity.MacEntity

@Database(
    entities = [IPhoneEntity::class, MacEntity::class],
    version = 1, exportSchema = true
)
abstract class CleanDatabase : RoomDatabase() {

    abstract fun iPhoneDao(): IPhoneDao

    abstract fun macDao(): MacDao
}
package com.khunzohn.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "macs")
data class MacEntity(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    val name: String,

    val favourite: Boolean,

    @ColumnInfo(name = "short_description")
    val shortDescription: String,

    val description: String,

    val price: Double,

    val currency: String,

    @ColumnInfo(name = "in_stock")
    val inStock: Boolean,

    @ColumnInfo(name = "is_new")
    val isNew: Boolean
)
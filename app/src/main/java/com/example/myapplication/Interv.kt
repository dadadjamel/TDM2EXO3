package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity()
data class Interv (
    @PrimaryKey (autoGenerate = true) var id: Int,
    @ColumnInfo(name="Numero") var num:String,
    @ColumnInfo(name="NomP") var  nom:String,
    @ColumnInfo(name="Type") var Type:String,
    @ColumnInfo(name="Date")  var date :String){


    constructor():this(0,"","","",""){

    }




}
package com.example.myapplication

import androidx.room.*


@Dao
public interface IntervDAO {
    @Query("SELECT * FROM interv")
    fun getIntervs(): MutableList<Interv>

    @Query("SELECT * FROM interv WHERE id = :code")
    fun getInterv(code: Int): List<Interv>


    @Query("SELECT * FROM interv WHERE  Date = :date1")
    fun getIntervss(date1  :String): MutableList<Interv>

    @Insert
    fun ajouter(interv : Interv)

    @Update
    fun modifier(interv : Interv)

    @Delete
    fun supprimer(interv: Interv)
}
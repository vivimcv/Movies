package com.example.practica2.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.practica2.R
import com.example.practica2.databinding.ActivityInsertBinding
import com.example.practica2.model.Movies

class DBmovies(context: Context?) : DBHelper(context) {
    private lateinit var setImage: ImageView
    private lateinit var binding: ActivityInsertBinding


    //Aquí va el código para el CRUD

    val context = context

    fun insertMovie(title: String, genre: Int, year: String, director: String): Long{
        //var id: Int, var title: String, var genre: String, var Year: String, var Director: String
        val dbHelper = DBHelper(context)
        //writableDatabase
        val db = dbHelper.writableDatabase
        var id: Long = 0



        try{

            val values = ContentValues()

            values.put("title", title)
            values.put("genre", genre)
            values.put("year",year)
            values.put("director", director)

            id = db.insert(TABLE_MOVIES, null, values)

        }catch(e: Exception){
            //Manejo de la excepción
        }finally {
            db.close()
        }

        return id
    }

    fun getMovies(): ArrayList<Movies>{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        var listMovies = ArrayList<Movies>()
        var movieTmp: Movies? = null
        var cursorMovies: Cursor? = null

        cursorMovies = db.rawQuery("SELECT * FROM $TABLE_MOVIES", null)

        if(cursorMovies.moveToFirst()){
            do{
                movieTmp = Movies(cursorMovies.getInt(0), cursorMovies.getString(1), cursorMovies.getInt(2),cursorMovies.getString(3), cursorMovies.getString(4))
                listMovies.add(movieTmp)
            }while(cursorMovies.moveToNext())
        }

        cursorMovies.close()

        return listMovies
    }

    fun getMovie(id: Int): Movies?{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        var movie: Movies? = null
        var cursorMovies: Cursor? = null

        cursorMovies = db.rawQuery("SELECT * FROM $TABLE_MOVIES WHERE id = $id LIMIT 1", null)

        if(cursorMovies.moveToFirst()){
            movie = Movies(cursorMovies.getInt(0), cursorMovies.getString(1), cursorMovies.getInt(2),cursorMovies.getString(3), cursorMovies.getString(4))
        }

        cursorMovies.close()

        return movie
    }

    fun updateMovie(id: Int, title: String, genre: Int, year: String,director: String): Boolean{

        var banderaCorrecto = false

        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try{
            db.execSQL("UPDATE $TABLE_MOVIES SET title = '$title', genre = '$genre', year = '$year', director = '$director' WHERE id = $id")
            banderaCorrecto = true
        }catch(e: Exception){

        }finally {
            db.close()
        }

        return banderaCorrecto

    }

    fun deleteMovie(id: Int): Boolean{

        var banderaCorrecto = true

        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try{
            db.execSQL("DELETE FROM $TABLE_MOVIES WHERE id = $id")
            banderaCorrecto = true
        }catch(e: Exception){

        }finally {
            db.close()
        }

        return banderaCorrecto
    }
}
package com.example.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.practica2.adapter.MoviesAdapter
import com.example.practica2.databinding.ActivityMainBinding
import com.example.practica2.db.DBmovies
import com.example.practica2.model.Movies

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listMovies: ArrayList<Movies>
    private lateinit var listMovies2: ArrayList<Movies>
    private lateinit var setImage: ImageView
    //val bundle = intent.extras
    val genre = Bundle()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




       // val bundle = intent.extras
     //  val genero = bundle?.getString("operacion", "")


        ////creando base
        val dbMovies = DBmovies(this)

        listMovies =dbMovies.getMovies()
        if(listMovies.size == 0) binding.tvSinRegistros.visibility = View.VISIBLE
        else binding.tvSinRegistros.visibility = View.INVISIBLE


        val moviesAdapter = MoviesAdapter(this,listMovies)
      //  val moviesAdapter = MoviesAdapter(listMovies)
        binding.lvMovies.adapter = moviesAdapter

      // val movieGenero =intent.getSerializableExtra("genero") as movieGen

       // genre.putString("genero","comedia")
       // intent.putExtras(genre)
      //  Toast.makeText(this@MainActivity, genre.toString(), Toast.LENGTH_SHORT).show()
      //  if (genre.toString()=)



      //  setImage.setImageResource(R.drawable.tension)
       // setImage.setImageResource(R.drawable.drama)
       /* val resultado = bundle?.getDouble("resultado", 0.0)

        val movieElegida = findViewById<TextView>(R.id.tietSpiner).apply {
            text = resultado.toString()
        }*/

        binding.lvMovies.setOnItemClickListener { adapterView, view, i, l ->


            //l es el id
            //i es la posición
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("ID", l.toInt())


            startActivity(intent)
            finish()
        }


    }

    fun click(view: View) {
        //eventos del click del botón flotante
        startActivity(Intent(this, InsertActivity::class.java))
        finish()
    }
}
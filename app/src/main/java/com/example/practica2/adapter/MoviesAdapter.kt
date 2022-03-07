package com.example.practica2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.practica2.R
import com.example.practica2.databinding.ListViewBinding
import com.example.practica2.model.Movies


class MoviesAdapter(private val mcontext: Context,listMovies: ArrayList<Movies>): ArrayAdapter<Movies>(mcontext,0,listMovies) {

    private val listMovies = listMovies
    private val layoutInflater = LayoutInflater.from(mcontext)
    private lateinit var setImage: ImageView


    override fun getCount(): Int {
        return listMovies.size
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
      // val layout = ActivityListItemBinding.inflate(layoutInflater)

        val layout = ListViewBinding.inflate(layoutInflater)
        val moviePo =listMovies[position]


      //  val rowView = layoutInflater.inflate(R.layout.activity_main_list_view,null)
      //  val imageView = rowView.findViewById<TextView>(R.id.tietSpiner)

       // Toast.makeText(this@MoviesAdapter,imageView.toString(), Toast.LENGTH_SHORT).show()
        //Toast.makeText(this,genero.toString() , Toast.LENGTH_SHORT).show()

    //    imageView.setImageResource(imageIdList[p0])

     //  layout.ivGeneroElegido.setImageResource(moviePo.imagen)
       //layout.tvTitulo

        with(layout){
             tvTitulo.text = moviePo.title

            when(listMovies[position].genre){
                0->{tvGenero.text= "Comedia"}
                1->{tvGenero.text= "Romance"}
                2->{tvGenero.text= "Terror"}
                3->{tvGenero.text= "Drama"}
                4->{tvGenero.text= "Suspenso"}
                5->{tvGenero.text= "Fantasía"}
            }
         //   setImage = findViewById(R.id.ivGeneroElegido)
        //  setImage.setImageResource(R.drawable.humor)
            tvYear.text= listMovies[position].Year.toString()
            tvDirector.text = listMovies[position].Director

           // genre.putString("genero","comedia")
          //  val imageView = ImageView
        // var genero =
         //   intent.putExtras(genre)
          //  Toast.makeText(this,genero.toString() , Toast.LENGTH_SHORT).show()

            //setImage.setImageResource(R.drawable.corriente)
        }


        return layout.root
    }
    override fun getItem(p0: Int): Movies? {

        return listMovies[p0]
    }

    override fun getItemId(p0: Int): Long {
        return listMovies[p0].id.toLong()
    }



}
package com.example.practica2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practica2.databinding.ActivityEditBinding
import com.example.practica2.db.DBmovies
import com.example.practica2.model.Movies

class EditActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    private lateinit var dbMovies: DBmovies
    var movie: Movies? = null
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            val bundle = intent.extras
            if(bundle != null){
                id = bundle.getInt("ID", 0)
            }
        }else{
            id = savedInstanceState.getSerializable("ID") as Int
        }

        dbMovies = DBmovies(this)

        movie = dbMovies.getMovie(id)

        if(movie != null){
            with(binding){
                tietTitulo.setText(movie?.title)


                val bundle = intent.extras
                val resultado = bundle?.getInt("genero", 0)
                // Toast.makeText(this@EditActivity, resultado.toString(), Toast.LENGTH_SHORT).show()

               //Toast.makeText(this@EditActivity, seleccion.toString(), Toast.LENGTH_SHORT).show()

              //  tietGenre.setText(movie?.genre)
                tietYear.setText(movie?.Year)
                tietDirector.setText(movie?.Director)

            }
        }
    }

    fun click(view: View) {
        with(binding){
            val seleccion = tietSpiner.selectedItemId.toInt()
           // val seleccionImagenGenero = ivSpinnerElegido.drawable.toString()


            if(!tietTitulo.text.toString().isEmpty() && !seleccion.toString().isEmpty() && !tietYear.text.toString().isEmpty()&& !tietDirector.text.toString().isEmpty()){
                if(dbMovies.updateMovie(id, tietTitulo.text.toString(), seleccion.toInt(), tietYear.text.toString(),tietDirector.text.toString())){
                    Toast.makeText(this@EditActivity, "Registro actualizado exitosamente", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@EditActivity, DetailsActivity::class.java)
                    intent.putExtra("ID", id)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@EditActivity, "Error al actualizar el registro", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@EditActivity, "Ningún campo puede quedar vacío", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
}
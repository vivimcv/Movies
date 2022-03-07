package com.example.practica2

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.practica2.databinding.ActivityDetailsBinding
import com.example.practica2.db.DBmovies
import com.example.practica2.model.Movies

class DetailsActivity: AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var spinnview: Spinner
   // private lateinit var setImage: ImageView



    private lateinit var dbMovies: DBmovies
    var movie: Movies? = null
    var id:Int = 0
    var genre:Int=0
    var imagen:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle2 = intent.extras
        val operacion = bundle2?.getString("operacion", "")


        //generando spinner
        val Generos = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item)
        Generos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //definiendo géneros
        Generos.addAll(listOf(getString(R.string.Género1),getString(R.string.Género2),getString(R.string.Género3),getString(R.string.Género4),getString(R.string.Género5),getString(R.string.Género6)))

      //  binding.tietGenre.onIte = this
       // binding.tietGenre.adapter = Generos



/////bueno:
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
/*val generoElegido = Bundle()
        generoElegido.putString("operacion","drama")
        intent.putExtras(generoElegido)
        // Toast.makeText(this@MainActivity, operacion.toString(), Toast.LENGTH_SHORT).show()
        startActivity(intent)*/


        if(movie != null){
            with(binding){
                tietTitulo.setText(movie?.title)
               // setImage = findViewById(R.id.ivGeneroElegido)

               // Toast.makeText(this@DetailsActivity, movie?.genre.toString(), Toast.LENGTH_LONG).show()
                //añadiendo genero seleccionado en el spinner
              if(movie?.genre==0){tietGenre.setText("Comedia")
                 // setImage.setImageResource(R.drawable.humor)

               }else {
                   if(movie?.genre==1)tietGenre.setText("Romance")
                  else{
                       if(movie?.genre==2)tietGenre.setText("Terror")
                       else{ if(movie?.genre==3)tietGenre.setText("Drama")
                           else{if(movie?.genre==4)tietGenre.setText("Suspenso")
                               else{tietGenre.setText("Fantasía")
                           }

                           }

                       }
                  }
               }
                tietYear.setText(movie?.Year)
                tietDirector.setText(movie?.Director)

                //Para que no se abra el teclado al momento de hacer click en los TextInputEditText
                tietTitulo.inputType = InputType.TYPE_NULL
                tietGenre.inputType = InputType.TYPE_NULL
                tietYear.inputType = InputType.TYPE_NULL
                tietDirector.inputType = InputType.TYPE_NULL

            }
        }


        //colocando imagen del género
     /*   setImage = findViewById(R.id.ivGeneroElegido)
        when (operacion) {
            "voltaje" -> {
                setImage.setImageResource(R.drawable.tension)
            }
            "corriente" -> {
                setImage.setImageResource(R.drawable.corriente)
            }
            "resistencia" -> {
                setImage.setImageResource(R.drawable.resistencia)
            }
            "potencia" -> {
                setImage.setImageResource(R.drawable.potencia)
            }
        }*/

    }

    fun click(view: View) {

        when(view.id){
            R.id.btnEdit -> {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
                finish()
            }

            R.id.btnDelete -> {
                AlertDialog.Builder(this)
                    .setTitle("Confirmación")
                    .setMessage("¿Realmente deseas eliminar la película ${movie?.title}?")
                    .setPositiveButton("Sí", DialogInterface.OnClickListener { dialogInterface, i ->
                        if(dbMovies.deleteMovie(id)){
                            Toast.makeText(this, "Registro eliminado exitosamente", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                        //Si se desea realizar alguna acción cuando el usuario selecciona NO
                    })
                    .show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}
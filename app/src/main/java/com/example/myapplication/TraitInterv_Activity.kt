package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import java.util.*
import android.widget.ArrayAdapter
import androidx.room.Room
import com.example.myapplication.IntervDAO

import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_trait_interv_.*

class TraitInterv_Activity : AppCompatActivity() {


    private var db: DataBase? = null
    private var dao: IntervDAO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trait_interv_)

        val c = Calendar.getInstance()
        val annee = c.get(Calendar.YEAR)
        val mois = c.get(Calendar.MONTH)
        val jour = c.get(Calendar.DAY_OF_MONTH)



        val addDate = findViewById<Button>(R.id.addDate)
        val spinner_type  = findViewById<Spinner>(R.id.spinner_type)
        val spinner_nom  = findViewById<Spinner>(R.id.spinner_nom)
         val InputDate=findViewById<TextView>(R.id.InputDate)
        val  spinner_nom_text = findViewById<TextView>(R.id.spinner_nom_text)
         val  spinner_type_text = findViewById<TextView>(R.id.spinner_type_text)
        val  NumInter =findViewById<TextView>(R.id.editNom)
          val btn_supp=findViewById<Button>(R.id.supp)

        val btn_ajouter = findViewById<Button>(R.id.Apliquer)
        val  mode = intent.getStringExtra("mode")
        if(mode == "modif")
        {
           val pos =intent.getIntExtra("pos",0)
            this.db = DataBase.invoke(this)
            this.dao = db?.intervDAO()

            val interv=  dao?.getInterv(pos+1)

            spinner_nom_text.text=interv!![0].nom
           spinner_type_text.text=interv!![0].Type

            InputDate.text=interv!![0].date
            NumInter.text=interv!![0].num


        }
       if (mode=="ajout")
       {
           btn_supp.visibility= View.GONE
       }
        btn_supp.setOnClickListener{
            val num: String= NumInter.text.toString()
            val type: String = spinner_type_text.text.toString()
            val nomp: String = spinner_nom_text.text.toString()
            val date: String = InputDate.text.toString()
            val pos =intent.getIntExtra("pos",0)

            val interv: Interv = Interv(num.toInt(), num, nomp, type, date)

            this.db = DataBase.invoke(this)
            this.dao = db?.intervDAO()
            dao?.supprimer(interv)

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }


        btn_ajouter.setOnClickListener{


            if(mode == "ajout") {
                val num: String = NumInter.text.toString()
                val type: String = spinner_type.selectedItem.toString()
                val nomp: String = spinner_nom.selectedItem.toString()
                val date: String = InputDate.text.toString()

                val interv: Interv = Interv(0, num, nomp, type, date)
                this.db = DataBase.invoke(this)
                this.dao = db?.intervDAO()
                dao?.ajouter(interv)

                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }
            else{
                if (mode=="modif") {
                    val num: String = NumInter.text.toString()
                    val type: String = spinner_type.selectedItem.toString()
                    val nomp: String = spinner_nom.selectedItem.toString()
                    val date: String = InputDate.text.toString()
                    val pos =intent.getIntExtra("pos",0)
                    val interv: Interv = Interv(num.toInt(), num, nomp, type, date)


                    this.db = DataBase.invoke(this)
                    this.dao = db?.intervDAO()
                    dao?.modifier(interv)
                    val intent = Intent(this, MainActivity::class.java)

                    startActivity(intent)
                }

            }
             }
        addDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, annee, mois, jour ->
                InputDate!!.text="$jour-${mois+1}-$annee"

            }, annee, mois, jour)

            datePickerDialog.show()
        }

        val NomP= arrayOf("Dada","Riache","Chacha","Missoumi","Hammas")
        val Type_Interv= arrayOf("Nurse","Menage","Baby-setting","Garde malade","transport de personne")
        val adapterSpinner_N = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, NomP
        )
        adapterSpinner_N.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_nom.setAdapter(adapterSpinner_N)


        val adapterSpinner_T = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, Type_Interv
        )
        adapterSpinner_T.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_type.setAdapter(adapterSpinner_T)

        //spinner_nom_text!!.text=spinner_nom.selectedItem.toString()
        //spinner_type_text!!.text=spinner_type.selectedItem.toString()

    }



}


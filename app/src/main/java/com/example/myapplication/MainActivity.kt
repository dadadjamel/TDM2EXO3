package com.example.myapplication
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_trait_interv_.*
import java.util.*


class MainActivity : AppCompatActivity() ,IntervAdapter.OnIntervListener{
    var array = arrayOf<Interv>()
    var intervList : MutableList<Interv> ?= null
    private var db: DataBase? = null
    private var dao: IntervDAO? = null
    var adapter: IntervAdapter? = null
     var layoutManager: LinearLayoutManager ?=  null
    val c = Calendar.getInstance()
    val annee = c.get(Calendar.YEAR)
    val mois = c.get(Calendar.MONTH)
    val jour = c.get(Calendar.DAY_OF_MONTH)
    var   InputDate1:String ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(com.example.myapplication.R.layout.activity_main)
        val btn_recherche= findViewById<Button>(R.id.button_recherche)

        initDB()

         intervList?.addAll(array)
        Log.i("main",array.size.toString())
        val recyclerView =findViewById(com.example.myapplication.R.id.recyclerView) as RecyclerView
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        btn_recherche.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, annee, mois, jour ->
                   InputDate1="$jour-${mois+1}-$annee"
                intervList!!.clear()
                this.db = DataBase.invoke(this)
                this.dao = db?.intervDAO()
                intervList= this.dao!!.getIntervss(InputDate1!!)
                Log.i("mainn",intervList?.size.toString())
                Log.i("mainn",InputDate1)

                val adapter =IntervAdapter(this, this)


                recyclerView.adapter = adapter
            }, annee, mois, jour)
            datePickerDialog.show()



            }


        val adapter =IntervAdapter(this, this)


        recyclerView.adapter = adapter

        val activity = this

            val  fab =findViewById(com.example.myapplication.R.id.fab) as View
            fab.setOnClickListener {
                val intent = Intent(activity, TraitInterv_Activity::class.java)
                intent.putExtra("mode","ajout")
                startActivity(intent)
                Log.i("mainn",intervList?.size.toString())
            }





    }

    override fun OnItemClick(item: Interv, position: Int) {
        Toast.makeText(this, item.nom , Toast.LENGTH_SHORT).show()
        val intent = Intent(this,TraitInterv_Activity::class.java)
        intent.putExtra("mode","modif")
        intent.putExtra("pos", position)

        startActivity(intent)
    }
    fun initDB() {
        var act = this
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                act.db = DataBase.invoke(act)
                act.dao = db?.intervDAO()
                intervList= act.dao!!.getIntervs()
                Log.i("kotlin",intervList!!.size.toString())
                return null
            }
            override fun onPostExecute(result: Void?) {
                if(intervList != null) {
                } }
        }.execute()
    }



    }









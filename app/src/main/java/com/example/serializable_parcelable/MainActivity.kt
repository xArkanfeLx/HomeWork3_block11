package com.example.serializable_parcelable

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val persons:MutableList<Person> = mutableListOf()

    private lateinit var nameET:EditText
    private lateinit var familyET:EditText
    private lateinit var addressET:EditText
    private lateinit var phoneET:EditText

    private lateinit var saveBTN:Button

    private lateinit var personsLV:ListView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameET = findViewById(R.id.nameET)
        familyET = findViewById(R.id.familyET)
        addressET = findViewById(R.id.addressET)
        phoneET = findViewById(R.id.phoneET)
        saveBTN = findViewById(R.id.saveBTN)
        personsLV = findViewById(R.id.personsLV)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,persons)
        personsLV.adapter = adapter

        saveBTN.setOnClickListener{
            val name = nameET.text
            val family = familyET.text
            val address = addressET.text
            val phone = phoneET.text
            if(name.isEmpty() || family.isEmpty() || address.isEmpty() || phone.isEmpty()) return@setOnClickListener
            val person = Person(name.toString(),family.toString(),address.toString(),phone.toString())
            persons.add(person)
            adapter.notifyDataSetChanged()
            name.clear()
            family.clear()
            address.clear()
            phone.clear()
        }

        personsLV.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val person = persons[id.toInt()]
            intent = Intent(this,SecondActivity::class.java)
            intent.putExtra(Person::class.java.simpleName,person)
            startActivity(intent)
        }
    }
}
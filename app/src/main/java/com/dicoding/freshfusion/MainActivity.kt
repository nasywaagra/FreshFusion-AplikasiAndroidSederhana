package com.dicoding.freshfusion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private lateinit var rvDrink: RecyclerView
    private val list = ArrayList<Drink>()
    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDrink = findViewById<RecyclerView>(R.id.rv_drink)
        rvDrink.setHasFixedSize(true)

        list.addAll(getListDrink())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvDrink.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvDrink.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_myprofile -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.action_share -> {
                val selectedItem = list[0]
                val itemName = selectedItem.name
                val itemDescription = selectedItem.description

                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, itemName)
                shareIntent.putExtra(Intent.EXTRA_TEXT, itemDescription)

                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getListDrink(): ArrayList<Drink>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listDrink= ArrayList<Drink>()
        for (i in dataName.indices) {
            val drink = Drink(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listDrink.add(drink)
        }
        dataPhoto.recycle()
        return listDrink
    }

    private fun showRecyclerList() {
        rvDrink.layoutManager = LinearLayoutManager(this)
        val listDrinkAdapter =ListDrinkAdapter(list)
        rvDrink.adapter = listDrinkAdapter
    }

}

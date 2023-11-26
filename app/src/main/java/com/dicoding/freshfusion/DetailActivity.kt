package com.dicoding.freshfusion

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataDrink = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Drink>("key_menu", Drink::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Drink>("key_menu")
        }

        val tvName = findViewById<TextView>(R.id.tv_item_name)
        val tvDeskDetail = findViewById<TextView>(R.id.tv_item_description)
        val imgphoto = findViewById<ImageView>(R.id.img_item_photo)

        tvName.text = dataDrink?.name
        tvDeskDetail.text = dataDrink?.description
        imgphoto.setImageResource(dataDrink?.photo!!)

        // Menambahkan listener untuk tombol Share
        findViewById<ImageView>(R.id.action_share).setOnClickListener {
            shareItem(dataDrink?.name, dataDrink?.description)
        }
    }

    private fun shareItem(name: String?, description: String?) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, name)
        shareIntent.putExtra(Intent.EXTRA_TEXT, description)
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}

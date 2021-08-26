package com.example.mergeded

import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

const val EXTRA_WORDLIST = "com.example.mergeded.WORDLIST"

class FlashcardActivity : AppCompatActivity() {
    var imageList = mutableListOf<Int>(
        R.drawable.rest,
//        R.drawable.climb,
        R.drawable.cook,
//        R.drawable.argue,
        R.drawable.arguing,
//        R.drawable.cry,
        R.drawable.crying,
//        R.drawable.dance,
        R.drawable.diving,
//        R.drawable.exercise,
//        R.drawable.sail,
//        R.drawable.walk,
//        R.drawable.write,
    )
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

/// TODO: implement audio
//        mediaPlayer = MediaPlayer.create(this, R.raw.to_climb)
//        mediaPlayer?.setOnPreparedListener {
//
//        }

        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageArray = intent.getIntArrayExtra(EXTRA_WORDLIST)
        if (imageArray != null) {
            imageList = imageArray.toMutableList()
        }
        //imageView.setImageResource(imageList[0])
        val displayMetrics: DisplayMetrics = getResources().getDisplayMetrics()
        Glide.with(this).load(
            imageList[0]
        ).override(displayMetrics.widthPixels, displayMetrics.heightPixels).into(imageView)

        imageView.setOnClickListener {
            mediaPlayer?.start()

            val intent = Intent(this, RightOrWrongActivity::class.java).apply {
                putExtra(EXTRA_WORDLIST, imageList.toIntArray())
            }
            startActivity(intent)
        }
    }
}
package com.example.moviebooking24787

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class movie_details : AppCompatActivity() {
    private var seatsRemaining = 0
    private var seatsSelected = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        // Get the Movie object from the intent
        val movie = intent.getParcelableExtra<movie>("movie")

        // Initialize the seats remaining and seats selected values
        seatsRemaining = movie!!.seatsRemaining
        seatsSelected = 0

        // Find the views in the layout
        val movieImage = findViewById<ImageView>(R.id.movie_poster)
        val movieTitle = findViewById<TextView>(R.id.movie_name)
        val movieCertification = findViewById<TextView>(R.id.certification)
        val movieDescription = findViewById<TextView>(R.id.description)
        val movieStarring = findViewById<TextView>(R.id.movie_starring)
        val movieRunningTime = findViewById<TextView>(R.id.running_time_mins)
        val movieSeatsRemaining = findViewById<TextView>(R.id.movie_seats_remaining)
        val minusButton = findViewById<ImageView>(R.id.minus_button)
        val plusButton = findViewById<ImageView>(R.id.plus_button)

        // Load the movie data into the views
        Glide.with(movieImage.context)
            .load(movie.image)
            .into(movieImage)
        movieTitle.text = movie.name
        movieCertification.text = movie.certification
        movieDescription.text = movie.description
        movieStarring.text = "Starring: ${movie.starring?.joinToString(", ")}"
        movieRunningTime.text = "Running time: ${movie.runningTime} minutes"
        movieSeatsRemaining.text = "Seats remaining: $seatsRemaining"

        // Set click listeners for the minus and plus buttons
        minusButton.setOnClickListener { v: View? ->
            if (seatsSelected > 0) {
                seatsSelected--
                seatsRemaining++
                updateSeatsText()
                updateSeatsRemainingText()
            }
        }
        plusButton.setOnClickListener { v: View? ->
            if (seatsRemaining > 0) {
                seatsSelected++
                seatsRemaining--
                updateSeatsText()
                updateSeatsRemainingText()
            }
        }

        // Update the seats selected and seats remaining text
        updateSeatsText()
        updateSeatsRemainingText()
    }

    // Override the onBackPressed() method to return the selected seats
    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra("seats_selected", seatsSelected)
        setResult(RESULT_OK, resultIntent)
        super.onBackPressed()
    }

    // Update the seats selected text
    private fun updateSeatsText() {
        val seatsSelectedText = findViewById<TextView>(R.id.seats_selected_text)
        seatsSelectedText.text = seatsSelected.toString()
    }

    // Update the seats remaining text and enable/disable the minus and plus buttons
    private fun updateSeatsRemainingText() {
        val seatsRemainingText = findViewById<TextView>(R.id.seats_remaining_text)
        seatsRemainingText.text = seatsRemaining.toString()

        val minusButton = findViewById<ImageView>(R.id.minus_button)
        val plusButton = findViewById<ImageView>(R.id.plus_button)

        minusButton.isEnabled = seatsSelected > 0
        plusButton.isEnabled = seatsRemaining > 0
    }
}

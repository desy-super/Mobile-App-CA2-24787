package com.example.moviebooking24787

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class movie_adapter(private val movies: Array<movie>) : RecyclerView.Adapter<movie_adapter.ViewHolder>() {

    // ViewHolder class to hold the views for each movie item
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Initialize the views for the movie item
        private val movieImage: ImageView = itemView.findViewById(R.id.movie_poster)
        private val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
        private val movieCertification: TextView = itemView.findViewById(R.id.certification)
        private val movieDescription: TextView = itemView.findViewById(R.id.description)
        private val movieStarring: TextView = itemView.findViewById(R.id.movie_starring)
        private val movieRunningTime: TextView = itemView.findViewById(R.id.running_time_mins)
        private val movieSeatsRemaining: TextView = itemView.findViewById(R.id.movie_seats_remaining)
        private val movieFillingFast: TextView = itemView.findViewById(R.id.movie_filling_fast)

        // Bind the movie data to the views
        @SuppressLint("SetTextI18n")
        fun bind(movie: movie) {
            // Load the movie poster image using Glide
            Glide.with(movieImage.context)
                .load(movie.image)
                .into(movieImage)

            // Set the movie title, certification, description, and starring
            movieTitle.text = movie.name
            movieCertification.text = movie.certification
            movieDescription.text = movie.description
            movieStarring.text = "Starring: ${movie.starring?.joinToString(", ")}"

            // Set the movie running time and seats remaining
            movieRunningTime.text = "Running time: ${movie.runningTime} minutes"
            movieSeatsRemaining.text = "Seats remaining: ${movie.seatsRemaining}"

            // Set the "Filling Fast" visibility based on the seats remaining
            if (movie.seatsRemaining < 3) {
                movieFillingFast.visibility = View.VISIBLE
                movieFillingFast.text = "Filling Fast"
            } else {
                movieFillingFast.visibility = View.GONE
            }

            // Set a click listener to open the MovieDetails activity
            itemView.setOnClickListener { view ->
                val intent = Intent(view.context, movie_details::class.java)
                intent.putExtra("movie", movie)
                view.context.startActivity(intent)
            }
        }
    }

    // Create a new ViewHolder instance when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_items, parent, false)
        return ViewHolder(view)
    }

    // Bind the movie data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    // Return the number of movies in the adapter
    override fun getItemCount(): Int {
        return movies.size
    }
}

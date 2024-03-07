package sns.example.projectsubmission.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import sns.example.projectsubmission.Detail.Detail
import sns.example.projectsubmission.R

class ListMovieAdapter(private val listMovie: ArrayList<Movie>) : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_movie)
        val tvName: TextView = itemView.findViewById(R.id.nama_film)
        val tvGenre: TextView = itemView.findViewById(R.id.genre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, genre, photo) = listMovie[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvGenre.text = genre
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listMovie[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }
}
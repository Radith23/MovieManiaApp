package sns.example.projectsubmission

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sns.example.projectsubmission.About.About
import sns.example.projectsubmission.Adapter.ListMovieAdapter
import sns.example.projectsubmission.Adapter.Movie
import sns.example.projectsubmission.Detail.Detail

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private val list = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recycleView)
        recyclerView.setHasFixedSize(true)

        list.addAll(getListMovies())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveAbout = Intent(this, About::class.java)
                startActivity(moveAbout)
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListMovies(): ArrayList<Movie> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listMovie = ArrayList<Movie>()
        for (i in dataName.indices) {
            val movie = Movie(dataName[i], dataGenre[i], dataPhoto.getResourceId(i, -1))
            listMovie.add(movie)
        }
        return listMovie
    }

    private fun showRecyclerList() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val listMovieAdapter = ListMovieAdapter(list)
        recyclerView.adapter = listMovieAdapter

        listMovieAdapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {
                showSelectedMovie(data)
            }
        })
    }

    private fun showSelectedMovie (movie: Movie) {
        val movieDetail = Intent(this@MainActivity, Detail::class.java)
        movieDetail.putExtra("photo", movie.photo)
        movieDetail.putExtra("movie", movie)
        startActivity(movieDetail)
    }
}
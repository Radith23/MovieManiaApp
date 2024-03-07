package sns.example.projectsubmission.Detail

import android.app.Person
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sns.example.projectsubmission.Adapter.Movie
import sns.example.projectsubmission.R

class Detail : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbar: Toolbar
    private lateinit var detailName: TextView
    private lateinit var detailImg: ImageView
    private lateinit var shareButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getDetailFilm = intent.getParcelableExtra<Movie>("movie")
        val getDetailImg = intent.getIntExtra("photo", 0)
        val deskripsiMovie: TextView = findViewById(R.id.deskripsi)
        shareButton = findViewById(R.id.share_button)
        shareButton.setOnClickListener(this)

        shareButton.setOnTouchListener(object : View.OnTouchListener {
            private var lastX = 0
            private var lastY = 0

            override fun onTouch(view: View, event: MotionEvent): Boolean {
                val layoutParams = view.layoutParams as RelativeLayout.LayoutParams

                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        lastX = event.rawX.toInt()
                        lastY = event.rawY.toInt()
                    }

                    MotionEvent.ACTION_MOVE -> {
                        val deltaX = (event.rawX - lastX).toInt()
                        val deltaY = (event.rawY - lastY).toInt()

                        layoutParams.leftMargin += deltaX
                        layoutParams.topMargin += deltaY

                        view.layoutParams = layoutParams

                        lastX = event.rawX.toInt()
                        lastY = event.rawY.toInt()
                    }
                }
                return true
            }
        })

        toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)

        detailName = findViewById(R.id.film_detail)
        detailName.text = getDetailFilm?.name

        detailImg = findViewById(R.id.detail_img)
        detailImg.setImageResource(getDetailImg)

        val descriptionDetail = when (getDetailFilm?.name) {
            "Avengers Endgame" -> R.string.avengers_endgame
            "Batman" -> R.string.the_batman
            "Black Panther" -> R.string.black_panther_wakanda_forever
            "Dune" -> R.string.dune
            "Free Guy" -> R.string.free_guy
            "Glass" -> R.string.glass
            "Godzilla King of The Monster" -> R.string.godzilla_king_of_the_monsters
            "Guardians of The Galaxy" -> R.string.guardians_of_the_galaxy
            "Inception" -> R.string.inception
            "Interstellar" -> R.string.interstellar
            "Jumanji Next Level" -> R.string.jumanji_next_level
            "Pirates of Caribbean" -> R.string.pirates_of_the_caribbean_dead_men_tell_no_tales
            "The Greatest Showman" -> R.string.the_greatest_showman
            "Up" -> R.string.up
            "Wall E" -> R.string.walle

            else -> R.string.deskripsi
        }

        deskripsiMovie.text = getString(descriptionDetail)

    }

    override fun onClick(v: View?) {
        if (v?.id == shareButton.id) {
            shareApp()
        }
    }

    private fun shareApp() {
        val appMsg: String = "Hey!, Check out this share button :- " +
                "https://play.google.com/store/apps/details?id=sns.example.projectsubmission"

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, appMsg)
        intent.type = "text/plain"
        startActivity(intent)
    }
}
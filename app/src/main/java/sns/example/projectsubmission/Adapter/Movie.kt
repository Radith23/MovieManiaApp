package sns.example.projectsubmission.Adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val genre: String,
    val photo: Int
) : Parcelable

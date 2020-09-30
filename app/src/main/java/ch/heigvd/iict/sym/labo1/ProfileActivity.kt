package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val email: TextView = findViewById(R.id.profileTvEmail)
        val imgProfile: ImageView = findViewById(R.id.profileImgProfile)

        email.text = intent.getStringExtra("email")
        ImageDownloader(imgProfile, "https://thispersondoesnotexist.com/image").show()
    }

}
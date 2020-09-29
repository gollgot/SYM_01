package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class HomeActivity : AppCompatActivity() {

    private lateinit var emailTextView: TextView
    private lateinit var connectedImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Display user email
        emailTextView = findViewById(R.id.home_email)
        emailTextView.text = intent.getStringExtra("email")

        // Fetch image from internet
        connectedImageView = findViewById(R.id.home_image)
        ImageDownloader(connectedImageView, "https://thispersondoesnotexist.com/image").show()
    }
}
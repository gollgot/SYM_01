package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class SuccessActivity : AppCompatActivity() {

    private lateinit var successActivityConnectedImageView: ImageView
    private lateinit var successActivityUserEmailTextView : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        // Récupère le mail de l'activité appelant et l'affiche sur l'écran
        successActivityUserEmailTextView = findViewById<TextView>(R.id.success_user_email).apply {
            text = intent.getStringExtra(MainActivity.EXTRA_EMAIL)
        }

        // Récupère l'image depuis le lien et l'afficher sur l'activité
        successActivityConnectedImageView = findViewById<ImageView>(R.id.success_connected_image)
        ImageDownloader(successActivityConnectedImageView, "https://thispersondoesnotexist.com/image").show()


    }
}
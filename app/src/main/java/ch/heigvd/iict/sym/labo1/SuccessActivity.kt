package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class SuccessActivity : AppCompatActivity() {

    private lateinit var successActivityConnectedImageView: ImageView
    private lateinit var successActivityUserEmailTextView : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        Log.d(TAG, "State onCreate")

        // Récupère le mail de l'activité appelant et l'affiche sur l'écran
        successActivityUserEmailTextView = findViewById<TextView>(R.id.success_user_email).apply {
            text = intent.getStringExtra(MainActivity.EXTRA_EMAIL)
        }

        // Récupère l'image depuis le lien et l'afficher sur l'activité
        successActivityConnectedImageView = findViewById<ImageView>(R.id.success_connected_image)
        ImageDownloader(successActivityConnectedImageView, "https://thispersondoesnotexist.com/image").show()

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "State onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "State onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "State onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "State onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "State onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "State onRestart")
    }

    companion object{
        private const val TAG: String = "SuccessActivity"
    }
}
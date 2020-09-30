package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class HomeActivity : AppCompatActivity() {

    private lateinit var emailTextView: TextView
    private lateinit var connectedImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, AccountUtils.LOG_CREATE)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Display user email
        emailTextView = findViewById(R.id.home_email)
        emailTextView.text = intent.getStringExtra("email")

        // Fetch image from internet
        connectedImageView = findViewById(R.id.home_image)
        ImageDownloader(connectedImageView, "https://thispersondoesnotexist.com/image").show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, AccountUtils.LOG_START)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, AccountUtils.LOG_STOP)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, AccountUtils.LOG_PAUSE)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, AccountUtils.LOG_RESUME)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, AccountUtils.LOG_RESTART)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, AccountUtils.LOG_DESTROY)
    }

    companion object {
        private const val TAG: String = "HomeActivity"
    }
}
// Authors: Loïc Dessaules, Robin Demarta, Chau Ying Kot

package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        Log.d(TAG, Utils.LOG_CREATE)

        val email: TextView = findViewById(R.id.profileTvEmail)
        val imgProfile: ImageView = findViewById(R.id.profileImgProfile)

        email.text = intent.getStringExtra("email")
        ImageDownloader(imgProfile, "https://thispersondoesnotexist.com/image").show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, Utils.LOG_START)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, Utils.LOG_STOP)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, Utils.LOG_PAUSE)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, Utils.LOG_RESUME)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, Utils.LOG_RESTART)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, Utils.LOG_DESTROY)
    }

    companion object {
        private const val TAG: String = "ProfileActivity"
    }

}
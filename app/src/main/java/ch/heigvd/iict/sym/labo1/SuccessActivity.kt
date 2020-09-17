package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SuccessActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)


        val textView = findViewById<TextView>(R.id.success_user_email).apply{
            text = intent.getStringExtra(EXTRA_EMAIL)
        }

    }
}
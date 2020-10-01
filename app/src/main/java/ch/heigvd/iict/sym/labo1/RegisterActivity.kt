// Authors: Loïc Dessaules, Robin Demarta, Chau Ying Kot

package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import ch.heigvd.iict.sym.labo1.validator.AccountValidator

private lateinit var etEmail: EditText
private lateinit var etPassword: EditText
private lateinit var btnValidate: Button

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Log.d(TAG, Utils.LOG_CREATE)

        etEmail = findViewById(R.id.registerEtEmail)
        etPassword = findViewById(R.id.registerEtPassword)
        btnValidate = findViewById(R.id.registerBtnValidate)

        // Clique sur le bouton de validation
        btnValidate.setOnClickListener{
            val accountValidator = AccountValidator(this, etEmail, etPassword)
            // Account correct -> return to mainActivity and pass as result the email / password
            if(accountValidator.isValid()){
                val intent = Intent()
                intent.putExtra("email", etEmail.text.toString())
                intent.putExtra("password", etPassword.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

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
        private const val TAG: String = "RegisterActivity"
    }
}
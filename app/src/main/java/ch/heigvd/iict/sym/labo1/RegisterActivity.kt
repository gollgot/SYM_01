package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

private lateinit var etEmail: EditText
private lateinit var etPassword: EditText
private lateinit var btnValidate: Button

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etEmail = findViewById(R.id.registerEtEmail)
        etPassword = findViewById(R.id.registerEtPassword)
        btnValidate = findViewById(R.id.registerBtnValidate)

        // Clique sur le bouton de validation
        btnValidate.setOnClickListener{
            //on réinitialise les messages d'erreur
            etEmail.error = null
            etPassword.error = null

            //on récupère le contenu de deux champs dans des variables de type String
            val emailInput = etEmail.text?.toString()
            val passwordInput = etPassword.text?.toString()

            // Email ou mot de passe vide
            if(emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {
                Log.d(TAG, "Au moins un des deux champs est vide")
                if(emailInput.isNullOrEmpty())
                    etEmail.error = getString(R.string.main_mandatory_field)
                if(passwordInput.isNullOrEmpty())
                    etPassword.error = getString(R.string.main_mandatory_field)

                return@setOnClickListener
            }

            // Email verification
            if(!emailInput!!.contains("@")){
                etEmail.error = getString(R.string.main_error_invalid_email)
                return@setOnClickListener
            }

            // All correct -> return to mainActivity and pass as result the email / password
            val intent = Intent()
            intent.putExtra("email", emailInput)
            intent.putExtra("password", passwordInput)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    companion object {
        private const val TAG: String = "RegisterActivity"
    }
}
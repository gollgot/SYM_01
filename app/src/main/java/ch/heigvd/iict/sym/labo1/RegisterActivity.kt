package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    companion object {
        private const val TAG: String = "RegisterActivity"
    }
}
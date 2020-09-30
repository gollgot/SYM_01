package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NewAccountActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_account)

        // Get UI elements
        email = findViewById(R.id.new_account_email)
        password = findViewById(R.id.new_account_password)
        cancelButton = findViewById(R.id.new_account_cancel)
        validateButton = findViewById(R.id.new_account_validate)

        // Empty the input field with cancel button
        cancelButton.setOnClickListener {
            AccountUtils.ResetInputs(email, password)
        }

        // Send new account to MainActivity
        validateButton.setOnClickListener {
            //on réinitialise les messages d'erreur
            email.error = null
            password.error = null

            // Input check
            if(!AccountUtils.CredentialsInputsCheck(email, password, this))
                return@setOnClickListener

            // Tout ok, passer les infos de compte à l'autre activity
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", email.text?.toString())
            intent.putExtra("password", password.text?.toString())
            setResult(Activity.RESULT_OK, intent)

            finish()
        }
    }

    companion object {
        private const val TAG: String = "NewAccountActivity"
    }
}
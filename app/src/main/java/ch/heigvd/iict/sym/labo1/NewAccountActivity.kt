package ch.heigvd.iict.sym.labo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

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
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            email.text?.clear()
            password.text?.clear()
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            email.error = null
            password.error = null
        }

        // Send new account to MainActivity
        validateButton.setOnClickListener {
            //on réinitialise les messages d'erreur
            email.error = null
            password.error = null

            //on récupère le contenu de deux champs dans des variables de type String
            val emailInput = email.text?.toString()
            val passwordInput = password.text?.toString()

            if(emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {
                // on affiche un message dans les logs de l'application
                Log.d(NewAccountActivity.TAG, "Au moins un des deux champs est vide")
                // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
                // la méthode getString permet de charger un String depuis les ressources de
                // l'application à partir de son id
                if(emailInput.isNullOrEmpty())
                    email.error = getString(R.string.main_mandatory_field)
                if(passwordInput.isNullOrEmpty())
                    password.error = getString(R.string.main_mandatory_field)
                // Pour les fonctions lambda, on doit préciser à quelle fonction l'appel à return
                // doit être appliqué
                return@setOnClickListener
            }

            if(!emailInput!!.contains('@')) {
                // Adresse email invalide
                Toast.makeText(this, R.string.main_email_error_msg, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tout ok, passer les infos de compte à l'autre activity
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("user", Pair(emailInput, passwordInput))

            finish()
        }
    }

    companion object {
        private const val TAG: String = "NewAccountActivity"
    }
}
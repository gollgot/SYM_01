package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class AddNewAccount : AppCompatActivity() {

    private lateinit var newAccountActivityEmailEditText: EditText
    private lateinit var newAccountActivityPasswordEditText: EditText
    private lateinit var newAccountActivitySubmitButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_account)

        newAccountActivityEmailEditText = findViewById(R.id.new_account_email)
        newAccountActivityPasswordEditText= findViewById(R.id.new_account_password)
        newAccountActivitySubmitButton = findViewById(R.id.new_account_submit)

        newAccountActivitySubmitButton.setOnClickListener{

            newAccountActivityEmailEditText.error = null
            newAccountActivityPasswordEditText.error = null

            val mEmailInput = newAccountActivityEmailEditText.text?.toString()
            val mPassword = newAccountActivityPasswordEditText.text?.toString()

            if(mEmailInput.isNullOrEmpty() or mPassword.isNullOrEmpty()){
                // on affiche un message dans les logs de l'application
                Log.d(AddNewAccount.TAG, "Au moins un des deux champs est vide")
                // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
                // la méthode getString permet de charger un String depuis les ressources de
                // l'application à partir de son id
                if (mEmailInput.isNullOrEmpty())
                    mEmail.error = getString(R.string.main_mandatory_field)
                if (passwordInput.isNullOrEmpty())
                    password.error = getString(R.string.main_mandatory_field)
                // Pour les fonctions lambda, on doit préciser à quelle fonction l'appel à return
                // doit être appliqué
                return@setOnClickListener
            }
        }


    }

    companion object{
        private const val TAG : String = "AddNewAccountActivity"
    }
}
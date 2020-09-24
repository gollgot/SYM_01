package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddNewAccountActivity : AppCompatActivity() {

    private lateinit var newAccountActivityEmailEditText: EditText
    private lateinit var newAccountActivityPasswordEditText: EditText
    private lateinit var newAccountActivitySubmitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_account)

        newAccountActivityEmailEditText = findViewById(R.id.new_account_email)
        newAccountActivityPasswordEditText = findViewById(R.id.new_account_password)
        newAccountActivitySubmitButton = findViewById(R.id.new_account_submit)

        newAccountActivitySubmitButton.setOnClickListener {

            newAccountActivityEmailEditText.error = null
            newAccountActivityPasswordEditText.error = null

            val mEmailInput = newAccountActivityEmailEditText.text?.toString()
            val mPasswordInput = newAccountActivityPasswordEditText.text?.toString()

            if (mEmailInput.isNullOrEmpty() or mPasswordInput.isNullOrEmpty()) {
                // on affiche un message dans les logs de l'application
                Log.d(AddNewAccountActivity.TAG, "Au moins un des deux champs est vide")
                // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
                // la méthode getString permet de charger un String depuis les ressources de
                // l'application à partir de son id
                if (mEmailInput.isNullOrEmpty())
                    newAccountActivityEmailEditText.error = getString(R.string.main_mandatory_field)
                if (mPasswordInput.isNullOrEmpty())
                    newAccountActivityPasswordEditText.error =
                        getString(R.string.main_mandatory_field)
                // Pour les fonctions lambda, on doit préciser à quelle fonction l'appel à return
                // doit être appliqué
                return@setOnClickListener
            } else if ('@' !in mEmailInput!!) {

                Log.d(TAG, "Le mail n'est pas valid")
                val toast = Toast.makeText(
                    applicationContext,
                    getString(R.string.main_invalid_email),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {

                // Crée un intent pour ajouter l'email et le mdp qui doivent être rendu à la main activity
                val mIntent = Intent(this, MainActivity::class.java).apply {
                    putExtra("email", mEmailInput)
                    putExtra("password", mPasswordInput)
                }

                Log.d(TAG, "data sent")

                setResult(Activity.RESULT_OK, mIntent)

                // termine l'activité
                finish()

            }
        }


    }

    companion object {
        private const val TAG: String = "AddNewAccountActivity"
    }
}
package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import ch.heigvd.iict.sym.labo1.validator.AccountValidator

class MainActivity : AppCompatActivity() {

    // on définit une liste de couples e-mail / mot de passe
    // ceci est fait juste pour simplifier ce premier laboratoire,
    // mais il est évident que de hardcoder ceux-ci est une pratique à éviter à tout prix...
    private val credentials = mutableListOf(
                                Pair("user1@heig-vd.ch","1234"),
                                Pair("user2@heig-vd.ch","abcd")
                            )

    // le modifieur lateinit permet de définir des variables avec un type non-null
    // sans pour autant les initialiser immédiatement
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnCancel: Button
    private lateinit var btnValidate: Button
    private lateinit var tvRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_main)

        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        etEmail = findViewById(R.id.main_email)
        etPassword = findViewById(R.id.main_password)
        btnCancel = findViewById(R.id.main_cancel)
        btnValidate = findViewById(R.id.main_validate)
        tvRegister = findViewById(R.id.main_new_account)
        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        //mise en place des événements
        btnCancel.setOnClickListener {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            etEmail.text?.clear()
            etPassword.text?.clear()
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            etEmail.error = null
            etPassword.error = null
        }

        // Click on validate button
        btnValidate.setOnClickListener {
            val accountValidator = AccountValidator(this, etEmail, etPassword)

            if(accountValidator.isValid()){
                // Credential verification
                if(!this.credentials.contains(Pair(etEmail.text.toString(),etPassword.text.toString()))){
                    val builder = AlertDialog.Builder(this)
                    builder.apply {
                        setPositiveButton(getString(R.string.main_dialog_close),
                            DialogInterface.OnClickListener { dialog, id ->
                                // User clicked OK button
                            })
                    }
                    builder.setTitle(getString(R.string.main_dialog_credentials_title))
                    builder.setMessage(getString(R.string.main_dialog_credentials_message))
                    // Create the AlertDialog
                    builder.create()
                    builder.show()

                    return@setOnClickListener
                }

                // All corrects -> go to ProfileActivity
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("email", etEmail.text.toString())
                startActivity(intent)
            }
        }

        // Click on register text view
        tvRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, LAUNCH_REGISTER_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // On revient de l'activity "Register"
        if(requestCode == LAUNCH_REGISTER_ACTIVITY) {
            // Resultat OK
            if(resultCode == Activity.RESULT_OK) {
                val email: String = data?.getStringExtra("email").toString()
                val password: String = data?.getStringExtra("password").toString()
                credentials.add(Pair(email, password))

                Toast.makeText(this, email + " " + getString(R.string.main_register_successful), Toast.LENGTH_SHORT).show()
            }
        }
    }

    // En Kotlin, les variables static ne sont pas tout à fait comme en Java
    // pour des raison de lisibilité du code, les variables et méthodes static
    // d'une classe doivent être regroupées dans un bloc à part: le companion object
    // cela permet d'avoir un aperçu plus rapide de tous les éléments static d'une classe
    // sans devoir trouver le modifieur dans la définition de ceux-ci, qui peuvent être mélangé
    // avec les autres éléments non-static de la classe
    companion object {
        private const val TAG: String = "MainActivity"
        private const val LAUNCH_REGISTER_ACTIVITY: Int = 1
    }

}

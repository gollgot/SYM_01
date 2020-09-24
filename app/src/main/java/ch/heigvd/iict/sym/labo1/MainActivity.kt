package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import ch.heigvd.iict.sym.labo1.account.InputChecker

const val EXTRA_EMAIL = "ch.heigvd.iict.sym.labo1.EMAIL"

class MainActivity : AppCompatActivity() {

    // on définit une liste de couples e-mail / mot de passe
    // ceci est fait juste pour simplifier ce premier laboratoire,
    // mais il est évident que de hardcoder ceux-ci est une pratique à éviter à tout prix...
    private val credentials = mutableListOf(
        Pair("user1@heig-vd.ch", "1234"),
        Pair("user2@heig-vd.ch", "abcd")
    )


    // le modifieur lateinit permet de définir des variables avec un type non-null
    // sans pour autant les initialiser immédiatement
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button
    private lateinit var mainActivityNewAccountTextView: TextView


    /**
     * Callback : récupère les informations de l'utilisateur et l'ajouter à la liste
     */
    private val getCredentials =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            // Si le résultat est un succès
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "data receive \n email : $email, password : $password")
                val email = result.data?.getStringExtra("email").toString()
                val password = result.data?.getStringExtra("password").toString()
                credentials.add(Pair(email, password))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_main)

        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        email = findViewById(R.id.main_email)
        password = findViewById(R.id.main_password)
        cancelButton = findViewById(R.id.main_cancel)
        validateButton = findViewById(R.id.main_validate)
        mainActivityNewAccountTextView = findViewById(R.id.main_new_account)
        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        //mise en place des événements
        cancelButton.setOnClickListener {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            email.text?.clear()
            password.text?.clear()
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            email.error = null
            password.error = null
        }

        validateButton.setOnClickListener {
            //on réinitialise les messages d'erreur
            email.error = null
            password.error = null

            //on récupère le contenu de deux champs dans des variables de type String
            val emailInput = email.text?.toString()
            val passwordInput = password.text?.toString()

            // Si l'input est correct
            if (InputChecker.input(
                    emailInput,
                    passwordInput,
                    email,
                    password,
                    this,
                    applicationContext
                )
            ) {
                if (credentials.contains(Pair(emailInput, passwordInput))) {
                    // ouvre une nouvelle activité si l'utilisatuer est reconnu
                    Log.d(TAG, "Utilisateur reconnu")
                    val intent = Intent(this, SuccessActivity::class.java).apply {
                        putExtra(EXTRA_EMAIL, emailInput)
                    }
                    startActivity(intent)
                } else {
                    Log.d(TAG, "Utilisteur inconnu")

                    // Construction du dialog
                    val builder = AlertDialog.Builder(this)

                    builder.setTitle(getString(R.string.main_error))
                    builder.setMessage(getString(R.string.main_invalid_email_password))
                    builder.setPositiveButton(
                        "OK"
                    ) { dialog, _ -> dialog.dismiss() }
                    builder.show()

                }
            }
        }

        // Ouvre une activité pour la création de compte et attends une réponse de la nouvelle activité
        mainActivityNewAccountTextView.setOnClickListener() {
            getCredentials.launch(Intent(this, AddNewAccountActivity::class.java))
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
    }

}

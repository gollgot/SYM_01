package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import ch.heigvd.iict.sym.labo1.AccountUtils.Companion.ResetInputs

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
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button
    private lateinit var newAccountTextView: TextView

    // Callback : récupère les informations de l'utilisateur et l'ajouter à la liste
    private val getCredentials =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            // Si le résultat est un succès
            if (result.resultCode == Activity.RESULT_OK) {
                val email = result.data?.getStringExtra("email").toString()
                val password = result.data?.getStringExtra("password").toString()
                credentials.add(Pair(email, password))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, AccountUtils.LOG_CREATE)

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
        newAccountTextView = findViewById(R.id.main_new_account)
        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        //mise en place des événements
        cancelButton.setOnClickListener {
            ResetInputs(email, password)
        }

        validateButton.setOnClickListener {
            //on réinitialise les messages d'erreur
            email.error = null
            password.error = null

            //on récupère le contenu de deux champs dans des variables de type String
            val emailInput = email.text?.toString()
            val passwordInput = password.text?.toString()

            // Input check
            if(!AccountUtils.CredentialsInputsCheck(email, password, this))
                return@setOnClickListener

            // Verify credentials validity
            if(!credentials.contains(Pair(emailInput, passwordInput))) {
                // Credentials invalides
                val alertDialog: AlertDialog? = this.let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setPositiveButton("OK",
                            DialogInterface.OnClickListener { dialog, id ->
                                // User clicked OK button
                            })
                    }

                    // Create the AlertDialog
                    builder.setMessage(R.string.main_credentials_error_msg)
                    builder.setTitle(R.string.main_credentials_error_title)
                    builder.create()
                }
                alertDialog?.show()
                return@setOnClickListener
            }

            // Tout ok, passer à l'autre activity
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("email", emailInput)
            startActivity(intent)
        }

        // Aller sur NewAccountActivity et attend un résulat de sa part
        newAccountTextView.setOnClickListener() {
            getCredentials.launch(Intent(this, NewAccountActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, AccountUtils.LOG_START)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, AccountUtils.LOG_STOP)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, AccountUtils.LOG_PAUSE)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, AccountUtils.LOG_RESUME)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, AccountUtils.LOG_RESTART)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, AccountUtils.LOG_DESTROY)
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

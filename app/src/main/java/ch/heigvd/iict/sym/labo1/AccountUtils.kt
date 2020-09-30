package ch.heigvd.iict.sym.labo1

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.widget.TextView
import android.widget.Toast

import android.content.Context
import android.widget.EditText
import ch.heigvd.iict.sym.labo1.R

class AccountUtils {
    companion object {
        private const val TAG: String = "AccountUtils"

        fun CredentialsInputsCheck(
            email: TextView,
            password: TextView,
            context: Context
        ): Boolean {
            val emailText = email.text
            val passwordText = password.text

            if(emailText.isEmpty() or passwordText.isEmpty()) {
                // on affiche un message dans les logs de l'application
                Log.d(TAG, "Au moins un des deux champs est vide")
                // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
                // la méthode getString permet de charger un String depuis les ressources de
                // l'application à partir de son id
                if(emailText.isEmpty())
                    email.error = context.getString(R.string.main_mandatory_field)
                if(passwordText.isEmpty())
                    password.error = context.getString(R.string.main_mandatory_field)
                // Pour les fonctions lambda, on doit préciser à quelle fonction l'appel à return
                // doit être appliqué
                return false
            }

            if(!emailText.contains('@')) {
                // Adresse email invalide
                Toast.makeText(context, R.string.main_email_error_msg, Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }

        fun ResetInputs(emailInput: EditText, passwordInput: EditText) {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            emailInput.text?.clear()
            passwordInput.text?.clear()
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            emailInput.error = null
            passwordInput.error = null
        }
    }
}
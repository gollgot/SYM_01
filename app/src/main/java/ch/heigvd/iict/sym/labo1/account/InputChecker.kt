package ch.heigvd.iict.sym.labo1.account

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import ch.heigvd.iict.sym.labo1.R

class InputChecker {


    companion object {
        fun input(
            email: String?,
            password: String?,
            emailEditText: EditText,
            passwordEditText: EditText,
            context: Context,
            applicationContext: Context
        ): Boolean {

            val emailNullOrEmpty = isInputNullOrEmpty(email, emailEditText, context)
            val passwordNullOrEmpty = isInputNullOrEmpty(password, passwordEditText, context)

            if(emailNullOrEmpty || passwordNullOrEmpty) return false

            if ('@' !in email!!) {
                Log.d(InputChecker.TAG, "Le mail n'est pas valid")
                val toast = Toast.makeText(
                    applicationContext,
                    context.getString(R.string.main_invalid_email),
                    Toast.LENGTH_SHORT
                )
                toast.show()

                return false
            }

            return true
        }

        private fun isInputNullOrEmpty(s: String?, sEditText: EditText, context: Context): Boolean {
            if (s.isNullOrEmpty()) {
                sEditText.error = context.getString(R.string.main_mandatory_field)
                return true
            }
            return false
        }

        private const val TAG: String = "InputChecker"
    }

}

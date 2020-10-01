package ch.heigvd.iict.sym.labo1.validator

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import ch.heigvd.iict.sym.labo1.R

class AccountValidator(var context: Context, var etEmail: EditText, var etPassword : EditText)  {

    /**
     * Check if the account (email / password) is correct
     * - Check that both fields are not empty
     * - Check that the email contains a "@"
     *
     * Set an error if the field is incorrect
     */
    fun isValid() : Boolean{
        // Clean error
        etEmail.error = null
        etPassword.error = null

        // Fetch texts from edit text
        val emailInput = etEmail.text?.toString()

        val emailHasError = isFieldNullOrEmpty(context, etEmail)
        val passwordHasError = isFieldNullOrEmpty(context, etPassword)
        // Check email / password empty validity
        if(emailHasError || passwordHasError){
            return false
        }

        // Email verification
        if(!emailInput!!.contains("@")){
            Toast.makeText(context, context.getString(R.string.main_error_invalid_email), Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    /**
     * Check if the EditText field in parameter is null or empty
     * Set an error if the field is null or empty
     */
    private fun isFieldNullOrEmpty(context: Context, field: EditText) : Boolean {
        if(field.text.isNullOrEmpty()) {
            field.error = context.getString(R.string.main_mandatory_field)
            return true
        }

        return false
    }


}
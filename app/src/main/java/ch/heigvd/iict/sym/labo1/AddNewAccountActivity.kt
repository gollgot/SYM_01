package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import ch.heigvd.iict.sym.labo1.account.InputChecker

class AddNewAccountActivity : AppCompatActivity() {

    private lateinit var newAccountActivityEmailEditText: EditText
    private lateinit var newAccountActivityPasswordEditText: EditText
    private lateinit var newAccountActivitySubmitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_account)

        Log.d(TAG, "State onCreate")

        newAccountActivityEmailEditText = findViewById(R.id.new_account_email)
        newAccountActivityPasswordEditText = findViewById(R.id.new_account_password)
        newAccountActivitySubmitButton = findViewById(R.id.new_account_submit)

        newAccountActivitySubmitButton.setOnClickListener {

            newAccountActivityEmailEditText.error = null
            newAccountActivityPasswordEditText.error = null

            val mEmailInput = newAccountActivityEmailEditText.text?.toString()
            val mPasswordInput = newAccountActivityPasswordEditText.text?.toString()


            // Si l'input est correct
            if (InputChecker.input(
                    mEmailInput,
                    mPasswordInput,
                    newAccountActivityEmailEditText,
                    newAccountActivityPasswordEditText,
                    this,
                    applicationContext
                )
            ) {

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


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "State onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "State onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "State onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "State onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "State onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "State onRestart")
    }

    companion object {
        private const val TAG: String = "AddNewAccountActivity"
    }
}
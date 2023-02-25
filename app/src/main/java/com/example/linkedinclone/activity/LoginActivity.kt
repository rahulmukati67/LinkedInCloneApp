package com.example.linkedinclone.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.linkedinclone.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.database.FirebaseDatabase



class LoginActivity : AppCompatActivity() {
    var mGoogleSignInCLientl: GoogleSignInClient? = null
    var RC_SIGN_IN = 1
    var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val b = intent.extras


        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        mGoogleSignInCLientl = GoogleSignIn.getClient(this, gso)
        findViewById<View>(R.id.loginButton).setOnClickListener {
            val intent: Intent = mGoogleSignInCLientl!!.getSignInIntent()
            startActivityForResult(intent, RC_SIGN_IN)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account: GoogleSignInAccount = task.getResult()
            authWithGoogle(account.getIdToken())
        }
    }

    fun authWithGoogle(idtoken: String?) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(idtoken, null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(object : OnCompleteListener<AuthResult?> {
                override fun onComplete(task: Task<AuthResult?>) {
                    if (task.isSuccessful()) {
//                        val user: FirebaseUser? = auth?.getCurrentUser()
//                        val FireBaseuser =
//                            user?.getDisplayName()
//                                ?.let { user?.let { it1 -> User(it1.getUid(), it, user.getEmail()!!) } }
//                        if (user != null) {
//                            database?.getReference()?.child("user")?.child(user.getUid())
//                                ?.setValue(FireBaseuser)
//                                ?.addOnCompleteListener(object : OnCompleteListener<Void?> {
//                                    override fun onComplete(task: Task<Void?>) {
                                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
//                                })
//                        }
//                    }
                }
            })
    }

}
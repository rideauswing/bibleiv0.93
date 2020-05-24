package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButtonReg;
    private Button mButtonLog;
    private EditText mEditEmail;
    private EditText mEditPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mButtonReg = findViewById(R.id.button_register);
        mButtonLog = findViewById(R.id.button_login);
        mEditEmail = findViewById(R.id.edit_text_email);
        mEditPassword = findViewById(R.id.edit_text_password);
        mButtonReg.setOnClickListener(this);
        mButtonLog.setOnClickListener(this);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){

                }
                else{

                }
            }
        };


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_register){
            registering(mEditEmail.getText().toString(), mEditPassword.getText().toString());
        }
        else{
            signing(mEditEmail.getText().toString(), mEditPassword.getText().toString());
        }
    }
    public void signing(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    //  Toast.makeText(AuthActivity.this, "Authentication was successful", Toast.LENGTH_SHORT).show();
                     openImagesActivity();
                }
                else
                    Toast.makeText(AuthActivity.this, "Authentication wasn't successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void registering(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    // Toast.makeText(AuthActivity.this, "Registration was successful", Toast.LENGTH_SHORT).show();
                     openImagesActivity();
                }
                else {
                    Toast.makeText(AuthActivity.this, "Registration wasn't successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void openImagesActivity(){
        Intent intent = new Intent(this, ImagesActivity.class);
        startActivity(intent);
    }
}


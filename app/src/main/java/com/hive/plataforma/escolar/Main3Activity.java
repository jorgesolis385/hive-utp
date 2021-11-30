package com.hive.plataforma.escolar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {

    TextView back;
    private EditText editTextName,editTextEmail,editTextPassw,editTextPassw2;
    private Button buttonAccess;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
        back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());
        this.firebaseAuth = FirebaseAuth.getInstance();
        //this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.editTextPassw =  findViewById(R.id.et_passw);
        this.editTextEmail = findViewById(R.id.et_email);
        this.buttonAccess = findViewById(R.id.bt_access);
        this.buttonAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(editTextEmail.getText().toString(),editTextPassw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(Main3Activity.this,HomeActivity.class));
                        }
                    }
                });
            }
        });
    }
}

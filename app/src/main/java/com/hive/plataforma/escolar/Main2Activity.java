package com.hive.plataforma.escolar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main2Activity extends AppCompatActivity {

    TextView back;
    private EditText editTextName,editTextEmail,editTextPassw,editTextPassw2;
    private Button buttonRegistro;
    private AutoCompleteTextView autoCompleteTextView;
    private String name="";
    FirebaseAuth firebaseAuth;
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference databaseReference;
    String tipos[] = {"Lion", "Tiger", "Monkey", "Elephant", "Dog", "Cat", "Camel"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        String items[]  = {"Estudiante", "Docente"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, items);
        this.autoCompleteTextView = findViewById(R.id.et_tipo);
        this.autoCompleteTextView.setAdapter(arrayAdapter);

    this.editTextName =  findViewById(R.id.et_nombre);
        this.editTextEmail = findViewById(R.id.et_correo);
        this.editTextPassw = findViewById(R.id.et_passw);
        this.editTextPassw2 = findViewById(R.id.et_passw2);
        this.buttonRegistro = findViewById(R.id.bt_registro);
        this.buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextName.getText().toString().isEmpty() && !editTextEmail.getText().toString().isEmpty() && !editTextPassw.getText().toString().isEmpty() ){
                   // if(editTextPassw.getText().toString().equals(editTextPassw.getText().toString())){
                        registerUser();
                    //}
                }else
                {
                    Toast.makeText(Main2Activity.this,"Por favor ingrese datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  void registerUser()
    {
        this.firebaseAuth.createUserWithEmailAndPassword(editTextEmail.getText().toString(),editTextPassw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.i("Activity","Ingreso" + editTextEmail.getText().toString());
               if(task.isSuccessful()){
                   Log.i("Activity","Success");
                    Map<String, Object> map = new HashMap<>();
                    map.put("name",editTextName.getText().toString());
                    map.put("email",editTextEmail.getText().toString());
                    map.put("tipo",autoCompleteTextView.getText().toString());
                    String id = firebaseAuth.getCurrentUser().getUid();
                    db.collection("Users").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> taskdb) {
                            if(taskdb.isSuccessful()){
                                Toast.makeText(Main2Activity.this,"Registro completado exitosamente",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

               } else
               {
                   Log.i("Activity","Error regsitro");
                   Toast.makeText(Main2Activity.this,"No se registro el usuario",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}

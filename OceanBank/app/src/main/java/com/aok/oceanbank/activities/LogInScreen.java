package com.aok.oceanbank.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aok.oceanbank.R;
import com.aok.oceanbank.databinding.ActivityLogInScreenBinding;
import com.aok.oceanbank.databinding.ActivityMainPageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInScreen extends AppCompatActivity {

    private ActivityLogInScreenBinding binding;
    private FirebaseAuth auth;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
    }

    public void logIn(View view){

        String email, password;

        email = binding.emailEditText.getText().toString();
        password = binding.passwordEditText.getText().toString();

        if (email.matches("")  || password.matches("")){
            Toast.makeText(LogInScreen.this, "Email adresi veya parola girilmedi", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LogInScreen.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogInScreen.this, MainPage.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LogInScreen.this, "Hata: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }



    public void newCustomer(View view){

        Intent intent = new Intent(LogInScreen.this, RegisterScreen.class);
        startActivity(intent);
        finish();

    }

    /*
     if (firebaseUser != null){
            userID = "X4kl07BMFPCZzH7iDprr";
        } else {
            Toast.makeText(MainPage.this, "Kullanıcı verisine ulaşılamadı", Toast.LENGTH_SHORT).show();
        }

        docRef = firestore.collection("Users").document(userID);
        docRef.get().addOnCompleteListener(task -> {
           if (task.isSuccessful()){
               DocumentSnapshot document = task.getResult();
               if (document.exists()){
                   userName = document.get("firstName").toString();
               }else {
                   System.out.println("Doküman Bulunamadı");
               }
           } else {
             Toast.makeText(MainPage.this, "HATAAAAA", Toast.LENGTH_SHORT).show();
           }
        });
     */

}
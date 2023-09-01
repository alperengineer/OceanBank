package com.aok.oceanbank.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aok.oceanbank.databinding.ActivityRegisterScreenBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterScreen extends AppCompatActivity {

    private ActivityRegisterScreenBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    public void register(View view) {

        String firstName, lastName, identificationNumber, phoneNumber, email, password;
        firstName = binding.registerScreenFirstNameText.getText().toString();
        lastName = binding.registerScreenLastNameText.getText().toString();
        identificationNumber = binding.registerScreenIdentificationNumber.getText().toString();
        phoneNumber = binding.registerScreenPhoneNumber.getText().toString();
        email = binding.registerScreenEmailText.getText().toString();
        password = binding.registerScreenPasswordText.getText().toString();

        if (firstName.matches("")) {
            Toast.makeText(RegisterScreen.this, "Ad girilmedi", Toast.LENGTH_SHORT).show();
        } else if (lastName.matches("")) {
            Toast.makeText(RegisterScreen.this, "Soyad girilmedi", Toast.LENGTH_SHORT).show();
        } else if (identificationNumber.matches("")) {
            Toast.makeText(RegisterScreen.this, "T.C. Kimlik Numarası girilmedi", Toast.LENGTH_SHORT).show();
        } else if (phoneNumber.matches("")) {
            Toast.makeText(RegisterScreen.this, "Telefon numarası girilmedi", Toast.LENGTH_SHORT).show();
        } else if (email.matches("")) {
            Toast.makeText(RegisterScreen.this, "Email adresi girilmedi", Toast.LENGTH_SHORT).show();
        } else if (password.matches("")) {
            Toast.makeText(RegisterScreen.this, "Parola girilmedi", Toast.LENGTH_SHORT).show();
        } else {

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {


                            HashMap<String, Object> setData = new HashMap<>();

                            setData.put("firstName", firstName);
                            setData.put("lastName", lastName);
                            setData.put("identficationNumber", identificationNumber);
                            setData.put("phoneNumber", phoneNumber);
                            setData.put("email", email);
                            setData.put("password", password);

                            firestore.collection("Users").add(setData)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Intent intent = new Intent(RegisterScreen.this, LogInScreen.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterScreen.this, "Hata: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterScreen.this, "Hata: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
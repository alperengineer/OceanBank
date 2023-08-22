package com.aok.oceanbank.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aok.oceanbank.databinding.FragmentHomeBinding;
import com.aok.oceanbank.model.UserModel;
import com.aok.oceanbank.view.MainPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    ArrayList<UserModel> userModelArrayList;
    FirebaseUser user;
    FirebaseFirestore firestore;
    private static final String TAG = "DENEME";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userModelArrayList = new ArrayList<>();

        getUserName();

        /*

        String firstName = user.zzb().getDisplayName();
        System.out.println(firstName);


         */



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding =  FragmentHomeBinding.inflate(inflater, container, false);



        binding.nameTextView.setText("Hoşgeldin, Alperen Oğuz");

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    public void getUserName() {


        /*
        firestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error != null){
                    Toast.makeText(HomeFragment.this , "Hata: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }



                if (value != null) {

                    for (DocumentSnapshot documentSnapshot : value.getDocuments()){

                        Map<String, Object> userData = documentSnapshot.getData();

                        String userFirstName = (String) userData.get("firstName");
                        //String userLastName = (String) userData.get("lastName");
                        //String userEmail = (String) userData.get("email");

                        UserModel userModel = new UserModel(userFirstName);
                        userModelArrayList.add(userModel);

                    }

                }

            }
        });
        */

    }



}
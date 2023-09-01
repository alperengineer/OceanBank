package com.aok.oceanbank.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aok.oceanbank.R;
import com.aok.oceanbank.databinding.FragmentPayBillBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PayBillFragment extends Fragment {

    FragmentPayBillBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    public PayBillFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPayBillBinding.inflate(inflater, container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.pay_bill_fragment_price_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payBill();
            }
        });

    }

    public void payBill(){

        String orgNumber, identificationNumber, price;

        orgNumber = binding.payBillFragmentOrganisationNumber.getText().toString();
        identificationNumber = binding.payBillFragmentIdentificationNumber.getText().toString();
        price = binding.payBillFragmentPrice.getText().toString();

        Map<String, Object> payBill = new HashMap<>();
        payBill.put("orgNumber", orgNumber);
        payBill.put("identificationNumber", identificationNumber);
        payBill.put("price", price);

        firestore.collection("Prices").add(payBill)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "Veri kaydedildi", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Veri kaydedilmedi", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
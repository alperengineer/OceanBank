package com.aok.oceanbank.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.aok.oceanbank.R;
import com.aok.oceanbank.databinding.FragmentCashbackBinding;

import java.util.Random;


public class CashbackFragment extends Fragment {

    FragmentCashbackBinding binding;
    private ImageView infoButton;
    private Button createCodeButton;

    public CashbackFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCashbackBinding.inflate(inflater, container, false);
       
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        infoButton = binding.cashbackFragmentInfoImage;
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               infoImageClick();

            }
        });

        createCodeButton = binding.cashbackFragmentCreateCodeButton;
        createCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCodeButtonClick();
            }
        });

    }

    public void infoImageClick(){


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Cashback")
                .setMessage("Cashback yaygın olarak kullanılan birçok ücretli uygulamayı daha ucuza kullanmanızı sağlayan bir Ocean fırsatıdır. İndirimlere ulaşmak için alttan kod üretin.")
                .setPositiveButtonIcon(getResources().getDrawable(R.drawable.oceanbank))
                .setPositiveButton("Ocean", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(),"Ocean fırsatlar dünyasına hoşgeldin", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    public void createCodeButtonClick(){

        Random random = new Random();
        int code = random.nextInt(89999)+10000;
        binding.cashbackFragmentCashbackCodeText.setText("Kod: " + code);
        
    }
}
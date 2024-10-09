package com.nela.dosgaracciolo.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.nela.dosgaracciolo.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);


        binding.btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.acceder(binding.etEmail.getText().toString(),binding.etClave.getText().toString());
                binding.etClave.setText("");
                binding.etEmail.setText("");
            }
        });


        binding.btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.registro();
            }
        });
    }


}
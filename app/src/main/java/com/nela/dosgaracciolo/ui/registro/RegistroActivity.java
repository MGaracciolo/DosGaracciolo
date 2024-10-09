package com.nela.dosgaracciolo.ui.registro;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nela.dosgaracciolo.databinding.ActivityRegistroBinding;
import com.nela.dosgaracciolo.models.Usuario;


public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding binding;
    private RegistroActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        setContentView(binding.getRoot());

        vm.getUsuarioMutable().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.edDni.setText(usuario.getDni());
                binding.edNombre.setText(usuario.getNombre());
                binding.edApellido.setText(usuario.getApellido());
                binding.etMail.setText(usuario.getEmail());
                binding.etClave.setText(usuario.getClave());
            }
        });
        vm.mostrar(getIntent());

        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = binding.edDni.getText().toString();
                String nombre = binding.edNombre.getText().toString();
                String apellido = binding.edApellido.getText().toString();
                String mail = binding.etMail.getText().toString();
                String clave = binding.etClave.getText().toString();
                Usuario u = new Usuario(dni,nombre,apellido,mail,clave);
                vm.crear(u);
            }
        });
    }
}
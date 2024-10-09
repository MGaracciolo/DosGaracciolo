package com.nela.dosgaracciolo.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nela.dosgaracciolo.models.Usuario;
import com.nela.dosgaracciolo.request.ApiClient;
import com.nela.dosgaracciolo.ui.login.MainActivity;


public class RegistroActivityViewModel extends AndroidViewModel {
    private Context contexto;
    private MutableLiveData<Usuario> usuarioMutable;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        contexto=application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioMutable() {
        if(usuarioMutable==null){
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }

    public void mostrar(Intent intent){
        if(intent != null && intent.hasExtra("login")){
            boolean ingresado = intent.getBooleanExtra("login", false);
            Log.d("RegistroActivityViewModel", "Usuario ingresado: " + ingresado);
            if(ingresado){
                usuarioMutable.setValue(ApiClient.leerDatos(contexto));
            } else {
                usuarioMutable.setValue(new Usuario());
            }
        }
    }


    public void crear(Usuario usuario){
        if(usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            Toast.makeText(contexto, "El email no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        if(usuario.getClave() == null || usuario.getClave().isEmpty()) {
            Toast.makeText(contexto, "La clave no puede estar vacía", Toast.LENGTH_SHORT).show();
            return;
        }

        if(ApiClient.guardar(contexto,usuario)) {
            Toast.makeText(contexto, "Usuario guardado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(contexto, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contexto.startActivity(intent);
        }else{
            Toast.makeText(contexto,"Error, usuario no guardado", Toast.LENGTH_SHORT).show();
        }
    }

}

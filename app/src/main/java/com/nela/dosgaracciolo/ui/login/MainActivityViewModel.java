package com.nela.dosgaracciolo.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nela.dosgaracciolo.models.Usuario;
import com.nela.dosgaracciolo.request.ApiClient;
import com.nela.dosgaracciolo.ui.registro.RegistroActivity;


public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }

    public void acceder(String usuario, String clave){
        if(usuario.isEmpty()){
            Toast.makeText(context, "Ingrese el usuario", Toast.LENGTH_SHORT).show();
            return;
        }else if(clave.isEmpty()){
            Toast.makeText(context, "Ingrese la clave", Toast.LENGTH_SHORT).show();
            return;
        }
        Usuario usu = ApiClient.login(context,usuario,clave);
        if(usu!=null){
            Intent intent = new Intent(context, RegistroActivity.class);
            intent.putExtra("login",true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
    public void registro(){
        Intent intent = new Intent(context,RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

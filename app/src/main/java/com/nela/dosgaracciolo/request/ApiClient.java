package com.nela.dosgaracciolo.request;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.nela.dosgaracciolo.models.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;




public class ApiClient {


    private static File file;

    private static File conectar(Context context) {
        if (file == null) {
            Log.d("ApiProblem", "Conectando con Usuario.obj");
            file = new File(context.getFilesDir(), "Usuario.obj");
        }
        Log.d("ApiProblem", "Archivo conectado correctamente: " + file.getAbsolutePath());
        return file;
    }

    public static boolean guardar(Context context, Usuario usuario) {
        Log.d("ApiProblem", "Entrando a guardar");
        File file = conectar(context);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            Log.d("ApiProblem", "Escribiendo el Usuario ");
            oos.writeObject(usuario);
            bos.flush();
            oos.close();
            Log.d("ApiProblem", "Usuario guardado correctamente");
            return true;
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "No se encontró el archivo", Toast.LENGTH_SHORT).show();
            return false;
        } catch (IOException e) {
            Toast.makeText(context, "Error de entrada/salida", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static Usuario leerDatos(Context context) {
        Log.d("ApiProblem", "leyending");
        Usuario usuario = null;
        File file = conectar(context);
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Log.d("ApiProblem", "leyending usuario");
            usuario = (Usuario) ois.readObject();
            ois.close();
            Log.d("ApiProblem", "Usuario leyido");
        } catch (FileNotFoundException ex) {
            Toast.makeText(context, "No se encontró el archivo", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(context, "Error de entrada/salida", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "Error al obtener el usuario", Toast.LENGTH_SHORT).show();
        }
        return usuario;
    }

    public static Usuario login(Context context, String email, String contrasena) {
        Log.d("ApiProblem", "login");
        Usuario usuario = leerDatos(context);
        if (usuario != null) {
            Log.d("ApiProblem", "Usuario encontrado verificando ");
            if (usuario.getEmail().equals(email) && usuario.getClave().equals(contrasena)) {
                Log.d("ApiProblem", "Login exitoso");
                return usuario;
            } else {
                Log.d("ApiProblem", "usuario incorrext");
            }
        } else {
            Log.d("ApiProblem", "nosencontro");
        }
        return null;
    }
}


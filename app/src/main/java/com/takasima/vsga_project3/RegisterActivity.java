package com.takasima.vsga_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText inputUsername, inputPassword, inputEmail, inputNama, inputAsal, inputAlamat;
    Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setTitle("Register");
        inputUsername = findViewById(R.id.et_usernameR);
        inputPassword = findViewById(R.id.et_passwordR);
        inputEmail = findViewById(R.id.et_emailR);
        inputNama = findViewById(R.id.et_namaR);
        inputAsal = findViewById(R.id.et_asalSekolahR);
        inputAlamat = findViewById(R.id.et_alamatR);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()){
                    simpanFileData();
                }else{
                    Toast.makeText(RegisterActivity.this, "Mohon Lenkapi Seluruh Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isValid(){
        if (inputUsername.getText().toString().equals("")||
                inputPassword.getText().toString().equals("")||
                inputEmail.getText().toString().equals("")||
                inputNama.getText().toString().equals("")||
                inputAsal.getText().toString().equals("")||
                inputAlamat.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }
    }

    void simpanFileData(){
        String isiFile = inputUsername.getText().toString() + ";"
                + inputEmail.getText().toString() + ";"
                + inputNama.getText().toString() + ";"
                + inputAsal.getText().toString() + ";"
                + inputAlamat.getText().toString();

        File file = new File(getFilesDir(), inputUsername.getText().toString());
        FileOutputStream outputStream = null;


        try {
            file.createNewFile(); //Buat file baru
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}
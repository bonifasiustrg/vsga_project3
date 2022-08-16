package com.takasima.vsga_project3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "login";

    EditText editUserName, editPassword, editEmail, editNama, editAsalSekolah, editAlamat;
    TextView tvUserName, tvPassword, tvEmail, tvNama, tvAsal, tvAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);//mengambil layout register

        //set judul halaman
        this.setTitle("Halaman Depan");

        //kenali komponen-komponen dari halaman register
        editUserName = findViewById(R.id.et_usernameR);
        editPassword = findViewById(R.id.et_passwordR);
        editEmail = findViewById(R.id.et_emailR);
        editNama = findViewById(R.id.et_namaR);
        editAsalSekolah = findViewById(R.id.et_asalSekolahR);
        editAlamat = findViewById(R.id.et_alamatR);

        tvUserName = findViewById(R.id.tv_usernameR);
        tvPassword = findViewById(R.id.tv_passwordR);
        tvEmail = findViewById(R.id.tv_emailR);
        tvNama = findViewById(R.id.tv_namaR);
        tvAsal = findViewById(R.id.tv_asalSekolahR);
        tvAlamat = findViewById(R.id.tv_alamatR);

        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setVisibility(View.GONE);

        editUserName.setEnabled(false);
        editPassword.setVisibility(View.GONE);
        tvPassword.setVisibility(View.GONE);
        editEmail.setEnabled(false);
        editNama.setEnabled(false);
        editAsalSekolah.setEnabled(false);
        editAlamat.setEnabled(false);

        //ambil file login yang sudah dibuat dari halaman login
        bacaFileLogin();
    }

    void bacaFileLogin(){
        File file = new File(getFilesDir(), FILENAME);

        if(file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }

            String data = text.toString();
            String[] dataUser = data.split(";");

            bacaDataUser(dataUser[0]);
        }
    }

    //berdasarkan file login, ambil data dari file registrasi
    void bacaDataUser(String namaFile){
        File file = new File(getFilesDir(), namaFile);

        if(file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }

            String data = text.toString();
            String[] dataUser = data.split(";");

            //Set data berdasarkan file
            editUserName.setText(dataUser[0]);
            editEmail.setText(dataUser[2]);
            editNama.setText(dataUser[3]);
            editAsalSekolah.setText(dataUser[4]);
            editAlamat.setText(dataUser[5]);
        }else{
            Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }
//
//    //panggil menu logout
//    //sebelumnya buat terlebih dahulu resource menu pada folder res
//    //dengan memilih new android resource file
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    //action ketika memilih menu logout
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menu_logout:
//                //pastikan user memang ingin keluar
//                tampilkanDialogKonfirmasiLogout();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    //fungsi hapus file
    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()){
            file.delete();
        }
    }

//    //fungsi untuk menampilkan kotak dialog logout
//    void tampilkanDialogKonfirmasiLogout(){
//        new AlertDialog.Builder(this)
//                .setTitle("Logout")
//                .setMessage("Apakah Anda yakin akan logout?")
//                .setIcon(R.drawable.ic_baseline_warning_24)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        hapusFile();
//                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .setNegativeButton(android.R.string.no, null)
//                .show();
//    }


}
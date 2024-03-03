package com.example.aplikasikasir;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgBarang;
    EditText etTotal;
    RadioButton rbMember;
    Button btnProcess, btnClear;
    TextView tvNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgBarang = findViewById(R.id.rgBarang);
        etTotal = findViewById(R.id.etTotal);
        rbMember = findViewById(R.id.rbMember);
        btnProcess = findViewById(R.id.btnResult);
        tvNota = findViewById(R.id.tvNota);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processTransaction();
            }
        });

    }


    private void processTransaction() {
        int selectedItemId = rgBarang.getCheckedRadioButtonId();
        if (selectedItemId == -1) {
            Toast.makeText(this, "Pilih barang terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedItem = findViewById(selectedItemId);
        String itemName = selectedItem.getText().toString();
        double itemPrice;

        try {
            itemPrice = Double.parseDouble(etTotal.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Masukkan jumlah barang yang valid", Toast.LENGTH_SHORT).show();
            return;
        }

        double hargabarang = 0;
        double adminFee = 0;

        switch (itemName) {
            case "Nike":
                hargabarang = 65000;
                adminFee = 2000;
                break;
            case "Adidas":
                hargabarang = 75000;
                adminFee = 2500;
                break;
            case "Puma":
                hargabarang = 85000;
                adminFee = 3000;
                break;
            // tambahkan case untuk barang lain jika diperlukan
        }

        double totalbarang = hargabarang * itemPrice;
        double totalAmount = totalbarang + adminFee;
        double discountPercentage = 0.10; // 10% discount for members
        double discountAmount = rbMember.isChecked() ? totalAmount * discountPercentage : 0;

        double finalAmount = totalAmount - discountAmount;

        String nota =
                "Nama Pengisian saldo: " + itemName + "\n" +
                        "Total Harga Barang: " + itemPrice + "\n" +
                        "Biaya Admin: " + adminFee + "\n" +
                        "Persentase Diskon: " + (discountPercentage * 100) + "%" + "\n" +
                        "Potongan Harga dari Diskon: " + discountAmount + "\n" +
                        "Total Bayar: " + finalAmount;

        tvNota.setText(nota);
        tvNota.setVisibility(View.VISIBLE);
    }
}
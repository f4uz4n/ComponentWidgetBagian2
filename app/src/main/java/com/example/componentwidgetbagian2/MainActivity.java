package com.example.componentwidgetbagian2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // deklarasi komponen
    String resultText;
    RadioGroup rgKetoprak;
    RadioButton rbKetoprak;
    Spinner spJalan;
    Button btnSubmit;
    TextView txtResult;
    TimePicker tpTidur;
    DatePicker dpTimeFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //inisiasi
        btnSubmit = findViewById(R.id.btn_submit);
        txtResult = findViewById(R.id.txt_result);
        rgKetoprak = findViewById(R.id.rg_ketoprak);
        spJalan = findViewById(R.id.sp_jalan);
        tpTidur = findViewById(R.id.tp_tidur);
        dpTimeFavorite = findViewById(R.id.dp_time_favorite);

        // set time picker to 24 hours
        tpTidur.setIs24HourView(true);

        // siapin pilihan
        String[] pilihanJalan = new String[]{"Suka banget", "Lumayan", "Gak punya duit"};
        // set pilihan
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pilihanJalan);
        // set layout
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set adapter ke spinner
        spJalan.setAdapter(spinnerAdapter);

        // event click button submit
        btnSubmit.setOnClickListener(view -> {
            this.getResult();
        });
    }

    private void getResult()
    {
        resultText = "";
        txtResult.setText(resultText);

        // get radiobutton yang diseleksi dari radio group
        int checkedIdRadioGroup = rgKetoprak.getCheckedRadioButtonId();
        rbKetoprak = findViewById(checkedIdRadioGroup);
        resultText += "\nSuka Ketoprak : " + rbKetoprak.getText();

        // get text dari pilihan spinner
        resultText += "\nSuka Jalan jalan? : " + spJalan.getSelectedItem().toString();

        // get jam dan menit dari timepicker
        int jam = tpTidur.getCurrentHour();
        int menit = tpTidur.getCurrentMinute();
        resultText += "\nJam Tidur : " + jam + ":" + menit;

        // get date picker value
        int day = dpTimeFavorite.getDayOfMonth();
        int month = dpTimeFavorite.getMonth() + 1;
        int year = dpTimeFavorite.getYear();
        resultText += "\nTanggal Favorit : " + day + "-" + month + "-" + year;

        // tampilkan result nya
        txtResult.setText(resultText);
    }
}
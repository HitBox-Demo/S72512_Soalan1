package com.example.s72512_soalan1;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText etBillAmount;
    RadioGroup rgTip;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBillAmount = findViewById(R.id.etBillAmount);
        rgTip = findViewById(R.id.rgTip);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTip();
            }
        });
    }

        private void calculateTip() {
            String billInput = etBillAmount.getText().toString();
            if (billInput.isEmpty()) {
                Toast.makeText(this,"Please enter Valid Amount", Toast.LENGTH_SHORT).show();
                return;
            }
            double billAmount;

            try{
                billAmount = Double.parseDouble(billInput);
            } catch (NumberFormatException e ) {
                Toast.makeText(this,"Invalid Amount", Toast.LENGTH_SHORT).show();
                return;
            }
            if (billAmount <=0){
                Toast.makeText(this,"Bill must above/ Greater than 0", Toast.LENGTH_SHORT).show();
                return;
            }
            int selectedId = rgTip.getCheckedRadioButtonId();
            // double tipPercentage =0.0;
            if (selectedId <=0){
                Toast.makeText(this,"Please Select the 'tip' option", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selectedRadio = findViewById(selectedId);
            String tipText = selectedRadio.getText().toString();
            int tipPercentage = Integer.parseInt(tipText.replace("%",""));
            double tipAmount = billAmount * tipPercentage/100;
            DecimalFormat df = new DecimalFormat("0.00");
            tvResult.setText("Tip Amount: RM" + df.format(tipAmount));
        }

    }

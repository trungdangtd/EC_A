package edu.huflit.ecapp1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.huflit.ecapp1.R;

public class AddressActivity extends AppCompatActivity {

    Button addAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        addAddress = findViewById(R.id.add_address_btn);

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
            }
        });
    }
}
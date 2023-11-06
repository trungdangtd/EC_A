package edu.huflit.ecapp1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import edu.huflit.ecapp1.R;

public class AddAddressActivity extends AppCompatActivity {

    EditText name,address,city,postalCode,phoneNumber;
    Toolbar toolbar;

    Button addAddressBtn;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        toolbar = findViewById(R.id.add_address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        name = findViewById(R.id.ad_name);
        address = findViewById(R.id.ad_address);
        city = findViewById(R.id.ad_city);
        postalCode = findViewById(R.id.ad_code);
        phoneNumber = findViewById(R.id.ad_phone);
        addAddressBtn = findViewById(R.id.ad_add_address);

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  userName = name.getText().toString();
                String  userCity = city.getText().toString();
                String  userAddress = address.getText().toString();
                String  userCode = postalCode.getText().toString();
                String  userNumber = phoneNumber.getText().toString();

                String final_address = " ";
                if(!userName.isEmpty()){
                    final_address+=userName;
                }
                if(userName.isEmpty()){
                    name.setError("khong duoc de trong ten dia chi");
                }
                if(!userCity.isEmpty()){
                    final_address+=userCity;
                }
                if(userCity.isEmpty()){
                    city.setError("khong duoc de trong thanh pho");
                }
                if(!userAddress.isEmpty()){
                    final_address+=userAddress;
                }
                if(userAddress.isEmpty()){
                    address.setError("khong duoc de trong dia chi");
                }
                if(!userCode.isEmpty()){
                    final_address+=userCode;
                }
                if(userCode.isEmpty()){
                    postalCode.setError("khong duoc de trong ma buu dien");
                }
                if(!userNumber.isEmpty()){
                    final_address+=userNumber;
                }
                if(userNumber.isEmpty()){
                    phoneNumber.setError("khong duoc de trong so dien thoai");
                }
                if(!userName.isEmpty() && !userCity.isEmpty() && !userAddress.isEmpty() && !userCode.isEmpty() && !userNumber.isEmpty()){

                    Map<String,String> map =  new HashMap<>();
                    map.put("userAddress",final_address);

                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    Toast.makeText(AddAddressActivity.this, "đã thêm địa chỉ", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AddAddressActivity.this,DetailedActivity.class));
                                    finish();
                                }
                            });
                }
                if(userName.isEmpty() && userCity.isEmpty() && userAddress.isEmpty() && userCode.isEmpty() && userNumber.isEmpty()){
                    Toast.makeText(AddAddressActivity.this, "vui long nhap day du du lieu", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddAddressActivity.this, "đầy", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
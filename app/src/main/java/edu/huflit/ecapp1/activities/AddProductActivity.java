package edu.huflit.ecapp1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import edu.huflit.ecapp1.R;

public class AddProductActivity extends AppCompatActivity {

    EditText edDes, edImg,edName,edPrice,edRat,edType;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        edDes = (EditText) findViewById(R.id.edtDes);
        edImg = (EditText) findViewById(R.id.edtURL);
        edName = (EditText) findViewById(R.id.edtName);
        edPrice = (EditText) findViewById(R.id.edtPrice);
        edRat = (EditText) findViewById(R.id.edtRat);
        edType = (EditText) findViewById(R.id.edtType);

        btnAdd = (Button) findViewById(R.id.btnAddProducts);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edDes.getText().toString())) {
                    edDes.setError("Không được để trống description");
                    return;
                }
                if(TextUtils.isEmpty(edImg.getText().toString())) {
                    edImg.setError("Không được để trống ảnh sản phẩm");
                    return;
                }
                if(TextUtils.isEmpty(edName.getText().toString())) {
                    edName.setError("Không được để trống tên sản phẩm");
                    return;
                }
                if(TextUtils.isEmpty(edPrice.getText().toString())) {
                    edPrice.setError("Không được để trống giá");
                    return;
                }
                if(TextUtils.isEmpty(edRat.getText().toString())) {
                    edRat.setError("Không được để trống đánh giá");
                    return;
                }
                if(TextUtils.isEmpty(edType.getText().toString())) {
                    edType.setError("Không được để trống Type");
                    return;
                }else
                    Toast.makeText(AddProductActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                final HashMap<String,Object> ShowAllMap = new HashMap<>();

                ShowAllMap.put("description",edDes.getText().toString());
                ShowAllMap.put("img_url",edImg.getText().toString());
                ShowAllMap.put("name",edName.getText().toString());
                ShowAllMap.put("price",Integer.parseInt(edPrice.getText().toString()));
                ShowAllMap.put("rating",edRat.getText().toString());
                ShowAllMap.put("type",edType.getText().toString());

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                // Tham chiếu đến tài liệu hoặc collection bạn muốn thêm dữ liệu vào
                DocumentReference documentReference = db.collection("ShowAll").document();

                // Thêm dữ liệu từ HashMap vào Firestore
                documentReference.set(ShowAllMap);
            }
        });
    }
}
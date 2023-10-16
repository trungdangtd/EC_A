package edu.huflit.ecapp1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import edu.huflit.ecapp1.R;
import edu.huflit.ecapp1.models.ShowAllModel;

public class AddProductActivity extends AppCompatActivity {

    EditText edDes, edImg,edName,edPrice,edRat,edType,edID;
    Button btnAdd;
    @SuppressLint("MissingInflatedId")
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
        edID = (EditText) findViewById(R.id.edtID);
        btnAdd = (Button) findViewById(R.id.btnAddProducts);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edID.getText().toString())) {
                    edID.setError("Không được để trống description");
                    return;
                }
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
                }else{
                    Add(new ShowAllModel(edID.getText().toString(),
                        edDes.getText().toString(),
                        edImg.getText().toString(),
                        edName.getText().toString(),
                        Integer.parseInt(edPrice.getText().toString()),
                        edRat.getText().toString(),
                        edType.getText().toString()));
                    Toast.makeText(AddProductActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    public static void Add(@NonNull ShowAllModel showAllModel)
    {
        final HashMap<String,Object> ShowAllMap = new HashMap<>();

        ShowAllMap.put("Id",showAllModel.getId());
        ShowAllMap.put("description",showAllModel.getDescription());
        ShowAllMap.put("img_url",showAllModel.getImg_url());
        ShowAllMap.put("name",showAllModel.getName());
        ShowAllMap.put("price",showAllModel.getPrice());
        ShowAllMap.put("rating",showAllModel.getRating());
        ShowAllMap.put("type",showAllModel.getType());


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Tham chiếu đến tài liệu hoặc collection bạn muốn thêm dữ liệu vào
        DocumentReference documentReference = db.collection("ShowAll").document(showAllModel.getId());

        // Thêm dữ liệu từ HashMap vào Firestore
        documentReference.set(ShowAllMap);
    }
}
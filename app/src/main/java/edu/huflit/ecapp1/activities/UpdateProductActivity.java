package edu.huflit.ecapp1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import edu.huflit.ecapp1.models.ShowAllModel;

public class UpdateProductActivity extends AppCompatActivity {
    EditText edDes, edImg,edName,edPrice,edRat,edType;
    Button btnUpdate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        edDes = (EditText) findViewById(R.id.edtUp_Des);
        edImg = (EditText) findViewById(R.id.edtUp_URL);
        edName = (EditText) findViewById(R.id.edtUp_Name);
        edPrice = (EditText) findViewById(R.id.edtUp_Price);
        edRat = (EditText) findViewById(R.id.edtUp_Rat);
        edType = (EditText) findViewById(R.id.edtUp_Type);

        Intent intent = getIntent();
        String documentId = intent.getStringExtra("document_id");

        btnUpdate = (Button) findViewById(R.id.btnUpProducts);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
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
                }else{
                    Update(new ShowAllModel(documentId, //đang làm ở dây
                            edDes.getText().toString(),
                            edImg.getText().toString(),
                            edName.getText().toString(),
                            Integer.parseInt(edPrice.getText().toString()),
                            edRat.getText().toString(),
                            edType.getText().toString()));
                    Toast.makeText(UpdateProductActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void Update(@NonNull ShowAllModel showAllModel)
    {
        final HashMap<String,Object> UpdateMap = new HashMap<>();

        UpdateMap.put("description",showAllModel.getDescription());
        UpdateMap.put("img_url",showAllModel.getImg_url());
        UpdateMap.put("name",showAllModel.getName());
        UpdateMap.put("price",showAllModel.getPrice());
        UpdateMap.put("rating",showAllModel.getRating());
        UpdateMap.put("type",showAllModel.getType());


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Tham chiếu đến tài liệu hoặc collection bạn muốn thêm dữ liệu vào
        DocumentReference documentReference = db.collection("ShowAll").document(showAllModel.getId());

        // Thêm dữ liệu từ HashMap vào Firestore
        documentReference.update(UpdateMap);
    }
}
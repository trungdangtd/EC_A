package edu.huflit.ecapp1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.ecapp1.R;
import edu.huflit.ecapp1.adapters.MyCartAdapter;
import edu.huflit.ecapp1.models.MyCartModel;
import edu.huflit.ecapp1.models.NewProductsModel;
import edu.huflit.ecapp1.models.PopularProductsModel;
import edu.huflit.ecapp1.models.ShowAllModel;

public class CartActivity extends AppCompatActivity {

    int overAllTotalAmount;

    TextView overAllAmount;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<MyCartModel> cartModelList;
    ImageView detailedImg;
    TextView rating,name,description,price,quantity;
    MyCartAdapter cartAdapter;
    Button buynow;
    int totalQuantity = 1;
    int totalPrice = 0;
    //New Products
    NewProductsModel newProductsModel = null;

    //popular product
    PopularProductsModel popularProductsModel = null;

    //Show all
    ShowAllModel showAllModel = null;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.my_cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //get data from cart adapter
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));

        overAllAmount = findViewById(R.id.textViewOver);
        recyclerView = findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(this,cartModelList);
        recyclerView.setAdapter(cartAdapter);
        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof NewProductsModel) {

            newProductsModel = (NewProductsModel) obj;
        }else if(obj instanceof PopularProductsModel){
            popularProductsModel = (PopularProductsModel) obj;
        }
        else if(obj instanceof ShowAllModel){
            showAllModel = (ShowAllModel) obj;
        }


        detailedImg = findViewById(R.id.detailed_img);
        quantity = findViewById(R.id.quantity);
        name = findViewById(R.id.detailed_name);
        rating = findViewById(R.id.rating);
        description = findViewById(R.id.detailed_desc);
        price = findViewById(R.id.detailed_price);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid()).collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        MyCartModel myCartModel = doc.toObject(MyCartModel.class);
                        cartModelList.add(myCartModel);
                        cartAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        //New Products
        if (newProductsModel !=null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg);
            name.setText(newProductsModel.getName());
            rating.setText(newProductsModel.getRating());
            description.setText(newProductsModel.getDescription());
            price.setText(String.valueOf(newProductsModel.getPrice()));
            name.setText(newProductsModel.getName());

            totalPrice = newProductsModel.getPrice() * totalQuantity;
        }
        //Popular Products
        if (popularProductsModel !=null){
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg);
            name.setText(popularProductsModel.getName());
            rating.setText(popularProductsModel.getRating());
            description.setText(popularProductsModel.getDescription());
            price.setText(String.valueOf(popularProductsModel.getPrice()));
            name.setText(popularProductsModel.getName());

            totalPrice = popularProductsModel.getPrice() * totalQuantity;
        }
        //show all Products
        if (showAllModel !=null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));
            name.setText(showAllModel.getName());

            totalPrice = showAllModel.getPrice() * totalQuantity;
        }
        buynow=findViewById(R.id.buy_now1);
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this,AddressActivity.class);

                if(newProductsModel!=null){
                    intent.putExtra("item",newProductsModel);
                }
                if (popularProductsModel!=null){
                    intent.putExtra("item",popularProductsModel);
                }
                if (showAllModel !=null){
                    intent.putExtra("item",showAllModel);
                }
                startActivity(intent);
            }

        });
    }

    public BroadcastReceiver mMessageReceiver =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = intent.getIntExtra("totalAmount",0);
            overAllAmount.setText("Total Amount :" + totalBill + "$");
        }
    };

}
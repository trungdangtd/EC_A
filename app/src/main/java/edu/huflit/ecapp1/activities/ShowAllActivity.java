package edu.huflit.ecapp1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.huflit.ecapp1.R;
import edu.huflit.ecapp1.adapters.ShowAllAdapter;
import edu.huflit.ecapp1.models.ShowAllModel;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelList;
    Toolbar toolbar;
    FirebaseFirestore firestore;
    EditText searchEditText;
    Button searchButton;

    ImageView sortBtns;
    TextView sortTitle;
    EditText minPriceEditText,maxPriceEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        minPriceEditText = findViewById(R.id.min_price_edittext);
        maxPriceEditText = findViewById(R.id.max_price_edittext);
        searchEditText = findViewById(R.id.search_edittext);
        searchButton = findViewById(R.id.search_button);

        toolbar = findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sortBtns = findViewById(R.id.sortBtn);
        sortTitle = findViewById(R.id.sortTitle);

        String type = getIntent().getStringExtra("type");

        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this,showAllModelList);
        recyclerView.setAdapter(showAllAdapter);


        sortTitle.setText("Sắp xếp");
        if(type == null || type.isEmpty()){
            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot doc :task.getResult().getDocuments()){
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }

        if(type != null && type.equalsIgnoreCase("men")){
            firestore.collection("ShowAll").whereEqualTo("type","men")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot doc :task.getResult().getDocuments()){
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if(type != null && type.equalsIgnoreCase("watch")) {
            firestore.collection("ShowAll").whereEqualTo("type", "watch")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
            if(type != null && type.equalsIgnoreCase("woman")) {
                firestore.collection("ShowAll").whereEqualTo("type", "woman")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                        ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                        showAllModelList.add(showAllModel);
                                        showAllAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        });
        }
        if(type != null && type.equalsIgnoreCase("camera")) {
            firestore.collection("ShowAll").whereEqualTo("type", "camera")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if(type != null && type.equalsIgnoreCase("kids")) {
            firestore.collection("ShowAll").whereEqualTo("type", "kids")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if(type != null && type.equalsIgnoreCase("shoes")) {
            firestore.collection("ShowAll").whereEqualTo("type", "shoes")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(minPriceEditText.getText().toString()) && !TextUtils.isEmpty(maxPriceEditText.getText().toString())){
                    double minPrice = Double.parseDouble(minPriceEditText.getText().toString());
                    double maxPrice = Double.parseDouble(maxPriceEditText.getText().toString());
                    filterDataByPriceRange( minPrice,  maxPrice);
                }
                else {
                String query = searchEditText.getText().toString();
                filterData(query);
            }

        }
        });

        sortBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SortBtn(view);
            }
        });
    }
    private void filterData(String query) {
        List<ShowAllModel> filteredList = new ArrayList<>();
        for (ShowAllModel model : showAllModelList) {
            if (model.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(model);
            }
        }
        showAllAdapter.setFilter(filteredList);
    }
    private void filterDataByPriceRange(double minPrice, double maxPrice) {
        List<ShowAllModel> filteredList = new ArrayList<>();
        for (ShowAllModel model : showAllModelList) {
            if (model.getPrice() >= minPrice && model.getPrice() <= maxPrice) {
                filteredList.add(model);
            }
        }
        showAllAdapter.setFilter(filteredList);
    }

    void SortBtn(View view){
// Create a new PopupWindow instance
        PopupWindow popup = new PopupWindow(view.getContext().getApplicationContext());

        // Inflate your custom layout
        LayoutInflater inflater = (LayoutInflater) view.getContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.sort_option, null);

        RadioButton sortNameAscBtn = layout.findViewById(R.id.TenAsc);
        RadioButton sortNameDescBtn = layout.findViewById(R.id.TenDesc);
        RadioButton sortGiaTCBtn = layout.findViewById(R.id.giaThapCao);
        RadioButton sortGiaCTBtn = layout.findViewById(R.id.giaCaoThap);
        Button closeBtn = layout.findViewById(R.id.closeBtn);

        sortNameAscBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortDataByNameAsc();
                sortTitle.setText("Tên tăng dần");
                popup.dismiss();
            }
        });
        sortNameDescBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortDataByNameDesc();
                sortTitle.setText("Tên giảm dần");
                popup.dismiss();
            }
        });
        sortGiaTCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortDataByPriceTC();
                sortTitle.setText("Giá tăng dần");
                popup.dismiss();
            }
        });

        sortGiaCTBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortDataByPriceCT();
                sortTitle.setText("Giá giảm dần");
                popup.dismiss();
            }
        });

        //đóng cửa sổ popup
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        // Set the custom layout as the content view for the popup window
        popup.setContentView(layout);

        // Set the width and height of the popup window
        popup.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // Show the popup window
        popup.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
    private void sortDataByNameAsc() {
        Collections.sort(showAllModelList, new Comparator<ShowAllModel>() {
            @Override
            public int compare(ShowAllModel model1, ShowAllModel model2) {
                return model1.getName().compareToIgnoreCase(model2.getName());
            }
        });
        showAllAdapter.notifyDataSetChanged();
    }
    private void sortDataByNameDesc() {
        Collections.sort(showAllModelList, new Comparator<ShowAllModel>() {
            @Override
            public int compare(ShowAllModel model1, ShowAllModel model2) {
                return model2.getName().compareToIgnoreCase(model1.getName());
            }
        });
        showAllAdapter.notifyDataSetChanged();
    }
    private void sortDataByPriceTC() {
        Collections.sort(showAllModelList, new Comparator<ShowAllModel>() {
            @Override
            public int compare(ShowAllModel model1, ShowAllModel model2) {
                // Sắp xếp theo giá tiền tăng dần
                return Double.compare(model1.getPrice(), model2.getPrice());
            }
        });
        showAllAdapter.notifyDataSetChanged();
    }
    private void sortDataByPriceCT() {
        Collections.sort(showAllModelList, new Comparator<ShowAllModel>() {
            @Override
            public int compare(ShowAllModel model1, ShowAllModel model2) {
                // Sắp xếp theo giá tiền tăng dần
                return Double.compare(model2.getPrice(), model1.getPrice());
            }
        });
        showAllAdapter.notifyDataSetChanged();
    }
}
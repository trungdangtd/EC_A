package edu.huflit.ecapp1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        searchEditText = findViewById(R.id.search_edittext);
        searchButton = findViewById(R.id.search_button);
        toolbar = findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String type = getIntent().getStringExtra("type");

        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this,showAllModelList);
        recyclerView.setAdapter(showAllAdapter);


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
                String query = searchEditText.getText().toString();
                filterData(query);
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
}
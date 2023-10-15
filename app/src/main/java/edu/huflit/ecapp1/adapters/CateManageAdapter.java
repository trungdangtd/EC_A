package edu.huflit.ecapp1.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import edu.huflit.ecapp1.R;
import edu.huflit.ecapp1.activities.UpdateProductActivity;
import edu.huflit.ecapp1.models.ShowAllModel;

public class CateManageAdapter extends RecyclerView.Adapter<CateManageAdapter.ViewHolder>{

    Context context;

    List<ShowAllModel> list;

    FirebaseFirestore db;

    Fragment homeFragment;
    public CateManageAdapter(Context context, List<ShowAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CateManageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.catemanage_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CateManageAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.mItemImage);

        holder.mName.setText(list.get(position).getName());

        db = FirebaseFirestore.getInstance();
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lấy ID của tài liệu hoặc thông tin cần cập nhật
                String documentId = list.get(position).getId();

                // Tạo một Intent để chuyển đến UpdateProductActivity
                Intent intent = new Intent(context, UpdateProductActivity.class);
                intent.putExtra("document_id", documentId); // Truyền ID hoặc dữ liệu cần cập nhật
                context.startActivity(intent);

            }
        });

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String documentId = list.get(position).getId();
                db.collection("ShowAll")
                        .document(documentId)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context, "xoá thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
                list.remove(list.get(position));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemImage;
        private TextView mName;

        private Button btnUpdate;
        private ImageView btnDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemImage = itemView.findViewById(R.id.cateImg);
            mName = itemView.findViewById(R.id.cateName);
            btnUpdate = itemView.findViewById(R.id.cateUpdate);
            btnDel = itemView.findViewById(R.id.cateDelete);
        }
    }


}

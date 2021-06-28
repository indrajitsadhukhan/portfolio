package com.example.collegeuser.ebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeuser.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder> {
    Context context;
    List<EbookData> list;

    public EbookAdapter(Context context, List<EbookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ebook_item_layout,parent,false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, final int position) {

        holder.ebookName.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PdfViewer.class);
                    intent.putExtra("pdfUrl",list.get(position).getUrl());
                    context.startActivity(intent);

            }
        });
        holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
               System.out.println("JIIJSIZE"+list.get(position).getName());

//                if(list.get(position).getName()==null)
                  //  System.out.println("JII");
                intent.setData(Uri.parse(list.get(position).getUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {

        TextView ebookName;
        ImageView ebookDownload;
        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);
            ebookDownload=itemView.findViewById(R.id.ebookDownload);
            ebookName=itemView.findViewById(R.id.ebookName);

        }
    }


}

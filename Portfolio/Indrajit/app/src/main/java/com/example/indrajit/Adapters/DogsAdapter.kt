package com.example.indrajit.Adapters
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.indrajit.Image
import com.example.indrajit.Model.DogsData
import com.example.indrajit.R

class DogsAdapter(private var dogsList: ArrayList<DogsData>) :
    RecyclerView.Adapter<DogsAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img: ImageView = view.findViewById(R.id.img)
        var mainrow: LinearLayout = view.findViewById(R.id.mainrow)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.dogs_row, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dog = dogsList[position]
        Glide.with(holder.itemView)
            .load(dog.imglink)
            .error(R.drawable.notfound)
            .into(holder.img);
        holder.mainrow.setOnClickListener{
            val intent = Intent(holder.itemView.context, Image::class.java)
            intent.putExtra("url",dog.imglink)
            holder.itemView.context.startActivity(intent)

        }

    }

    fun addData(listItems: ArrayList<DogsData>) {
        var size = this.dogsList.size
        for(i in 0 until listItems.size)
        {
            this.dogsList.add(listItems[i]);
        }
        var sizeNew = this.dogsList.size
        notifyItemRangeChanged(size, sizeNew)
    }

    override fun getItemCount(): Int {
        return dogsList.size
    }
}
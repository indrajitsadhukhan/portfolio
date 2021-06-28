package com.example.indrajit

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.indrajit.Adapters.DogsAdapter
import com.example.indrajit.Model.DogsData


class MainActivity : AppCompatActivity() {
lateinit var recycler: RecyclerView
    lateinit var imagelist: ArrayList<DogsData>
    lateinit var dogAdapter: DogsAdapter
    lateinit var button: Button
    var flag=false;
    lateinit var layout: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler=findViewById(R.id.recycler)
        button=findViewById(R.id.btn)
        imagelist= ArrayList();
        flag=false
        loadmoreimages()
        button.setOnClickListener{
            loadmoreimages()
        }
    }
     fun loadmoreimages()
    {
        val queue = Volley.newRequestQueue(this)
        var url = loadurl+ countimg.toString()
        val jsonrequest =    JsonObjectRequest(Request.Method.GET,url,null, {
                response ->
            run {
                var status = response.getString("status");
                if (status == "success") {
                    var jsonarray = response.getJSONArray("message");
imagelist= ArrayList()
                    for(i in 0 until jsonarray.length())
                    {
                        imagelist.add(DogsData(imglink = jsonarray[i].toString()));
                    }
                    if(flag==false)
                    {
                        dogAdapter= DogsAdapter(imagelist);
                        layout = LinearLayoutManager(this);
                        recycler.layoutManager = layout;
                        flag=true
                        recycler.adapter = dogAdapter

                    }
                    dogAdapter.addData(imagelist)
                    dogAdapter.notifyItemInserted(imagelist.size);
                    dogAdapter.notifyDataSetChanged()

                }
                else
                {
                    Toast.makeText(this,status,Toast.LENGTH_SHORT).show();
                }
            }
        }, {
            it.message?.let { it1 -> Log.e("Volley", it1) };
        })
        queue.add(jsonrequest)
    }
}
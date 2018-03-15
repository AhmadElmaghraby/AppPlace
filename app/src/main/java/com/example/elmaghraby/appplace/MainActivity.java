package com.example.elmaghraby.appplace;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.internal.th;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class MainActivity extends AppCompatActivity {
    ListView listView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView) findViewById(R.id.listview_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("a");
//
//        recyclerView=(RecyclerView) findViewById(R.id.rec_main);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        FirebaseRecyclerAdapter<String,ViewHolder> adapter=new FirebaseRecyclerAdapter<String, ViewHolder>(
//                String.class,
//                R.layout.simple_item,
//                ViewHolder.class,
//                myRef
//        ) {
//            @Override
//            protected void populateViewHolder(ViewHolder viewHolder, String model, int position) {
//                viewHolder.textView.setText(model);
//            }
//        };
//        recyclerView.setAdapter(adapter);
        final FirebaseListAdapter<String> adapter=new FirebaseListAdapter<String>(
                this,
                String .class,
                R.layout.simple_item,
                myRef) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView t=(TextView) v.findViewById(R.id.simple);
                t.setText(model);
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                String  name = adapter.getItem(position);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//
//        TextView textView;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            textView=(TextView)itemView.findViewById(R.id.simple);
//        }
//    }
    }
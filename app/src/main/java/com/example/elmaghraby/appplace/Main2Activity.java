package com.example.elmaghraby.appplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    String nameA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView=(ListView) findViewById(R.id.listview_main2);
        nameA=getIntent().getStringExtra("name");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child(nameA).child("a");
        final FirebaseListAdapter<String> adapter=new FirebaseListAdapter<String>(
                this, String .class, R.layout.simple_item, myRef) {
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
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                 String  name = adapter.getItem(position);
                intent.putExtra("nameb",name);
                intent.putExtra("namea",nameA);
                startActivity(intent);
            }
        });
    }
}

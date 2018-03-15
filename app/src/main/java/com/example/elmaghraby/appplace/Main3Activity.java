package com.example.elmaghraby.appplace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Main3Activity extends AppCompatActivity {
    ImageView imageView;
    String nameA;
    String nameB;
    Button btnNumber,btnAddres,btnComment;
    TextView txtDis,txtRate;
    RatingBar rt;
    EditText edComment;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        imageView=(ImageView)findViewById(R.id.img);
        txtRate=(TextView)findViewById(R.id.txt_rate);
        btnAddres=(Button) findViewById(R.id.btn_addres);
        btnNumber=(Button) findViewById(R.id.btn_number);
        txtDis=(TextView) findViewById(R.id.txt_description);
        rt=(RatingBar) findViewById(R.id.ratingBar);
        btnComment=(Button) findViewById(R.id.btn_comment);
        edComment=(EditText) findViewById(R.id.edit_txt_comment);
        recyclerView=(RecyclerView) findViewById(R.id.rec_comment);
        nameA=getIntent().getStringExtra("namea");
        nameB=getIntent().getStringExtra("nameb");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child(nameA).child(nameB);

        Picasso.with(this).load("https://i.imgur.com/tGbaZCY.jpg").into(imageView);

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edComment.getText().toString().equals(null)){
                    Toast.makeText(Main3Activity.this,"can not", Toast.LENGTH_LONG).show();}
                else {
                    myRef.child("comment").push().setValue(edComment.getText().toString());}
            }
        });


        rt.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                myRef.child("4").setValue((int)rating+"");

            }
        });

        myRef.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                btnAddres.setText(value);
             //   Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
             //   Log.w(TAG, "Failed to read value.", error.toException());
                btnAddres.setText("fail");

            }
        });

        myRef.child("2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                btnNumber.setText(value);
                //   Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //   Log.w(TAG, "Failed to read value.", error.toException());
                btnAddres.setText("fail");

            }
        });

        myRef.child("3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                txtDis.setText(value);
                //   Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //   Log.w(TAG, "Failed to read value.", error.toException());
                btnAddres.setText("fail");

            }
        });

        myRef.child("4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                txtRate.setText(value);
                //   Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //   Log.w(TAG, "Failed to read value.", error.toException());
                btnAddres.setText("fail");

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerAdapter<String,ViewHolder> adapter=new FirebaseRecyclerAdapter<String , ViewHolder>(
                String.class,
                R.layout.simple_item,
                ViewHolder.class,
                myRef.child("comment")
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, String model, int position) {
                viewHolder.textView.setText(model);
            }
        };
        recyclerView.setAdapter(adapter);
    }
        public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.simple);
        }
    }
}
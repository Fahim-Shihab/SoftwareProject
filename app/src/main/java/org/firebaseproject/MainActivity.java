package org.firebaseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Firebase mFire;
    private EditText NameField;
    private EditText AgeField;
    private ArrayList<String>Users = new ArrayList<>();
    private ArrayList<String>UsersAge = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addFire = findViewById(R.id.fire);
        mFire = new Firebase("https://myfirebasefirst-f6c0c.firebaseio.com/User");

        NameField = findViewById(R.id.Name);
        AgeField = findViewById(R.id.Age);

        addFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = NameField.getText().toString();
                String age = AgeField.getText().toString();

                Firebase mFireChild = mFire.child(name);
                mFireChild.setValue(age);
            }
        });

        ListView mUserView = findViewById(R.id.NameList);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Users);

        ListView mAgeView = findViewById(R.id.AgeList);
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,UsersAge);

        mUserView.setAdapter(arrayAdapter);
        mAgeView.setAdapter(arrayAdapter2);

        mFire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();

                Users.add(key);
                UsersAge.add(value);
                arrayAdapter.notifyDataSetChanged();
                arrayAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        ImageButton CallogF = findViewById(R.id.CallF);
        CallogF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CallLogFirebase.class);
                startActivity(intent);
            }
        });
        ImageButton MessageLogF = findViewById(R.id.MessageLogF);
        MessageLogF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MessageFirebase.class);
                startActivity(intent);
            }
        });

        ImageButton AppUsageF = findViewById(R.id.AppLogF);
        AppUsageF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AppUsageStatFire.class);
                startActivity(intent);
            }
        });

        startService(new Intent(this, MyService.class));

    }
}

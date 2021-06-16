package com.example.room;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    WaitDatabase waitDatabase;
    WaitDao waitDao;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    private EditText mNewGuestNameEditText;
    private EditText mNewPartySizeEditText;
    LiveData<List<Wait>>allWait;
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);

        myAdapter=new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        waitDatabase = Room.databaseBuilder(this, WaitDatabase.class, "Wait_database")
                .allowMainThreadQueries().build();
        waitDao = waitDatabase.getWaitDao();
        allWait=waitDao.getAllWait();

        myAdapter.notifyDataSetChanged();
        mNewGuestNameEditText = (EditText) this.findViewById(R.id.name);
        mNewPartySizeEditText = (EditText) this.findViewById(R.id.party);
        allWait.observe(this, waits -> {
            myAdapter.setAllWords(waits);
            myAdapter.notifyDataSetChanged();
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                 ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                //  上下拖移callback
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                 //左右滑動callback
                int pos=viewHolder.getAdapterPosition();

                myAdapter.removed(pos,myAdapter,waitDao);

            }
        }).attachToRecyclerView(recyclerView);
    }





    public void addTo(View view){
        if (mNewGuestNameEditText.getText().length() ==0 ||
                mNewPartySizeEditText.getText().length() == 0) {
            return;
        }
        String partySize = "1";
        try {
            //mNewPartyCountEditText inputType="number", so this should always work
            partySize = mNewPartySizeEditText.getText().toString();
        } catch (NumberFormatException ex) {
            Log.e(LOG_TAG, "Failed to parse party size text to number: " + ex.getMessage());
        }

        // Add guest info to mDb
        addNewGuest(mNewGuestNameEditText.getText().toString(), partySize);
        mNewPartySizeEditText.clearFocus();
        mNewGuestNameEditText.getText().clear();
        mNewPartySizeEditText.getText().clear();
    }
    private void addNewGuest(String name,String partySize){
        Wait wait = new Wait(name, partySize);
        waitDao.insertWait(wait);
    }


}



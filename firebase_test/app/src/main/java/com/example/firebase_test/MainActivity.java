package com.example.firebase_test;

                                          //this is BookList_Activity from the video

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById (R.id.recyclerview_books);
        new FirebaseDatabaseHelper ().readBooks (new FirebaseDatabaseHelper.DataStatus () {
            @Override
            public void DataIsLoaded(List<book> books, List<String> keys) {

                //loading bar
                findViewById (R.id.loading_books_pb).setVisibility (View.GONE);

                new RecyclerView_config ().setConfig (mRecyclerView, MainActivity.this ,books,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsDeletes() {

            }

            @Override
            public void DataIsUpdated() {

            }
        });

    }

                          //menu (add new book ) java :

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater ().inflate (R.menu.booklist_activity_menu,menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId ()){

            case R.id.new_book :
                startActivity (new Intent (this, NewBookActivity.class));
                return true;
        }
        return super.onOptionsItemSelected (item);
    }
}
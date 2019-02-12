package com.example.itemanimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnStartDragListener {

    private List<String> mList;
    private RecyclerView mRecyclerView;
    private Button btnAdd;
    private EditText mEditText;
    private Adapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.mRecyclerview);

        mAdapter = new Adapter(mList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();

        mRecyclerView.setItemAnimator(itemAnimator);

        ItemTouchHelper.Callback callback =
                new EditItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);


        mRecyclerView.setAdapter(mAdapter);

        btnAdd = findViewById(R.id.btnAdd);
        mEditText = findViewById(R.id.txtText);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            int id = v.getId();
            if (R.id.btnAdd == id) {
                if(mList.size() >= 3) {
                    mList.add(2,mEditText.getText().toString());
                    mAdapter.notifyItemInserted(2);
                }
                else {
                    mList.add(mEditText.getText().toString());
                    mAdapter.notifyItemInserted(mList.size());
                }
                mEditText.setText("");
            }
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}

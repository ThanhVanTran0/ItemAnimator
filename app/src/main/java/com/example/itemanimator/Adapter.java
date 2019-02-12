package com.example.itemanimator;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements ItemTouchHelperAdapter{

    private List<String> mList;
    private Context context;
    private final OnStartDragListener mDragStartListener;

    public Adapter(List<String> list, OnStartDragListener dragListener) {
        this.mList = list;
        this.mDragStartListener = dragListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.rowText.setText(mList.get(i));
        viewHolder.rowText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < mList.size() && toPosition < mList.size()) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mList, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
        }
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        //Remove item
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView rowText;
        public Button btnDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowText = itemView.findViewById(R.id.rowText);
            btnDel = itemView.findViewById(R.id.btnDel);
            btnDel.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.equals(btnDel)) {
                //Click nhanh 2 lan la no loi. Why?
                int position = getAdapterPosition();
                mList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mList.size());
            }
        }

    }
}

package com.example.itemanimator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;

public class TestAnimator extends SimpleItemAnimator {
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        return false;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1, int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public void runPendingAnimations() {

    }

    @Override
    public void endAnimation(@NonNull RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}

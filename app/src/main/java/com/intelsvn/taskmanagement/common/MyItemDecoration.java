package com.intelsvn.taskmanagement.common;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.intelsvn.taskmanagement.R;

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private final int decorationHeight;
    private Context context;

    public MyItemDecoration(Context context) {
        this.context = context;
        decorationHeight = context.getResources().getDimensionPixelSize(R.dimen.dp10);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent != null && view != null) {

            int itemPosition = parent.getChildAdapterPosition(view);
            int totalCount = parent.getAdapter().getItemCount();

            if (itemPosition >= 0 && itemPosition < totalCount - 1) {
                outRect.bottom = decorationHeight;
            }

        }

    }
}

package com.growingrecyclerviewtest;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AppBarLayout mAppBarLayout;
    private static final String TAG = MainActivity.class.getName();
    private LinearLayout mLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerItemAdapter());
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        mLl = (LinearLayout) findViewById(R.id.ll);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * Callback method to be invoked when RecyclerView's scroll state changes.
             *
             * @param recyclerView The RecyclerView whose scroll state has changed.
             * @param newState     The updated scroll state. One of {@link #SCROLL_STATE_IDLE},
             *                     {@link #SCROLL_STATE_DRAGGING} or {@link #SCROLL_STATE_SETTLING}.
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int visibleItemPosition = manager.findFirstCompletelyVisibleItemPosition();
                    if (visibleItemPosition == 0) {
                        mAppBarLayout.setExpanded(true, true);
                    }
                    
                }
            }

            /**
             * Callback method to be invoked when the RecyclerView has been scrolled. This will be
             * called after the scroll has completed.
             * <p>
             * This callback will also be called if visible item range changes after a layout
             * calculation. In that case, dx and dy will be 0.
             *
             * @param recyclerView The RecyclerView which scrolled.
             * @param dx           The amount of horizontal scroll.
             * @param dy           The amount of vertical scroll.
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!recyclerView.canScrollVertically(-1)) {
                    mLl.setVisibility(View.GONE);
                    Log.i(TAG, "onScrolled: onScrolledToTop();");
                } else if (!recyclerView.canScrollVertically(1)) {
                    Log.i(TAG, "onScrolled: onScrolledToBottom();");
                    mLl.setVisibility(View.VISIBLE);
                } else if (dy < 0) {
                    mLl.setVisibility(View.GONE);
                    Log.i(TAG, "onScrolled:    onScrolledUp();");
                } else if (dy > 0) {
                    mLl.setVisibility(View.GONE);
                    Log.i(TAG, "onScrolled:  onScrolledDown();");
                }
            }
        });
    }
}

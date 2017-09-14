package com.growingrecyclerviewtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by 明正 on 2017/9/14.
 */

public class ItemFragment extends Fragment {
    private static final  String TAG = ItemFragment.class.getName();
    private RecyclerView mRecyclerView;
    private LinearLayout mLl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= View.inflate(getContext(),R.layout.fragment_item,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new RecyclerItemAdapter());
        mLl = (LinearLayout) view.findViewById(R.id.ll);
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
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                    int visibleItemPosition = manager.findFirstCompletelyVisibleItemPosition();
//                    if (visibleItemPosition == 0) {
//                        mAppBarLayout.setExpanded(true, true);
//                    }
//
//                }

                if(newState ==RecyclerView.SCROLL_STATE_IDLE){
                    if (!recyclerView.canScrollVertically(-1)) {
                        mLl.setVisibility(View.GONE);
                        Log.i(TAG, "onScrolled: onScrolledToTop();");
                    } else if (!recyclerView.canScrollVertically(1)) {
                        Log.i(TAG, "onScrolled: onScrolledToBottom();");
                        mLl.setVisibility(View.VISIBLE);
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

//                if (!recyclerView.canScrollVertically(-1)) {
//                    mLl.setVisibility(View.GONE);
//                    Log.i(TAG, "onScrolled: onScrolledToTop();");
//                } else if (!recyclerView.canScrollVertically(1)) {
//                    Log.i(TAG, "onScrolled: onScrolledToBottom();");
//                    mLl.setVisibility(View.VISIBLE);
//                } else if (dy < 0) {
//                    mLl.setVisibility(View.GONE);
//                    Log.i(TAG, "onScrolled:    onScrolledUp();");
//                } else if (dy > 0) {
//                    mLl.setVisibility(View.GONE);
//                    Log.i(TAG, "onScrolled:  onScrolledDown();");
//                }
            }
        });



        if(view!=null){
            return view;
        }else{
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }
}

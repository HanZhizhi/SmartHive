package com.space.smarthive.info;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.space.smarthive.R;
import com.space.smarthive.data.Feed;
import com.space.smarthive.databinding.FragmentHivesBinding;
import com.space.smarthive.databinding.FragmentInfoBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment implements InfoContract.View{
    private static final String TAG = "InfoFragment";
    private FragmentInfoBinding viewBinding;
    private RecyclerView rvFeeds;
    private FeedsAdapter feedsAdapter;

    private Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private InfoContract.Presenter mPresenter;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    
        setPresenter(new InfoPresenter(this));

        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        viewBinding = FragmentInfoBinding.inflate(inflater, container, false);

        rvFeeds = viewBinding.fragInfoRecyclerView;
        rvFeeds.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        // rvFeeds.addItemDecoration(new RvDec());
        rvFeeds.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        feedsAdapter = new FeedsAdapter(rvFeeds);
        rvFeeds.setAdapter(feedsAdapter);

        return viewBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");

        mPresenter.start();
    }

    @Override
    public void updateRecyclerView(List<Feed> feeds) {
        feedsAdapter.setData(feeds);
    }

    @Override
    public void setPresenter(InfoContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
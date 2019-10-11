package com.iavariav.kbmonline.ui.atasan.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.iavariav.kbmonline.R;
import com.iavariav.kbmonline.ui.atasan.presenter.AprovalPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AprovalFragment extends Fragment {
    private RecyclerView rv;

    private AprovalPresenter aprovalPresenter;
    private LinearLayout div;


    public AprovalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aproval, container, false);
        initView(view);
        aprovalPresenter = new AprovalPresenter();
        aprovalPresenter.getDatas(getActivity(), rv, div);


        return view;
    }

    private void initView(View view) {
        rv = view.findViewById(R.id.rv);
        div = view.findViewById(R.id.div);
    }
}

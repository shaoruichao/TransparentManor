package com.fat.bigfarm.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fat.bigfarm.R;
import com.fat.bigfarm.base.BaseFragment;

/**
 * 我的农庄
 */
public class MyfarmFragment extends BaseFragment {


    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myfarm, container, false);

        return view;
    }


}

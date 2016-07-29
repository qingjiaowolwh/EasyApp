package com.edu.zum.easyapp.fragment;

import android.graphics.Color;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.widgets.WordView;

import butterknife.Bind;

public class WordFragment extends BaseFragment {
    @Bind(R.id.wordview)
    WordView mWordview;
    private final String DEMO_WORDS = "From tomorrow on, I will be a happy man;\n\nGrooming, chopping, and traveling all over the world.\n\nFrom tomorrow on, I will care foodstuff and vegetable.\n\nLiving in a house towards the sea, with spring blossoms.\n\nFrom tomorrow on, write to each of my dear ones,\n\nTelling them of my happiness.";
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_word;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        mWordview.setText(DEMO_WORDS);
        mWordview.setBackgroundColor(Color.WHITE);
        mWordview.setTextColor(Color.BLACK);
    }
}

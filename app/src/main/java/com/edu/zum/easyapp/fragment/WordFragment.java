package com.edu.zum.easyapp.fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.interactor.WordClickableSpan;

import java.text.BreakIterator;
import java.util.Locale;

import butterknife.Bind;

public class WordFragment extends BaseFragment {
    @Bind(R.id.wordview)
    TextView mWordview;
    private final String DEMO_WORDS = "From tomorrow on , I will be a happy man ; " +
            "Grooming , chopping , and traveling all over the world . " +
            "From tomorrow on , I will care foodstuff and vegetable . Living in a house towards the sea , " +
            "with spring blossoms . \n\nFrom tomorrow on , write to each of my dear ones , \n\nTelling them of my happiness . ";


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_word;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        WordClickableSpan();


    }
    public void WordClickableSpan(){
        mWordview.setMovementMethod(LinkMovementMethod.getInstance());
        mWordview.setTextSize(22);
        mWordview.setText(new SpannableString(DEMO_WORDS), TextView.BufferType.SPANNABLE);

        BreakIterator localBreakIterator = BreakIterator.getWordInstance(Locale.US);
        localBreakIterator.setText(DEMO_WORDS);
        int start = localBreakIterator.first();
        int end = localBreakIterator.next();
        String word = "";
        SpannableString sp = (SpannableString) mWordview.getText();

        while (localBreakIterator.next() != -1) {
            System.out.println("start:" + start + "end:" + end);
            word = DEMO_WORDS.substring(start, end);
            System.out.println("word:" + word);
            sp.setSpan(new WordClickableSpan(word, start, mContext.getResources().getColor(R.color.colorPrimaryDark)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end + 1;
            end = localBreakIterator.next();
        }
    }
}

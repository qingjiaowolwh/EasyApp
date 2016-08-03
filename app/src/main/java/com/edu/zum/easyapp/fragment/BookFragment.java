package com.edu.zum.easyapp.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.adapter.BaseRecyclerAdapter;
import com.edu.zum.easyapp.adapter.BookAdapter;
import com.edu.zum.easyapp.base.AnimatorUtil;
import com.edu.zum.easyapp.model.BookBean;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends XRecyclerViewFragment {
    View content;
    ImageView bookIv;
    ImageView bookMark;
    public int[] resId = {R.drawable.book, R.drawable.book, R.drawable.book, R.drawable.book,
            R.drawable.book, R.drawable.book, R.drawable.book, R.drawable.book};

    private float fromX, toX, fromY, toY;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        return new BookAdapter();
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new GridLayoutManager(mContext, 3);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        List<BookBean> list = new ArrayList<>();
        BookBean book = null;
        for (int i = 0; i < resId.length; i++) {
            book = new BookBean();
            book.setResId(resId[i]);
            list.add(book);
        }
        adapter.append(list);
//        bookIn = AnimatorInflater.loadAnimator(getContext(), R.animator.book_in);
//        bookOut = AnimatorInflater.loadAnimator(getContext(), R.animator.book_out);
        content = LayoutInflater.from(mContext).inflate(R.layout.layout_book_pop, null);
        content.setClickable(true);
        bookIv = (ImageView) content.findViewById(R.id.book_iv);
        bookMark = (ImageView) content.findViewById(R.id.book_mark);
        getMultiStateView().addView(content);
        content.setVisibility(View.INVISIBLE);

        adapter.setOnItemChildClickListener(new BaseRecyclerAdapter.OnItemChildClickListener<BookBean>() {
            @Override
            public void onItemChildClick(View view, int position, BookBean model) {
                showBook(model, position);
                AnimatorUtil.alfaDisappear(mRecyclerView).start();
            }
        });
    }

    public void showBook(BookBean model, int position) {
        content.setVisibility(View.VISIBLE);
        bookMark.setVisibility(View.INVISIBLE);

        bookIv.setImageResource(model.getResId());
        fromX = mRecyclerView.getChildAt(position + 1).getX() - bookIv.getX() + ((LinearLayout) mRecyclerView.getChildAt(position + 1)).getChildAt(0).getX();
        toX = 0;
        fromY = mRecyclerView.getChildAt(position + 1).getY() - bookIv.getY() + ((LinearLayout) mRecyclerView.getChildAt(position + 1)).getChildAt(0).getY();
        toY = 0;
        System.out.println("position:" + position);
        System.out.println("bookIv.getX():" + bookIv.getX() + "bookIv.getY():" + bookIv.getY());
        System.out.println("fromX:" + fromX + "toX:" + toX + "fromY:" + fromY + "toY:" + toY);

        TranslateAnimation translateAnimation = new TranslateAnimation(fromX, toX, fromY, toY);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setDuration(300);
        animationSet.setFillAfter(true);
        bookIv.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showMark();
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void hideBook() {
        bookMark.setVisibility(View.INVISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(toX, fromX, toY, fromY);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setDuration(300);
        bookIv.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                content.setVisibility(View.INVISIBLE);
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void showMark() {
        TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f);
        translate.setInterpolator(new AccelerateDecelerateInterpolator());
        translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                bookMark.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        translate.setDuration(200);
        bookMark.startAnimation(translate);

    }

    public void hideBooks() {
        if (content.getVisibility() != View.INVISIBLE) {
            hideBook();
            AnimatorUtil.alfaAppear(mRecyclerView).start();
        } else {
            getActivity().finish();
        }
    }


    // 返回键按下时会被调用
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            hideBooks();
            return true;
        }
        return false;
    }
}

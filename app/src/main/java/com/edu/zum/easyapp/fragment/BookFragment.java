package com.edu.zum.easyapp.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.adapter.BaseRecyclerAdapter;
import com.edu.zum.easyapp.adapter.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends XRecyclerViewFragment {
    RecyclerView.LayoutManager mLayoutManager;
    Animator bookIn, bookOut;
    ImageView bookIv;

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
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.ic_menu_overflow);
        list.add(R.drawable.ic_menu_white_24dp);
        list.add(R.drawable.ic_menu_overflow);
        list.add(R.drawable.ic_menu_white_24dp);
        list.add(R.drawable.ic_menu_overflow);
        list.add(R.drawable.ic_menu_white_24dp);
        list.add(R.drawable.ic_menu_overflow);
        list.add(R.drawable.ic_menu_white_24dp);

        adapter.append(list);
        bookIn = AnimatorInflater.loadAnimator(getContext(), R.animator.book_in);
        bookOut = AnimatorInflater.loadAnimator(getContext(), R.animator.book_out);
        View content = LayoutInflater.from(mContext).inflate(R.layout.layout_book_pop, null);
        getMultiStateView().addView(content);
        bookIv = (ImageView) content.findViewById(R.id.book_iv);

        adapter.setOnItemChildClickListener(new BaseRecyclerAdapter.OnItemChildClickListener<Integer>() {
            @Override
            public void onItemChildClick(View view, int position, Integer model) {
                System.out.println("position:" + position);
                System.out.println("view.getY():" + mRecyclerView.getChildAt(position + 1).getY() + "view.getX():" + mRecyclerView.getChildAt(position + 1).getX());
                System.out.println("ChildAt(2).getY():" + mRecyclerView.getChildAt(1 + 1).getY() + "ChildAt(2).getX():" + mRecyclerView.getChildAt(1 + 1).getX());

                bookIv.setImageResource(model);

//                ObjectAnimator translationtop = AnimatorUtil.translationBottom(bookIv, mRecyclerView.getChildAt(1 + 1).getY() - mRecyclerView.getChildAt(position + 1).getY());
//                ObjectAnimator translationright = AnimatorUtil.translationRight(bookIv, mRecyclerView.getChildAt(1 + 1).getX() - mRecyclerView.getChildAt(position + 1).getX());

//                ObjectAnimator translationtop = ObjectAnimator.ofFloat(bookIv, "translationY", mRecyclerView.getChildAt(position + 1).getY(), bookIv.getY());
//                ObjectAnimator translationright = ObjectAnimator.ofFloat(bookIv, "translationX", mRecyclerView.getChildAt(position + 1).getX(), bookIv.getX());
//                ObjectAnimator scalex = AnimatorUtil.scaleX(bookIv, 1.5f);
//                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.play(translationtop)
//                        .with(translationright);
//                animatorSet.play(scalex).with(translationright);
//                animatorSet.playTogether(scalex, AnimatorUtil.scaleY(bookIv, 1.5f));
//                animatorSet.start();

                TranslateAnimation translateAnimation = new TranslateAnimation(mRecyclerView.getChildAt(position + 1).getX() - mRecyclerView.getChildAt(position).getWidth(), bookIv.getY(), mRecyclerView.getChildAt(position + 1).getY(), bookIv.getY());
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(translateAnimation);
                animationSet.addAnimation(scaleAnimation);
                animationSet.setDuration(300);
                animationSet.setFillAfter(true);
                bookIv.startAnimation(animationSet);
            }
        });
    }

}

package com.edu.zum.easyapp.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.utils.AnimUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lwh on 2016/6/22.
 */
public class AnimatonFragment extends BaseFragment {
    @Bind(R.id.flower)
    ImageView flower;
    @Bind(R.id.flower1)
    ImageView flower1;
    @Bind(R.id.flower2)
    ImageView flower2;
    @Bind(R.id.bottom1)
    ImageView bottom1;
    @Bind(R.id.bottom2)
    ImageView bottom2;
    @Bind(R.id.bottom3)
    ImageView bottom3;
    @Bind(R.id.start)
    Button start;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_ainmation;
    }

    @Override
    protected void setUpView() {
        super.setUpView();

    }

    private void startBottomAnim() {

    }

    public void startMyAnim() {
        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.in_translate_top);
        flower2.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @OnClick(R.id.flower2)
    public void onClick() {
//        AnimUtil.startTranslationLX(mContext, flower);
//        AnimUtil.startTranslationRX(mContext, flower1);
    }

    private void getAnimatorSet() {

//        AnimUtil.startTranslationLX(mContext, flower);
        AnimUtil.startTranslationPK(mContext, flower, flower1);
//        ObjectAnimator translationX = ObjectAnimator.ofFloat(flower, "translationX", 0.0f, ScreenUtils.getScreenWidth(mContext)/2+flower.getX());
////        Animator anim=AnimatorInflater.loadAnimator(getContext(),R.animator.scales);
//        AnimatorSet anim=new AnimatorSet();
//        anim.play(translationX);
//        anim.start();
    }

    private void countdown() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(flower, "scaleX", 4.0f, 0.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(flower, "scaleY", 4.0f, 0.0f);
//        ObjectAnimator translationX = ObjectAnimator.ofFloat(flower, "translationX", 200f, 500f);
//        ObjectAnimator rotation = ObjectAnimator.ofFloat(flower, "rotation", 0f, 360f);

        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(flower1, "scaleX", 4.0f, 0.0f);
        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(flower1, "scaleY", 4.0f, 0.0f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(scaleX).with(scaleY);
        animSet.setDuration(500);
        animSet.start();

        AnimatorSet animSet1 = new AnimatorSet();
        animSet1.play(scaleX1).with(scaleY1);
        animSet1.setDuration(500);
        animSet1.setStartDelay(500);
        animSet1.start();

        Animator animX = AnimatorInflater.loadAnimator(getContext(), R.animator.scalex);
        Animator animY = AnimatorInflater.loadAnimator(getContext(), R.animator.scaley);
        AnimatorSet animSet2 = new AnimatorSet();
        animSet2.play(animX).with(animY);
        animSet2.setTarget(flower2);
        animSet2.setStartDelay(1000);
        animSet2.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.bottom1, R.id.bottom2, R.id.bottom3, R.id.start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom1:
                getAnimatorSet();
                break;
            case R.id.bottom2:
                getAnimatorSet();
                break;
            case R.id.bottom3:
                getAnimatorSet();
                break;
            case R.id.start:
                AnimUtil.startTranslationBottom(mContext,bottom1,bottom2,bottom3);
                break;
        }
    }
}

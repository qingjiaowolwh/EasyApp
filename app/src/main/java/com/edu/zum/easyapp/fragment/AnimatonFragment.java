package com.edu.zum.easyapp.fragment;

import android.animation.AnimatorSet;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.base.AnimatorUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by lwh on 2016/6/22.
 */
public class AnimatonFragment extends BaseFragment {

    @Bind(R.id.anim_pk_left)
    ImageView animPkLeft;
    @Bind(R.id.anim_pk_right)
    ImageView animPkRight;
    @Bind(R.id.anim_countdown)
    TextView animCountdown;
    @Bind(R.id.anim_bottom1)
    ImageView animBottom1;
    @Bind(R.id.anim_bottom2)
    ImageView animBottom2;
    @Bind(R.id.anim_bottom3)
    ImageView animBottom3;
    @Bind(R.id.start)
    Button start;
    AnimatorSet countDownSet;

    Thread mythread;
    @Bind(R.id.pr_loading)
    ImageView prLoading;
    AnimationDrawable animationDrawable;

    @Bind(R.id.ptr_loading1)
    ImageView prLoading1;
    @Bind(R.id.anim_gift_img)
    //送礼物
            ImageView animGiftImg;
    @Bind(R.id.anim_gift_count)
    TextView animGiftCount;
    @Bind(R.id.anim_gift_ll)
    LinearLayout animGiftLl;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_ainmation;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        prLoading = $(R.id.pr_loading);
        animationDrawable = (AnimationDrawable) prLoading.getDrawable();
        prLoading1 = $(R.id.ptr_loading1);

    }

    @OnClick({R.id.anim_pk_left, R.id.anim_pk_right, R.id.start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.anim_pk_left:
                break;
            case R.id.anim_pk_right:
                break;
            case R.id.start:
                animationDrawable.start();
//                AnimatorUtil.translationPK(animPkLeft, mContext.getResources().getDimension(R.dimen.margin_200dp), animPkRight, mContext.getResources().getDimension(R.dimen.margin_right)).start();
//                mythread = new Thread(new MyThread());
                mythread.start();
                new Timer().schedule(new TimerTask() {
                    int times = 3;

                    public void run() {
                        System.out.println("time:" + times);
                        if (times > 0) {
                            mHandler.sendEmptyMessage(times);
                            times--;
                        } else {
                            cancel();
                        }
                    }
                }, 500, 800);
                break;
        }
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int t = msg.what;
            animCountdown.setText(t + "");
            animCountdown.setScaleX(0);
            animCountdown.setScaleY(0);
            animCountdown.setAlpha(1);
            AnimatorUtil.animCountDown(animCountdown, 5).start();
            mythread.interrupt();
        }
    };

//    public void startMyAnim() {
//        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.in_translate_top);
//        flower2.startAnimation(anim);
//        anim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//    }

//    private void getAnimatorSet() {
//        AnimUtil.startTranslationLX(mContext, flower);
//        AnimUtil.startTranslationPK(mContext, flower, flower1);
//        ObjectAnimator translationX = ObjectAnimator.ofFloat(flower, "translationX", 0.0f, ScreenUtils.getScreenWidth(mContext)/2+flower.getX());
////        Animator anim=AnimatorInflater.loadAnimator(getContext(),R.animator.scales);
//        AnimatorSet anim=new AnimatorSet();
//        anim.play(translationX);
//        anim.start();
//    }
//    private void countdown() {
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(flower, "scaleX", 4.0f, 0.0f);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(flower, "scaleY", 4.0f, 0.0f);
////        ObjectAnimator translationX = ObjectAnimator.ofFloat(flower, "translationX", 200f, 500f);
////        ObjectAnimator rotation = ObjectAnimator.ofFloat(flower, "rotation", 0f, 360f);
//
//        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(flower1, "scaleX", 4.0f, 0.0f);
//        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(flower1, "scaleY", 4.0f, 0.0f);
//
//        AnimatorSet animSet = new AnimatorSet();
//        animSet.play(scaleX).with(scaleY);
//        animSet.setDuration(500);
//        animSet.start();
//
//        AnimatorSet animSet1 = new AnimatorSet();
//        animSet1.play(scaleX1).with(scaleY1);
//        animSet1.setDuration(500);
//        animSet1.setStartDelay(500);
//        animSet1.start();
//
//        Animator animX = AnimatorInflater.loadAnimator(getContext(), R.animator.scalex);
//        Animator animY = AnimatorInflater.loadAnimator(getContext(), R.animator.scaley);
//        AnimatorSet animSet2 = new AnimatorSet();
//        animSet2.play(animX).with(animY);
//        animSet2.setTarget(flower2);
//        animSet2.setStartDelay(1000);
//        animSet2.start();
//    }

//    @OnClick({R.id.bottom1, R.id.bottom2, R.id.bottom3, R.id.start})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.bottom1:
////                getAnimatorSet();
//                AnimatorUtil.translationPK(flower, mContext.getResources().getDimension(R.dimen.margin_left), flower1, mContext.getResources().getDimension(R.dimen.margin_right)).start();
//                break;
//            case R.id.bottom2:
//                getAnimatorSet();
//                break;
//            case R.id.bottom3:
////                getAnimatorSet();
//                break;
//            case R.id.start:
//                AnimUtil.startTranslationBottom(mContext, bottom1, bottom2, bottom3);
//                break;
//        }
//    }
}

package com.edu.zum.easyapp.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.utils.PopUtil;
import com.edu.zum.easyapp.utils.ScreenUtils;
import com.edu.zum.easyapp.utils.ToastUtil;

public class ScreenshotFragment extends BaseFragment {
    ImageView shotImg;
    Button share;
    Button save;
    View view;
    Bitmap bitmap;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_screenshot;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_screenshot_dialog, null);
        shotImg = (ImageView) view.findViewById(R.id.shotImg);
        share = (Button) view.findViewById(R.id.share);
        save = (Button) view.findViewById(R.id.save);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("分享");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("保存");
            }
        });
        $(R.id.shot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = ScreenUtils.snapShotWithStatusBar(getActivity());
                if (bitmap != null) {
                    shotImg.setImageBitmap(bitmap);
                    new PopUtil(getActivity(), view, v).showCenter();
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f);
                    AnimatorSet animSet = new AnimatorSet();
                    animSet.play(scaleX).with(scaleY);
                    animSet.setDuration(500);
                    animSet.start();
                }
            }
        });
    }


}

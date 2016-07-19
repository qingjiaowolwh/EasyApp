package com.edu.zum.easyapp.fragment;

import android.graphics.Color;
import android.view.View;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.widgets.CircleTextProgressbar;
import com.edu.zum.easyapp.widgets.PrintTextView;

public class PrintAndroidCircleProgressbarFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_print_android_circle_progressbar;
    }

    /**
     * 默认类型。
     */
    private CircleTextProgressbar mTvDefault;
    /**
     * 五个字的。
     */
    private CircleTextProgressbar mTvFive;
    /**
     * 圆心点击变色。
     */
    private CircleTextProgressbar mTvCicleColor;

    /**
     * 顺数进度条。
     */
    private CircleTextProgressbar mTvCount;
    /**
     * 宽进度条。
     */
    private CircleTextProgressbar mTvWide;
    /**
     * 窄进度条。
     */
    private CircleTextProgressbar mTvNarrow;
    /**
     * 红色进度条。
     */
    private CircleTextProgressbar mTvRedPro;
    /**
     * 红色边框。
     */
    private CircleTextProgressbar mTvRedFrame;
    /**
     * 红色圆心。
     */
    private CircleTextProgressbar mTvRedCircle;
    /**
     * 跳过。
     */
    private CircleTextProgressbar mTvSkip;
    /**
     * 更新进度条文字。
     */
    private CircleTextProgressbar mTvProgressBar1, mTvProgressBar2;

    @Override
    protected void setUpView() {
        super.setUpView();
        getContentView().findViewById(R.id.kaishi).setOnClickListener(this);
        PrintTextView tv = (PrintTextView) getContentView().findViewById(R.id.printText);
        tv.printString("你空间放到空间急急急急急急急急急急急急机可怜见看都是非法的地方大师傅士大夫但是范德萨范德萨似的大师傅大师傅似的法大师傅士大夫士大夫士大夫犯得上发射点来非得加上了顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶");

        mTvDefault = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_default);
        mTvFive = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_five_text);
        mTvCicleColor = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_red_circle_color);

        // 和系统进度条一样，由0-100。
        mTvCount = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_count);
        mTvCount.setProgressType(CircleTextProgressbar.ProgressType.COUNT);

        // 宽进度条。
        mTvWide = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_five_wide);
        mTvWide.setProgressLineWidth(30);//写入宽度。
        mTvWide.setTimeMillis(3500);// 把倒计时时间改长一点。

        // 窄进度条。
        mTvNarrow = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_five_narrow);
        mTvNarrow.setProgressLineWidth(3);// 写入宽度。

        // 红色进度条。
        mTvRedPro = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_red_progress);
        mTvRedPro.setProgressColor(Color.RED);

        // 红色边框进度条。
        mTvRedFrame = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_red_frame);
        mTvRedFrame.setOutLineColor(Color.RED);

        // 红色圆心进度条。
        mTvRedCircle = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_red_circle);
        mTvRedCircle.setInCircleColor(Color.RED);

        mTvProgressBar1 = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_red_progress_text1);
        mTvProgressBar1.setCountdownProgressListener(1, progressListener);
        mTvProgressBar1.setProgressType(CircleTextProgressbar.ProgressType.COUNT);

        mTvProgressBar2 = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_red_progress_text2);
        mTvProgressBar2.setCountdownProgressListener(2, progressListener);


        // 模拟网易新闻跳过。
        mTvSkip = (CircleTextProgressbar) getContentView().findViewById(R.id.tv_red_skip);
        mTvSkip.setOutLineColor(Color.TRANSPARENT);
        mTvSkip.setInCircleColor(Color.parseColor("#AAC6C6C6"));
        mTvSkip.setProgressColor(Color.DKGRAY);
        mTvSkip.setProgressLineWidth(3);
    }

    private CircleTextProgressbar.OnCountdownProgressListener progressListener = new CircleTextProgressbar.OnCountdownProgressListener() {
        @Override
        public void onProgress(int what, int progress) {
            if (what == 1) {
                mTvProgressBar1.setText(progress + "%");
            } else if (what == 2) {
                mTvProgressBar2.setText(progress + "%");
            }
            // 比如在首页，这里可以判断进度，进度到了100或者0的时候，你可以做跳过操作。
        }
    };


    @Override
    public void onClick(View v) {
        mTvDefault.reStart();
        mTvFive.reStart();
        mTvCicleColor.reStart();
        mTvCount.reStart();
        mTvWide.reStart();
        mTvNarrow.reStart();
        mTvRedPro.reStart();
        mTvRedFrame.reStart();
        mTvRedCircle.reStart();
        mTvProgressBar1.reStart();
        mTvProgressBar2.reStart();
        mTvSkip.reStart();
    }
}

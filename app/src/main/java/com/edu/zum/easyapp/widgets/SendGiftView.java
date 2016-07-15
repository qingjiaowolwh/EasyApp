package com.edu.zum.easyapp.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.base.AnimatorUtil;

/**
 * Created by lwh on 2016/7/15.
 */
public class SendGiftView extends RelativeLayout {
    private View gift_ll;
    private View gift_img;
    private View gift_count;
    private int count=1;


    public SendGiftView(Context context) {
        super(context);
        init();
    }

    public SendGiftView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SendGiftView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_anim_gift, this, false);
        gift_ll = view.findViewById(R.id.anim_gift_ll);
        gift_img = view.findViewById(R.id.anim_gift_img);
        gift_count = view.findViewById(R.id.anim_gift_count);
        addView(view);
    }

    private void sendGift() {
        AnimatorUtil.sendGift(gift_ll, gift_img, gift_count, count).start();
    }

}

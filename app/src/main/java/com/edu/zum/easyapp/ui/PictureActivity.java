package com.edu.zum.easyapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PictureActivity extends BaseActivity {

    @Bind(R.id.picture)
    ImageView picture;
    String url;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_picture;
    }

    @Override
    protected void getBundleExtras(@NonNull Bundle extras) {
        super.getBundleExtras(extras);
        url = extras.getString("url");

    }

    @Override
    protected void setUpView() {
        ImageLoader.getInstance().displayImage(
                url, picture,
                ImageLoaderHelper.getInstance(mContext).getDisplayOptions(50));

    }

}

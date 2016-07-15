package com.edu.zum.easyapp.manager;

import android.content.Context;

import com.ganhuo.dao.DaoMaster;
import com.ganhuo.dao.DaoSession;

/**
 * Created by lwh on 2016/7/15.
 */
public class DaoManager {
    private final static String TAG = DaoManager.class.getSimpleName();
    private final static String DB_NAME = "mydb.sqlite";
    private volatile static DaoManager daoManager;//多线程访问

    private static DaoMaster.DevOpenHelper helper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private Context context;

    public static DaoManager getInstance() {
        DaoManager instance = null;
        if (daoManager == null) {
            synchronized (DaoManager.class) {
                if (instance == null) {
                    instance = new DaoManager();
                    daoManager = instance;
                }
            }
        }
        return daoManager;
    }

    public void init(Context context) {
        this.context = context;
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public void closeConnection() {
        closeDaoSession();
        closeHelper();

    }

    public void closeHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }
}

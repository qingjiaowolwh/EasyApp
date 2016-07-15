package com.edu.zum.easyapp.manager;

import android.content.Context;

import com.ganhuo.dao.GanhuoDao;
import com.ganhuo.entity.Ganhuo;

import java.util.List;


/**
 * Created by lwh on 2016/7/15.
 */
public class DaoUtils {
    private DaoManager daoManager;
    private GanhuoDao ganhuodao;

    public DaoUtils(Context context) {
        daoManager = DaoManager.getInstance();
        daoManager.init(context);
        ganhuodao = daoManager.getDaoSession().getGanhuoDao();
    }

    public boolean insertGanhuo(final List<Ganhuo> ganHuos) {
        boolean flag = false;
        try {
            ganhuodao.getSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Ganhuo ganhuo : ganHuos) {
                        ganhuodao.insertOrReplace(ganhuo);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<Ganhuo> searchGanhuo() {
        boolean flag = false;
        List<Ganhuo> ganhuos = ganhuodao.loadAll();
        return ganhuos;
    }


}

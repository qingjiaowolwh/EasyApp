package com.edu.zum.easyapp.api;



import com.edu.zum.easyapp.model.BaseModel;
import com.edu.zum.easyapp.model.ResultModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by asus on 2016/3/9.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("index.php/Interface/User/loginApp")
    public Observable<BaseModel> getUser(@Field("data") String data);

//    Apis.GanHuo + "/" + 休息视频 + "/10/" + mCurrentPageIndex
//    "id") int id
    @GET("api/data/{type}/10/{PageIndex}")
    public Observable<ResultModel> getGoods(@Path("type")String type, @Path("PageIndex")int PageIndex);
}

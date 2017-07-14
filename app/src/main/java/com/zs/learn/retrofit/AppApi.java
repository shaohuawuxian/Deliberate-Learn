package com.zs.learn.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhangshao on 2017/7/13.
 */

public interface AppApi {
    @GET("dictionary.php?key=ACFC93FF5988E38CCD2BE9A9277B9A74")
    Call<ResponseBody> getEnglishWord(@Query("w") String word);
    @GET("dsapi/")
    Call<ResponseBody> getDailySentence();
}

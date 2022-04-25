package com.imocha.sfactr.retrofit;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitSfactrGateway {
	@POST("v1.7/auth_smart.php")
	Call<ResponseBody> executeAuthSmart(@Body RequestBody request);

	@POST("/otpless/pack1/v1.1/auth_smart.php")
	Call<ResponseBody> executeOTPlessAuthSmart(@Body RequestBody request);
}

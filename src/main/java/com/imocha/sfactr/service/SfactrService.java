package com.imocha.sfactr.service;

import java.io.IOException;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.imocha.sfactr.model.SfactrAuthResultRequest;
import com.imocha.sfactr.retrofit.RetrofitSfactrGateway;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@Slf4j
@Service
public class SfactrService {

	private RetrofitSfactrGateway retrofitSfactrGateway;

	@Value("${sfactr.base_url}")
	private String BASE_URL;

	@Value("${sfactr.hash_key}")
	private String HASH_KEY;

	@Value("${sfactr.app_id}")
	private String APP_ID;

	@Value("${sfactr.secret_key}")
	private String SECRET_KEY;

	private String OTPLESS_ENABLED_MESSAGE = "OTPless enabled";
	private String APP_ID_NAME = "AppID";
	private String SECRET_KEY_NAME = "SecretKey";

	@PostConstruct
	private void init() {

		OkHttpClient.Builder builder = new OkHttpClient.Builder();

		OkHttpClient okHttpClient = builder.build();

		Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL).build();

		retrofitSfactrGateway = retrofit.create(RetrofitSfactrGateway.class);
	}

	public String authSmart() throws IOException {

		RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(APP_ID_NAME, APP_ID)
				.addFormDataPart(SECRET_KEY_NAME, SECRET_KEY).build();

		Call<ResponseBody> call = retrofitSfactrGateway.executeAuthSmart(body);

		Response<ResponseBody> response = call.execute();

		return response.body().string();
	}

	public String OTPlessAuthSmart() throws IOException {

		RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(APP_ID_NAME, APP_ID)
				.addFormDataPart(SECRET_KEY_NAME, SECRET_KEY).build();

		Call<ResponseBody> call = retrofitSfactrGateway.executeOTPlessAuthSmart(body);

		Response<ResponseBody> response = call.execute();

		return response.body().string();
	}
	
	public String OTPlessAuthSmartCallbackVerify() {
		return OTPLESS_ENABLED_MESSAGE;
	}

	public String OTPlessAuthSmartCallback(SfactrAuthResultRequest request) throws IOException {

		Base64.Decoder decoder = Base64.getUrlDecoder();

		String header = new String(decoder.decode(request.getSfactrAuthResult()));

		return header;
	}

}

package com.powwau.gtad;

import android.util.Base64;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;

/**
 * 20150211. Initial version created by jorge.
 */
public class TodolyService {

    final static String TODOLY_API_URL = "https://todo.ly/api";
    final static String PROJECTS_ENDPOINT = "/projects.json";
    final static String ACCEPTED_DATA = "application/json";

    private String mUsername;
    private String mPassword;

    public interface ApiInterface {
        @GET(PROJECTS_ENDPOINT)
        void getProjects(Callback<List<TodolyProject>> callback);
    }

    public TodolyService(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public ApiInterface generateServiceInterface() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(TODOLY_API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        String authString = "Basic " + Base64.encodeToString((mUsername + ":" + mPassword).getBytes(), Base64.NO_WRAP);
                        request.addHeader("Accept", ACCEPTED_DATA);
                        request.addHeader("Authorization", authString);
                    }
                });
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(ApiInterface.class);
    }
}

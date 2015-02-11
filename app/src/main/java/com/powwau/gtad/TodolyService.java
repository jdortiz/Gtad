package com.powwau.gtad;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * 20150211. Initial version created by jorge.
 */
public class TodolyService {

    final static String TODOLY_API_URL = "https://todo.ly/api";
    final static String PROJECTS_ENDPOINT = "/projects.json";

    public interface ApiInterface {
        @GET(PROJECTS_ENDPOINT)
        void getProjects(Callback<List<TodolyProject>> callback);
    }


    public TodolyService() {
    }

    public ApiInterface generateServiceInterface() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        RestAdapter restAdapter = builder.setEndpoint(TODOLY_API_URL)
                .build();
        return restAdapter.create(ApiInterface.class);
    }
}

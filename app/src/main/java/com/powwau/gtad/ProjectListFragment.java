package com.powwau.gtad;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProjectListFragment extends ListFragment {

    final static String LOG_TAG = ProjectListFragment.class.getSimpleName();

    TodolyService.ApiInterface mTodolyApiInterface;
    ArrayAdapter<TodolyProject> mAdapter;

    public ProjectListFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TodolyService todolyService = new TodolyService(getString(R.string.todoly_username), getString(R.string.todoly_password));
        mTodolyApiInterface = todolyService.generateServiceInterface();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<TodolyProject> projects = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_item_project, R.id.text_view_project_name,
                projects);
        setListAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mTodolyApiInterface.getProjects(new Callback<List<TodolyProject>>() {
            @Override
            public void success(List<TodolyProject> projects, Response response) {
                if (response.getStatus() == 200) {
                    mAdapter.clear();
                    mAdapter.addAll(projects);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Log.e(LOG_TAG, "Project retrieval status problem: " + response.getReason());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.w(LOG_TAG, "ERROR: downloading " + error.getBody());
            }
        });

    }
}

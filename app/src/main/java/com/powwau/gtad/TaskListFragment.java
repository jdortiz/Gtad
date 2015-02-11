package com.powwau.gtad;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class TaskListFragment extends ListFragment {

    final static String PROJECT_ID = "PROJECT_ID";

    private String mProjectID = "";
    private TodolyService.ApiInterface mTodolyApiInterface;
    private ArrayAdapter<TodolyTask> mAdapter;

    public TaskListFragment() {
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
        retrieveProjectId();
        prepareListView();
    }

    private void retrieveProjectId() {
        if (getArguments().containsKey(PROJECT_ID)) {
            mProjectID = getArguments().getString(PROJECT_ID);
        }
    }

    private void prepareListView() {
        List<TodolyTask> tasks = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_task, R.id.text_view_task_name, tasks);
        setListAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mTodolyApiInterface.getTasksForProjectId(mProjectID, new Callback<List<TodolyTask>>() {
            @Override
            public void success(List<TodolyTask> todolyTasks, Response response) {
                mAdapter.clear();
                mAdapter.addAll(todolyTasks);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }
}

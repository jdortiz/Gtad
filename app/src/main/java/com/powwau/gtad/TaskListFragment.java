package com.powwau.gtad;

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

/**
 * A placeholder fragment containing a simple view.
 */
public class TaskListFragment extends ListFragment {

    final static String PROJECT_ID = "PROJECT_ID";

    private String mProjectID = "";
    private ArrayAdapter<TodolyTask> mAdapter;

    public TaskListFragment() {
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


}

package com.powwau.gtad;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProjectListFragment extends ListFragment {

    public ProjectListFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<TodolyProject> projects = new ArrayList<>();
        ArrayAdapter<TodolyProject> adapter = new ArrayAdapter<TodolyProject>(getActivity(),
                R.layout.list_item_project, R.id.text_view_project_name,
                projects);
        setListAdapter(adapter);
    }
}

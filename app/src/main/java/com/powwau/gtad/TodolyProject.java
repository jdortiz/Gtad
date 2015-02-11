package com.powwau.gtad;

import com.google.gson.annotations.SerializedName;

/**
 * 20150211. Initial version created by jorge.
 */
public class TodolyProject {
    @SerializedName("Id")
    private String mId;
    @SerializedName("Content")
    private String mName;

    public TodolyProject() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "(ID=" + mId + "): " + mName;
    }
}

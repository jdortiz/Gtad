package com.powwau.gtad;

import com.google.gson.annotations.SerializedName;

/**
 * 20150211. Initial version created by jorge.
 */
public class TodolyTask {

    @SerializedName("Id")
    private String mId;
    @SerializedName("Content")
    private String mName;

    public TodolyTask() {
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
        this.mName = name;
    }

    @Override
    public String toString() {
        return "(ID=" + mId + "):" + mName;
    }
}

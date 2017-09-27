package com.zs.learn.designpattern;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangshao on 2017/9/26.
 */

public class DesignPattern implements Parcelable{

    public String name;
    public String what;
    public String how;
    public String why;
    public String example;
    public String url;
    public DesignPattern(){}

    protected DesignPattern(Parcel in) {
        name = in.readString();
        what = in.readString();
        how = in.readString();
        why = in.readString();
        example = in.readString();
        url = in.readString();
    }

    public static final Creator<DesignPattern> CREATOR = new Creator<DesignPattern>() {
        @Override
        public DesignPattern createFromParcel(Parcel in) {
            return new DesignPattern(in);
        }

        @Override
        public DesignPattern[] newArray(int size) {
            return new DesignPattern[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(what);
        dest.writeString(how);
        dest.writeString(why);
        dest.writeString(example);
        dest.writeString(url);
    }
}


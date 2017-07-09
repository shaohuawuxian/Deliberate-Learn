package com.zs.learn.english.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by shao on 2017/7/8.
 */

public class EnglishWord extends DataSupport {
    @Column(unique = true, defaultValue = "unknown")
    public String word=null;//单词
    public String phonogram;//音标

    public long insertTime=System.currentTimeMillis();//写入时间
    public String annotation;//注释

    public boolean isPassed;//
}

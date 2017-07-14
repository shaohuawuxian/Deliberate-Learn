package com.zs.learn.english.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by zhangshao on 2017/7/14.
 */

public class DailySentence extends DataSupport {
    @Column(unique = true, defaultValue = "unknown")
    public String date;
    public String content;//英语句子
    public String note;//翻译句子

    public String imageUrl;
}

package com.zs.learn.english.model;


import android.text.TextUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by zhangshao on 2017/7/13.
 */

public class EnglishWordXml extends DefaultHandler {
    public EnglishWord englishWord;

    private  String nodeName;
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        englishWord=new EnglishWord();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName=qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String data=new String(ch,start,length);
        if(nodeName==null){
            return;
        }
        switch (nodeName){
            case "key":
                englishWord.word=data;
                break;
            case "ps":
                englishWord.phonogram="["+data+"]";
                break;
            case "pron":
                englishWord.readUrl=data;
                break;
            case "pos" :
                if(TextUtils.isEmpty(englishWord.annotation)){
                    englishWord.annotation=data;
                }else{
                    englishWord.annotation=englishWord.annotation+data;
                }
                break;
            case "acceptation":
                englishWord.annotation+=data;
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        nodeName=null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}

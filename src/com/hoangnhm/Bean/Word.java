package com.hoangnhm.Bean;

import com.hoangnhm.Util.Constant;


/**
 * Created by vantuegia on 1/17/2016.
 */
public class Word {

    private String mRowId;
    private String mWord;
    private String mType;
    private String mUkPron;
    private String mUsPron;
    private String mErrorMsg;

    public Word() {
        mUkPron = "";
        mUsPron = "";
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String mWord) {
        this.mWord = mWord;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public String getUkPron() {
        return mUkPron;
    }

    public void setUkPron(String mUkPron) {
        this.mUkPron = mUkPron;
    }

    public String getUsPron() {
        return mUsPron;
    }

    public void setUsPron(String mUsPron) {
        this.mUsPron = mUsPron;
    }

    public String getRowId() {
        return mRowId;
    }

    public void setRowId(String mRowId) {
        this.mRowId = mRowId;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }

    public void setErrorMsg(String mErrorMsg) {
        this.mErrorMsg = mErrorMsg;
    }
}

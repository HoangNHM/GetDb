package com.hoangnhm.Util;

/**
 * Created by vantuegia on 1/19/2016.
 */
public enum WordType {
    NOUN(Constant.NOUN),
    VERB(Constant.VERB),
    ADJ(Constant.ADJ);

    private final String type;

    private WordType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}

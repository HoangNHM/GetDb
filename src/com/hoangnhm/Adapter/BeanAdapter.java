package com.hoangnhm.Adapter;

import com.hoangnhm.Bean.Word;
import com.hoangnhm.Util.Constant;
import com.hoangnhm.Util.WordType;

import java.util.List;

/**
 * Created by vantuegia on 1/23/2016.
 */
public class BeanAdapter {

    private Word mWord;

    public BeanAdapter(Word word) {
        this.mWord = word;
    }

    public void setPron(List<String> prons) {
        if (prons.size() < 2) {

            String string = (String) prons.get(0);

            mWord.setUkPron(string);
            mWord.setUsPron(string);

//            System.out.println(mWord.getWord() + " " + mWord.getUkPron());

        } else {

            String uk = (String) prons.get(0);
            String us = (String) prons.get(1);

            mWord.setUkPron(uk);
            mWord.setUsPron(us);

//            System.out.println(mWord.getWord() + " uk: " + mWord.getUkPron() + " us: " + mWord.getUsPron());

        }
    }


}

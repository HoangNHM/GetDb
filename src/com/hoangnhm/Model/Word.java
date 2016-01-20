package com.hoangnhm.Model;

import com.hoangnhm.Util.Constant;
import com.hoangnhm.Util.WordType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * Created by vantuegia on 1/17/2016.
 */
public class Word {
    private WordType mType;
    public String mPron;

    public Word(String word, String type) throws Exception {
        Document doc = Jsoup.connect("http://dictionary.cambridge.org/dictionary/english/" + word)
//                .data("query", "Java")
//                .userAgent("Mozilla")
//                .cookie("auth", "token")
                .timeout(Constant.TIMEOUT)
                .post();

        setType(type);
        Elements elements = doc.getElementsByClass(Constant.POS_HEADER);
        setPron(elements);

//        System.out.println(mPron);
    }

    public void setType(String type) {
        switch (type) {
            case Constant.NOUN:
                mType = WordType.NOUN;
                break;
            case Constant.VERB:
                mType = WordType.VERB;
                break;
            case "adj.":
                mType = WordType.ADJ;
                break;
        }
    }

    public void setPron(Elements elements) throws Exception {
        Element element = findSameType(elements);
        if (null != element) {
            StringBuilder str = new StringBuilder();
            for (Element e : element.children()) {
                if ("uk".equals(e.className()) || "us".equals(e.className())) {
                    str.append(" ").append(e.text());
                }
            }
            mPron = str.toString();
        }
    }

    private Element findSameType(Elements elements) throws Exception{
        Element element = null;
        TopLoop : for (Element ele : elements) {
            Elements eles = ele.getElementsByClass(Constant.POS);
            for (Element e : eles) {
                if (e.text().equals(mType.getType())) {
//                    System.out.println(ele);
                    element = ele;
                    break TopLoop;
                }
            }
        }
        return element;
    }
}

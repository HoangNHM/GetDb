package com.hoangnhm.Get;

import com.hoangnhm.Adapter.BeanAdapter;
import com.hoangnhm.Bean.Word;
import com.hoangnhm.Util.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vantuegia on 1/23/2016.
 */
public class HtmlToBean {
    private Word mWord;

    public HtmlToBean(Word word) {
        this.mWord = word;
    }

    public void queryHtml() throws Exception, IndexOutOfBoundsException {
        String lowcaseWord = mWord.getWord().toLowerCase();
//        mWord.setWord(lowcaseWord);
        modifyType(mWord.getType());
        Document doc = Jsoup.connect("http://dictionary.cambridge.org/dictionary/english/" + lowcaseWord)
                .timeout(Constant.TIMEOUT)
                .post();

        Elements elements = doc.getElementsByClass(Constant.POS_HEADER);
        Element element = findSameType(elements);
        if (element == null) {
            System.out.println(mWord.getWord() + " -------- 609 type not found");
            return;
        }
        List<String> list = getPron(element);
        if (null != list) {
            BeanAdapter adapter = new BeanAdapter(mWord);
            adapter.setPron(list);
        } else {
            System.out.println(mWord.getWord() + " -------- empty pron");
        }
    }

    private Element findSameType(Elements elements) throws Exception {
        Element element = null;
        TopLoop:
        for (Element ele : elements) {
            Elements eles = ele.getElementsByClass(Constant.POS);
            for (Element e : eles) {
                if (e.text().equals(mWord.getType())) {
//                    System.out.println(ele);
                    element = ele;
                    break TopLoop;
                }
            }
        }
        return element;
    }

    private List<String> getPron(Element element) throws NullPointerException{
        ArrayList<String> strings = new ArrayList<>();
        for (Element e : element.children()) {
//                if ("uk".equals(e.className()) || "us".equals(e.className())) {
            Elements e1 = e.getElementsByClass("ipa");
            for (Element e2 : e1) {
                strings.add(e2.text());
            }
//                }
        }
        return strings;
    }

    private void modifyType(String type) {
        switch (type) {
            case "noun":
                mWord.setType("noun");
                break;
            case "verb":
                mWord.setType("verb");
                break;
            case "adj.":
                mWord.setType("adjective");
                break;
        }
    }

}

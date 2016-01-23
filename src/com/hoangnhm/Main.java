package com.hoangnhm;
import com.hoangnhm.Bean.Word;
import com.hoangnhm.Db.Db;
import com.hoangnhm.Get.HtmlToBean;
import org.jsoup.HttpStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Db db = new Db();
        List<Word> words = db.queryDb();
        int count = 0;
        for (Word word : words) {
            System.out.println(count);
//              Word w = new Word(resultSet.getString("word"), resultSet.getString("type"));
            HtmlToBean htmlToBean = new HtmlToBean(word);
            try {
                htmlToBean.queryHtml();
            } catch (HttpStatusException e) {
                System.out.println(word.getWord() + " -------- 404");
//                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println(word.getWord() + " -------- NULL");
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(word.getWord() + " -------- 404");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(word.getWord() + " -------- Exception");
                e.printStackTrace();
            }
            count++;
        }
        // write your code here
//        Word word = new Word();
//        HtmlToBean htmlToBean = new HtmlToBean(word);
//        try {
//            htmlToBean.queryHtml("record", "verb");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Connection connection = null;
//        ResultSet resultSet = null;
//        Statement statement = null;
//
//        try {
//            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getConnection("jdbc:sqlite:collo_dictionary.db");
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM word_list WHERE word_id = \"0\"");
//            while (resultSet.next()) {
//                System.out.println("word name:"
//                        + resultSet.getString("word"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                resultSet.close();
//                statement.close();
//                connection.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


//        try {
//            Word w = new Word("record", "verb");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

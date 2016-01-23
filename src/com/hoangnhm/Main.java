package com.hoangnhm;
import com.hoangnhm.Bean.Word;
import com.hoangnhm.Db.Db;
import com.hoangnhm.File.File;
import com.hoangnhm.Get.HtmlToBean;
import org.jsoup.HttpStatusException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Db db = new Db();
        List<Word> result = new ArrayList<>();
        List<Word> words = db.queryDb();
        List<String> errorWords = new ArrayList<>();
        int count = 0;
        for (Word word : words) {
            System.out.println(count + " ; word: " + word.getWord());
            HtmlToBean htmlToBean = new HtmlToBean(word);
            Exception exception = null;
            try {
                htmlToBean.queryHtml();
                result.add(word);
            } catch (HttpStatusException e) {
                exception = e;
//                System.out.println(word.getWord() + " -------- 404");
                e.printStackTrace();
            } catch (NullPointerException e) {
                exception = e;
//                System.out.println(word.getWord() + " -------- NULL");
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                exception = e;
//                System.out.println(word.getWord() + " -------- 404");
                e.printStackTrace();
            } catch (Exception e) {
                exception = e;
//                System.out.println(word.getWord() + " -------- Exception");
                e.printStackTrace();
            } finally {
                if (word.getUkPron().isEmpty() || word.getUsPron().isEmpty()) {
                    if (null != exception) {
                        errorWords.add(count + " " + word.getWord() + " " + exception.toString());
                    } else {
                        errorWords.add(count + " " + word.getWord() + " error unknown");
                    }
//                    System.out.println(word.getWord() + " pron empty");
                }
            }
            count++;
        }

        File file = new File("error_list.txt");
        try {
            file.writeToFile(errorWords);
        } catch (IOException e) {
            e.printStackTrace();
        }

        db.updateDb(result);

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

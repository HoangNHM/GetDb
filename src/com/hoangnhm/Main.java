package com.hoangnhm;
import com.hoangnhm.Bean.Word;
import com.hoangnhm.Db.Db;
import com.hoangnhm.Get.HtmlToBean;
import org.jsoup.HttpStatusException;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final String MSG_ERROR = "%s %s %s";

    public static void main(String[] args) {
        Db db = new Db();
//        File file = new File("error_list.txt");
//        List<Word> result = new ArrayList<>();
        List<Word> words = db.queryDb();
//        List<String> errorWords = new ArrayList<>();
//        int count = 0;
        for (Word word : words) {
            System.out.println(word.getRowId() + " ; " + word.getWord());
            HtmlToBean htmlToBean = new HtmlToBean(word);
            Exception exception = null;
            try {
                htmlToBean.queryHtml();
                db.updatePron(word);
//                result.add(word);
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
                        word.setErrorMsg(String.format(MSG_ERROR, word.getRowId(), word.getWord(), exception.toString()));
//                        errorWords.add(count + " " + word.getWord() + " " + exception.toString());
                    } else {
                        word.setErrorMsg(String.format(MSG_ERROR, word.getRowId(), word.getWord(), "error unknown"));
//                        errorWords.add(count + " " + word.getWord() + " error unknown");
                    }
                    db.updateErrorMsg(word);
//                    file.writeToFile(word.getErrorMsg());
//                    System.out.println(word.getWord() + " pron empty");
                }
            }
//            count++;
        }
//        try {
//            file.mWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            file.writeToFile(errorWords);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        db.updatePron(result);

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

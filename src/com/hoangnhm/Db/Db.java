package com.hoangnhm.Db;

import com.hoangnhm.Bean.Word;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vantuegia on 1/20/2016.
 */
public class Db {

    public List<Word> queryDb() {
        List<Word> words = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:collo_dictionary.db");
            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT word, type, word_id FROM word_list WHERE word_id < \"20\"");
            resultSet = statement.executeQuery("SELECT word, type, word_id FROM word_list");
            while (resultSet.next()) {
                Word word = new Word();
                word.setWord(resultSet.getString("word"));
                word.setType(resultSet.getString("type"));
                words.add(word);

//                System.out.println(resultSet.getString("word") + " " + resultSet.getString("type"));

//                try {
//                    Word w = new Word(resultSet.getString("word"), resultSet.getString("type"));
//                    Statement statementUpdate = connection.createStatement();
//                    statementUpdate.execute("UPDATE word_list\n" + "SET pron='" + w.mPron + "' WHERE word_id='" + resultSet.getString("word_id") + "'");
//                    statementUpdate.close();
//                } catch (Exception e) {
//                    System.out.println(resultSet.getString("word"));
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return words;
    }

    public void updateDb(List<Word> words) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:collo_dictionary.db");
            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT word, type, word_id FROM word_list WHERE word_id < \"20\"");
//            resultSet = statement.executeQuery("SELECT word, type, word_id FROM word_list");
            for (Word w : words) {
                Statement statementUpdate = connection.createStatement();
                String query = String.format("UPDATE word_list SET uk_pron='%s', us_pron='%s' WHERE word='%s' AND type='%s'",
                        w.getUkPron(), w.getUsPron(), w.getWord(), w.getType());
                statementUpdate.execute(query);
                statementUpdate.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

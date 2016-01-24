package com.hoangnhm.Db;

import com.hoangnhm.Bean.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vantuegia on 1/20/2016.
 */
public class Db {

    private Connection mConnection;
    private final static String QUERY_UPDATE = "UPDATE word_list SET uk_pron='%s', us_pron='%s' WHERE rowid='%s'";
    private final static String QUERY_ERROR = "UPDATE word_list SET error_msg='%s' WHERE rowid='%s'";

    public Db() {
        try {
            Class.forName("org.sqlite.JDBC");
            mConnection = DriverManager.getConnection("jdbc:sqlite:collo_dictionary.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Word> queryDb() {
        List<Word> words = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;

        try {
//            Class.forName("org.sqlite.JDBC");
//            mConnection = DriverManager.getConnection("jdbc:sqlite:collo_dictionary.db");
            statement = mConnection.createStatement();
//            resultSet = statement.executeQuery("SELECT word, type, rowid FROM word_list WHERE word_id < \"20\"");
//            resultSet = statement.executeQuery("SELECT word, type, rowid, uk_pron FROM word_list WHERE uk_pron=''");
            resultSet = statement.executeQuery("SELECT word, type, rowid FROM word_list");
            while (resultSet.next()) {
                Word word = new Word();
                word.setWord(resultSet.getString("word"));
                word.setType(resultSet.getString("type"));
                word.setRowId(resultSet.getString("rowid"));
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
//                mConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return words;
    }

    public void updatePron(List<Word> words) {
        Statement statementUpdate = null;
        try {
//            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getConnection("jdbc:sqlite:collo_dictionary.db");
            for (Word w : words) {
                statementUpdate = mConnection.createStatement();
                String query = String.format(QUERY_UPDATE,
                        w.getUkPron(), w.getUsPron(), w.getRowId());
                statementUpdate.execute(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statementUpdate.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updatePron(Word word) {

        Statement statementUpdate = null;
        try {
//            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getConnection("jdbc:sqlite:collo_dictionary.db");
            statementUpdate = mConnection.createStatement();
            String query = String.format(QUERY_UPDATE,
                    word.getUkPron(), word.getUsPron(), word.getRowId());
            statementUpdate.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statementUpdate.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateErrorMsg(Word word) {

        Statement statementUpdate = null;
        try {
//            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getConnection("jdbc:sqlite:collo_dictionary.db");
            statementUpdate = mConnection.createStatement();
            String query = String.format(QUERY_ERROR,
                    word.getErrorMsg(), word.getRowId());
            statementUpdate.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statementUpdate.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

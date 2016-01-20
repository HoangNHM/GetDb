package com.hoangnhm.Db;

import com.hoangnhm.Model.Word;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by vantuegia on 1/20/2016.
 */
public class Db {
    public void queryDb() {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:collo_dictionary.db");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT word, type, word_id FROM word_list WHERE word_id < \"8061\"");
            while (resultSet.next()) {
//                System.out.println(resultSet.getString("word") + " " + resultSet.getString("type"));

                try {
                    Word w = new Word(resultSet.getString("word"), resultSet.getString("type"));
                    Statement statementUpdate = connection.createStatement();
                    statementUpdate.execute("UPDATE word_list\n" +
                            "SET pron='" + w.mPron + "' WHERE word_id='" + resultSet.getString("word_id") + "'");
                    statementUpdate.close();
                } catch (Exception e) {
                    System.out.println(resultSet.getString("word"));
                }
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
    }
}

package com.hoangnhm;
import com.hoangnhm.Db.Db;
import com.hoangnhm.Model.Word;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Db db = new Db();
        db.queryDb();
        // write your code here

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
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

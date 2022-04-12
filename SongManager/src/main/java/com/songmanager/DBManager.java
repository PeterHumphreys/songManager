package com.songmanager;

import java.sql.*;

public class DBManager
{
    //Creates a DB connection
    public static Connection getConnection()
    {
        Connection connection = null;
        String url = "jdbc:mariadb://localhost:3306/songsproject";
        String user = "root";
        String pwd = "root";

        try
        {
            connection = DriverManager.getConnection(url, user, pwd);
        }
        catch (Exception ex)
        {
            System.out.println("Connection failed");
        }
        System.out.println("Connection successful");
        return connection;
    }

    //Creates a table in the DB
    public static void createTable() throws Exception {
        try
        {
            Connection connection = getConnection();
            PreparedStatement create = connection.prepareStatement("CREATE OR REPLACE TABLE songsproject.songDetails(id int NOT NULL, SongTitle varchar(25), Artist char(25), Genre char(15), LengthOfSong varchar(4), PublishedYear varchar(4), Label varchar(20), Album varchar(20), PRIMARY KEY(SongTitle));");
            create.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {System.out.println("createTable function completed...");};
    }

    //Adds a song to the db
    public static void addSong(String title, String artist,  String genre, String songLength, String year, String recordLabel, String album)
    {
        try
        {
            Connection connection = getConnection();
            String query = "INSERT INTO song ("+
                    " SongTitle," +
                    " ArtistID," +
                    " GenreName," +
                    " LengthOfSong," +
                    " YearReleased," +
                    " Label," +
                    " Album) VALUES ('"+
                    title +
                    "', '" +
                    artist +
                    "', '" +
                    genre +
                    "', '" +
                    songLength +
                    "', '" +
                    year +
                    "', '" +
                    recordLabel +
                    "', '" +
                    album +
                    "');";
            System.out.println(query);
            PreparedStatement insertStmt = connection.prepareStatement(query);
            /*String query = "INSERT INTO song (" +
                    " SongID," +
                    " SongTitle," +
                    " ArtistID," +
                    " GenreName," +
                    " LengthOfSong," +
                    " YearReleased," +
                    " Label," +
                    " Album)" +
                    " VALUES (null, ?, ?, ?, ?, ?, ?);";
            PreparedStatement insertStmt = connection.prepareStatement(query);
            insertStmt.setString(1, title);
            insertStmt.setString(2, artist);
            insertStmt.setString(3, genre);
            insertStmt.setString(4, songLength);
            insertStmt.setString(5, year);
            insertStmt.setString(6, recordLabel);
            insertStmt.setString(7, album);*/
            insertStmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    /*public static Song getSong()
    {

    }

    public static boolean deleteSong()
    {
        boolean deletedSong = false;
        //Logic to delete songs
        return deletedSong;
    }*/



}

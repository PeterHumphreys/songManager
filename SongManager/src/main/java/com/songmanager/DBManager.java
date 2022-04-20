package com.songmanager;

import java.sql.*;

public class DBManager
{
    //TODO: create a connection variable / establish connection when DBManager is created.
    private Connection connection;

    public DBManager(String url, String user, String pwd)
    {
        try
        {
            connection = DriverManager.getConnection(url, user, pwd);
        }
        catch(Exception ex)
        {
            System.out.println("Connection failed");
        }
    }
    //Creates a DB connection
   /* public static Connection getConnection()
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
    }*/

    //TODO: Check if tables are created. if not create them
    //Creates a table in the DB
    public void createTable() throws Exception {
        try
        {
            PreparedStatement create = connection.prepareStatement("CREATE OR REPLACE TABLE songsproject.songDetails(id int NOT NULL, SongTitle varchar(25), Artist char(25), Genre char(15), LengthOfSong varchar(4), PublishedYear varchar(4), Label varchar(20), Album varchar(20), PRIMARY KEY(SongTitle));");
            create.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {System.out.println("createTable function completed...");};
    }

    //TODO: Use correct way.
    //Adds a song to the db
    public void addSong(String title, String artist,  String genre, String songLength, String year, String recordLabel, String album)
    {
        try
        {
            int rowsInserted;
            System.out.println("Creating data...");
            try (PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO song(SongTitle, Artist, Genre, LengthOfSong, PublishedYear, Label, Album)
                    VALUES (?,?,?,?,?,?,?)""")) {
                statement.setString(1, title);
                statement.setString(2, artist);
                statement.setString(3, genre);
                statement.setString(4, songLength);
                statement.setString(5, year);
                statement.setString(6, recordLabel);
                statement.setString(7, album);

                rowsInserted = statement.executeUpdate();
            }
            System.out.println("Rows inserted: " + rowsInserted);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    //TODO: RETREIVE SONGS / ARTISTS / ANYTHING ELSE
    /*public static Song getSong()
    {

    }*/

//TODO: DELETE

    public void deleteSong(String songName)
    {
        try {
            System.out.println("Deleteing data...");
            int rowsDeleted;
            try (PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM song
                    WHERE songTitle LIKE ?
                    """)) {
                statement.setString(1, songName);
                rowsDeleted = statement.executeUpdate();
                System.out.println("Rows deleted: " + rowsDeleted);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }



}

package com.songmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class Song
{
    //Song data returned from db should be stored here.  Doesn't do anything yet.
    private String songID;
    private String songTitle;
    private String artist;
    private String genre;
    private int lengthOfSong;
    private Year yearReleased;
    private String label;
    private String album;

    public Song(String songID, String songTitle, String artist, String genre, int lengthOfSong, Year yearReleased, String label, String album)
    {
        this.songID = songID;
        this.songTitle = songTitle;
        this.artist = artist;
        this.genre = genre;
        this.lengthOfSong = lengthOfSong;
        this.yearReleased = yearReleased;
        this.label = label;
        this.album = album;
    }
}

package com.songmanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EditSongsController implements Initializable
{
    private DBManager dbManager;
    private String songToEdit;
    private String songTitleString;
    private String artistString;
    private String genreString;
    private String songLengthString;
    private String yearString;
    private String recordLabelString;
    private String albumString;

    //Song title
    @FXML private Label songTitleLabel;
    @FXML private TextField songTitle;

    //Artist info
    @FXML private Label artistNameLabel;
    @FXML private TextField artistName;

    //Album info
    @FXML private Label albumLabel;
    @FXML private TextField album;

    //Genre
    @FXML private Label genreLabel;
    @FXML private TextField genre;

    //Song length
    @FXML private Label songLengthLabel;
    @FXML private TextField hours;
    @FXML private TextField minutes;
    @FXML private TextField seconds;

    //Record label
    @FXML private Label recordLabelLabel;
    @FXML private TextField recordLabel;

    //Year released
    @FXML private Label yearReleasedLabel;
    @FXML private ComboBox yearReleased;

    //update song button
    @FXML private Button updateSongBtn;

    //Song List VBox
    //song list header
    @FXML private Label songListHeader;


    public void setSongToEdit(String songToEdit)
    {
        this.songToEdit = songToEdit;
        System.out.println(songToEdit);

        //TODO: Get all values from DB
        try{
            ResultSet songInfo = dbManager.getSong(songToEdit);
            if(songInfo.next()) {
                this.songTitle.setText(songInfo.getString("SongTitle"));
                this.artistName.setText(songInfo.getString("ArtistName"));
                this.album.setText(songInfo.getString("AlbumName"));
                this.genre.setText(songInfo.getString("GenreName"));
                this.recordLabel.setText(songInfo.getString("RecordLabelName"));
                //yearReleasesd
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.dbManager = new DBManager("jdbc:mariadb://localhost:3306/songsproject", "root", "root");

    }

    @FXML protected void updateSongBtnClick()
    {
        if (isValidInput())
        {
            Stage stage = (Stage) updateSongBtn.getScene().getWindow();
            // do what you have to do
            songTitleString = this.songTitle.getText();
            artistString = this.artistName.getText();
            genreString = this.genre.getText();
            //songLengthString = "00:01:12";
            songLengthString = this.hours.getText() + ":" + this.minutes.getText() + ":" + this.seconds.getText();
            yearString = "1999"; //this.yearReleased.getValue().toString()
            recordLabelString = this.recordLabel.getText();
            albumString = this.album.getText();
            dbManager.editSong(songToEdit, songTitleString, artistString, genreString, songLengthString, yearString, recordLabelString, albumString);
            stage.close();
        }
    }

    private boolean isValidInput()
    {
        boolean valid = true;
        //Check for empty values on columns with NOT NULL constraint
        if (this.songTitle != null && this.artistName != null && this.genre != null && yearReleased != null)
        {
            //Check for proper data types
            //songTitle
            if (this.songTitle.getText().length() > 25)
            {
                valid = false;
            }
            //artistID
            if (this.artistName.getText().length() > 25)
            {
                valid = false;
            }
            //genreName
            if (this.genre.getText().length() > 15)
            {
                valid = false;
            }
            //songLength
           /* try
            {
                Duration songLength = new Duration()
                if ()
            }
            //not a valid integer
            catch (Exception ex)
            {
                System.out.println("Please enter a valid length!");
            }*/
            //recordLabel
            if (this.recordLabel.getText().length() > 25)
            {
                valid = false;
            }
            //album
            if (this.album.getText().length() > 25)
            {
                valid = false;
            }
        }
        return valid;
    }
}

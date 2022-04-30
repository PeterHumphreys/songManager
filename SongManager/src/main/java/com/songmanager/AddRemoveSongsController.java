package com.songmanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.time.Year;
import java.util.Collection;
import java.util.ResourceBundle;

public class AddRemoveSongsController implements Initializable
{
    private DBManager dbManager;
    private String songTitleString;
    private String artistString;
    private String genreString;
    private String songLengthString;
    private String yearString;
    private String recordLabelString;
    private String albumString;

    //Overall Header
    @FXML private Label pageHeader;

    //Add song VBox
    //mainHeader
    @FXML private Label mainHeader;

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
    @FXML private Spinner hourSpinner;
    @FXML private Spinner minuteSpinner;
    @FXML private Spinner secondSpinner;

    //Record label
    @FXML private Label recordLabelLabel;
    @FXML private TextField recordLabel;

    //Year released
    @FXML private Label yearReleasedLabel;
    @FXML private ComboBox yearReleased;

    //User feedback message
    @FXML private Label feedbackLabel;

    //add song button
    @FXML private Button addSongBtn;

    //Song List VBox
    //song list header
    @FXML private Label songListHeader;

    //Remove Song Vbox
    //Remove Song ListView
    @FXML private ListView<String> removeSongList;

   public AddRemoveSongsController()
   {
       this.dbManager = new DBManager("jdbc:mariadb://localhost:3306/songsproject", "root", "root");
   }

   @FXML protected void AddSongBtnClick()
   {
       if (isValidInput())
       {
           this.songTitleString = this.songTitle.getText().toString();
           this.artistString = this.artistName.getText().toString();
           this.genreString = this.genre.getText().toString();
           //Default value for now
           this.songLengthString = "00:03:12";
           this.yearString = this.yearReleased.getValue().toString();
           this.recordLabelString = this.recordLabel.getText().toString();
           this.albumString = this.album.getText().toString();

           dbManager.addSong(songTitleString, artistString, genreString, songLengthString, yearString, recordLabelString, albumString);
           feedbackLabel.setText("Added song");
           updateLists();
       }
   }

   @FXML protected void RemoveSongBtnClick()
   {
        String songToRemove = removeSongList.getSelectionModel().getSelectedItem();
        dbManager.deleteSong(songToRemove);
        updateLists();
   }

   //Updates the ListViews on the page to correctly display the songs available
   private void updateLists()
   {
       ResultSet resultSet = dbManager.getSongs();
       try {
           removeSongList.getItems().clear();
           while (resultSet.next()) {
               String name = resultSet.getString(1);
               removeSongList.getItems().add(name);

           }
       }
       catch(Exception e)
       {
            System.out.println(e);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Add all years from start to current year to the yearReleased comboBox
        for (int year = Integer.valueOf(Year.now().toString()); year >= 1900 ; year--)
            this.yearReleased.getItems().add(year);
        updateLists();
        //this.songLengthSpinner.set
    }
}
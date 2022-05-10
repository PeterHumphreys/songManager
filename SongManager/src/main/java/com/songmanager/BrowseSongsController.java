package com.songmanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.ResultSet;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BrowseSongsController implements Initializable
{
    private DBManager dbManager;
    //Lists of criteria to filter by
    private ArrayList<String> artistList;
    private ArrayList<String> genreList;
    private ArrayList<String> recordLabelList;
    private ArrayList<String> albumList;
    //For UI, don't even remember how I made this work, but it's like magic.
    private HashMap<String, VBox> criteriaVBoxMap;

    public BrowseSongsController()
    {
        this.dbManager = new DBManager("jdbc:mariadb://localhost:3306/songsproject", "root", "root");
        this.artistList = new ArrayList<String>();
        this.albumList = new ArrayList<String>();
        this.genreList = new ArrayList<String>();
        this.recordLabelList = new ArrayList<String>();

        getCriteriaData();

        //For UI
        this.criteriaVBoxMap = new HashMap<String, VBox>();
    }

    public void populateComboBoxes()
    {
        for (String artist : this.artistList)
            artistComboBox.getItems().add(artist);

        for (String album : this.albumList)
            albumComboBox.getItems().add(album);

        for (String genre : this.genreList)
            genreComboBox.getItems().add(genre);

        for (String recordLabel : this.recordLabelList)
            recordLabelComboBox.getItems().add(recordLabel);

        this.criteriaComboBox.getItems().add("Artist");
        this.criteriaComboBox.getItems().add("Album");
        this.criteriaComboBox.getItems().add("Genre");
        this.criteriaComboBox.getItems().add("Record Label");

        this.criteriaComboBox.getItems().add("Year");
        this.criteriaComboBox.getItems().add("Length of Song");

        for (int year = Integer.valueOf(Year.now().toString()); year >= 1900 ; year--)
        {
            this.startYear.getItems().add(year);
            this.endYear.getItems().add(year);
        }
        //Populate songs list using DBManager
    }

    public void getCriteriaData()
    {
        //Get data from database
        ResultSet resultSet = dbManager.getAllArtistNames();
        //Get all artists
        try
        {
            while (resultSet.next())
            {
                String artist = resultSet.getString("ArtistName");
                this.artistList.add(artist);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        resultSet = dbManager.getAllAlbumNames();
        //Get all albums
        try
        {
            while (resultSet.next())
            {
                String album = resultSet.getString("AlbumName");
                this.albumList.add(album);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        resultSet = dbManager.getAllGenreNames();
        //Get all albums
        try
        {
            while (resultSet.next())
            {
                String genre = resultSet.getString("GenreName");
                this.genreList.add(genre);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        resultSet = dbManager.getAllRecordLabelNames();
        //Get all albums
        try
        {
            while (resultSet.next())
            {
                String recordLabel = resultSet.getString("RecordLabelName");
                this.recordLabelList.add(recordLabel);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    /*@FXML ListView artistCriteriaList;
    @FXML ListView genreCriteriaList;
    @FXML ListView yearCriteriaList;*/
    @FXML ListView songList;

    @FXML ComboBox genreComboBox;
    @FXML ComboBox artistComboBox;
    @FXML ComboBox albumComboBox;
    @FXML ComboBox recordLabelComboBox;
    @FXML ComboBox criteriaComboBox;
    @FXML ComboBox startYear;
    @FXML ComboBox endYear;

    @FXML Button filterSongsBtn;
    @FXML Button addArtistBtn;
    @FXML Button addGenreBtn;
    @FXML Button addYearBtn;
    @FXML Label errorMessage;

    @FXML VBox criteriaBox;

    @FXML VBox artistBox;
    @FXML VBox albumBox;
    @FXML VBox genreBox;
    @FXML VBox yearBox;
    @FXML VBox recordLabelBox;
    @FXML VBox lengthOfSongBox;

    @FXML ComboBox songLengthLowerLimit;
    @FXML ComboBox songLengthUpperLimit;

    @FXML Button selectCriteriaBtn;

    @FXML CheckBox yearCheckBox;
    @FXML CheckBox artistCheckBox;
    @FXML CheckBox albumCheckBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Remove all FXML criteria VBoxes and add to list
        int count = criteriaBox.getChildren().size();
        for (Node box : criteriaBox.getChildren())
        {
            System.out.println(((VBox)(box)).getId());
            criteriaVBoxMap.put( ((VBox)(box)).getId(), (VBox)(box));
        }
        for (int i =criteriaBox.getChildren().size() - 1; i>= 0; i--)
        {
            criteriaBox.getChildren().remove(i);

        }
        populateComboBoxes();
        /*criteriaBox.getChildren().remove(2);
        criteriaBox.getChildren().remove(1);
        criteriaBox.getChildren().remove(0);*/


    }

    @FXML protected void selectCriteriaBtnClick()
    {
        if (criteriaComboBox.getValue() != null)
        {
            if (criteriaComboBox.getValue().toString() == "Artist")
            {
                if (criteriaBox.getChildren().size() != 0)
                {
                    criteriaBox.getChildren().remove(0);
                }
                criteriaBox.getChildren().add(criteriaVBoxMap.get("artistBox"));
            }
            if (criteriaComboBox.getValue().toString() == "Album")
            {
                if (criteriaBox.getChildren().size() != 0)
                {
                    criteriaBox.getChildren().remove(0);
                }
                criteriaBox.getChildren().add(criteriaVBoxMap.get("albumBox"));
            }
            else if (criteriaComboBox.getValue().toString() == "Genre")
            {
                if (criteriaBox.getChildren().size() != 0)
                {
                    criteriaBox.getChildren().remove(0);
                }
                criteriaBox.getChildren().add(criteriaVBoxMap.get("genreBox"));
            }
            else if (criteriaComboBox.getValue().toString() == "Year")
            {
                if (criteriaBox.getChildren().size() != 0)
                {
                    criteriaBox.getChildren().remove(0);
                }
                criteriaBox.getChildren().add(criteriaVBoxMap.get("yearBox"));
            }
            else if (criteriaComboBox.getValue().toString() == "Record Label")
            {
                if (criteriaBox.getChildren().size() != 0)
                {
                    criteriaBox.getChildren().remove(0);
                }
                criteriaBox.getChildren().add(criteriaVBoxMap.get("recordLabelBox"));
            }
            else if (criteriaComboBox.getValue().toString() == "Length of Song")
            {
                if (criteriaBox.getChildren().size() != 0)
                {
                    criteriaBox.getChildren().remove(0);
                }
                criteriaBox.getChildren().add(criteriaVBoxMap.get("lengthOfSongBox"));
            }
        }
    }

    @FXML protected void selectArtistBtnClick()
    {
        if (this.artistComboBox.getValue() != null)
        {
            String artist = artistComboBox.getValue().toString();
            ResultSet resultSet = dbManager.getSongByArtist(artist);
            try
            {
                this.songList.getItems().clear();
                while (resultSet.next())
                {
                    String song = resultSet.getString("SongTitle");
                    System.out.println(song);
                    this.songList.getItems().add(song);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }

    @FXML protected void selectAlbumBtnClick()
    {
        if (this.albumComboBox.getValue() != null)
        {
            String album = albumComboBox.getValue().toString();
            ResultSet resultSet = dbManager.getSongByAlbum(album);
            //Get all albums
            try
            {
                this.songList.getItems().clear();
                while (resultSet.next())
                {
                    String song = resultSet.getString("SongTitle");
                    System.out.println(song);
                    this.songList.getItems().add(song);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }

    @FXML protected void selectGenreBtnClick()
    {
        if (this.genreComboBox.getValue() != null)
        {
            String genre = genreComboBox.getValue().toString();
            ResultSet resultSet = dbManager.getSongByGenre(genre);

            try
            {
                this.songList.getItems().clear();
                while (resultSet.next())
                {
                    String song = resultSet.getString("SongTitle");
                    System.out.println(song);
                    this.songList.getItems().add(song);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    @FXML protected void selectRecordLabelBtnClick()
    {
        if (this.recordLabelComboBox.getValue() != null)
        {
            String recordLabel = recordLabelComboBox.getValue().toString();
            ResultSet resultSet = dbManager.getSongByRecordLabel(recordLabel);

            try
            {
                this.songList.getItems().clear();
                while (resultSet.next())
                {
                    String song = resultSet.getString("SongTitle");
                    System.out.println(song);
                    this.songList.getItems().add(song);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    @FXML protected void selectLengthBtnClick()
    {
        if (this.songLengthLowerLimit.getValue() != null && this.songLengthUpperLimit.getValue() != null)
        {
            String minLength = songLengthLowerLimit.getValue().toString();
            String maxLength = songLengthUpperLimit.getValue().toString();

            ResultSet resultSet = dbManager.getSongByLength(minLength, maxLength);
            try
            {
                this.songList.getItems().clear();
                while (resultSet.next())
                {
                    String song = resultSet.getString("SongTitle");
                    System.out.println(song);
                    this.songList.getItems().add(song);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    @FXML protected void selectYearBtnClick()
    {
        if (this.genreComboBox.getValue() != null)
        {
            String startYear = this.startYear.getValue().toString();
            String endYear = this.endYear.getValue().toString();

            ResultSet resultSet = dbManager.getSongByYear(startYear, endYear);
            try
            {
                this.songList.getItems().clear();
                while (resultSet.next())
                {
                    String song = resultSet.getString("SongTitle");
                    System.out.println(song);
                    this.songList.getItems().add(song);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }

}

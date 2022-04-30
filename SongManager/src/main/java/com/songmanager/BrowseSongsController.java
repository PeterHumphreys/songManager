package com.songmanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BrowseSongsController implements Initializable
{
    private ArrayList<String> artistList;
    private ArrayList<String> genreList;
    private HashMap<String, VBox> criteriaVBoxMap;

    public BrowseSongsController()
    {
        this.artistList = new ArrayList<String>();
        this.genreList = new ArrayList<String>();
        this.criteriaVBoxMap = new HashMap<String, VBox>();
    }

    /*@FXML ListView artistCriteriaList;
    @FXML ListView genreCriteriaList;
    @FXML ListView yearCriteriaList;*/
    @FXML ListView songList;

    @FXML ComboBox genreComboBox;
    @FXML ComboBox artistComboBox;
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
        /*criteriaBox.getChildren().remove(2);
        criteriaBox.getChildren().remove(1);
        criteriaBox.getChildren().remove(0);*/

        genreComboBox.getItems().add("Heavy Metal");
        genreComboBox.getItems().add("Hip Hop");
        genreComboBox.getItems().add("Jazz");

        artistComboBox.getItems().add("artist1");
        artistComboBox.getItems().add("artist2");
        artistComboBox.getItems().add("artist3");

        this.criteriaComboBox.getItems().add("Artist");
        this.criteriaComboBox.getItems().add("Genre");
        this.criteriaComboBox.getItems().add("Year");
        this.criteriaComboBox.getItems().add("Record Label");
        this.criteriaComboBox.getItems().add("Length of Song");

        for (int year = Integer.valueOf(Year.now().toString()); year >= 1900 ; year--)
        {
            this.startYear.getItems().add(year);
            this.endYear.getItems().add(year);
        }
        //Populate songs list using DBManager
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

    @FXML protected void addArtistBtnClick()
    {
        /*if (this.artistComboBox.getValue() != null)
        {
            String artist = artistComboBox.getValue().toString();
            //artist not selected, add to list
            if (!this.artistList.contains(artist))
            {
                artistList.add(artist);
                artistCriteriaList.getItems().add(artist);
            }
            //artist already selected
            else
            {
                errorMessage.setText(artist + " already selected!");
            }
        }*/
    }
    @FXML protected void addGenreBtnClick()
    {
        /*if (this.genreComboBox.getValue() != null)
        {
            String genre = genreComboBox.getValue().toString();
            //genre not selected, add to list
            if (!this.genreList.contains(genre))
            {
                genreList.add(genre);
                genreCriteriaList.getItems().add(genre);
            }
            //artist already selected
            else
            {
                errorMessage.setText(genre + " already selected!");
            }
        }*/

    }

}

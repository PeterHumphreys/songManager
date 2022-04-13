package com.songmanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BrowseSongsController implements Initializable
{
    @FXML Label songListHeader;
    @FXML ComboBox songList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Populate songs list using DBManager
    }
}

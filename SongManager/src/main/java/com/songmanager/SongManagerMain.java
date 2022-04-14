package com.songmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SongManagerMain extends Application {
    private SongManagerController controller;

    public SongManagerMain()
    {
        this.controller = new SongManagerController();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        //FXMLLoader fxmlLoader = new FXMLLoader(SongManagerMain.class.getResource("AddRemoveSongsView.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("HomePageView.fxml"));
        Scene scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.setTitle("Song Manager");
        stage.show();
    }
        /*
        //FXMLLoader fxmlLoader = new FXMLLoader(SongManagerMain.class.getResource("AddRemoveSongsView.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("AddRemoveSongsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        stage.setTitle("Song Manager");
        stage.setScene(scene);
        stage.show();*/
    public static void main(String[] args) throws Exception {launch();}
}
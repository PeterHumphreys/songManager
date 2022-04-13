package com.songmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
        FXMLLoader fxmlLoader = new FXMLLoader(SongManagerMain.class.getResource("AddRemoveSongsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {launch();}
}
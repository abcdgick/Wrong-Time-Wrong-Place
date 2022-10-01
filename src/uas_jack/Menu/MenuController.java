/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_jack.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import uas_jack.Main;
import static uas_jack.Main.player;

/**
 *
 * @author USER
 */
public class MenuController implements Initializable {
    
    @FXML
    private Button start, load, option, extras, quit, back, reset;
    
    @FXML
    private ImageView startImage, loadImage, optionImage, extrasImage, 
            quitImage,titleImage, backImage, volumeImage, resetImage, 
            grid00, grid10, grid20, grid01, grid11, grid21, grid12, 
            grid1, grid2;
    
    
    
    @FXML
    private MediaView mediaView;
    
    @FXML
    private AnchorPane menuPane;
    
    @FXML 
    private Slider volumeSlider;
    
    @FXML
    private Label volumeLabel;
    
    @FXML
    private GridPane badEnd, goodEnd;
    
    private Stage disStage;
    private Scene disScene;
    private Parent disRoot;
    private Media music;
    private MediaPlayer mediaPlayer;
    
    private int pil = 0;
    
    Stage stage;
    
    @FXML
    private void start(ActionEvent event) throws IOException{
        System.out.println("Start");
        
        mediaPlayer.stop();
        
        //Masalahnya terdapat di mouse click, kalau pake on action bisa
        //tbh ketika lu udah bener2 cape nyari error, lu nga ada tenaga buat marah karena
        //kesalahan goblok, udah cape
        
        ganti(event, "/uas_jack/Prolog/Prolog.fxml", "/uas_jack/Prolog/Font.css");
    }
    
    @FXML
    public void load(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Load");
        alert.setHeaderText("Are you sure you want to Load from a save file?");
        alert.setContentText("All unsaved progress will be lost!");
        
        if(alert.showAndWait().get() == ButtonType.OK){
            player.loadGame();
            mediaPlayer.stop();
            switch(player.getScene()) {
                case 0: ganti (event, "/uas_jack/Prolog/Prolog.fxml");
                        break;
                case 1: ganti (event, "/uas_jack/Pagi/Pagi.fxml");
                        break;
                case 2: ganti (event, "/uas_jack/Freedom/Freedom.fxml");
                        break;
                case 3: ganti (event, "/uas_jack/Tru/Tru.fxml");
                        break;
            }
        }
    }
    
    private void ganti (ActionEvent event, String s) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        disRoot = loader.load();
        
        disStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        disScene = new Scene(disRoot);
        
        disStage.setScene(disScene);
        disStage.show();
    }
    
    private void ganti (ActionEvent event, String s, String c) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        disRoot = loader.load();
        
        disStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        disScene = new Scene(disRoot);
        
        String css = this.getClass().getResource(c).toExternalForm();
        disScene.getStylesheets().add(css);
        
        disStage.setScene(disScene);
        disStage.show();
    }
    
    @FXML
    private void option(){
        System.out.println("Option");
        hide1();
        showOption();
    }
    
    @FXML
    private void extras(){
        System.out.println("Extras");
        hide1();
        showExtras();
    }
    
    @FXML
    private void quit(ActionEvent event) {
        System.out.println("Quit");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You're going to quit the game");
        alert.setContentText("All unsaved progress will be lost!");
        
        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) menuPane.getScene().getWindow();
            System.out.println("Come Again!");
            stage.close();
        }
    }
    
    @FXML
    private void reset(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset Progress");
        alert.setHeaderText("Are you sure to reset all of your progress?");
        alert.setContentText("This action is unreversible");
        
        if(alert.showAndWait().get() == ButtonType.OK){
            try{
                File save = new File ("Save.txt");
                save.delete();
                
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Reset Successful");
                alert1.setHeaderText("Your Progress has been reset");
                alert1.setContentText("Enjoy the game!");
        
                if(alert1.showAndWait().get() == ButtonType.OK){
                    System.out.println("Pop up REEEEE");
                }
                
            } catch(Exception e){
                System.out.println("Failed to reset progress");
            }
            System.out.println("Reset");
        }
    }
    
    @FXML 
    private void back(){
        hide2();
        
        switch (pil) {
            case 1:
                hideOption();
                break;
            case 2:
                hideExtras();
                break;
                
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        justPlayAudio();
        volume();
    }    
    
    private void justPlayAudio(){
        music = new Media(getClass().getResource("Music.mp4").toExternalForm()); 
        
        mediaPlayer = new MediaPlayer(music);
        
        mediaPlayer.setAutoPlay(true);
        //mediaPlayer.play();
        mediaView.setMediaPlayer(mediaPlayer);

        //KENAPA PAKE MEDIAVIEW?? KARENA JAVA 1.8 ADA BUG JADI NGGA BISA PLAY AUDIO
        //HAHA JADI DIAKALIN PAKE MEDIAVIEW TAPI OPACITY NYA 0
        //ORACLE PLS FIX
    }
    
    private void hide1(){
        start.setDisable(true);
        load.setDisable(true);
        option.setDisable(true);
        extras.setDisable(true);
        quit.setDisable(true);
        
        startImage.setOpacity(0);
        loadImage.setOpacity(0);
        optionImage.setOpacity(0);
        extrasImage.setOpacity(0);
        quitImage.setOpacity(0);
        //titleImage.setOpacity(0);
        
        back.setDisable(false);
        back.setLayoutX(38);
        back.setLayoutY(666);
        
        backImage.setOpacity(1);
        backImage.setLayoutX(38);
        backImage.setLayoutY(666);
    }
    
    private void hide2(){
        start.setDisable(false);
        load.setDisable(false);
        option.setDisable(false);
        extras.setDisable(false);
        quit.setDisable(false);
        
        startImage.setOpacity(1);
        loadImage.setOpacity(1);
        optionImage.setOpacity(1);
        extrasImage.setOpacity(1);
        quitImage.setOpacity(1);
        //titleImage.setOpacity(1);
        
        back.setDisable(true);
        back.setLayoutX(0);
        back.setLayoutY(0);
        
        backImage.setOpacity(0);
        backImage.setLayoutX(0);
        backImage.setLayoutY(0);
        
        
    }
    
    private void showOption(){
        volumeImage.setOpacity(1);
        volumeImage.setLayoutX(40);
        volumeImage.setLayoutY(250);
        
        resetImage.setOpacity(1);
        resetImage.setLayoutX(38);
        resetImage.setLayoutY(550);
        resetImage.setDisable(false);
        
        reset.setDisable(false);
        reset.setLayoutX(49);
        reset.setLayoutY(556);
        
        volumeSlider.setDisable(false);
        volumeSlider.setLayoutX(49);
        volumeSlider.setLayoutY(329);
        volumeSlider.setOpacity(1);
        
        volumeLabel.setDisable(false);
        volumeLabel.setLayoutX(431);
        volumeLabel.setLayoutY(329);
        volumeLabel.setOpacity(1);
        
        
        pil = 1;
    }
    
    private void showExtras(){
        badEnd.setLayoutX(25);
        badEnd.setLayoutY(120);
        badEnd.setVisible(true);
        
        boolean[] bad = player.getBad();
        
        boolean[] good = player.getGood();
        
        if (bad[0]) grid00.setVisible(true);
        if (bad[1]) grid10.setVisible(true);
        if (bad[2]) grid20.setVisible(true);
        if (bad[3]) grid01.setVisible(true);
        if (bad[4]) grid11.setVisible(true);
        if (bad[5]) grid21.setVisible(true);
        System.out.println(bad[6]);
        if (bad[6]) grid12.setVisible(true);
        
        if (good[0]) grid1.setVisible(true);
        if (good[1]) grid2.setVisible(true);
        
        pil = 2;
    }
    
    private void hideOption(){
        volumeImage.setOpacity(0);
        volumeImage.setLayoutX(0);
        volumeImage.setLayoutY(0);
        
        resetImage.setOpacity(0);
        resetImage.setLayoutX(0);
        resetImage.setLayoutY(0);
        
        reset.setDisable(true);
        reset.setLayoutX(0);
        reset.setLayoutY(0);
        
        volumeSlider.setOpacity(0);
        volumeSlider.setDisable(true);
        volumeSlider.setLayoutX(0);
        volumeSlider.setLayoutY(0);
        
        volumeLabel.setOpacity(0);
        volumeLabel.setDisable(false);
        volumeLabel.setLayoutX(0);
        volumeLabel.setLayoutY(0);

        pil = 0;
    }
    
    private void hideExtras(){
        badEnd.setLayoutX(0);
        badEnd.setLayoutY(0);
        badEnd.setVisible(false);
        
        grid00.setVisible(false);
        grid10.setVisible(false);
        grid20.setVisible(false);
        grid01.setVisible(false);
        grid11.setVisible(false);
        grid21.setVisible(false);
        grid12.setVisible(false);
        grid1.setVisible(false);
        grid2.setVisible(false);
    }
    
    private void volume(){
        Main.volume = volumeSlider.getValue()/100.0;
        volumeLabel.setText(Integer.toString((int) (Main.volume * 100.0)));
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Main.volume = volumeSlider.getValue()/100;
                volumeLabel.setText(Integer.toString((int)(Main.volume * 100.0)));
                mediaPlayer.setVolume(Main.volume);
            }
        
        
    
        });
        
    }
}

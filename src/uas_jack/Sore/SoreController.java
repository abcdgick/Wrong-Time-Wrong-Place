/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_jack.Sore;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import uas_jack.Main;
import static uas_jack.Main.peta;
import static uas_jack.Main.player;
import static uas_jack.Main.sekop;
import static uas_jack.Main.senter;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class SoreController implements Initializable {

    @FXML
    private AnchorPane sorePane;
    
    @FXML
    private Button next, save4, load4, title4, quit4, 
            cerita, noCerita, switchh, tryAgain;
    
    @FXML
    private Label dialog;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private MediaView mediaView;
    
    private Stage disStage;
    private Scene disScene;
    private Parent disRoot;
    private Media music;
    private MediaPlayer mediaPlayer;
    
    ArrayList<String> text = new ArrayList<>();
    
    int dis = 0, end;
    Random rand = new Random();
    
    Stage stage;
    
    @FXML
    public void save(){
        player.saveGame();
        popUp();
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
    
    @FXML
    public void title(ActionEvent event) throws IOException{
        System.out.println("Title");
        
        
        //Masalahnya terdapat di mouse click, kalau pake on action bisa
        //tbh ketika lu udah bener2 cape nyari error, lu nga ada tenaga buat marah karena
        //kesalahan goblok, udah cape
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Title");
        alert.setHeaderText("You're going to the game title");
        alert.setContentText("All unsaved progress will be lost!");
        
        if(alert.showAndWait().get() == ButtonType.OK){
            tryAgain(event);
        }
    }
    
    @FXML
    private void quit(ActionEvent event) {
        System.out.println("Quit");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You're going to quit the game");
        alert.setContentText("All unsaved progress will be lost!");
        
        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) sorePane.getScene().getWindow();
            System.out.println("Come Again!");
            stage.close();
        }
    }
    
    @FXML
    public void switchScene(ActionEvent event) throws IOException{
        mediaPlayer.stop();
        
        //Masalahnya terdapat di mouse click, kalau pake on action bisa
        //tbh ketika lu udah bener2 cape nyari error, lu nga ada tenaga buat marah karena
        //kesalahan goblok, udah cape
        
        ganti(event, "/uas_jack/Malam/Malam.fxml");
    }
    
    @FXML
    public void tryAgain (ActionEvent event) throws IOException{
        mediaPlayer.stop();
        player.reset();
        ganti(event, "/uas_jack/Menu/Menu.fxml", "/uas_jack/Menu/Style.css");
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setStyle();
       setDialog();
       nextDialog();
       justPlayAudio();
    }

    
    public void setStyle(){
        sorePane.setStyle("-fx-background-color:  #88ac93;");
        
        dialog.setStyle("-fx-font-size: 24;"
            +"-fx-font-family: 'Lucida Console';"
            +"-fx-text-fill: #117529;"
            +"-fx-background-color: #74e08e;");
        
        //have to set the 2 of this
        
        
    }
    
    public void setDialog(){
        text.add("Sore hari adalah waktu dimana tahan" 
                +" diberikan kesempatan untuk bekerja" 
                +" sebagai bentuk pemasyarakatan yang" 
                +" dilakukan di dalam penjara. Jika tahanan" 
                +" melakukan kerja dengan baik, maka mereka" 
                +" bisa mendapatkan upah");
        
        text.add("Apa yang Ryn lakukan?");
        
        text.add("Ryn bekerja dengan baik sehingga ia bisa mendapatkan" 
                +" upah dari pekerjaanya dipenjara.");
        
        text.add("Ia berpikir bahwa" 
                +" dengan mengumpulkan uang dipenjara, ia bisa" 
                +" menggunakan uang itu untuk menyogok sipir bahkan" 
                +" membeli keperluan yang ia butuhkan diluar penjara nanti.");
        
        text.add("Ryn merasa malas untuk bekerja karena ia berpikir bahwa tidak " 
                +" ada gunanya untuk bekerja dipenjara. Ia fokus pad tujuannya" 
                +" untuk keluar dari penjara dan menolong orang lain walaupun ia" 
                +" tidak mendapatkan ucapan terimakasih sama sekali. Ryn bisa" 
                +" mengumpulkan suatu alat yang ia sembunyikan di tempat ia" 
                +" bekerja dan ketika ia kembali ke selnya ia menyembunyikan alat" 
                +" tersebut di pakaiannya.");
        
        /* text.add("Pencarian Ryan ternyata membuahkan hasil bla bla bla"); " 
           text.add("Sayangnya Ryn pada hari itu tidak menemukan apa2" );
        */
        
        end = text.size();
        
        //have to set all of this
    }
    
    public void nextDialog(){
        dialog.setText(text.get(dis));
        
        dis++;
        
        switch(dis) {
            case 2: cabang();
                    break;
            case 3: pindahImage("src/uas_jack/Sore/2.jpg");
                    break;
            case 4: pindahImage("src/uas_jack/Sore/3.jpg");
                    break;
            case 5: rng();
                    break;
            //This switch case too
        }
        if (dis >= end) {
            next.setDisable(true);
            /* if (end == text.size()) {
                tryAgain.setVisible(true);
            } */
            //else 
            switchh.setVisible(true);
        }
        
    }
    
    public void cabang(){
        cerita.setDisable(false);
        cerita.setOpacity(1.00);
        
        noCerita.setDisable(false);
        noCerita.setOpacity(1.00);
        
        next.setDisable(true);
    }
    
    public void kerja(){
        cerita.setDisable(true);
        cerita.setOpacity(0.0);
        
        noCerita.setDisable(true);
        noCerita.setOpacity(0.0);
        
        next.setDisable(false);
        
        end -= 1;
        nextDialog();

    }
    
    public void nyari(){
        cerita.setDisable(true);
        cerita.setOpacity(0.0);
        
        noCerita.setDisable(true);
        noCerita.setOpacity(0.0);
        
        next.setDisable(false);
        
        dis = 4;
        //end -= 1;
        nextDialog();
                
    }
    
    public void rng(){
        switch(rand.nextInt(3)){
            case 0: if (!peta.getHave())
                    pindahImage("src/uas_jack/Sore/2_1.jpg");
                    peta.setHave(true);
                    break;
            case 1: if (!senter.getHave())
                    pindahImage("src/uas_jack/Sore/2_2.png");
                    senter.setHave(true);
                    break;
            case 2: if (!sekop.getHave()) 
                    pindahImage("src/uas_jack/Sore/2_3.png");
                    sekop.setHave(true);
                    break;
            default:pindahImage("src/uas_jack/Sore/2.jpg");
        }
    }
    
    
    public void pindahImage(String dis){
        File file = new File(dis);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
    
    private void justPlayAudio(){
        music = new Media(getClass().getResource("Sore.mp4").toExternalForm()); 
        
        mediaPlayer = new MediaPlayer(music);
        
        mediaPlayer.setVolume(0.2 * Main.volume);
        mediaPlayer.setAutoPlay(true);
        //mediaPlayer.play();
        mediaView.setMediaPlayer(mediaPlayer);

        //KENAPA PAKE MEDIAVIEW?? KARENA JAVA 1.8 ADA BUG JADI NGGA BISA PLAY AUDIO
        //HAHA JADI DIAKALIN PAKE MEDIAVIEW TAPI OPACITY NYA 0
        //ORACLE PLS FIX
    }
    
    /*
    private void pindahAudio(){
        mediaPlayer.stop();
        music = new Media(getClass().getResource("notSantuy.mp4").toExternalForm()); 
        
        mediaPlayer = new MediaPlayer(music);
        
        mediaPlayer.setVolume(0.2 * Main.volume);
        mediaPlayer.setAutoPlay(true);
        //mediaPlayer.play();
        mediaView.setMediaPlayer(mediaPlayer);
    }    
    */
    private void popUp(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save");
        alert.setHeaderText("Your Progress has been saved");
        alert.setContentText("Enjoy the game!");
        
        if(alert.showAndWait().get() == ButtonType.OK){
            System.out.println("Pop up REEEEE");
        }
    }
}

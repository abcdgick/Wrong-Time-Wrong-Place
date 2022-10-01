/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_jack.Pagi;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import static uas_jack.Main.player;
import uas_jack.NPC;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class PagiController implements Initializable {

    @FXML
    private AnchorPane pagiPane;
    
    @FXML
    private Button next, save1, load1, title1, quit1, 
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
    
    int dis = 0, end, pls;
    boolean telat = false, conver = false;
    String[] wall;
    
    Stage stage;
    
    NPC today;
    
    
    
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
            stage = (Stage) pagiPane.getScene().getWindow();
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
        
        ganti(event, "/uas_jack/Siang/Siang.fxml");
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
       justPlayAudio();
       setStyle();
       setDialog();
       nextDialog();
    }

    
    public void setStyle(){
        pagiPane.setStyle("-fx-background-color:  #658887;");
        
        dialog.setStyle("-fx-font-size: 24;"
            +"-fx-font-family: 'Lucida Console';"
            +"-fx-text-fill: #57fff9;"
            +"-fx-background-color: #398885;");
        
        //have to set the 2 of this
        
        
    }
    
    public void setDialog(){
        text.add("Ryn masuk ke dalam penjara dan pada pagi hari itu, Ryn terbeban, ia bingung apa yang harus ia lakukan. Ia " 
                + "berpikir dan mempertimbangkan apa yang harus ia lakukan selama 7 hari, karena target pembunuh yang akan " 
                + "membunuh orang dalam daftar tersebut akan dilakukan setelah hari setelah 7 hari (hari ke - 8)");
        
        text.add("Bicara dengan sipir?");
        
        text.add("Ryn tidak kuat menahan beban pikirannya" 
                + " sendirian, sehingga ia berniat untuk bercerita" 
                + " kepada sipir tentang masalah tersebut dan" 
                + " berharap bisa keluar dengan cara yang baik. Sipir" 
                + " yang kepadanya Ryn bercerita cuek dan tidak" 
                + " peduli pada Ryn dan tetap melakukan " 
                + " kewajibannya. Walaupun tidak didengar oleh" 
                + " sipir, Ryn tetap meminta bantuan sipir untuk kabur" 
                + " dari penjara dan menolong orang lain.");
        
        text.add("Ryn menjadi kesal dan putus asa"
                +" sehingga dia memutuskan untuk tidak" 
                +" bercerita kepada sipir karena ia pikir" 
                +" percuma ia bercerita, ia berpikir ia" 
                +" harus melakukan semuanya sendirian.");
        
        text.add("Ryn terlalu lama berpikir dan mempertimbangkan apakah ia harus menolong" 
                +" orang yang akan dibunuh atau tidak sampai-sampai sudah lewat 7 hari" 
                +" setelah ia dipenjara. Dua orang sipir yang berada di depan sel dimana Ryn" 
                +" ditahan sedang membaca koran dan melihat orang terbunuh secara sadisnya." 
                +" Ryn meminta untuk melihat koran tersebut kepada sipir dan ternyata orang" 
                +" yang dibunuh itu adalah orang yang seharusnya ia tolong.");
        
        end = text.size();
        
        //have to set all of this
    }
    
    public void nextDialog(){
        dialog.setText(text.get(dis));
        
        dis++;
        
        switch(dis) {
            case 2: cabang();
                    break;
            case 3: pindahImage("src/uas_jack/Pagi/2.jpg");
                    setConver();
                    break;
            case 4: pindahImage("src/uas_jack/Pagi/3.jpg");
                    break;
            //This switch case too
        }
        
        
        if (player.getDay() >= 7 && !telat){
            pindahImage("src/uas_jack/Pagi/0.png");
            telat();
            player.setBad(2);
        }
        
        //while(conver) conver();
        
        if (dis >= end) {
            end();
        }

        
    }
    
    public void end(){
        next.setDisable(true);
            if (end == text.size()) {
                tryAgain.setVisible(true);
            }
            else switchh.setVisible(true);
    }
    
    public void cabang(){
        cerita.setDisable(false);
        cerita.setOpacity(1.00);
        
        noCerita.setDisable(false);
        noCerita.setOpacity(1.00);
        
        next.setDisable(true);
    }
    
    public void cerita(){
        cerita.setDisable(true);
        cerita.setOpacity(0.0);
        
        noCerita.setDisable(true);
        noCerita.setOpacity(0.0);
        
        next.setDisable(false);
        
        end -= 2;
        nextDialog();

    }
    
    public void noCerita(){
        cerita.setDisable(true);
        cerita.setOpacity(0.0);
        
        noCerita.setDisable(true);
        noCerita.setOpacity(0.0);
        
        next.setDisable(false);
        
        dis = 3;
        end -= 1;
        nextDialog();
                
    }
    
    public void telat(){
        cerita.setDisable(true);
        cerita.setOpacity(0.0);
        
        noCerita.setDisable(true);
        noCerita.setOpacity(0.0);
        
        next.setDisable(false);
        
        dis = end - 1;
        telat = true;
        pindahAudio();
        nextDialog();
    }
    
    public void setConver(){
        pls = 0;
        next.setOnAction(e -> conver());
        end += 1;
        switch(player.getDay()){
            case 1: today = Main.pBaik;
                    break;
            case 2: today = Main.pJahad;
                    break;
            case 3: today = Main.pBaik;
                    break;
            case 4: today = Main.pJahad;
                    break;
            case 5: today = Main.pBaik;
                    break;
            default: today = null;
        }
        wall = today.getDialog(today.getDekat());
        
        //dialog.setText(wall[pls]);
        //pls++;
        System.out.println(pls);
    }
    
    public void conver(){
        if (pls <= wall.length - 2){
            dialog.setText(wall[pls]);
            pls++;
            System.out.println(pls);
        } else {
            dialog.setText(wall[pls]);
            today.progress();
            end -=1;
            end();
        }
    }
    
    
    public void pindahImage(String dis){
        File file = new File(dis);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
    
    private void justPlayAudio(){
        music = new Media(getClass().getResource("Pagi.mp4").toExternalForm()); 
        
        mediaPlayer = new MediaPlayer(music);
        
        mediaPlayer.setVolume(0.2 * Main.volume);
        mediaPlayer.setAutoPlay(true);
        //mediaPlayer.play();
        mediaView.setMediaPlayer(mediaPlayer);

        //KENAPA PAKE MEDIAVIEW?? KARENA JAVA 1.8 ADA BUG JADI NGGA BISA PLAY AUDIO
        //HAHA JADI DIAKALIN PAKE MEDIAVIEW TAPI OPACITY NYA 0
        //ORACLE PLS FIX
    }
    
    
    private void pindahAudio(){
        mediaPlayer.stop();
        music = new Media(getClass().getResource("Sad.mp4").toExternalForm()); 
        
        mediaPlayer = new MediaPlayer(music);
        
        mediaPlayer.setVolume(0.2 * Main.volume);
        mediaPlayer.setAutoPlay(true);
        //mediaPlayer.play();
        mediaView.setMediaPlayer(mediaPlayer);
    }    
    
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

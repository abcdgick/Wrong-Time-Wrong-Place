/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_jack.Prolog;

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
/**
 * FXML Controller class
 *
 * @author USER
 */
public class PrologController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane prologPane;
    
    @FXML
    private Button next, help, cuek, save1, load1, title1, quit1, 
            switchh, tryAgain;
    
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
    boolean badEnd = false;
    
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
            stage = (Stage) prologPane.getScene().getWindow();
            System.out.println("Come Again!");
            stage.close();
        }
    }
    
    @FXML
    public void switchScene(ActionEvent event) throws IOException{
        mediaPlayer.stop();
        player.progress();
        //Masalahnya terdapat di mouse click, kalau pake on action bisa
        //tbh ketika lu udah bener2 cape nyari error, lu nga ada tenaga buat marah karena
        //kesalahan goblok, udah cape
        
        ganti(event, "/uas_jack/Pagi/Pagi.fxml");
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
        prologPane.setStyle("-fx-background-color:  #dff4f9;");
        
        dialog.setStyle("-fx-font-size: 24;"
            +"-fx-font-family: 'Lucida Console';"
            +"-fx-text-fill: #003dfe;"
            +"-fx-background-color: #89e5fa;");
        
        
    }
    
    public void setDialog(){
        text.add("Ryn adalah seorang pria yang baik. Ia seorang yang memiliki ilmu "
                + "bela diri yang hebat. Selain itu, Ryn juga "
                + "memiliki kemampuan yang luar biasa dalam menembak.");
        
        text.add("Suatu ketika ia berjalan di tengah keramaian alun- alun "
                + "kota sambil menikmati keramaian kota yang hangat. Saat bersamaan "
                + "ketika ia berjalan, ia melihat seseorang pria "
                + "misterius yang membawa belati yang sangat tajam yang dapat membunuh "
                + "orang lain.");
        
        text.add("Ryn sangat terkejut dan ia mengeluarkan pistol kesayangannya. "
                + "Firasat Ryn ternyata benar! Pria misterius tersebut terus menerus "
                + "membuntuti seorang wanita dan berniat membunuhnya");
        
        text.add("Apa yang akan Ryn lakukan? ");
        
        text.add("Ketika pria misterius tersebut menyiapkan Belati dan mendekati wanita "
                + "tersebut untuk menikamnya. Ryan dengan sigap mulai membidik");
        
        text.add("Ryn dengan refleks menembak jantung pria "
                + "tersebut, dengan demikian pria misterius itu mati di tempat tepat di "
                + "belakang wanita yang akan dibunuh. Wanita tersebut sangat kaget dan "
                + "wanita itu menatap Ryn kemudian lari terbirit-birit"); 
        
        text.add("Polisi berdatangan "
                + "dan mengarahkan senapan ke arah Ryn yang terang-terangan telah membunuh "
                + "pria misterius tersebut");
        
        text.add("Ryn tertangkap dan dimasukkan ke dalam penjara. Ryn tersangka membunuh "
                + "dan divonis hukuman penjara seumur hidup walapun ia telah bercerita bahwa "
                + "Ryn membunuh pria misterius demi melindung nyawa wanita yang akan dibunuh pria misterius itu. ");
        
        text.add("Mirisnya, wanita yang ia lindungi malah memberikan kesaksian yang"
                + "memberatkan Ryn dan Ryn yang seharusnya mendapat keringanan atas "
                + "hukumannya, ia tidak mendapatkannya sama sekali. ");
        
        text.add("Ryn sangat sedih dan kecewa karena walaupun ia berbuat hal yang benar, malah membuahkan "
                + "malapetaka kepadanya dan harus dipenjara seumur hidup. Sebelum Ryn tertangkap dan masuk ke penjara, ia "
                + "sempat mengambil buku si pria misterius tersebut. Ryn sangat kaget, ternyata buku kecil tersebut adalah "
                + "beberapa target yang telah dibunuh dan target yang akan dibunuh.");
        
        text.add("Walaupun Ryn melihat gelagat pria tersebut Ryn merasa ia tidak harus peduli "
                + "pada wanita tersebut, Ryn hanya peduli pada dirinya sendiri dan tidak mau "
                + "terseret ke dalam masalah karena ia sendiri juga sudah terlalu lelah "
                + "dengan banyak masalah yang terjadi di hidupnya.");
        
        end = text.size();
    }
    
    public void nextDialog(){
        dialog.setText(text.get(dis));
        
        dis++;
        switch(dis) {
            case 2: pindahImage("src/uas_jack/Prolog/2.jpg");
                    break;
            case 3: pindahAudio();
                    break;
            case 4: cabang();
                    break;
            case 5: pindahImage("src/uas_jack/Prolog/3.jpg");
                    break;
            case 6: pindahImage("src/uas_jack/Prolog/4.png");
                    break;
            case 7: pindahImage("src/uas_jack/Prolog/5.jpg");
                    break;
            case 8: pindahImage("src/uas_jack/Prolog/6.jpg");
                    break;
            case 10: pindahImage("src/uas_jack/Prolog/7.jpg");
                    break;     
            case 11: pindahImage("src/uas_jack/Prolog/8.jpg");
                    player.setBad(0);
                    break;
        }
        if (dis >= end) {
            next.setDisable(true);
            if (badEnd) {
                tryAgain.setVisible(true);
            }
            else switchh.setVisible(true);
        }
        
    }
    
    public void cabang(){
        help.setDisable(false);
        help.setOpacity(1.00);
        
        cuek.setDisable(false);
        cuek.setOpacity(1.00);
        
        next.setDisable(true);
    }
    
    public void bantu(){
        help.setDisable(true);
        help.setOpacity(0.0);
        
        cuek.setDisable(true);
        cuek.setOpacity(0.0);
        
        next.setDisable(false);
        
        end -= 1;
        nextDialog();

    }
    
    public void cuek(){
        help.setDisable(true);
        help.setOpacity(0.0);
        
        cuek.setDisable(true);
        cuek.setOpacity(0.0);
        
        next.setDisable(false);
        
        dis = end - 1;
        badEnd = true;
        nextDialog();
                
    }
    
    
    public void pindahImage(String dis){
        File file = new File(dis);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
    
    private void justPlayAudio(){
        music = new Media(getClass().getResource("Santuy.mp4").toExternalForm()); 
        
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
        music = new Media(getClass().getResource("notSantuy.mp4").toExternalForm()); 
        
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_jack.Tru;

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
public class TruController implements Initializable {

    @FXML
    private AnchorPane truPane;
    
    @FXML
    private Button next, save7, load7, title7, quit7, 
            bertindak, cape, eye, talk,
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
    Stage stage;
    
    ArrayList<String> text = new ArrayList<>();
    
    int dis = 0, end;
    boolean badEnd = false;
    
    NPC today;
    int pls;
    boolean conver = false;
    String[] wall;
    
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
            stage = (Stage) truPane.getScene().getWindow();
            System.out.println("Come Again!");
            stage.close();
        }
    }
    
    @FXML
    public void switchScene(ActionEvent event) throws IOException{
        mediaPlayer.stop();
        
        ganti(event, "/uas_jack/Menu/Menu.fxml", "/uas_jack/Menu/Style.css");
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
        truPane.setStyle("-fx-background-color:  #a2a2a2;");
        
        dialog.setStyle("-fx-font-size: 24;"
            +"-fx-font-family: 'Lucida Console';"
            +"-fx-text-fill: #f7f7f7;"
            +"-fx-background-color: #1b1b1b;");
        
        //have to set the 2 of this
        
        
    }
    
    public void setDialog(){
        text.add("Para pembunuh yang menargetkan para korban di buku merah "
                + "adalah suruhan dari Murle, sang penjahat utama. Murle "
                + "adalah sorang yang suka dengan membunuh orang. Bisa "
                + "dibilang, dia seorang psikopat. Tapi dia sendiri tidak mau "
                + "mengotori tangannya sendiri.");
        
        text.add("Murle benci kepada orang yang beragama “Tru” karena orang "
                + "jahat selalu dikecam oleh orang yang beragama “Tru” atas "
                + "setiap tindakan yang ia lakukan");
        
        
        text.add("Walaupun ditegur dan dikecam, Murle tidak meninggalkan perbuatan "
                + "jahatnya. Malahan menjadi semakin bengis dan ia hendak melenyapkan "
                + "orang beragama “Tru”. Itulah mengapa alasan Murle mau mempekerjakan "
                + "sang ilmuan. Akan tetapi pada faktanya sang ilmuan kabur melarikan diri. ");
        
        text.add("Kembali lagi pada Ryn. Sang ilmuan memberitahukan ciri-ciri dari "
                + "pembunuh yang akan membunuhnya. Singkat cerita Ryn berhasil untuk "
                + "membunuh sang pembunuh secara diam-diam dan tanpa diketahui oleh "
                + "public. Hal itu dia lakukan supaya nyawa sang ilmuan selamat untuk"
                + " sementara.");
        
        text.add("Dengan membunuh si pembunuh, Ryn bisa mengetahui keberadaan si Murle, "
                + "sang penjahat utama.walaupun ia mengetahuinya, Ryn tetap bergumul. "
                + "Apakah ia harus membunuh orang yang ingin melenyapkan Murle, orang "
                + "beragam “Tru”, atau diam dan membiarkan hal tersebut terjadi");
        
        
        text.add("Apa yang akan Ryn lakukan?");
        
        //bertindak
        text.add("Ryn merasa sangat kesal dan berikthiar untuk membunuh Murle. "
                + "Ryn pun mulai menelusuri dimana posisi murle berdiam."
                + " Ryn pergi menuju ke kediaman Murle.");
        
        text.add("Dalam perjalanan menuju kediaman Murle, Ryn tidak "
                + "senggaja menyenggol ibu-ibu dan barang belanjaan "
                + "ibu tersebut. Ryn meminta maaf dan membantu ibu. ");
        
        text.add("Melalui dialog yang telah dilakukan, Ryn merasa bingung. "
                + "Di satu sisi Ryn berpikir bahwa Murle adalah orang jahat "
                + "yang melalui kejahatan yang telah dilakukannya patut untuk "
                + "mendapat kematian. Di sisi lain Ryn melihat bahwa setiap orang "
                + "bisa berubah menjadi orang yang lebih baik lagi. Apa yang "
                + "sebaiknya Ryn lakukan terhadap Murle?");
        
        //9/10
        text.add("Apa yang sebaiknya Ryn lakukan terhadap Murle?");
        
        //Nego
        text.add("Ryn berpikir bahwa Murle akan bertobat dari perbuatannya "
                + "yang jahat bila ditegur secara langsung oleh Ibunya yang "
                + "selama ini tidak mengetahui perbuatan anaknya yang bejat itu.");
        
        text.add("Singkat cerita Ibu Murle menegur Murle dengan belinang "
                + "air mata. Dengan melihat ibunya yang ia sayangi, "
                + "Murle pun mulai meneteskan air matanya dan mengakui "
                + "setiap kesalahan dan kejahatan yang ia lakukan kepada "
                + "orang lain. ");
        
        text.add("Melalui teguran ibunya, Murle berubah menjadi orang "
                + "yang lebih baik lagi dan berubah dari perbuatan "
                + "jahatnya yang selama ini dikecam oleh pemeluk agama Tru. "
                + "Murle bersedia untuk masuk penjara dan menerima hukuman "
                + "yang pantas dari apa yang ia perbuat selama ini.");
        
        text.add("Ryn juga mendapatkan kebebasan dari penjara tempat "
                + "ia melarikan diri. Ryn juga mendapat penghargaan "
                + "dan permintaan maaf dari pejabat setempat karena "
                + "hukum yang kurang adil dan tidak objektif. ");
        
        text.add("Ryn mendapat hikmah, yaitu melalui perbuatan yang ia lakukan, "
                + "dahulu dengan menolong orang lain dan dipenjara dengan tidak "
                + "adil, ia dapat menyelamatkan lebih banyak orang benar. Mulai "
                + "saat itu Ryn tidak akan ragu untuk menolong orang walaupun ada "
                + "resiko yang besar yang mungkin akan Ryn hadapi");
        
        
        //Kill
        text.add("Walaupun Ibu Murle telah bercerita kepada Ryn, Ryn tetap saja "
                + "menganggap Murle merupakan suatu ancaman besar yang tidak bisa "
                + "dibiarkan. Ryn berpikir bahwa Murle tidak akan berubah sekalipun "
                + "Murle ditegur oleh Ryn ataupun ditegur oleh Ibunya.");
        
        text.add("Ryn tetap membunuh Murle. Dengan demikian Ryn menyelamatkan "
                + "orang-orang yang tidak bersalah yang menjadi terget Murle. "
                + "Dengan kesaksian dari ilmuwan yang Ryn tolong, ");
        
        text.add("Ryn dinyatakan bebas dari penjara tempat Ryn kabur. "
                + "Ryn juga mendapat penghargaan dan permintaan maaf "
                + "dari pejabat setempat karena hukum yang kurang adil "
                + "dan tidak objektif.");
        
        text.add("Namun di sisi lain, Ibu Murle tidak bisa menerima akan "
                + "kematian anaknya. Ibu Murle juga tidak menyalahkan Ryn atas "
                + "kematian Murle karena memang Murle lah yang jahat dan pantas "
                + "menerimanya. Oleh karena kesedihan Ibu Murle yang mendalam "
                + "karena kehilangan anaknya, Ibu Murle menjadi terganggu jiwanya "
                + "dan dirawat di rumah sakit jiwa");
        
        
        //cape
        text.add("Ryn merasa lelah dengan hidupnya ini, sehingga ia memutuskan untuk "
                + "pulang saja kerumahnya dan hidup seperti biasa lagi dan membiarkan "
                + "pemeluk agama “Tru” terbunuh oleh Murle dengan cara lain.");
        
        
        
        end = text.size();
        
        //have to set all of this
    }
    
    public void nextDialog(){
        dialog.setText(text.get(dis));
        
        dis++;
        
        switch(dis) {
            case 2: pindahImage("src/uas_jack/Tru/2.png");
                    break;
            case 3: pindahImage("src/uas_jack/Tru/3.png");
                    break;
            case 4: pindahImage("src/uas_jack/Tru/4.png");
                    pindahAudio("Bertindak.mp4");
                    break;
            case 5: pindahImage("src/uas_jack/Tru/5.png");
                    break;
            case 6: cabang1();
                    break;
            case 7: pindahImage("src/uas_jack/Tru/7-1.png");
                    break;
            case 8: pindahImage("src/uas_jack/Tru/7-2.png");
                    setConver(0);
                    break;
            case 9: pindahImage("src/uas_jack/Tru/7-5.png");
                    break;
            case 10:cabang2();
                    break;
            case 11:pindahImage("src/uas_jack/Tru/8-1.png");
                    pindahAudio("Pacifist.mp4");
                    setConver(1);
                    break;
            case 12:pindahImage("src/uas_jack/Tru/8-3.png");
                    break;
            case 13:pindahImage("src/uas_jack/Tru/8-4.png");
                    break;
            case 14:pindahImage("src/uas_jack/Tru/8-5.png");
                    break;
            case 15:pindahImage("src/uas_jack/Tru/8-6.png");
                    player.setGood(0);
                    break;
            case 16:pindahImage("src/uas_jack/Tru/9-1.png");
                    pindahAudio("Vigilante.mp4");
                    break;
            case 17:pindahImage("src/uas_jack/Tru/9-2.png");
                    break;
            case 18:pindahImage("src/uas_jack/Tru/8-5.png");
                    break;
            case 19:pindahImage("src/uas_jack/Tru/9-3.png");
                    player.setGood(1);
                    break;
            case 20:pindahImage("src/uas_jack/Tru/6.png");
                    player.setBad(6);
                    badEnd = true;
                    break;        
                    
            //This switch case too
        }
        if (dis >= end) {
            end();
        }
        
    }
    
    public void end(){
        next.setDisable(true);
            if (badEnd) {
                tryAgain.setVisible(true);
            }
            else 
            switchh.setVisible(true);
    }
    
    public void cabang1(){
        bertindak.setDisable(false);
        bertindak.setVisible(true);
        
        cape.setDisable(false);
        cape.setVisible(true);
        
        next.setDisable(true);
    }
    
    public void cabang2(){
        eye.setDisable(false);
        eye.setVisible(true);
        
        talk.setDisable(false);
        talk.setVisible(true);
        
        next.setDisable(true);
    }
    
    
    public void bertindak(){
        bertindak.setDisable(true);
        bertindak.setVisible(false);
        
        cape.setDisable(true);
        cape.setVisible(false);
        
        next.setDisable(false);
        
        end -=1;
        nextDialog();

    }
    
    public void cape(){
        bertindak.setDisable(true);
        bertindak.setVisible(false);
        
        cape.setDisable(true);
        cape.setVisible(false);

        next.setDisable(false);
        
        
        dis = 19;
        end -= 1;
        nextDialog();
                
    }
    
    public void eye(){
        eye.setDisable(true);
        eye.setVisible(false);
        
        talk.setDisable(true);
        talk.setVisible(false);
        
        next.setDisable(false);
        
        dis = 15;
        
        nextDialog();

    }
    
    public void talk(){
        eye.setDisable(true);
        eye.setVisible(false);
        
        talk.setDisable(true);
        talk.setVisible(false);

        next.setDisable(false);
        
        
        end -= 4;
        nextDialog();
                
    }
    
    public void setConver(int f){
        pls = 0;
        next.setOnAction(e -> conver());
        
        today = Main.ibu;
        
        wall = today.getDialog(f);
        //dialog.setText(wall[pls]);
        //pls++;
        System.out.println(pls);
    }
    
    public void conver(){
        if (pls <= wall.length - 2){
            dialog.setText(wall[pls]);
            pls++;
            System.out.println(wall.length);
        } else {
            dialog.setText(wall[pls]);
            next.setOnAction(e -> nextDialog());
        }
        
        if (wall.length == 15) {
            if (pls == 1) pindahImage("src/uas_jack/Tru/7-3.png");
            if (pls == 9) pindahImage("src/uas_jack/Tru/7-4.png");
        } else if (wall.length == 8) {
            pindahImage("src/uas_jack/Tru/8-2.png");
        }
        
    }
    
    public void pindahImage(String dis){
        File file = new File(dis);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
    
    private void justPlayAudio(){
        music = new Media(getClass().getResource("Tru.mp4").toExternalForm()); 
        
        mediaPlayer = new MediaPlayer(music);
        
        mediaPlayer.setVolume(0.2 * Main.volume);
        mediaPlayer.setAutoPlay(true);
        //mediaPlayer.play();
        mediaView.setMediaPlayer(mediaPlayer);

        //KENAPA PAKE MEDIAVIEW?? KARENA JAVA 1.8 ADA BUG JADI NGGA BISA PLAY AUDIO
        //HAHA JADI DIAKALIN PAKE MEDIAVIEW TAPI OPACITY NYA 0
        //ORACLE PLS FIX
    }
    
    
    private void pindahAudio(String dis){
        mediaPlayer.stop();
        music = new Media(getClass().getResource(dis).toExternalForm()); 
        
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

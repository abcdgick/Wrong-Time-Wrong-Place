/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_jack.Malam;

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
import static uas_jack.Main.pBaik;
import static uas_jack.Main.pJahad;
import static uas_jack.Main.peta;
import static uas_jack.Main.player;
import static uas_jack.Main.sekop;
import static uas_jack.Main.senter;
import static uas_jack.Main.tBaik;
import static uas_jack.Main.tJahad;
import uas_jack.NPC;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MalamController implements Initializable {

    @FXML
    private AnchorPane malamPane;
    
    @FXML
    private Button next, save5, load5, title5, quit5, 
            gass, lain, barbar, alat, dibantuT, dibantuP, batal,
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
    ArrayList<NPC> talk = new ArrayList<>();
    
    int dis = 0, end;
    boolean badEnd = false, bebas = false;
    
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
            stage = (Stage) malamPane.getScene().getWindow();
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
        if (bebas) {
            player.progress();
            ganti(event, "/uas_jack/Freedom/Freedom.fxml");
        } 
        else{
            player.nexDay();
            System.out.println(player.getDay());
            ganti(event, "/uas_jack/Pagi/Pagi.fxml");
        }
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
       cek();
    }

    
    public void setStyle(){
        malamPane.setStyle("-fx-background-color:  #222432;");
        
        dialog.setStyle("-fx-font-size: 24;"
            +"-fx-font-family: 'Lucida Console';"
            +"-fx-text-fill: #031784;"
            +"-fx-background-color: #9597a2;");
        
        //have to set the 2 of this
        
        
    }
    
    public void setDialog(){
        text.add("Malam hari adalah waktu dimana para tahanan beistirahat untuk kembali "
                + "melakukan kegiatan lagi di esok harinya. Selain itu malam hari adalah "
                + "waktu yang tepat bagi para tahanan untuk melarikan diri, sebab "
                + "kurangnya pengawasan dari penjaga dan pencahayaan yang minim secara "
                + "tidak langsung mendukung untuk kabur menyebabkan naiknya kesempatan "
                + "para narapidana untuk dapat lolos dari penjara tersebut. ");
        
        text.add("Sebelum tidur, sipir di penjara tersebut mematikan lampu di "
                + "setiap sel narapidana, dengan tujuan mengurangi anggaran "
                + "biaya listrik di penjara tersebut.");
        
        text.add("Coba Kabur?");
        
        //Lain kali
        text.add("Ryn merasa hari tersebut ia belum siap untuk kabur dari penjara "
                + "dan menyelamatkan orang yang terdapat pada daftar tersebut. "
                + "Selain itu Ryn juga merasa lelah karena telah melakukan banyak "
                + "kegiatan di sore hari. Ia memutuskan untuk tidur dan menyiapkan "
                + "diri, sambil menunggu kesempatan yang baik untuk melarikan diri");
        
        
        text.add("Bagaimana cara kaburnya? ");
        
        //barbar
        text.add("Ryn sangat tidak kuat mengingat hukumannya selama seumur hidup penjara, "
                + "sedangkan orang lain di dalam ancaman bahaya. Tanpa pikir panjang, "
                + "sebelum masuk ke sel dan tidur malam, Ryn menerobos para penjaga. "
                + "Ia melewati penjaga satu demi satu sampai akhirnya ada penjaga "
                + "yang mengeluarkan senapan dan mengenai kakinya. Ryn gagal untuk "
                + "kabur dari penjara dan dimasukkan ke penjara dengan keamanan maksimal");
        
        //pake alat
        text.add("Ryn telah mengumpulkan beberapa alat di selnya yang ia ambil selama "
                + "bekerja di sore hari. Sekarang ia siap untuk kabur. Ia mendapatkan "
                + "sebuah arahan bahwa dibawah sel nya, terdapat ruang yang bisa "
                + "digunakan untuk melarikan diri. Sehingga Ryn memilih untuk kabur "
                + "lewat bawah tanah menggunakan sekop yang dia temukan untuk menggali tanah.");
        
        //gali tanah sendiri
        text.add("Sesampainya di bawah tanah, Ryn langsung berlari kencang mencari "
                + "jalan keluar menggunakan arahan yang ada");
        
        //penjaga
        text.add("Karena Ryn sudah memiliki hubungan yang dekat dengan sang sipir, "
                + "dan sipir itu mau membantu Ryn, maka sang sipir pun memberitahunya "
                + "bagaimana cara untuk kabur. Ia diberikan beberapa alat yang "
                + "dibutuhkan untuk kabur. Lalu Ryn pun mulai menggali tanah untuk bisa "
                + "sampai di sebuah tempat.");
        
        //gali tanah sendiri
        text.add("Sesampainya di bawah tanah, Ryn langsung berlari kencang mencari "
                + "jalan keluar menggunakan arahan yang ada");
        
        //sama tahanan
        text.add("Ryn sudah memiliki hubungan yang dekat dengan teman satu sel nya. "
                + "Sehingga mereka berencana untuk kabur bersama-sama. "
                + "Sebenarnya temannya ini sudah memiliki niatan untuk kabur dan "
                + "dia menyembunyikan sebuah alat untuk menggali tanah. "
                + "Dan akhirnya mereka berdua pun memulai aksi untuk kabur dari sel tersebut. "
                + "Karna Teman satu sel Ryn ini baik, maka dia yang menggali tanahnya.");
        
        //gali tanah bersama
        text.add("Sesampainya di bawah tanah, Ryn dan temannya ini langsung berlari "
                + "kencang mencari jalan keluar menggunakan arahan yang ada. "
                + "Dan mereka pun berpencar supaya tidak ketahuan oleh polisi "
                + "yang sedang berjaga");
        
        
        
        end = text.size();
        
        //have to set all of this
    }
    
    public void nextDialog(){
        dialog.setText(text.get(dis));
        
        dis++;
        
        switch(dis) {
            case 2: pindahImage("src/uas_jack/Malam/2.png");
                    break;
            case 3: cabang1();
                    break;
            case 4: pindahImage("src/uas_jack/Malam/3.png");
                    break;
            case 5: cabang2();
                    break;
            case 6: pindahImage("src/uas_jack/Malam/4.png");
                    player.setBad(1);
                    break;
            case 7: pindahImage("src/uas_jack/Malam/5a.png");
                    break;
            case 8: pindahImage("src/uas_jack/Malam/5b.png");
                    break;
            case 9: pindahImage("src/uas_jack/Malam/6.png");
                    setConver(1);
                    break;
            case 10: pindahImage("src/uas_jack/Malam/5b.png");
                    break;
            case 11: pindahImage("src/uas_jack/Malam/7a.png");
                    setConver(2);
                    break;
            case 12: pindahImage("src/uas_jack/Malam/7b.png");
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
    
    public void cek(){
        if (pBaik.kabur() || pJahad.kabur()) dibantuP.setDisable(false);
        if (tBaik.kabur()|| tJahad.kabur()) dibantuT.setDisable(false);
        if (peta.getHave() && senter.getHave() && sekop.getHave()) alat.setDisable(false);
    }
    
    public void cabang1(){
        gass.setDisable(false);
        gass.setVisible(true);
        
        lain.setDisable(false);
        lain.setVisible(true);
        
        next.setDisable(true);
    }
    
    public void cabang2(){
        barbar.setDisable(false);
        barbar.setVisible(true);
        
        //change the three of these
        //alat.setDisable(false);
        alat.setVisible(true);
        
        //dibantuP.setDisable(false);
        dibantuP.setVisible(true);
        
        //dibantuT.setDisable(false);
        dibantuT.setVisible(true);
        
        batal.setDisable(false);
        batal.setVisible(true);
        
        next.setDisable(true);
    }
    
    public void gas(){
        gass.setDisable(true);
        gass.setVisible(false);
        
        lain.setDisable(true);
        lain.setVisible(false);
        
        next.setDisable(false);
        
        dis = 4;
        
        nextDialog();

    }
    
    public void lain(){
        gass.setDisable(true);
        gass.setVisible(false);
        
        lain.setDisable(true);
        lain.setVisible(false);
        
        next.setDisable(false);
        
        
        end = 4;
        nextDialog();
                
    }
    
    public void barbar(){
        barbar.setDisable(true);
        barbar.setVisible(false);
        
        alat.setDisable(true);
        alat.setVisible(false);
        
        dibantuP.setDisable(true);
        dibantuP.setVisible(false);
        
        dibantuT.setDisable(true);
        dibantuT.setVisible(false);
        
        batal.setDisable(true);
        batal.setVisible(false);
        
        next.setDisable(false);
        badEnd = true;
        
        end -= 6;
        nextDialog();
    }
    
    public void alat(){
        barbar.setDisable(true);
        barbar.setVisible(false);
        
        alat.setDisable(true);
        alat.setVisible(false);
        
        dibantuP.setDisable(true);
        dibantuP.setVisible(false);
        
        dibantuT.setDisable(true);
        dibantuT.setVisible(false);
        
        batal.setDisable(true);
        batal.setVisible(false);
        
        next.setDisable(false);
        
        dis = 6;
        end -=4;
        bebas = true;
        nextDialog();
    }
    
    public void dibantuP(){
        barbar.setDisable(true);
        barbar.setVisible(false);
        
        alat.setDisable(true);
        alat.setVisible(false);
        
        dibantuP.setDisable(true);
        dibantuP.setVisible(false);
        
        dibantuT.setDisable(true);
        dibantuT.setVisible(false);
        
        batal.setDisable(true);
        batal.setVisible(false);
        
        next.setDisable(false);
        
        dis = 8;
        end -= 2;
        bebas = true;
        nextDialog();
    }
    
    public void dibantuT(){
        barbar.setDisable(true);
        barbar.setVisible(false);
        
        alat.setDisable(true);
        alat.setVisible(false);
        
        dibantuP.setDisable(true);
        dibantuP.setVisible(false);
        
        dibantuT.setDisable(true);
        dibantuT.setVisible(false);
        
        batal.setDisable(true);
        batal.setVisible(false);
        
        next.setDisable(false);
        dis = 10;
        bebas = true;
        
        nextDialog();
    }
    
    public void batal(){
        barbar.setDisable(true);
        barbar.setVisible(false);
        
        alat.setDisable(true);
        alat.setVisible(false);
        
        dibantuP.setDisable(true);
        dibantuP.setVisible(false);
        
        dibantuT.setDisable(true);
        dibantuT.setVisible(false);
        
        batal.setDisable(true);
        batal.setVisible(false);
        
        next.setDisable(false);
        dis = 3;
        end = 4;

        nextDialog();
    }
    
    
    //Need fix: sehabis conver set ke nextDialog lagi
    public void setConver(int f){
        pls = 0;
        next.setOnAction(e -> conver());
        switch(f){
            case 1: today = Main.pBaik;   
                    break;
            case 2: today = Main.tBaik;
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
            next.setOnAction(e -> nextDialog());
        }
    }
    
    public void pindahImage(String dis){
        File file = new File(dis);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
    
    private void justPlayAudio(){
        music = new Media(getClass().getResource("Malam.mp4").toExternalForm()); 
        
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

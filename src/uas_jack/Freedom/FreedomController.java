/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_jack.Freedom;

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
public class FreedomController implements Initializable {

    @FXML
    private AnchorPane freedomPane;
    
    @FXML
    private Button next, save6, load6, title6, quit6, 
            pertama, kedua, cewek, samperin, no,
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
            stage = (Stage) freedomPane.getScene().getWindow();
            System.out.println("Come Again!");
            stage.close();
        }
    }
    
    @FXML
    public void switchScene(ActionEvent event) throws IOException{
        mediaPlayer.stop();
        
        player.progress();
        ganti(event, "/uas_jack/Tru/Tru.fxml");
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
        freedomPane.setStyle("-fx-background-color:  #a6f1f0;");
        
        dialog.setStyle("-fx-font-size: 24;"
            +"-fx-font-family: 'Lucida Console';"
            +"-fx-text-fill: #146ce8;"
            +"-fx-background-color: #ccf0f5;");
        
        //have to set the 2 of this
        
        
    }
    
    public void setDialog(){
        text.add("Akhirnya, Ryn pun bebas dari Penjara. Dia pulang dulu "
                + "kerumahnya untuk mengganti baju dan mengubah "
                + "penampilannya supaya tidak ketahuan oleh para polisi. "
                + "Kemudian dia mulai mengeluarkan buku catatan sang "
                + "penjahat serta mulai memilih manakah orang yang harus "
                + "dia tolong pertama-tama");
        
        text.add("What Now?");
        
        
        //Orang Pertama
        text.add("Ryn memutuskan untuk menolong orang pertama. "
                + "Saat ia sampai ketempat orang pertama, Ryn "
                + "memaparkan semuanya dan menjelaskan kepada "
                + "orang pertama tersebur situasi dan kondisi yang "
                + "terjadi. Orang pertama tersebut curiga pada Ryn "
                + "dan mengizinkan ia tinggal di rumahnya.");
        
        
        text.add("Pada malam itu Ryn dibunuh oleh orang pertama. "
                + "Orang pertama tersebut adalah orang jahat yang "
                + "seharusnya memang dibunuh oleh pembunuh bayaran "
                + "tersebut karena orang pertama suka membunuh orang "
                + "lain tanpa sebab. Orang ini suka membunuh karena "
                + "ia korban dari keluarga yang kurang harmonis, "
                + "terutama karena ayahnya berlaku jahat. ");
        
        text.add("Sehingga ia tumbuh menjadi seseorang yang jahat dan tidak "
                + "segan untuk membunuh orang lain. Hal tersebut terlihat "
                + "ketika ia berkonflik dengan orang lain, musuh-musuhnya "
                + "menghlang begitu saja. Jejak kriminalnya pun ditutupi "
                + "dan oleh kakanya yang adalah seorang hakim yang serong, "
                + "membiarkan perbuatan adiknya, bahkan ketika adiknya "
                + "tertangkap membunuh dapat dibebaskan dengan liciknya.");
        
        //cewek
        text.add("Ryn memutuskan untuk menolong wanita yang pernah ditolongnya. "
                + "Saat ia sampai ketempat wanita yang pernah ditolongnya. "
                + "Tanpa pikir panjang, Ryn menghampiri wanita tersebut dengan "
                + "niat untuk memaparkan semuanya.");
        
        text.add("Belum sampai ke wanita tersebut, wanita tersebut berteriak "
                + "“pembunuh! Ada pembunuh!” sambil mengacungkan tangannya ke arah "
                + "Ryn. Ryn ditangkap dan dipukuli warga di daerah tersebut dan "
                + "Ryn masuk kembali ke tahanan dengan keamanan maksimal");
        
        
        //Orang Kedua
        text.add("Ryn memutuskan untuk membantu orang kedua. Ryn menyiapkan "
                + "diri dengan membeli berbagai senjata seperti sniper dan "
                + "pistol untuk menggantikan pistol kesayangannya. Ketika "
                + "dalam perjalanan untuk menghampiri orang kedua, Ryn "
                + "berpikir apakah dia harus menceritakan semuanya atau "
                + "dia harus melindungi orang kedua tersebut dengan sniper "
                + "yang telah ia beli.");
        
        text.add("Kemudian Ryn mencari si orang kedua ini. "
                + "Dan akhirnya dia menemukannya. Apa yang harus Ryn "
                + "lakukan sekarang?  Memberitahukannya atau tetap memantaunya, "
                + "menjaga dari kejauhan?");
        
        //10
        text.add("What to do?");
        
        //Samperin
        text.add("Ryn datang kepada orang kedua dan ia menceritakan semuanya mulai "
                + "dari ia menyelamatkan wanita yang ia selamatkan, hingga sebab ia "
                + "dipenjara. Orang kedua tersebut percaya kepada Ryn karena ia "
                + "begitu gigih akan keinginannya menyelamatkan orang lain. ");
        
        text.add("Karena orang kedua ini adalah orang yang baik, maka ia "
                + "menceritakan tentang dirinya pada Ryn. Tenyata, dia "
                + "adalah seorang ilmuan cantik nan muda yang sangat pintar "
                + "dalam dunia sains. Dia menyadari bahwa nyawanya ini memang "
                + "sedang tidak aman karena bakatnya ini.");
        
        text.add("Dia dipaksa oleh penjahat utama untuk membuat bakteri yang dapat "
                + "menginfeksi orang-orang tertentu. Bakteri tersebut akan mengenali "
                + "gen dari sang inang."); 
        
        text.add("Kemudian jika target tersebut adalah golongan yang diincar maka "
                + "bakteri tersebut akan melakukan reproduksi yang luar biasa cepat "
                + "yang pada akhirnya menjadi sangat banyak dan inang akan keracunan "
                + "karena racun yang dihasilkan dari metabolisme bakteri yang sangat banyak.");
        
        text.add("Orang yang telah terinfeksi tidak mungkin terselamatkan, karena "
                + "konsentrasi racun yang dihasilkan akan sangat banyak sehingga sel "
                + "tubuh korban akan mati.");
        
        text.add("Mengetahui hal tersebut, sang ilmuwan tidak mau bekerja untuk "
                + "si penjahat utama. Dia sudah berhasil lolos dan kabur dari "
                + "genggaman sang penjahat hingga saat ini ia menyamar sebagai "
                + "seorang mahasiswi. ");
        
        text.add("Tapi sayangnya, sang penjahat utama sudah mengetahui penyamarannya. "
                + "Sehingga, penjahat utama menargetkannya sebagai orang yang harus di musnahkan");
        
        //No
        text.add("Ryn menjaga orang kedua tersebut hingga akhirnya benar bahwa ada "
                + "pembunuh lain yang masuk ke rumah orang kedua sambil membawa pistol.");
        
        text.add("Melihat hal tersebut Ryn langsung mengarahkan snipernya ke arah pembunuh "
                + "yang datang itu dan langsung menembak matinya. Orang kedua dan warga yang "
                + "ada disitu kaget dan menangkap Ryn. Ryn kembali dipenjara dan Ryn divonis "
                + "hukuman mati.");
        
        
        
        end = text.size();
        
        //have to set all of this
    }
    
    public void nextDialog(){
        dialog.setText(text.get(dis));
        
        dis++;
        
        switch(dis) {
            case 2: cabang1();
                    break;
            case 3: pindahImage("src/uas_jack/Freedom/1a.png");
                    pindahAudio();
                    break;
            case 4: pindahImage("src/uas_jack/Freedom/1b.png");
                    player.setBad(3);
                    badEnd = true;
                    break;
            case 6: pindahImage("src/uas_jack/Freedom/3a.png");
                    break;
            case 7: pindahImage("src/uas_jack/Freedom/3b.png");
                    badEnd = true;
                    player.setBad(4);
                    break;
            case 8: pindahImage("src/uas_jack/Freedom/2-a.png");
                    break;
            case 9: pindahImage("src/uas_jack/Freedom/2-b.png");
                    break;
            case 10:cabang2();
                    break;
            case 11: pindahImage("src/uas_jack/Freedom/2-c1.png");
                    break;
            case 12: pindahImage("src/uas_jack/Freedom/2-c2.png");
                    break;
            case 13: pindahImage("src/uas_jack/Freedom/2-c3.png");
                    break;
            case 15: pindahImage("src/uas_jack/Freedom/2-c4.png");
                    break;
            case 16: pindahImage("src/uas_jack/Freedom/2-c5.png");
                    break;
            case 18: pindahImage("src/uas_jack/Freedom/2-d1.png");
                    break;
            case 19: pindahImage("src/uas_jack/Freedom/2-d2.png");
                    player.setBad(5);
                    break;
            //This switch case too
        }
        if (dis >= end) {
            next.setDisable(true);
            if (badEnd) {
                tryAgain.setVisible(true);
            }
            else 
            switchh.setVisible(true);
        }
        
    }
    
    public void cabang1(){
        pertama.setDisable(false);
        pertama.setVisible(true);
        
        kedua.setDisable(false);
        kedua.setVisible(true);
        
        cewek.setDisable(false);
        cewek.setVisible(true);
        
        next.setDisable(true);
    }
    
    public void cabang2(){
        samperin.setDisable(false);
        samperin.setVisible(true);
        
        //change the three of these
        no.setDisable(false);
        no.setVisible(true);
        
        next.setDisable(true);
    }
    
    public void pertama(){
        pertama.setDisable(true);
        pertama.setVisible(false);
        
        kedua.setDisable(true);
        kedua.setVisible(false);
        
        cewek.setDisable(true);
        cewek.setVisible(false);
        
        next.setDisable(false);
        
        end = 5;
        badEnd = true;
        
        nextDialog();

    }
    
    public void kedua(){
        pertama.setDisable(true);
        pertama.setVisible(false);
        
        kedua.setDisable(true);
        kedua.setVisible(false);
        
        cewek.setDisable(true);
        cewek.setVisible(false);
        
        next.setDisable(false);
        
        
        dis = 7;
        nextDialog();
                
    }
    
    public void cewek(){
        pertama.setDisable(true);
        pertama.setVisible(false);
        
        kedua.setDisable(true);
        kedua.setVisible(false);
        
        cewek.setDisable(true);
        cewek.setVisible(false);
        
        next.setDisable(false);
        
        next.setDisable(false);
        badEnd = true;
        
        dis = 5;
        end -= 12;
        nextDialog();
    }
    
    public void samper(){
        samperin.setDisable(true);
        samperin.setVisible(false);
        
        no.setDisable(true);
        no.setVisible(false);
        
        
        next.setDisable(false);
        end -= 2;
        
        nextDialog();
    }
    
    public void no(){
        samperin.setDisable(true);
        samperin.setVisible(false);
        
        no.setDisable(true);
        no.setVisible(false);
        
        next.setDisable(false);
        
        dis = 17;
        badEnd = true;
        nextDialog();
    }
    
    
    
    
    public void pindahImage(String dis){
        File file = new File(dis);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
    
    private void justPlayAudio(){
        music = new Media(getClass().getResource("Freedom.mp4").toExternalForm()); 
        
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
        music = new Media(getClass().getResource("Mokad.mp4").toExternalForm()); 
        
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

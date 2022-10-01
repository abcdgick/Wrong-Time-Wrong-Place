/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_jack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */

public class Main extends Application {
    
    public static double volume;
    public static int deadline = 7;
    public static NPC pBaik, pJahad, tBaik, tJahad, orang1, orang2,
            cewek, hakim, jahad, ibu;
    public static Inventory peta, senter, sekop;
    public static Player player;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Menu/Menu.fxml"));
        
        Scene scene = new Scene(root);

        String css = this.getClass().getResource("Menu/Style.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            quit(stage);
        });
        
        setUp();
        
    }
    
    private void quit(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You're going to quit the game");
        alert.setContentText("All unsaved progress will be lost!");
        
        if(alert.showAndWait().get() == ButtonType.OK){
            System.out.println("Come Again!");
            stage.close();
        }
    }

    public void setUp(){
                
        pBaik = new Penjaga("Andi", (byte) 11, (byte) 0, 5);
        pJahad = new Penjaga("Budi", (byte)12, (byte) 0 , 5);
        
        tBaik = new Tahanan("Udin", (byte) 21, (byte) 0, 5);
        tJahad = new Tahanan("Adit", (byte) 22, (byte) 1, 5);
        
        orang1 = new NPC("Death", (byte) 31, (byte) 2, 0);
        orang2 = new NPC("Tru", (byte) 32, (byte) 0, 0);
        cewek = new NPC("Cwk", (byte) 33, (byte) 2, 0);
        
        hakim = new NPC("Hakim", (byte) 34, (byte) 0, 1);
        jahad = new NPC("Murle", (byte) 35, (byte) 2, 0);
        ibu = new NPC("Toriel", (byte) 36, (byte) 0, 2);
        
        peta = new Inventory(1, "Peta");
        senter = new Inventory(2, "Senter");
        sekop = new Inventory(3, "Sekop");
        
        player = new Player ((byte) 91);
        
        setDialog();
    }
    
    public void setDialog(){
        String[] diss = {hakim.getNama()+" : Saudara Ryn, apakah benar Anda telah membunuh?",
        player.getNama()+" : Benar Pak",
        hakim.getNama()+": Dipersilakan untuk memberikan pembelaan",
        player.getNama()+": Saya melakukan hal tersebut karena orang "
                + "tersebut ingin membunuh wanita ini, Pak. Saya berusaha untuk melindunginya !",
        hakim.getNama()+": Saudara saksi, apakah benar demikian?",
        player.getNama()+": Tidak Pak, saya melihat dia mengacungkan pistol miliknya ke arah "
                + "saya dan ia telah membunuh orang di belakang saya saat kejadian tersebut berlangsung.",
        hakim.getNama()+": Baik, sudah diputuskan bahwa terdakwa Ryn divonis "
                + "hukuman penjara seumur hidup karena telah membunuh dengan senapan api",
        "[ketok palu 3x :v]"};

        hakim.setDialog(diss);
        
        
        String[] diss1 = {pBaik.getNama()+": Jadilah tahanan yang baik dan melakukan setiap tanggung jawabmu dipenjara ini!",
        player.getNama()+" : Pak, aku tidak bisa berlama-lama dipenjara ini, ada orang yang harus ku tolong!",
        pBaik.getNama()+": Lah, itu urusanmu dan tidak ada hubungannya dengan ku. Lagi pula kau sudah "
                + "divonis penjara seumur. Kau tidak punya kesempatan untuk hal tersebut. Pikirkanlah dirimu sendiri!",
        player.getNama()+" : (monolog) Huh, percuma saja berbicara dengan sipir ini, aku harus cari cara lain untuk keluar dari tempat ini."
        };
        
        pBaik.setDialog(diss1);
        
        String[] diss2 = {player.getNama()+": Pak, saya punya satu permintaan.",
        pJahad.getNama()+": Apa itu?",
        player.getNama()+": Bantu saya untuk kabur dari penjara ini",
        pJahad.getNama()+": Untuk apa saya menolong anda?",
        player.getNama()+": Saya harus menolong orang lain Pak, mereka bisa dibunuh.",
        pJahad.getNama()+": Lalu apa untungnya buat saya?",
        player.getNama()+": Pak ini bukan soal untung tidak untung Pak, ini soal nyawa !",
        pJahad.getNama()+": Jika itu tidak memberikan suatu keuntungan buat saya, saya tidak ada urusan!",
        player.getNama()+": Pak….",
        pJahad.getNama()+": (sipirnya nyelonong kabur :v)"};
        
        pJahad.setDialog(diss2);
        
        
        String[] diss3 = {player.getNama()+" : Pak, bisakah saya mendapat keringanan atas hukuman saya? Atau bisakah saya keluar dari penjara ini untuk sebentar saja?",
        pBaik.getNama()+" : Memangnya kau mau apa keluar dari penjara ini? Ingin kabur dan berbuat jahat lagi?",
        player.getNama()+" : Bukan Pak, saya harus menyelamatkan orang lain dari rencana pembunuhan.",
        pBaik.getNama()+" : Rencana pembunuhan? Tau dari mana kau bahwa akan ada rencana pembunuhan?",
        player.getNama()+" : Saat saya membunuh pembunuh untuk menyelamatkan orang lain, saya menemukan buku yang berisi daftar orang yang akan dibunuh Pak !",
        pBaik.getNama()+" : Masakan pembunuh sepertimu mau menolong orang padahal sudah jelas membunuh?",
        player.getNama()+" : Saya tidak punya pilihan Pak, saya refleks membunuh pembunuh itu, saya tidak bisa diam membiarkan orang tak bersalah dibunuh !",
        pBaik.getNama()+" : Jika itu niatmu, hiduplah dengan baik dipenjara ini, berdoalah kepada Tuhanmu, mungkin mereka akan selamat tanpa bantuanmu",
        "(sipirnya nyelonong kabur lagi)"};

        pBaik.setDialog(diss3);
        
        
        String[] diss4 = {player.getNama()+" : Pak sipir",
        pJahad.getNama()+" : Ya?",
        player.getNama()+" : Apakah anda mau menolong saya?",
        pJahad.getNama()+" : Tidak",
        player.getNama()+" : Tapi Pak saya butuh bantuan anda",
        pJahad.getNama()+" : Diam, berisik !!!",
        "[Ryn kena mental :v)"};

        pJahad.setDialog(diss4);
        
        
        String[] diss5 = {player.getNama()+" : Pak",
        pBaik.getNama()+" : Apa?",
        player.getNama()+" : Saya mohon Pak, Bantu saya untuk kabur dari penjara ini. Saya harus menolong orang lain Pak, Apa yang bisa saya lakukan supaya Anda mau membantu saya?",
        pBaik.getNama()+" : Bagaimana saya bisa percaya kepadamu kalau apa yang akan kau perbuat setelah keluar dari penjara ini sesuai dengan apa yang kau katakan ?",
        player.getNama()+" : Pak saya mohon Pak, saya bersumpah tidak akan membunuh orang lain selain mereka yang ingin berbuat jahat.",
        pBaik.getNama()+" : (Terdiam dan pergi)",
        player.getNama()+" : Pak saya mohon, Pakkkk…."};

        pBaik.setDialog(diss5);
        
        
        String[] diss6 = {tBaik.getNama()+" : Hei anak baru, kejahatan apa yang telah kau lakukan hahaha",
        player.getNama()+" : Tidak, aku tidak melakukan kejahatan",
        tBaik.getNama()+" : Lalu kenapa kau ada di tempat ini? Sedang berekreasi?",
        player.getNama()+" : Aku dipenjara karena membunuh untuk menolong orang lain yang akan dibunuh",
        tBaik.getNama()+" : Untuk apa kau lakukan sesuatu yang membawa malapetaka kepada dirimu?",
        player.getNama()+" : Lalu kenapa kau dipenjara ?",
        tBaik.getNama()+" : Oh, aku dipenjara karena aku mencuri.",
        player.getNama()+" : Mengapa kau mencuri?",
        tBaik.getNama()+" : Aku di PHK dari pekerjaanku. Selama berbulan-bulan aku tidak bekerja dan ada anak istri yang harus ku beri nafkah. Aku tidak punya pilihan lain, aku mencuri selama berbulan -bulan untuk mencukupi kebutuhan rumah tangga.",
        player.getNama()+": Hukum di negara kita memang tidak adil",
        tBaik.getNama()+" : Ya itu benar, sedangkan pejabat yang mencuri uang rakyat mendapat perlakuan yang baik."};

        tBaik.setDialog(diss6);
        

        String[] diss7 = {tJahad.getNama()+" : (nabrak Ryn saat jalan ke toilet)",
        player.getNama()+" : aduh",
        tJahad.getNama()+" : maaf aku sedang tidak melihat tadi",
        player.getNama()+" : iya, tidak apa-apa",
        tJahad.getNama()+" : apakah kau ingin kabur dari penjara ini? ",
        player.getNama()+" : Ya, aku perlu untuk kabur dari tempat ini",
        tJahad.getNama()+" : Mungkin kau perlu untuk lebih bertanggung jawab di penjara ini"};

        tJahad.setDialog(diss7);



        String[] diss8 = {tJahad.getNama()+" : Biasakah kau memberi makananmu sedikit saja kepadaku? Tidak biasanya aku selapar ini",
        player.getNama()+" : Boleh, ambil saja sesukamu",
        tJahad.getNama()+" : Terimakasih nak, siapa namamu? ",
        player.getNama()+" : Ryn",
        tJahad.getNama()+". Baik Ryn, aku harus pergi sekarang"};

        tJahad.setDialog(diss8);



        String[] diss9 = {tBaik.getNama()+" : Apakah kau akan bekerja hari ini?",
        player.getNama()+" : Entahlah aku harus memikirkan cara agar dapat keluar dari tempat ini",
        tBaik.getNama()+" : Baiklah,aku sudah memutuskan untuk tidak bekerja dan pura-pura sakit."};
        
        tBaik.setDialog(diss9);

        
        String[] diss10 = {player.getNama()+" : Untuk apa benda-benda itu kau sembunyikan?",
        tBaik.getNama()+" : Ssshh… ini benda yang kutemukan mungkin bisa berguna untuk membuatku kabur dari penjara]",
        player.getNama()+" : Ayo kita kabur bersama",
        tBaik.getNama()+" : Kita lihat saja nanti."};

        tBaik.setDialog(diss10);
        
        
        String[] diss11 = {player.getNama()+" : Pak saya mohon bantulah saya untuk keluar dari penjara ini",
        pBaik.getNama()+" : Sssshh… diamlah",
        pBaik.getNama()+"[sipir membuka kunci sel]",
        pBaik.getNama()+" : Saya akan membiarkan mu untuk kabur malam ini. Hanya selamatkanlah orang yang dalam bahaya tersebut. Sebaiknya kau bergegas pergi sejauh mungkin malam ini, karena kau akan dikejar saat pagi nanti. Semoga beruntung",
        player.getNama()+" : Terimakasih banyak Pak."};

        pBaik.setDialog(diss11);
        
        
        /* String[] diss121 = {player.getNama()+" : Jadi bagaimana dengan rencana kabur kita itu",
        tBaik.getNama()+" : Sssstt, jangan keras-keras. Kita lihat saja malam ini",
        player.getNama()+" : Malam ini? Bagaimana?",
        tBaik.getNama()+" : Sudah kubilang, kita lihat saja malam ini"};

        tBaik.setDialog(diss121);
        */
        
        String[] diss12 = {player.getNama()+" : Bagaimana caranya supaya kita dapat kabur malam ini?",
        tBaik.getNama()+" : Aku tau caranya, ayo kita kabur malam ini. Sebab aku telah mengumpulkan alat yang dapat melancarkan aksi kita",
        player.getNama()+" : Apa yang akan kita lakukan dengan peralatan itu ?",
        tBaik.getNama()+" : Kita akan menggali dan akan kabur lewat bawah tanah",
        player.getNama()+" : Baiklah, kau yang mencangkul dan aku akan mengawasi keadaan."};

        tBaik.setDialog(diss12);
        
        
        String[] diss13 = {ibu.getNama()+" : Aduh…", 
        ibu.getNama()+"(barang belanjaan jatuh)",
        player.getNama()+" : Maaf Bu saya tidak sengaja saya mencari alamat",
        ibu.getNama()+" : Memangnya kamu sedang mencari alamat siapa nak sampai menyenggol ibu begitu keras?",
        player.getNama()+" : Saya sedang mencari alamat seorang penjahat besar Bu, dia ingin melenyapkan orang-orang yang tidak bersalah.",
        player.getNama()+"(memberitahu alamat Murle)",
        ibu.getNama()+" : Hah? Ini tidak mungkin! Kamu pasti salah alamat, nak!",
        player.getNama()+" : Tidak Bu saya tidak salah alamat!, alamat ini saya dapatkan dari anak buahnya yang adalah salah satu pembunuh bayaran!",
        ibu.getNama()+" : Hah?!? Benarkah demikian?! Murle mengapa nak? Mengapa?",
        player.getNama()+" : Bu, apakah ibu tau tentang pembunuh ini?",
        ibu.getNama()+" : Alamat itu adalah alamat di mana anak saya tinggal! Dan pembunuh yang kau bicarakan itu adalah anakku!",
        player.getNama()+" : Ibu tidak bercanda kan?",
        ibu.getNama()+" : Tidak nak, pembunuh itu adalah anak ibu, Murle namanya. Ia adalah seorang anak yang baik, anak yang rajin membantu ibunya dalam pekerjaan ibu rumah tangga. Ibu tidak menyangka ia adalah pembunuh yang ingin melenyapkan orang yang tidak bersalah!",
        player.getNama()+" : Jikalau demikian, mari kita temui dia Bu!!",
        ibu.getNama()+" : Baik nak, ibu juga akan menegur dia atas semua perbuatan yang telah ia perbuat."};

        ibu.setDialog(diss13);
        
        
        String[] diss14 = {ibu.getNama()+" : Nakkkk",
        jahad.getNama()+" : Ibu? Itukah engkau",
        ibu.getNama()+" : Nak mengapa engkau berbuat begitu jahat nak?",
        jahad.getNama()+" : Jahat? Ibu? Mengapa engkau mengetahuinya???",
        ibu.getNama()+" : Nak",
        jahad.getNama()+" : iya ibu… Benar aku telah melakukannya maafkan aku Bu. Aku membenci banyak orang karena hal yang sepele dan membunuh mereka dengan pembunuh yang ku suruh. Aku tidak bisa mengontrol emosiku.",
        ibu.getNama()+" : Nak, mengapa engkau menjadi seperti ini nak? Perbuatan mu itu salah! Jika ada orang yang menegurmu dan bahkan mengecam kamu, seharusnya kamu intropeksi diri dan berubah menjadi pribadi yang lebih baik lagi nak!",
        jahad.getNama()+" : Maafkan aku Ibu, aku tidak melaksanakan setiap didikan yang telah engkau ajarakan kepada ku."};

        ibu.setDialog(diss14);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_jack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import static uas_jack.Main.pBaik;
import static uas_jack.Main.pJahad;
import static uas_jack.Main.peta;
import static uas_jack.Main.sekop;
import static uas_jack.Main.senter;
import static uas_jack.Main.tBaik;
import static uas_jack.Main.tJahad;

/**
 *
 * @author USER
 */
public class Player {
    private String nama;
    private int id, scene, days;
    private boolean[] badEnd = {false,false,false,false,false,false,false};
    private boolean[] goodEnd = {false, false};
    
    public Player (int i){
        nama = "Ryn";
        id = i;
        scene = 0;
        days = 1;
        badEnd = new boolean [] {false,false,false,false,false,false,false};
        goodEnd = new boolean[] {false, false};
    }
    
    public Player (int i, int p, boolean[] bad){
        nama = "Ryn";
        id = i;
        scene = p;
        badEnd = bad;
        days = 1;
    }
        
    public String getNama() {
        return nama;
    }
    
    public void progress(){
        scene++;
        System.out.println("Let's gooo "+scene);
    }
    
    public void nexDay(){
        days++;
    }
    
    public int getDay(){
        return days;
    }
    
    public void reset(){
        scene = 0;
        System.out.println("Reset Progress");
    }
    
    public boolean[] getBad(){
        return badEnd;
    }
    
    public boolean[] getGood(){
        return goodEnd;
    }
    
    public int getScene(){
        return scene;
    }
    
    public void setBad(int i){
        badEnd[i] = true;
    }
    
    public void setGood(int i){
        goodEnd[i] = true;
    }
    
    public void saveGame(){
        byte progress[] = {pBaik.getDekat(), pJahad.getDekat(), 
                tBaik.getDekat(), tJahad.getDekat()};
        boolean punya[] = {peta.getHave(), senter.getHave(), sekop.getHave()};
        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter(new FileOutputStream("Save.txt"));
            outputStream.println(id);
            outputStream.println(scene);
            outputStream.println(Arrays.toString(badEnd));
            outputStream.println(Arrays.toString(progress));
            outputStream.println(Arrays.toString(punya));
            outputStream.println(days);
            outputStream.println(Arrays.toString(goodEnd));
            outputStream.close();
            System.out.println("Saving");
        
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Gagal save file, pls help " + e.getMessage()  + "\n");
        }
    }
    
    public void loadGame(){
        BufferedReader inputStream = null;
        String baris = null;
        try
        {
            inputStream = new BufferedReader(new FileReader("Save.txt"));
            
            baris = inputStream.readLine();
            id = Integer.parseInt(baris);
            baris = inputStream.readLine();
            scene = Integer.parseInt(baris);
            
            System.out.print(scene);
            
            baris = inputStream.readLine();
            String[] array = baris.replaceAll("\\[", "")
                                  .replaceAll("]", "").split(", ");
            for (int i = 0; i < array.length; i++)
                badEnd[i] = Boolean.valueOf(array[i]);
            
            System.out.print(badEnd[6]);
            
            baris = inputStream.readLine();
            array = baris.replaceAll("\\[", "")
                         .replaceAll("]", "").split(", ");
            pBaik.setDekat(Byte.valueOf(array[0]));
            pJahad.setDekat(Byte.valueOf(array[1]));
            tBaik.setDekat(Byte.valueOf(array[2]));
            tJahad.setDekat(Byte.valueOf(array[3]));
            
            System.out.print(pBaik.getDekat());
            
            baris = inputStream.readLine();
            array = baris.replaceAll("\\[", "")
                         .replaceAll("]", "").split(", ");
            peta.setHave(Boolean.valueOf(array[0])); 
            senter.setHave(Boolean.valueOf(array[1]));
            sekop.setHave(Boolean.valueOf(array[2]));
            
            System.out.println(senter.getHave());
            
            baris = inputStream.readLine();
            days = Integer.parseInt(baris);
            
            System.out.println(days);
            
            baris = inputStream.readLine();
            array = baris.replaceAll("\\[", "")
                                  .replaceAll("]", "").split(", ");
            for (int i = 0; i < array.length; i++)
                goodEnd[i] = Boolean.valueOf(array[i]);
            
            System.out.println(goodEnd[1]);
        }
        catch(InputMismatchException e){
            System.out.println("Input yang dimasukkan salah, " + e.getMessage() + "\n");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Gagal membuka yang diinginkan, " + e.getMessage() + "\n");
        }     
        catch(IOException e)
        {
            System.out.println("Gagal membaca yang diinginkan, " + e.getMessage() + "\n");
        } 
        
    }

    
}

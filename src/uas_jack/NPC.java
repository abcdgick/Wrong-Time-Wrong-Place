/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_jack;

/**
 *
 * @author USER
 */
public class NPC {
    protected String nama;
    protected byte id, tingkah;
    protected String[][] dialog;
    int i = 0, diss = 0;
    
    public NPC(String n, byte i, byte t, int m){
        nama = n;
        id = i;
        tingkah = t;
        this.i = m;
        dialog = new String[this.i][];
    }
    
    public void setTingkah(byte e){
        tingkah = e;
    }
    
    public void setDekat(byte e){
        
    }
    
    public String getJenis(){
        return "NPC";
    }
    
    public String getNama(){
        return nama;
    }
    
    public byte getTingkah(){
        return tingkah;
        //0 buat baik
        //1 buat greedy
        //2 buat jahad
    }
    
    public byte getDekat(){
        return 0;
        //0 ngga kenal
        //1 mulai kenal
        //2 bisa gas
    }
    
    public boolean kabur(){
        return false;
    }
    
    public void setJaga(boolean dis){
        
    }
    
    public boolean getJaga(){
        return false;
    }
    
    public void progress(){
        
    }
    
    public void setDialog(String[] dis){
        dialog[diss] = new String[dis.length];
        dialog[diss] = dis;
        diss++;
        
    }
    
    public String[] getDialog(int i){
        return dialog[i];
    }
}

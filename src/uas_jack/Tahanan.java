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
public class Tahanan extends NPC{
    private boolean makan;
    private byte dekat;
    
    public Tahanan(String n, byte i, byte t, int m){
        super (n,i,t, m);
        dekat = 0;
        makan = false;
    }
    
    @Override
    public void setDekat(byte e){
        dekat = e;
    }
    
    @Override
    public void progress(){
        dekat++;
    }
    
    @Override
    public byte getDekat(){
        return dekat;
    }
    
    @Override
    public String getJenis(){
        return "Penjaga";
    }
    
    @Override
    public boolean kabur(){
        return dekat >= 3;
    }
    
    @Override
    public void setJaga(boolean dis){
       makan = dis; 
    }
    
    @Override
    public boolean getJaga(){
        return makan;
    }
}

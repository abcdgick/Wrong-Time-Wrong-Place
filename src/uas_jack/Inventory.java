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
public class Inventory {
    private final int id;
    private final String nama;
    private boolean have;
    
    public Inventory(int i, String n){
        id = i;
        nama = n;
        have = false;
    }
    
    public int getid(){
        return id;
    }
    
    public String getNama(){
        return nama;
    }
    
    public boolean getHave(){
        return have;
    }
    
    public void setHave(boolean dis){
        have = dis;
    }
    
    
}

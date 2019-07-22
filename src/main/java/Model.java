
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiro
 */
public class Model implements Observable{
    private int score, life;
    private String word;
    private char[] tabWord;
    
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers)
            o.update(score, life, tabWord);
    }  
    
    private void removeAccents(){   
        String avec = "âäàçéêëèîïôöûüù";
        String sans = "aaaceeeeiioouuu";
        
        for(int i=0; i < avec.length(); i++)
            this.word = this.word.replace(avec.charAt(i), sans.charAt(i));
    }
        
    public void newGame(){   
        this.life = 7;
        this.score = 0;
        
        try {
            BufferedReader b = new BufferedReader(new FileReader("dictionnaire.txt"));            
            Stream<String> s = b.lines(); 
            Object o[] = s.toArray();

            double rand = Math.random() * o.length;   
            word = o[(int)rand].toString();
           
            b.close();  
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        removeAccents();
        
        tabWord = new char[word.length()];
            for(int i=0; i<tabWord.length; i++) tabWord[i] = '*';
            
        this.notifyObservers();
    }
    
    public void check(char letter){
        boolean find = false;
        
        letter = (char)((int)letter + 32);
        
        for(int i=0; i < word.length(); i++){
            if(letter == word.charAt(i)){
                tabWord[i] = letter;
                find = true;
            }
        }
        
        if(!find)
            life--;
        
        this.notifyObservers();
    }
    
}

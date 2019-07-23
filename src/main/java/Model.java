
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
    
    private Data data = new Data();
    private ArrayList<Score> listScore = new ArrayList<Score>();
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
    public void notifyObservers(GameState state) {
        for(Observer o : observers)
            o.update(state, data);
    } 
    
    public void writeAt(int index, char letter){
        this.data.tabWord[index] = letter;
    }
    
    public char readAt(int index){
        return this.data.tabWord[index];
    }
    
    public ArrayList<Score> getListScore(){
        return this.listScore;
    }
    
    public int getScore(){
        return this.data.score;
    }
    
    public String getWord(){
        return this.data.word;
    }
    
    public int getLife(){
        return this.data.life;
    }
    
    public void setLife(int value){
        this.data.life = value;
    }
        
    public void newGame(int score){   
        this.data.life = 7;
        this.data.score = score;
        
        /*
         * Récuperation d'un mot du dictionnaire
        */
        try {
            BufferedReader b = new BufferedReader(new FileReader("dictionnaire.txt"));            
            Stream<String> s = b.lines(); 
            Object o[] = s.toArray();

            double rand = Math.random() * o.length;   
            this.data.word = removeAccents(o[(int)rand].toString());
           
            b.close();  
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        /*
         * Initialisation de la table qui contient le mot avec des '*'
        */
        this.data.tabWord = new char[this.data.word.length()];
            for(int i=0; i<this.data.tabWord.length; i++) this.data.tabWord[i] = '*';
            
        this.notifyObservers(GameState.InGame);
    }
    
    private String removeAccents(String word){   
        String avec = "âäàçéêëèîïôöûüù";
        String sans = "aaaceeeeiioouuu";
        
        for(int i=0; i < avec.length(); i++)
            word = word.replace(avec.charAt(i), sans.charAt(i));
        return word;
    }
    
    public void calculScore(){
        int points = 0;
        
        switch(this.data.life){
            case 7: points = 100; break;
            case 6: points = 50; break;
            case 5: points = 35; break;
            case 4: points = 25; break;
            case 3: points = 15; break;
            case 2: points = 10; break;
            case 1: points = 5; break;
        }
        this.data.score += points;
    }
}

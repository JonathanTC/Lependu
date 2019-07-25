package Model;

import Observer.Observer;
import Observer.Observable;
import Scenes.SceneIntro;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    
    private int life, score, count;
    private Word word;
    private Sauvegarde save = new Sauvegarde();
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    
    public Model(){
        this.reset();
        
        /*
         * Chargement des scores du fichier de sauvegarde
        */
        
        ObjectInputStream ois;
        
        try { 
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File("sauvegarde.txt"))));
            
            save = (Sauvegarde)ois.readObject();
        } 
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*
     * Nous cherchons si le caractère se trouve dans le mot
     * puis nous faisont .
    */
    
    public void find(char[] table){   
        boolean find = false;
        
        for(int i = 0; i < table.length; i++){
            if(word.find(table[i])) find = true;
        }
        
        this.operation(find);
    }
    
    public void find(char letter){  
        this.operation(word.find(letter));
    }
    
    /*
     * Effectue toute les opérations possible après la recherche du caractère.
     * Si des caractères ont été remplacer :
     *   - Si le mot est complet on réinitialise un nouveau mot et ont réinitialise
     *     la vie à 7 puis ont fais la mise à jour la vue.
     *   - Si le mot est incomplet ont met à jour la vue.
     * Si aucun caractères ont été trouver et remplacer :
     *   La vie du joueur diminue.
     *   - Si le joueur n'a plus de vie ont regarde si sont score est dans le top 10
     *     pour le sauvegarder.
     *   Pour finir ont propose au joueur de rejouer et ont fait la mise à jour de la vue.
    */
    
    private void operation(boolean find){
        
        if(find){  
            if(word.isCompleted()){
                calcul();
                life = 7;
                count++;
                word = new Word("dictionnaire.txt");
                
                this.notifyObservers(score, word.toString(), count, life, true);
            }
            else{
                this.notifyObservers(score, word.toString(), count, life, false);
            }   
        }

        if(!find){
            life--;
            
            if(life <= 0){
                calcul();
                
                if(isBestScore()){
                    String name = JOptionPane.showInputDialog("Entre ton nom pour sauvegarder ton score");
                    saveScore(name, "score.txt");
                }
                
                reset();
                
                int choice = JOptionPane.showConfirmDialog(null, "Voulez vous réessayer ?", "Game Over", JOptionPane.YES_NO_OPTION);
                
                if(choice == JOptionPane.NO_OPTION){
                    this.notifyObservers(score, word.toString(), count, life, true);
                    this.notifyObservers(new SceneIntro());
                }
                else{
                    this.notifyObservers(score, word.toString(), count, life, true);
                }
            }
            else
            {
                this.notifyObservers(score, word.toString(), count, life, false);
            }
        }
    }
    
    /*
     * Permet de calculer le score du joueur
    */
    
    private void calcul(){
        int points = 0;
        
        switch(life){
            case 7: points = 100; break;
            case 6: points = 50; break;
            case 5: points = 35; break;
            case 4: points = 25; break;
            case 3: points = 15; break;
            case 2: points = 10; break;
            case 1: points = 5; break;
        }  
        score += points;
    }
    
    /*
     * Verifie si le score se trouve dans le top 10
    */
    
    private boolean isBestScore(){
        int i = 0;
        
        for(Score s : save.scores){
            if(score > s.getScore()) break;   
            i++;
        }
        
        if(i <= 9)
            return true;
        
        return false;
    }
    
    /*
     * Permet de sauvegarder le score du joueur dans un fichier
    */
    
    private void saveScore(String name, String path){

        int i= 0;
        for(Score s : save.scores){
            if(score > s.getScore()) break;   
            i++;
        }
        
        save.scores.add(i, new Score(name, score));
        
        ObjectOutputStream oos;
        
        try{
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                        new FileOutputStream(
                            new File("sauvegarde.txt"))));
            
            oos.writeObject(save);
            oos.close();   
        }
        catch(IOException ex){
           System.out.println(ex.getMessage());
        }
    }
    
    /*
     * Permet de réinitialiser les données du model à leur origine
    */
    
    private void reset(){
        score = count = 0;
        life = 7;
        word = new Word("dictionnaire.txt");
    }

    /*
     * Permet de récuperer le mot 
    */
    public String getWord(){
        return word.toString();
    }
    
    /*
     * Permet de récuperer la liste des scores
    */
    
    public ArrayList<Score> getScores(){
        return save.scores;
    }
    
    /*
     * Permet l'ajout d'une vue
    */
    
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }
    
    /*
     * Permet de supprimer une vue
    */

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /*
     * Permet de mettre à jour l'affichage de la vue
    */
    
    @Override
    public void notifyObservers(int score, String word, int count, int life, boolean resetKeys) {
        for(Observer o : observers)
            o.update(score, word, count, life, resetKeys);
    } 
    
    /*
     * Permet de changer la Scene de la vue
    */
    
    @Override
    public void notifyObservers(JPanel panel) {
        for(Observer o : observers)
            o.update(panel);
    }

}

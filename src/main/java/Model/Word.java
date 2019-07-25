/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author thiro
 */
public class Word {
    
    private String word;
    private char[] wordTable;
    
    public Word(String path){ 
        
        /*
        * Permet de récuperer un mot au hazare dans le dictionnaire
        */
        
        try
        {
            BufferedReader b = new BufferedReader(new FileReader(path));            
            Stream<String> s = b.lines(); 
            Object o[] = s.toArray();
            b.close();  
            
            double rand = Math.random() * o.length;   
            this.word = o[(int)rand].toString();   
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
        
        /*
        * Permet de créer et d'initialiser la table qui contient le mot à 
        * trouver avec des '*'.
        */
        
        wordTable = new char[this.word.length()];
        Arrays.fill(wordTable, '*');
    }
    
    /*
     * Permet de vérifier si tout les caractères du mot ont été trouver.
    */
    
    public boolean isCompleted(){
        for(int i = 0; i < word.length(); i++){
            if( wordTable[i] == '*')
                return false;
        }
        return true;
    }
    
    /*
     * Permet de verifier si une lettre est dans le mot puis l'afficher
    */
    
    public boolean find(char c){
        boolean find = false;
        for(int i=0; i < word.length(); i++){
            if(c == word.charAt(i)){
                wordTable[i] = c;
                find = true;
            }
        }
        return find;
    }

    /*
     * Permet de récuperer l'affichage du mot cacher par des '*'
    */
    
    public String toString(){
        String word = "";
        for(int i = 0; i < wordTable.length; i++) word += wordTable[i];
        return word;
    }
}

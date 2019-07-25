package Controler;
import Model.Model;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiro
 */
public class Controler {
    private Model model;
    
    public Controler(Model model){
        this.model = model;
    }
    
    /*
     * la fonction qui permet de rectifier le caractère
    */
    public void control(char letter){
        letter = (char)((int)letter + 32);
        
        switch(letter){
            case 'a': model.find(new char[]{'â', 'ä', 'à', letter}); break;
            case 'e': model.find(new char[]{'é', 'è', 'ê', 'ë', letter}); break;
            case 'i': model.find(new char[]{'î', 'ï' , letter}); break;
            case 'o': model.find(new char[]{'ô', 'ö', letter}); break;
            case 'u': model.find(new char[]{'û', 'ü', 'ù', letter}); break;
            case 'c': model.find(new char[]{'ç', letter}); break;
            default : model.find(letter); break;
        }
    }
}

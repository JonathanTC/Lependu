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
    
    public void check(char letter){
        System.out.println("Cherche dans le model la lettre");
    }
}

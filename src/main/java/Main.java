import Controler.Controler;
import Model.Model;
import View.Window;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiro
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Model model = new Model();
        Window window = new Window(model);
        model.addObserver(window);
    }  
}

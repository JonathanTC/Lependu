
import javax.swing.JOptionPane;

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
        boolean find = false;
        
        letter = (char)((int)letter + 32);
        
        for(int i=0; i < model.getWord().length(); i++){
            if(letter == model.getWord().charAt(i)){
                model.writeAt(i, letter);
                find = true;
            }
        }
        
        if(!find){
            model.setLife(model.getLife() - 1);
            
            if(model.getLife() <= 0)
                model.notifyObservers(GameState.Lose);
            else
                model.notifyObservers(GameState.InGame);
        }
        else{
            if(checkWin()){
                model.calculScore();
                model.notifyObservers(GameState.Win);
            }
            else{
                model.notifyObservers(GameState.InGame);
            }
        }
    }

    public void newGame(int score){
        model.newGame(score);
    }
    
    public boolean checkWin(){
        for(int i=0; i<model.getWord().length(); i++){
            if(model.readAt(i) == '*')
                return false;
        }
        
        return true;
    }
}

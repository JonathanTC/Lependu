
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
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
public class SeekLetter implements ActionListener{
    private char letter;
    private String word;
    private char[] tabWord;
    private Game game;
    
    public SeekLetter(Game pGame){
        game = pGame;
        
        this.letter = pGame.getLetter();
        this.word = pGame.getWord();
        this.tabWord = pGame.getTabWord();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {    
       
        ((JButton)e.getSource()).setEnabled(false);
        
        boolean find = false;
        
        letter = (char)((int)letter + 32);
         
        for(int i=0; i<word.length(); i++){
            if(letter == word.charAt(i)){
                tabWord[i] = letter;
                find = true;
            }
        }
        
        if(!find){
            game.setLife(game.getLife() - 1);
            
            if(game.getLife() == 0){
                int choice = JOptionPane.showConfirmDialog(game, "Vous avez perdu la partie, votre score est de : " + game.getScore() + "\nvoulez vous recommencer ?", "Game over", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.OK_OPTION){ game.loadGame(); }
                else System.out.println("revenir au menu principal");
            }
        }
 
        game.repaint();
        
    } 
}

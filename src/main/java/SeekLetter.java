
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;

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
       
        
        letter = (char)((int)letter + 32);
         
        for(int i=0; i<word.length(); i++){
            if(letter == word.charAt(i))
                tabWord[i] = letter;
        }
        
        ((JButton)e.getSource()).setEnabled(false);
        game.repaint();
        
    } 
}

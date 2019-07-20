
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
    private String letter;
    private String word;
    private char[] tabWord;
    
    public SeekLetter(String letter, String word, char[] tabWord){
        this.letter = letter;
        this.word = word;
        this.tabWord = tabWord;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {          
        letter = letter.toLowerCase();
        
        if(word.contains(letter)) System.out.println("La chaine contient la lettre " + letter);

        ((JButton)e.getSource()).setEnabled(false);
    } 
}


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
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
public class Game extends JPanel implements Observer{
    
    private JPanel keyboard;
    private Image[] img = new Image[7]; 
    private Controler controler;
    private ArrayList<Bouton> boutons = new ArrayList();
    
    private Data data = new Data();
    private Window parent;

    public Game(Window window, Controler controler){ 
        this.controler = controler; 
        this.parent = window;
    } 
        
    public void load(){    
       GridBagConstraints gbc = new GridBagConstraints();
       int py, px, nb;
       
       this.setBackground(Color.WHITE);
       this.setLayout( null);

       keyboard = new JPanel();
       keyboard.setLayout(new GridBagLayout());
       
       resetButton();
       controler.newGame(0);
       
        /*
         * Creation du clavier virtuel (Controller)
        */
        nb = px = py = 0;
        for(int i=65; i<91; i++){
           gbc.gridx = px;
           gbc.gridy = py;
           gbc.fill = GridBagConstraints.BOTH;
           
           JPanel p = new JPanel();
           p.setPreferredSize(new Dimension(60,40));
           p.setLayout(null);
           
           char letter = (char)i;
           Bouton bouton = new Bouton("" + letter);
          
           
           bouton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   bouton.setEnabled(false);
                   controler.check(letter);  
               }
           });
           
           boutons.add(bouton);
           
           p.add(bouton);
           keyboard.add(p, gbc);
           
           if(px >= 6){
               py++; 
               if(py < 3) px = 0;
               else px = 1;
           }
           else
               px++;
       }    
       
       keyboard.setBounds(new Rectangle(30, 170, 420, 160));
       keyboard.setBackground(Color.RED);

       this.add(keyboard);    
       
       for(int i=0; i < 7; i++){
           String str = "images/" + Integer.toString(i) + ".jpg";
            try{
                img[i] = ImageIO.read(new File(str));
            } catch (IOException ex) {
               ex.printStackTrace();
           }
       }
       
       this.revalidate();
    }
      
    public void paintComponent(Graphics g){    
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setFont(new Font("Arial", Font.BOLD, 15));
        g2d.setColor(Color.BLACK);
        String str = "Nombre de mot trouvé: " + this.data.wordCount;
        g2d.drawString(str, 30, 70);

        str = "Votre score actuel est de : " + this.data.score;
        g2d.drawString(str, 30, 90);

        /*
         * affichage du mot à trouver
        */
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLUE);
        
        g2d.drawChars(this.data.tabWord, 
                0, 
                this.data.tabWord.length, 
                keyboard.getX() + keyboard.getWidth()/2 - (g2d.getFontMetrics().charsWidth(this.data.tabWord, 0, this.data.tabWord.length)/2), 
                keyboard.getY() - 15);

        /*
        * dessin de l'image
        */
        g2d.drawImage(img[this.data.life-1], 480, 30, 300, 300, this);   
    } 
    
    @Override
    public void update(GameState notifycation, Data data) {
        
        if(notifycation.equals(GameState.InGame)){
            // recupere les données
            this.data = data;
        }
        
        if(notifycation.equals(GameState.Win)){
            // nouveau mot
            controler.newGame(data.score);
            resetButton();
        }
        
        if(notifycation.equals(GameState.Lose)){
            // sauvegarde le score et relance une partie

            if(controler.checkScore()){
                String name = JOptionPane.showInputDialog(this, "Veuillez entrer votre prénom :");
                controler.saveScore(name);
            }
            
            int choice = JOptionPane.showConfirmDialog(this, "Vous avez perdu !, votre score est de : " + data.score + "\nVoulez vous rejouer ?", "Game over", JOptionPane.YES_NO_OPTION);

            if(choice == JOptionPane.YES_OPTION){
                controler.newGame(0);
                resetButton();
            }
            else{
                parent.changeScene(new Intro());
            }         
        }
                
        this.repaint();
    }
    
    private void resetButton(){
        for(Bouton b : boutons)
            b.setEnabled(true);
    }
}

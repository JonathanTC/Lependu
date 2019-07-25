package Scenes;

import Controler.Controler;
import Model.Model;
import Observer.Observer;
import View.Component.Bouton;
import View.Window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
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
public class SceneGame extends JPanel{
    
    private JPanel keyboard;
    private Image[] img = new Image[7]; 
    private ArrayList<Bouton> boutons = new ArrayList();
    private Window parent;
    private int score, life, count;
    private String word;

    public SceneGame(Controler controler, Model model){ 
        this.score = this.count = 0;
        this.life = 7;
        this.word = model.getWord();
        
        load(controler);
    } 
        
    public void load(Controler controler){    
       GridBagConstraints gbc = new GridBagConstraints();
       int py, px, nb;
       
       this.setBackground(Color.WHITE);
       this.setLayout( null);

       keyboard = new JPanel();
       keyboard.setLayout(new GridBagLayout());
       keyboard.setBounds(new Rectangle(30, 170, 420, 160));
       keyboard.setBackground(Color.WHITE);
       
       /*
       * Chargement du score
       */

       //resetKeys();
       
        /*
         * Creation du clavier virtuel (Controller)
        */
        nb = px = py = 0;
        for(int i=65; i<91; i++){
           gbc.gridx = px;
           gbc.gridy = py;
           gbc.fill = GridBagConstraints.BOTH;
           
           JPanel p = new JPanel();
           p.setBackground(Color.WHITE);
           p.setPreferredSize(new Dimension(60,40));
           p.setLayout(null);
           
           char letter = (char)i;
           Bouton bouton = new Bouton("" + letter);
          
           bouton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   bouton.setEnabled(false);
                   controler.control(letter);
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
       
       this.add(keyboard);    
       
       /*
       * Chargement des images
       */
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
        String str = "Nombre de mot trouvé: " + this.count;
        g2d.drawString(str, 30, 70);

        str = "Votre score actuel est de : " + this.score;
        g2d.drawString(str, 30, 90);

        /*
         * affichage du mot à trouver
        */
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLUE);
        
        g2d.drawString(this.word, 
                keyboard.getX() + keyboard.getWidth()/2 - g2d.getFontMetrics().stringWidth(this.word)/2,
                keyboard.getY() - 15);
                
        /*
        * dessin de l'image
        */
        g2d.drawImage(img[this.life-1], 480, 30, 300, 300, this);   
    } 
    
    public void resetKeys(){
        for(Bouton b : boutons) b.setEnabled(true);
    }

    public void setScore(int score){
        this.score = score;
    }
    
    public void setWord(String word){
        this.word = word;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void setLife(int life){
        this.life = life;
    }
}

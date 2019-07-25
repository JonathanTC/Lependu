package Scenes;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class SceneRegles extends JPanel{
    public SceneRegles(){ System.out.println("Regles"); }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        String data = "Le jeu du PENDU:";
        g2d.drawString(data, this.getWidth()/2 - g2d.getFontMetrics().stringWidth(data)/2, 40);   
        
        g2d.setFont(new Font("Arial", Font.PLAIN, 15));
        data = "Vous avez 7 coups pour trouver le mot caché! Et si vous réussissez: on recommence !";
        g2d.drawString(data, 60, 100);
        data = "Plus vous avez trouvé de mots, plus votre socre grandira !!, Alors à vous de jouer !";
        g2d.drawString(data, 60, 115);
        
        g2d.setFont(new Font("Arial", Font.BOLD, 15));
        data = "COMPTE DES POINTS:";
        g2d.drawString(data, 60, 150);
        
        g2d.setFont(new Font("Arial", Font.PLAIN, 15));
        
        for(int i=0; i<7; i++){
            switch(i){
                case 0: data = "Mot trouvé sans erreur"; break;
                case 1: data = "Mot trouvé avec " + i + " erreur"; break;
                default: data = "Mot trouvé avec " + i + " erreurs"; break;
            }
            g2d.drawString(data, 110, 180 + (i*15));
        }
        
        data = "Je vous souhaite bien du plaisir...";
        g2d.drawString(data, 60, 310);
        data = "Et, si vous pensez pouvoir trouver le mot en un coup, c'est que vous pensez que le dictionnaire est petit !";
        g2d.drawString(data, 60, 325);
        data = "Hors pour votre formation il comprend plus de 336 000 mots... Donc bonne chance !";
        g2d.drawString(data, 60, 340);
    }
}

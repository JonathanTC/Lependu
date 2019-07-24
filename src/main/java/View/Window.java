package View;


import Model.Model;
import Controler.Controler;
import View.Regles;
import View.Intro;
import View.SceneScore;
import View.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
public class Window extends JFrame{
    
    private JMenuBar menu = new JMenuBar();
    private JMenu fichier = new JMenu("Fichier");
    private JMenu apropos = new JMenu("A propos");
    private JMenuItem nouveau = new JMenuItem("Nouveau");
    private JMenuItem score = new JMenuItem("Score");
    private JMenuItem quitter = new JMenuItem("Quitter");
    private JMenuItem regles = new JMenuItem("Règles");
    private JMenuItem help = new JMenuItem("?");
    
    private JPanel scene;
    private Model model = new Model();
    private Controler controler = new Controler(model);
    
    private Game game;
    private SceneScore sScore;
    
    public Window(int width, int height){
        this.setTitle("Le pendu");
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(menu);
        this.setResizable(false);

        initComponent();
        
        this.setVisible(true);
    }

    
    private void initComponent(){      
        
        game = new Game(this, controler);
        model.addObserver(game);
        
        sScore = new SceneScore(controler);
        model.addObserver(sScore);
                
        /* construction de la bar de menu */
        menu.add(fichier);
        menu.add(apropos);
        
        /* construction du menu fichier */
        fichier.add(nouveau);
        fichier.add(score);
        fichier.addSeparator();
        fichier.add(quitter);
        
        /* construction du menu A propos */
        apropos.add(regles);
        apropos.add(help);
        
        /* applique les actions aux boutons du menu */
        quitter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }          
        });
        
        help.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "Auteur : Jonathan Thiroux-Collard\nEmail : thirouxjonathan@yahoo.fr";
                JOptionPane.showMessageDialog(null, str);
            };
        });
        
        nouveau.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                game.load();    
                changeScene(game);
            };
        });
        
        score.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               sScore.load();
               changeScene(sScore);
            }     
        });
        
        regles.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeScene(new Regles());
            }
        }); 
        
        changeScene(new Intro());
    }
    
    public void changeScene(JPanel scene){
        this.setContentPane(scene);
        this.revalidate();
    }
}

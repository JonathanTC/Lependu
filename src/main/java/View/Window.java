package View;


import Model.Model;
import Controler.Controler;
import Observer.Observer;
import Scenes.SceneRegles;
import Scenes.SceneIntro;
import Scenes.SceneScore;
import Scenes.SceneGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Window extends JFrame implements Observer{
    
    private JMenuBar menu = new JMenuBar();
    private JMenu fichier = new JMenu("Fichier");
    private JMenu apropos = new JMenu("A propos");
    private JMenuItem nouveau = new JMenuItem("Nouveau");
    private JMenuItem score = new JMenuItem("Score");
    private JMenuItem quitter = new JMenuItem("Quitter");
    private JMenuItem regles = new JMenuItem("Règles");
    private JMenuItem help = new JMenuItem("?");
    
    private JPanel scene;
    private SceneGame game;
    private Controler controler;
    
    public Window(Model model){
        this.setTitle("Le pendu");
        this.setSize(800, 430);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(menu);
        this.setResizable(false);
        
        controler = new Controler(model);

        initComponent(model);
        
        this.setVisible(true);
    }

    private void initComponent(Model model){                            
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
                game = new SceneGame(controler, model);
                changeScene(game);
            };
        });
        
        score.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               changeScene(new SceneScore(model));
            }     
        });
        
        regles.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeScene(new SceneRegles());
            }
        }); 
        
        changeScene(new SceneIntro());
    }
    
    public void changeScene(JPanel scene){
        this.setContentPane(scene);
        this.revalidate();
    }

    @Override
    public void update(int score, String word, int count, int life, boolean resetKeys) {
        game.setScore(score);
        game.setWord(word);
        game.setCount(count);
        game.setLife(life);
        
        if(resetKeys) game.resetKeys();
        
        game.repaint();
    }

    @Override
    public void update(JPanel panel) {
        this.changeScene(panel);
    }
}

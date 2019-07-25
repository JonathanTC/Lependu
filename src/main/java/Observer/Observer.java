package Observer;

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
public interface Observer {
    public void update(int score, String word, int count, int life, boolean resetKeys);
    public void update(JPanel panel);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiro
 */
public interface Observable {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}

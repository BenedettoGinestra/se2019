 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.utils;

import twinkingdom.staticentities.grabbable.GrabbableStar;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author mario
 */
public class GrabbableStarCollection extends Observable implements Observer{
    
    private LinkedList<GrabbableStar> collection;
    private LinkedList<Observer> observers;
    
    public GrabbableStarCollection(){
        collection = new LinkedList();
        observers=new LinkedList();
    }
    
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }
    
    public void addStar(GrabbableStar star){
        collection.add(star);
        
        
            
            for (Observer o: observers) {
                o.update(this, this);
            }
       
        
    }
    
    public int getSize(){
        System.out.println("La size è: "+collection.size());
        return collection.size();
    }

    @Override
    public void update(Observable o, Object arg) {
        
        addStar((GrabbableStar) o);
        setChanged();
        notifyObservers();
        System.out.println("Chiamato il metodo Update");
        System.out.println(collection);
        
    }
    
    public void clearStars() {
        
        collection.clear();
        
        System.out.println("La size della collection è:"+collection.size());
        //collection=new LinkedList();
        
        System.out.println("GLI OBSERVERS SONO: "+observers.size());
        for (Observer o: observers) {
            System.out.println("SONO NEL FOR");
                o.update(this, this);
            }
        
    }
    
    
}

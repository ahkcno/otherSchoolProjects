// **************************************************************************************************************
// Class Name: GenericQueue
// Purpose: Implements a queue of Object objects via the LinkedList class
// Date Written 10/23/21
// Author: Alex Littlefield
// **************************************************************************************************************
package PPL_LittlefieldA;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.
import java.util.LinkedList;

public class PatientQueue  implements Cloneable // extends LinkedList 
{
    // Global declarations
    protected LinkedList genQueue;
    protected int qLength;
   
    // Constructor
    public PatientQueue() 
    { genQueue = new LinkedList(); qLength = 0;}
    
    // The add method 
    public void addRear(Object thisObject)
    { genQueue.addLast(thisObject); qLength++; }
    
    // The contains method 
    public boolean contains(Object thisObject)
    { return genQueue.contains(thisObject); }
    
    // The indexOf method 
    public int indexOf(Object thisObject)
    { return genQueue.indexOf(thisObject); }
     
    // The remove method 
    public Object removeFront()
    {   Object Discard = null;
        if (!genQueue.isEmpty()) {Discard = genQueue.removeFirst(); qLength--;} 
        return Discard;
    }
    
    // The getInfo method
    public Object get(int pos)
    {
      // Find the retrieval point in the list and return the information at that location.
      int x = 1;
      Object[] thisList = toArray();
      return thisList[pos];
    } // End of get method
    
    // The get method 
    // This is an alternate get method
     public Object getX(int pos)
     {  Object[] auxList = new Object[qLength];
       int y, priorLength = qLength;
       
       // Load items to auxList
       for (y = 1; y <= priorLength; y++)
       { auxList[y-1] = new Object(); auxList[y-1] = removeFront();}
              
       // Reload auxList to the queue, then return the sought item  
       for (y = 1; y <= priorLength; y++) {addRear(auxList[y-1]);}     
       return auxList[pos];
     } // End of getX method
    
    // The size method 
    public int size()
    { return qLength; }
    
    // The clear method 
    public void clear()
    { genQueue.clear(); qLength = 0;}
    
    // The toArray method 
    // public Object[] toArray() { return genQueue.toArray(); } This version crashes, hence the alternate code below
    public Object[] toArray()
    {
       Object[] auxList = new Object[qLength];
       int y, PriorLength = qLength;

       // Load queue items to auxList
       for (y = 1; y <= PriorLength; y++)
       { auxList[y-1] = new Object(); auxList[y-1] = removeFront();}
       
       // Reload auxList to the queue, then return auxList  
       for (y = 1; y <= PriorLength; y++) {addRear(auxList[y-1]);}     
       return auxList;
    }
        
    // The isEmpty method 
    public boolean isEmpty()
    { return genQueue.isEmpty(); }
    
    // The getFront method 
    public Object getFront()
    { return genQueue.getFirst(); }
    
    // The getRear method 
    public Object getRear()
    { return genQueue.getLast(); }
    
    // The peekFront method 
    public Object peekFront()
    { return genQueue.getFirst(); }
    
    // The peekRear method 
    public Object peekRear()
    { return genQueue.getLast(); }
    
} // End of Generic Queue class

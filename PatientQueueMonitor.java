// **************************************************************************************************************
// Class Name: PatientQueueMonitor
// Purpose: Implements queue of Patient objects via the PatientQueue class
// Last Modified 4/23/22
// Author: Alex Littlefield
// **************************************************************************************************************

package PPL_LittlefieldA;
import javax.swing.JOptionPane;

public class PatientQueueMonitor 
{
    
    // Global Data Items
    public static final int MAX = 6;
    public static PatientQueue [] theClinic = new PatientQueue[MAX];
    public static String [] theDoc = new String[MAX];
    public static boolean [] openFlag = new boolean[MAX];
    public static PatientQueue pQueue;
    public static final String HEADING = "Patient Queue";
    public static final int DEFAULT_NUMBER = 0;

    // main method
    public static void main(String[] args) 
    {
        // Declare main variables
        
        String promptString = "1. open a line \n" + "2. Enqueue patient \n" + "3. List Patients \n" +
                "4. Dequeue \n" + "5. Check Size of Queue \n" + "6. Empty the Queue \n" + 
                "7. Reassign Patients \n" + "8. Close Queue \n" + "0. Quit Processing";  
        boolean exitTime = false;
        int userOption, patientQueueSize;
        
        initializeList(); // Initialize the queue
        
        while (!exitTime) // While user wishes to continue
        {
            try{
            // Present menu and process user's request
            patientQueueSize = pQueue.size();
            userOption = Integer.parseInt(JOptionPane.showInputDialog(null, promptString, HEADING, JOptionPane.QUESTION_MESSAGE));
            
            
            switch (userOption)   
            {
                case 0: {exitTime = true; break;}
                case 1: {openQueue(); break;} 
                case 2: {inputPatients(); break;}
                case 3: {pQueue = decider("Which doctor would you like to see?");
                        listPatients(pQueue); break;}
                case 4: {pQueue = decider("Which queue would you like to remove from?");
                        removePatients(pQueue); break;}
                case 5: {pQueue = decider("Which queue would you like to see?");checkSize(pQueue); break;}
                case 6: {pQueue = decider("Which queue would you like to empty?");
                        empty(pQueue); break;}
                case 7: {pQueue = decider("From which queue would you like to assign? ");
                        reassignPatients(pQueue); break;
                }
                case 8: { closeQueue();}
            }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a number 0-8");
            }
        } // End While
    } // End of main method
    
    //to pick a Queue
    public static PatientQueue decider(String thisString){
        int userOption = Integer.parseInt(JOptionPane.showInputDialog(null, "Please select a doctor 1-6 \n", thisString, JOptionPane.QUESTION_MESSAGE ));
        if(openFlag[userOption-1] = true){
            pQueue = theClinic[userOption-1];
        } else {
            JOptionPane.showMessageDialog(null, "Not a valid choice. "); //returns an empty Queue
        }
        return pQueue;
    }
    
    // Initialize Method
    public static void initializeList()
    { 
        pQueue = new PatientQueue(); // Creates an empty queue
        for(int x = 1; x<= MAX; x++){
            theClinic[x-1] = new PatientQueue();
            theDoc[x-1] = x + ". Dr thing " + x;
            openFlag[x-1] = false;
        }
    }//end method
    
    //Opens Queues
    public static void openQueue(){
        boolean exitTime = false;
        String docString = "";
        while(!exitTime){
            int queueChoice;
            
            //create string of docs
            for(int x = 1; x<= MAX; x++){
                docString += theDoc[x-1] + "\n";
            }//end for
            
            queueChoice = Integer.parseInt(JOptionPane.showInputDialog(null, docString, HEADING, JOptionPane.QUESTION_MESSAGE));
            if(queueChoice < 1 || queueChoice > 6){
                queueChoice = Integer.parseInt(JOptionPane.showInputDialog(null, docString, HEADING, JOptionPane.QUESTION_MESSAGE));
            }
            openFlag[queueChoice - 1] = true;
            exitTime = true;
        }
    }//end method
    
    // The inputPatients Method
    public static void inputPatients()
    {
        int x, qChoice, qLimit, nextUserAction;
        Patient currentPatient = new Patient();
        boolean exitTime = false;
        
        while(!exitTime){
            qChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Which line would you like to join? ", HEADING, JOptionPane.QUESTION_MESSAGE));
            if(qChoice < 1 || qChoice > 10){
                qChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter a valid queue, 1-10 ", HEADING, JOptionPane.QUESTION_MESSAGE));
            }
            if(openFlag[qChoice-1] == false){
                JOptionPane.showMessageDialog(null, "This doctor is not accepting patients right now.\n"
                        + "Please return later, after the queue has been opened.");
            }
            if(openFlag[qChoice-1]==true){
                qLimit = Integer.parseInt(JOptionPane.showInputDialog(null, "How many patients would you like to enqueue? ", HEADING, JOptionPane.QUESTION_MESSAGE));
                for(x=1;x<=qLimit;x++){
                    currentPatient = new Patient();
                    currentPatient.inputData(x);
                    theClinic[qChoice-1].addRear(currentPatient);
                }//end for
            }//end if
            nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to query another. Click No or Cancel to exit.");
            if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)) exitTime = true;            
        }//end while
    }//end inputPatients

    //removes patients
    public static void removePatients(PatientQueue thisList){
        int qStart, qStop, x, qChoice, qLimit;
        boolean exitTime = false;
        
        while(!exitTime){
              //done by decider()
//            if(openFlag[qChoice-1] = true){
                qStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Where would you like to start? ", HEADING, JOptionPane.QUESTION_MESSAGE));
                qStop = Integer.parseInt(JOptionPane.showInputDialog(null, "Where would you like to stop? ", HEADING, JOptionPane.QUESTION_MESSAGE));
                while(qStop< qStart || qStart < 0){
                    qStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Range invalid. Please enter a valid range\nWhere would you like to start?", HEADING, JOptionPane.QUESTION_MESSAGE));
                    qStop = Integer.parseInt(JOptionPane.showInputDialog(null, "Range invalid. Please enter a valid range\nWhere would you like to stop? ", HEADING, JOptionPane.QUESTION_MESSAGE));                    
                }//end while
                
                for(x = qStart; x<= qStop; x++){
                    thisList.removeFront();
                }//end for
//            }//end if
            int nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to query another. Click No or Cancel to exit.");
            if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)) exitTime = true;                        
        }//end while
    } // End of removePatients Method
    
    // The listPatients Method: 
    public static void listPatients(PatientQueue thisList) 
    {
        String outString = "Members of the queue are: \n"; int x;
        for (x = 1; x <= thisList.size(); x++)
        { outString += ((Patient) thisList.get(x-1)).printMe() + "\n"; }
        JOptionPane.showMessageDialog(null, outString, HEADING, JOptionPane.INFORMATION_MESSAGE);
    } // End of listPatients Method
    
    // The queryPatient Method   
    public static void queryPatient(PatientQueue thisList)
    {
        // Declarations
        String outString; 
        int nextUserAction, searchNumber;
        Patient foundPatient; 
        String qHeading = "Patient Query";
        boolean exitTime = false;
        boolean exitNow; 
        int thisLim = thisList.size();
        
        // Prompt for the then check it it's in the list
        if (thisLim > 0) // If there are Patients 
        {    
            while (!exitTime) // While more processing required 
            {    
                searchNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Specify the Patient number of Interest: ", qHeading, JOptionPane.QUESTION_MESSAGE));
                foundPatient = new Patient();
                                
                // Search thisList for the Patient object, then return it
                exitNow = false;
                for (int x = 1; ((x <= thisLim) && (!exitNow)); x++)
                {
                    if (searchNumber == ((Patient) thisList.get(x-1)).getID())  
                    { foundPatient.modifyMe(((Patient) thisList.get(x-1))); exitNow = true; } 
                } // End-For 
                if (foundPatient.getID() != DEFAULT_NUMBER) outString = foundPatient.printMe();
                else outString = "Library Patient specified is not in the list.";
                JOptionPane.showMessageDialog(null, outString, qHeading, JOptionPane.INFORMATION_MESSAGE);
        
                // Check whether user wishes to continue
                nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to query another. Click No or Cancel to exit.");
                if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)) exitTime = true;
            } // End-While more processing required 
        } // End-If there are Patients
    } // End queryPatient       

    // The removePatients Method

    // The checkSize Method
    public static void checkSize(PatientQueue thisList)
    {
       JOptionPane.showMessageDialog(null, "There are " + thisList.size() + " Patients in the queue", HEADING, JOptionPane.INFORMATION_MESSAGE);
    } // End of checkSize Method
    
    // The empty Method
    public static void empty(PatientQueue thisList)
    {   
        int nextUserAction;
        String removalPrompt = "You are about to empty the queue. " + "Click Yes to Empty. Click No or Cancel to exit.";
        nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
        if (nextUserAction == JOptionPane.YES_OPTION) thisList.clear(); 
    } // End of empty Method
    
    //finds the smallest
    public static int findSmallestQ(PatientQueue [] thisClinic, int refQueue){
        int smallestSize = Integer.MAX_VALUE;
        int x = 1; int smallestQ = 1;
        int tempSize, userOption;
        
        for(x =1; x<= MAX; x++){
            tempSize = thisClinic[x-1].size();
            if(tempSize < smallestSize && openFlag[x-1] == true && x != refQueue){
                smallestSize = tempSize; smallestQ = x;
            }//end if
        }//end for
        if(openFlag[smallestQ-1] == false){
            userOption = JOptionPane.showConfirmDialog(null, "Would you like to open a Queue "
                    + "to reassign a patient to?");
            if(userOption == JOptionPane.YES_OPTION){
                openFlag[smallestQ-1] = true;
            }
        }
        return smallestQ;
    }//end method
    
    //reassigns
    public static void reassignPatients(PatientQueue thisQueue){
        int targetQ;
        boolean exitTime = false;
        
        while(!exitTime){
            
            while(thisQueue.qLength > 0){
                targetQ = findSmallestQ(theClinic, thisQueue.size());
                theClinic[targetQ-1].addRear(thisQueue.removeFront());
                thisQueue.removeFront();
            }//end while
            int nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to reassign another. Click No or Cancel to exit.");
            if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)) exitTime = true;
        }//end while
    }//end method
    
    //closes Queues
    public static void closeQueue(){
        int targetQ;
        boolean exitTime = false;

        while (!exitTime) {
            PatientQueue sourceQ = decider("Which queue would you like to close? ");
            while (sourceQ != null) {
                targetQ = findSmallestQ(theClinic, sourceQ.size());
                theClinic[targetQ - 1].addRear(sourceQ.getFront());
                sourceQ.removeFront();
                for(int x =1; x<= theClinic.length; x++){//checks all queues for data. closes empty ones
                    if(theClinic[x-1] == null){
                        openFlag[x-1] = false;
                    }//end if
                }//end for
            }//end while
            int nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to reassign another. Click No or Cancel to exit.");
            if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)) {
                exitTime = true;}
        }//end while 
    }//end method
} // End of PatientsQueueMonitor class

// *********************************************************************************
// Class Name: Patient
// Purpose: Patient object class
// Date Created: 10/22/21
// Author: Alex Littlefield
// **********************************************************************************
package PPL_LittlefieldA;
import javax.swing.JOptionPane;
public class Patient {
    
    //decs
    protected Integer pNumber;
    protected String pFName, pLName, pTele;
    
    //constructor
    public Patient(){
        pNumber = 0;
        pFName = "";
        pLName = "";
        pTele = "000-000-0000";
    }//end contstructor
    
    public void modifyMe(Patient thisOne){
        pNumber = thisOne.pNumber;
        pFName = thisOne.pFName;
        pLName = thisOne.pLName;
        pTele = thisOne.pTele;
    }//end method
    
    //returns a string for output
    public String printMe(){
        String printString = "";
        printString = "Patient: " + pNumber + "-" + pFName + " " + pLName +
                "\n" + "Telephone" + pTele;
        return printString;
    }//end method
    
    //gets the ID
    public int getID(){
        return pNumber;
    }//end method
    
    public void inputData(int x){
        String inputHeading = "Patient Check In";
        String strInTele, strInNum;
        
        strInNum = JOptionPane.showInputDialog(null, "Please enter a numeric patient ID: ", inputHeading, JOptionPane.QUESTION_MESSAGE);
        while (!validateNum(strInNum)){
            strInNum = JOptionPane.showInputDialog(null, "Please enter a numeric patient ID: ", inputHeading, JOptionPane.QUESTION_MESSAGE);
            
            
        }//end while
        
        pNumber = Integer.parseInt(strInNum);
        pFName = JOptionPane.showInputDialog(null, "Please enter patient's first name: ", inputHeading, JOptionPane.QUESTION_MESSAGE);
        pLName = JOptionPane.showInputDialog(null, "Please enter patient's last name: ", inputHeading, JOptionPane.QUESTION_MESSAGE);
        
        strInTele = JOptionPane.showInputDialog(null, "Please enter patient's phone number"
                + "in format xxx-xxx-xxxx: ", inputHeading, JOptionPane.QUESTION_MESSAGE);
        validateTele(strInTele);
        while(!validateTele(strInTele)){
            strInTele = JOptionPane.showInputDialog(null, "Please enter patient's phone number"
                + " in format xxx-xxx-xxxx: ", inputHeading, JOptionPane.QUESTION_MESSAGE);
        }//end while
        
        pTele = strInTele;
    }//end Method

    public boolean validateNum(String thisID){
        boolean isValid = true;
        
        for (int x = 1; x<= thisID.length(); x++){
            char tempChar = thisID.charAt(x-1);
            //if(isDigit((thisID.charAt(x-1))) == false){
            if (!Character.isDigit(tempChar)){isValid = false; break;}
        }
        return isValid;
    }
    
    public boolean validateTele(String thisTele){
        boolean isValid = true;
        
        for (int x = 1; x<= thisTele.length(); x++){
            switch(x){
                case 4:{
                    char tempChar = thisTele.charAt(x-1);
                    if (!Character.isDigit(tempChar)){isValid = false;}
                    break;
                }
                case 8: {
                    char tempChar = thisTele.charAt(x-1);
                    if (!Character.isDigit(tempChar)){isValid = false;}
                    break;
                }
                default: {
                    char tempChar = thisTele.charAt(x-1);
                    if (Character.isDigit(tempChar) == true){isValid = true;}
                    break;
                }
            }//end switch
        }//end for
        return isValid;
    }//end method
        
}//end class

// **********************************************************************************
// Class Name: PatientNode
// Purpose: Facilitates node linking for a Queue.
// Date Created: 10/22/21
// Author: Alex Littlefield
// **********************************************************************************

package PPL_LittlefieldA;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.
import java.util.ArrayList;

public class PatientNode 
{
    // Global Data Items
    protected Patient nInfo;
    protected PatientNode nNext;
    
    // Constructor
    public PatientNode() 
    {
        nInfo = new Patient();
        nNext = null;
    } // End of constructor
    
    // modifyMe method
    public void modifyMe(PatientNode thisNode)
    {
        nInfo.modifyMe(thisNode.nInfo);
        nNext = thisNode.nNext;
    } // End of modifyMe method
    
    // inputData method
    public void inputData(int x)
    {
        nInfo.inputData(x);
        nNext = null;
    } // End of inputData method
    
     // printMe method
    public String printMe()
    { return nInfo.printMe();} // End of printMe method
    
    
    
} // End of PatronNode class

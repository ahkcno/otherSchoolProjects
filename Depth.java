package project5;


/* Julie & Alex
   
   3/3/22
   CS 495/AI & Robotics

This program uses a depth-first based search method for
finding a route through a series of cartesian nodes. 

*/

import java.util.*;
import java.io.*;

class Depth {
// create the jump object with origin and destination

    class nextJump {

        String from;
        String destination;
        boolean skip;  // used in backtracking

        nextJump(String f, String t) {
            from = f;
            destination = t;
            skip = false;
        }
    }
    
    // This array holds jump information.
    final int MAX = 100;
    nextJump jumps[] = new nextJump[MAX];
    
    int numjumps = 0; // number of entries in jump Array
    Stack btStack = new Stack(); // backtrack stack

    public static void main(String args[]) {
        String destination, from;
        Depth ob = new Depth();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ob.setup();
        try {
            from = "A";
            System.out.print("Destination? ");
            destination = br.readLine().toUpperCase(); //for input inconsistency
            ob.isJump(from, destination);
            if (ob.btStack.size() != 0) {
                ob.route(destination);
            }
        } catch (IOException exc) {
            System.out.println("Error on input.");
        }
    }
    
    // Initialize the jump database.
    void setup() {
        addJump("A", "B");
        addJump("B", "D");
        addJump("B", "E");
        addJump("A", "C");
        addJump("E", "F");
        addJump("E", "G");
        addJump("G", "H");
        addJump("G", "I");
        addJump("H", "J");
        addJump("H", "K");
        addJump("J", "L");
        addJump("J", "M");
    }
// Put jumps into the database.

    void addJump(String from, String destination) {
        if (numjumps < MAX) {
            jumps[numjumps] = new nextJump(from, destination);
            numjumps++;
        } else {
            System.out.println("Database full!\n");
        }
    }

// Determine if there is a route between A and destination. 
    void isJump(String from, String destination) {
        boolean dist;
        nextJump f;

        // See if we can find a destination match in the database
        dist = match(from, destination);
        if (dist != false) {
            btStack.push(new nextJump(from, destination));
            return;
        }

        // Try another connection.
        f = find(from);
        if (f != null) {
            btStack.push(new nextJump(from, destination));
            isJump(f.destination, destination);
        } else if (btStack.size() > 0) {
            // Backtrack and try another connection.
            f = (nextJump) btStack.pop();
            isJump(f.from, f.destination);
        }
    }
// Show the route and destination.

    void route(String destination) {
        Stack rev = new Stack();
        int dist = 0;
        nextJump f;
        int num = btStack.size();
// Reverse the stack destination display route.
        for (int i = 0; i < num; i++) {
            rev.push(btStack.pop());
        }
        for (int i = 0; i < num; i++) {
            f = (nextJump) rev.pop();
            System.out.print(f.from + " to ");
        }
        System.out.println(destination);
    }

    /* If there is a jump between from "from" and destination,
     return true
     otherwise, return false. */

    boolean match(String from, String destination) {
        for (int i = numjumps - 1; i > -1; i--) {
            if (jumps[i].from.equals(from)
                    && jumps[i].destination.equals(destination)
                    && !jumps[i].skip) {
                jumps[i].skip = true; // prevent reuse
                return true;
            }
        }
        return false; // not found 
    }

// Find a connection.
    nextJump find(String from) {
        for (int i = 0; i < numjumps; i++) {
            if (jumps[i].from.equals(from)
                    && !jumps[i].skip) {
                nextJump f = new nextJump(jumps[i].from,
                        jumps[i].destination);
                jumps[i].skip = true; // prevent reuse
                return f;
            }
        }
        return null;
    }

}


/* 
 * File:   SalesRep.cpp
 * Author: alex
 * 
 * Created on April 9, 2022, 4:07 PM
 */

#include <cstdlib>
#include <iostream>
#include <string>

using std::endl;
using std::string;

class SalesRep
{
//decs
public:
    string name;
    string department;
    int id;
    double sales;
    
    SalesRep(string n, string d, int i, double s);
    void modify(SalesRep thisRep);
    void inputData();
    string printMe(SalesRep thisRep);
    string getName();
    string getDepartment();
    int getID();
    double getSales();
};//end decs

//constructor
SalesRep :: SalesRep(string n, string d, int i, double s){
    name = n;
    department = d;
    id = i;
    sales = s;
}
//modify method
void SalesRep :: modify(SalesRep thisRep){
    name = thisRep.name;
    department = thisRep.department;
    id = thisRep.id;
    sales = thisRep.sales;
}
//printMe method returns a string
string SalesRep :: printMe(SalesRep thisRep){
    string out;
    
    out = "Name: " + thisRep.name + "\n" +
          "Department: " + thisRep.department + "\n";
    std :: string tmp = std::to_string(thisRep.id);
    out += "ID: " + tmp + "\n";
    std :: string tmp = std::to_string(thisRep.sales);
    out += "Sales: " + tmp + "\n";
    return out;
}
//getters
string SalesRep :: getName(){
    return name;
}
string SalesRep :: getDepartment(){
    return department;
}
int SalesRep :: getID(){
    return id;
}
double SalesRep :: getSales(){
    return sales;
}
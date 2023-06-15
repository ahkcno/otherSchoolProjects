# -*- coding: utf-8 -*-
"""
Created on Wed Nov 25 23:51:22 2020

@author: Alex Littlefield

Data from https://www.basketball-reference.com/teams/MIA/2020.html


Algorithm/pseudocode:
    
    get data from csv file
    read line by line into a list
    calculate efficiency for each line
    append to list
    append list into list
    sort list of lists by efficiency
    print to file
    
    read from file into list
    print top 5 to console
    calculate player efficiency overall
    calculate player efficiency by year
    calculate team efficiency overall
    calculate team efficiency by year
    print to console
    draw plot
    
    


This program calculates efficiency for players by year. It will read from the
input file, calculate efficiency and store the data in a list. It will 
store this list in a list of all players and sort them. It will then write the
output to a txt file.

From there, it will pull the data back out of the output file and print the top
5 to console. Then it will calculate average efficiency of the players for the
whole time period. It will print that to console.

Then it will calculate average team efficiency per year. It will print this to 
console also. It will create a graph from that data for visual comparison.



In this case, we have 5 years of data from the Miami Heat. The program 
will run as many years as you like provided you paste each year from the above
url after scrolling to "Totals" and navigating to "Get table as csv" in the
drop-down menu.





"""

stats = []
name = []
flist = []
inlist = []
avgList = []
yearlist = []
avgpllist = []
outstr = ""
avg = 0
eff = 1


#Functions

import pylab
                                                #the following reads line by
                                                #line in order to build a list
                                                
                                                #playerAverage(player) builds
                                                #a list more like a function
                                                #should behave.

def openFile():                                 #opens file, does calculations
    year = 2021                                 #sets year
    global stats                                #global variables
    global name
    global flist
    global eff
    
    inFile= open("Book1.csv", "r")              #reads file. This is direct dump from
                                                #website with no editing. In chronological order
    for line in inFile:                        
        line = line.split(",")                  
        if line[0] == '"Rk':                    #sanitizing. if the line is not a player
            year = year - 1                     #sets year. Inputs are in order.

        if "\\" in line[1]:                     #so we can grab players with varied names
            name = line[1].split("\\")          
            if name[0] != None:
                stats = line[2:]                #create list with only numbers
                stats[-1] = stats[-1].split('\"')#more sanitizing. last item has a newline "\n" attached
                pts = stats[-1]                 #grab the last string from the line
                stats[-1] = pts[0]              #format the last string in the line
                                                #Calculations broken up for readability(sorta)
                eff = float(stats[25]) + float(stats[19]) + float(stats[20]) + float(stats[21]) + float(stats[22])
                eff = eff - ((float(stats[5]) - float(stats[4])) + (float(stats[15])-float(stats[14])) + float(stats[23]))
                eff = eff / float(stats[1])     
                
                name[1] = "{:.2f}".format(eff)  #formats efficiency variable. replaces gibberish
                name.append(year)  
                flist.append(name)              #list of lists for output
 
                
def sortlist():                                 #sorts list by eff score and prints to console
    global flist
    flist = sorted(flist, reverse=True, key = lambda x: float(x[1])) #sorts largest to smallest
    print("\n" + "This program calculates efficiency for players for each year and over their career with the team." + "\n"
       + "It also calculates whole team efficiency ratings for each year and over the dataset period." + "\n")
    print("\n"+ "Top 5 performances by year")
    print("\n" + "{: >20} {: >20} {: >20}".format("Name", "Efficiency Score", "Year") + "\n") 
    x = 5
    while x > 0:
        for entry in flist[0:5]:                #formatting
            print("{: >20} {: >20} {: >20}".format(entry[0], entry[1], entry[2]))
            x = x-1


          
def writefile():                                #writes out to file
    global outstr
    rwFile= open("Results.txt", "w")            #clears out old output
    outstr = str(flist)
    
    outstr = outstr.replace("[", "")            #cleans up list reminants
    outstr = outstr.replace("], ", "\n")
    outstr = outstr.replace("]]", "\n")
    outstr = outstr.replace("'","")
    rwFile.write(outstr)

    

def readoutfile():                              #reads output file
    global inlist
    rwFile= open("Results.txt", "r")
    for line in rwFile:
        line = line.split(",")
        inlist.append(line)
    for line in inlist:
        line[1] = line[1].replace(" ","")       #always cleaning up
        line[2] = line[2].replace(" ","")       #soap and water, 20 sec
        line[2] = line[2].replace("\n", "")     #bad for your computer though


def allCalc():                                  #calculates average efficiency
                                                #for all years combined
    global inlist
    global avglist
    x = 0
    i = len(inlist)

    for entry in inlist:
        x = float(entry[1]) + float(x)
    allAvg = x/i                                #calculate average across all
    for entry in inlist:                        #sort by year
        inlist = sorted(inlist, reverse=True, key = lambda x: x[2])
    avgList.append(allAvg)


def avgYearcalc(year):                          #calculates average efficiency
                                                #by year for given year
    global inlist
    global yearlist
    avgYear = 0
    x = 0
    for entry in inlist:
        if entry[2] == year:
            yearlist.append(entry[0:])
            avgYear = avgYear + float(entry[1])
            x = x + 1
    if avgYear > 0:
        avgYear = avgYear / x
        avgList.append(avgYear)


def playerAverage(player):                      #calculates average efficiency
                                                #by player for all years
    global inlist
    global avgpllist
    templist = []
    avg = float(0)
    x = float(0)
    for entry in inlist:
        if player == entry[0]:
            avg = float(entry[1]) + float(avg)
            x = x + 1
    avg = avg / x
    templist.append(player)
    templist.append(avg)
    avgpllist.append(templist)
    

#End functions, begin script
    

            

openFile()                                      #call everyone
sortlist()                                      
writefile()                                     
readoutfile()                                   
allCalc()

year = inlist[0]                                #setting the year
year = year[2]                                  
year = int(year)

allYears = inlist[-1]                           #for variable dataset sizes
allYears = allYears[2]
allYears = year - int(allYears) + 1


while allYears > 0:                             #calls the function for each
    avgYearcalc(str(year))                      #year in the set, calculating
    year = year - 1                             #total average efficiency
    allYears = allYears - 1                     #for the entire team


for entry in inlist:                            #to calculate player average
    playerAverage(entry[0])                     #efficiency over career with team


y = []                                          #to get rid of duplicates
for dupe in avgpllist:
    if dupe not in y:
        y.append(dupe)


avgpllist = y                                   #to sort by best efficiency avg
for entry in avgpllist:
    avgpllist = sorted(avgpllist, reverse=True, key = lambda x: x[1])     


for entry in avgpllist:                         #to make everything pretty
    entry[1] = ("{:.2f}".format(entry[1]))

print("\n"+ "Top 5 average efficiencies over all years")
print("\n" + "{: >20} {: >20}".format("Name", "Efficiency Score") + "\n") 
x = 5
while x > 0:
    for entry in avgpllist[0:5]:                         
        print("{: >20} {: >20}".format(entry[0], entry[1]))
        x = x-1

print("\n" + "Average team performance")        #This block prints team averages

print("\n" + "Team overall across all years:   " + "{:.2f}".format(avgList[0]))

year = inlist[0]                                #setting the year (again)
year = year[2]                                  
year = int(year)

print("\n" + "{: >20} {: >20}".format("Team Efficiency", "Year") + "\n")
tograph = []
for entry in avgList[1:]:
    print("{: >20} {: >20}".format("{:.2f}".format(entry), str(year)))
    tograph.append(entry)
    tograph.append(year)
    year = year - 1

year = inlist[0]
year = year[2]
year = int(year)
allYears = inlist[-1]                           #for variable dataset sizes
allYears = allYears[2]
allYears = year - int(allYears) + 1
                                                #final console print
                                                #use a variable to fix the year
print("\n" + "All players average performance over the " + str(allYears) + " year period")
print("\n" + "{: >20} {: >20}".format("Team Efficiency", "Year") + "\n")  
for entry in avgpllist:   
    print("{: >20} {: >20}".format(entry[0], entry[1]))
                                                
    
                                                #Pylab plotting. Just team efficiency
pylab.plot(tograph[1::2],tograph[0::2])
pylab.grid(True) 
pylab.locator_params(axis='x', nbins=5)
pylab.xlabel('Year')
pylab.ylabel('Team Efficiency')

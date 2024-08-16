/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author jesses
 */
public class StormChaser {
    public static void main(String[] args)
    {
      // Constants
    final int MAX_STORMS = 300;

    Storm[] list = new Storm[MAX_STORMS];  // array of Storms
    Storm currentStorm;      // storm returned by GetStorm
    int nStorms = 0;         // number in array List
    int total = 0;           // total number of storms in the input file
    Scanner fileInput;
    
   // Openning hurricane data file
   try{
        System.out.println("Openning hurricane data file...");
        fileInput = new Scanner(new File("hurricane.data"));
   }
   catch(FileNotFoundException e){
        System.err.println("FileNotFoundException: " + e.getMessage());
        return;
   }
   System.out.println( "File opened successfully...");
   System.out.println( "Reading file..." );
   

   // Read Storm data from file until EOF

   while( fileInput.hasNextLine() ) 
   {
	currentStorm = GetStorm (fileInput);
	total++;	
	if( currentStorm.getCategory() >= 3 )
	{
            list[nStorms] = currentStorm;
            nStorms++; 
	}
    }
    System.out.println( "Number of storms: " + total);
    System.out.println( "Hurricanes with category 3 and above: " + nStorms );
    DisplayStorms( "First Ten Storms", list, 10 );
    Sort( list, nStorms );
    DisplayStorms( "Top Twenty Storms", list, 20 );
    fileInput.close();
}

public static Storm GetStorm( Scanner in ) 
{
   // Build a Storm object and return it
        
    int year = 0, month = 0, day = 0, hour = 0;
    int category = 0, wind = 0, pressure = 0;
    String name; 
    int current = 0, beginDate = 0, duration = 0;
    int skip = 0;
    double junk = 0.0;
    Storm newStorm;
   
        		

	// Read next record.
	year = in.nextInt();
	month = in.nextInt();
	day = in.nextInt();
        hour = in.nextInt();
        category = in.nextInt();
	name = in.next();
	in.next();
        in.next();
	wind = in.nextInt();
	pressure = in.nextInt();

        

	
	// Make a storm object and initialize it with info from the current record
	beginDate = year * 10000 + month * 100 + day;
	duration = 6;
	current = category;
        newStorm = new Storm(beginDate, duration, name, wind, pressure);


	while( in.hasNextLine() && current == category ) 
	{
		//update storm info
		duration += 6; 
		newStorm.setDuration(duration);
		newStorm.setWind(wind);
		newStorm.setPressure(pressure);
		

      
		//get next record 

            year = in.nextInt();
            month = in.nextInt();
            day = in.nextInt();
            hour = in.nextInt();
            category = in.nextInt();
            name = in.next();
            in.next();
            in.next();
            wind = in.nextInt();
            pressure = in.nextInt();
	}

   // and return the new storm object
   return newStorm;
}

public static void DisplayStorms( String title, Storm[] list, int nStorms ) 
{
   // display nStorms storms
   // print some title and column headings
	System.out.println(title + "\n");
	System.out.println("Begin Date   Duration   Name   Category   Maximum    Minimum");
	System.out.println("             (hours)                     Winds (mph) Press. (mb)");
	System.out.println("----------------------------------------------------------------");
	for( int k = 0; k < nStorms; k++ )
		System.out.print(list[k].toString());
	System.out.println ("\n");
}

public static void Sort( Storm[] stormList, int n ) 
{
	// bubble sort the list of Storms
	int pass = 0, k, switches;
	Storm temp;
	switches = 1;
	while( switches != 0 ) 
	{
		switches = 0;
		pass++;
		for( k = 0; k < n - pass; k++ ) 
		{
			if( stormList[k].getCategory() < stormList[k+1].getCategory() )
			{
				temp = stormList[k];
				stormList[k] = stormList[k+1];
				stormList[k+1] = temp;
				switches = 1;
			}
		}
	}
}
}


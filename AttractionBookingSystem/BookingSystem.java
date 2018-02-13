/*
 * class BookingSystem
 * 
 * This code is a partially complete application for the booking system which 
 * implements a fully functional menu for CPT121 Assignment 3 in SP2 2015.
 * 
 */

import java.util.Scanner;

public class BookingSystem
{
   // Declare the array of Tour references in which the Tour and
   // ScheduledTour objects that the user adds will be stored in and
   // the corresponding tour count.
   //
   // Note that this array and the corresponding counter will be
   // accessible both from within the main method as well as from
   // within any helper methods you may decide to implement.

   private static final Attraction[] attractionList = new Attraction[100];
   private static int attractionCount = 0;
   

   // Declaring a shared scanner just in case you choose to
   // implement some helper methods in your application class
   // that need access to one.

   private static final Scanner sc = new Scanner(System.in);

   public static void main(String[] args)
   {
      // variables required to process user's menu selection
      String input;
      char selection = '\0';

      // keep repeating the menu until the user chooses to exit
      do
      {
         // display menu options
         System.out.println("******* Tour Booking System Menu *******");
         System.out.println("");
         System.out.println("A - Add New Attraction");
         System.out.println("B - Display Attraction Summary");
         System.out.println("C - Sell Passes");
         System.out.println("D - Refund Passes");
         System.out.println("E - Add New Scheduled Tour");
         System.out.println("F - Update Maximum Group Size");
         System.out.println("X - Exit Program");
         System.out.println();

         // prompt the user to enter their selection
         System.out.print("Enter your selection: ");
         input = sc.nextLine();

         System.out.println();

         // check to see if the user failed to enter exactly one character
         // for their menu selection

         if (input.length() != 1)
         {
            System.out.println("Error - selection must be a single character!");

         }
         else
         {
            // extract the user's menu selection as a char value and
            // convert it to upper case so that the menu becomes
            // case-insensitive

            selection = Character.toUpperCase(input.charAt(0));

            // process the user's selection
            switch (selection)
            {
               case 'A':
                  
                  // call addNewAttraction() helper method
                  addNewAttraction();
                  break;

               case 'B':

                  // call displayAttractionSummary() helper method
                  displayAttractionSummary();
                  break;

               case 'C':

                  // call sellPasses() helper method
                  sellPasses();
                  break;

               case 'D':

                  // call refundPasses() helper method
                  refundPasses();
                  break;

               case 'E':

                  // call addNewScheduledTour() helper method
                  addNewScheduledTour();
                  break;
                  
               case 'F':

                  // call updateMaxTourGroupSize() helper method
                  updateMaxGroupSize();
                  break;
                  
               case 'X':

                  System.out.println("Booking system shutting down – goodbye...");
                  break;

               default:

                  // default case - handles invalid selections
                  System.out.println("Error - invalid selection!");

            }
         }
         System.out.println();

      } while (selection != 'X');

   }
   
      
   	// STAGE 2 - REQUIREMENT A
    // method which implements the ability to add a new attraction into the system
   private static final void addNewAttraction()
   {         
      String attractionID;
      String description;
      double admissionFee;
      boolean duplicateID = false;
      
      System.out.println("Add New Attraction Feature Selected!");
      System.out.println("--------------------------------------");
      
      System.out.print("Enter Attraction ID: ");
      attractionID = sc.nextLine();
      
      System.out.print("Enter Attraction Description: ");
      description = sc.nextLine();
      
      System.out.print("Enter Admission Fee: ");
      admissionFee = sc.nextDouble();
      sc.nextLine();
      System.out.println();
      
      // check attractionID with other ID's already in the system
      // if duplicate found print error, if no duplicate add object to array
      for(int i = 0; i < attractionCount; i++)
      {
    	  if(attractionList[i].getAttractionID().equalsIgnoreCase(attractionID))
    	  {
    		  duplicateID = true;
    		  System.out.print("Error! Attraction / Tour with this ID already exists!");
    		  System.out.println();
    	  }
      }  

      if(duplicateID == false)
      {
          attractionList[attractionCount] = new Attraction(attractionID, description, admissionFee);
          attractionCount++;
      }
   }
   
   // STAGE 2 - REQUIREMENT B
   // method which displays list of all attractions currently in the system
   private static final void displayAttractionSummary()
   {
      System.out.println("Display Attraction Summary Feature Selected!");
      System.out.println("--------------------------------------");
      
      // step through array of attractions with for loop
      for(int i = 0; i < attractionCount; i++)
      {
          attractionList[i].printDetails();
          System.out.println();
      }
   }
   
   //STAGE 2 - REQUIREMENT C
   // method which add number of tourists to the booking count 
   // & returns booking cost by calling sellPasses() 
   private static final void sellPasses()
   {
	  Boolean invalidID = true;
	  String attractionID;
	  int numOfTourists;
	  double purchaseCost = 0;
	   
      System.out.println("Sell Passes Feature Selected!");
      System.out.println("--------------------------------------");
      
      System.out.print("Enter attraction ID: ");
      attractionID = sc.nextLine();
      
      // for loop to iterate through the array
      for(int i = 0; i < attractionCount; i++)
      {
    	  // check if attractionID is found, if yes - call sellPasses()
    	  if(attractionList[i].getAttractionID().equalsIgnoreCase(attractionID))
    	  {
    		  invalidID = false;
    		  System.out.print("Enter number of tourists: ");
    		  numOfTourists = sc.nextInt();
    		  sc.nextLine();
    		  try 
    		  {
				purchaseCost = attractionList[i].sellPasses(numOfTourists);
    		  } 
    		  catch (BookingException e) 
    		  {
				System.out.println(e.getMessage());
    		  }

    		  
    		  // if purchase value returned is greater than $0, tickets were successfully sold
    		  if (purchaseCost > 0)
    		  {
    			  System.out.println("Passes successfully sold!");
    			  System.out.printf("%s%.2f", "Booking Cost: $", purchaseCost);
    			  System.out.println();
    		  }
    		  // print error if value returned is not greater than 0.
    		  else
    		  {
    			  System.out.println("Request to purchase passes was unsuccessful");
    		  }
    		  break;
    	  }
      }
      // print error if attraction ID was not found in above for loop
      if (invalidID == true)
	  {
		  System.out.println("Error - attraction ID " + attractionID + " does not exist");
	  }
      
   }
   
   //STAGE 2 - REQUIREMENT D
   // method which removes tourists bookings 
   // & returns refunded booking cost by calling refundPasses()
   private static final void refundPasses()
   {
	   Boolean invalidID = true;
	   String attractionID;
	   int numOfTourists;
	   double refundCost = 0;
	   
      System.out.println("Refund Passes Feature Selected!");
      System.out.println("--------------------------------------");
 
      System.out.print("Enter attraction ID: ");
      attractionID = sc.nextLine();
      
      // for loop to iterate through the array
      for(int i = 0; i < attractionCount; i++)
      {
    	  // check if attractionID is found, if yes - call refundPasses()
    	  if(attractionList[i].getAttractionID().equalsIgnoreCase(attractionID))
    	  {
    		  invalidID = false;
    		  System.out.print("Enter number of tourists: ");
    		  numOfTourists = sc.nextInt();
    		  sc.nextLine();
    		  
    		  refundCost = attractionList[i].refundPasses(numOfTourists);
    		  
    		  // if value returned from sellPasses() is not Double.NaN, success!
    		  if (Double.isNaN(refundCost) == false)
    		  {
    			  System.out.println("Passes successfully refunded.");
    			  System.out.printf("%s%.2f", "Refund Cost: $", refundCost);
    			  System.out.println();
    		  }
    		  // print error if value returned is Double.NaN
    		  else
    		  {
    			  System.out.println("Error! Request to refund passes was unsuccessful");
    		  }
    		  break;
    	  }
      }
      // print error if attraction ID was not found in above for loop
      if (invalidID == true)
	  {
		  System.out.println("Error - attraction ID " + attractionID + " does not exist");
	  }
      
   }
   


   // STAGE 4 - REQUIREMENT A
   // add a new scheduled tour into the booking system
   private static void addNewScheduledTour()
   {
	   String tourID;
	   String description;
	   double admissionFee;
	   String tourDate;
	   int maxGroupSize;
	   String tourLeader;
	   boolean duplicateID = false;
	   
      System.out.println("Add New Scheduled Tour Feature Selected!");
      System.out.println("--------------------------------------");
      
      System.out.print("Enter Tour ID: ");
      tourID = sc.nextLine();
      
      System.out.print("Enter Tour Description: ");
      description = sc.nextLine();
      
      System.out.print("Enter Admission Fee: ");
      admissionFee = sc.nextDouble();
      sc.nextLine();
      
      System.out.print("Enter Tour Date: ");
      tourDate = sc.nextLine();
      
      System.out.print("Enter Maximum Group Size: ");
      maxGroupSize = sc.nextInt();
      sc.nextLine();
      
      System.out.print("Enter Tour Leader: ");
      tourLeader = sc.nextLine();
      System.out.println();
      
      // check if tourID is already in use in the booking system
      for(int i = 0; i < attractionCount; i++)
      {
    	  if(attractionList[i].getAttractionID().equalsIgnoreCase(tourID))
    	  {
    		  duplicateID = true;
    		  System.out.print("Error! Attraction / Tour with this ID already exists!");
    		  System.out.println();
    	  }
      }
      
      if(duplicateID == false)
      {
    	  attractionList[attractionCount] = new ScheduledTour(tourID, description,
    			  admissionFee, tourDate, maxGroupSize, tourLeader);
    	  attractionCount++;
      }
   }
   
   // STAGE 4 - REQUIREMENT B
   //Update maximum group size for tours
   private static void updateMaxGroupSize()
   {
	   String tourID;
	   boolean invalidID = true;
	   int newSize;
	   
      System.out.println("Update Maximum Group Size Feature Selected!");
      System.out.println("--------------------------------------");
      
      System.out.print("Enter tour ID: ");
      tourID = sc.nextLine();
      
      // for loop to iterate through array
      for(int i = 0; i < attractionCount; i++)
      {
    	  // check if tourID is found in attractionList array
    	  if(attractionList[i].getAttractionID().equalsIgnoreCase(tourID))
    	  {
    		  invalidID = false;
    		  
    		  // check if attraction is a scheduled tour
    		  if (attractionList[i] instanceof ScheduledTour)
    		  {
    			  System.out.print("Enter New Maximum Group Size: ");
    			  newSize = sc.nextInt();
    			  sc.nextLine();
    		  
    			  ((ScheduledTour) attractionList[i]).setGroupSize(newSize);
    			  
    			  System.out.println("Success! Maximum group size has been updated to " 
    					  + newSize +".");
    		  }
    		  else
    		  {
    			  System.out.println("Error! Attraction ID entered is not a scheduled tour!");
    		  }
    	  }
    	  
      }
      
      // print error if attraction ID not found in loop
      if (invalidID == true)
      {
    	  System.out.println("Error - tour ID " + tourID + " does not exist");
      }
      
      
   }
}

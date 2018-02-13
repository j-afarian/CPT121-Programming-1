/*
 * class Attraction
 * 
 * This code is the implementation of the Attraction class for CPT121 
 * Assignment 3 in SP2, 2015.
 * 
 */
public class Attraction
{
   // Step 1: Define instance variables required for a Attraction
   private String attractionID;
   private String description;
   private double admissionFee;
   private int bookingCount;

   // Step 2: Define a constructor that sets up a new Attraction
   public Attraction(String attractionID, String description, double admissionFee)
   {
      this.attractionID = attractionID;
      this.description = description;
      this.admissionFee = admissionFee;
   }

   // Step 3 - Define accessors (getters) for each instance variable
   public String getAttractionID()
   {
      return attractionID;
   }

   public String getDescription()
   {
      return description;
   }

   public double getAdmissionFee()
   {
      return admissionFee;
   }

   public int getBookingCount()
   {
      return bookingCount;
   }

   // Step 4: Define operations that can be performed on a Attraction
   
   // sellPasses()
   //
   // Attempts to add the specified number of tourists to the
   // booking count for this Attraction and return the booking
   // cost that is payable.
   //
   // Returns a signal of Double.NaN when the number of tourists
   // specified is not a positive value.
   //
   public double sellPasses(int numberOfTourists) throws BookingException
   {
      // check for an invalid number of tourists
      if (numberOfTourists <= 0)
      {
         throw new BookingException("Invalid number of tourists. Must be at least 1+");
      }
      else
      {
         // update the total number of bookings for this Attraction
         bookingCount += numberOfTourists;
         
         // calculate the total booking cost to be charged
         double bookingCost = this.calculateBookingCost(numberOfTourists);
         
         // return the booking cost payable to the caller
         return bookingCost;
      }
   }


   // refundPasses()
   //
   // Attempts to subtract the specified number of tourists from the
   // booking count for this Attraction and return the booking fees to be
   // refunded accordingly.
   //
   // Returns a signal of Double.NaN when the number of tourists
   // specified is not a positive value OR is greater than the current
   // booking count for this Attraction.
   //
   public double refundPasses(int numberOfTourists)
   {
      // check to make sure number of tourists is valid
      if (numberOfTourists <= 0 || numberOfTourists > bookingCount)
      {
         return Double.NaN;
      }
      else
      {
         // update the total number of bookings for this Attraction
         bookingCount -= numberOfTourists;
         
         // calculate the booking fees to be refunded
         double refundedFees = this.calculateBookingCost(numberOfTourists);
         
         // return the refunded booking fees to the caller
         return refundedFees;
      }
   }
   
   // Step 5: Define helper methods that we may need
   
   // calculateBookingCost()
   //
   // Helper method which calculates the booking cost that
   // apply based on the specified number of tourists that has
   // been passed in as a parameter and the admission fee for this 
   // Attraction
   //
   private double calculateBookingCost(int numberOfTourists)
   {
      return numberOfTourists * admissionFee;
   }
   
   // printDetails()
   //
   // Helper method which displays the basic details for this Attraction,
   // as well as the total booking fees received for this Attraction.
   public void printDetails()
   {
      System.out.printf("%s %s\n", "Attraction ID:", attractionID);
      System.out.printf("%s %s\n", "Description:", description);
      System.out.printf("%s $%.2f\n", "Attraction Fee:", admissionFee);
      System.out.printf("%s %s\n", "Total Bookings:", bookingCount);
      
      // call the calculateBookingCost() method to help figure out the
      // total fees 
      System.out.printf("%s $%.2f\n", "Total Booking Fees Received:",
                                      this.calculateBookingCost(bookingCount));
   }
}

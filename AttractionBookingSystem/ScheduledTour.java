import java.util.Scanner;

// STAGE 3 - REQUIREMENT A
// New subclass which extends the Attraction class
public class ScheduledTour extends Attraction
{
	// Define new instance variables for ScheduledTour
	private String tourDate;
	private int maxGroupSize;
	private String tourLeader;
	
	// STAGE 3 - REQUIREMENT B
	// Constructor to set up new tour and stores information in the instance variables
	public ScheduledTour(String attractionID, String description, double admissionFee,
			String tourDate, int maxGroupSize, String tourLeader)
	{
		super(attractionID, description, admissionFee);
		this.tourDate = tourDate;
		this.maxGroupSize = maxGroupSize;
		this.tourLeader = tourLeader;
	}
	
	// STAGE 3 - REQUIREMENT C
	// Mutator for maximum group size
	public void setGroupSize(int maxGroupSize)
	{
		this.maxGroupSize = maxGroupSize;
	}
	
	// STAGE 3 - REQUIREMENT D
	public double sellPasses(int numberOfTourists) throws BookingException
	{
		int currentGroup = getBookingCount();
		double ticketCost = 0;
		
		// Check group size does not exceed maxGroupSize
		if ((numberOfTourists + currentGroup) >= maxGroupSize)
		{
			throw new BookingException("Number of tourists exceeds max group size.");
		}
		else
		{
			//use parent sellPasses() method to add group to booking
			ticketCost = super.sellPasses(numberOfTourists);
		}	
				return ticketCost;
	}
	
	// STAGE 3 - REQUIREMENT E
	public void printDetails()
	{
		super.printDetails();
		System.out.printf("%s %s\n", "Tour Date: ", this.tourDate);
		System.out.printf("%s %d\n", "Maximum Group Size: ", this.maxGroupSize);
		System.out.printf("%s %s\n", "Tour Leader: ", this.tourLeader);
	}

	
}

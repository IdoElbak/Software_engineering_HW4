import java.util.*;

public class Park {

    private String name;
    private ArrayList<AmusementRide> rides;

    public Park(String name){
        this.name = name;
        this.rides = new ArrayList<AmusementRide>();
    }

    /**
     * This function gets a new ride and adds it to the list of rides.
     * @param newRide - the new ride that needs to be added to the park
     */
    public void add(AmusementRide newRide){
        rides.add(newRide);
    }

    /**
     * This function gets a ride and removes it from the list of rides
     * @param ride - the ride that needs to be removed
     */
    public void remove(AmusementRide ride){
        for(AmusementRide tempRide : rides){
            if(tempRide == ride){
                rides.remove(tempRide);
            }
        }
    }

    /**
     * This function starts all the rides one after another in the same order they were added.
     */
    public void startRides(){
        for(AmusementRide ride : rides){
            ride.startRide();
        }
    }

    public void addPerson(AmusementRide ride, Person person){
        for(AmusementRide tempRide : rides){
            if(tempRide == ride){
                tempRide.addPerson(person);
            }
        }
    }


}

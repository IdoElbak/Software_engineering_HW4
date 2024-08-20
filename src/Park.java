import java.util.*;

public class Park {
    private static int MAX_RIDES = 5;
    private String name;
    private AmusementRide[] rides;
    private int ride_count;

    public Park(String name){
        this.name = name;
        this.rides = new AmusementRide[MAX_RIDES];
        this.ride_count = 0;
    }

    /**
     * This function gets a new ride and adds it to the list of rides.
     * @param newRide - the new ride that needs to be added to the park
     */
    public void add(AmusementRide newRide){
        if(ride_count != 5) {
            rides[ride_count] = newRide;
            ride_count++;
        }
    }

    /**
     * This function gets a ride and removes it from the list of rides
     * Afterward, the function moves the entire rides array 1 slot back from the removal spot.
     * So the array won't have null in between rides.
     * @param ride - the ride that needs to be removed
     */
    public void remove(AmusementRide ride){
        boolean removedRide = false;
        for(int i = 0; i < MAX_RIDES && rides[i] != null; i++){
            if(rides[i] == ride){
                rides[i] = null;
                removedRide = true;
                ride_count--;
            }
            if(removedRide && (i != MAX_RIDES-1)){
                rides[i] = rides[i+1];
            }
        }
    }

    /**
     * This function starts all the rides one after another in the same order they were added.
     */
    public void startRides(){
        for(AmusementRide ride : rides){
            if(ride == null){
                break;
            }
            ride.startRide();
        }
    }

    /**
     * This function gets a person and the ride he wants to enter and adds him to that ride's queue.
     * @param ride - the ride which the person wants to ride
     * @param person - the person that wants to be added to the ride's queue.
     */
    public void addPerson(AmusementRide ride, Person person){
        for(AmusementRide tempRide : rides){
            if(tempRide == ride){
                tempRide.addPerson(person);
            }
        }
    }


}

public class AmusementRide {

    private final String name;
    private IsraeliQueue<Person> queue;
    private int maxCapacity;

    public AmusementRide(String name, int maxCapacity){
        this.maxCapacity = maxCapacity;
        this.name = name;
        this.queue = new IsraeliQueue<Person>();
    }

    /**
     * This function checks if the line to the ride is empty and if not it starts the ride.
     * After starting the ride the function prints the names of the people on the ride.
     */
    public void startRide(){
        if(queue.size() == 0){
            System.out.println("Ride is empty");
            return;
        }

        System.out.println("Currently using the ride:");
        int i = maxCapacity;
        for(Person person : queue){
            System.out.println(person);
            i--;
            if(i == 0){ break; }
        }
    }

    public void addPerson(Person person){
        this.queue.add(person);
    }

}

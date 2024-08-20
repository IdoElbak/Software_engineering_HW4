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
     * And removes the people that went on the ride from the queue.
     */
    public void startRide(){
        if(queue.size() == 0){
            System.out.println("Ride is empty.");
            return;
        }

        System.out.println("Currently using the ride:");

        for(int i = 0; i < maxCapacity && queue.size() != 0; i++){
            Person temp = queue.remove();
            System.out.println(temp);
        }

    }

    /**
     * This function gets that person's friend and adds him to the IsraeliQueue based on his friend.
     * If there is no friend then it doesn't matter because the "add" function takes that case into account.
     * @param person - a person that wants to go on a ride.
     */
    public void addPerson(Person person){
        Person friend = person.getFriend();
        this.queue.add(person, friend);
    }

}

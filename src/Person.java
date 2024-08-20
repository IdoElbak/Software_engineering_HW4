public class Person {

    private String name;
    private final int ID;
    private Person friend;

    public Person(String name, int ID, Person friend) {
        this.name = name;
        this.ID = ID;
        this.friend = friend;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person) {
            return this.ID == ((Person)obj).ID;
        }

        return false;
    }
}

public class Person implements Cloneable{

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


    /**Clones the person*/
    @Override
    public Person clone() {
        return new Person(this.name,this.ID,this.friend);
    }
}

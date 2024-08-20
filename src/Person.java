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

    @Override
    public int hashCode() {
        return this.ID;
    }

    public Person getFriend(){
        return friend;
    }

    /**Clones the person*/
    @Override
    public Person clone(){
        try {
            Person clone = (Person) super.clone();
            if(this.friend != null) {
                clone.friend = this.friend.clone();
            }
            return clone;
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }
}

import java.util.Iterator;

/**
 *
 * Data structure following the IsraeliQueue design.
 * <br><br>
 * Rational for design:
 * <br>
 * The Israeli queue is a queue of groups of objects, with the ability to add an object to an existing or new group.
 * The implementation is required using {@link Node} as the only additional data structure,
 * And therefore, a group is represented via the {@code Node<Node<Type>>} datatype.
 * */
public class IsraeliQueue<Type extends Cloneable> implements Iterable<Type>, Cloneable{

    /**Head group of the queue*/
    private Node<Node<Type>> head;

    /**Tail group of the queue*/
    private Node<Node<Type>> tail;

    /**the amount of elements in the queue*/
    private int size;

    /**Adds a value to a group as following:
     * <br>
     * If {@code friendValue} is found in one of the groups, then {@code value} is added to it.
     * <br>
     * Otherwise, a new group is opened at the end of the queue, with only {@code value} in it.*/
    public void add(Type value, Type friendValue){
        if(value == null){
            throw new InvalidInputException();
        }
        Node<Node<Type>> temp = head;
        while(temp != null){

            //Checks if friendValue is found in the currently checked group.
            if(temp.getValue().isContained(friendValue)){
                appendToGroupTail(temp.getValue(), value);
                this.size++;
                return;
            }
            temp = temp.getNext();

        }
        //In this case, we reach the end of the list, and should open a new group.
        //add(value) does this, and so we call it to avoid repetitive code.
        // also reaches here when the head == null. add(value) initializes the head and tail if needed.
        add(value);
    }

    /**
     * Adds a value to a new group at the end of the queue.
     * */
    public void add(Type value){
        if(value == null){
            throw new InvalidInputException();
        }
        if(this.tail == null){
            this.head = new Node<>(new Node<>(value));
            this.tail = this.head;
        }else {
            this.tail.setNext(new Node<>(new Node<>(value)));
            this.tail = this.tail.getNext();
        }
        this.size++;

    }

    /**Removes the head group from the queue and returns the group.
     * @throws EmptyQueueException If the queue is empty.*/
    public Type remove() throws EmptyQueueException{
        if(this.size == 0)
            throw new EmptyQueueException();
        size--;

        Type value = head.getValue().getValue();
        if(this.head.getValue().getNext() == null){
            this.head = this.head.getNext();
        }else {
            this.head.setValue(this.head.getValue().getNext());
        }
        if(head == null)
            tail = null;
        return value;

    }

    /**Returns the head group of the queue without removing it from the queue.
     * @throws EmptyQueueException If the queue is empty.*/
    public Type peek() throws EmptyQueueException{
        if(this.size == 0)
            throw new EmptyQueueException();

        return this.head.getValue().getValue();
    }

    public int size() {
        return size;
    }

    /**
     * Iterator used to iterate over the queue.
     * <br>
     * The iteration is done as follows:
     * <br>
     * <ul>
     *     <li>The head list is iterated over in its current arrangement.</li>
     *     <li>After iterating over the head list, the next group is iterated over.</li>
     *     <li>This continues until all groups have been iterated over, in order.</li>
     * </ul>
     * */
    @Override
    public Iterator<Type> iterator() {

        return new Iterator<Type>() {

            //Stores the next group that will be iterated over.
            Node<Node<Type>> nextGroup = head.getNext();

            //Stores the next value in current group which is being iterated over.
            Node<Type> nextValue = head.getValue();

            @Override
            public boolean hasNext() {
                if(nextValue != null)
                    return true;

                return nextGroup != null;
            }

            @Override
            public Type next() {
                if(nextValue == null){
                    nextValue = nextGroup.getValue();
                    nextGroup = nextGroup.getNext();
                }
                Type value = nextValue.getValue();
                nextValue = nextValue.getNext();

                return value;
            }

        };
    }

    /**Appends {@code value} to the end of {@code group}.*/
    private void appendToGroupTail(Node<Type> group, Type value){
        Node<Type> temp = group;
        while (temp.getNext() != null)
            temp = temp.getNext();

        temp.setNext(new Node<Type>(value));
    }

    /**Clones the queue in a deep-clone approach.*/
    @Override
    public IsraeliQueue<Type> clone() {
        try {
            IsraeliQueue<Type> cloneQueue = (IsraeliQueue<Type>)super.clone();

            if(head != null) {
                //Clone the queue's head.
                cloneQueue.head = this.head.clone();
                cloneQueue.tail = this.tail.clone();
            }
            return cloneQueue;
        }
        catch (Exception e){
            return null;
        }

    }
}

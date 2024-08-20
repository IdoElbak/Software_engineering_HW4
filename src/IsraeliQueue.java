import java.util.Iterator;

/**
 *
 * Data structure following the IsraeliQueue design.
 * <br><br>
 * Rational for design:
 * <br>
 * The israeli queue is a queue of groups of objects, with the ability to add an object to an existing or new group.
 * The implementation is required using {@link Node} as the only additional data structure,
 * and therefore, a group is represented via the {@code Node<Node<Type>>} datatype.
 * */
public class IsraeliQueue<Type extends Cloneable> implements Iterable<Type>, Cloneable{

    /**Head group of the queue*/
    private Node<Node<Type>> head;

    /**Tail group of the queue*/
    private Node<Node<Type>> tail;

    /**The group's size. Note that since an israeli queue considers a group to be a value,
     * this attribute refers to the number of groups in the queue.*/
    private int size;

    /**Adds a value to a group as following:
     * <br>
     * If {@code friendValue} is found in one of the groups, then {@code value} is added to it.
     * <br>
     * Otherwise, a new group is opened at the end of the queue, with only {@code value} in it.*/
    public void add(Type value, Type friendValue){

        Node<Node<Type>> temp = head;
        while(temp.getNext() != null){

            //Checks if friendValue is found in the currently checked group.
            if(temp.getValue().isContained(friendValue)){
                appendToGroupTail(temp.getValue(), friendValue);
            }

        }

        //In this case, we reach the end of the list, and should open a new group.
        //add(value) does this, and so we call it to avoid repetitive code.
        add(value);

    }

    /**
     * Adds a value to a new group at the end of the queue.
     * */
    public void add(Type value){

        this.tail.setNext(new Node<>(new Node<>(value)));
        this.tail = this.tail.getNext();
        this.size++;

    }

    /**Removes the head group from the queue and returns the group.
     * @throws EmptyQueueException If the queue is empty.*/
    public Type remove() throws EmptyQueueException{

        if(this.size == 0)
            throw new EmptyQueueException();

        Type value = head.getValue().getValue();
        if(this.head.getNext() == null){
            size--;
            this.head = this.head.getNext();
        }
        this.head.setValue(this.head.getValue().getNext());

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

            //Stores the current group which is being iterated over.
            Node<Node<Type>> currGroup = head;

            //Stores the current value in currGroup which is being iterated over.
            Node<Type> currValue = head.getValue();

            @Override
            public boolean hasNext() {
                if(currValue.getNext() != null)
                    return false;

                return currGroup.getNext() != null;
            }

            @Override
            public Type next() {
                if(currValue.getNext() == null){

                    currGroup = currGroup.getNext();
                    if(currGroup.getNext() == null)
                        return null;

                    currValue = currGroup.getValue();
                }

                else currValue = currValue.getNext();

                return currValue.getValue();

            }

        };
    }

    /**Appends {@code value} to the end of {@code group}.*/
    private void appendToGroupTail(Node<Type> group, Type value){

        Node<Type> temp = group;
        while (temp.getNext() != null)
            temp = temp.getNext();

        temp.setNext(new Node<>(value));

    }

    /**Clones the queue in a deep-clone approach.*/
    @Override
    public IsraeliQueue<Type> clone() {

        IsraeliQueue<Type> cloneQueue = new IsraeliQueue<>();

        //Clone the queue's head.
        cloneQueue.head = this.head.clone();

        Node<Node<Type>> cloneTemp = cloneQueue.head;
        Node<Node<Type>> temp = this.head;

        //Note that the check temp != this.tail is valid.
        //This is because temp iterates over the original list.
        //Once it reaches tail, temp will be a pointer to tail, meaning that temp != this.tail will be false.
        while(temp != this.tail){

            //Clone the queue's content except the head and tail.
            cloneTemp.setNext(temp.getNext().clone());
            temp = temp.getNext();

        }

        //Clone the queue's tail.
        cloneTemp.setNext(this.tail.clone());
        cloneQueue.tail = cloneTemp.getNext();

        return cloneQueue;

    }
}

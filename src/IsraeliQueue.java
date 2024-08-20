import java.util.Iterator;

public class IsraeliQueue<Type> implements Iterable<Type> {

    private Node<Node<Type>> head;
    private Node<Node<Type>> tail;
    private int queueSize;


    public IsraeliQueue() {}
    public IsraeliQueue(Type head) {
        this.head = new Node<>(new Node<>(head));
        this.tail = this.head;
    }

    private void appendToGroupTail(Node<Type> group, Type addon){

        Node<Type> temp = group;
        while (temp.getNext() != null)
            temp = temp.getNext();

        temp.setNext(new Node<>(addon));

    }

    public void add(Type value, Type friendValue) throws InvalidInputException{

        if(friendValue == null)
            throw new InvalidInputException();

        Node<Node<Type>> temp = head;
        while (temp != null){

            if(temp.getValue().isContained(value)){
                appendToGroupTail(temp.getValue(), friendValue);
                return;
            }

            temp = temp.getNext();

        }

        //Case in which friend was not found.
        tail.setNext(new Node<>(new Node<>(value)));
        tail = tail.getNext();



    }

    public void add(Type value){

        tail.setNext(new Node<>(new Node<>(value)));
        tail = tail.getNext();

    }

    public Node<Node<Type>> remove() throws EmptyQueueException{

        if(queueSize == 0)
            throw new EmptyQueueException();

        Node<Node<Type>> removedHead = head;
        head = head.getNext(); //Should head.next Be set to null ?
        queueSize--;

        return removedHead;

    }

    public Node<Node<Type>> peek() throws EmptyQueueException{

        if(queueSize == 0)
            throw new EmptyQueueException();
        return head;

    }

    public int size() {
        return queueSize;
    }

    @Override
    protected IsraeliQueue<Type> clone() {

        IsraeliQueue<Type> clone = new IsraeliQueue<>();
        clone.head = new Node<>(new Node<>(this.head.getValue().getValue()));
        return clone;
    }

    @Override
    public Iterator<Type> iterator() {
        return new Iterator<Type>() {
            private Node<Node<Type>> currGroup = head;
            private Node<Type> currValue = head.getValue();

            @Override
            public boolean hasNext() {
                if (currValue.getNext() != null) {
                    return true;
                } else return currGroup.getNext() != null;
            }

            @Override
            public Type next() {
                if (currValue.getNext() == null) {

                    if (currGroup.getNext() == null)
                        return null;

                    else {
                        currGroup = currGroup.getNext();
                        currValue = currGroup.getValue();
                    }

                } else currValue = currValue.getNext();

                return currValue.getValue();
            }

            public boolean groupsEnd(){

                return currValue.getNext() == null;

            }

            public boolean tailReached(){
                return currGroup.getNext() == null;
            }
        };
    }
}

import java.util.Iterator;

public class Node<E> implements Cloneable{
    private E value;
    private Node<E> next;
    public Node(E value, Node<E> next) {
        this.value= value;this.next= next;
    }
    public Node(E value) {
        this(value, null);
    }

    public E getValue() {
        return value;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setValue(E value) {
        this.value= value;
    }

    public void setNext(Node<E> next) {
        this.next= next;
    }

    public boolean isContained(E other){
        if (this.value.equals(other))
            return true;
        if (this.next == null)
            return false;
        return next.isContained(other);
    }

    public Node<E> clone(){
        Node<E> cloneNode = new Node<>(this.value);
        Node<E> cloneTemp = cloneNode;
        Node<E> temp = this;

        while(temp != null){
            cloneNode.setNext(new Node<>(temp.getValue()));
            cloneTemp = cloneTemp.getNext();
            temp = temp.getNext();
        }

        return cloneNode;
    }
}

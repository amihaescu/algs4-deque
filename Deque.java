import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by andrei on 7/18/14.
 */
public class Deque<Item> implements Iterable<Item> {
    protected class Node{
        protected Node prev;
        protected Node next;
        Item item;

        public Node(Item item){
            this.item = item;
        }

    }
    private Node firstNode;
    private Node lastNode;
    private int size;
    public Deque() {
        firstNode = lastNode = null;
        size = 0;

    }
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }                 // is the deque empty?
    public int size() {
        return size;
    }                       // return the number of items on the deque
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (firstNode == null){
            firstNode = new Node(item);
            if (lastNode == null){
                lastNode = firstNode;
            }
        }else {
            Node oldFirstNode = firstNode;
            firstNode = new Node(item);
            firstNode.next = oldFirstNode;
            oldFirstNode.prev = firstNode;

        }
        size++;


    }         // insert the item at the front
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (lastNode == null){
            lastNode = new Node(item);
            if (firstNode == null){
                firstNode = lastNode;
            }
        }else {
            Node oldLastNode = lastNode;
            lastNode = new Node(item);
            lastNode.prev = oldLastNode;
            //lastNode.next = new Node(item);
            oldLastNode.next = lastNode;

        }
        size++;

    }           // insert the item at the end
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        firstNode.next.prev = null;
        firstNode.item = null;
        firstNode = firstNode.next;
        size--;
        return firstNode.item;

    }               // delete and return the item at the front
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        lastNode.prev.next = null;
        lastNode.item = null;
        lastNode = lastNode.prev;
        size--;
        return lastNode.item;

    }                 // delete and return the item at the end
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(firstNode);

    }        // return an iterator over items in order from front to end
    private class DequeIterator<Item> implements Iterator<Item>{
        private Node current;
        public DequeIterator(Node n){
            this.current = n;
        }
        @Override
        public boolean hasNext(){
            return (current != null);
        }
        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
        @Override
        public Item next(){
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = (Item) current.item;
            current = current.next;
            return item;

        }

    }
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("hello");
        deque.addLast("dear");
        deque.addLast("world!");
        for (String s: deque){
            System.out.println(s);
        }
        System.out.println("size "+deque.size());
        System.out.println("new first item: "+deque.removeFirst());
        System.out.println("size "+deque.size());
        System.out.println("new first item: "+deque.removeFirst());
        System.out.println("size "+deque.size());


    }
}

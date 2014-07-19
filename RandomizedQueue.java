import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by andrei on 7/18/14.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int N;
    public RandomizedQueue() {
        array = (Item[]) new Object[2];

    }                 // construct an empty randomized queue
    public boolean isEmpty() {
        return N == 0;
    }                // is the queue empty?
    public int size() {
        return N;
    }               // return the number of items on the queue
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == array.length) resize(2*array.length);
        array[N++] = item;
    }           // add the item

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        exchange(array, StdRandom.uniform(N), --N);
        Item result = array[N];
        array[N] = null;
        if (N > 0 && N == array.length/4) resize(array.length/2);
        return result;

    }          // delete and return a random item
    private void exchange(Item[] items, int i, int j){
        if (i == j)
            return;
        Item swap = items[i];
        items[i] = items[j];
        items[j] = swap;
    }
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[StdRandom.uniform(N)];
    }         // return (but do not delete) a random item
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }        // return an independent iterator over items in random order
    private class RandomizedQueueIterator implements Iterator<Item>{
        private int count = N;
        private int[] idx;

        public RandomizedQueueIterator() {
            idx = new int[count];
            for (int i = 0; i < count; i++)
                idx[i] = i;
            StdRandom.shuffle(idx);
        }

        public boolean hasNext() {
            return count > 0;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return array[idx[--count]];
        }

        public void remove() {
            throw new UnsupportedOperationException("Can't remove");
        }

    }
    public static void main(String[] args){
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        System.out.println("Randomized queue is empty? "+rq.isEmpty());
        rq.enqueue("Hello");
        rq.enqueue("dear");
        rq.enqueue("world!");

        System.out.println("Randomized queue is empty? "+rq.isEmpty());
        for (String s:rq){
            System.out.println(s);
        }

        rq.dequeue();
        System.out.println();

        for (String s:rq){
            System.out.println(s);
        }

    }   // unit testing
}


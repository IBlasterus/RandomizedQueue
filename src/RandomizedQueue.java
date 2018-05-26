import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from
 * items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    /**
     * Pointers
     */
    int[] pointers;

    /**
     * Items
     */
    Item[] items;

    /**
     * Last pointer
     */
    int lastPointer;

    /**
     * Pointer to the end of the queue
     */
    int tail;

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {
        this.lastPointer = -1; // sort of null
        this.pointers = new int[1];
        this.pointers[0] = -1; // sort of null
        this.items = (Item[]) new Object[1];
    }

    /**
     * @return is the randomized queue empty?
     */
    public boolean isEmpty() {
        return this.lastPointer == -1;
    }

    /**
     * @return the number of items on the randomized queue
     */
    public int size() {
        return this.lastPointer + 1;
    }

    /**
     * Add the item
     *
     * @param item item
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item can't be null");
        if (this.lastPointer + 1 > this.pointers.length - 1) this.resize(2 * this.pointers.length);
        this.lastPointer++;
        this.pointers[this.lastPointer] = this.tail;
        this.items[this.tail] = item;
        this.tail++;
    }

    /**
     * @return remove and return a random item
     */
    public Item dequeue() {
        if (this.isEmpty()) throw new NoSuchElementException("queue is empty");
        int randomPointer = StdRandom.uniform(this.lastPointer + 1);
        Item item = this.items[this.pointers[randomPointer]];
        this.items[this.pointers[randomPointer]] = null;
        this.pointers[randomPointer] = this.pointers[this.lastPointer];
        this.pointers[this.lastPointer] = -1; // sort of null
        this.lastPointer--;
        if (this.isEmpty()) {
            this.tail--;
            return item;
        }
        if (this.lastPointer <= this.pointers.length / 4) this.resize(this.pointers.length / 2);
        return item;
    }

    /**
     * @return a random item (but do not remove it)
     */
    public Item sample() {
        return this.items[this.pointers[StdRandom.uniform(this.lastPointer + 1)]];
    }

    /**
     * @return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    /**
     * Resize arrays items and pointers
     *
     * @param capacity new size of arrays
     */
    private void resize(int capacity) {
        if (this.isEmpty()) throw new IndexOutOfBoundsException("you can't resize empty queue");
        if (capacity < 1) throw new IllegalArgumentException("capacity can be more than 0");
        if (capacity < this.lastPointer + 1)
            throw new IllegalArgumentException("capacity can be more than number of entity in queue");
        Item[] itemsCopy = (Item[]) new Object[capacity];
        int[] pointersCopy = new int[capacity];
        for (int i = 0; i <= this.lastPointer; i++) {
            itemsCopy[i] = this.items[this.pointers[i]];
            pointersCopy[i] = i;
        }
        for (int i = this.lastPointer + 1; i < capacity; i++) {
            pointersCopy[i] = -1; // sort of null
        }
        this.items = itemsCopy;
        this.pointers = pointersCopy;
        this.tail = this.lastPointer + 1;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        int[] iPointers = new int[pointers.length];
        int iLastPointer = lastPointer;

        RandomizedQueueIterator() {
            System.arraycopy(pointers, 0, this.iPointers, 0, pointers.length);
        }

        @Override
        public boolean hasNext() {
            return this.iLastPointer > -1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("method unsupported for iterator of RandomizedQueue");
        }

        @Override
        public Item next() {
            if (this.iLastPointer < 0) throw new NoSuchElementException("no more items in iteration");
            Item item = items[this.iLastPointer];
            this.iLastPointer--;
            return item;
        }
    }

    /**
     * Unit testing (optional)
     *
     * @param args arguments
     */
    public static void main(String[] args) {

    }
}

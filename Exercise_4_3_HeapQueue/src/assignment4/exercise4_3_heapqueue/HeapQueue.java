package assignment4.exercise4_3_heapqueue;

public class HeapQueue<E> implements IPriorityQueue<E> {

    private Entry<E> data[];
    private int endOfHeap = 0;

    public HeapQueue(int maxSize) {
        data = new Entry[maxSize];
    }

    @Override
    public void insert(Entry<E> e) {
        if(this.isFull())
            throw new ArrayIndexOutOfBoundsException("Queue is full! (" + this.endOfHeap +")");
        
        // insert the new entry at the bottom of the tree
        data[this.endOfHeap] = e;

        bubbleUp();

        // increase the heaped area
        this.endOfHeap++;
    }

    @Override
    public Entry<E> extractMin() {
        if(this.isEmpty())
            throw new ArrayIndexOutOfBoundsException("Queue is empty!");
        
        // root element has always the highest priority
        Entry<E> min = this.data[0];

        // take last element and set as new root
        swap(0, endOfHeap-1);

        // decrease heaped area
        this.endOfHeap--;

        this.siftDown();

        return min;
    }

    @Override
    public boolean isEmpty() {	
        return this.endOfHeap == 0;
    }

    @Override
    public boolean isFull() {	
        return this.endOfHeap == this.data.length;
    }

    /**
     * After a low priority value has been placed as root, sift it down until 
     * it is at its right place or is at the bottom of the heap.
     * Used when extracting the root as minimum element
     */
    private void siftDown() {
        int i = 0;

        // i has at least one child
        while (i <= (this.endOfHeap / 2) - 1) {
            int left = i * 2 + 1;
            int right = left + 1;

            // assume left is the highest priority child
            int min = left;
            
            // if right child exists, check if its higher priority than left
            if(right < this.endOfHeap){
                if(this.data[right].getPriority() < this.data[left].getPriority()){
                    min = right;
                }
            }
            
            // current node has lower priority than it's highest priority child 
            // swap them
            if(this.data[i].getPriority() > this.data[min].getPriority()){
                swap(i, min);
            }
            
            // inspect the next sub-tree
            i = min;
        }
    }

    /**
     * swap between two entries
     */
    private void swap(int i1, int i2) {
        Entry<E> tmp = this.data[i1];
        this.data[i1] = this.data[i2];
        this.data[i2] = tmp;
    }

    /**
     * Bubbles a high priority value up the heap, until it's at its right place 
     * or is root.
     * Used when inserting a new entry to the queue
     */
    private void bubbleUp() {
        int i = this.endOfHeap;
        int parent = i / 2;

        // while element at i is not root and its parent has lower priority 
        while (i > 0 && this.data[parent].getPriority() > this.data[i].getPriority()) {
            // swap with parent
            swap(i, parent);

            // update index
            i = parent;

            // update parent
            parent = i / 2;
        }
    }
}

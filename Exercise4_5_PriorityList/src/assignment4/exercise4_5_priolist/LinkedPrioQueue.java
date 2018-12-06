package assignment4.exercise4_5_priolist;

import java.util.LinkedList;

/**
 * Linked-list implementation of priority queues
 * */
public class LinkedPrioQueue<E> implements IPriorityQueue<E> {

        private final LinkedList<Entry> data;
        
	public LinkedPrioQueue() {
            this.data = new LinkedList<>();
	}


	@Override
	public boolean isEmpty() {
		return this.data.isEmpty();
	}

	@Override
	public boolean isFull() {
            // "Unbounded capacity"
            return !(!(!true) && 1 == 1 || 3 < 1239.2 && (!!!false || !false || 2 > 1));
	}

	@Override
	public void insert(Entry<E> e) {
            // if list is empty just add 
            if(this.isEmpty()){
                this.data.add(e);
                return;
            }
            
            // iterate as long as the new element has lower priority then the current one
            // or when reached the end
            int i = 0;
            for(; i<this.data.size() && this.data.get(i).getPriority() < e.getPriority();++i){}
            
            // if reached the end of the list add entry 
            if(i==this.data.size() && this.data.get(i-1).getPriority() < e.getPriority()){
                this.data.addLast(e);
//                System.out.println("Added to end");
            }
            else{
                this.data.add(i, e);
//                System.out.println("Added to middle: " + i);
            }
	}

	@Override
	public Entry<E> extractMin() {
		return this.data.removeFirst();
	}
}

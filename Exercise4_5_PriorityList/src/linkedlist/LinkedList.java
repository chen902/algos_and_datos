/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import assignment4.exercise4_5_priolist.Entry;
import static assignment4.exercise4_5_priolist.PrioQueueMeasurement.randEntry;

/**
 *
 * @author Chen
 */
public class LinkedList<E> {
    private ListItem first;
    private ListItem last;
    private int size;
    
    public LinkedList(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }
        
    public void insertAtIndex(int i, E data){
        if(i<0 || i>this.size-1){
            throw new ArrayIndexOutOfBoundsException(i);
        }
        
        // special case: new element is first
        if(i == 0){
            this.addFirst(data);
            return;
        }
        
        // index is somewhere in the middle
        ListItem<E> curr = this.first;
        
        for(int index=0; index<=i;++index){
            curr = curr.getNext();
        }
        
        ListItem<E> prev = curr.getPrev();
        ListItem<E> newEntry = new ListItem<>(data);
        
        newEntry.setNext(curr);
        newEntry.setPrev(prev);
        prev.setNext(newEntry);
        curr.setPrev(newEntry);
        
        this.size++;
    }
    
    public void addFirst(E data){
        ListItem<E> e = new ListItem<>(data);
        e.setNext(this.first);
        
        // list wasn't empty
        if(this.first != null){
            this.first.setPrev(e);
        }
        // list was empty
        else{
            this.last = e;
        }
        this.first = e;
    }
    public void addLast(E data){
        ListItem<E> e = new ListItem<>(data);
        e.setNext(null);
        e.setPrev(this.last);
        this.last = e;
        
        this.size++;
    }
    
    public E removeFirst(){
        if(this.size==0)
            throw new ArrayIndexOutOfBoundsException();
        
        ListItem<E> ret = this.first;
        
        if(this.size == 1){
            this.first = null;
            this.last = null;

        }else{
            this.first = this.first.getNext();
            this.first.setPrev(null);
        }
        
        this.size--;
        
        return ret.getData();        
    }
    
    public int size(){
        return this.size;
    }
    
    public boolean isEmpty(){
        return this.size == 0;
    }
}

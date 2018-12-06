/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author Chen
 */
public class ListItem<E> {
    private E data;
    private ListItem next;
    private ListItem prev;
    
    public ListItem(E data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    public E getData(){
        return this.data;
    }
    
    public ListItem getNext(){
        return this.next;
    }
    
    public ListItem getPrev(){
        return this.prev;
    }
    
    public void setNext(ListItem next){
        this.next = next;
    }
    
    public void setPrev(ListItem prev){
        this.prev = prev;
    }
}

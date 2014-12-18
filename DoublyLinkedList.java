/*
 * DoublyLinkedList
 * This class implements the Java List interface using a doubly-linked list.
 * Two nested classes are included:  ListNode and DoublyLinkedListIterator.
 * Date: 10/21/2014
 * Author: Carlande Nicolas
 */
import java.util.*;

public class DoublyLinkedList<T> extends AbstractList<T> {
   int size;  // number of data items in the list
   ListNode header, trailer;  // sentinel nodes
   
   /*
    * ListNode
    * 
    * This class represents one node in a doubly linked list.
    */
   protected class ListNode {
      T datum;
      ListNode prior, next;
      
      ListNode(){
         this(null);
      }
      
      ListNode(T data){
         this(data,null,null);
      }
      
      ListNode(T data, ListNode prior, ListNode next){
         this.datum = data;
         this.prior = prior;
         this.next = next;
      }
   } // end of class ListNode
   
   /*
    * Constructs an empty list.
    */
   DoublyLinkedList(){
      header = new ListNode(null);
      trailer = new ListNode(null);
      header.next = trailer;
      trailer.prior = header;
      size = 0; 
   }
   
   /*
    * Returns a reference to the nth node in the list
    */
   private ListNode getNthNode(int n) {
      ListNode temp = header.next;
      if(n>0){
         for(int i=0; i<n; i++)
            temp=temp.next;
      }
      return temp;
   }
   
   /*
    * Returns a count of the number of elements in the list.
    */
   public int size() {
      return size;
   }
   
   /*
    * Returns the data item at the given position in the list.
    */
   public T get(int position) {
      return getNthNode(position).datum;
   }
   
   /*
    * Replaces the item at the given position with the
    * given data item.  The return value is the item
    * that is replaced.
    */
   public T set(int position, T data) {
      T temp = getNthNode(position).datum;
      if(temp==null)
         throw new NullPointerException();
      else
         getNthNode(position).datum=data;
      return temp;
   }
   
   /*
    * Inserts the given data item at the end of the list.
    */
   public boolean add(T data) {
      ListNode add = new ListNode(data, trailer.prior, trailer);
      trailer.prior = add;
      trailer.prior.prior.next = add;
      size++;
      return true;
   }
   
   /*
    * Inserts the given data item at the given position in the list.
    */
   public void add(int position, T data) {
      if(position<0)
         throw new IndexOutOfBoundsException();
      ListNode nth = getNthNode(position);
      ListNode add = new ListNode(data, nth.prior, nth);
      nth.prior = add;
      nth.prior.prior.next = add;
      size++;
   }
   
   /*
    * Removes the element at a given index in the list.
    */
   public T remove(int index) {
      ListNode nth = getNthNode(index);
      T removed = nth.datum;
      nth.next.prior = nth.prior;
      nth.prior.next = nth.next;
      size--;
      return removed;
   }
   
   /*
    * Searches the list for a given object.  Returns true if found,
    * false otherwise.
    */
   public boolean contains(Object o){
      boolean solved=false;
      Iterator<T> it = this.iterator();
      while(!solved&&it.hasNext()){
         if(it.next().equals(o))
            solved=true;
      }
      return solved;
   }
   
   /*
    * Deletes all elements from the list.
    */
   public void clear() {
      header.next = trailer;
      header.prior = trailer;
      trailer.next = header;
      trailer.prior = header;
      this.size=0;
   }
   
   /*
    * Determines if the list is empty.
    */
   public boolean isEmpty() {
      return (size==0);
   }
   
   /*
    * Returns an iterator for this list.  The iterator is written
    * as an anonymous class.
    */
   public Iterator<T> iterator(){
      return new Iterator<T>(){
         ListNode current = header.next;
         
         public boolean hasNext(){
            return current!=trailer;
         }
         public T next(){
            T item = current.datum;
            current = current.next;
            return item;
         }
         public void remove(){
            ListNode prior = current.prior.prior;
            prior.next = current;
            current.prior = prior;
            size--;
         }
      };
   }
   
}


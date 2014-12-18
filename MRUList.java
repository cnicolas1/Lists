/**
 * MRUList
 * Date: 10/21/2014
 * Author: Carlande Nicolas
 * The MRUList class is a subclass of DoublyLinkedList.
 * When an item in the MRUList is modified, it is pushed to the front of the list.
 */

import java.util.*;

class MRUList<T> extends DoublyLinkedList<T> {
   
   /**
    * Checks whether an item is in the list.
    * If so, it is moved to the front of the list and returns true.
    * Otherwise, it returns false.
    */
   public boolean contains(Object item){
      boolean solved=false;
      Iterator<T> it = this.iterator();
      while(!solved&&it.hasNext()){
         if(it.next().equals(item)){
            this.add((T)item);
            solved=true;
         }
      }
      return solved;
   }
   
   /**
    * Adds an item to the list.
    */
   public boolean add(T item){
      super.add(0,item);
      return true;
   }
   
   /**
    * Adds an item to the front of the list, even if there's an index input.
    */
   public void add(int index, T item)
   {
      super.add(0,item);
   }
}
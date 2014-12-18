/**
 * MRUListTest
 * Date: 10/21/2014
 * Author: Carlande Nicolas
 * This class tests the methods of the MRUList class.
 */

import junit.framework.TestCase;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class MRUListTest extends TestCase {
   
      public void testMRUList() //tests the constructor of MRUList
   {
      MRUList<String> test = new MRUList<String>();
      LinkedList<String> standard = new LinkedList<String>();
      assertEquals("Size after construction", standard.size(), test.size);
   }
   
   public void testSize() //tests the size method of MRUList
   {
      MRUList<String> test = new MRUList<String>(); 
      LinkedList<String>   standard = new LinkedList<String>();
      assertEquals( "Size after construction", standard.size(), test.size);
      test.add("hello");
      standard.add("hello");
      assertEquals( "Size after add", standard.size(), test.size);
   }
   
   public void testAdd() throws FileNotFoundException //tests the add method of MRUList
   {  
      MRUList<String> test = new MRUList<String>(); 
      LinkedList<String>   standard = new LinkedList<String>();
      Scanner s = new Scanner(new File("emma_mini.txt"));
      while (s.hasNext())
      {
         String str = s.next();
         standard.add(0,str); //MRU adds at 0th index; standard must as well to compare correctly
         test.add(str);
      }
      s.close();
      assertEquals("Size after add", standard.size(), test.size);
      for(int i=0; i<standard.size(); i++)
         assertEquals( "Each "+i+"th element", standard.get(i), test.get(i));
   }
    
   public void testSet() throws FileNotFoundException //tests the set() method for MRUList
   {
      MRUList<String> test = new MRUList<String>(); 
      LinkedList<String>   standard = new LinkedList<String>();
      Scanner s = new Scanner(new File("emma_mini.txt"));
      while (s.hasNext())
      {
         String str = s.next();
         standard.add(str); //MRU adds at 0 so list must add at end; when set reverses the list later, they should be equal.
         test.add(str);
      }
      s.close();
      
      int k=test.size-1;
      for(int i=0; i<test.size/2&&k>0; i++)
      {
         String a=test.get(i);
         String b=test.get(k);
         test.set(i,b);
         test.set(k,a);
         k--;
      }
      
      assertEquals( "Size after rearrangements", standard.size(), test.size);
      for(int i=0; i<test.size; i++)
         assertEquals( "Each "+i+"th element", standard.get(i), test.get(i));
   }
   
   public void testRemove() throws FileNotFoundException //tests the remove() method for MRUList 
   {
      MRUList<String> test = new MRUList<String>(); 
      LinkedList<String>   standard = new LinkedList<String>();
      Scanner s = new Scanner(new File("emma_mini.txt"));
      while (s.hasNext())
      {
         String str = s.next();
         standard.add(0,str);
         test.add(str);
      }
      s.close();
      
      for(int i=0; i<test.size; i+=2)
      {
         test.remove(i);
         standard.remove(i);
      }
      
      for(int i=0; i<test.size; i++)
         assertEquals( "Each "+i+"th element", standard.get(i), test.get(i));
   }
   
   public void testIsEmpty() //tests the isEmpty() method for MRUList
   {
      MRUList<String> test = new MRUList<String>(); 
      assertTrue(test.isEmpty());
      LinkedList<String>   standard = new LinkedList<String>();
      assertTrue(standard.isEmpty());
   }

   public void testClear() //tests the clear() method for MRUList
   {
      MRUList<String> test = new MRUList<String>(); 
      LinkedList<String>   standard = new LinkedList<String>();
      test.add("hello");test.add("goodbye");test.add("beatles");
      standard.add("hello");standard.add("goodbye");standard.add("beatles");
      test.clear();
      standard.clear();
      assertEquals( "Size after clear", standard.size(), test.size);
   }
   
   public void testContains() //tests the contains() method for MRUList
   {
      MRUList<String> test = new MRUList<String>(); 
      LinkedList<String>   standard = new LinkedList<String>();
      test.add("hello");test.add("goodbye");test.add("beatles");
      standard.add("hello");standard.add("goodbye");standard.add("beatles");
      assertEquals("Contains goodbye",standard.contains(new String("goodbye")), test.contains(new String("goodbye")));
   }
}



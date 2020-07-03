package deber3_orderedlists;

import java.util.Iterator;

/**
 * @author EAMT
 */
public class InsertionSort {
    /** Insertion-sort of a positional list of integers into nondecreasing order
     * @param list */
  public static void insertionSort(PositionalList<Integer> list) {
    Position<Integer> marker = list.first();         // last position known to be sorted
    while (marker != list.last()) {
      Position<Integer> pivot = list.after(marker);
      int value = pivot.getElement();                // number to be placed
      if (value > marker.getElement())               // pivot is already sorted
        marker = pivot;
      else {                                         // must relocate pivot
        Position<Integer> walk = marker;             // find leftmost item greater than value
        while (walk != list.first() && list.before(walk).getElement() > value)
          walk = list.before(walk);
        list.remove(pivot);                          // remove pivot entry and
        list.addBefore(walk, value);                 // reinsert value in front of walk
      }
    }
  }
  /*Return an intercalated and sort list of two lists*/
  public static LinkedPositionalList<Integer> intercalate(LinkedPositionalList<Integer> list1, LinkedPositionalList<Integer> list2){
      //Linked list to return
      LinkedPositionalList<Integer> list3 = new LinkedPositionalList<>(); 
      
      //Uses two fors in case lists have no the same size();
      for(Iterator<Integer> it = list1.iterator(); it.hasNext();){
          list3.addFirst(it.next());
      }
      
      for(Iterator<Integer> it = list2.iterator(); it.hasNext();){
          list3.addFirst(it.next());
      }     
      
      insertionSort(list3);
      return list3;
  }
}
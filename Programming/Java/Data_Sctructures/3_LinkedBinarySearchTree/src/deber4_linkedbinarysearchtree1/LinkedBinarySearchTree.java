package deber4_linkedbinarysearchtree1;

import java.util.List;

/**
 * @author EAMT
 * @param <E>
 */
public class LinkedBinarySearchTree<E> extends LinkedBinaryTree<E> {
    
    //Inheritance constructors from LinkedBinaryTree<E>
    LinkedBinarySearchTree(){super();}    
    LinkedBinarySearchTree(E e){super(e);}
    
    
    public void add(E e) {
        Node<E> temp = new Node<>(e, null, null, null);
        Comparable<E> comparableE = (Comparable<E>)e;

        if (isEmpty())
            root = temp;
        else {
            Node<E> current = root;
            boolean added = false;

            while (!added) {
                if (comparableE.compareTo(current.getElement()) < 0)
                    if (current.getLeft() == null){
                        System.out.println("Adding at left: \n" + e.toString());
                        current.setLeft(temp); 
                        added = true;
                } 
                else
                    current = current.getLeft();
                else
                    if (current.getRight() == null) {
                        System.out.println("Adding at right: \n" + e.toString());
                        current.setRight(temp);
                        added = true;
                    } 
                    else
                        current = current.getRight();
         }
      }
      size++;
    }  
    
    public E remove (E removeE) { 
        E result = null;
        System.out.println("\nRemoving...\n" + removeE.toString());
        if (!isEmpty())
            if (((Comparable)removeE).equals(root.getElement())) {
                //System.out.println("\nRemoving root...\n" + root.getElement().toString());
                root.getElement().toString();
                result = root.getElement();
                root = replace (root);
                size--;
            } 
        else {
            Node<E> current = root;
            Node<E> parent = root;
            boolean found = false;

            if (((Comparable)removeE).compareTo(root.getElement()) < 0){
                current = root.getLeft();
                //System.out.println("\nRemoving left child of root...\n" + current.getElement().toString());
            }
            else{
                current = root.getRight();
                //System.out.println("\nRemoving right child of root...\n" + current.getElement().toString());
            }

            while (current != null && !found) {
                if (removeE.equals(current.getElement())) {
                    found = true;
                    size--;
                    result = current.getElement();
                    
                    if (current == parent.getLeft()){
                        parent.setLeft(replace(current));
                        //System.out.println("\nRemoving...\n" + current.getElement().toString());
                    }
                    else{
                        //System.out.println("\nRemoving...\n" + current.getElement().toString());
                        parent.setRight(replace(current));
                    }
                } 
                else{
                    parent = current;
                    if (((Comparable)removeE).compareTo(current.getElement()) < 0){
                        //System.out.println("\nRemoving...\n" + current.getElement().toString());
                        current = current.getLeft();
                    }
                    else{
                        //System.out.println("\nRemoving...\n" + current.getElement().toString());
                        current = current.getRight();
                    }
                 } 
            }
            if (!found)
                System.out.println("Element NO founded!!!");
            else
                System.out.println("Successfull remove!!!");
         }
      return result;
   }  // method removeElement
   
    public Node<E> replace (Node<E> node) {
        Node<E> rep = null;
        if (node.getLeft() == null && node.getRight() == null)
            rep = null;
        else if (node.getLeft() != null && node.getRight() == null){
            rep = node.getLeft();
            //remove(rep);
        }
        else if (node.getLeft() == null && node.getRight() != null)
            rep = node.getRight();
        else{
            Node<E> current = node.getRight();
            Node<E> parent = node;

            while (current.getLeft() != null) {
               parent = current;
               current = current.getLeft();
            }

            if (node.getRight() == current)
               current.setLeft(node.getLeft());
            else{
                parent.setLeft(current.getRight());
                current.setRight(node.getRight());
                current.setLeft(node.getLeft());
            }
            rep = current;
      }
      return rep;
   }  // method replacement
    
    //Indicates if E is in Tree
    public boolean contains(E element){
        List<Position<E>> TreeSearch;
        TreeSearch = (List<Position<E>>) positions(); //inOrder transversal
        
        for(int i = 0; i < TreeSearch.size(); i++){
            if(TreeSearch.get(i).getElement().equals(element))
                return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        List<Position<E>> TreeString;
        TreeString = (List<Position<E>>) positions();
        StringBuilder sb = new StringBuilder("");
        
        for(int i = 0; i < TreeString.size(); i++){
            sb.append("\n" + (i+1) + TreeString.get(i).getElement().toString());
        }
        return sb.toString();
   }
}
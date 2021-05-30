import java.lang.Math; 
import java.io.*;
import java.util.*;
public class A3SetMapEC{
   public static List<String> readInFile(String filename) {
      List<String> input = new ArrayList<>();
      try (Scanner sin = new Scanner(new FileReader(filename))){
         while (sin.hasNextLine()){
	    input.add(sin.nextLine());
         }
      } catch (FileNotFoundException e){
	 e.printStackTrace();
      }
      return input;
  }
   public static void printByFrequency(List<String> n){
       //your code starts here...
	   TreeMap<String, Integer> names = new TreeMap<String, Integer>();
	   for(String w: n) {
		   Integer i = names.get(w);
		   if(i == null) {
			   names.put(w, 1);
		   } else {
			   names.put(w, i + 1);
		     }
	   }
	   
	   
	   for (String i : names.keySet()) {
			  System.out.println("Name: " + i + " Frequency: " + names.get(i) + " ");
	   }
   }
   
   
   public static void main(String[] args){
      List<String> names = readInFile("A3input.txt");
      printByFrequency(names);
      
     
      int[] num = {24,4,-243,-345,246,-45,-34,23,346,65,75,-73,-3,54,63,-35,-23,86,-4,29};
      int i = sumZero(num); //7
      if (i != -1)System.out.println("Pair value: " + num[i]);
      
      AVLtree<Integer> tr = new AVLtree<Integer>();
      tr.add(4);
      tr.add(5);
      System.out.println("Tree Height:" + tr.height());//1
      tr.print();
      tr.add(6);
      System.out.println("Tree Height:" + tr.height());//1
      tr.print();
      tr.add(1);
      System.out.println("Tree Height:" + tr.height());//2
      tr.print();
      tr.add(2);
      System.out.println("Tree Height:" + tr.height());//2
      tr.print();
      tr.add(0);
      System.out.println("Tree Height:" + tr.height());//2
      tr.print();
      tr.add(3);
      System.out.println("Tree Height:" + tr.height());//3
      tr.print();
      tr.print2();//2, 1, 0, 5, 4, 3, 6,
      Integer x = 1;
      Integer[] v1 = {x,x,x,null,null,x,x,null,x,null,null,null,null};
      Integer[] v2 = {x,x,null,x,x,x,null,x,x,null,x,null,null,x,x,null,null,null,x,null,null,x,x,x,null,null,null,null,null};
      Btree<Integer> bt = new Btree<Integer>(v1);
      System.out.println("Small tree longest zig zag: " + bt.longestZigZag()); //3
      bt = new Btree<Integer>(v2);
      System.out.println("Big tree longest zig zag: " + bt.longestZigZag()); //4
      
      Integer[] v3 = {3,2,6,1,null,5,7,null,null,4,null,null,null,null,null};
      bt = new Btree<Integer>(v3);
      if (bt.isAVLtree()) System.out.println("v3 answer is correct");
      else System.out.println("v3 answer is wrong");
      
      Integer[] v4 = {3,2,6,null,null,5,7,4,null,null,null,null,null}; 
      bt = new Btree<Integer>(v4);
      if (bt.isAVLtree()) System.out.println("v4 answer is wrong");
      else System.out.println("v4 answer is correct");
      
      //Integer[] v5 = {2, 1, 0, 5, 4, 3, 6};
      //bt = new Btree<Integer>(v5);
      //System.out.println(bt.isAVLtree());
   }
   private static int sumZero(int[] num) {
	   //your code starts here...
       int index = 0; // {24,4,-243,-345,246,-45,-34,23,346,65,75,-73,-3,54,63,-35,-23,86,-4,29}
	   int start_num = 0;
	   int current_num = 0;
	   while(index < num.length) {
			start_num = num[index];
			for(int i = index + 1; i < num.length; i++) {
				current_num = num[i];
				//System.out.println("index: " + index + " start: " + start_num + " current: " + current_num);
				if(start_num + current_num == 0) {
					return index;
				}
			}
			index++;
		}
	   return -1;
   }
}
class AVLtree<E extends Comparable<? super E>>{
   private static class AVLNode<E extends Comparable<? super E>>{
      private E element;
      private AVLNode<E> parent;
      private AVLNode<E> left;
      private AVLNode<E> right;
      int height;
      public AVLNode(E e){
	  this(e, null, null);
     }
      public AVLNode(E e, AVLNode<E> l, AVLNode<E> r){
	 	element = e;
	 	left = l;
	 	right = r;
         height = 0;
         if (l != null && r != null) height = Math.max(l.height, r.height) + 1;
         else if (r != null) height = r.height + 1;
         else if (l != null) height = l.height + 1;
      }
      public E getE(){
	 return element;
      }
      public AVLNode<E> getParent(){
	 return parent;
      }
      public AVLNode<E> getLeft(){
	 return left;
      }
      public AVLNode<E> getRight(){
	 return right;
      }
      public int getHeight(){
	 return height;
      }
      public void setE(E e){
	 element = e;
      }
      public void setParent(AVLNode<E> p){
	 parent = p;
      }
      public void setLeft(AVLNode<E> l){
	 left = l;
      }
      public void setRight(AVLNode<E> r){
	 right = r;
      }
      public void setHeight(int h){
	 height = h;
      }
   }
   private AVLNode<E> root;
   private int size;
   public AVLtree(){
      root = null;
      size = -1;
   }
   private AVLNode<E> balance(AVLNode<E> n){
      if (n == null || isLeaf(n))
         return n;
      int bFactor = height(n.left) - height(n.right);
      if (bFactor > 1){
         if (height(n.left.left) >= height(n.left.right))
            n = rotateWithLeftChild(n);
         else
            n = doubleRotateLeftChild(n);
      }
      else if (bFactor < -1){
         if (height(n.right.right) >= height(n.right.left))
	    n = rotateWithRightChild(n);
         else
	    n = doubleRotateRightChild(n);
      }
      else
         n.height = Math.max(height(n.left), height(n.right)) + 1;
      return n;
   }
   public int height(){
      return height(root);
   }
   private int height(AVLNode<E> n){
      if (n == null) return -1;
      return n.height;
   }
   private boolean isLeaf(AVLNode<E> n){
      if (n == null || n.left != null || n.right != null) return false;
      return true;
   }
   private AVLNode<E> rotateWithLeftChild(AVLNode<E> n1){
      AVLNode<E> n2 = n1.left;
      //your code starts here...
      n1.left = n2.right;
      n2.right = n1;
      n1.height =  Math.max(height(n1.left), height(n1.right)) + 1;
      n2.height = Math.max(height(n2.left), height(n2.right)) + 1;
      n2.setParent(n1.getParent());
      n1.setParent(n2);
      if (n1.left != null)
          n1.left.setParent(n1);
      return n2;
  }
   private AVLNode<E> rotateWithRightChild(AVLNode<E> n1){
      AVLNode<E> n2 = n1.right;
      n1.right = n2.left;
      n2.left =  n1;
      n1.height =  Math.max(height(n1.left), height(n1.right)) + 1;
      n2.height = Math.max(height(n2.left), height(n2.right)) + 1;
      n2.setParent(n1.getParent());
      n1.setParent(n2);
      if (n1.right != null)
         n1.right.setParent(n1);
      return n2;
  }
   private AVLNode<E> doubleRotateLeftChild(AVLNode<E> n1){
      n1.left = rotateWithRightChild(n1.left);
      return rotateWithLeftChild(n1);
   }
   private AVLNode<E> doubleRotateRightChild(AVLNode<E> n1){
      n1.right = rotateWithLeftChild(n1.right);
      return rotateWithRightChild(n1);
   }

   public void add(E e){
      root = add(e, root);
      root.setParent(null);
   }
   private AVLNode<E> add(E e, AVLNode<E> n){
      if (n == null){
         size++;
         return  new AVLNode <E>(e);
      }
      int compare = e.compareTo(n.getE());
      if (compare < 0){
	 	n.left = add(e, n.left);
	 	n.left.setParent(n);
      }
      else if (compare > 0){
	 	n.right = add(e, n.right);
	 	n.right.setParent(n);
      }
      n = balance(n); 
      return n;
   }
   public AVLNode<E> getRoot(){
      return root;
   }
   public AVLNode<E> findMin(AVLNode<E> n){
      if (n == null) return n;
      while (n.left != null) n = n.left;
      return n;
   }
   public int size(){ return size;}
   public void print(){
         System.out.println("Total: " + size);
	 	 inOrder(root);
         System.out.println();
   }
   public void print2(){
         System.out.println("Total: " + size);
	 	 preOrder(root);
         System.out.println();
   }
   public void inOrder(AVLNode<E> n){
         if (n == null) return;
         inOrder(n.left);
         System.out.print(n.getE() + ", ");
         inOrder(n.right);
   }
   public void preOrder(AVLNode<E> n){
         if (n == null) return;
  		 System.out.print(n.getE() + ", " );
 		 preOrder(n.left);
 		 preOrder(n.right);
 }
}
class Btree<E>{
   private static class BNode<E>{
      private E element;
      private BNode<E> parent;
      private BNode<E> left;
      private BNode<E> right;
      public BNode(E e){
	  this(e, null, null);
     }
      public BNode(E e, BNode<E> l, BNode<E> r){
	 	element = e;
	 	left = l;
	 	right = r;
        }
      public E getE(){
	 return element;
      }
      public BNode<E> getParent(){
	 return parent;
      }
      public BNode<E> getLeft(){
	 return left;
      }
      public BNode<E> getRight(){
	 return right;
      }
      public void setE(E e){
	 element = e;
      }
      public void setParent(BNode<E> p){
	 parent = p;
      }
      public void setLeft(BNode<E> l){
	 left = l;
      }
      public void setRight(BNode<E> r){
	 right = r;
      }
   }
   private BNode<E> root;
   private int size;
   public Btree(){
      root = null;
      setSize(-1);
   }
   public Btree(E[] v){
   		root = new BNode<E>(v[0]);
   		int l = v.length, i = 0;
   		ArrayDeque<BNode<E>> queue = new ArrayDeque<BNode<E>>();
   		queue.add(root);
   		while (++i < l){
   			BNode<E> n = queue.remove();
   			if (v[i] != null) {
   			   n.setLeft(new BNode<E>(v[i]));
   			   n.getLeft().setParent(n);
   			   size++;
   			   queue.add(n.getLeft());
   			}
   			if (++i < l){
   				if (v[i] != null) {
   			   		n.setRight(new BNode<E>(v[i]));
   			   		n.getRight().setParent(n);
   			   		size++;
   			   		queue.add(n.getRight());
   				}
   			}
   		}
   
   }
	public boolean isAVLtree(){
		//your code starts here...
		BNode r = this.root;
		if(compareHeight(r)) {
			return true;
		} else {
			return false;
		}
	}
	public boolean compareHeight(BNode h) {
		int lHeight = 0;
		int rHeight = 0;
		if(h == null) {
			return true;
		}
		lHeight = height(h.left);
		rHeight = height(h.right);
		if(Math.abs(lHeight - rHeight) < 2 && compareHeight(h.left) && compareHeight(h.right)) {
			return true;
		}
		return false;
	}
	int height(BNode h) 
    { 
        if (h == null) 
            return 0;
        return 1 + Math.max(height(h.left), height(h.right)); 
    } 
	
    public int longestZigZag(){
    	BNode r = this.root;
    	if(r == null) {
    		return 0;
    	}
    	return 1;
    }
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}


public class LinkedListProject {

	public static void main(String[] args) {
		LinkedList<Integer> sl = new LinkedList<Integer>();
		for (int i = 1000; i > 0; i-=3) sl.add(i);
		
		System.out.println("Mid Element is " + sl.midElement());
		
		try {
			sl.insert(111, sl.getNode(50), sl.getNode(51));
			if (sl.detectLoop()) System.out.println("Loop!");
			else System.out.println("No loop.");
		
		
			sl.insert(123, sl.getNode(51), sl.getNode(50));
			if (sl.detectLoop()) System.out.println("Loop!");
			else System.out.println("No loop.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		PolynomialLinkedList p1, p2, p3, p4;
		p1 = new PolynomialLinkedList(21,3);
		p2 = new PolynomialLinkedList(32,2);
		p3 = p1.add(p2);
		p1 = new PolynomialLinkedList(33,2);
		p2 = new PolynomialLinkedList(14,0);
		p4 = p1.add(p2);
		PolynomialLinkedList sum = p3.add(p4);
		PolynomialLinkedList prod = p3.multiply(p4);
		System.out.println("This is P3");
		p3.print();
		System.out.println("This is P4");
		p4.print();
		System.out.println("This is Sum");
		sum.print();
		System.out.println("This is Prod");
		prod.print();
		

	}

}

class LinkedList<E>{
	private static class Node<E>{
		private E element;
		private Node<E> next;
		public Node(E e, Node<E> n){
			element = e;
			next = n;
		}
		public E getElement(){
			return element;
		}
		public Node<E> getNext(){
			return next;
		}
		public void setElement(E e){
			element = e;
		}
		public void setNext(Node<E> n){
			next = n;
		}
	}
	private Node<E> head;
	public LinkedList(){
		head = null;
	}
	public void add(E e){
		Node<E> temp = new Node<>(e, head);			
		head = temp;
	}
	public void insert(E e, Node<E> p, Node<E> n){
		p.setNext(new Node<>(e, n));      
	}
	public Node<E> getNode(int i) throws Exception{
		Node<E> temp = head;
		while (i > 0){
			if (temp == null) throw new Exception("Out of bound");
			temp = temp.getNext();
			i--;
		}
		return temp;
	}
	public E midElement(){
		//implement this method
		Node<E> ts = head;
		Node<E> os = head;
		while(ts != null && ts.next != null ){
			os = os.next;
			ts = ts.next.next;
		}
		return (E)os; 
	}
	public boolean detectLoop(){
		//implement this method
		Node<E> ts = head;
		Node<E> os = head;
		while(ts != null && ts.next != null){
			ts = ts.next.next;
			os = os.next;
			if(os == ts){
				return true;
			}
		}
		return false; 
	}
	
}

class PolynomialLinkedList{
	private static class PNode{
		private int coe;
		private int exp;
		private PNode next;
		public PNode(int c, int e){
			this(c, e, null);
		}
		public PNode(int c, int e, PNode n){
			coe = c;
			exp = e;
			next = n;
		}
		public void setCoe(int c){ coe = c;}
		public void setExp(int e){ exp = e;}
		public void setNext(PNode n){ next = n;}
		public int getCoe(){ return coe;}
		public int getExp(){ return exp;}
		public PNode getNext(){ return next;}
	}
	private PNode first;
	private PNode last;
	    public PolynomialLinkedList(){
			first = last = null;
		}
		public PolynomialLinkedList(int c, int e){
			PNode tempn = new PNode(c, e);
			first = last = tempn;
		}
		public void print(){
			if (first == null){
				System.out.println();
				return;
			}
			PNode temp = first;
			String ans = "";
			while (temp != null){
				if (temp.getCoe() > 0) {
					if (temp != first) ans = ans + " + ";
					ans = ans + temp.getCoe();
				}
				else if (temp.getCoe() < 0) ans = ans + " - " + temp.getCoe() * -1;
				if (temp.getExp() != 0){
					ans = ans + "X^" + temp.getExp();
				}
				temp = temp.getNext();
			}
			System.out.println(ans);
		}
		
		public PolynomialLinkedList add(PolynomialLinkedList s){
			PolynomialLinkedList sum = new PolynomialLinkedList(0,0);
			//implement this method
			PNode head = sum.first = this.first;
			PNode addend1 = this.first.next;
			PNode addend2 = s.first;
			while(addend1 != null) {
				head.setNext(addend1);
				head = head.next;
				addend1 = addend1.next;
			}
			while(addend2 != null) {
				head.setNext(addend2);
				head = head.next;
				addend2 = addend2.next;
			}
			return sum;
		}
		
		public PolynomialLinkedList multiply(PolynomialLinkedList s){
			PolynomialLinkedList product = new PolynomialLinkedList(1,0);
			//implement this method
			PNode head = product.first = new PNode(this.first.getCoe() * s.first.getCoe(), this.first.getExp() + s.first.getExp());
			PNode multi1 = this.first.next;
			PNode multi2 = s.first.next;
			while(multi1 != null && multi2 != null) {
				PNode combine = new PNode(multi1.getCoe() * multi2.getCoe(), multi1.getExp() + multi2.getExp());
				head.setNext(combine);
				head= head.next;
				multi1 = multi1.next;
				multi2 = multi2.next;
				
			}
			return product;
		}
		
}


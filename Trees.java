import java.util.*;
public class Trees{
   public static void main(String argc[]){
      ExpressionTree et;

      System.out.println("Enter an algebraic expression: ");
      Scanner s = new Scanner(System.in);
      String alg =  s.nextLine();
      s.close();
      
      et = new ExpressionTree(alg);
      et.print();
      System.out.println();
      et = new ExpressionTree("3+4*5");
      et.print(); //print (3+(4*5))
      System.out.println();
      et = new ExpressionTree("((1-(2+3))+(4*5))");
      et.print(); //print above
      System.out.println();
      et = new ExpressionTree("(3(4+5))");
      et.print(); //no output - missing operator
      System.out.println();
      et = new ExpressionTree("(4+5)*3");
      et.print(); //print ((4+5)*3)
      System.out.println();
      et = new ExpressionTree("1*((4+5)*2+1)/6");
      et.print(); //print "((1*(((4+5)*2)+1))/6)"
      System.out.println();
      et = new ExpressionTree("((4+5)*3))");
      et.print(); //no output - parenthesis not match
      System.out.println();
      et = new ExpressionTree("34+5");
      et.print(); //no output - single value only
      System.out.println();

      Tree t1 = new Tree();
      Tree t2 = new Tree();
      t1.makeTree();
      t1.levelOrder();	//0 1 2 3 4 5 6 13 14 15 7 8 9 10 11 12
      System.out.println();
      t2.makeTree2();
      t2.levelOrder();	//0 1 2 3
      System.out.println();
      System.out.println("sub tree t1 and t1 " + t1.isSubTree(t1));
      System.out.println("sub tree t1 and t2 " + t1.isSubTree(t2)); //t2 is not a subTree of t1
      Tree t3 = new Tree();
      t3.makeTree3();
      t3.levelOrder();
      System.out.println();
      System.out.println("sub tree t1 and t3 " + t1.isSubTree(t3)); //t3 is a subTree of t1
      Tree t4 = new Tree();
      t4.makeTree4();
      t4.levelOrder();
      System.out.println();
      System.out.println("sub tree t1 and t4 " + t1.isSubTree(t4)); //t4 is a subTree of t1

   }
}
      
class ExpressionTree{
   private static class BTNode{
      char value;
      BTNode parent, left, right;
      public BTNode(char e){
        this(e, null, null, null);
      }
      public BTNode(char e, BTNode p, BTNode l, BTNode r){
		value = e;
		parent = p;
		left = l;
		right = r;
      }
   }
   BTNode root;
   boolean validEq;
   public ExpressionTree(String eq){
      root = null;
      /*implement*/
      int pCount = 0;
      int oCount = 0;
      int lCount = 0;
      char[] eqArr = eq.toCharArray();
      Stack<BTNode> stack = new Stack<BTNode>();
      BTNode currentNode, temp1 = null, temp2 = null;
      for(int i = 0; i < eqArr.length; i++) {
    	  if(eqArr[i] == '(' || eqArr[i] == ')') {
    		  pCount++;
    	  } else if(!isOp(eqArr[i])) {
    		  lCount++;
    		  currentNode = new BTNode(eqArr[i]);
    		  if(i > 0 && i < eqArr.length - 1) {
    			  if(eqArr[i-1] == '(')
    				  currentNode.left = new BTNode('(');
    			      if(i >= 2 && eqArr[i-2] == '(') {
    			    	  currentNode.left.left = new BTNode('(');
    			      }
    			  else if (eqArr[i+1] == ')') {
    				  currentNode.right = new BTNode(')');
    				  if(i < eqArr.length - 2 && eqArr[i+2] == ')') {
    					  currentNode.right.right = new BTNode(')');
    				  }
    			  }
    		  }
    		  //System.out.println("Leaf Value: " + currentNode.value);
    		  if(stack.empty() || !isOp(stack.peek().value))
    			  stack.push(currentNode);
    		  else if (isOp(stack.peek().value)) {
    			  //System.out.println("Stack Operator Value: " + st.peek().value);
    			  if(stack.peek().left == null) {
    				  stack.peek().left = currentNode;
    				  //System.out.println("Stack Operator Left: " + st.peek().left.value);
    			  } else if (stack.peek().right == null) {
    				  stack.peek().right = currentNode;
    				  //System.out.println("Stack Operator Right: " + st.peek().right.value);
    			  }
    		  }
    	  } else if(isOp(eqArr[i])){
    		  oCount++;
    		  currentNode = new BTNode(eqArr[i]);
    		  //System.out.println("Operator Value: " + currentNode.value);
    		  if(!stack.empty())
    			  temp1 = stack.pop();
    		  if(!stack.empty())
    			  temp2 = stack.pop();
    		  currentNode.left = temp1;
    		  //if(currentNode.left != null)
    			  //System.out.println("Operator left " + currentNode.left.value);
    		  currentNode.right = temp2;
    		  //if(currentNode.right != null)
    			  //System.out.println("Operator right " + currentNode.right.value);
    		  stack.push(currentNode);
    	  }
      }
      if(pCount % 2 != 0) {
    	  validEq = false;
      } else if (oCount != lCount - 1) {
    	  validEq = false;
      } else {
    	  validEq = true;
    	  root = stack.pop();
      }
   }
   public boolean isOp(char op) {
	   if(op == '+' || op == '-' || op == '*' || op == '/') {
		   return true;
	   }
	   return false;
   }
   public boolean isEmpty(){
      return root == null;
   }
   public void print(BTNode node){
      /*implement*/
	  if(!this.validEq) {
		  System.out.print("Invalid Expression");
	  }
	  if(node == null) {
		  return;
	  }
	  print(node.left);
	  System.out.print(node.value);
	  print(node.right);
   }
   void print() {print(root);} 

}
class Tree{
   private static class TNode{
      private int value;
      private TNode parent;
      private List<TNode> children;
      public TNode(){
         this(0, null);
      }
      public TNode(int e){
         this(e, null);
      }
      public TNode(int e, TNode p){
         value = e;
         parent = p;
         children = new ArrayList<TNode>();
      }
   }
   private TNode root;
   private int size;
   Tree(){
      root = null;
      size = 0;
   }
    public TNode createNode(int e, TNode p){
       return new TNode(e, p);
    }
    public TNode addChild(TNode n, int e){
       TNode temp = createNode(e, n);
       n.children.add(temp);
       size++;
       return temp;
    }
    public void makeTree(){
       root = createNode(0, null);
       size++;
       buildTree(root, 3);
    }
    public void makeTree2(){
       root = createNode(0, null); 
       size++; 
       buildTree(root, 1);
    }
    public void makeTree3(){
       root = createNode(3, null); 
       size++; 
    }
    public void makeTree4(){
       root = createNode(2, null); 
       size += 13; 
       buildTree(root, 1);
       size -= 12;
    }
    private void buildTree(TNode n, int i){
       if (i <= 0) return;
       TNode fc = addChild(n, size);
       TNode sc = addChild(n, size);
       TNode tc = addChild(n, size);
       buildTree(fc, i - 1);
       buildTree(sc, i - 2);
       if (i % 2 == 0)
          buildTree(tc, i - 1);
   }
   public void levelOrder(){
      /*implement*/
	  Queue<TNode> queue = new LinkedList<TNode>(); 
	  queue.add(root);
	  while(!queue.isEmpty()) {
		  TNode temp = queue.poll();
		  //if(temp.parent != null) {System.out.print("Parent: " + temp.parent.value + " ");}
		  System.out.print(temp.value + " ");
		  for(int i = 0; i < temp.children.size(); i++) {
			  queue.add(temp.children.get(i));
		  }
	  }
	  
   }
   public boolean isSubTree(Tree st){
      /*implement*/
	  TNode tree1 = this.root;
	  TNode tree2 = st.root;
	  if(tree1 == null || tree2 == null) {
		  return false;
	  }
	  if(this == st) {
		  return true;
	  }
	  if(tree1.value == tree2.value && tree1.children.size() == 0 && tree2.children.size() == 0) {
		  return true;
	  }
	  if(tree1.value == tree2.value && tree1.children.size() == tree2.children.size()) {
		  for(int i = 0; i < tree1.children.size(); i++) {
			  if(tree1.children.get(i).value != tree2.children.get(i).value) {
				  return false;
			  } else if (tree1.children.get(i).children.size() != tree2.children.get(i).children.size()) {
				  return false;
			  } else if (tree1.children.get(i).children.size() == tree2.children.get(i).children.size()) {
				  for(int k = 0; k < tree1.children.get(i).children.size(); k++) {
					  if(tree1.children.get(i).children.get(k).value != tree2.children.get(i).children.get(k).value) {
						  return false;
				      } 
				  }
					  
			 } 
		     }
	  } else {
		  return true;
	  }
	  
	  return false;
   }
   
}

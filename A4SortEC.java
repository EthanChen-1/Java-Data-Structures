public class A4SortEC{

   public static void quickSort(int[] a, int l, int h){
	  if (l >= h) return;
      if (l < h) {
         /*fill in */
    	 int pivot = partition(a, l, h);
         quickSort(a, l, pivot - 1); 
         quickSort(a, pivot + 1, h);
      }
   }
    
   public static int partition(int[] a, int l, int h){
      int pivot = a[h];
      //use last entry
      int last = h;
      h--;
      while (l < h){
         while(a[l] < pivot) {
	    	l++;
         }
         while(a[h] > pivot){
	    	h--;
         }
	 	 if (l < h) swap(a,l,h);
	 	 else swap(a,l,last);
      }
      return l;
   }
   
   public static void insertionSort(int[] a, int l, int h){
	      for (int i = l; i < h;  i++){
	         int j = i;
	         int v = a[i];
	         while (j > 0 && v < a[j - 1]){
	            a[j] = a[j - 1];
	            j--;
	         }
	         a[j] = v;
	      }
   }
   
   public static void swap(int[] a, int i, int j){
      int temp = a[i];
      a[i] = a[j];
      a[j] = temp;
   }
   public static void heapSort(int[] a, int l, int h){
      heapify(a); //maxheap
   }
   public static void heapify(int[] a){
	   int end = a.length -1;
	   int start = (end - 1) / 2; 
	   while (start >= 0) {
	     bubbleDown(a, start, end - 1);
	     start--;
	   }
   }
   public static void bubbleDown(int[] a, int i, int size){
	   int root = i;
	   while ((2 * root) <= size) {
		   int child = 2 * root + 1;
	       if (child + 1 <= size && a[child] < a[child + 1]) {
	       child = child + 1;
	       }
	       if (a[root] < a[child]) {
	    	   int temp = a[root];
	    	   a[root] = a[child];
	    	   a[child] = temp;
	    	   root = child;
	     } else {
	    	 return;
	     }
	   }
   }

   
   public static void main(String[] args){
      int[] a = new int[]{3,19,12,7,15,1,16,4,18,9,13,2,17,5,10,11,14,6,8,20};
      int[] b = new int[]{3,19,12,7,15,1,16,4,18,9,13,2,17,5,10,11,14,6,8,20};
      long time, nextTime;
      System.out.println("quickSort: ");
      time = System.nanoTime();
      quickSort(a, 0, a.length - 1);
      nextTime = System.nanoTime();
      System.out.println("\tTime used: " + (nextTime - time) + " nseconds");
      
      for (int i = 0; i < a.length; i++) System.out.print(a[i] + ",");
      System.out.println();

      System.out.println("heapSort: ");
      heapSort(b, 0, b.length - 1);
      for (int i = 0; i < b.length; i++) System.out.print(b[i] + ",");
      System.out.println();
   }
}

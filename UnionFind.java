package HW8;
/*
Assignment number: 8
File name: UnionFind.java
Name: Yoad Ashuri
Student ID: 311162606
Email: Yoad.Ashuri@post.idc.ac.il
 */
/** 
 * Implementation of the Union-Find ADT. 
 */ 
public class UnionFind { 
 
   int up[]; 
   int weight[];
   int height[];   //new field for roots heights.
   int numSets;


   /** 
    * Constructor - initializes up and weight arrays. 
    * 
    * @param numElements initial number of singleton sets. 
    */
   public UnionFind (int numElements) { 
		up = new int [numElements + 1];
		weight = new int[numElements + 1];
		height = new int[numElements +1];
        numSets = numElements;
        for (int i = 1; i<up.length; i++){               // the first cell in the arrays is dummy.
		   up[i] = -1;
		   weight[i] = 1;
        }
   } 
 
   /** 
    * Returns the size of the set which contains i.
    * 
    * @param i
    */ 
   public int getSize(int i){
      if ((i < 1) || (i > weight.length)) {
         throw new IndexOutOfBoundsException("Not valid index");
      }
      return weight[find(i)];
   }
   
   /** 
    * Returns the height of the tree containing i. 
	* Disregards any changes which might have been caused by path compression.
    * 
    * @param i
    */ 
   public int getHeight(int i) {
      if ((i < 1) || (i > weight.length)) {
         throw new IndexOutOfBoundsException("Not valid index");
      }
      return height[find(i)];
   }
		
   /** 
    * Unites two sets using weigthed union. 
    * 
    * @param i is an element of the first set.
    * @param j is an element of the second set. 
    */ 
   public void union (int i, int j) {
      if (((i < 1) || (i > weight.length) || ((j < 1)) || (j > weight.length))) {
         throw new IndexOutOfBoundsException("Not valid index");
      }

      if (find(i) == find(j)){                                        // if they already connected do noting.
		   return;
        }
		int wij = getSize(i) + getSize(j);
		if (getSize(i) < getSize(j)) {                                // check witch root have more wight and connect
           height[find(j)] = Math.max(getHeight(j),getHeight(i) + 1); // according to weigthed union.
           up[find(i)] = find(j);
		   weight[j] = wij;
        }
		else{
           height[find(i)] = Math.max(getHeight(i),getHeight(j) + 1);
		   up[find(j)] = find(i);
		   weight[i] = wij;
        }
		numSets-- ;
   } 
 
   /** 
    * Finds the set representative, and applies path compression. 
    * 
    * @param i the element to search. 
    * @return the representative of the set which contains i. 
    */ 
   public int find (int i) {
      if ((i < 1) || (i > weight.length)) {
         throw new IndexOutOfBoundsException("Not valid index");
      }
      int root = i;
      int pointer;

      while (up[root] != -1){          // path compression.
         root = up[root];
      }
      if (root != i) {
         pointer = up[i];
         while (pointer != root) {
            up[i] = root;
            i = pointer;
            pointer = up[pointer];
         }
      }
      return root;
   } 
 
   /** 
    * Find the current number of sets. 
    * 
    * @return the number of set. 
    */ 
   public int getNumSets() { 
		return numSets;

   } 
 
   /** 
    * Prints the contents of the up array. 
    */ 
   private void debugPrintUp() { 
 
      System.out.print ("up:     "); 
      for (int i = 1; i < up.length; i++) 
         System.out.print (up[i] + " "); 
      System.out.println (""); 
   } 
 
   /** 
    * Prints the results of running find on all elements. 
    */ 
   private void debugPrintFind() { 
 
      System.out.print ("find:   "); 
      for (int i = 1; i < up.length; i++) 
         System.out.print (find (i) + " "); 
      System.out.println (""); 
   } 
 
   /** 
    * Prints the contents of the weight array. 
    */ 
   private void debugPrintWeight() { 
 
      System.out.print ("weight: "); 
      for (int i = 1; i < weight.length; i++) 
         System.out.print (weight[i] + " "); 
      System.out.println (""); 
   } 
 
   /** 
    * Various tests for the Union-Find functionality. 
    * 
    * @param args command line arguments - not used. 
    */ 
   public static void main (String[] args) { 
 
      UnionFind uf = new UnionFind (10); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println (""); 
 
      uf.union (2, 1); 
      uf.union (3, 2); 
      uf.union (4, 2); 
      uf.union (5, 2); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println(); 
 
      uf.union (6, 7); 
      uf.union (8, 9); 
      uf.union (6, 8);

      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println(); 

      uf.find (8); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println(); 
   } 
} 

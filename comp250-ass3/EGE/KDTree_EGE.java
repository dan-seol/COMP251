package assignment3;

import java.util.ArrayList;
import java.util.Iterator;
public class KDTree implements Iterable<Datum>{ 

	KDNode 		rootNode;
	int    		k; 
	int			numLeaves;
	
	// constructor

	public KDTree(ArrayList<Datum> datalist) throws Exception {

		Datum[]  dataListArray  = new Datum[ datalist.size() ]; 

		if (datalist.size() == 0) {
			throw new Exception("Trying to create a KD tree with no data");
		}
		else
			this.k = datalist.get(0).x.length;

		int ct=0;
		for (Datum d :  datalist) {
			dataListArray[ct] = datalist.get(ct);
			ct++;
		}
		
	//   Construct a KDNode that is the root node of the KDTree.
		
		rootNode = new KDNode(dataListArray);
		this.numLeaves = rootNode.sumDepths_numLeaves()[1];
	}
	
	//   KDTree methods
	
	public Datum nearestPoint(Datum queryPoint) {
		return rootNode.nearestPointInNode(queryPoint);
	}	

	public int height() {
		return this.rootNode.height();	
	}

	public int countNodes() {
		return this.rootNode.countNodes();	
	}
	
	public int size() {
		return this.numLeaves;	
	}

	//-------------------  helper methods for KDTree   ------------------------------

	public static long distSquared(Datum d1, Datum d2) {

		long result = 0;
		for (int dim = 0; dim < d1.x.length; dim++) {
			result +=  (d1.x[dim] - d2.x[dim])*((long) (d1.x[dim] - d2.x[dim]));
		}
		// if the Datum coordinate values are large then we can easily exceed the limit of 'int'.
		return result;
	}

	public double meanDepth(){
		int[] sumdepths_numLeaves =  this.rootNode.sumDepths_numLeaves();
		return 1.0 * sumdepths_numLeaves[0] / sumdepths_numLeaves[1];
	}
	
	
	class KDNode { 

		boolean leaf;
		Datum leafDatum;           //  only stores Datum if this is a leaf
		
		//  the next two variables are only defined if node is not a leaf

		int splitDim;      // the dimension we will split on
		int splitValue;    // datum is in low if value in splitDim <= splitValue, and high if value in splitDim > splitValue  

		KDNode lowChild, highChild;   //  the low and high child of a particular node (null if leaf)
		  //  You may think of them as "left" and "right" instead of "low" and "high", respectively

		KDNode(Datum[] datalist) throws Exception{

			/*
			 *  This method takes in an array of Datum and returns
			 *  the calling KDNode object as the root of a sub-tree containing
			 *  the above fields.
			 */			
			this.leaf = false;
			if(datalist.length == 1) {
				this.leaf= true;
				this.leafDatum = datalist[0];
				this.highChild= null;
				this.lowChild=null;

			} else {

				boolean isThereDuplicate = datalist[0].equals(datalist[1]);
				for(int i = 1; i<datalist.length-1;i++) {
					isThereDuplicate &= datalist[i].equals(datalist[i+1]);
				}
				if(isThereDuplicate) {
					this.leaf= true;
					this.leafDatum = datalist[0];
					this.highChild= null;
					this.lowChild=null;
				} else {double maxRangeValue = 0;
				splitDim = 0;
				for(int i =0; i< datalist[0].x.length;++i){
					int largestNum = Integer.MIN_VALUE;
					int smallestNum = Integer.MAX_VALUE; 					for(int j=0;j<datalist.length;++j){
						if(datalist[j].x[i] > largestNum)
						{largestNum=datalist[j].x[i];}
						if(datalist[j].x[i] < smallestNum)
						{smallestNum=datalist[j].x[i];}
					}

					if(largestNum - smallestNum >maxRangeValue ){
						maxRangeValue =largestNum - smallestNum;

						splitDim = i;
					}
				}

				int maxValue = Integer.MIN_VALUE;
				double minValue = Double.MAX_VALUE;

				for(int i =0;i<datalist.length;i++) {
					if (datalist[i].x[splitDim] > maxValue) {
						maxValue = datalist[i].x[splitDim];
					}
					if (datalist[i].x[splitDim] < minValue) {
						minValue = datalist[i].x[splitDim];
					}
				}
				double rawSplitValue = (maxValue+minValue)/2;

				int lowChildren =0;
				int highChildren =0;

				for(int i = 0; i<datalist.length ; i++ ){
					if(datalist[i].x[splitDim] > rawSplitValue){
						highChildren++;
					}else {
						lowChildren++;
					}
				}
				Datum[] rawLowChild  = new Datum[lowChildren];
				Datum[] rawHighChild = new Datum[highChildren];

				for(int i = 0,j = 0,k = 0 ; i<datalist.length; i++){
					if(datalist[i].x[splitDim] <= rawSplitValue){
						rawLowChild[j]=datalist[i];
						j++;
					}else {
						rawHighChild[k]=datalist[i];
						k++;
					}
				}

				this.splitValue = (int) rawSplitValue;
				this.lowChild=new KDNode(rawLowChild);
				this.highChild=new KDNode(rawHighChild);
			}}
				
		}

		public Datum nearestPointInNode(Datum queryPoint) {
			Datum nearestPoint, nearestPoint_otherSide;
		
			//   ADD YOUR CODE BELOW HERE
			boolean isLow;
			if(this.leaf) {
				nearestPoint = this.leafDatum;
				return nearestPoint;
			} else {
				int distanceFromSplit = queryPoint.x[splitDim] - this.splitValue;
				if(distanceFromSplit <=0) {
					nearestPoint = this.lowChild.nearestPointInNode(queryPoint);
					isLow = true;
				} else {
					nearestPoint = this.highChild.nearestPointInNode(queryPoint);
					isLow = false;
				}
				long minDistCandidate =  distSquared(nearestPoint, queryPoint);
				if(minDistCandidate <= distanceFromSplit * distanceFromSplit) {
					return nearestPoint;
				} else {
					if(isLow) {
						nearestPoint_otherSide = this.highChild.nearestPointInNode(queryPoint);
					} else {
						nearestPoint_otherSide = this.lowChild.nearestPointInNode(queryPoint);

					}
					long other_minDistCandidate = distSquared(nearestPoint_otherSide, queryPoint);
					if(minDistCandidate <= other_minDistCandidate) {
						return nearestPoint;
					} else {
						return nearestPoint_otherSide;
					}
				}
			}
			//   ADD YOUR CODE ABOVE HERE
		}
		
		// -----------------  KDNode helper methods (might be useful for debugging) -------------------

		public int height() {
			if (this.leaf) 	
				return 0;
			else {
				return 1 + Math.max( this.lowChild.height(), this.highChild.height());
			}
		}

		public int countNodes() {
			if (this.leaf)
				return 1;
			else
				return 1 + this.lowChild.countNodes() + this.highChild.countNodes();
		}
		
		/*  
		 * Returns a 2D array of ints.  The first element is the sum of the depths of leaves
		 * of the subtree rooted at this KDNode.   The second element is the number of leaves
		 * this subtree.    Hence,  I call the variables  sumDepth_size_*  where sumDepth refers
		 * to element 0 and size refers to element 1.
		 */
				
		public int[] sumDepths_numLeaves(){
			int[] sumDepths_numLeaves_low, sumDepths_numLeaves_high;
			int[] return_sumDepths_numLeaves = new int[2];
			
			/*     
			 *  The sum of the depths of the leaves is the sum of the depth of the leaves of the subtrees, 
			 *  plus the number of leaves (size) since each leaf defines a path and the depth of each leaf 
			 *  is one greater than the depth of each leaf in the subtree.
			 */
			
			if (this.leaf) {  // base case
				return_sumDepths_numLeaves[0] = 0;
				return_sumDepths_numLeaves[1] = 1;
			}
			else {
				sumDepths_numLeaves_low  = this.lowChild.sumDepths_numLeaves();
				sumDepths_numLeaves_high = this.highChild.sumDepths_numLeaves();
				return_sumDepths_numLeaves[0] = sumDepths_numLeaves_low[0] + sumDepths_numLeaves_high[0] + sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
				return_sumDepths_numLeaves[1] = sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
			}	
			return return_sumDepths_numLeaves;
		}
		
	}

	public Iterator<Datum> iterator() {
		return new KDTreeIterator();
	}
	
	public static void inOrder(KDNode kdNode, ArrayList<Datum> dataArr) {
		if(kdNode.leaf) {
			dataArr.add(kdNode.leafDatum);
		} else {
			inOrder(kdNode.lowChild, dataArr);
			inOrder(kdNode.highChild, dataArr);
		}
	}
	
	private class KDTreeIterator implements Iterator<Datum> {
		
		//   ADD YOUR CODE BELOW HERE
		KDTree kdTree;
		KDNode root;
		ArrayList<Datum> data = new ArrayList<Datum>();
		Iterator<Datum> iterator;
		KDTreeIterator() {
			this.kdTree = KDTree.this;
			this.root = kdTree.rootNode;
			inOrder(root, this.data);
			this.iterator = this.data.iterator();

		}
		public Datum next() {
			return this.iterator.next();
		}
		public boolean hasNext() {
			return this.iterator.hasNext();
		}
		//   ADD YOUR CODE ABOVE HERE
		
		
	}

}
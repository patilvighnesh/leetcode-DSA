class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
       	int n=buildings.length;
	
	List<List<Integer>>res=new ArrayList<>();
	Pair[]arr=new Pair[2*n];
	int i=0;
      for(int[]a:buildings) {
    	  int x1=a[0];
    	  int x2=a[1];
    	  int h=a[2];
    	  arr[i++]=new Pair(x1,h,0);
    	  arr[i++]=new Pair(x2,h,1);
      }
      Arrays.sort(arr,(a,b)->{
    	  
    	  if(a.start!=b.start)
     return Integer.compare(a.start, b.start);
    	  if(a.end==0&&b.end==0)
    		  return Integer.compare(b.height,a.height);
    	  if(a.end==1&&b.end==1)
    		  return Integer.compare(a.height, b.height);
    	  return Integer.compare(a.end, b.end);
      });
      PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->b-a);
      pq.add(0);
      int max=0;
      for(Pair rv:arr) {
    	  
    	  List<Integer>ll=new ArrayList<>();
    	
    	  int a=rv.start;
    	  int h=rv.height;
    	  int p=rv.end;
    	 
    	  if(p==0) {
    		  
    	    
    	  pq.add(h);
    	  if(max!=pq.peek()) {
    		  ll.add(a);
    		  ll.add(pq.peek());
    		  max=pq.peek();
    	  }
    	  
      }
    	  else {
    		  pq.remove(h);
    		  if(max!=pq.peek()) {
    			  ll.add(a);
    			  ll.add(pq.peek());
    			  max=pq.peek();
    		  }
    	  }
    	  
    	  if(!ll.isEmpty()) {
    		    res.add(ll);
    		}
    	
    	
    	  
	
}
      return res;
}
public static class Pair{
	int start;
	
	int height;
	int end;
	Pair(){
		
	}

	Pair(int start,int height,int end){
		this.start=start;
		
		this.height=height;
		this.end=end;
	}
}

}
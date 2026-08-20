class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        int[] nums={1,2,3,4,5,6,7,8,9};
        List<List<Integer>> list=new ArrayList<>();
        rec(0,nums,list,n,new ArrayList<>(),k);

        return list;
    }
    static void rec(int ind,int[] nums,List<List<Integer>> list,int tar,ArrayList<Integer> arr,int k){
        if(tar==0){
            if(arr.size()==k){
                list.add(new ArrayList<>(arr));
            }
            return;
        }
        if(ind==nums.length){
            return;
        }
        if(tar<0){
            return;
        }
        arr.add(nums[ind]);
        rec(ind+1,nums,list,tar-nums[ind],arr,k);
        arr.remove(arr.size()-1);
        rec(ind+1,nums,list,tar,arr,k);
    }
}
class Solution {
    public int searchInsert(int[] nums, int target) {
        int found = Arrays.binarySearch(nums, target);
        if(found >= 0) {
            return found;
        }
        return (found + 1) * -1;
    }
}
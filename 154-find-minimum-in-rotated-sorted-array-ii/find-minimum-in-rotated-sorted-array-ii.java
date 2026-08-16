class Solution {
    public int findMin(int[] nums) {

        int s = 0;
        int e = nums.length - 1;

        while (s < e) {

            // Find middle index
            int mid = s + (e - s) / 2;

            // Minimum lies in right half
            if (nums[mid] > nums[e]) {
                s = mid + 1;
            }

            // Minimum lies in left half including mid
            else if (nums[mid] < nums[e]) {
                e = mid;
            }

            // Duplicate case
            // Cannot determine correct side
            else {
                e--;
            }
        }

        // s points to minimum element
        return nums[s];
    }
}
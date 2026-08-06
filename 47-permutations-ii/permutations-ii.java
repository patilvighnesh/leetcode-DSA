class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        backtracking(nums, new ArrayList<>(), list, visited);

        return list;
    }

    public static void backtracking(int[] nums, List<Integer> currList,
                                    List<List<Integer>> list, boolean[] visited) {

        if (currList.size() == nums.length) {
            list.add(new ArrayList<>(currList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            visited[i] = true;
            currList.add(nums[i]);

            backtracking(nums, currList, list, visited);

            currList.remove(currList.size() - 1);
            visited[i] = false;
        }
    }
}
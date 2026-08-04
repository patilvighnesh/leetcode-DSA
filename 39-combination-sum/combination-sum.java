class Solution {

    private void findCombinations(int i, int target, List<List<Integer>> ans,
                                  List<Integer> li, int[] candidates) {

        if (target == 0) {
            ans.add(new ArrayList<>(li));
            return;
        }

        if (i == candidates.length) {
            return;
        }

        if (candidates[i] <= target) {
            li.add(candidates[i]);
            findCombinations(i, target - candidates[i], ans, li, candidates);
            li.remove(li.size() - 1);
        }

        findCombinations(i + 1, target, ans, li, candidates);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> ans = new ArrayList<>();

        findCombinations(0, target, ans, new ArrayList<>(), candidates);

        return ans;
    }
}
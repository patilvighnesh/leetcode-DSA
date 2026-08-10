class Solution {
    public int largestRectangleArea(int[] arr) {
        int n = arr.length;
        int[] leftMin = new int[n], rightMin = new int[n];

        Fn(arr, leftMin, 1, -1, 0);
        Fn(arr, rightMin, -1, n, n - 1);

        int max = 0;

        for (int i = 0; i < n; i++) {
            int len = rightMin[i] - leftMin[i] - 1;
            max = Math.max(max, len * arr[i]);
        }

        return max;
    }

    private static void Fn(int[] arr, int[] res, int incre, int inValid, int st) {
        int n = arr.length;
        Stack<Integer> stk = new Stack<>();

        for (int i = st; i < n && i >= 0; i += incre) {
            while (!stk.isEmpty() && arr[i] <= arr[stk.peek()]) {
                stk.pop();
            }

            if (stk.isEmpty()) {
                res[i] = inValid;
            } else {
                res[i] = stk.peek();
            }

            stk.push(i);
        }
    }
}
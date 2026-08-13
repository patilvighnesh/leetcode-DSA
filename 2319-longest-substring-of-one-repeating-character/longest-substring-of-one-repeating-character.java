class Solution {

    class Node {
        int len;
        int prefix;
        int suffix;
        int best;
        char leftChar;
        char rightChar;

        Node(int len, int prefix, int suffix, int best,
             char leftChar, char rightChar) {
            this.len = len;
            this.prefix = prefix;
            this.suffix = suffix;
            this.best = best;
            this.leftChar = leftChar;
            this.rightChar = rightChar;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String str, String queryCharacters,
                                   int[] queryIndices) {

        s = str.toCharArray();
        int n = s.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            // Update the character
            s[index] = ch;

            // Update segment tree
            update(1, 0, n - 1, index);

            // Root contains answer for complete string
            ans[i] = tree[1].best;
        }

        return ans;
    }

    // Build segment tree
    private void build(int node, int l, int r) {

        if (l == r) {
            tree[node] = new Node(
                1,       // len
                1,       // prefix
                1,       // suffix
                1,       // best
                s[l],    // leftChar
                s[l]     // rightChar
            );
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one index
    private void update(int node, int l, int r, int index) {

        if (l == r) {
            tree[node] = new Node(
                1, 1, 1, 1,
                s[l], s[l]
            );
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two segments
    private Node merge(Node a, Node b) {

        int len = a.len + b.len;

        int prefix = a.prefix;
        int suffix = b.suffix;

        int best = Math.max(a.best, b.best);

        // If boundary characters are same,
        // suffix of left + prefix of right can combine
        if (a.rightChar == b.leftChar) {

            best = Math.max(
                best,
                a.suffix + b.prefix
            );

            // Entire left segment has same character
            if (a.prefix == a.len) {
                prefix = a.len + b.prefix;
            }

            // Entire right segment has same character
            if (b.suffix == b.len) {
                suffix = b.len + a.suffix;
            }
        }

        return new Node(
            len,
            prefix,
            suffix,
            best,
            a.leftChar,
            b.rightChar
        );
    }
}
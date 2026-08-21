import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {

        // Each event = {x, height}
        // Negative height -> building starts
        // Positive height -> building ends
        List<int[]> events = new ArrayList<>();

        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]}); // start
            events.add(new int[]{b[1], b[2]});  // end
        }

        // Sort by x first, then height.
        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Max heap containing heights of active buildings
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        // Initially, ground level is active
        maxHeap.offer(0);

        // Frequency of each height
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);

        List<List<Integer>> result = new ArrayList<>();

        int i = 0;

        while (i < events.size()) {

            int x = events.get(i)[0];

            // Process ALL events at the same x
            while (i < events.size() && events.get(i)[0] == x) {

                int height = events.get(i)[1];

                if (height < 0) {
                    // Building starts
                    int h = -height;

                    maxHeap.offer(h);
                    count.put(h, count.getOrDefault(h, 0) + 1);

                } else {
                    // Building ends
                    count.put(height, count.get(height) - 1);
                }

                i++;
            }

            // Lazy removal of heights that are no longer active
            while (!maxHeap.isEmpty()
                    && count.getOrDefault(maxHeap.peek(), 0) == 0) {
                maxHeap.poll();
            }

            int currentHeight = maxHeap.peek();

            // Add a key point only when height changes
            if (result.isEmpty()
                    || result.get(result.size() - 1).get(1) != currentHeight) {

                result.add(Arrays.asList(x, currentHeight));
            }
        }

        return result;
    }
}
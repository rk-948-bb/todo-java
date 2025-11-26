import java.util.ArrayList;
import java.util.List;

public class AlgorithmSolution {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 3, 2, 1};
        List<List<Integer>> allSequences = getSubSequence(arr);
        for (List<Integer> list : allSequences) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> getSubSequence(int[] arr) {
        List<List<Integer>> allSequences = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (current.isEmpty() || arr[i] > current.get(current.size() - 1)) {
                current.add(arr[i]);
            } else {
                if (current.size() > 1) {
                    allSequences.add(new ArrayList<>(current));
                }
                current.clear();
                current.add(arr[i]);
            }
        }
        if (arr[arr.length - 1] > current.get(current.size() - 1)) {
            current.add(arr[arr.length - 1]);
            allSequences.add(new ArrayList<>(current));
        }
        return allSequences;
    }

}

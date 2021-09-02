package myapp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationsGenerator {

	
	public static List<int[]> generate(int n, int r) {
	    List<int[]> combinations = new ArrayList<int[]>();
	    helper(combinations, new int[r], 0, n-1, 0);
	    return combinations;
	}
	
	
	
	private static void helper(List<int[]> combinations, int data[], int start, int end, int index) {
	    if (index == data.length) {
	        int[] combination = data.clone();
	        combinations.add(combination);
	    } else if (start <= end) {
	        data[index] = start;
	        helper(combinations, data, start + 1, end, index + 1);
	        helper(combinations, data, start + 1, end, index);
	    }
	}
	
	
	public static void main(String[] args) {
		int N = 47;
		int R = 2;
		List<int[]> combinations = generate(N, R);
		for (int[] combination : combinations) {
		    System.out.println(Arrays.toString(combination));
		}
		System.out.printf("generated %d combinations of %d items from %d ", combinations.size(), R, N);



	}

}

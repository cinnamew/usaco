import java.util.Arrays;

public class SumOfTwoValues {

	public static void main(String[] skdfh) {
		System.out.println(Arrays.toString(findValues(new int[]{2,7,5,1}, 4, 8)));

	}
	
	public static int[] findValues(int[] arr, int n, int targetSum) {
		int[] ans = new int[2];
		Arrays.sort(arr);
		
		int leftPointer = 0;
		int rightPointer = n - 1;
		
		while(leftPointer < rightPointer) {
			int currSum = arr[leftPointer] + arr[rightPointer];
			if(currSum > targetSum) {
				rightPointer--;
			}else if(currSum < targetSum) {
				leftPointer++;
			}else break;
		}
		
		if(leftPointer == rightPointer) {
			ans[0] = -1;
			return ans;
		}
		
		ans[0] = leftPointer;
		ans[1] = rightPointer;
		
		
		return ans;
	}

}

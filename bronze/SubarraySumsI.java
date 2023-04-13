
public class SubarraySumsI {

	public static void main(String[] args) {
		System.out.println(calculateSubarrays(2, 3, new int[]{4,3}));
	}
	
	public static int calculateSubarrays(int n, int targetSum, int[] arr) {
		int ans = 0;
		
		int leftPointer = 0;
		int rightPointer = 0;
		int currSum = arr[0];
		
		while(leftPointer <= arr.length) {
			//System.out.println(leftPointer + ", " + rightPointer + "; " + currSum);
			if(currSum == targetSum) {
				//System.out.println(leftPointer + ", " + rightPointer);
				
				ans++;
				currSum -= arr[leftPointer];
				leftPointer++;
				rightPointer = Math.max(rightPointer, leftPointer);
				if(rightPointer == leftPointer && rightPointer != arr.length) currSum += arr[rightPointer];
				
			}else if(currSum < targetSum) {
				rightPointer++;
				if(rightPointer != n) currSum += arr[rightPointer];
			}else {
				currSum -= arr[leftPointer];
				leftPointer++;
				
			}
			
			
			if(rightPointer >= arr.length) {
				break;
			}
			
		}
		
		return ans;

	}

}

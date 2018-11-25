public class Sequential {
	public void sequentialsearch(int[] arr, int searchelement) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == searchelement) {
				System.out.println("Found at " + i);
				return;
			}
		}
		System.out.println("Not Found");
	}
}
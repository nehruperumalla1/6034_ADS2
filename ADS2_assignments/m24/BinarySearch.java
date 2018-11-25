public class BinarySearch {
	public int binarySearch(int lo, int hi, int search) {
		if (lo <= hi) {
			System.out.println("Out of ****");
			return -1;
		}
		int mid = (lo + hi) / 2;
		if (search < arr[mid]) {
			binarySearch(lo, mid - 1, search);
		}
		else if (search > arr[mid]) {
			binarySearch(mid + 1, hi, search);
		} else {
			System.out.println("Found");
			return mid;
		}
	}
}
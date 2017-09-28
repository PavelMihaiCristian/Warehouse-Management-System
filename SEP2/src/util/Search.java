package util;

public class Search {

	public static int linearSearch(int[] arr, int key, int left, int right) {
		if (arr.length > 0 && left >= 0 && right < arr.length && left <= right) {
			for (int i = left; i <= right; i++) {
				if (arr[i] == key) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int binSearchRec(int[] arr, int key, int left, int right) {
		// ToDo: recursive implementation
		int mid;
		if (arr.length > 0 && left >= 0 && right < arr.length && left <= right) {
			if (right < left) // no more elements
				return -1;
			else {
				mid = left + (right - left) / 2;
				if (arr[mid] == key)
					return mid;
				else if (key < arr[mid])
					return binSearchRec(arr, key, left, mid - 1);
				else
					return binSearchRec(arr, key, mid + 1, right);
			}
		}
		else return -1;
	}

	public static int binSearchRecWithIterative(int[] arr, int key, int left,
			int right) {
		// ToDo: recursive implementation
		int mid;
		if (arr.length > 0 && left >= 0 && right < arr.length && left <= right) {
			if (right - left < 10)
				return linearSearch(arr, key, left, right);
			else {
				if (right < left) // no more elements
					return -1;
				else {
					mid = left + (right - left) / 2;
					if (arr[mid] == key)
						return mid;
					else if (key < arr[mid])
						return binSearchRec(arr, key, left, mid - 1);
					else
						return binSearchRec(arr, key, mid + 1, right);
				}
			}
		}
		else return -1;
	}
}

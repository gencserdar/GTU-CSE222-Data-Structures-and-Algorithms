public class MergeSort extends SortAlgorithm {
	
	public MergeSort(int input_array[]) {
		super(input_array);
	}
	
    // l = left, r = right
	private void merge(int l, int mid, int r){
        // Find sizes of two subarrays to be merged
        int len1 = mid - l + 1;
        int len2 = r - mid;

        // Create temp arrays
        // L[] = left part of subarray
        int L[] = new int[len1];
        // R[] = right part of subarray
        int R[] = new int[len2];

        // Copy data to temp arrays
        for (int i = 0; i < len1; i++)
            L[i] = arr[l + i];
        for (int j = 0; j < len2; j++)
            R[j] = arr[mid + 1 + j];

        // Merge the temp arrays to create a sorted subarray of arr[]
        int i = 0, j = 0, k = l;

        while (i < len1 && j < len2) {
            comparison_counter++; // Increment comparison counter
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        // Copy remaining elements of L[]
        while (i < len1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        // Copy remaining elements of R[]
        while (j < len2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // l = left, r = right
    private void sort(int l, int r){
        if (l < r) {
            // Find the middle point
            int mid = (l + r) / 2;
            // Sort first half
            sort(l, mid);
            // Sort second half
            sort(mid + 1, r);
            // Merge the sorted halves
            merge(l, mid, r);
        }
    }
    
    @Override
    public void sort() {
        // Sends full array into recursive sort function
        sort(0, arr.length - 1);
    }
    
    @Override
    public void print() {
    	System.out.print("Merge Sort\t=>\t");
    	super.print();
    }
}

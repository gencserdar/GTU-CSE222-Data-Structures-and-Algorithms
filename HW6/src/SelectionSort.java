public class SelectionSort extends SortAlgorithm {

	public SelectionSort(int input_array[]) {
		super(input_array);
	}

    @Override
    public void sort() {
        int len = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < len-1; i++) {
            // Find the minimum element in unsorted array
            int min_index = i;
            for (int j = i+1; j < len; j++) {
                comparison_counter++; // Increment comparison counter
                if (arr[j] < arr[min_index]) min_index = j;
            }
            // Swap the found minimum element with the first element
            swap(min_index, i);
        }
    }

    @Override
    public void print() {
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}

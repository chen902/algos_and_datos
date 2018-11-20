package assignment3.exercise_3_4_sort;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 *
 * @author Chen
 */
public class Main {
    public static void main(String[] args){
        
        // calls to each of the sorting functions are stored in the map
        HashMap<String,Consumer<double[]>> sortingAlgos = new HashMap<>();
        sortingAlgos.put("Insertion Sort", Sort::insertionsort);
        sortingAlgos.put("Selection Sort", Sort::selectionsort);
        sortingAlgos.put("Bubble Sort", Sort::bubblesort);
        sortingAlgos.put("QuickSort", Sort::quicksort);
        sortingAlgos.put("Heap Sort", Sort::heapsort);
        sortingAlgos.put("Merge Sort", Sort::mergesort);
        
        for(int n=100; n<=100000; n*=10){
            double[] arr = Sort.generateTestData(n);
            System.out.println(n+ " elements:");
            
            for(String k: sortingAlgos.keySet()){
                // create a copy of the data since the sorting is in-place
                double[] arr_clone = arr.clone();
                
                // make sure array is not sorted
                if(isSorted(arr_clone))
                    throw new RuntimeException("Array shouldn't be sorted");
                
                // records start time
                double start = System.nanoTime();
                
                // calls the sorting function
                sortingAlgos.get(k).accept(arr_clone);
                
                // records end time 
                double finish = System.nanoTime();
                
                // array should be sorted
                if(!isSorted(arr_clone))
                    throw new RuntimeException("Array should be sorted");
                
                double elapsed = (finish-start)/1000000.0;
                System.out.println(k + " - " + String.valueOf(elapsed) + " ms.");
            }
            System.out.println("");
        }
    }
    
    // Checks if an array is sorted
    public static boolean isSorted(double[] arr){
        for(int i = 0; i< arr.length-1; i++){
            if(arr[i+1] - arr[i] < 0){
                return false;
            }
        }
        return true;
    }
}




public class Sort {

    public static void selectionSort(Comparable[] a) {
        int temp;
        for (int i = 0; i < a.length; i++) {
            temp = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[temp])) {
                    temp = j;
                }
            }
            swap(a, i, temp);
        }
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }


    public static void insertionSort(Comparable[] a) {
        for(int i = 1; i < a.length; i++) {
            for(int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                swap(a, j, j-1);
            }
        }
    }

    private static boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }


    public static void bubbleSort(Comparable[] a) {

        // my version
//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < a.length - i - 1; j++) {
//                if(less(a[j+1], a[j])) {
//                    swap(a, j, j+1);
//                }
//            }
//        }

        // fatma's version
        for (int i = 0; i < a.length; i++) {
            for (int j = a.length-1; j > i; j--) {
                if (less(a[j], a[j-1])) {
                    swap(a, j-1, j);
                }
            }
        }
    }

    public static void mergeSort(Comparable[] a, int first, int last) {
        if(first < last) {
            int mid = (first+last) / 2;
            mergeSort(a, first, mid);
            mergeSort(a, mid+1, last);
            merge(a, first, mid, last);
        }
    }

    public static void merge(Comparable[] a, int first, int mid, int last) {
        Comparable[] temp = new Comparable[a.length];
        int first1 = first;
        int last1 = mid;
        int first2 = mid+1;
        int last2 = last;
        int index = first; // index for next available position in temp array
        for (;(first1<=last1 && first2<=last2);index++) {
            if(less(a[first1],a[first2])) {
                temp[index] = a[first1];
                first1++;
            } else {
                temp[index] = a[first2];
                first2++;
            }
        }
        for(;first1<=last1;index++) {
            temp[index] = a[first1];
            first1++;
        }
        for(;first2<=last2;index++) {
            temp[index] = a[first2];
            first2++;
        }
        for(int i = first; i <= last; i++) {
            a[i] = temp[i];
        }
    }

    public static void quickSort(Comparable[] a, int first, int last) {
        if (first < last) {
            int pivotIndex = partition(a, first, last);
            quickSort(a, first, pivotIndex-1);
            quickSort(a, pivotIndex+1, last);
        }
    }

    private static int partition(Comparable[] a, int first, int last) {

        // choose a pivot and place it into the first element of the array
        choosePivot(a, first, last);
        int pivotIndex = first;
        Comparable pivot = a[first];
        int lastS1 = pivotIndex;
        int firstUnknown = pivotIndex + 1;
        for(;firstUnknown<=last;firstUnknown++){
            if(less(a[firstUnknown],pivot)) {
                lastS1++;
                swap(a, firstUnknown, lastS1);
            }
        }
        swap(a, pivotIndex, lastS1);
        pivotIndex = lastS1;
        return pivotIndex;
    }

    private static void choosePivot(Comparable[] a, int first, int last) {
        // choose pivot as the first element

        // ignore this
//        int pivotIndex = (first+last)/2;
//        swap(a, first, pivotIndex);
    }

    public static void heapSort(Comparable[] a) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--){
            heapify(a, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            Comparable temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            heapify(a, i, 0);
        }
    }

    private static void heapify(Comparable[] a, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && less(a[largest], a[l])) {
            largest = l;
        }

        if (r < n && less(a[largest], a[r])) {
            largest = r;
        }

        if (largest != i) {
            swap(a, i, largest);
//            Comparable swap = a[i];
//            a[i] = a[largest];
//            a[largest] = swap;

            heapify(a, n, largest);
        }
    }
    public static void shellSort(Comparable[] a) {
        int n = a.length;

        for(int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                Comparable temp = a[i];
                int j;
                for(j = i; j >= gap && less(temp, a[j - gap]); j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }
    }

    private static void countSort(Comparable[] a) {
        //
    }
}

public class Code07_HeapSort {

    public static void printArr(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int Parent(int i) {
        return (i - 1) / 2;
    }

    private static int Left(int i) {
        return 2 * i + 1;
    }

    private static int Right(int i) {
        return 2 * i + 2;
    }

    public static void Heapify(int[] arr, int i, int size) {
        int l = Left(i);
        int r = Right(i);
        int largest = i;

        if (l<size && arr[l]>arr[i]) {
            largest = l;
        }
        if (r<size && arr[r]>arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(arr, i, largest);
            Heapify(arr, largest, size);
        }
    }

    public static void BuildMaxHeap(int[] arr, int size) {
        for (int i=(size-1)/2; i>=0; i--) {
            Heapify(arr, i, size);
        }
    }

    public static void HeapSort(int[] arr) {
        int size = arr.length;
        BuildMaxHeap(arr, size);
        for (int i=arr.length-1; i>=1; i--) {
            swap(arr, 0, i);
            Heapify(arr, 0, --size);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4,1,3,2,16,9,10,14,8,7};
        printArr(arr);
//        BuildMaxHeap(arr, arr.length);
        HeapSort(arr);
        printArr(arr);
    }
}

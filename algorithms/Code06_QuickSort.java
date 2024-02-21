import java.util.Arrays;

public class Code06_QuickSort {

    public static int Partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p - 1;

        for (int j=p; j<r; j++) {
            if (A[j] <= x) {
                Code_Utils.swap(A, ++i, j);
            }
        }
        Code_Utils.swap(A, ++i, r);
        return i;
    }

    public static void QuickSort(int[] A, int p, int r) {
        if (p < r) {
            int q = Partition(A, p, r);
            QuickSort(A, p, q-1);
            QuickSort(A, q+1, r);
        }
    }

    public static int RandomizedPartition(int[] A, int p, int r) {
        int i = p + (int)(Math.random() * (r - p + 1)); // i为p到r中的一个数
        Code_Utils.swap(A, i, r);
        return Partition(A, p, r);
    }

    public static void RandomizedQuickSort(int[] A, int p, int r) {
        if (p < r) {
            int q = RandomizedPartition(A, p, r);
            RandomizedQuickSort(A, p, q-1);
            RandomizedQuickSort(A, q+1, r);
        }
    }

    public static void main(String[] args) {
//        int[] arr = {4,1,3,2,16,9,10,14,8,7};
//        Code_Utils.printArr(arr);
//        RandomizedQuickSort(arr, 0, arr.length-1);
//        Code_Utils.printArr(arr);
        int testTime = 50000;
        int maxSize = 109;
        int maxValue = 100;
        boolean succeed = true;
        for (int i=0; i<testTime; i++) {
            int[] arr = Code_Utils.generateRandomArray(maxSize, maxValue);
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            RandomizedQuickSort(arr, 0, arr.length-1);
            Arrays.sort(arr1);
            if (!Arrays.equals(arr, arr1)) {
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "Test succeed!" : "Test failed!");
    }
}

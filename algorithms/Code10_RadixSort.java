import java.util.Arrays;

public class Code10_RadixSort {

    private static int HighestDigit(int[] A) {
        if (A==null || A.length==0) {
            return 0;
        }
        int max = A[0];
        for (int j : A) {
            max = Math.max(max, j);
        }
        int res = 0;
        while (max != 0) {
            max /= 10;
            res++;
        }
        return res;
    }
    public static void RadixSort(int[] A, int l, int r, int d) {
        int radix = 10;
        int size = r - l + 1;
        int[] bucket = new int[size];
        //在每一位上进行计数排序
        for (int i=0; i<d; i++) {
            int[] cnt = new int[radix];
            //计数
            for (int j=l; j<size; j++) {
                int digit = (A[j] / ((int)(Math.pow(10, i)))) % 10;
                cnt[digit]++;
            }
            //累加换算为出桶位置
            for (int j=1; j<radix; j++) {
                cnt[j] += cnt[j-1];
            }
            //出桶
            for (int j=r; j>=l; j--) {
                int digit = (A[j] / ((int)(Math.pow(10, i)))) % 10;
                bucket[--cnt[digit]] = A[j];
            }
            for (int j=0; j<size; j++) {
                A[l+j] = bucket[j];
            }
        }
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 99999;
        int testTimes = 400000;

        boolean succeed = true;
        for (int i=0; i<testTimes; i++) {
            int[] arr = Code_Utils.generateRandomPositiveArray(maxSize, maxValue);
//        int[] arr = {268, 325, 808, 566, 644, 165};
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            int[] arr2 = Arrays.copyOf(arr, arr.length);
//            Code_Utils.printArr(arr);
            Arrays.sort(arr1);
            RadixSort(arr2, 0, arr.length-1, HighestDigit(arr));
            if (!Arrays.equals(arr2, arr1)) {
                succeed = false;
                Code_Utils.printArr(arr);
                break;
            }
        }
        System.out.println(succeed ? "Test succeed!" : "Test failed!");

    }
}

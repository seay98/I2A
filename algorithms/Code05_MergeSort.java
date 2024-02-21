public class Code05_MergeSort {
    public static void printArr(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /*recursion*/
    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);
        merge(arr, l, r, m);
    }

    /*no recursion*/
    public static void mergeSort1(int[] arr) {
        int step = 1;
        int N = arr.length;
        int l = 0;
        int r = 0;
        int m = 0;

        while (step < N) {
            m = l + step - 1;
            while (m < N-1) {
                r = Math.min(m + step, N - 1);
                merge(arr, l, r, m);
                l = r + 1;
                m = l + step - 1;
            }
            l = 0;
            //防止溢出
            if (step > N/2) {
                break;
            }
            step <<= 1;
        }
    }

    private static void merge(int[] arr, int l, int r, int m) {
        int[] arrHlp = new int[r - l + 1];
        int pl = l;
        int pr = m + 1;
        int i = 0;

        while (pl <= m && pr <= r) {
            arrHlp[i++] = arr[pl] <= arr[pr] ? arr[pl++] : arr[pr++];
        }
        while (pl <= m) {
            arrHlp[i++] = arr[pl++];
        }
        while (pr <= r) {
            arrHlp[i++] = arr[pr++];
        }
        for (i=0; i<arrHlp.length; i++) {
            arr[l++] = arrHlp[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 5, 8, 3, 6, 8, 7, 6, 9, 0};

        printArr(arr);
//        mergeSort(arr, 0, arr.length-1);
        mergeSort1(arr);
        printArr(arr);
    }
}

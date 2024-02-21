public class Code02_Sort {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArr(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /*//选择排序，操作数*/
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for (int i=0; i<N; i++) {
            int minValue = arr[i];
            for (int j=i+1; j<N; j++) {
                if (arr[j] < arr[i]) {
                    minValue = arr[j];
                    swap(arr, i, j);
                }
            }
        }
    }

    //选择排序，操作索引
    public static void selectionSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for (int i=0; i<N; i++) {
            int minValueIndex = i;
            for (int j=i+1; j<N; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    /*冒泡排序*/
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for (int i=0; i<N; i++) {
            for (int j=1; j<N-i; j++) {
                if (arr[j-1] > arr[j]) {
                    swap(arr, j-1, j);
                }
            }
        }
    }

    /*插入排序*/
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for (int i=1; i<N; i++) {
            int curIndex = i;
            while (curIndex >= 1 && arr[curIndex] < arr[curIndex-1]) {
                swap(arr, curIndex, curIndex-1);
                curIndex--;
            }
        }
    }

    /*插入排序1*/
    public static void insertSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for (int i=1; i<N; i++) {
            for (int j=i; j>=1 && arr[j] < arr[j-1]; j--) {
                swap(arr, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 5, 8, 3, 6, 8, 7, 6, 9, 0};

        printArr(arr);
//        selectionSort1(arr);
//        bubbleSort(arr);
        insertSort1(arr);
        printArr(arr);
    }
}

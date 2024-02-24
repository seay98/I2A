public class Code_Utils {
    public static void printArr(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static int[] copyArr(int[] arr) {
        if (arr==null) {
            return null;
        }
        int[] copy = new int[arr.length];
        for (int i=0; i<arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /*完全随机*/
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i=0; i<arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)((maxValue + 1) * Math.random());
        }
        return arr;
    }

    /*完全随机正数数组*/
    public static int[] generateRandomPositiveArray(int maxSize, int maxValue) {
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i=0; i<arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random());
        }
        return arr;
    }

    /*相邻不相等*/
    public static int[] generateRandomArray1(int maxSize, int maxValue) {
        int len = (int)(Math.random() * maxSize);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int)(Math.random() * maxValue);
            for (int i=1; i<len; i++) {
                do {
                    arr[i] = (int)(Math.random() * maxValue);
                }while (arr[i] == arr[i-1]);
            }
        }
        return arr;
    }
}

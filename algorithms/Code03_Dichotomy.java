public class Code03_Dichotomy {

    public static void printArr(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static boolean find(int[] arr, int num) {
        if (arr==null || arr.length==0) {
            return false;
        }

        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == num) {
                return true;
            }
            else if (num < arr[mid]) {
                r = mid - 1;
            }
            else if (num > arr[mid]) {
                l = mid + 1;
            }
        }

        return false;
    }

    public static int biggerThanLeft(int[] arr, int num) {
        if (arr==null || arr.length==0) {
            return -1;
        }

        int l = 0;
        int r = arr.length - 1;
        int idx = -1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (num <= arr[mid]) {
                r = mid - 1;
                idx = mid;
            }
            else if (num > arr[mid]) {
                l = mid + 1;
            }
        }
        return idx;
    }

    /*查找局部最小值*/
    public static int relativeSmallest(int[] arr) {
        if (arr==null || arr.length==0) {
            return -1;
        }

        int N = arr.length;
        if (N == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length-1] < arr[arr.length-2]) {
            return N - 1;
        }

        int l = 0;
        int r = N - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            //处理当mid与l重合为0的情况，如数组[3,2,3,4,5]
            if (mid == 0) {
                return mid + 1;
            }
            if (arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]) {
                return mid;
            }
            else if (arr[mid] > arr[mid-1]) {
                r = mid - 1;
            }
            else if (arr[mid] > arr[mid+1]) {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static boolean BSEtest(int[] sortedArr, int num) {
        for (int cur : sortedArr) {
            if (cur == num) {
                return true;
            }
        }
        return false;
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

    /*完全随机*/
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i=0; i<arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    public static boolean checkSmallest(int[] arr, int idx) {
        if (idx == -1) {
            return true;
        }
        int val = arr[idx];
        boolean smallerThanLeft = idx==0 ? true : arr[idx] < arr[idx-1];
        boolean smallerThanRight = idx==(arr.length-1) ? true : arr[idx] < arr[idx+1];

        return smallerThanLeft && smallerThanRight;
    }

    public static void main(String[] args) {
//        int[] arr1 = {5, 4, 1, 5, 8, 3, 6, 8, 7, 6, 9, 4, 3, 3, 12, 14};
        int[] arr1 = {87, 37, 48, 95, 56};
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
//        boolean succeed = true;
//
//        for (int i=0; i<testTime; i++) {
//            int[] arr = generateRandomArray(maxSize, maxValue);
//            Arrays.sort(arr);
//            int num = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
//            if (find(arr, num) != BSEtest(arr, num)) {
//                succeed = false;
//                break;
//            }
//        }

//        System.out.println(succeed ? "Test succeed!" : "Test failed!");

//        Arrays.sort(arr1);
//        printArr(arr1);
//        int res = biggerThanLeft(arr1, 10);
//        System.out.println(res);

//        printArr(arr1);
//        int res = relativeSmallest(arr1);
//        System.out.println(res);

        for (int i=0; i<testTime; i++) {
            int[] arr = generateRandomArray1(maxSize, maxValue);
            int idx = relativeSmallest(arr);
            if (!checkSmallest(arr, idx)) {
                printArr(arr);
                System.out.println(idx);
                System.out.println("Test failed!");
                break;
            }
        }
        System.out.println("Test finished!");
//        checkSmallest(arr1, relativeSmallest(arr1));
    }
}

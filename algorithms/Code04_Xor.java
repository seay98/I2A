import java.util.HashMap;
import java.util.HashSet;

public class Code04_Xor {

    public static void printArray(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void printBinary(int a) {
        for (int i=31; i>=0; i--) {
            System.out.print((a & (1<<i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static int findK(int[] arr, int k, int m) {
        int knum = 0;
        int[] cntOne = new int[32];
        for (int i=0; i<32; i++) {
            cntOne[i] = 0;
        }
        //统计所有位置1的个数
        for (int val : arr) {
            for (int i=0; i<32; i++) {
                if ((val&(1<<i)) != 0) {
                    cntOne[i]++;
                }
            }
        }
        //用1出现的次数模m，如果结果为0，则说明出现k次的数次位置一定不为1，反之亦然
        for (int i=0; i<32; i++) {
            if ((cntOne[i]%m) != 0) {
                knum = knum ^ (1<<i);
            }
        }
        return knum;
    }

    public static int testKm(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int val : arr) {
            if (map.containsKey(val)) {
                map.put(val, map.get(val) + 1);
            } else {
                map.put(val, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    /*生产测试数组，数组中有一个数出现k次，其余数出现m次，k<m */
    public static int[] randomArrayKm(int maxSize, int maxValue, int k, int m) {
        int numSize = (int)(Math.random() * maxSize) + 2; //2 ~ maxSize + 1
        int[] arr = new int[(numSize - 1) * m + k];
        int idx = 0;

        HashSet<Integer> set = new HashSet<>();
        for (int i=0; i<numSize; i++) {
            int randomNum = 0;
            do {
                randomNum = (int)((Math.random() * maxValue) + 1) - (int)((Math.random() * maxValue) + 1);
            } while (set.contains(randomNum));
            set.add(randomNum);
            if (i < (numSize-1)) {
                for (int j=0; j<m; j++) {
                    arr[idx++] = randomNum;
                }
            } else {
                for (int j=0; j<k; j++) {
                    arr[idx++] = randomNum;
                }
            }
        }

        //打乱数组
        for (int i=0; i<arr.length; i++) {
            int j = (int)(Math.random() * arr.length);
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    public static void main(String[] args) {

        //不用额外变量数组交换//////////////////////////////////////////////////
        int[] arr = {3, 2, 5, 1};
        //当a等于b时会出错，数值被置0
        int a = 0;
        int b = 3;
        arr[a] = arr[a] ^ arr[b]; // a = a ^ b
        arr[b] = arr[a] ^ arr[b]; // b = a ^ b ^ b
        arr[a] = arr[a] ^ arr[b]; // a = a ^ b ^ a
        printArray(arr);

        //找出出现奇数次的那个数值///////////////////////////////////////////////
        int[] arr1 = {3, 2, 5, 1, 3, 2, 3, 5, 2, 1, 3};
        int res = 0;
        for (int v : arr1) {
            res ^= v;
        }
        System.out.println(res);

        //获得整数最右边的1，其他位置0；///////////////////////////////////////////
        int x = -760;
        printBinary(x);
        int fx = (~x) + 1;
        int r = x & fx; // a & -a
        printBinary(r);

        //找出出现奇数次的两个数////////////////////////////////////////////////////////
        int[] arr2 = {3, 2, 5, 1, 3, 2, 3, 5, 2, 1, 3, 5};
        int xr = 0;
        //得到a^b
        for (int v : arr2) {
            xr ^= v;
        }
        //找到xr中的一个1，这里找最右边的1（为1代表这一位a与b不同，故在异或后为1），其他位置0
        int one = xr & ((~xr) + 1);
        //将所有这一位为1的数异或，得到第一个数，我们记他为a
        int aa = 0;
        for (int v : arr2) {
            if ((v & one) != 0) {
                aa ^= v;
            }
        }
        //用a^b^a得到b
        int bb = xr ^ aa;
        System.out.println(aa + ", " + bb);

        //找到数组中出现了k次的数///////////////////////////////////////////////////////////

        //test
        int testTime = 50000;
        int maxSize = 1000;
        int maxValue = 200;
        int maxTime = 9;

        for (int i=0; i<testTime; i++) {
            int aaa = (int)(Math.random() * maxTime) + 1; //1 ~ 9
            int bbb = (int)(Math.random() * maxTime) + 1; //1 ~ 9
            int kk = Math.min(aaa, bbb);
            int mm = Math.max(aaa, bbb);
            if (kk == mm) {
                mm++;
            }
            int[] arrkm = randomArrayKm(maxSize, maxValue, kk, mm);
            //printArray(arrkm);
            int an1 = findK(arrkm, kk, mm);
            int an2 = testKm(arrkm, kk, mm);
            if (an2 != an1) {
                System.out.println("Test failed!");
            }
        }
        System.out.println("Test finished!");
    }
}

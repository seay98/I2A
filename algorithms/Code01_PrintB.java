public class Code01_PrintB {
    public static void print(int num) {
        for (int i=31; i>=0; i--) {//从大到小打印，否则打印顺序相反
            System.out.print((num & 1<<i) == 0 ? '0' : '1');
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int num = 856123294;
        print(num);

        print(Integer.MAX_VALUE); //最高位为符号位

        print(-1); //最高位为符号位，负数表示需要取反再加1

        print(Integer.MIN_VALUE); //能表示的数的范围：-2^31 ~ 2^31-1。能表示的数的个数：正负都为2^31个

        print(Integer.MIN_VALUE >> 1); //带符号右移，用符号为填充
        print(Integer.MIN_VALUE >>> 1); //不带符号右移，用0填充

        //取反再加1，可使正负互相转化。但最小数的相反数是他自己。
        print(~Integer.MIN_VALUE + 1);

    }
}

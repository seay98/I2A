public class Code12_ListPartition {

    public static class Node {
        int value;
        Node next;
        public Node(int data) {
            value = data;
            next = null;
        }
    }

    /* 荷兰国旗问题 */
    public static void partitionArray(int[] A, int pivot) {
        int small = -1;
        int big = A.length;
        int idx = 0;
        while (idx != big){
            if (A[idx] < pivot) {
                Code_Utils.swap(A, ++small, idx++);
            } else if (A[idx] == pivot) {
                idx++;
            } else {
                Code_Utils.swap(A, --big, idx);
            }
        }
    }

    /* 使用容器进行分区 */
    public static void partitionContainer(Node head, int pivot) {
        Node cur = head;
        int idx = 0;
        //获得链表大小
        while (cur != null) {
            idx++;
            cur = cur.next;
        }
        //将值放入数组
        int[] arr = new int[idx];
        cur = head;
        idx = 0;
        while (cur != null) {
            arr[idx++] = cur.value;
            cur = cur.next;
        }
        //分区
        partitionArray(arr, pivot);
        //将值放回链表
        cur = head;
        idx = 0;
        while (cur!=null) {
            cur.value = arr[idx++];
            cur = cur.next;
        }
    }

    /* 不使用容器的分区 */
    public static Node partitionList(Node head, int pivot) {
        Node smallHead = null;
        Node smallEnd = null;
        Node equalHead = null;
        Node equalEnd = null;
        Node bigHead = null;
        Node bigEnd = null;

        //按值将节点加入相应链表
        Node cur = head;
        while (cur != null) {
            if (cur.value < pivot) {
                if (smallHead == null) {
                    smallHead = cur;
                } else {
                    smallEnd.next = cur;
                }
                smallEnd = cur;
            } else if (cur.value == pivot) {
                if (equalHead == null) {
                    equalHead = cur;
                } else {
                    equalEnd.next = cur;
                }
                equalEnd = cur;
            } else {
                if (bigHead == null) {
                    bigHead = cur;
                } else {
                    bigEnd.next = cur;
                }
                bigEnd = cur;
            }
            cur = cur.next;
        }

        //将三个链表相连，设置原链表头
        if (smallEnd != null) {
            smallEnd.next = equalEnd==null ? bigHead : equalHead;
        }
        if (equalEnd != null) {
            equalEnd.next = bigHead;
        }
        if (bigEnd != null) {
            bigEnd.next = null;
        }
        return smallEnd==null ? (equalEnd==null ? bigHead : equalHead) : smallHead;
    }

    public static Node generateRandomList(int maxSize, int maxValue) {
//        int[] arr = {1,2,3,2,1};
        int[] arr = Code_Utils.generateRandomArray(maxSize, maxValue);

        Node head = null;
        Node end = null;
        for (int i=0; i<arr.length; i++) {
            Node node = new Node(arr[i]);
            if (i == 0) {
                head = node;
            } else {
                end.next = node;
            }
            end = node;
        }
        return head;
    }

    public static int findNum(Node head, int num) {
        int res = -1;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                res++;
                break;
            }
            res++;
            cur = cur.next;
        }
        return res;
    }

    public static Node copyList(Node head) {
        Node newHead = null;
        Node newEnd = null;
        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.value);
            if (newHead == null) {
                newHead = node;
            } else {
                newEnd.next = node;
            }
            newEnd = node;
            cur = cur.next;
        }
        return newHead;
    }

    public static void printList(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 999;
        int testRounds = 1000;

        boolean succeed = true;
        for (int i=0; i<testRounds; i++) {
            Node ll = generateRandomList(maxSize, maxValue);
            Node ll1 = copyList(ll);
            int pivot = ll==null ? 0 : ll.value;
            ll = partitionList(ll, pivot);
//            printList(ll);
            partitionContainer(ll1, pivot);
//            printList(ll1);
            if (findNum(ll, pivot) != findNum(ll1, pivot)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Test succeed!" : "Test failed!");
    }
}

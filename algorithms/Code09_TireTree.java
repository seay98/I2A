import java.util.HashMap;

public class Code09_TireTree {

    private static class Node {
        int pass;
        int end;
        HashMap<Character, Node> nexts; //HashMap<Integer, Node> nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class TireTree{
        private Node root;

        public TireTree() {
            root = new Node();
        }

        /* 将字符串加入前缀树 */
        public void insert(String word) {
            if (word == null) {
                return;
            }

            char[] chs = word.toCharArray();
            Node node = root;
            node.pass++;
            //逐字符加入前缀树
            for (int i=0; i<chs.length; i++) {
                //字符路径不存在则创建
                if (!node.nexts.containsKey(chs[i])) {
                    node.nexts.put(chs[i], new Node());
                }
                //更新节点属性
                node = node.nexts.get(chs[i]);
                node.pass++;
            }
            node.end++;
        }

        /* 在树中查找字符串word，返回出现次数 */
        public int search(String word) {
            if (word == null) {
                return 0;
            }

            char[] chs = word.toCharArray();
            Node node = root;
            for (int i=0; i<chs.length; i++) {
                if (!node.nexts.containsKey(chs[i])) {
                    return 0;
                }
                node = node.nexts.get(chs[i]);
            }
            return node.end;
        }

        /* 在树中查找前缀为pre的字符串，返回出现次数 */
        public int searchPre(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node node = root;
            for (int i=0; i<chs.length; i++) {
                if (!node.nexts.containsKey(chs[i])) {
                    return 0;
                }
                node = node.nexts.get(chs[i]);
            }
            return node.pass;
        }

        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            char[] chs = word.toCharArray();
            Node node = root;
            node.pass--;
            for (int i=0; i<chs.length; i++) {
                if (--node.nexts.get(chs[i]).pass == 0) {
                    node.nexts.put(chs[i], null);
                    return;
                }
                node = node.nexts.get(chs[i]);
            }
            node.end--;
        }
    }


}

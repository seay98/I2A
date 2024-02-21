import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code08_LineMaxCover {
    public static class Line {
        public int start;
        public int end;
        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class LineEndComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }

    public static class LineStartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int MaxCover(int[][] p) {
        Line[] lines = new Line[p.length];
        for (int i=0; i<p.length; i++) {
            lines[i] = new Line(p[i][0], p[i][1]);
        }
        //线段按起点排序，升序。
        Arrays.sort(lines, new LineStartComparator());

        //创建一个最小堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int res = 0;
        //按起点从小到大遍历每一条线段
        for (int i=0; i<lines.length; i++) {
            //当堆中记录的线段终点小于等于当前线段起点时，弹出。
            //此操作表示将终点在该起点前的线段删除，因为其必然不与该线重合。
            //操作后，此时堆中数值个数为通过该起点的重合线段的数目。
            while (!heap.isEmpty() && heap.peek()<=lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            //记录出现过的线段的重合数的最大值
            res = Math.max(res, heap.size());
        }
        return res;
    }

    
}

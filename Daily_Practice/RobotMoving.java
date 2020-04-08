import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目描述：地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 * 本题关键点在于读懂题目的意思，而且要看到可能存在的坑。
 * 机器人是从(0, 0)开始运动的，而且每次的移动是上下左右任意方向的一个单位，条件是所在坐标的各位之和要小于k，这就可能存在类似图里面的“非联通”的情况，即可能存在一些区域满足小于k 的条件，但是从(0, 0)出发到达不了这些区域，因为中间有不满足小于k的区域，这些区域成了机器人运动的障碍物。
 * 因此，本题可采用DFS算法或者BFS算法进行求解
 */


public class RobotMoving {

    //DFS
    int m, n, k;
    boolean[][] visited;
    public int movingCount_01(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.visited = new boolean[m][n];
        return dFS(0, 0, 0, 0);
    }

    //i，j为方格中的行列索引，si，sj为对应索引上的数位之和
    public int dFS(int i, int j, int si, int sj) {
        //递归终止条件
        if (i < 0 || i >= m || j < 0 || j >= n || si + sj > k || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        //回溯返回：当前可达解+下方的可达解数+右方的可达解数
        return 1 + dFS(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj) + dFS(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);
    }


    //BFS
    public int movingCount_02(int m, int n, int k) {
        int ans = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 0, 0});
        while (queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0], j = x[1], si = x[2], sj = x[3];
            if (i < 0 || i >= m || j < 0 || j >= n || si + sj > k || visited[i][j]) {
                continue;
            }
            visited[i][j] = true;
            ++ans;
            queue.add(new int[] {i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj});
            queue.add(new int[] {i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8});
        }
        return ans;
    }

    public static void main(String[] args) {
        //测试集
        RobotMoving r = new RobotMoving();
        // System.out.println(r.movingCount_01(2, 3, 1));
        // System.out.println(r.movingCount_01(3, 1, 0));
        // System.out.println(r.movingCount_01(16, 8, 4));
        System.out.println(r.movingCount_01(38, 15, 9));
        System.out.println(r.movingCount_02(38, 15, 9));
    }
}
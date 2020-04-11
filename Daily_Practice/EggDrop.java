import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 *         每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 *         你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 *         每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 *         你的目标是确切地知道 F 的值是多少。
 *         无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * 
 * 分析：这里首先得吐槽下，LeetCode上有些题目是一些国外知名企业的面试题，题目的中文意思大多都是根据英文翻译过来的，但有时的翻译着实看着令人百思不得其解，
 *      本题中的也是，有很多信息也没直接指明，比如：如果鸡蛋扔下去没碎，还可以继续使用，说得太过于含糊。
 *      动态规划的题目做的不算太多，这道题也拿来学习学习吧
 * 
 *      本题的解决办法是采用的动态规划+二分搜索
 */

public class EggDrop {

    public int eggDrop(int K, int N) {
        return dp(K, N);
    }

    Map<Integer, Integer> memo = new HashMap<>();
    public int dp(int K, int N) {
        if (!memo.containsKey(N * 100 + K)) {       //N * 100 + K是为了保证
            int ans;
            if (N == 0)
                ans = 0;
            else if (K == 1)
                ans = N;
            else {
                int lo = 1, hi = N;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(K-1, x-1);
                    int t2 = dp(K, N-x);

                    if (t1 < t2)
                        lo = x;
                    else if (t1 > t2)
                        hi = x;
                    else
                        lo = hi = x;
                }
                ans = 1 + Math.min(Math.max(dp(K-1, lo-1), dp(K, N-lo)),
                                   Math.max(dp(K-1, hi-1), dp(K, N-hi)));
            }
            memo.put(N * 100 + K, ans);
        }
        return memo.get(N * 100 + K);
    }

    public static void main(String[] args) {
        EggDrop e = new EggDrop();
        System.out.println(e.eggDrop(1, 2));
        System.out.println(e.eggDrop(2, 6));
        System.out.println(e.eggDrop(3, 14));
    }
}

public class Solution_01 {
    public static int countLargestGroup(int n) {
        int ans = 0;        //返回值
        int max = 0;
        int[] arr = new int[n+1];     //转存数组
        for(int i = 1; i <= n; i++) {
            int temp = 0;
            while(i > 0) {
                temp += i % 10;
                i /= 10;
            }
            System.out.println(temp);
            ++arr[temp];
        }
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == max) {
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Solution_01.countLargestGroup(9));
    }
}
import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */

public class GetRain {

    //采用单调栈的方法来解答
    //单调栈就是比普通的栈多一个性质，即维护一个栈内元素单调。
    public static int trap(int[] height) {
        if(height == null) {        //数组为空，返回0
            return 0;
        }

        int ans = 0;                //最终返回的结果
        Stack<Integer> stack = new Stack<>();           //定义栈，存放数组height下标
        for(int i = 0; i < height.length; ++i) {
            while(!stack.empty() && height[stack.peek()] < height[i]) {
                int curIdx = stack.pop();               //当前出栈的下标

                //数组height当前的元素与栈顶的元素相等，出栈
                while (!stack.empty() && height[stack.peek()] == height[curIdx]) {
                    stack.pop();
                }

                if (!stack.empty()) {
                    int stackTop = stack.peek();        //stackTop为能够接住雨水的左边界，对应数组height里的下标
                    //Math.min(height[stackTop], height[i])为当前能够左边界与当前柱子更小的那个
                    //i-stackTop-i为左边界与当前柱子之间的宽度，这也解释了为什么stack里面存放下标，可以方便此时计算宽度
                    //计算的实质是按一层一层来计算的，每一层按宽度×高度得到当前层的雨水量
                    ans += (Math.min(height[stackTop], height[i]) - height[curIdx]) * (i - stackTop - 1);
                }
            }
            stack.push(i);      //下标进栈，对应数组height的值从栈底到栈顶严格单调递减，即单调栈
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(GetRain.trap(height));
    }
}
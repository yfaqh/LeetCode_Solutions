import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * 括号成对出现，也就是说左括号和右括号都是n个，添加右括号的时候要保证成对的括号的话，此时右括号剩余的个数必须要大于左括号剩余的个数
 * 返回值是一个/List类型的集合，需要将每次产生的一个结果通过add方法添加到List中
 * 主要是是采用深度遍历的思想，同时采用递归和回溯的方法得到结果
 */

public class GenerateBrackets {

    List<String> ansList = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        dFS(n, n, "");
        return ansList;
    }

    public void dFS(int left, int right, String string) {
        if (left == 0 && right == 0) {
            ansList.add(string);
        }
        if (left > 0) {
            dFS(left - 1, right, string + "(");
        }
        if (right > left) {
            dFS(left, right - 1, string + ")");
        }
    }
}
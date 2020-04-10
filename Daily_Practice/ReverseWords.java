
/**
 * 题目描述：给定一个字符串，逐个翻转字符串中的每个单词。
 * 
 * 说明：
 * 1.无空格字符构成一个单词。
 * 2.输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 3.如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 
 * 思路：
 * 根据说明里的要求和可能存在的极端情况，分为三步：对所给的s按空格符进行分割；然后进行相应的判断处理；最后添加到新的字符串ans。
 * 1.分割使用split()方法，这里一般使用的就是重写的split()方法，只有一个参数regex，即split(String regex)。
 *   实际上的split()方法有两个参数regex和limit，即split(String regex, int limit)，上面默认的limit为0。
 *   参数解释：regex参数实际上是一个正则表达式，用来限定分隔符规则，将字符串分割为若干个子字符串，通俗讲就是用什么来进行分割；而limit参数控制应用模式的次数，因此影响生成的数组的长度。
 *           1)如果极限n大于0，则模式最多应用n -1次，数组的长度不大于n ，数组的最后一个条目将包含超出最后一个匹配分隔符的所有输入。
 *           2)如果n是非正的，那么模式将被应用到尽可能多的次数，并且数组可以有任何长度。
 *           3)如果n为0，则模式将被应用尽可能多次，数组可以有任何长度，并且尾随的空字符串将被丢弃。
 * 
 * 2.相应的判断与处理：若是正常情况，单词之间由一个空格分隔，则分割后的字符串数组无需处理，直接反向拼接加上空格隔开；若是单词之间有若干个空格，则分割后的字符串数组会有空字符串，这时不需要将空字符串加入到ans字符串
 * 
 * 3.最后返回时需要使用trim()方法，去除掉字符串ans首尾的空格。
 */

public class ReverseWords {

    public String reverseWords(String s) {
        String ans = "";
        String[] strArray = s.split(" ");
        for (int i = strArray.length - 1; i >= 0; i--) {
            //输出测试
            // System.out.println(strArray[i] + "-->" +strArray[i].length());
            if (strArray[i].equals("")) {
                continue;
            }
            ans += strArray[i];
            ans += " ";
        }
        //输出测试
        // System.out.println(ans.trim().length());
        return ans.trim();
    }

    public static void main(String[] args) {
        ReverseWords r = new ReverseWords();
        //测试用例
        // System.out.println(r.reverseWords("the sky is blue"));
        System.out.println(r.reverseWords("  hello world!  "));
        // System.out.println(r.reverseWords("a good   example"));
    }
}
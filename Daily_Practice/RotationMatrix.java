
/**
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 */

public class RotationMatrix {

    //int n = N - 1;
    //matrix[i][j]旋转后得位置matrix[j][n - i]
    //本题的解决方式是从外到内对矩阵进行一层一层的旋转，即对数据进行换位

    class Solution {
        public void rotate(int[][] matrix) {
            int N = matrix[0].length;       //数组matrix对应的N的值
            int floor = 0;                  //需要旋转的层数
            int n = N - 1;                  //对应到数组matrix最大的下标
            int cir = N;

            //判断出旋转的层数
            if(N % 2 == 1) {
                floor = N / 2 + 1; 
            } else {
                floor = N / 2;
            }

            for(int i = 0; i < floor; ++i) {        //旋转操作
                cir = N + i - 1;                    //最核心的一步：得到该层的最大下标
                /* 上面一步可分解为：
                    cir = N;
                    cir += i;
                    cir -= 1;
                */
                for(int j = i; j < cir; ++j) {     //在该层进行旋转的时候取不到最大下标处，cir-j为当前层内需要旋转的组数
                    //四个方位进行旋转交换，需要用到两个中间变量temp1，temp2
                    int temp1 = matrix[j][n - i];
                    matrix[j][n - i] = matrix[i][j];
                    int temp2 = matrix[n - i][n - j];
                    matrix[n - i][n - j] = temp1;
                    temp1 = matrix[n - j][i];
                    matrix[n - j][i] = temp2;
                    matrix[i][j] = temp1;
                }
                N -= 2;     //进入下一层，下一层的长度比上一层小2
            }
        }
    }
}
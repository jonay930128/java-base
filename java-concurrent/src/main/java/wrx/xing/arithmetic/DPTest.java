package wrx.xing.arithmetic;

import java.util.Arrays;
import java.util.Random;

/**
 * @author liuyumeng
 * @create 2019-11-05
 */
public class DPTest {
    public static void main(String[] args) {

//        int[] arr=  new int[50];
//        int[] arr = {2,3,5,7,11,13,1,3,1,3,5,5,5,1};
//        int[] arr = {2,3,5,7,11};
        int[] arr = {1, 1, 3, 5, 10};
//        for(int i =0;i<arr.length;i++){
//            arr[i] = new Random().nextInt(20);
//            arr[i]=i+1;
//        }
        System.out.println(Arrays.toString(arr));
        int target = 13;

        int sum = 0;
        for(int i =0;i<arr.length;i++){
            sum+=arr[i];
        }
        if(sum<target){
            System.out.println(Arrays.toString(arr));
            return;
        }
        //否则，用dp
//        int[][] dps = new int[arr.length][sum];
        boolean[][] dps = new boolean[arr.length][sum+1]; // 默认值false
        dps[0][0] = true;
        if (arr[0] <= sum) {
            dps[0][arr[0]] = true;
        }
        for (int i = 1; i < arr.length; ++i) { // 动态规划
            for (int j = 0; j <= sum; ++j) {// 不用
                if (dps[i-1][j] == true) dps[i][j] = dps[i-1][j];
            }
            for (int j = 0; j <= sum-arr[i]; ++j) {//用
                if (dps[i-1][j]==true) dps[i][j+arr[i]] = true;
            }
        }
        int val = 0;
        for (int i = target; i <= sum; i++) { // 输出结果
            if (dps[arr.length-1][i] == true) {
                System.out.println(i);
                val = i;
                break;
            }
        }
        int[] a = new int[arr.length];
        for(int i =arr.length-1;i>=1;i--){
            if(dps[i][val]&& (!dps[i-1][val])){
                a[i]=1;
                val-=arr[i];
            }
        }
        if(val >0){
            a[0]=1;
        }

        System.out.println(Arrays.toString(a));



    }
}

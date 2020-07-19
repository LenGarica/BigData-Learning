package LeetCode.easy.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * LeetCode 数组类型题目
 * 题目来源： https://leetcode-cn.com/problems/two-sum/
 */

public class Code_1 {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int temp = target - nums[i];
            if(map.containsKey(temp)){
                return new int[]{map.get(temp),i};
            }else{
                map.put(nums[i] , i);
            }
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,7,11,15};
        int[] b = new int[]{3,5,9,4};
        Code_1 co = new Code_1();
        System.out.println(Arrays.toString(co.twoSum(a, 9)));
        System.out.println(Arrays.toString(co.twoSum(b, 13)));
    }


}

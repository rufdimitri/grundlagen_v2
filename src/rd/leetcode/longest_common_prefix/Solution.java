package rd.leetcode.longest_common_prefix;

/**
 *  https://leetcode.com/problems/longest-common-prefix/
 *  14. Longest Common Prefix
 * Easy
 * Topics
 * Companies
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(
            new Solution().longestCommonPrefix(new String[]{"flower", "flow", "flight"})
        );
    }

    public String longestCommonPrefix(String[] strs) {
        int count = 0;
        String prefix = null;
        if (strs.length > 0) {
            prefix = strs[0];
            count = prefix.length();
        } else {
            return "";
        }

        for (String str : strs) {
            count = Math.min(str.length(), count);
            for (int i = 0; i < count; i++) {
                if (str.charAt(i) != prefix.charAt(i)) {
                    count = i;
                    break;
                }
            }
        }

        return prefix.substring(0, count);
    }
}

package rd.leetcode.boats_to_save_people;

import rd.files.ListUtil;

/** https://leetcode.com/problems/boats-to-save-people/description/?envType=daily-question&envId=2024-05-04
 *  You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats
 *  where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time,
 *  provided the sum of the weight of those people is at most limit.
 *  Return the minimum number of boats to carry every given person.
 */
public class Main {
    public static void main(String[] args) {
        int limit = 29999;
        int[]people = ListUtil.readFromFile("C:\\Temp\\array_people.txt ", ",")
                .stream()
                .mapToInt(Integer::parseInt)
                .toArray();
        int boats = new Solution().numRescueBoats(people, limit);
        System.out.println("Boats needed: " + boats);
    }
}

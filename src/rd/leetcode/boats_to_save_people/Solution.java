package rd.leetcode.boats_to_save_people;

public class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int boats = 0;
        //System.out.println(Arrays.toString(people));
        for(int i = 0; i < people.length; i++) {
            if (people[i] < 0) //person is already saved
                continue;
            int bestPartnerId = -1;
            int maxPartnerWeight = limit - people[i];
            //System.out.println("person " + people[i] + " looking for partner <= " + maxPartnerWeight);
            if (maxPartnerWeight > 0) { //makes sense to search for a partner
                for (int j = i+1; j < people.length; j++) { //try to find a light partner
                    if (people[j] < 0) //person is already saved
                        continue;
                    if ((bestPartnerId < 0 || people[j] > people[bestPartnerId]) && people[j] <= maxPartnerWeight) {
                        bestPartnerId = j;
                        if (people[j] == maxPartnerWeight) {
                            break; //boat is full with this partner, there is no better partner
                        }
                    }
                }
                if (bestPartnerId >= 0) {
                    //System.out.println("person " + people[bestPartnerId] + " saved on boat " + boats);
                    people[bestPartnerId] = -1; //saved
                }
            }

            //System.out.println("person " + people[i] + " saved on boat " + boats);
            people[i] = -1;
            boats++;
            //System.out.println(Arrays.toString(people));
        }
        return boats;
    }
}

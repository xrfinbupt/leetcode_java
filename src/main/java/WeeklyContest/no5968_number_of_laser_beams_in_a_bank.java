package WeeklyContest;

/**
 * https://leetcode-cn.com/problems/number-of-laser-beams-in-a-bank
 *
 * @author xurongfei
 * @Date 2022/1/2
 */
public class no5968_number_of_laser_beams_in_a_bank {

    public int numberOfBeams(String[] bank) {
        int result = 0;
        int m = bank.length;
        if (m == 0) {
            return result;
        }

        int last1Count = 0;
        for (int i = 0; i < m; i++) {
            String line = bank[i];
            if (line.contains("1")) {
                int current1Count = get1Count(line);
                if (last1Count > 0) {
                    result += current1Count * last1Count;
                }
                last1Count = current1Count;
            }
        }
        return result;
    }

    private int get1Count(String line) {
        int count = 0;
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char ch = line.charAt(i);
            if (ch == '1') {
                count++;
            }
        }
        return count;
    }
}

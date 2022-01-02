package WeeklyContest;

import java.math.BigInteger;
import java.util.*;

/**
 * @author xurongfei
 * @Date 2022/1/2
 */
public class no5967_check_if_all_as_appears_before_all_bs {
    public boolean checkString(String s) {
        int len = s.length();
        Boolean bFlag = null;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (bFlag == null) {
                if (ch == 'b') {
                    bFlag = true;
                }
            } else {
                if (ch == 'a') return false;
            }
        }
        return true;
    }
}

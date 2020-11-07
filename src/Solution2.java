import java.util.Arrays;


public class Solution2 {

    final String OP_PLUS = "+";
    final String OP_MINUS = "-";
    final String OP_MULTIPLY = "*";

    public long[] solution(String s, String op) {
        long[] answer = new long[s.length()-1];

        //0. 예외처리(제한사항)
        if (!(s.length() >=2 && s.length() <= 10))
            prt("Error: s length error!!");


        //1. 스트링 s를 사이즈-1 만큰 나눠라
        //2. 나눌때 두개로 나눠라(lStr, rStr)
        //3. answer[idx] = lStr op(+/-/*) rStr;

        String lStr = null;
        String rStr = null;
        for (int idx = 1; idx<s.length(); idx++) {
            lStr = s.substring(0, idx);
            rStr = s.substring(idx);
            switch (op) {
                case OP_PLUS:
                    answer[idx-1] = Long.parseLong(rStr) + Long.parseLong(lStr);
                    break;
                case OP_MINUS:
                    answer[idx-1] = -(Long.parseLong(rStr) - Long.parseLong(lStr));
                    break;
                case OP_MULTIPLY:
                    answer[idx-1] = Long.parseLong(rStr) * Long.parseLong(lStr);
                    break;
                default:
                    prt("Error:  operator error!");
                    break;
            }
        }

        return answer;
    }

    void run() {
//        Test 값
/*  s	        op	        result
  "1234"	    "+"	    [235,46,127]
  "987987"	    "-"	    [-87978,-7889,0,9792,98791]
  "31402"	    "*"	    [4206,12462,628,6280]
*/
        final String s1 = "1234";
        final String op1 = "+";
        final long[] expected1 = {235,46,127};

        final String s = s1;
        final String op = op1;
        final long[] expected = expected1;


        prt("우아한테크코스-프로그래머스 코딩테스트 2번 문제 : '암호문을 해석'");
        final long[] actual = solution(s, op);

        if (actual == null | actual.length != expected.length ) {
            prt("error : returned answer (null or length) error!");
            return;
        }
        for (int i=0; i<actual.length; i++) {
            if ( expected[i] != actual[i]) {
                prt("error : returned answer's value error!");
                return;
            }
        }
        prt("Test 성공");

        prt("result : " + Arrays.toString(Arrays.stream(actual).boxed().toArray(Long[]::new)) );
    }

    private void prt(String msg) {
        System.out.println(msg);
    }

}

import java.util.HashMap;
import java.util.Map;

public class Solution1 {

    final Integer ERROR_GRADES_LENGTH = -1;
    final Integer ERROR_GRADES_KEY = -2;
    final Integer ERROR_WEIGHTS_LENGTH = -3;
    final Integer ERROR_WEIGHTS_VALUE = -4;
    final Integer ERROR_THRESHOLD_VALUE = -5;


    class ErrorValue {
        boolean isError;
        int errorCode = Integer.MIN_VALUE;

        void setError(boolean isError, int errorCode) {
            this.isError = isError;
            this.errorCode = errorCode;
        }
    }

    public int solution(String[] grades, int[] weights, int threshold) {
        int answer = -1234567890;
        // 과목별 성적 : grades, 과목 가중치 weight, 졸업기준 threshold
        Map<String, Integer> gradeMap = new HashMap<String, Integer>(){{
            put("A+", 10);  put("A0", 9); put("B+", 8); put("B0", 7);
            put("C+", 6); put("C0", 5); put("D+", 4); put("D0", 3); put("F", 0);
        }};

        ErrorValue error = checkError(grades, weights, threshold, gradeMap);
        if (error.isError) {
            prt("에러 발생!! Error Code : " + error.errorCode);
            return error.errorCode;
        }


        answer = 0;
        for (int grIdx=0; grIdx < grades.length; grIdx++) {
            answer += gradeMap.get(grades[grIdx]) * weights[grIdx];
        }
        answer -= threshold;

        return answer;
    }

    ErrorValue checkError(String[] grades, int[] weights, int threshold, Map<String, Integer> gradeMap) {
        ErrorValue errorValue = new ErrorValue();
        int errorCode = 0;
        if (!(grades.length >= 1 && grades.length <= 1000))
            errorValue.setError(true, ERROR_GRADES_LENGTH);
        for (String grade : grades) {
            if (!gradeMap.containsKey(grade))
                errorValue.setError(true, ERROR_GRADES_KEY);
        }

        if (weights.length != grades.length) {
            errorValue.setError(true, ERROR_WEIGHTS_LENGTH);
        }
        for (int weight: weights) {
            if (!(weight >=1 && weight <= 1000))
                errorValue.setError(true, ERROR_WEIGHTS_VALUE);
        }

        if (!(threshold >= 1 && threshold <= 20000000))
            errorValue.setError(true, ERROR_THRESHOLD_VALUE);

        return errorValue;
    }


    void run() {
//        Test 값
/*    grades	         weights	 threshold	result
   ["A+","D+","F","C0"]	 [2,5,10,3]	  50	     5
   ["B+","A0","C+"]	     [6,7,8]	  200	    -41
*/
        final String[] testGrades1 = {"A+","D+","F","C0"};
        final int[] testWeights1 = {2,5,10,3};
        final int testThreshold1 = 50;
        final int testExpectedResult1 = 5;

        final String[] testGrades2 = {"B+","A0","C+"};
        final int[] testWeights2 = {6,7,8};
        final int testThreshold2 = 200;
        final int testExpectedResult2 = -41;


        final String[] grades = testGrades2;
        final int[] weights = testWeights2;
        final int threshold = testThreshold2;
        final int expected = testExpectedResult2;

        prt("우아한테크코스-프로그래머스 코딩테스트 1번 문제 : '과목별로 받은 성적'");
        int actual = solution(grades, weights, threshold);
        if ( expected == actual)
            prt("Test 성공");
        else
            prt("Test 실패");
        prt("result : " + actual);
    }

    void prt(String msg) {
        System.out.println(msg);
    }

}

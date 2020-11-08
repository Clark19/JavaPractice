
public class Solution3 {

    private final int BASIC_STAKE = 100;

    public int solution(int money, String[] expected, String[] actual) {
        int answer = Integer.MIN_VALUE;

        //0. 예외처리(제한사항)
        if (checkError(money, expected, actual))
            return answer;

        //1. Win or Lose => plus or minus money.
        int stake = BASIC_STAKE; //  판돈
        for (int i=0; money > 0 && i < actual.length; i++) {

            if (expected[i].equals(actual[i])) { //win : get money
                money += stake;
                if (money >= BASIC_STAKE)
                    stake = BASIC_STAKE;
                else
                    stake = money;
            } else { //lose: lose monney
                money -= stake;
                if (money >= stake * 2)
                    stake *= 2;
                else
                    stake = money;
            }
        }
        answer = money;

        return answer;
    }

    void prt(String msg) {
        System.out.println(msg);
    }


    private boolean checkError(int money, String[] expected, String[] actual) {
        boolean isError = false;

        if (!(money >= 1000 && money <= 100000)) {
            prt("Error : money amount");
            isError = true;
        }
        if (expected.length != actual.length) {
            prt("Error : exp/actual length is not equal ");
            isError = true;
        }
        if (!(expected.length >=1 && expected.length <=10000) || !(actual.length >=1 && actual.length <=10000)) {
            prt("Error : exp/actual length range");
            isError = true;
        }
        for (int i=0; i < expected.length; i++) {
            if (!(actual[i].equals("H") || actual[i].equals("T")) || !(expected[i].equals("H") || expected[i].equals("T")) ) {
                prt("Error : actual/exp is not H or T");
                isError = true;
            }
        }

        return isError;
    }

    private class TestValue {
        private int money;
        private String[] expected;
        private String[] actual;
        private int expectedResult;

        TestValue(int money, String[] expected, String[] actual, int expectedResult) {
            this.money = money;
            this.expected = expected;
            this.actual = actual;
            this.expectedResult = expectedResult;
        }

        public TestValue(TestValue tv) {
            this.money = tv.money;
            this.expected = tv.expected;
            this.actual = tv.actual;
            this.expectedResult = tv.expectedResult;
        }
    }

    void run() {
/*
입출력 예
money	expected	                        actual	                                result
1000	['H', 'T', 'H', 'T', 'H', 'T', 'H']	 ['T', 'T', 'H', 'H', 'T', 'T', 'H']	1400
1200	['T', 'T', 'H', 'H', 'H']	         ['H', 'H', 'T', 'H', 'T']	            900
1500	['H', 'H', 'H', 'T', 'H']	         ['T', 'T', 'T', 'H', 'T']	            0
 */
        TestValue tv1 = new TestValue(1000, new String[]{"H", "T", "H", "T", "H", "T", "H"}
                , new String[]{"T", "T", "H", "H", "T", "T", "H"}, 1400 );
        TestValue tv2 = new TestValue(1200, new String[]{"T", "T", "H", "H", "H"}
            , new String[]{"H", "H", "T", "H", "T"} ,900);
        TestValue tv3 = new TestValue(1500, new String[]{"H", "H", "H", "T", "H"}
                , new String[]{"T", "T", "T", "H", "T"},0);
//        List<TestValue> testValues = new ArrayList<>() { {add(tv1); add(tv2); add(tv3);} };
        TestValue[] testValues = {tv1, tv2, tv3};


        prt("우아한테크코스-프로그래머스 코딩테스트 3번 문제 : '동전뒤집기게임(마틴게일 베팅법)'");
        int actualResult = Integer.MIN_VALUE;
        int testCnt = 1;
        for (TestValue testValue : testValues) {
            actualResult = solution(testValue.money, testValue.expected, testValue.actual);
            if (testValue.expectedResult == actualResult)
                prt("Test" + testCnt++ +" 성공");
            else
                prt("Test" + testCnt++ +" 실패");
            prt("result : " + actualResult + "\n");
        }

    }


}

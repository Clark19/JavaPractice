public class Solution3 {

    private final int BASIC_STAKE = 100;

    public int solution(int money, String[] expected, String[] actual) {
        int answer = -1;

        //0. 예외처리(제한사항)
        if (!(money >= 1000 && money <= 100000))
            prt("Error : money amount");
        if (expected.length != actual.length)
            prt("Error : exp/actual length is not equal ");
        if (!(expected.length >=1 && expected.length <=10000) || !(actual.length >=1 && actual.length <=10000))
            prt("Error : exp/actual length range");
        for (int i=0; i < expected.length; i++) {
            if (!(actual[i].equals("H") || actual[i].equals("T")) || !(expected[i].equals("H") || expected[i].equals("T")) )
                prt("Error : actual/exp is not H or T");
        }

        //1.
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
//        100
        }
        answer = money;

        //2.
        return answer;
    }

    void run() {
/*
입출력 예
money	expected	                        actual	                                result
1000	['H', 'T', 'H', 'T', 'H', 'T', 'H']	 ['T', 'T', 'H', 'H', 'T', 'T', 'H']	1400
1200	['T', 'T', 'H', 'H', 'H']	         ['H', 'H', 'T', 'H', 'T']	            900
1500	['H', 'H', 'H', 'T', 'H']	         ['T', 'T', 'T', 'H', 'T']	            0
 */
        //테스트 값
// 테스트값       개선 할 점 생각해볼것
        final int money1 = 1000;
        final String[] expected1 = {"H", "T", "H", "T", "H", "T", "H"};
        final String[] actual1 = {"T", "T", "H", "H", "T", "T", "H"};
        final int expectedResult1 = 1400;

        final int money2 = 1200;
        final String[] expected2 = {"T", "T", "H", "H", "H"};
        final String[] actual2 = {"H", "H", "T", "H", "T"};
        final int expectedResult2 = 900;

        final int money3 = 1500;
        final String[] expected3 = {"H", "H", "H", "T", "H"};
        final String[] actual3 = {"T", "T", "T", "H", "T"};
        final int expectedResult3 = 0;

        //테스트 값 변경하면서 대입하는거 개선할 방법 생각해 볼 것.
        final int money = money1;
        final String[] expected = expected1;
        final String[] actual = actual1;
        final int expectedResult = expectedResult1;


        prt("우아한테크코스-프로그래머스 코딩테스트 3번 문제 : '동전뒤집기게임(마틴게일 베팅법)'");
        int actualResult = solution(money, expected, actual);
        if ( expectedResult == actualResult)
            prt("Test 성공");
        else
            prt("Test 실패");
        prt("result : " + actualResult);

    }

    private void prt(String msg) {
        System.out.println(msg);
    }

}

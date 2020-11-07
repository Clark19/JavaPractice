import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// 프로그래머스 알고리즘 레벨1 문제 : '크레인 인형뽑기 게임'
// https://programmers.co.kr/learn/courses/30/lessons/64061#
public class Solution01 {
    int[][] testInputValues1 = { {1,1}, {2,2}, {1,2} }; // =>{2,1}

    public int solution(int[][] board, int[] moves) {
        // Basket에 같은 모양 인형 두개가 인접해서 쌓이면 사라진다(vanishedDollCnt++)
        List<Integer> basket = new ArrayList<Integer>();
        int vanishedDollCnt = 0;
        if ( !(moves.length >= 1 && moves.length <= 1000) )
            return -1;

//        1.put all doll into baseket
//        2. watch or print basket
//        3. verify that whether expectedResultvanishedDollCnt is same with 연속된 인형 개수들 in basket.

        int doll = 0;
        int preDollInBasket = 0;
        for (int col: moves ) {
            if (col<1 || col > board[0].length)
                return -1;
            col--;
            for (int row=0; row<board.length; row++ ) {
                // 보드의 해당 컬럼(moves의 해당 값)에서 최고 윗칸에 있는 값 탐색
                if ( 1 <= board[row][col] && board[row][col] <= 100) {
                    doll = board[row][col];
                    if (basket.size() > 0)
                        preDollInBasket = basket.get(basket.size()-1);

                    if (preDollInBasket != doll) {
                        basket.add(doll);
                    } else if (preDollInBasket == doll){
                        basket.remove(basket.size()-1);
                        vanishedDollCnt += 2;
                    }

                    // 보드의 해당 열,컬럼의 값을 뽑아 냈으므로 0으로 표기해서 제거 표기.
                    board[row][col] = 0;
                    break; // 해당 컬럼 탐색 탈출, moves에서 새 값(크레인 위치 값) 뽑아 다른 컬럼에서 작업하기 위해.
                }
            }
        }

        prt("[최종] 인형 담는 바구니 상황 : ");
        Collections.reverse(basket);
        for (int basketDoll : basket)
            prt("|"+basketDoll+"|");


        return vanishedDollCnt;
    }

//    바구니 구현을 Stack 이용한 방식. 난 List 이용함. 다른 사람 풀이
    public int solutionStack(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][move - 1] != 0) {
                    if (stack.isEmpty()) {
                        stack.push(board[j][move - 1]);
                        board[j][move - 1] = 0;
                        break;
                    }
                    if (board[j][move - 1] == stack.peek()) {
                        stack.pop();
                        answer += 2;
                    } else
                        stack.push(board[j][move - 1]);
                    board[j][move - 1] = 0;
                    break;
                }
            }
        }
        return answer;
    }


    void run() {
        prt("프로그래머스 알고리즘 레벨1 문제 : '크레인 인형뽑기 게임");
        int res = solutionStack(TestInputValue.board2, TestInputValue.moves);
        prt("사라진 인형 개수 : " + res);
    }

    void prt(String msg) {
        System.out.println(msg);
    }
}

class TestInputValue {
    static final int[][] board = new int[][] { //정사각 격자: 5x5~30x30, 아래칸부터 쌓임. 0:빈칸, 1~100:인형종류
            {0,0,0,0,0}, {0,0,1,0,3}, {0,2,5,0,1}, {5,2,4,4,2}, {3,5,1,3,1}
    };
    static final int[] moves = new int[] {1,5,3,5,1,2,1,4}; // 1<= size <=1000, 1 <= value <= board의 가로 크기 이하
    static final int expectedResult = 4;


    static final int[][] board2 = { {0,0}, {3,3} };
    static final int[] moves2 = new int[] {1,2};
    static final int expectedResult2 = 2;

    static final int[][] board3 = { {0,0}, {0,0} };
    static final int[] moves3 = new int[] {1,2};
    static final int expectedResult3 = 0;
}
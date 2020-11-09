import com.sun.jdi.Value;

import java.util.*;

class Solution4 {
    public int solution(int n, int[][] board) {
        int minMoveCnt = 0;

        //0. set minValues into TreeSet(오름차순), 최소값의 rowIdx, colIdx도 할당.
        // 오름 차순으로 값 HashMap<int/str key, Integer[2]> or List or TreeSet 등 중에 넣음.
        TreeSet<ValueObjec> minValues = setMinValue(board);
        ValueObjec curValue = new ValueObjec(board[0][0], 0, 0);
        ValueObjec minValue;

        while (!isEmpty(board)) {
            //정사각형 상하좌우 연결되있음

            //1. 최소 값 찾기
            // 1-0.
            // per Step increase minValue
//            minValue.num++ per Step <= SQUARE_OF_N;
//             or  idx = 0, minValue = idx+1;
            if (minValues.isEmpty())
                break;
            minValue = minValues.pollFirst();

            //2. 최소 이동 횟수 찾기 & 리턴
            minMoveCnt += getMinMove(curValue, minValue, n);

            board[minValue.rowIdx][minValue.colIdx] = 0; // 보드 위 해당 칸 숫자 지우기
            curValue = new ValueObjec(minValue);

            System.out.printf("minMoveCnt=%d\n", minMoveCnt );
        }

        return minMoveCnt;
    }

    private int getMinMove(ValueObjec curValue, ValueObjec minValue, int n) {
        int moveCnt;
        final int ENTER_CNT = 1;
        final int stdGap = n/2 + 1;
        int rowGap = Math.abs(curValue.rowIdx - minValue.rowIdx);
        int colGap = Math.abs(curValue.colIdx - minValue.colIdx);
        if (rowGap >= stdGap) {
            if (curValue.rowIdx < minValue.rowIdx)
                rowGap = curValue.rowIdx + (n-minValue.rowIdx);
            else
                rowGap = minValue.rowIdx + (n-curValue.rowIdx);
        }
        if (colGap >= stdGap) {
            if (curValue.colIdx < minValue.colIdx)
                colGap = curValue.colIdx + (n-minValue.colIdx);
            else
                colGap = minValue.colIdx + (n-curValue.colIdx);
        }

        moveCnt = rowGap + colGap + ENTER_CNT;
        System.out.printf("curValue: %d, minValue: %d\n", curValue.num, minValue.num);
        System.out.printf("rowGap: %d, colGap: %d, +1 :  sum = %d\n", rowGap, colGap, moveCnt);

        return moveCnt;
    }

    private TreeSet<ValueObjec> setMinValue(int[][] board) {
        TreeSet<ValueObjec> minValues = new TreeSet<>(
                new Comparator<>() {
                    @Override
                    public int compare(ValueObjec o1, ValueObjec o2) {
                        return o1.num - o2.num;
                    }
                }
        );
//        TreeSet<ValueObjec> minValues = new TreeSet<>(
//                (o1, o2) -> o1.num - o2.num
//        );

        int minNum = 0;
        for (int rowIdx=0; rowIdx < board.length; rowIdx++) {
            for (int colIdx=0; colIdx < board[rowIdx].length; colIdx++) {
                minValues.add(new ValueObjec(board[rowIdx][colIdx], rowIdx, colIdx));
            }
        }
        System.out.println("TreeSet minValues: " + minValues);

        return minValues;
    }

    class ValueObjec {
        int num;
        int rowIdx;
        int colIdx;

        ValueObjec(int num, int rowIdx, int colIdx) {
            this.num = num;
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }

        public ValueObjec(ValueObjec minValue) {
            this(minValue.num, minValue.rowIdx, minValue.colIdx);
        }

        @Override
        public String toString() {
            return "{" +
                    "num=" + num +
                    ", rowIdx=" + rowIdx +
                    ", colIdx=" + colIdx +
                    '}';
        }
    }

    boolean isEmpty(int[][] board) {
        boolean bEmpty = true;
        final int N = board.length;
        final int SQUARE_OF_N = N * N;
        int[] rowNums;
        for (int row = 0; row < N; row++) {
            rowNums = board[row];
//        for (int[] rowNums: board) {
            if (!bEmpty)
                break;
            for (int cellNum: rowNums) {
                if (cellNum >= 1 && cellNum <= SQUARE_OF_N) {
                    bEmpty = false;
//                    prt("Not Empty" + Arrays.toString(board[row]));
                    break;
                }
            }
        }

        // for debug
//        if (bEmpty) {
//            for (int[] row: board)
//              prt("Empty. " + Arrays.toString(row));
//        }
        return bEmpty;
    }

    void prt(String msg) {
        System.out.println(msg);
    }

    private boolean checkError(int n, int[][] board) {
        boolean isError = false;
        if (!(n>=2 && n<=30)) {
            prt("Error : n");
            isError = true;
        }
        if (board.length != board[0].length) {
            prt("Error : board's length");
            isError = true;
        }
        final int BOARD_CELL_SIZE = board.length * board[0].length;
        for (int[] boardRow: board) {
            for (int num: boardRow) {
                if (!(num >= 1 && num <= BOARD_CELL_SIZE)) {
                    prt("Error : board cell size");
                    isError = true;
                }
                //do checkRedundantValue
                // 배열 안 값 중복 체크
            }
        }



        return isError;
    }

    private class TestValue {
        int n;
        int[][] board;
        int expectedResult;

        TestValue(int n, int[][] board, int expectedResult) {
            this.n = n;
            this.board = board;
            this.expectedResult = expectedResult;
        }

        TestValue(TestValue tv) {
            this.n = tv.n;
            this.board = tv.board;
            this.expectedResult = tv.expectedResult;
        }
    }

    void run() {
/* 입출력 예
n   board                                                           result
3   [[3, 5, 6], [9, 2, 7], [4, 1, 8]]                                22
2   [[2, 3], [4, 1]]                                                 11
4   [[11, 9, 8, 12], [2, 15, 4, 14], [1, 10, 16, 3], [13, 7, 5, 6]]  46
*/

        TestValue tv0 = new TestValue(3, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, 22);
        TestValue tv1 = new TestValue(3, new int[][]{{3, 5, 6}, {9, 2, 7}, {4, 1, 8}}, 22);
        TestValue tv2 = new TestValue(2, new int[][]{{2, 3}, {4, 1}}, 11);
        TestValue tv3 = new TestValue(4, new int[][]{{11, 9, 8, 12}, {2, 15, 4, 14}, {1, 10, 16, 3}, {13, 7, 5, 6}}, 46);
        final TestValue[] testValues = {tv1, tv2, tv3};

        prt("우아한테크코스-프로그래머스 코딩테스트 4번 문제 : 'n x n 정사각형안의 1부터 n2 까지의 숫자를 순서대로 최단 경로로 지우는 게임'");
        int actualResult = Integer.MIN_VALUE;
        int testCnt = 1;
        for (TestValue testValue : testValues) {
            actualResult = solution(testValue.n, testValue.board);
            if (testValue.expectedResult == actualResult)
                prt("Test" + testCnt++ +" 성공");
            else
                prt("Test" + testCnt++ +" 실패");
            prt("result : " + actualResult + "\n");
        }

    }

}
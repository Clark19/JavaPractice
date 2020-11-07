import java.util.ArrayList;

public class Solution00 {

    int[][] testInputValues1 = {
            {1,4}, {3,4}, {3,10}
    }; //=> {1,10}
    int[][] testInputValues2 = { {1,1}, {2,2}, {1,2} }; // =>{2,1}


    public int[] solution(int[][] v) {
        int[] answer = new int[2];

        if (v.length != 3) prt("input size error");

        ArrayList<int[]> pairPts1 = new ArrayList<>();
        ArrayList<int[]> pairPts2 = new ArrayList<>();

        int[] pt1 = v[0];
        int[] pt2 = new int[2];
        boolean isSameY = false;
        int i = 0;
        for (i=1; i< v.length; i++) {
            pt2 = v[i];

            if ( pt1[1] == pt2[1] ) { // y 좌표 값이 같은거
                isSameY = true;
                pairPts1.add(pt1);
                pairPts1.add(pt2);
            } else if ( pt1[0] == pt2[0] ) { // 또는 x좌표 값이 같은거 찾았다면
                isSameY = false;
                pairPts1.add(pt1);
                pairPts1.add(pt2);
            }

        }
        prt("pairPts1 : ");
        prt(pairPts1.get(0)[0] + ", " + pairPts1.get(0)[1]);
        prt(pairPts1.get(1)[0] + ", " + pairPts1.get(1)[1]);


        // 입력 배열중에서 남는 값 찾기.
        //  == 두 배열에서 없는 값 찾기
        for ( int[] pt : v ) {
            if (!pairPts1.contains(pt)) {
                // 그 값 pairPt2[0] 에 넣기
                // 찾은 배열만 남기거나, 새 변수에 담기
                pairPts2.add(pt);
            }
        }
        prt("\npairPts2 : ");
        prt(pairPts2.get(0)[0] + ", " + pairPts2.get(0)[1]);

//        pairPt2[1] = 계산되어 생성된 값;
        int x = 0;
        int y = 0;
        if (isSameY) {
            y = pairPts2.get(0)[1];
            if (pairPts1.get(0)[0] != pairPts2.get(0)[0]) {
                x = pairPts1.get(0)[0];
            }
            else {
                x = pairPts1.get(1)[0];
            }
        } else { // isSameX == true
            x = pairPts2.get(0)[0];
            if (pairPts1.get(0)[1] != pairPts2.get(0)[1])
                y = pairPts1.get(0)[1];
            else
                y = pairPts1.get(1)[1];
        }
        int[] calculatedPt = {x, y};
        pairPts2.add(calculatedPt);
        answer = calculatedPt;
        prt("\nresult : [" + answer[0] + ", " + answer[1] + "]");

        return answer;
    }

    public void run() {
        solution(testInputValues2);
    }

    public static void prt(String msg) {
        System.out.println(msg);
    }

}

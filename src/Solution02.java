import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

// 프로그래머스 알고리즘 레벨1 문제 : '두 개 뽑아서 더하기'
// https://programmers.co.kr/learn/courses/30/lessons/68644
public class Solution02 {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        //중복제거 : set 이용. 정렬은 TreeSet 이용(오름차순으로 정렬 저장함)
        Set<Integer> sum = new TreeSet<>();

        for (int idx1=0; idx1 < numbers.length; idx1++) {
            /* 개선할 점 : idx2 를 0부터 할당하지 말고, idx2 = idx1+1 해도 됨.
            */
            for (int idx2=0; idx2 < numbers.length; idx2++) {
                if (idx1 != idx2)
                    sum.add(numbers[idx1] + numbers[idx2]);
            }
        }

        Integer[] result = new Integer[sum.size()];
        sum.toArray(result); // Set => 배열로 변환환
       answer = Arrays.stream(result).mapToInt(Integer::intValue).toArray();

        return answer;
    }

    // 다른 사람 풀이
    // https://programmers.co.kr/learn/courses/30/lessons/68644/solution_groups?language=java
    public int[] solution2(int[] numbers) {
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<numbers.length; i++) {
            for(int j=i+1; j<numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }


    void prt(String msg) {
        System.out.println(msg);
    }

    void run() {
// numbers	    result
// [2,1,3,4,1]	[2,3,4,5,6,7]
// [5,0,2,7]	[2,5,7,9,12]
        int numbers[] = {2,1,3,4,1};
        int numbers2[] = {5,0,2,7};
        prt("프로그래머스 알고리즘 레벨1 문제 : '두 개 뽑아서 더하기'");
        int[] answer = solution(numbers);
        prt("result : " + Arrays.toString(answer));
    }


}

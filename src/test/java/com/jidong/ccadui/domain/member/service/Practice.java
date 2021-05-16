package com.jidong.ccadui.domain.member.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.w3c.dom.NodeList;

public class Practice {
    private final String[] numberOf124 = {"4", "1", "2"};

    // 1. 124나라의 숫자
    public String solution_124(int n) {
        String answer = "";

        ArrayList<String> list = new ArrayList<>();

        while (n > 0) {
            int remainder = n % 3; //나머지
            n /= 3;

            // 3으로 나누어 떨어지면 새로운 규칙 적용
            if (remainder == 0) {
                n--;
            }

            answer = numberOf124[remainder] + answer;
        }

        return answer;
    }

    @Test
    void test() {
        solution_124(5);
    }

    // 다리를 지나는 트럭
    class Truck {
        int weight;
        int sec;

        public Truck(int weight) {
            this.weight = weight;
            this.sec = 1;
        }

        void move(int sec) {
            this.sec = sec++;
        }
    }


    // 2. 카카오 프렌즈 컬러링북
    // bfs로 푸는사람도 있구.. 이풀이는 재귀함수를 이용
    public int[] solution_coloring(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        // 1. picture 배열과 같은 사이즈의 배열을 만들어준다 (탐색이 끝났는지 표시해주는 역할)
        int[][] isVisited = new int[m][n];

        //행 (picture.length)
        for (int i = 0; i < m; i++) {
            //열 (picture[0].length)
            for (int j = 0; j < n; j++) {
                // 2. 반복문을 호출하여 원소의 처음부터 끝까지 재귀함수를 호출한다.
                int area = area(picture, isVisited, i, j);
                if (area > 0) {
                    numberOfArea++;
                    maxSizeOfOneArea = maxSizeOfOneArea < area ? area : maxSizeOfOneArea;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        System.out.println("numberofArea : " + numberOfArea);
        System.out.println("maxSizeOfOneArea : " + maxSizeOfOneArea);
        return answer;
    }

    public int area(int[][] picture, int[][] isVisited, int i, int j) {
        // 0일경우 무시(탐색할 필요 없음)
        if (picture[i][j] == 0) {
            return 0;
        }

        // 이미 방문했던 영역이면 방문할 필요 없음. && 방문했는지 후위연산자로 표시
        if (isVisited[i][j]++ > 0) {
            return 0;
        }

        int result = 1;
        if (i + 1 != picture.length) { // 맨마지막행이 아니면
            if (picture[i + 1][j] == picture[i][j]) {
                result += area(picture, isVisited, i + 1, j); // ???
            }
        }

        if (j + 1 != picture[0].length) { // 맨마지막열이 아니면
            if (picture[i][j + 1] == picture[i][j]) {
                result += area(picture, isVisited, i, j + 1);
            }
        }

        if (i - 1 >= 0) { // 위에 값이 있는지 탐색
            if (picture[i - 1][j] == picture[i][j]) {
                result += area(picture, isVisited, i - 1, j);
            }
        }

        if (j - 1 >= 0) { // 옆에 값이 있는지 탐색
            if (picture[i][j - 1] == picture[i][j]) {
                result += area(picture, isVisited, i, j - 1);
            }
        }

        return result;
    }


    @Test
    void test2() {

        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        solution_coloring(6, 4, picture);
    }


    //3. 문자열 압축
    // 경우의 수를 구하고 가장짧을 수 있는 경우를 도출해내야한다..
    // length / 2 부터 하나씩 감소하면서 중복될 수 있는 문자열의 개수를 탐색한다.
    // 하 개빡쳐 ㅡㅡ

    public int solution_compression(String s) {
        if (s.length() == 1) {
            return 1;
        }

        int answer = 1000;
        for (int i = 1; i <= s.length() / 2; i++) {
            String now, next = "", result = "";
            int hit = 1;
            for (int j = 0; j <= s.length() / i; j++) {
                int start = j * i;
                int end = i * (j + 1) > s.length() ? s.length() : i * (j + 1);
                now = next;
                next = s.substring(start, end);

                if (now.equals(next)) {
                    hit++;
                } else {
                    result += (processHit(hit) + now);
                    hit = 1;
                }
            }
            result += (processHit(hit) + next);
            answer = Math.min(answer, result.length());
        }
        System.out.println("answer : " + answer);
        return answer;
    }

    private static String processHit(int hit) {
        return hit > 1 ? String.valueOf(hit) : "";
    }

    @Test
    void test3() {
        solution_compression("aabbaccc");
    }

    // 4. 삼각달팽이 //하ㅏ...하기싫어.. 패스
    public int[] solution_ekfvod(int n) {
        int[] answer = {};


        return answer;
    }


    // 5. 같은 숫자는 싫어

    // 1) 효율성을 통과하지 못한 코드.
    // 앞에 숫자를 비교할때 Q 에 넣어서 마지막꺼 비교하는 식으로 했는데 이게 효율성이 떨어지나보다.
    public int[] solution_rkxdms(int[] arr) {
        int[] answer = {};

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            // 연속된 숫자 넣어주기
            if (list.isEmpty()) {
                list.add(arr[i]);
            } else {
                if (list.getLast() != arr[i]) {
                    list.add(arr[i]);
                }
            }
        }

        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    // 2) 효율성을 통과한 코드
    // 같은숫자 비교할때 임의값 설정해줘서 넣을 필요 없이 바로바로 비교해줌
    public int[] solution_rkxms2(int[] arr) {
        int[] answer = {};

        ArrayList<Integer> list = new ArrayList<>();

        int prenum = 10;
        for (int i = 0; i < arr.length; i++) {
            // 연속된 숫자 넣어주기
            if (prenum != arr[i]) {
                list.add(arr[i]);
                prenum = arr[i];
            }
        }

        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    @Test
    void test4() {
        int[] arr = {1, 1, 3, 3, 0, 1, 1};
        solution_rkxdms(arr);
    }


    // 6. 나누어떨어지는 숫자 배열
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                list.add(arr[i]);
            }
        }

        if (list.isEmpty()) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }

        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        Arrays.sort(answer);
        return answer;
    }

    // 7. 두 정수 사이의 합
    public long solution_hap(int a, int b) {
        long answer = 0;

        if (a == b) {
            return a;
        }

        if (a < b) {
            for (int i = a; i <= b; i++) {
                answer += a;
                a++;
            }
        } else {
            for (int i = b; i <= a; i++) {
                answer += b;
                b++;
            }
        }

        System.out.println("answer : " + answer);
        return answer;
    }

    @Test
    void test7() {
        solution_hap(3, 5);
    }

    // 8. 문자열 내 마음대로 정렬하기
    // 인덱스 기준 문자열로 오름차순 정렬하는 문제
    // 비교할 인덱스 기준 문자열이 같을경우 사전적정렬로 맨 처음 문자열부터 비교
    // 해시맵으로 할라했는데 넘 노가다같아지구 같을경우에 처리가 안됨..
    // https://udud0510.tistory.com/23 참고함
    public String[] solution(String[] strings, int n) {
        String[] answer = new String[strings.length];

        for (int i = 0; i < strings.length; i++) {
            answer[i] = strings[i].substring(n, n + 1) + strings[i]; // u + sun
        }

        Arrays.sort(answer);

        for (int i = 0; i < strings.length; i++) {
            answer[i] = answer[i].substring(1);
        }

        return answer;
    }


    // HashMap Value로 내림차순 정렬
    public static List sortByValue(final Map map) {

        List<String> list = new ArrayList();
        list.addAll(map.keySet());

        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                return ((Comparable) v2).compareTo(v1);
            }

        });

        Collections.reverse(list);

        return list;

    }

    @Test
    void test8() {
        String[] arr = {"sun", "bed", "car"};
        solution(arr, 1);
    }

    // 9. 문자열 내 p와 y의 개수
    boolean solution_pdhky(String s) {
        boolean answer = true;

        int numberOfP = 0;
        int numberOfY = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'p' || s.charAt(i) == 'P') {
                numberOfP++;
            }
            if (s.charAt(i) == 'y' || s.charAt(i) == 'Y') {
                numberOfY++;
            }
        }

        if (numberOfP + numberOfY == 0) {
            answer = true;
        }

        if (numberOfP == numberOfY) {
            answer = true;
        } else {
            answer = false;
        }

        return answer;
    }

    // 10. 폰켓몬
    // n/2 마리를 가질 수 있음. 그 모든 경우의 수
    public int solution(int[] nums) {
        int answer = 0;

        ArrayList<Integer> list = new ArrayList<>();

        Arrays.sort(nums);
        int prenum = nums[0];
        list.add(prenum);

        for (int i = 0; i < nums.length; i++) {
            if (prenum != nums[i]) {
                list.add(nums[i]);
                prenum = nums[i];
            }
        }

        if (list.size() > nums.length / 2) {
            return nums.length / 2;
        }

        answer = list.size();
        return answer;
    }

    @Test
    void test10() {
        int[] nums = {3, 3, 3, 2, 2, 2};
        solution(nums);
    }

    // 11. 문자열 내림차순으로 배치하기
    public String solution_answkduf(String s) {
        String answer = "";

        String[] charArr = new String[s.length()];

        for (int i = 0; i < s.length(); i++) {
            charArr[i] = Character.toString(s.charAt(i));
        }

        // 내림차순 정렬
        Arrays.sort(charArr, Comparator.reverseOrder());

        for (int i = 0; i < charArr.length; i++) {
            answer += charArr[i];
        }

        return answer;
    }

    // 12. 문자열을 정수로 바꾸기
    public int solution_answkdufwjdtn(String s) {
        int answer = 0;

        if (s.startsWith("-")) {
            return Integer.parseInt(s);
        }

        return answer;
    }


    // 13. 주식가격
    public int[] solution_stockPrice(int[] prices) {
        int[] answer = {};

        List<Integer> list = new ArrayList<>();

        //가격떨어지지 않은 시간 리스트에 넣기
        int index = 1;
        for (int p : prices) {
            int second = 0; // 시간


            if (index == prices.length) {
                list.add(0);
            } else {
                for (int i = index; i < prices.length; i++) {
                    if (p <= prices[i]) {
                        second++;
                    } else {
                        second++;
                        break;
                    }
                }
                list.add(second);
            }

            index++;
        }

        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    @Test
    void test13() {

        int[] pr = {1, 2, 3, 2, 3};
        solution_stockPrice(pr);
    }


    // 14. 스킬트리
    // 내가 푼거 왜틀린겨 모르곘다
    public int solution_skillTree(String skill, String[] skill_trees) {
        int answer = 0;

        for (int i = 0; i < skill_trees.length; i++) {
            if (check(skill, skill_trees[i])) {
                answer++;
            }
        }

        return answer;
    }

    private boolean check(String skill, String skillTree) {
        int preIndex = 0;
        int currentIndex = 0;

        for (int i = 0; i < skill.length(); i++) {
            String firstString = Character.toString(skill.charAt(0));
            String currentString = Character.toString(skill.charAt(i));

            // 맨처음값이 없으면 검증 필요 없음
            if (skillTree.indexOf(firstString) == -1) {
                return false;
            }

            currentIndex = skillTree.indexOf(currentString);
            if (currentIndex != -1 && preIndex > currentIndex) {
                return false;
            }
            preIndex = currentIndex;
        }

        return true;
    }


    public int solution_skillTree2(String skill, String[] skill_trees) {
        int answer = skill_trees.length;
        int beforeIdx = 0;
        int currentIdx = 0;

        for (int i = 0; i < skill_trees.length; i++) {
            beforeIdx = skill_trees[i].indexOf(skill.charAt(0));

            // 문자열 그전의 인덱스와 그 후 인덱스를 비교
            for (int j = 1; j < skill.length(); j++) {
                currentIdx = skill_trees[i].indexOf(skill.charAt(j));

                if ((beforeIdx > currentIdx && currentIdx != -1) // curIndex값이 있을때, 앞이 먼저나오면 false
                        || beforeIdx == -1 && currentIdx != -1) { // index값이 모두 없는 경우 false
                    answer--;
                    break;
                }

                beforeIdx = currentIdx;
            }
        }

        return answer;
    }


    @Test
    void test14() {
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        solution_skillTree2("CBD", skill_trees);


        System.out.println(skill_trees[2].indexOf("D"));
    }

    // 15. 괄호 변환
    // ( ) 개수가 같으면 : 균형잡힌 괄호 문자열
    // (()) 올바른 괄호 문자열
    // ex)
    // ()))((()
    // -> u : () ()

    // 포기 뭔 개소리삼
    public String solution(String p) {
        String answer = "";

        return answer;
    }

    // 16. H-Index
    // 문제 이해가 안가 하
    // HIndex 찾아보고 이해가 갔다
    public int solution_H_Index(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        for (int i = 0; i < citations.length; i++) {
            int index = citations.length - i;

            System.out.println(citations[i] + ", " + index);
            if (citations[i] >= index) {
                answer = index;
                break;
            }
        }

        return answer;
    }


    @Test
    void test16() {
        int[] citations = {3, 0, 6, 1, 5};
        solution_H_Index(citations);
    }

    // 17. 조이스틱
    // AAA -> 다 못품 예외케이스가 있을텐데.... 모르겠따 생각하기 싫다..^^
    public int solution_joystick(String name) {
        int answer = 0;

        for (int i = 0; i < name.length(); i++) {
//            첫번째경우에 다음숫자가 A일때 뒤로 가는게 이득
//            if(i==0 && "A".equals(name.charAt(i+1)) && )

            if ("A".charAt(0) != name.charAt(i)) {
                if ((int) name.charAt(i) <= 78) {
                    answer += (int) name.charAt(i) - (int) "A".charAt(0);
                } else {
                    answer += (int) "Z".charAt(0) - (int) name.charAt(i) + 1;
                }
            } else {
                answer--;
            }

            if (i != name.length() - 1) {
                answer++;
            }


        }
        System.out.println(answer);

        return answer;
    }

    @Test
    void test17() {
        solution_joystick("JAN");
    }


    // 18. 큰 수 만들기
    // "1924" -> 1249 -> 49
    // "1231234" -> 1122334 -> 2334 -> 4x3x2x1
    // -> 못품... 다시 풀기 스택으로 하는게 훨쉽다
    public String solution(String number, int k) {
        String answer = "";

        char[] numberChar = new char[number.length()];
        for (int i = 0; i < number.length(); i++) {
            numberChar[i] = number.charAt(i);
        }

        // 오름차순 정렬
        Arrays.sort(numberChar);

        // 제거할 수 제거하고 리스트에 넣어줌
        List<String> list = new ArrayList<>();
        for (int i = k; i < numberChar.length; i++) {
            list.add(Character.toString(numberChar[i]));
        }

        StringBuilder sb = new StringBuilder();


        int remainNumber = list.size();
        int maxIndex = 0;
        while (remainNumber > 0) {
            char maxChar = '0';
            for (int i = maxIndex; i < list.size() && maxChar != '9'; i++) {
                if (list.get(i).charAt(0) > maxChar) {
                    maxChar = list.get(i).charAt(0);
                    list.remove(i);
                    maxIndex = i;
                }
            }

            sb.append(maxChar);
            remainNumber--;
        }

        answer = sb.toString();
        return answer;
    }


    @Test
    void test18() {

        solution("1924", 2);

    }


    //카카오커머스 1번
    //  [4, 5, 3, 2, 1]

    //  [2, 5, 3, 4, 1]
    //  [2, 4, 3, 5, 1]
    // .. 3번쨰는 못받음 원하는상품 못받는 사람 1
    //  [2, 4, 4, 5, 1]
    public int solution(int[] gift_cards, int[] wants) {
        int answer = 0;

        for (int i = 0; i < gift_cards.length; i++) {
            for (int j = 0; j < wants.length; j++) {
                if (wants[i] == gift_cards[j] && gift_cards[i] != wants[i]) {
                    int temp = gift_cards[i];
                    gift_cards[i] = gift_cards[j];
                    gift_cards[j] = temp;
                }
            }
        }

        // 못받는 사람
        for (int i = 0; i < gift_cards.length; i++) {
            if (gift_cards[i] != wants[i]) {
                answer++;
            }
        }

        return answer;

    }

    @Test
    void test19() {

        int[] gi = {4, 5, 3, 2, 1};
        int[] wa = {2, 4, 4, 5, 1};
        solution(gi, wa);

    }


    //카카오커머스 2번
    //0: [1, 0, 0 ],  -> 0번부품 필요
    //1: [1, 1, 0],   -> 0번부품, 1번부품 필요
    //2: [1, 1, 0],   -> 0번   , 1번
    //3: [1, 0, 1],   -> 0번   ,     2번
    //4: [1, 1, 0],   -> 0번   , 1번
    //5: [0, 1, 1],  ->       , 1번 2 번
    public int solution(int[][] needs, int r) {
        int answer = 0;

        // 1. 어떤 부품을 처리하는 로봇을 구매할지 결정
        int[] arr = new int[needs[0].length];
        for (int i = 0; i < needs[0].length; i++) { //행
            int maxCount = 0;

            for (int j = 0; j < needs.length; j++) { //열
                if (needs[j][i] == 1) {
                    maxCount++;
                }
            }
            arr[i] = maxCount; // 5, 4, 2
        }

        String[] robotArr = new String[r]; // 0, 1
        int number = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;

            if (arr[i] >= arr[i + 1]) {
                robotArr[number] = Integer.toString(index);
            }
            number++;
        }

        // 2. 구매한 로봇으로 만들 수 있는 부품 반환
        for (int i = 0; i < needs.length; i++) { //행
            for (int j = 0; j < needs[0].length; j++) { //열
                if (!Arrays.asList(robotArr).contains(Integer.toString(j))) {
                    if (needs[i][j] == 1) {
                        answer--;
                        break;
                    }
                }

            }

            answer++;

        }


        return answer;
    }

    @Test
    void test20() {
        int[][] needs = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 1, 1}};

        solution(needs, 2);
    }

    // 21. leatCode myCircularQueue
    class MyCircularQueue {

        int[] queue; // 배열로 원형큐를 구현한다
        int head;
        int tail;
        int size; // empty체크를 위한 값

        public MyCircularQueue(int k) {
            queue = new int[k];
            head = 0;
            tail = -1;
            size = 0;
        }

        // 삽입
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }

            tail = (tail + 1) % queue.length;
            queue[tail] = value;
            size++;
            return true;
        }

        // 삭제
        public boolean deQueue() {

            if (isEmpty()) {
                return false;
            }

            head = (++head) % queue.length;
            size--;
            return true;
        }

        // 맨앞에 있는 원소 거내기
        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return queue[head];
        }

        // 맨뒤에 있는 원소 꺼내기
        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return queue[tail];
        }

        // 공백 상태 확인
        public boolean isEmpty() {
            return size == 0;
        }

        // 포화상태 확인
        public boolean isFull() {
            return size == queue.length;
        }
    }

    @Test
    void test21() {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        myCircularQueue.enQueue(1); // return True
        myCircularQueue.enQueue(2); // return True
        myCircularQueue.enQueue(3); // return True
        myCircularQueue.enQueue(4); // return False
        myCircularQueue.Rear();     // return 3
        myCircularQueue.isFull();   // return True
        myCircularQueue.deQueue();  // return True
        myCircularQueue.enQueue(4); // return True
        myCircularQueue.Rear();     // return 4
    }

    //22. Number of Islands
    public int numIslands_DFS(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int numberOfIsland = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    markCurrentIsland_DFS(grid, i, j, n, m);
                    numberOfIsland += 1;
                }
            }
        }

        return numberOfIsland;
    }

    // DFS 재귀
    void markCurrentIsland_DFS(char[][] grid, int i, int j, int n, int m) {
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '2';

        markCurrentIsland_DFS(grid, i + 1, j, n, m); // 아래
        markCurrentIsland_DFS(grid, i - 1, j, n, m); // 위
        markCurrentIsland_DFS(grid, i, j + 1, n, m); // 우
        markCurrentIsland_DFS(grid, i, j - 1, n, m); // 좌
    }


    @Test
    void test22() {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        numIslands_BFS(grid);
    }

    //22. Number of Islands
    public int numIslands_BFS(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int numberOfIsland = 0;

        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    queue.offer(new int[] {i, j});
                    visited[i][j] = true;

                    markCurrentIsland_BFS(grid, n, m, queue, visited);
                    numberOfIsland += 1;
                }
            }
        }

        System.out.println(numberOfIsland);
        return numberOfIsland;
    }

    int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    void markCurrentIsland_BFS(char[][] grid, int n, int m, Queue<int[]> queue, boolean[][] visited) {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int[] dirs : dirs) {
                int x = current[0] + dirs[0];
                int y = current[1] + dirs[1];

                if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || grid[x][y] == '0') {
                    continue;
                }

                visited[x][y] = true;
                queue.offer(new int[] {x, y});
            }
        }
    }

    // ** BFS
    // BFS란 특정위치 기준으로 인접한 노드를 모두 방문하며 한 번 방문했던 노드는
    // 방문이력을 저장해가면서 다음노드, 다음노드로 넘어가 전체를 검색하는 방법입니다.

    // 최단거리 검색 예제
    // (1 : 이동가능 ,0 : 이동불가)
    int N = 0; //행
    int M = 0; //열
    int[][] arr;
    boolean[][] visited;


    @Test
    void test23() {
        N = 4;
        M = 6;
        visited = new boolean[N][M];

        arr = new int[][] {{1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1}};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = false;
            }
        }

        visited[0][0] = true;
        BFS(0, 0);
    }

    // queue에 넣으면 넣은 순서대로(선입선출) 다시 검색이 시작됨. 인접노드를 모두 queue에 담은 후에 다시 처음 넣었던 노드를 중심으로 인접노드를 다시 검색하게 됨.
    void BFS(int start, int end) {

        try {

            Queue<Node> q = new LinkedList<>();
            //최초 queue 삽입
            q.add(new Node(start, end, 1));

            while (!q.isEmpty()) {

                Node node = q.poll();
                visited[node.x][node.y] = true;
                System.out.println(node.x + "," + node.y);
                // 상하좌우 이동 가능여부를 확인해본다.
                // "1"이 있는지 확인

                //좌
                if (node.y - 1 >= 0 && node.y - 1 < M && arr[node.x][node.y - 1] == 1 && visited[node.x][node.y - 1] == false) {
                    q.add(new Node(node.x, node.y - 1, node.depth + 1));
                }

                //우
                if (node.y + 1 >= 0 && node.y + 1 < M && arr[node.x][node.y + 1] == 1 && visited[node.x][node.y + 1] == false) {
                    q.add(new Node(node.x, node.y + 1, node.depth + 1));
                }

                //위
                if (node.x - 1 >= 0 && node.x - 1 < N && arr[node.x - 1][node.y] == 1 && visited[node.x - 1][node.y] == false) {
                    q.add(new Node(node.x - 1, node.y, node.depth + 1));
                }

                //아래
                if (node.x + 1 >= 0 && node.x + 1 < N && arr[node.x + 1][node.y] == 1 && visited[node.x + 1][node.y] == false) {
                    q.add(new Node(node.x + 1, node.y, node.depth + 1));
                }

                if (visited[N - 1][M - 1]) {
                    System.out.println("완료");
                    System.out.println(node.depth);
                    break;
                }

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    class Node {
        int x;
        int y;
        int depth;

        Node(int _x, int _y, int _depth) {
            this.x = _x;
            this.y = _y;
            this.depth = _depth;
        }
    }

    // leetCode OpenLock
    // BFS로 풀어야할것같음..
    // 1. 특정조건의 숫자(deadends)가 나오면 탐색을 끝내야함.
    // 2. 만족하는 숫자가 나올때까지 반복
    // -> 다시 풀기.. 나중에 ..
    public int openLock(String[] deadends, String target) {


        return 0;
    }


    int cnt = 0; // 최소 카운트 숫자

    int openLock_BFS(String[] deadList, String target) {
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadList));

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");

        while (!queue.isEmpty()) {
            int size = queue.size();

            // queue 사이즈만큼 반복
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                // 4 digit 검사
                for (int pos = 0; pos < 4; pos++) {
                    char[] chars = curr.toCharArray();
                    // char 아스키코드 계산법.. 각 디짓에 +1 하는 경우
                    chars[pos] = (char) ((chars[pos] - '0' + 1 + 10) % 10 + '0');
                    String nextString = String.valueOf(chars);
                    if (nextString.equals(target)) {
                        return cnt + 1;
                    }

                    if (!deadSet.contains(nextString)) {
                        queue.offer(nextString);
                        deadSet.add(nextString); // 다음경우의수를 제한숫자에 넣음으로 중복체크하지 않도록한다.
                    }

                    // char 아스키코드 계산법.. 각 디짓에 -1 하는 경우 ex) 0 -> 9
                    // 왜 -2임? 졸라 이해안감.
                    chars[pos] = (char) ((chars[pos] - '0' - 1 + 10) % 10 + '0');

                    nextString = String.valueOf(chars);
                    if (nextString.equals(target)) {
                        return cnt + 1;
                    }
                    if (!deadSet.contains(nextString)) {
                        queue.offer(nextString);
                        deadSet.add(nextString);
                    }
                }
            }
            cnt++;
        }

        return -1;
    }

    @Test
    void test24() {

    }

    // leetCode XOR Operation in an Array
    public int xorOperation(int n, int start) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = start + 2 * i;
        }

        int xorResult = 0;
        for (int i = 0; i < n; i++) {
            xorResult ^= result[i];
        }

        return xorResult;
    }

    @Test
    void test25() {
        xorOperation(1, 7);
    }


    // leetCode 914. X of a Kind in a Deck of Cards
    //Input: deck = [1,2,3,4,4,3,2,1]
    //Output: true
    //Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
    public boolean hasGroupsSizeX(int[] deck) {

        int[] count = new int[1000];
        for (int c : deck) {
            count[c]++; // 각 동일한 원소의 개수
        }

        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (count[i] > 0) {
                values.add(count[i]);
            }
        }

        // group size는 2이상 이어야함.
        search:
        for (int i = 2; i <= deck.length; ++i) {
            if (deck.length % i == 0) { // 전체 개수가 group size와 맞아떨어질 때
                for (int v : values) {
                    if (v % i != 0)  // group size 검사, 나누어떨어져야함.
                    {
                        continue search;
                    }
                }
                return true;
            }
        }

        return false;
    }

    @Test
    void test26() {
        int[] deck = {0, 0, 0, 0, 0, 1, 1, 2, 3, 4};
        System.out.println(hasGroupsSizeX(deck));
    }

    // leetCode 290. Word Pattern
    //Input: pattern = "abba", s = "dog cat cat dog"
    //Output: true
    // a : dog
    // b : cat
    // b : cat
    // a : dog
    public boolean wordPattern(String pattern, String s) {

        String[] sArray = s.split(" ");
        HashMap<Character, String> map = new HashMap<>();

        // 검사할 패턴과 문자열의 갯수가 일치해야함.
        if (pattern.length() != sArray.length) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {

            // ex) a라는 키를 가졌는지 검사
            if (map.containsKey(pattern.charAt(i))) {
                String value = map.get(pattern.charAt(i));
                // ex) a라는 키와 같은 순서의 value의 동일한지 검사
                if (!value.equals(sArray[i])) {
                    return false;
                }
                // ex) dog라는 value를 이미 가졌는지 검사
                // (key중복이 안되므로 같은 value인 다른 키값을 가질 수 없음)
            } else if (map.containsValue(sArray[i])) {
                return false;
            }
            map.put(pattern.charAt(i), sArray[i]);
        }

        return true;
    }


    @Test
    void test27() {
        String pattern = "aaa";
        String s = "aa aa aa aa";
        System.out.println(wordPattern(pattern, s));
    }


    // leetCode 28. Implement strStr()
    //    Input: haystack = "hello", needle = "ll"
    //    Output: 2
    public int strStr(String haystack, String needle) {

        // 비교할 문자열이 없으면 항상 0
        if ("".equals(needle)) {
            return 0;
        }

        if (!"".equals(haystack) && haystack.length() >= needle.length()) {
            for (int i = 0; i < haystack.length(); i++) {
                StringBuilder stringBuilder = new StringBuilder();
                if (haystack.charAt(i) == needle.charAt(0) && haystack.substring(i).length() > needle.length()) {
                    for (int j = 0; j < needle.length(); j++) {
                        stringBuilder.append(haystack.charAt(i + j));
                    }

                    System.out.println(stringBuilder.toString());
                    if (stringBuilder.toString().equals(needle)) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    //    "mississippi"
//    "issipi"
    @Test
    void test28() {
        String haystack = "mississippi";
        String needle = "issipi";
        System.out.println(strStr(haystack, needle));
    }

    // leetCode1346.
    // Check If N and Its Double Exist
    // Input: arr = [10,2,5,3]
    // Output: true
    public boolean checkIfExist(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j && arr[i] == arr[j] * 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfExistOn(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int a : arr) {
            // 두배의 값을 포함하거나, 짝수이며 1/2 값을 포함하거나.. ex) 7 -> 3.5 (x)
            if (set.contains(a * 2) || (a % 2 == 0 && set.contains(a / 2))) {
                return true;
            }
            set.add(a);
        }
        return false;
    }

    @Test
    void test29() {
        int[] arr = {-2, 0, 10, -19, 4, 6, -8};
        System.out.println(checkIfExist(arr));
    }

    //leetcode Maximum Ice Cream Bars
    //Input: costs = [1,3,2,4,1], coins = 7
    //Output: 4
    public int maxIceCream(int[] costs, int coins) {
        int count = 0;
        int afford = 0;

        Arrays.sort(costs);
        for (int i = 0; i < costs.length; i++) {
            if (costs[i] < coins || afford < coins) {
                afford += costs[i];
                if (afford > coins) {
                    afford -= costs[i];
                    continue;
                }
                count++;
            }
        }
        return count;
    }

    @Test
    void test30() {
        int[] costs = {27, 23, 33, 26, 46, 86, 70, 85, 89, 82, 57, 66, 42, 18, 18, 5, 46, 56, 42, 82, 52, 78, 4, 27, 96, 74, 74, 52, 2, 24, 78, 18, 42, 10, 12, 10, 80, 30, 60, 38, 32, 7, 98, 26, 18, 62, 50, 42, 15, 14, 32, 86, 93, 98, 47, 46,
                58, 42, 74, 69, 51, 53, 58, 40, 66, 46, 65, 2, 10, 82, 94, 26, 6, 78, 2, 101, 97, 16, 12, 18, 71, 5, 46, 22, 58, 12, 18, 62, 61, 51, 2, 18, 34, 12, 36, 58, 20, 12, 17, 70};
        int coins = 241;
        System.out.println(maxIceCream(costs, coins));
    }

    /**
     *
     * leetcode 2. Add Two Numbers
     *
     * Definition for singly-linked list.
     *
     *
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     *
     * NumberFormat Exception 난다 ㅠㅠ 실패
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        final Stack<Integer> stack1 = new Stack<>();
        final Stack<Integer> stack2 = new Stack<>();

        stack1.add(l1.val);
        stack2.add(l2.val);
        while (l1.next!=null) {
            stack1.add(l1.next.val);
            l1 = l1.next;
        }

        while (l2.next!=null) {
            stack2.add(l2.next.val);
            l2 = l2.next;
        }

        String sum1 = "";
        while (!stack1.isEmpty()) {
            sum1 += stack1.pop();
        }

        String sum2 = "";
        while (!stack2.isEmpty()) {
            sum2 += stack2.pop();
        }

        long finalSum = Long.parseLong(sum1) + Long.parseLong(sum2);
        String finalSumStr = finalSum + "";

        int[] arr = new int[finalSumStr.length()];

        ListNode answer = new ListNode();
        ListNode node = answer;
        for(int i=finalSumStr.length()-1; i>=0; i--) {
            arr[i] = finalSumStr.charAt(i) - '0';
            ListNode temp = new ListNode(arr[i]);
            if(i==finalSumStr.length()-1) {
                node.val = arr[i];
            } else {
                node.next = temp;
                node = node.next;
            }
        }

        return answer;
    }

    @Data
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @Test
    void test31() {
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();

        l1.setVal(2);
        l1.setNext(new ListNode(4, new ListNode(3)));

        l2.setVal(5);
        l2.setNext(new ListNode(6, new ListNode(4)));

        addTwoNumbers(l1, l2);

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }


}

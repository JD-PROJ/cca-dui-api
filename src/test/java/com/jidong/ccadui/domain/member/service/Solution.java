package com.jidong.ccadui.domain.member.service;

import static jdk.nashorn.internal.objects.Global.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class Solution {
    public void duplicateZeros(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);

            if (arr[i] == 0) {
                queue.add(0);

                arr[i] = queue.remove();
            }

        }

    }

    //Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    //Output: [1,2,2,3,5,6]
    public static void mergeByQueue(int[] nums1, int m, int[] nums2, int n) {
        Queue<Integer> queue = new LinkedList<>();
        int j = m - n;

        for (int i = 0; i < m + n; i++) {

            if (nums1[i] == 0) {
                queue.add(nums2[j]);
                j++;
            } else {
                queue.add(nums1[i]);

            }
            nums1[i] = queue.poll();
            // 오름차순으로 못바꾸겠음........

        }
    }

    //Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    //Output: [1,2,2,3,5,6]
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;

        for (int i = 0; i < m + n; i++) {
            if (nums1[i] == 0 && j < n) { // j는 num2의 순서인데 자기 크기 n 보다는 작아야한다
                nums1[i] = nums2[j];
                j++;
            }
        }
        Arrays.sort(nums1);
    }

    @Test
    void merge() {
        int[] nums1 = {0};
        int[] nums2 = {1};
        merge(nums1, 0, nums2, 1);

        System.out.println("answer : " + Arrays.toString(nums1));
    }

    //Input: nums = [3,2,2,3], val = 3
    //Output: 2, nums = [2,2]
    public int removeElement(int[] nums, int val) {
        int length = nums.length;

        for (int i = length - 1; i >= 0; i--) {
            if (nums[i] == val) {
                if (nums[length - 1] != val) {
                    nums[i] = nums[length - 1];
                } else {
                    int temp = nums[i];
                    nums[i] = nums[length - 1];
                    nums[length - 1] = temp;
                }
                length--;
            }
        }

        return length;
    }


    @Test
    void removeElement() {
        int[] nums = {3, 2, 2, 3};

        int answer = removeElement(nums, 3);
        System.out.println("lenghth : " + answer);
    }

    //Input: nums = [0,0,1,1,1,2,2,3,3,4]
    //Output: 5, nums = [0,1,2,3,4]
    public int removeDuplicates(int[] nums) {
        int length = nums.length;

        for (int i = length; i >= 0; i--) {

            if (nums[i] == nums[i - 1]) {
                nums[i] = nums[i + 1];
                length--;
            }
        }

        return 0;
    }

    //    ["leo", "kiki", "eden"]	["eden", "kiki"]
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        final HashMap<String, Integer> map = new HashMap<>();

        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        for (String c : completion) {
            map.replace(c, map.get(c) - 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                answer = entry.getKey();
            }
        }
        return answer;
    }

    @Test
    public void dd() {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};

        solution(participant, completion);
    }


    public boolean solutionPhoneBook(String[] phone_book) {
        boolean answer = true;

        //Hash이용하기

        for (int i = 0; i < phone_book.length; i++) {
            int phoneHash = phone_book[i].hashCode();
            int lengthOfPhone = phone_book[i].length();

            for (int j = i + 1; j < phone_book.length; j++) {
                if (lengthOfPhone <= phone_book[j].length()) {
                    if (phoneHash == phone_book[j].substring(0, lengthOfPhone).hashCode()) {
                        return false;
                    }
                }

                if (lengthOfPhone > phone_book[j].length()) {
                    if (phoneHash == phone_book[j].hashCode()) {
                        return false;
                    }
                }
            }


        }


        //75점짜리...ㅠㅠ
//        for (int j = 0; j < phone_book.length; j++) {
//            for (int i = j+1; i < phone_book.length; i++) {
//                    if (phone_book[i].startsWith(phone_book[j])) {
//                        return false;
//                    }
//            }
//        }


//        HashMap<String, String> hashMap = new HashMap<>();
//        for (int i=0; i<phone_book.length; i++) {
//            for(String p : phone_book) {
//                //같은값은 넣지 않는다.
//                if(!p.equals(phone_book[i])) {
//                    hashMap.put(p, phone_book[i]);
//                }
//            }
//        }
//
//        for(Map.Entry<String, String> k : hashMap.entrySet()) {
//            if(k.getKey().contains(k.getValue())) {
//                answer = false;
//            }
//
//            if(k.getValue().contains(k.getKey())) {
//                answer = false;
//            }
//        }


        return answer;
    }

    @Test
    public void cc() {
        String[] phone_book = {"12", "123", "1235", "567", "88"};
        solutionPhoneBook(phone_book);
    }


    //HASHMAP 을 이용하여 같은키값있으면 +1 해줄 수 있음
//[["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]]
    public int solutionClothes(String[][] clothes) {
        int answer = 0;

        HashMap<String, Integer> clothesMap = new HashMap<>(); // 종류 : 갯수

        for (int i = 0; i < clothes.length; i++) {
            // 종류별로 넣어주기. 같은 키 값으로 있으면 +1 해줌
            clothesMap.put(clothes[i][1], clothesMap.getOrDefault(clothes[i][1], 0) + 1);
        }

        // 경우의수 체크
        for (int values : clothesMap.values()) {
            answer *= values;
        }

        return answer;
    }


    //["classic", "pop", "classic", "classic", "pop"]
    //    [500,     600,    150,        800,    2500]
    public int[] solution_BestSong(String[] genres, int[] plays) {
        int[] answer = {};

        HashMap<String, Integer> playsPerSongMap = new HashMap<>();

        // 1. 각각 장르의 플레이수 더해주기

        int number = 0;
        for (String g : genres) {

            playsPerSongMap.put(g, playsPerSongMap.getOrDefault(g, 0) + plays[number]);
            if (number < plays.length - 1) {
                number++;
            }
        }

        // 2. 제일 큰 플레이수의 장르 가져오기
        int max = 0;
        String mostPlayedGenres = "";
        for (String m : playsPerSongMap.keySet()) {
            for (int v : playsPerSongMap.values()) {
                if (v > max) {
                    max = v;
                    mostPlayedGenres = m;
                }
            }
        }

        // 3. 곡넘버에 따른 플레이수 map에 담기
        // {0, 500} {1, 600} {2, 150} ...
        HashMap<Integer, Integer> playPerNumber = new HashMap<>();
        int numberPer = 0;
        for (int p : plays) {
            playPerNumber.put(numberPer++, p);
        }

        // 4. 곡넘버에 따른 장르 map에 담기
        // {0, classic} {1, pop} {2, classic} ...
        HashMap<Integer, String> genresPerNumber = new HashMap<>();
        numberPer = 0;
        for (String g : genres) {
            genresPerNumber.put(numberPer++, g);
        }

        // 플레이수 정렬하고.. 그 인덱스를 가지ㅣ고 장르 가져오고 ..
        //
        // 하 ....................

        System.out.println("most Played : " + mostPlayedGenres);


        return answer;
    }

    @Test
    public void ddd() {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        solution_BestSong(genres, plays);
    }


    class Truck {
        int weight;
        int move;

        public Truck(int weight) {
            this.weight = weight;
            this.move = 1;
        }

        public void moving() {
            move++;
        }
    }

    public int solution_Truck(int bridge_length, int limitWeight, int[] truck_weights) {

        Queue<Truck> waitQ = new LinkedList<>();
        Queue<Truck> moveQ = new LinkedList<>();


        for (int t : truck_weights) {
            waitQ.offer(new Truck(t));
        }

        int answer = 0; // 시간
        int currentWeight = 0;

        // waitQ와 moveQ가 빌때까지 반복
        while (!waitQ.isEmpty() || !moveQ.isEmpty()) {

            answer++;

            // 다리가 비어있으면 트럭은 다리에 진입할 수 있다.
            if (moveQ.isEmpty()) {
                Truck t = waitQ.poll();
                currentWeight += t.weight;
                moveQ.offer(t);
                continue;
            }

            // 진입 트럭이 다리를 이동한다
            for (Truck t : moveQ) {
                t.moving();
            }

            // 이동중 다리 길이를 넘어가면 (=다건너면) 그 트럭 무게를 빼준다
            if (moveQ.peek().move > bridge_length) {
                Truck t = moveQ.poll();
                currentWeight -= t.weight;
            }

            // 남은 트럭의 무게가 제한무게를 넘지 않으면 이동시킨다.
            if (!waitQ.isEmpty() && currentWeight + waitQ.peek().weight <= limitWeight) {
                Truck t = waitQ.poll();
                currentWeight += t.weight;
                moveQ.offer(t);
            }

        }
        return answer;
    }

    @Test
    void truckTest() {
        int[] truckWeight = {7, 4, 5, 6};
        solution_Truck(2, 10, truckWeight);
    }

    public int[] solution_Stock(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int sec = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] >= prices[i]) {
                    sec++;
                }
            }
            answer[i] = sec;
        }

        return answer;
    }

    @Test
    void stock() {
        int[] prices = {1, 3, 3, 2, 3};
        solution_Stock(prices);
    }

    private int answer = 0;

    public int solution_targetNumber(int[] numbers, int target) {
        this.answer = answer;
        dfs(0, 0, target, numbers);
        return answer;
    }

    private void dfs(int index, int sum, int target, int[] numbers) {
        // 합계가 target일때, 그리고 마지막 index일때 answer에 더해줌
        if (sum == target && index == numbers.length) {
            answer++;
            return;
        }

        // 탈출
        if (index >= numbers.length) {
            return;
        }

        dfs(index + 1, sum + numbers[index], target, numbers);
        dfs(index + 1, sum - numbers[index], target, numbers);
    }


    @Test
    void targetNumber() {
        int[] numbers = {1, 1, 1, 1, 1};
        solution_targetNumber(numbers, 3);
    }


    public int solutionGreed(int n, int[] lost, int[] reserve) {
        int answer = 0;

        //여벌옷이 있는 학생
        for (int i = 0; i < reserve.length; i++) {

            // 여벌옷빌려주기 끝
            if (answer >= reserve.length) {
                break;
            }

            // 도난당한 학생
            for (int j = 0; j < lost.length; j++) {
                if (lost[j] != reserve[i]) { // 여벌옷을 가지고 있는 학생이 도난당했을 시 자기옷 입는다.
                    if (lost[j] == reserve[i] - 1 || lost[j] == reserve[i] + 1) {
                        answer++;
                        break;
                    }
                }
            }
        }

        // 원래 수업을 들을 수 있는 학생
        int originStudent = n - lost.length;
        answer += originStudent;
        System.out.println("answer : " + answer);
        return answer;
    }

    @Test
    void greed() {
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};

        solutionGreed(n, lost, reserve);
    }


    public int[] solution_Supoja(int[] answers) {
        int[] supo1 = {1, 2, 3, 4, 5}; // 5번반복
        int[] supo2 = {2, 1, 2, 3, 2, 4, 2, 5}; // 8번반복
        int[] supo3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}; // 10번반복

        // 수포자삼인방의 점수
        int[] score = new int[3];

        // answers의 길이에 맞게 각자의 답안을 넣어야함.
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == supo1[i % 5]) {
                score[0]++;
            }

            if (answers[i] == supo2[i % 8]) {
                score[1]++;
            }

            if (answers[i] == supo3[i % 10]) {
                score[2]++;
            }
        }

        // 가장높은 점수를 받은 사람 찾기
        int max = Math.max(Math.max(score[0], score[1]), score[2]);
        ArrayList<Integer> winner = new ArrayList<>();
        if (max == score[0]) {
            winner.add(1);
        }
        if (max == score[1]) {
            winner.add(2);
        }
        if (max == score[2]) {
            winner.add(3);
        }


        // 배열에 넣어주기
        int[] answer = new int[winner.size()];
        for (int i = 0; i < winner.size(); i++) {
            answer[i] = winner.get(i);
        }

        return answer;
    }

    @Test
    void supoja() {
        int[] answer = {1, 2, 3, 4, 5};
        solution_Supoja(answer);
    }

    public int solution_sosu(String numbers) {
        int answer = 0;

        return answer;
    }

    //2. DFS를 이용해 구현  - 순서를 지키면서 n 개중에서 r 개를 뽑는 경우
    static void per2(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            print(output, r); //순열 출력을 위한 print 함수
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                per2(arr, output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }


    // 스택&큐 프로그래머스 기능개발
    public int[] solution_deploy(int[] progresses, int[] speeds) {

        double[] days = new double[progresses.length];
        double max = 0;
        LinkedList<Integer> arrayList = new LinkedList<>();

        // 각 일마다 걸리는 날을 구한다
        for (int i = 0; i < progresses.length; i++) {
            days[i] = Math.ceil((100 - progresses[i]) / (double) speeds[i]);

            if (days[i] > max) {
                // 일자가 최대값보다 크면 싱글 1day으로 본다.
                max = days[i];
                arrayList.add(1);
            } else {
                // 최댓값보다 작으면 마지막 일자와 set로 본다.
                int lastNumber = arrayList.getLast();
                lastNumber++;
                arrayList.removeLast();
                arrayList.offer(lastNumber);
            }
        }

        int[] answer = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            answer[i] = arrayList.get(i);
        }
        return answer;
    }


    @Test
    void dddddd() {
//        int[] pro = {95, 90, 99, 99, 80, 99};
        int[] pro = {93, 30, 55};
//        int[] spe = {1, 1, 1, 1, 1, 1};
        int[] spe = {1, 30, 5};

        solution_deploy(pro, spe);
    }

    //
    public int solution_print(int[] priorities, int location) {
        int answer = 1;
        LinkedList<Integer> priorityQ = new LinkedList<>();

        for (int p : priorities) {
            priorityQ.add(p);
        }

        //최대값구하기
        Arrays.sort(priorities);
        int max = priorities[priorities.length - 1];


        int index = 0; // 큐에서 가장 앞에 있는 숫자의 인덱스
        while (priorityQ.isEmpty()) {

            if (index == priorityQ.size()) {
                index = 0;
            }

            if (priorityQ.get(index) == max) {
                return answer;
            } else if (priorityQ.get(index) < max) {
                priorityQ.addFirst(priorityQ.get(index));
                priorityQ.removeLast();
                answer++;

            }
        }

//        for (int i = 0; i < priorities.length; i++) {
//            // 우선순위가 높은 문서가 하나라도 있으면 맨뒤로 간다
//            if (priorityQ.peek().get(i)< max) {
//                priorityQ.addLast(priorityQ.peek());
//                priorityQ.poll();
//            } else if (priorityQ.peek().get(i) == max) {
//                break;
//            }
//        }


        return answer;
    }

    public int solution_print2(int[] priorities, int location) {
        int answer = 0;

        // 각 우선순위 값을 리스트에 넣어준다
        List<Integer> list = new ArrayList<>();
        for (int p : priorities) {
            list.add(p);
        }


        Arrays.sort(priorities);
        int max = priorities[priorities.length - 1];


        int cnt = 1;
        while (!list.isEmpty()) {
            Integer j = list.get(0);

            if (j < max) {
                list.add(list.remove(0));
            } else {
                if (location == 0) {
                    return cnt;
                }
                list.remove(0);
                cnt++;
            }

            if (location > 0) {
                location--;
            } else {
                location = list.size() - 1;
            }
        }

        return answer;
    }


    @Test
    void ccccc() {
        int[] prio = {2, 1, 3, 2};
        int location = 2;
        solution_print2(prio, location);
    }

    //프로그래머스 두개 뽑아서 더하기
    public int[] solution_array(int[] numbers) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int sum = numbers[i] + numbers[j];

                if (!list.contains(sum)) {
                    list.add(sum);
                }
            }
        }
        int[] answer = new int[list.size()];

        for (int k = 0; k < list.size(); k++) {
            answer[k] = list.get(k);
        }

        Arrays.sort(answer);
        return answer;
    }

    @Test
    void asdsadsa() {
        int[] a = {2, 1, 3, 4, 1};
        solution_array(a);
    }

    // 크레인 인형뽑기
    //[[0,0,0,0,0],
    // [0,0,1,0,3],
    // [0,2,5,0,1],
    // [4,2,4,4,2],
    // [3,5,1,3,1]]
    public int solution_dollPick(int[][] board, int[] moves) {
        // 사라진 인형수
        int answer = 0;

        // 인형을 담을 공간
        ArrayList<Integer> list = new ArrayList<>();

        // 인형을 뽑을 위치
        for (int m : moves) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][m - 1] != 0) {
                    if (!list.isEmpty() && list.get(list.size() - 1) == board[j][m - 1]) {
                        //인형만나서 터지고 삭제해줌
                        list.remove(list.size() - 1);
                        answer += 2;
                    } else {
                        list.add(board[j][m - 1]);
                    }
                    board[j][m - 1] = 0;
                    break;
                }
            }
        }

        return answer;
    }

    @Test
    void casdasda() {
//        int[][] borad = {{0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}, {0, 2, 1, 0, 0}, {0, 2, 1, 0, 0}, {0, 2, 1, 0, 0}};
//        int[] moves = {1, 2, 3, 3, 2, 3, 1};
        int[][] borad = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        solution_dollPick(borad, moves);
    }


    //[[2, 5, 3],
    // [4, 4, 1],
    // [1, 7, 3]]
    // K번째수
    public int[] solution_Kth(int[] array, int[][] commands) {

        ArrayList<int[]> arrayList = new ArrayList<>();

        // array의 2번째부터 5번째까지 자르면 [5, 2, 6, 3]입니다.
        for (int j = 0; j < commands.length; j++) {

            int index = 0;

            int start = commands[j][0] - 1;
            int end = commands[j][1] - 1;
            int[] list = new int[end - start + 1]; // size 할당
            for (int i = start; i <= end; i++) {
                int temp = array[i];
                list[index] = temp;
                index++;
            }
            arrayList.add(list);
        }


        // 오름차순 정렬
        // 1에서 나온 배열을 정렬하면 [2, 3, 5, 6]입니다.
        int[] answer = new int[arrayList.size()];
        int index = 0;
        for (int[] a : arrayList) {
            Arrays.sort(a);

            // command 2의자리
            // 2에서 나온 배열의 3번째 숫자는 5입니다.
            answer[index] = a[commands[index][2] - 1];
            index++;
        }


        return answer;
    }

    @Test
    void dddaaaa() {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] comman = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        solution_Kth(array, comman);
    }


    // [1, 2, 3, 9, 10, 12]
    // 더맵게
    // 우선순위 큐 쓰기
    //  PriorityQueue (우선순위 큐)로 주어지며 자동으로 최소 힙으로 만들어진다. 힙에 관해서는 나중에 따로 설명하도록 하겠다.
    //  값을 넣는 순서에 상관없이 poll()을 하면 최솟값이 순서대로 나온다.
    public int solution_scoville(int[] scoville, int K) {
        Queue<Integer> priorityQ = new PriorityQueue<>();
        for (int s : scoville) {
            priorityQ.offer(s); // 우선순위큐에 넣는다
        }

        // 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수
        int answer = 0;

        //
        while (priorityQ.peek() < K) {
            if (priorityQ.size() == 1) {
                return -1;
            }

            priorityQ.offer(priorityQ.poll() + (priorityQ.poll() * 2));
            answer++;
        }

        return answer;
    }


    @Test
    void dddssaaaa() {
        int[] scoville = {1, 2, 3, 9, 10, 12};

        solution_scoville(scoville, 110000000);
    }

    // 멀쩡한 사각형
    // - W*H의 사각형을 대각선으로 잘랐을때 망가지게되는 사각형을 구하는 최종 공식 : [ W+H-최대공약수 ]
    // 주입식 교육
    public long solution_normalSquare(int w, int h) {
        long originSquare = (long) w * h;
        long gcd = getGCD(Math.max(w, h), Math.min(w, h)); //최대공약수

        long answer = originSquare - ((long) w + (long) h - gcd);
        return answer;
    }

    public long getGCD(long a, long b) {
        while (b > 0) {
            long tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }

    // 프로그래머스 메뉴 리뉴얼
    // 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성하려고 합니다.
    // 1번 손님	A, B, C, F, G
    //2번 손님	A, C
    //3번 손님	C, D, E
    //4번 손님	A, C, D, E
    //5번 손님	B, C, F, G
    //6번 손님	A, C, D, E, H

    // dfs
    // ["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]
    // [2,3,4] -- 구성하고싶은 코스요리의 갯수
    private static List<String> combinationMenu;

    public String[] solution_menuRenewal(String[] orders, int[] course) {
        String[] answer = {};

        // 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성하려고 합니다.
        // 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함
        // 1. 한 주문 당 가능한 모든 조합 구하기
        combinationMenu = new ArrayList<>();
        for (int i = 0; i < orders.length; i++) {
            char[] charOrders = orders[i].toCharArray(); //ex) ABCFG
            Arrays.sort(charOrders); // A B C F G

            for (int j = 0; j < charOrders.length; j++) { // 글자수의 모든 조합을 구한다
                for (int k = 0; k < course.length; k++) { // course의 길이만큼의 조합
                    menu_dfs(charOrders, j, 1, course[k], String.valueOf(charOrders[j]));
                }
            }

        }


        // 2. 조합 배열에서 많이 나타나는 순서대로 내림차순 정렬
        Map<String, Integer> map = new HashMap<>();
        for (String key : combinationMenu) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        List<String> keySetList = new ArrayList<>(map.keySet());
        Collections.sort(keySetList, (o1, o2) -> (map.get(o2).compareTo(map.get(o1))));


        // 3. 문자열의 길이가 같은 조합 중 가장 많이 나타난 조합 찾기
        List<String> ansList = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            int max_value = 0;

            for (String key : keySetList) {
                if (map.get(key) >= 2 && key.length() == course[i]) { //2명이상 주문한 조합 && input 코스메뉴수 같아야함
                    if (map.get(key) >= max_value) {
                        ansList.add(key);
                        max_value = map.get(key);
                    }
                }
            }
        }
        Collections.sort(ansList);
        answer = new String[ansList.size()];
        ansList.toArray(answer);

        return answer;
    }

    public static void menu_dfs(char[] arr, int index, int length, int courseLength, String str) {
        if (length == courseLength) {
            combinationMenu.add(str);
        }

        for (int i = index + 1; i < arr.length; i++) {
            menu_dfs(arr, i, length + 1, courseLength, str + arr[i]);
        }
    }

    // 가장큰 수
    public String solution_mostValue(int[] numbers) {
        String answer = "";

        // 큰수부터 내림차순 정렬
        Integer[] arr = new Integer[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = numbers[i];
        }
        Arrays.sort(arr, Collections.reverseOrder());

        // String 배열로 선언 및 할당
        String str_numbers[] = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            str_numbers[i] = String.valueOf(numbers[i]);
        }

        // 가장큰 수가 0일경우 예외처리
        if (str_numbers[0].startsWith("0")) {
            answer += "0";
        } else {
            for (int i = 0; i < str_numbers.length; i++) {
                answer += str_numbers[i];
            }
        }

        return answer;
    }

    // 후위 표기법으로 표현된 식
    public long solution_Postfix(String[] p) {
        long answer = 0;

        for (int i = 2; i < p.length; i++) {

            if ("*".equals(p[i]) || "+".equals(p[i]) || "/".equals(p[i]) || "-".equals(p[i])) {
                int temp = 0;

//                String[] arr = new String[p.length]; //새로운 arr에 결과를 넣어줌

                if ("*".equals(p[i])) {
                    //연산수행
                    if (i > 1) {
                        temp = Integer.parseInt(p[i - 2]) * Integer.parseInt(p[i - 1]);
                    } else {
                        temp = Integer.parseInt(p[i - 1]) * Integer.parseInt(p[i + 1]);
                    }

                    // 결과저장
                    p[i - 2] = temp + "";
                    // 버릴결과
                    p[i - 1] = "0";
                    p[i] = "0";

                    int index = 0;
                    for (int j = 0; j < p.length; j++) {
                        if (!"0".equals(p[j])) {
                            p[index] = p[j];
                        } else {
                            index--;
                        }
                        index++;
                    }
                }

                if ("+".equals(p[i])) {
                    //연산수행
                    if (i > 1) {
                        temp = Integer.parseInt(p[i - 2]) + Integer.parseInt(p[i - 1]);
                    } else {
                        temp = Integer.parseInt(p[i - 1]) + Integer.parseInt(p[i + 1]);
                    }

                    // 결과저장
                    p[i - 2] = temp + "";
                    // 버릴결과
                    p[i - 1] = "0";
                    p[i] = "0";

                    int index = 0;
                    for (int j = 0; j < p.length; j++) {
                        if (!"0".equals(p[j])) {
                            p[index] = p[j];
                        } else {
                            index--;
                        }
                        index++;
                    }
                }

                if ("/".equals(p[i])) {
                    //연산수행
                    if (i > 1) {
                        temp = Integer.parseInt(p[i - 2]) / Integer.parseInt(p[i - 1]);
                    } else {
                        temp = Integer.parseInt(p[i - 1]) / Integer.parseInt(p[i + 1]);
                    }

                    // 결과저장
                    p[i - 2] = temp + "";
                    // 버릴결과
                    p[i - 1] = "0";
                    p[i] = "0";

                    int index = 0;
                    for (int j = 0; j < p.length; j++) {
                        if (!"0".equals(p[j])) {
                            p[index] = p[j];
                        } else {
                            index--;
                        }
                        index++;
                    }
                }

                if ("-".equals(p[i])) {
                    //연산수행
                    if (i > 1) {
                        temp = Integer.parseInt(p[i - 2]) - Integer.parseInt(p[i - 1]);
                    } else {
                        temp = Integer.parseInt(p[i - 1]) - Integer.parseInt(p[i + 1]);
                    }

                    // 결과저장
                    p[i - 2] = temp + "";
                    // 버릴결과
                    p[i - 1] = "0";
                    p[i] = "0";

                    int index = 0;
                    for (int j = 0; j < p.length; j++) {
                        if (!"0".equals(p[j])) {
                            p[index] = p[j];
                        } else {
                            index--;
                        }
                        index++;
                    }
                }
            }
        }

        return answer;
    }

    @Test
    void asdsadasd() {
        String arr[] = {"12", "2", "5", "+", "*", "9", "3", "/", "-"};
        solution_Postfix(arr);
    }


    //4번
    ///* eslint-disable max-depth */
    //export function solution_124(arr) {
    //  let answer = 0;
    //
    //  while (true) {
    //    let tempArr = [];
    //    let curValue = arr[0];
    //    let step = 0;
    //
    //    for (let i = 0; i < arr.length; i++) {
    //      if (curValue === arr[i]) {
    //        step += 1;
    //      } else {
    //        tempArr.push(step);
    //        step = 1;
    //        curValue = arr[i];
    //      }
    //
    //      if (i === arr.length - 1) {
    //        tempArr.push(step);
    //      }
    //    }
    //    answer += 1;
    //
    //    arr = [...tempArr];
    //
    //    if (tempArr.length === 1 && tempArr[0] === 1) {
    //      break;
    //    }
    //  }
    //
    //  return answer;
    //}


    // **** 카카오뱅크4번
    public int solution_arrayY(int[] arr) {
        int answer = 0;

        List<Integer> arrayList = new ArrayList<>();
        for (int a : arr) {
            arrayList.add(a);
        }

        List<Integer> tempList = new ArrayList<>();

        while (true) {

            if (arrayList.size() == 1 && arrayList.get(0) == 1) {
                break;
            }

            tempList.clear();
            int currentValue = arrayList.get(0);
            int step = 0;

            // 수열 합치는 부분
            for (int i = 0; i < arrayList.size(); i++) {
                if (currentValue == arrayList.get(i)) {
                    step++;
                } else {
                    tempList.add(step);
                    step = 1;
                    currentValue = arrayList.get(i);
                }

                if (i == arrayList.size() - 1) {
                    tempList.add(step);
                }
            }

            answer++;

            arrayList.clear();
            for (int i = 0; i < tempList.size(); i++) {
                arrayList.add(tempList.get(i));
            }

        }

        System.out.println("answer : " + answer);
        return answer;
    }

    @Test
    void dasdsssad() {
        int[] arr = {1};
        solution_arrayY(arr);
    }

    //5번
    //const numberKey = {
    //  zero: '0',
    //  one: '1',
    //  two: '2',
    //  three: '3',
    //  four: '4',
    //  five: '5',
    //  six: '6',
    //  seven: '7',
    //  eight: '8',
    //  nine: '9',
    //};
    //export function solution_124(n) {
    //  let answer = '';
    //  let currentIdx = 0;
    //
    //  while (currentIdx < n.length) {
    //    for (let key of Object.keys(numberKey)) {
    //      if (n.indexOf(key, currentIdx) === currentIdx) {
    //        currentIdx += key.length;
    //        answer = answer + numberKey[key];
    //        break;
    //      }
    //    }
    //  }
    //
    //  return answer;
    //}

    // ******** 카카오뱅크 5번
    // onetwofour
    public String solution(String number) {

        HashMap<String, String> numberMap = new HashMap<>();
        numberMap.put("zero", "0");
        numberMap.put("one", "1");
        numberMap.put("two", "2");
        numberMap.put("three", "3");
        numberMap.put("four", "4");
        numberMap.put("five", "5");
        numberMap.put("six", "6");
        numberMap.put("seven", "7");
        numberMap.put("eight", "8");
        numberMap.put("nine", "9");

        String answer = "";

        int currentIndex = 0;
        while (currentIndex < number.length()) {
            for (String key : numberMap.keySet()) {
                if (currentIndex == number.indexOf(key, currentIndex)) {
                    currentIndex += key.length();
                    answer += numberMap.get(key);
                    break;
                }
            }
        }

        System.out.println("answer : " + answer);
        return answer;
    }

    @Test
    void ddsssdddd() {
        solution("onesevenfive");
    }

    //1번
    //export function solution_124(p) {
    //  let stack = [];
    //
    //  if (p.length === 1) return Number(p);
    //
    //  stack.push(Number(p[0]));
    //
    //  for (let i = 1; i < p.length; i++) {
    //    if (['*', '+', '/', '-'].includes(p[i])) {
    //      const op2 = stack.pop();
    //      const op1 = stack.pop();
    //      switch (p[i]) {
    //        case '+':
    //          stack.push(op1 + op2);
    //          break;
    //        case '*':
    //          stack.push(op1 * op2);
    //          break;
    //        case '/':
    //          stack.push(Math.floor(op1 / op2));
    //          break;
    //        case '-':
    //          stack.push(op1 - op2);
    //          break;
    //        default:
    //          break;
    //      }
    //    } else {
    //      stack.push(Number(p[i]));
    //    }
    //  }
    //
    //  return stack[0];
    //}

    // ******** 카카오뱅크 1번
    public long solution_aa(String[] p) {
        long answer = 0;
        Stack<Long> stack = new Stack();

        if (p.length == 1) {
            return Long.parseLong(p[0]);
        }

        stack.push(Long.parseLong(p[0]));

        for (int i = 1; i < p.length; i++) {
            if ("*+/-".contains(p[i])) {
                long op2 = stack.pop();
                long op1 = stack.pop();
                switch (p[i]) {
                    case "+":
                        stack.push(op1 + op2);
                        break;
                    case "*":
                        stack.push(op1 * op2);
                        break;
                    case "/":
                        stack.push(op1 / op2);
                        break;
                    case "-":
                        stack.push(op1 - op2);
                        break;
                    default:
                        break;
                }
            } else {
                stack.push(Long.parseLong(p[i]));
            }
        }

        return stack.get(0);
    }

    //2번
    ///* eslint-disable max-depth */
    //
    //const NOT_FOUND = 404;
    //const FORBIDDEN = 403;
    //const SUCCESS = 200;
    //
    //function create(accounts, key, value) {
    //  if (accounts.hasOwnProperty(key))
    //    return { accounts: { ...accounts }, code: FORBIDDEN };
    //
    //  return {
    //    accounts: {
    //      ...accounts,
    //      [key]: {
    //        balance: value,
    //        withdrawalLimit: value,
    //      },
    //    },
    //    code: SUCCESS,
    //  };
    //}
    //
    //function deposit(accounts, key, value) {
    //  if (!accounts.hasOwnProperty(key))
    //    return { accounts: { ...accounts }, code: NOT_FOUND };
    //
    //  return {
    //    accounts: {
    //      ...accounts,
    //      [key]: {
    //        ...accounts[key],
    //        balance: accounts[key].balance + value,
    //      },
    //    },
    //    code: SUCCESS,
    //  };
    //}
    //
    //function withdraw(accounts, key, value) {
    //  if (!accounts.hasOwnProperty(key))
    //    return { accounts: { ...accounts }, code: NOT_FOUND };
    //
    //  if (value > accounts[key].withdrawalLimit)
    //    return {
    //      accounts: { ...accounts },
    //      code: FORBIDDEN,
    //    };
    //
    //  if (value > accounts[key].balance)
    //    return {
    //      accounts: { ...accounts },
    //      code: FORBIDDEN,
    //    };
    //
    //  return {
    //    accounts: {
    //      ...accounts,
    //      [key]: {
    //        ...accounts[key],
    //        balance: accounts[key].balance - value,
    //      },
    //    },
    //    code: SUCCESS,
    //  };
    //}
    //
    //export function solution_124(reqs) {
    //  let answer = [];
    //  let accounts = {};
    //  let state = {};
    //
    //  for (const req of reqs) {
    //    const [command, key, value] = req.split(' ');
    //    switch (command) {
    //      case 'CREATE':
    //        state = create(accounts, key, value);
    //        break;
    //      case 'DEPOSIT':
    //        state = deposit(accounts, key, value);
    //        break;
    //      case 'WITHDRAW':
    //        state = withdraw(accounts, key, value);
    //        break;
    //      default:
    //        break;
    //    }
    //
    //    accounts = state.accounts;
    //    answer.push(state.code);
    //  }
    //
    //  return answer;
    //}


    //  ******** 카카오뱅크 2번
    private final String NOT_FOUND = "404";
    private final String FORBIDDEN = "403";
    private final String SUCCESS = "200";

    public int[] solution_bank(String[] reqs) {

        Map<String, Account> accounts = new HashMap<>();
        List<String> list = new ArrayList<>();
        String state = "";

        for (String req : reqs) {
            String[] r = req.split(" ");
            String command = r[0];
            String key = r[1];
            long value = Long.parseLong(r[2]);

            switch (command) {
                case "CREATE":
                    state = create(accounts, key, value);
                    break;
                case "DEPOSIT":
                    state = deposit(accounts, key, value);
                    break;
                case "WITHDRAW":
                    state = withdraw(accounts, key, value);
                    break;
                default:
                    break;
            }
            list.add(state);
        }

        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = Integer.parseInt(list.get(i));
        }

        return answer;
    }

    // Map key : 입금계좌명 value : Account(계좌내정보)
    private String create(Map<String, Account> accountMap, String key, long value) {
        if (accountMap.get(key) != null) {
            return FORBIDDEN;
        }

        Account account = new Account();
        account.setBalance(value);
        accountMap.put(key, account);
        return SUCCESS;
    }

    private String deposit(Map<String, Account> account, String key, long value) {
        if (account.get(key) == null) {
            return NOT_FOUND;
        }

        Account thisAccount = account.get(key);
        thisAccount.setBalance(account.get(key).getBalance() + value);
        account.put(key, thisAccount);
        return SUCCESS;
    }

    private String withdraw(Map<String, Account> account, String key, long value) {
        if (account.get(key) == null) {
            return NOT_FOUND;
        }

//        if (value > account.get(key).withdrawLimit) {
//            return FORBIDDEN;
//        }
        if (value > account.get(key).balance) {
            return FORBIDDEN;
        }

        Account thisAccount = account.get(key);
        thisAccount.setBalance(thisAccount.getBalance() - value);
        return SUCCESS;
    }

    class Account {
        long balance; //잔고
        long withdrawLimit; //출금한도

        public Account() {

        }

        public Account(long balance, long withdrawLimit) {
            this.balance = balance;
            this.withdrawLimit = withdrawLimit;
        }

        public long getBalance() {
            return balance;
        }

        public long getWithdrawLimit() {
            return withdrawLimit;
        }

        public void setBalance(long balance) {
            this.balance = balance;
        }

        public void setWithdrawLimit(long withdrawLimit) {
            this.withdrawLimit = withdrawLimit;
        }
    }


    @Test
    void asdasdsdsad() {
        String[] req = {"DEPOSIT 3a 10000", "CREATE 3a 300000", "WITHDRAW 3a 150000", "WITHDRAW 3a 150001"};
        solution_bank(req);
    }



}
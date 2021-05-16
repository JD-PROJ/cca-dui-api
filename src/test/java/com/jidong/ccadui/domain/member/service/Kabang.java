package com.jidong.ccadui.domain.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class Kabang {

    //1번. 후위표현식
    //["12","2","5","+","*","9","3","/","-"]
    // 2 + 5 = 7
    //["12","7","*","9","3","/","-"]
    // 12 * 7 = 84
    //["84","9","3","/","-"]
    //9 / 3
    //["84","3","-"]
    //84 - 3 = 81
    //81
    public long solution_1(String[] p) {
        Stack<Long> stack = new Stack<>();

        if (p.length == 1) {
            return Long.parseLong(p[0]);
        }

        // 맨 처음은 그냥 넣어줌
        stack.push(Long.parseLong(p[0]));

        // 연산자를 만나면 값을 꺼내서 계산후 넣어줌. 아니면 그냥 숫자를 넣어줌.
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
                }
            } else {
                stack.push(Long.parseLong(p[i]));
            }
        }
        System.out.println("answer : " + stack.get(0).intValue());

        // 이로케했어야했는데 변환을 안했다 ㅠㅠ
        return stack.get(0);
    }

    @Test
    void run_solution_1() {
        String[] p = {"12", "2", "5", "+", "*", "9", "3", "/", "-"};
        solution_1(p);
    }


    //2번. 계좌개설 은행 서버 구현
    // 1) 계좌 개설 요청(CREATE 계좌아이디 최대한도) - 이미 개설됐으면 403 / 개설되지 않았으면 최대한도 출금계좌 개설후 200코드
    // 2) 입금 요청(DEPOSIT 계좌아이디 금액) - 없는 계좌라면 404 / 잇는계좌라면 금액만큼 입금한 후 200코드
    // 3) 출금 요청(WITHDRAW 계좌아이디 금액) - 없는 계좌라면 404 / 계좌 있지만 최대한도 초과시 403, 그외 경우 출금 후 200코드

    private final int NOT_FOUND = 404;
    private final int FORBIDDEN = 403;
    private final int SUCCESS = 200;

    public int[] solution_2(String[] reqs) {

        Map<String, Account> accountMap = new HashMap<>(); // key : 계좌아이디, Account : 계좌
        List<Integer> list = new ArrayList<>();
        int state = 0;

        for (String req : reqs) {
            String[] r = req.split(" ");
            String command = r[0];
            String key = r[1];
            long value = Long.parseLong(r[2]);

            switch (command) {
                case "CREATE":
                    state = create(accountMap, key, value);
                    break;
                case "DEPOSIT":
                    state = deposit(accountMap, key, value);
                    break;
                case "WITHDRAW":
                    state = withdraw(accountMap, key, value);
            }

            list.add(state);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.println("answer : " + answer[i]);
        }
        return answer;
    }


    // 1) 계좌 개설 요청(CREATE 계좌아이디 최대한도) - 이미 개설됐으면 403 / 개설되지 않았으면 최대한도 출금계좌 개설후 200코드
    private int create(Map<String, Account> accountMap, String key, long value) {
        if (accountMap.get(key) != null) {
            return FORBIDDEN;
        }

        Account account = new Account();
        account.setBalance(value);
        accountMap.put(key, account);
        return SUCCESS;
    }


    // 2) 입금 요청(DEPOSIT 계좌아이디 금액) - 없는 계좌라면 404 / 잇는계좌라면 금액만큼 입금한 후 200코드
    private int deposit(Map<String, Account> account, String key, long value) {
        if (account.get(key) == null) {
            return NOT_FOUND;
        }

        Account thisAccount = account.get(key);
        thisAccount.setBalance(account.get(key).getBalance() + value);
        account.put(key, thisAccount);
        return SUCCESS;
    }

    // 3) 출금 요청(WITHDRAW 계좌아이디 금액) - 없는 계좌라면 404 / 계좌 있지만 최대한도 초과시 403, 그외 경우 출금 후 200코드
    private int withdraw(Map<String, Account> account, String key, long value) {
        if (account.get(key) == null) {
            return NOT_FOUND;
        }

        if (account.get(key).balance < value) {
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

        public long getBalance() {
            return balance;
        }

        public void setBalance(long balance) {
            this.balance = balance;
        }
    }

    @Test
    void run_solution_2() {
        String[] req = {"DEPOSIT 3a 10000", "CREATE 3a 300000", "WITHDRAW 3a 150000", "WITHDRAW 3a 150001"};
        solution_2(req);
    }

    //4번. 수열합치기
    // [1, 1, 3, 3, 2, 2, 4, 5, 1, 1, 1, 3, 3, 3]
    // => 연속해서 나온 개수는 [2, 2, 2, 1, 1, 3, 3]
    // 새로 구한 수열에서 다시 같은 값이 나온 수를 나열
    // => [3, 2, 2]
    // => [1, 2] => [1, 1] => [2] => [1]
    // 1이 나올때까지 과정을 몇번 수행했는지 return 하도록 작성
    public int solution_4(int[] arr) {
        int answer = 0;

        // 1. 리스트에 넣기
        List<Integer> arrayList = new ArrayList<>();
        for (int a : arr) {
            arrayList.add(a);
        }

        // 임시 리스트
        List<Integer> tempList = new ArrayList<>();

        while (true) {

            // 2. 수열의 크기가 하나만 남고 1이면 break
            if (arrayList.size() == 1 && arrayList.get(0) == 1) {
                break;
            }

            tempList.clear();
            int currentValue = arrayList.get(0);
            int step = 0; // 연속해서 나올수 있는 개수

            // 3. 연속한 수열 개수 찾기
            for (int i = 0; i < arrayList.size(); i++) {
                // 비교할 값과 리스트의 인덱스 비교 같으면 ++
                if (currentValue == arrayList.get(i)) {
                    step++;
                } else { // 다르면 임시리스트에 넣어주고 step 초기화, 비교할 값 변경
                    tempList.add(step);
                    step = 1;
                    currentValue = arrayList.get(i);
                }

                // 비교할값이 더이상 없으면 임시리스트에 넣어줌
                if (i == arrayList.size() - 1) {
                    tempList.add(step);
                }
            }

            // 4. 임시리스트 값 수열리스트에 할당 & 연속한 수열 1이될떄까지 다시 찾기
            arrayList.clear();
            for(int i=0; i< tempList.size(); i++) {
                arrayList.add(tempList.get(i));
            }

            answer++;
        }

        System.out.println("answer : " + answer);
        return answer;
    }

    @Test
    void run_solution_4() {
//        int[] arr = {1, 1, 3, 3, 2, 2, 4, 5, 1, 1, 1, 3, 3, 3};
        int[] arr = {1, 3};
        solution_4(arr);
    }

    //5번.
}

insert into MB_MEM_OAUTH(MEM_NO, SERVICE_NAME, SERVICE_USER_ID, SERVICE_USER_EMAIL, SERVICE_PROFILE_ID, ACCESS_TOCKEN, UPDATE_NO)
values(1, 'KAKAO', 'kakaoServiceID', 'test@naver.com', 'testNickName', 'fZ_MNJOUENe_PRpfqbSqdavTOeUVsf397vy_4wo9dNoAAAF3RAndig', 1);

insert into MB_MEM_OAUTH(MEM_NO, SERVICE_NAME, SERVICE_USER_ID, SERVICE_USER_EMAIL, SERVICE_PROFILE_ID, ACCESS_TOCKEN, UPDATE_NO)
values(2, 'KAKAO', 'kakaoServiceID2', 'test2@naver.com', 'testNickName2', 'fZ_MNJOUENe_PRpfqbSqdavTOeUVsf397vy_4wo9dNoAAAF3RBndig', 2);

insert into CAL_SCHEDULE(SCHEDULE_NO, SCHEDULE_TITLE, SCHEDULE_STATUS, SCHEDULE_OWNER_NO, BEGIN_DATE, END_DATE, CONFIRM_DATE, UPDATE_NO, UPDATE_DATE, CREATE_NO, CREATE_DATE) values (1, '지지동 만나는 날', '01', 1000, '2021-02-22', '2021-03-20', null, 1000, sysdate(), 1000, sysdate());
insert into CAL_SCHEDULE_MEM(MEM_NO, SCHEDULE_NO, NON_AVAILABLE_TIME, UPDATE_NO, UPDATE_DATE, CREATE_NO, CREATE_DATE) values (1000, 1, '2021-02-22', 1000, sysdate(), 1000, sysdate());
insert into CAL_SCHEDULE_MEM(MEM_NO, SCHEDULE_NO, NON_AVAILABLE_TIME, UPDATE_NO, UPDATE_DATE, CREATE_NO, CREATE_DATE) values (1000, 1, '2021-02-23', 1000, sysdate(), 1000, sysdate());
insert into CAL_SCHEDULE_MEM(MEM_NO, SCHEDULE_NO, NON_AVAILABLE_TIME, UPDATE_NO, UPDATE_DATE, CREATE_NO, CREATE_DATE) values (1000, 1, '2021-02-25', 1000, sysdate(), 1000, sysdate());

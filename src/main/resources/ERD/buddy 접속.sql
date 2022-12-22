-- 회원테이블
create table member(
   member_seq number not null primary key,
   member_id varchar2(50) not null, -- 이메일로 받음
   member_pw varchar2(200),
   member_name varchar2(50) not null,
   member_phone varchar2(30),
   member_signup_date timestamp default sysdate not null,
   member_logtype varchar(20) not null /* normal, kakao, naver로 로그인 구분 */
);
select * from member_img;
-- 회원 sequence
create sequence member_seq
start with 1
increment by 1
nocache
nomaxvalue;
                                                                              

-- 프로필 이미지 파일
create table member_img(
   member_img_member_seq number, -- member테이블의 member_seq 값 (외래키)
   member_img_oriname varchar2(100),
   member_img_sysname varchar2(100)
   /*, CONSTRAINT fk_member_img_member_id foreign key(member_img_member_id) references member (member_id) ON DELETE CASCADE*/
);

-- 개인 파일 테이블
create table personal_files(
   personal_files_key varchar(20)  not null primary key,
   personal_files_oriname varchar2(200),
   personal_files_sysname varchar2(200),
   personal_files_member_seq number not null, -- member테이블의 member_seq 값 (외래키)
   personal_files_folder_key varchar2(20) -- personal_folder테이블의 personal_folder_seq 값 (외래키) -- key로 변경
);

-- 개인 폴더 테이블
create table personal_folder(
   personal_folder_key varchar2(20) not null primary key, -- key로 변경(회원가입할 때 새로운 난수 지정, parent_key에 basic값 입력)
   personal_folder_name varchar(100) not null,
   personal_folder_member_seq number not null, -- member테이블의 member_seq 값 (외래키)
   personal_folder_parent_key varchar(20), -- personal_folder테이블의 folder_seq값(외래키X), 초기 값은 basic_folder테이블의 basic_folder_seq값으로 쓸 예정(default 아님), -- key로 변경
   personal_folder_path varchar(200), -- 실제 저장될 경로
   personal_folder_date Timestamp
);

-- 기본 생성 폴더
create table basic_folder(
    basic_folder_key varchar2(20) not null primary key, -- key
    basic_folder_member_seq number,  -- member테이블의 member_seq값 (외래키)
    basic_folder_name varchar2(20)-- 기본 생성된 테이블의 이름(member테이블 name)
);


-- 단체 폴더 테이블(나중에 추가)
/*
create table team_folder(
   team_folder_seq number primary key,
   team_folder_name varchar(200),
   team_folder_team_seq number
);

-- 단체 폴더 sequence
create sequence team_file_seq
start with 1
increment by 1
nocache
nomaxvalue;
*/

-- 회원 초대 테이블
create table invite(
   invite_team_seq number not null, /*team테이블의 team_seq값 (외래키) */
   invite_send_mem_seq number not null, /* member테이블의 member_seq값 (외래키) */
   invite_receive_mem_email varchar(50) not null,
   invite_code varchar(50) not null /* 일회성 코드 */
);

------------------------ 팀 테이블

create table team (
    team_seq number primary key not null,
    team_name varchar2(50) not null,
    team_owner_seq number not null, -- 회원번호 foreign key
    team_count number not null,
    team_made_time timestamp default sysdate not null
);

create sequence team_seq
start with 1
increment by 1
nomaxvalue 
nocache;

create table team_member(
team_seq number not null, /* team테이블의 team_seq 가져오기 (외래키) */
/*CONSTRAINT fk_team_seq foreign key(team_seq) references team (team_seq) */
member_seq number not null, /* member 테이블의 seq 값 가져오기 (외래키) */
member_id varchar2(50) not null, /* member 테이블의 member_id 값이랑 같음*/
team_member_nickname varchar(50), /* 팀 내에서의 닉네임 */
grade varchar2(50) not null, /* 등급 컬럼 */
team_join_date timestamp default sysdate not null /* 팀 들어왔을 때 시기 */
);

----------------------- 채팅 테이블

create table chat_room (
    chat_room_seq number PRIMARY KEY,
    team_seq number not null, -- team테이블의 team_seq 가져오기 (외래키)(해당 채팅방이 어느팀의 채팅방인지 파악하기위한 외래키)
    chat_title varchar2(50) not null,
    room_owner_seq number not null,
    member_count number not null,
    chat_made_time timestamp default sysdate not null
);

create sequence chat_room_seq
start with 1
increment by 1
nomaxvalue 
nocache;

create table chat_member (
    chat_room_seq number not null, -- chat_room_seq 외래키
    member_seq number not null, -- 회원정보 외래키
    chat_join_date timestamp default sysdate not null,
    recent_view_date timestamp default sysdate
);

create table chat_msg (
    chat_seq number primary Key,
    chat_room_seq number not null, -- chat_room_seq 외래키
    chat_content varchar2(1000) not null,
    chat_writer_seq number not null, --회원번호 외래키
    read_count number,
    chat_date timestamp default sysdate not null    
);

-----------채팅 파일 테이블
create table chat_files(
    chat_files_seq number NOT NULL PRIMARY KEY,
    chat_oriname varchar2(100) not null,
    chat_sysname varchar2(100) not null,
    chat_room_seq number not null /* chat_room테이블의 chat_room_seq 값 가져오기 (외래키) */
);

create sequence chat_files_seq
start with 1
increment by 1
nomaxvalue 
nocache;

--------------- QNA 테이블

create table qna_board(
    qna_seq number NOT NULL PRIMARY KEY,
    qna_writer number NOT NULL, /* member 테이블의 seq 값 가져오기 (외래키) */
    qna_title varchar2(90) NOT NULL,
    qna_contents varchar2(600) NOT NULL,
    qna_write_date timestamp NOT NULL,
    qna_type varchar2(100) NOT NULL -- 문의유형구분 (계정, 버그리포트 등등)
);

create sequence qna_seq
start with 1
increment by 1
nomaxvalue 
nocache;

create table qna_comment (
    qna_comment_seq number NOT NULL primary key,
    qna_comment_writer number NOT NULL, /* member 테이블의 seq 값 가져오기 (외래키) */
    qna_comment_contents varchar2 (600) NOT NULL,
    qna_comment_write_date Timestamp NOT NULL,
    qna_seq number NOT NULL /* qna_board테이블의 qna_seq 값 가져오기 (외래키) */
);

create sequence qna_comment_seq
start with 1
increment by 1
nomaxvalue 
nocache;

create table qna_files(
    qna_files_seq number NOT NULL PRIMARY KEY,
    qna_oriname varchar2(100) not null,
    qna_sysname varchar2(100) not null,
    qna_seq number not null, /* qna_board테이블의 qna_seq 값 가져오기 (외래키) */
    qna_category varchar2(100) not null -- 문의글에 첨부된 내용인지, 문의 댓글에 첨부된 내용인지 분류
);

create sequence qna_files_seq
start with 1
increment by 1
nomaxvalue 
nocache;

--------------------------- 뉴스(공지) 테이블

create table notice_board(
    notice_seq number NOT NULL PRIMARY KEY,
    notice_writer number NOT NULL, /* member 테이블의 seq 값 가져오기 (외래키) */
    notice_title varchar2(90) NOT NULL,
    notice_contents varchar2(600) NOT NULL,
    notice_write_date timestamp NOT NULL
);

create sequence notice_seq
start with 1
increment by 1
nomaxvalue 
nocache;

create table notice_files(
    notice_files_seq number NOT NULL PRIMARY KEY,
    notice_oriname varchar2(100) not null,
    notice_sysname varchar2(100) not null,
    notice_seq number not null /*notice_board 테이블의 notice_seq값 가져오기 (외래키) */
);

create sequence notice_files_seq
start with 1
increment by 1
nomaxvalue 
nocache;

---------------------일정 테이블
create table event(
    "event_seq" number primary key not null,
    -- 기본키 일정 시퀀스
    "team_seq" number not null,
    -- 팀 seq [외래키]
    "event_name" varchar2(50) not null,
    -- 일정 제목
    "event_writer" varchar(200) not null,
    -- 작성자
    "event_type" int not null,
    -- 개인 / 팀 용도 구분
    "event_start" date not null,
    -- 일정의 시작 시점
    "event_end" date not null,
    -- 일정의 종료 시점
    "event_location" varchar2(200) ,
    -- 일정 장소
    "event_memo" varchar2(500)
    -- 일정 내용
);

create sequence event_seq
start with 1
increment by 1
nomaxvalue
nocache;

---------------------
/* 투표 테이블 */
create table vote(
vote_seq number primary key,
team_seq number not null, /* team 테이블의 seq 값 가져오기 (외래키) */
chat_room_seq number not null, /* chat_room 테이블의 seq값 가져오기 (외래키) */
vote_title varchar2(100) not null, /* 투표 제목 */
vote_content varchar(100), /* 투표 설명 */
vote_anonymous number default 0 not null, /* 익명투표 기능 0,1로 구분 */
vote_duplication number default 0 not null, /* 중복선택 기능 0,1로 구분 */
vote_made_time timestamp default sysdate not null, /* 투표 만든 시간 */
vote_end_time timestamp default sysdate not null /* 투표 마감 시간 */
);

create sequence vote_seq
start with 1
increment by 1
nomaxvalue 
nocache;

/*투표 항목 테이블 */
create table vote_item(
    vote_seq number not null, /* vote 테이블의 seq값 가져오기(외래키) */
    vote_item_seq number not null PRIMARY KEY, /* 항목 번호 */
    vote_item_title varchar(50) not null, /* 항목 이름*/
    vote_item_count number default 0 not null /* 항목에 투표한 사람 수 */
);

create sequence vote_item_seq
start with 1
increment by 1
nomaxvalue 
nocache;

/* 투표 항목에 투표한 member 테이블 */
create table vote_item_member(
    vote_seq number not null, /* vote 테이블의 seq값 가져오기(외래키) */ 
    vote_item_seq number not null, /* vote_item 테이블의 vote_item_seq값 가져오기(외래키) */
    member_seq number not null /* member 테이블의 seq 값 가져오기 (외래키) */
);

create sequence vote_item_member_seq
start with 1
increment by 1
nomaxvalue 
nocache;
-- ȸ�����̺�
create table member(
   member_seq number not null primary key,
   member_id varchar2(50) not null, -- �̸��Ϸ� ����
   member_pw varchar2(200),
   member_name varchar2(50) not null,
   member_phone varchar2(30),
   member_signup_date timestamp default sysdate not null,
   member_logtype varchar(20) not null /* normal, kakao, naver�� �α��� ���� */
);

-- ȸ�� sequence
create sequence member_seq
start with 1
increment by 1
nocache
nomaxvalue;
                                                                              

-- ������ �̹��� ����
create table member_img(
   member_img_member_seq number, -- member���̺��� member_seq �� (�ܷ�Ű)
   member_img_oriname varchar2(100),
   member_img_sysname varchar2(100)
   /*, CONSTRAINT fk_member_img_member_id foreign key(member_img_member_id) references member (member_id) ON DELETE CASCADE*/
);

-- ���� ���� ���̺�
create table personal_files(
   personal_files_key varchar(20)  not null primary key,
   personal_files_oriname varchar2(200),
   personal_files_sysname varchar2(200),
   personal_files_member_seq number not null, -- member���̺��� member_seq �� (�ܷ�Ű)
   personal_files_folder_key varchar2(20) -- personal_folder���̺��� personal_folder_seq �� (�ܷ�Ű) -- key�� ����
);

-- ���� ���� ���̺�
create table personal_folder(
   personal_folder_key varchar2(20) not null primary key, -- key�� ����(ȸ�������� �� ���ο� ���� ����, parent_key�� basic�� �Է�)
   personal_folder_name varchar(100) not null,
   personal_folder_member_seq number not null, -- member���̺��� member_seq �� (�ܷ�Ű)
   personal_folder_parent_key varchar(20), -- personal_folder���̺��� folder_seq��(�ܷ�ŰX), �ʱ� ���� basic_folder���̺��� basic_folder_seq������ �� ����(default �ƴ�), -- key�� ����
   personal_folder_path varchar(200), -- ���� ����� ���
   personal_folder_date Timestamp
);

-- �⺻ ���� ����
create table basic_folder(
    basic_folder_key varchar2(20) not null primary key, -- key
    basic_folder_member_seq number,  -- member���̺��� member_seq�� (�ܷ�Ű)
    basic_folder_name varchar2(20)-- �⺻ ������ ���̺��� �̸�(member���̺� name)
);


-- ��ü ���� ���̺�(���߿� �߰�)
/*
create table team_folder(
   team_folder_seq number primary key,
   team_folder_name varchar(200),
   team_folder_team_seq number
);

-- ��ü ���� sequence
create sequence team_file_seq
start with 1
increment by 1
nocache
nomaxvalue;
*/

-- ȸ�� �ʴ� ���̺�
create table invite(
   invite_team_seq number not null, /*team���̺��� team_seq�� (�ܷ�Ű) */
   invite_send_mem_seq number not null, /* member���̺��� member_seq�� (�ܷ�Ű) */
   invite_receive_mem_email varchar(50) not null,
   invite_code varchar(50) not null /* ��ȸ�� �ڵ� */
);

------------------------ �� ���̺�

create table team (
    team_seq number primary key not null,
    team_name varchar2(50) not null,
    team_owner_seq number not null, -- ȸ����ȣ foreign key
    team_count number not null,
    team_made_time timestamp default sysdate not null
);

create sequence team_seq
start with 1
increment by 1
nomaxvalue 
nocache;

create table team_member(
team_seq number not null, /* team���̺��� team_seq �������� (�ܷ�Ű) */
/*CONSTRAINT fk_team_seq foreign key(team_seq) references team (team_seq) */
member_seq number not null, /* member ���̺��� seq �� �������� (�ܷ�Ű) */
member_id varchar2(50) not null, /* member ���̺��� member_id ���̶� ����*/
team_member_nickname varchar(50), /* �� �������� �г��� */
grade varchar2(50) not null, /* ��� �÷� */
team_join_date timestamp default sysdate not null /* �� ������ �� �ñ� */
);

----------------------- ä�� ���̺�

create table chat_room (
    chat_room_seq number PRIMARY KEY,
    team_seq number not null, -- team���̺��� team_seq �������� (�ܷ�Ű)(�ش� ä�ù��� ������� ä�ù����� �ľ��ϱ����� �ܷ�Ű)
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
    chat_room_seq number not null, -- chat_room_seq �ܷ�Ű
    member_seq number not null, -- ȸ������ �ܷ�Ű
    chat_join_date timestamp default sysdate not null,
    recent_view_date timestamp default sysdate
);

create table chat_msg (
    chat_msg_seq number primary Key,
    chat_room_seq number not null, -- chat_room_seq �ܷ�Ű
    chat_content varchar2(1000) not null,
    chat_writer_seq number not null, --ȸ����ȣ �ܷ�Ű
    read_count number,
    chat_date timestamp default sysdate not null    
);

-----------ä�� ���� ���̺�
create table chat_files(
    chat_files_seq number NOT NULL PRIMARY KEY,
    chat_oriname varchar2(100) not null,
    chat_sysname varchar2(100) not null,
    chat_room_seq number not null /* chat_room���̺��� chat_room_seq �� �������� (�ܷ�Ű) */
);

create sequence chat_files_seq
start with 1
increment by 1
nomaxvalue 
nocache;

--------------- QNA ���̺�

create table qna_board(
    qna_seq number NOT NULL PRIMARY KEY,
    qna_writer number NOT NULL, /* member ���̺��� seq �� �������� (�ܷ�Ű) */
    qna_title varchar2(90) NOT NULL,
    qna_contents varchar2(600) NOT NULL,
    qna_write_date timestamp NOT NULL,
    qna_type varchar2(100) NOT NULL -- ������������ (����, ���׸���Ʈ ���)
);

create sequence qna_seq
start with 1
increment by 1
nomaxvalue 
nocache;

create table qna_comment (
    qna_comment_seq number NOT NULL primary key,
    qna_comment_writer number NOT NULL, /* member ���̺��� seq �� �������� (�ܷ�Ű) */
    qna_comment_contents varchar2 (600) NOT NULL,
    qna_comment_write_date Timestamp NOT NULL,
    qna_seq number NOT NULL /* qna_board���̺��� qna_seq �� �������� (�ܷ�Ű) */
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
    qna_seq number not null, /* qna_board���̺��� qna_seq �� �������� (�ܷ�Ű) */
    qna_category varchar2(100) not null -- ���Ǳۿ� ÷�ε� ��������, ���� ��ۿ� ÷�ε� �������� �з�
);

create sequence qna_files_seq
start with 1
increment by 1
nomaxvalue 
nocache;

--------------------------- ����(����) ���̺�

create table notice_board(
    notice_seq number NOT NULL PRIMARY KEY,
    notice_writer number NOT NULL, /* member ���̺��� seq �� �������� (�ܷ�Ű) */
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
    notice_seq number not null /*notice_board ���̺��� notice_seq�� �������� (�ܷ�Ű) */
);

create sequence notice_files_seq
start with 1
increment by 1
nomaxvalue 
nocache;

---------------------���� ���̺�
create table event(
    "event_seq" number primary key not null,
    -- �⺻Ű ���� ������
    "team_seq" number not null,
    -- �� seq [�ܷ�Ű]
    "event_name" varchar2(50) not null,
    -- ���� ����
    "event_writer" varchar(200) not null,
    -- �ۼ���
    "event_type" int not null,
    -- ���� / �� �뵵 ����
    "event_start" date not null,
    -- ������ ���� ����
    "event_end" date not null,
    -- ������ ���� ����
    "event_location" varchar2(200) ,
    -- ���� ���
    "event_memo" varchar2(500)
    -- ���� ����
);

create sequence event_seq
start with 1
increment by 1
nomaxvalue
nocache;

---------------------
/* ��ǥ ���̺� */
create table vote(
vote_seq number primary key,
team_seq number not null, /* team ���̺��� seq �� �������� (�ܷ�Ű) */
chat_room_seq number not null, /* chat_room ���̺��� seq�� �������� (�ܷ�Ű) */
vote_title varchar2(100) not null, /* ��ǥ ���� */
vote_content varchar(100), /* ��ǥ ���� */
vote_anonymous number default 0 not null, /* �͸���ǥ ��� 0,1�� ���� */
vote_duplication number default 0 not null, /* �ߺ����� ��� 0,1�� ���� */
vote_made_time timestamp default sysdate not null, /* ��ǥ ���� �ð� */
vote_end_time timestamp default sysdate not null /* ��ǥ ���� �ð� */
);

create sequence vote_seq
start with 1
increment by 1
nomaxvalue 
nocache;

/*��ǥ �׸� ���̺� */
create table vote_item(
    vote_seq number not null, /* vote ���̺��� seq�� ��������(�ܷ�Ű) */
    vote_item_seq number not null PRIMARY KEY, /* �׸� ��ȣ */
    vote_item_title varchar(50) not null, /* �׸� �̸�*/
    vote_item_count number default 0 not null /* �׸� ��ǥ�� ��� �� */
);

create sequence vote_item_seq
start with 1
increment by 1
nomaxvalue 
nocache;

/* ��ǥ �׸� ��ǥ�� member ���̺� */
create table vote_item_member(
    vote_seq number not null, /* vote ���̺��� seq�� ��������(�ܷ�Ű) */ 
    vote_item_seq number not null, /* vote_item ���̺��� vote_item_seq�� ��������(�ܷ�Ű) */
    member_seq number not null /* member ���̺��� seq �� �������� (�ܷ�Ű) */
);

create sequence vote_item_member_seq
start with 1
increment by 1
nomaxvalue 
nocache;
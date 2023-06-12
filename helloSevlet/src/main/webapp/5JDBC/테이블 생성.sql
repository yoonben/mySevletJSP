-- 회원 테이블 생성
create table member (
    id varchar2(10) not null,
    pass varchar2(10) not null,
    name varchar2(30) not null,
    regidate date default sysdate not null,
    primary key (id)
);

-- 게시판 테이블 생성
create table board (
    num number primary key,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    id varchar2(10) not null,
    postdate date default sysdate not null,
    visitcount number(6)
);

-- 외래키 추가
alter table board
    add constraint board_mem_fk foreign key (id)
    references member (id);

-- 시퀀스 생성
create sequence seq_board_num 
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;
    
-- 더미데이터 입력
insert into member (id, pass, name) values ('test', '1234', '머스트해브');
insert into board  (num, title, content, id, postdate, visitcount) 
	values (seq_board_num.nextval, '제목1입니다', '내용1입니다', 'test', sysdate, 0);

-- comment 추가
COMMENT ON table BOARD IS '게시판';
COMMENT ON COLUMN BOARD.NUM IS '일련번호';
COMMENT ON COLUMN BOARD.TITLE IS '제목';
COMMENT ON COLUMN BOARD.CONTENT IS '내용';
COMMENT ON COLUMN BOARD.ID IS '작성자 아이디';
COMMENT ON COLUMN BOARD.POSTDATE IS '작성일';
COMMENT ON COLUMN BOARD.VISITCOUNT IS '조회수';

COMMENT ON table MEMBER IS '회원';
COMMENT ON COLUMN MEMBER.ID IS '아이디';
COMMENT ON COLUMN MEMBER.PASS IS '비밀번호';
COMMENT ON COLUMN MEMBER.NAME IS '이름';
COMMENT ON COLUMN MEMBER.REGIDATE IS '생성일';


    
    
    
    
    
    
    
    
    
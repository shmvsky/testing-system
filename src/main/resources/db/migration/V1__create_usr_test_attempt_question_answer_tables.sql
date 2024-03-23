CREATE SEQUENCE IF NOT EXISTS answer_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS attempt_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS question_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS test_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS usr_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE answer
(
    id         BIGINT  NOT NULL,
    attempt_id INTEGER,
    user_ans   VARCHAR(255),
    is_right   BOOLEAN NOT NULL,
    CONSTRAINT pk_answer PRIMARY KEY (id)
);

CREATE TABLE attempt
(
    id          INTEGER NOT NULL,
    user_id     INTEGER,
    test_id     INTEGER,
    status      VARCHAR(255),
    started_at  TIMESTAMP WITHOUT TIME ZONE,
    finished_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_attempt PRIMARY KEY (id)
);

CREATE TABLE question
(
    id      BIGINT  NOT NULL,
    test_id INTEGER,
    body    VARCHAR(255),
    answer  VARCHAR(255),
    CONSTRAINT pk_question PRIMARY KEY (id)
);

CREATE TABLE test
(
    id             INTEGER NOT NULL,
    author_id      INTEGER,
    title          VARCHAR(255),
    description    VARCHAR(255),
    attempts_limit INTEGER NOT NULL,
    is_opened      BOOLEAN NOT NULL,
    duration       BIGINT  NOT NULL,
    CONSTRAINT pk_test PRIMARY KEY (id)
);

CREATE TABLE usr
(
    id       INTEGER NOT NULL,
    fullname VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    role     VARCHAR(255),
    CONSTRAINT pk_usr PRIMARY KEY (id)
);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_ATTEMPT FOREIGN KEY (attempt_id) REFERENCES attempt (id);

ALTER TABLE attempt
    ADD CONSTRAINT FK_ATTEMPT_ON_TEST FOREIGN KEY (test_id) REFERENCES test (id);

ALTER TABLE attempt
    ADD CONSTRAINT FK_ATTEMPT_ON_USER FOREIGN KEY (user_id) REFERENCES usr (id);

ALTER TABLE question
    ADD CONSTRAINT FK_QUESTION_ON_TEST FOREIGN KEY (test_id) REFERENCES test (id);

ALTER TABLE test
    ADD CONSTRAINT FK_TEST_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES usr (id);
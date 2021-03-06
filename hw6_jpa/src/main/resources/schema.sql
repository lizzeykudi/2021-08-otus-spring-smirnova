DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(ID BIGINT NOT NULL AUTO_INCREMENT, TITLE VARCHAR(255), AUTHOR_ID BIGINT, BOOK_GENRE_ID BIGINT, PRIMARY KEY (ID));

DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR(255), PRIMARY KEY (ID));

DROP TABLE IF EXISTS BOOK_GENRES;
CREATE TABLE BOOK_GENRES(ID BIGINT NOT NULL AUTO_INCREMENT, TITLE VARCHAR(255), PRIMARY KEY (ID));

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS(ID BIGINT NOT NULL AUTO_INCREMENT, TEXT VARCHAR(255), BOOK_ID BIGINT, PRIMARY KEY (ID));

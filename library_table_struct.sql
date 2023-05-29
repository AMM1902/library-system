CREATE DATABASE library;

USE library;

CREATE TABLE librarians (
    librarian_id INT PRIMARY KEY AUTO_INCREMENT,
    librarian_name VARCHAR(30) NOT NULL,
    librarian_password VARCHAR(20) NOT NULL,
    librarian_email VARCHAR(50) NOT NULL,
    librarian_phone VARCHAR(20) NOT NULL,
    librarian_address VARCHAR(100) NOT NULL
);

CREATE TABLE members (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    member_name VARCHAR(30) NOT NULL,
    member_password VARCHAR(20) NOT NULL,
    member_email VARCHAR(50) NOT NULL,
    member_phone VARCHAR(20) NOT NULL,
    member_address VARCHAR(100) NOT NULL
);

CREATE TABLE authors (
    author_id INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(30) NOT NULL,
    author_email VARCHAR(50) NOT NULL,
    author_phone VARCHAR(20) NOT NULL
);

CREATE TABLE publishers (
    publisher_id INT PRIMARY KEY AUTO_INCREMENT,
    publisher_name VARCHAR(30) NOT NULL,
    publisher_email VARCHAR(50) NOT NULL,
    publisher_phone VARCHAR(20) NOT NULL
);


CREATE TABLE books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    book_name VARCHAR(100) NOT NULL,
    book_genre VARCHAR(40) NOT NULL,
    release_date DATE NOT NULL,
    book_location INT NOT NULL,
    author_id INT NOT NULL,
    publisher_id INT NOT NULL,
    librarian_id INT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(author_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (publisher_id) REFERENCES publishers(publisher_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (librarian_id) REFERENCES librarians(librarian_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE borrowed_books (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    member_id INT NOT NULL,
    issuer_id INT NOT NULL,
    issued_date DATE NOT NULL,
    std_return_date DATE AS (DATE(issued_date + INTERVAL 2 WEEK)),
    collector_id INT,
    return_date DATE,
    fine_mmk INT DEFAULT 0,
    FOREIGN KEY (book_id) REFERENCES books(book_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (member_id) REFERENCES members(member_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (issuer_id) REFERENCES librarians(librarian_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (collector_id) REFERENCES librarians(librarian_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
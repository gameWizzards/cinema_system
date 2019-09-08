--
-- Скрипт сгенерирован Devart dbForge Studio 2019 for MySQL, Версия 8.1.22.0
-- Домашняя страница продукта: http://www.devart.com/ru/dbforge/mysql/studio
-- Дата скрипта: 09-Sep-19 12:32:32 AM
-- Версия сервера: 8.0.17
-- Версия клиента: 4.1
--


-- 
-- Установить режим SQL (SQL mode)
-- 
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 
-- Установка кодировки, с использованием которой клиент будет посылать запросы на сервер
--
SET NAMES 'utf8';

DROP DATABASE IF EXISTS cinema_system;

CREATE DATABASE IF NOT EXISTS cinema_system
CHARACTER SET utf8mb4
COLLATE utf8mb4_0900_ai_ci;

--
-- Установка базы данных по умолчанию
--
USE cinema_system;

--
-- Создать таблицу `halls`
--
CREATE TABLE IF NOT EXISTS halls (
  ID int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  number decimal(10, 0) NOT NULL,
  max_places int(255) UNSIGNED DEFAULT 0,
  max_vip_places int(11) UNSIGNED DEFAULT 0,
  rows_number int(11) UNSIGNED DEFAULT 0,
  column_number int(11) DEFAULT 0,
  PRIMARY KEY (ID)
)
ENGINE = INNODB,
AUTO_INCREMENT = 4,
AVG_ROW_LENGTH = 5461,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci,
COMMENT = 'Залы в кинотеатре';

--
-- Создать индекс `FK_halls` для объекта типа таблица `halls`
--
ALTER TABLE halls
ADD INDEX FK_halls (max_places);

--
-- Создать таблицу `films`
--
CREATE TABLE IF NOT EXISTS films (
  ID int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  title varchar(50) DEFAULT NULL,
  standart_price float UNSIGNED DEFAULT NULL,
  premier_date date DEFAULT NULL,
  end_date date DEFAULT NULL,
  PRIMARY KEY (ID)
)
ENGINE = INNODB,
AUTO_INCREMENT = 6,
AVG_ROW_LENGTH = 4096,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;

--
-- Создать таблицу `sessions`
--
CREATE TABLE IF NOT EXISTS sessions (
  ID int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  date date DEFAULT NULL,
  time time DEFAULT NULL,
  Film int(11) UNSIGNED NOT NULL,
  Hall int(11) UNSIGNED NOT NULL,
  session_price float DEFAULT 0,
  vip_price float DEFAULT 0,
  free_places int(11) DEFAULT 0,
  amount_cash float DEFAULT 0,
  PRIMARY KEY (ID)
)
ENGINE = INNODB,
AUTO_INCREMENT = 7,
AVG_ROW_LENGTH = 8192,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;

--
-- Создать внешний ключ
--
ALTER TABLE sessions
ADD CONSTRAINT FK_sessions_films_ID FOREIGN KEY (Film)
REFERENCES films (ID);

--
-- Создать внешний ключ
--
ALTER TABLE sessions
ADD CONSTRAINT FK_sessions_halls_ID FOREIGN KEY (Hall)
REFERENCES halls (ID);

--
-- Создать таблицу `seats`
--
CREATE TABLE IF NOT EXISTS seats (
  ID int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  seat_row int(11) UNSIGNED DEFAULT NULL,
  place_in_row int(11) DEFAULT NULL,
  is_booked tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (ID)
)
ENGINE = INNODB,
AUTO_INCREMENT = 108,
AVG_ROW_LENGTH = 172,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;

--
-- Создать таблицу `tickets`
--
CREATE TABLE IF NOT EXISTS tickets (
  ID int(11) NOT NULL AUTO_INCREMENT,
  Session int(11) UNSIGNED NOT NULL,
  final_price float DEFAULT NULL,
  Seat int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (ID)
)
ENGINE = INNODB,
AUTO_INCREMENT = 106,
AVG_ROW_LENGTH = 172,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;

--
-- Создать внешний ключ
--
ALTER TABLE tickets
ADD CONSTRAINT FK_ticket_seat_ID FOREIGN KEY (Seat)
REFERENCES seats (ID);

--
-- Создать внешний ключ
--
ALTER TABLE tickets
ADD CONSTRAINT FK_ticket_sessions_ID FOREIGN KEY (Session)
REFERENCES sessions (ID);

-- 
-- Вывод данных для таблицы halls
--
INSERT INTO halls VALUES
(1, 1, 40, 10, 4, 10),
(2, 2, 30, 10, 3, 10),
(3, 3, 60, 10, 6, 10);

-- 
-- Вывод данных для таблицы films
--
INSERT INTO films VALUES
(1, 'Avangers Final', 60, '2019-09-01', '2019-09-30'),
(2, 'Deadpool 2', 70, '2019-09-03', '2019-09-18'),
(3, 'Godzila II', 80, '2019-08-08', '2019-10-31'),
(4, 'Not Valid Date Film ', 0, '2019-05-01', '2019-08-16');

-- 
-- Вывод данных для таблицы sessions
--
INSERT INTO sessions VALUES
(1, '2019-09-09', '18:00:00', 2, 2, 0, 100, 0, 0),
(2, '2019-09-09', '16:00:00', 2, 2, 0, 100, 0, 0),
(4, '2019-09-03', '17:40:00', 1, 1, 0, 120, 0, 1380),
(6, '2019-09-05', '20:00:00', 2, 2, 0, 160, 0, 0),
(7, '2019-09-09', '11:00:00', 2, 3, 50, 0, 0, 0),
(8, '2019-09-09', '20:00:00', 2, 2, 0, 100, 0, 0),
(9, '2019-09-09', '22:00:00', 2, 2, 0, 100, 0, 0),
(10, '2019-09-09', '10:00:00', 1, 1, 45, 0, 0, 0),
(11, '2019-09-09', '12:00:00', 1, 1, 45, 0, 0, 0),
(12, '2019-09-09', '14:00:00', 1, 1, 0, 150, 0, 0),
(13, '2019-09-09', '16:00:00', 1, 2, 0, 150, 0, 0),
(14, '2019-09-09', '18:00:00', 1, 1, 0, 150, 0, 0),
(15, '2019-09-09', '20:00:00', 1, 1, 0, 150, 0, 0),
(16, '2019-09-09', '14:00:00', 3, 3, 10, 0, 0, 0),
(17, '2019-09-09', '20:00:00', 3, 2, 10, 0, 0, 0);

-- 
-- Вывод данных для таблицы seats
--
INSERT INTO seats VALUES
(108, 1, 1, 1),
(109, 1, 2, 1),
(110, 1, 5, 1),
(111, 1, 6, 1),
(112, 1, 3, 1),
(113, 1, 4, 1),
(114, 6, 1, 1),
(115, 6, 2, 1),
(116, 6, 8, 1),
(117, 6, 9, 1),
(118, 7, 1, 1),
(119, 7, 6, 1),
(120, 7, 9, 1);

-- 
-- Вывод данных для таблицы tickets
--
INSERT INTO tickets VALUES
(106, 4, 60, 108),
(107, 4, 60, 109),
(108, 4, 60, 110),
(109, 4, 60, 111),
(110, 4, 60, 112),
(111, 4, 60, 113),
(112, 4, 60, 114),
(113, 4, 60, 115),
(114, 4, 60, 116),
(115, 4, 60, 117),
(116, 4, 120, 118),
(117, 4, 120, 119),
(118, 4, 120, 120);

-- 
-- Восстановить предыдущий режим SQL (SQL mode)
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
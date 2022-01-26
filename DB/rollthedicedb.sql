-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rollthedicedb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `rollthedicedb` ;

-- -----------------------------------------------------
-- Schema rollthedicedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rollthedicedb` DEFAULT CHARACTER SET utf8 ;
USE `rollthedicedb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(50) NULL,
  `city` VARCHAR(50) NULL,
  `state` VARCHAR(50) NULL,
  `postal_code` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `profile_picture_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  `address_id` INT NULL,
  `biography` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game` ;

CREATE TABLE IF NOT EXISTS `game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `max_players` INT NULL,
  `link_to_game` VARCHAR(2000) NULL,
  `time_to_play` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_event` ;

CREATE TABLE IF NOT EXISTS `game_event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_of_event` DATE NULL,
  `max_number_of_guests` INT NULL,
  `host_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  `enabled` TINYINT NULL,
  `start_time` TIME NULL,
  `end_time` TIME NULL,
  `image_url` VARCHAR(500) NULL,
  `description` TEXT NULL,
  `title` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_user_idx` (`host_id` ASC),
  INDEX `fk_event_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_event_user`
    FOREIGN KEY (`host_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NULL,
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `post_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_event1_idx` (`event_id` ASC),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `game_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `genre` ;

CREATE TABLE IF NOT EXISTS `genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review` ;

CREATE TABLE IF NOT EXISTS `review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NULL,
  `event_id` INT NOT NULL,
  `review_date` DATE NULL,
  `rating` INT NULL,
  `game_id` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_event1_idx` (`event_id` ASC),
  INDEX `fk_review_game1_idx` (`game_id` ASC),
  INDEX `fk_review_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_review_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `game_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_genre` ;

CREATE TABLE IF NOT EXISTS `game_genre` (
  `game_id` INT NOT NULL,
  `genre_id` INT NOT NULL,
  INDEX `fk_game_genre_game1_idx` (`game_id` ASC),
  INDEX `fk_game_genre_genre1_idx` (`genre_id` ASC),
  CONSTRAINT `fk_game_genre_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_genre_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `genre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friends` ;

CREATE TABLE IF NOT EXISTS `friends` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `friend_id`),
  INDEX `fk_user_has_user_user2_idx` (`friend_id` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`friend_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorite_venues`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_venues` ;

CREATE TABLE IF NOT EXISTS `favorite_venues` (
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `address_id`),
  INDEX `fk_user_has_address_address1_idx` (`address_id` ASC),
  INDEX `fk_user_has_address_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_address_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_address_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_event` ;

CREATE TABLE IF NOT EXISTS `user_has_event` (
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `event_id`),
  INDEX `fk_user_has_event_event1_idx` (`event_id` ASC),
  INDEX `fk_user_has_event_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_event_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `game_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_tags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_tags` ;

CREATE TABLE IF NOT EXISTS `event_tags` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_tags_has_game_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_tags_has_game_event` ;

CREATE TABLE IF NOT EXISTS `event_tags_has_game_event` (
  `event_tags_id` INT NOT NULL,
  `game_event_id` INT NOT NULL,
  PRIMARY KEY (`event_tags_id`, `game_event_id`),
  INDEX `fk_event_tags_has_game_event_game_event1_idx` (`game_event_id` ASC),
  INDEX `fk_event_tags_has_game_event_event_tags1_idx` (`event_tags_id` ASC),
  CONSTRAINT `fk_event_tags_has_game_event_event_tags1`
    FOREIGN KEY (`event_tags_id`)
    REFERENCES `event_tags` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_tags_has_game_event_game_event1`
    FOREIGN KEY (`game_event_id`)
    REFERENCES `game_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_event_has_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_event_has_game` ;

CREATE TABLE IF NOT EXISTS `game_event_has_game` (
  `game_event_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`game_event_id`, `game_id`),
  INDEX `fk_game_event_has_game_game1_idx` (`game_id` ASC),
  INDEX `fk_game_event_has_game_game_event1_idx` (`game_event_id` ASC),
  CONSTRAINT `fk_game_event_has_game_game_event1`
    FOREIGN KEY (`game_event_id`)
    REFERENCES `game_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_event_has_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_game` ;

CREATE TABLE IF NOT EXISTS `user_has_game` (
  `user_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `game_id`),
  INDEX `fk_user_has_game_game1_idx` (`game_id` ASC),
  INDEX `fk_user_has_game_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_game_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS rollthediceuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'rollthediceuser'@'localhost' IDENTIFIED BY 'rollthediceuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'rollthediceuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `rollthedicedb`;
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `profile_picture_url`, `enabled`, `role`, `address_id`, `biography`) VALUES (1, 'admin', '$2a$10$zHd.Ca6BmYwL3sO0oJm79uYjVH4z1RcDlRXcbyVsdfdhF5S6V.YCi', 'Lavender', 'Cupcake', NULL, NULL, 1, 'ROLE_ADMIN', NULL, NULL);

COMMIT;


-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema snitchdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `snitchdb` ;

-- -----------------------------------------------------
-- Schema snitchdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `snitchdb` DEFAULT CHARACTER SET utf8 ;
USE `snitchdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `role` ENUM('ADMIN', 'USER') NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `picture_url` TEXT NULL,
  `create_date` DATETIME NULL,
  `usercol` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pin_name` TEXT NULL,
  `street` VARCHAR(100) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `snitch`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snitch` ;

CREATE TABLE IF NOT EXISTS `snitch` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `create_date` DATETIME NOT NULL,
  `img_url` TEXT NULL,
  `enabled` TINYINT NULL,
  `resolved` TINYINT NULL,
  `resolution_date` DATETIME NULL,
  `resolution` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_snitch_inmate_idx` (`user_id` ASC),
  INDEX `fk_snitch_address1_idx` (`address_id` ASC),
  INDEX `fk_snitch_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_snitch_inmate`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_snitch_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_snitch_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `snitch_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `content` TEXT NOT NULL,
  `create_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_snitch1_idx` (`snitch_id` ASC),
  INDEX `fk_comment_inmate1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_snitch1`
    FOREIGN KEY (`snitch_id`)
    REFERENCES `snitch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_inmate1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `snitch_vote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snitch_vote` ;

CREATE TABLE IF NOT EXISTS `snitch_vote` (
  `user_id` INT NOT NULL,
  `snitch_id` INT NOT NULL,
  `vote` TINYINT NOT NULL,
  `create_time` DATETIME NULL,
  `note` TEXT NULL,
  PRIMARY KEY (`user_id`, `snitch_id`),
  INDEX `fk_inmate_has_snitch_snitch1_idx` (`snitch_id` ASC),
  INDEX `fk_inmate_has_snitch_inmate1_idx` (`user_id` ASC),
  CONSTRAINT `fk_inmate_has_snitch_inmate1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inmate_has_snitch_snitch1`
    FOREIGN KEY (`snitch_id`)
    REFERENCES `snitch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `alert` ;

CREATE TABLE IF NOT EXISTS `alert` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `subject` VARCHAR(100) NULL,
  `content` TEXT NULL,
  `created_at` DATETIME NULL,
  `expired` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_alert_inmate1_idx` (`user_id` ASC),
  INDEX `fk_alert_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_alert_inmate1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alert_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS snitchuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'snitchuser'@'localhost' IDENTIFIED BY 'snitchuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'snitchuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `role`, `enabled`, `picture_url`, `create_date`, `usercol`) VALUES (1, 'admin', 'admin@admin.com', '$2a$10$q5lOwLLyWMHyQc5muboyiO.UAtfnq1i63GiEjRTwb/qqmA1VmAAZS', NULL, NULL, 'ADMIN', 1, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `role`, `enabled`, `picture_url`, `create_date`, `usercol`) VALUES (2, 'user', 'u@u.com', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', NULL, NULL, 'USER', 1, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `role`, `enabled`, `picture_url`, `create_date`, `usercol`) VALUES (3, 'user2', 'u2@u2.com', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', NULL, NULL, 'USER', 1, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `address` (`id`, `pin_name`, `street`, `city`, `state`, `zip`) VALUES (1, 'Home Depot', '123 Main St', 'Parker', 'CO', 80138);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `category` (`id`, `name`, `description`) VALUES (1, 'Traffic', NULL);
INSERT INTO `category` (`id`, `name`, `description`) VALUES (2, 'Lost Animal', NULL);
INSERT INTO `category` (`id`, `name`, `description`) VALUES (3, 'Gigs', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `snitch`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `snitch` (`id`, `user_id`, `address_id`, `category_id`, `title`, `description`, `create_date`, `img_url`, `enabled`, `resolved`, `resolution_date`, `resolution`) VALUES (1, 2, 1, 1, 'Angry Yetis on Main St', 'There is a wild animal prowling around downtown, yelling System.exit() at everyone', '2020-04-05 10:22:10', NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `comment` (`id`, `snitch_id`, `user_id`, `content`, `create_date`) VALUES (1, 1, 3, 'Ya\'ll crazy!!!', '2020-05-05 11:22:22');

COMMIT;


-- -----------------------------------------------------
-- Data for table `alert`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`) VALUES (1, 1, 1, NULL, 'Alert! meow.', NULL, NULL);

COMMIT;


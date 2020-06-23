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
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `role` ENUM('ADMIN', 'USER') NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `create_date` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pin_name` VARCHAR(45) NULL,
  `street` VARCHAR(100) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `snitch`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snitch` ;

CREATE TABLE IF NOT EXISTS `snitch` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inmate_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` TEXT NOT NULL,
  `create_date` DATE NOT NULL,
  PRIMARY KEY (`id`, `address_id`),
  INDEX `fk_snitch_inmate_idx` (`inmate_id` ASC),
  INDEX `fk_snitch_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_snitch_inmate`
    FOREIGN KEY (`inmate_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_snitch_address1`
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
  `snitch_id` INT NOT NULL,
  `inmate_id` INT NOT NULL,
  `content` TEXT NOT NULL,
  `create_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_snitch1_idx` (`snitch_id` ASC),
  INDEX `fk_comment_inmate1_idx` (`inmate_id` ASC),
  CONSTRAINT `fk_comment_snitch1`
    FOREIGN KEY (`snitch_id`)
    REFERENCES `snitch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_inmate1`
    FOREIGN KEY (`inmate_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `snitch_vote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snitch_vote` ;

CREATE TABLE IF NOT EXISTS `snitch_vote` (
  `inmate_id` INT NOT NULL,
  `snitch_id` INT NOT NULL,
  `vote` TINYINT NOT NULL,
  PRIMARY KEY (`inmate_id`, `snitch_id`),
  INDEX `fk_inmate_has_snitch_snitch1_idx` (`snitch_id` ASC),
  INDEX `fk_inmate_has_snitch_inmate1_idx` (`inmate_id` ASC),
  CONSTRAINT `fk_inmate_has_snitch_inmate1`
    FOREIGN KEY (`inmate_id`)
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
-- Table `profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `profile` ;

CREATE TABLE IF NOT EXISTS `profile` (
  `allfieldsfrominmate` INT NOT NULL,
  PRIMARY KEY (`allfieldsfrominmate`))
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
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `role`, `enabled`, `create_date`) VALUES (1, 'admin', 'admin@admin.com', 'admin', 'admin', 'administrator', 'admin', DEFAULT, NULL);

COMMIT;


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
  `vote` TINYINT NOT NULL DEFAULT 1,
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
  `img_url` TEXT NULL,
  `zipcode` INT(5) NULL,
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
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `role`, `enabled`, `picture_url`, `create_date`) VALUES (1, 'admin', 'admin@admin.com', '$2a$10$q5lOwLLyWMHyQc5muboyiO.UAtfnq1i63GiEjRTwb/qqmA1VmAAZS', NULL, NULL, 'ADMIN', 1, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `role`, `enabled`, `picture_url`, `create_date`) VALUES (2, 'user', 'u@u.com', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', NULL, NULL, 'USER', 1, NULL, '2020-05-05 11:22:22');
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `role`, `enabled`, `picture_url`, `create_date`) VALUES (3, 'user2', 'u2@u2.com', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', NULL, NULL, 'USER', 1, NULL, '2020-05-05 11:22:22');
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `role`, `enabled`, `picture_url`, `create_date`) VALUES (4, 'vince', 'vince@vince.com', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'vince', 'b', 'USER', 1, 'https://www.vwlawfirm.com/content/uploads/posts/163/vincent-borden-participates-in-military-veterans.jpg', '2020-02-05 11:22:22');
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `role`, `enabled`, `picture_url`, `create_date`) VALUES (5, 'tabatha', 'tab@tab.com', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'tab', 'f', 'USER', 1, 'https://lh4.googleusercontent.com/-LgTtt5APi2Y/AAAAAAAAAAI/AAAAAAAAAEI/BYQNA3tVtCs/photo.jpg', '2020-02-05 11:22:22');

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `address` (`id`, `pin_name`, `street`, `city`, `state`, `zip`) VALUES (1, NULL, '123 Main St', 'Parker', 'CO', 80138);
INSERT INTO `address` (`id`, `pin_name`, `street`, `city`, `state`, `zip`) VALUES (2, 'Cherry Creek Trail', '343 Runner St', 'Denver', 'CO', 80014);
INSERT INTO `address` (`id`, `pin_name`, `street`, `city`, `state`, `zip`) VALUES (3, 'Community Center', '222 Dub St', 'Fort Collins', 'CO', 80521);
INSERT INTO `address` (`id`, `pin_name`, `street`, `city`, `state`, `zip`) VALUES (4, 'My House', '4 Robot St', 'Colorado Springs', 'CO', 80907);
INSERT INTO `address` (`id`, `pin_name`, `street`, `city`, `state`, `zip`) VALUES (5, 'Mile High Condos', '34 Borscht', 'Denver', 'CO', 80014);
INSERT INTO `address` (`id`, `pin_name`, `street`, `city`, `state`, `zip`) VALUES (6, 'VFW', '50 Freedom St', 'Fort Collins', 'CO', 80521);
INSERT INTO `address` (`id`, `pin_name`, `street`, `city`, `state`, `zip`) VALUES (7, 'Roundabout', '77 Westy Rd', 'Colorado Springs', 'CO', 80907);
INSERT INTO `address` (`id`, `pin_name`, `street`, `city`, `state`, `zip`) VALUES (8, 'All of Downtown Denver', '', 'Denver', 'CO', 80014);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `category` (`id`, `name`, `description`) VALUES (1, 'Traffic', NULL);
INSERT INTO `category` (`id`, `name`, `description`) VALUES (2, 'Lost Animal', NULL);
INSERT INTO `category` (`id`, `name`, `description`) VALUES (3, 'Gigs', NULL);
INSERT INTO `category` (`id`, `name`, `description`) VALUES (4, 'Public Works', 'Issues that the local governement should be aware of');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (5, 'Neighborhood', 'Issues within the community ');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (6, 'Events', 'What is going on in your area?');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (7, 'Services', 'A place to offer your skills');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (8, 'MISC', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `snitch`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `snitch` (`id`, `user_id`, `address_id`, `category_id`, `title`, `description`, `create_date`, `img_url`, `enabled`, `resolved`, `resolution_date`, `resolution`) VALUES (1, 2, 1, 1, 'Angry Yetis on Main St', 'There is a wild animal prowling around downtown, yelling System.exit() at everyone', '2020-04-05 10:22:10', NULL, 0, NULL, NULL, NULL);
INSERT INTO `snitch` (`id`, `user_id`, `address_id`, `category_id`, `title`, `description`, `create_date`, `img_url`, `enabled`, `resolved`, `resolution_date`, `resolution`) VALUES (2, 2, 2, 5, 'Running Trails overloaded!', 'I\'m a bigtime trailrunner. Lately the trails are so populated with slow people skylarking and milling about enjoying their day. I am so fast that i cant properly social distance. Does anyone else feel the same as me? something needs to be done about these walkers on my running trails!', '2020-04-06 11:39:00', 'https://images.unsplash.com/photo-1504025468847-0e438279542c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2035&q=80', 1, NULL, NULL, NULL);
INSERT INTO `snitch` (`id`, `user_id`, `address_id`, `category_id`, `title`, `description`, `create_date`, `img_url`, `enabled`, `resolved`, `resolution_date`, `resolution`) VALUES (3, 2, 3, 6, 'EDM Music Event interest?', 'Im a local EDM Wookie ISO local insomniac shufflers who have interest in creating our own local burn in the area! I can bring out my fire poi and vape pen. Like this to support or email me at SergioMatic@hotmail.com', '2020-04-06 11:39:00', 'https://images.unsplash.com/photo-1581974944026-5d6ed762f617?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 1, NULL, NULL, NULL);
INSERT INTO `snitch` (`id`, `user_id`, `address_id`, `category_id`, `title`, `description`, `create_date`, `img_url`, `enabled`, `resolved`, `resolution_date`, `resolution`) VALUES (4, 3, 4, 7, 'Online Presentation Skills', 'Is your job requiring you to shift to a remote working schedule? Do you panic when thinking about how to properly speak in a Zoom meeting, or fear what screens you are sharing? Hi! My name is Mike and I am locally famous for my remote presentation skills. I am willing to give free classes to better your skills during this shift to electronic meetings and presentations. ', '2020-06-01 12:40:00', 'https://images.unsplash.com/photo-1583508915901-b5f84c1dcde1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 1, NULL, NULL, NULL);
INSERT INTO `snitch` (`id`, `user_id`, `address_id`, `category_id`, `title`, `description`, `create_date`, `img_url`, `enabled`, `resolved`, `resolution_date`, `resolution`) VALUES (5, 3, 5, 5, 'Loud neighbors!', 'A very loud, possibly Ukranean family moved upstairs with their dog. They are always playing an accordion. Does anyone else hear them?', '2020-06-01 11:44:00', 'https://images.unsplash.com/photo-1563485572474-3392e2a0c3f7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 1, NULL, NULL, NULL);
INSERT INTO `snitch` (`id`, `user_id`, `address_id`, `category_id`, `title`, `description`, `create_date`, `img_url`, `enabled`, `resolved`, `resolution_date`, `resolution`) VALUES (6, 3, 6, 4, 'Large Pothole on Juniper St', 'There has been a large pot hole on Juniper St near the the VFW. It is getting much bigger and noone seems to care! It is very dangerous! We need to raise awareness about it!', '2020-06-01 12:40:00', 'https://images.unsplash.com/photo-1515162816999-a0c47dc192f7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 1, NULL, NULL, NULL);
INSERT INTO `snitch` (`id`, `user_id`, `address_id`, `category_id`, `title`, `description`, `create_date`, `img_url`, `enabled`, `resolved`, `resolution_date`, `resolution`) VALUES (7, 4, 7, 5, 'Abandoned Car?', 'There has been an old 84 VW Vanagon parked on the side of the road for the past week on Westy St. I dont know if anyone is in there but i think the owner may be stealing free internet and boondocking. ', '2020-04-01 12:05:00', 'https://images.unsplash.com/photo-1521366195717-179d65b0d9ef?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1952&q=80', 1, NULL, NULL, NULL);
INSERT INTO `snitch` (`id`, `user_id`, `address_id`, `category_id`, `title`, `description`, `create_date`, `img_url`, `enabled`, `resolved`, `resolution_date`, `resolution`) VALUES (8, 4, 8, 4, 'Sidewalk is being uprooted!', 'All the sidewalks downtown are becoming uneven! Definitely a tripping hazard! The town really needs to put more effort into this! Do you agree?', '2020-05-08 10:11:00', 'https://images.unsplash.com/photo-1586352343684-e9e42c549c03?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1932&q=80', 1, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `comment` (`id`, `snitch_id`, `user_id`, `content`, `create_date`) VALUES (1, 1, 3, 'Ya\'ll crazy!!!', '2020-05-05 11:22:22');
INSERT INTO `comment` (`id`, `snitch_id`, `user_id`, `content`, `create_date`) VALUES (2, 4, 4, 'Hey im a pretty loud and boisterous person and would love to see what you know!', '2020-06-01 12:00:00');
INSERT INTO `comment` (`id`, `snitch_id`, `user_id`, `content`, `create_date`) VALUES (3, 4, 5, 'SAME AS YOU VINCE!!!!! I am interested too!', '2020-06-01 12:02:00');
INSERT INTO `comment` (`id`, `snitch_id`, `user_id`, `content`, `create_date`) VALUES (4, 6, 5, 'I have seen this pothole! it must be 5 feet deep because when i fell it, i could not climb out!', '2020-06-01 12:05:00');
INSERT INTO `comment` (`id`, `snitch_id`, `user_id`, `content`, `create_date`) VALUES (5, 6, 4, 'That is super unsafe! We do need to tell the town about this!', '2020-06-01 12:06:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `snitch_vote`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `snitch_vote` (`user_id`, `snitch_id`, `vote`, `create_time`, `note`) VALUES (2, 1, 1, '2020-04-05 10:22:10', 'I like this!');
INSERT INTO `snitch_vote` (`user_id`, `snitch_id`, `vote`, `create_time`, `note`) VALUES (3, 1, 1, '2020-04-05 10:22:20', 'ehh.....');

COMMIT;


-- -----------------------------------------------------
-- Data for table `alert`
-- -----------------------------------------------------
START TRANSACTION;
USE `snitchdb`;
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (1, 1, 1, 'Construction on I-25', 'Slow moving traffic between Monument and Castle rock causing delays in commute.', '2020-06-01 10:22:10', NULL, 'https://images.unsplash.com/photo-1415594445260-63e18261587e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 80014);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (2, 1, 2, 'Missing Dog', 'Beagle, Dachshund mix, 3 years old, responds to \"Toni\" If found, call 555-555-5555', '2020-06-02 10:22:10', NULL, 'https://images.unsplash.com/photo-1561037404-61cd46aa615b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 80014);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (3, 1, 3, 'Job Alert', 'Homeowner ISO able-bodied person to clear snow from sidewalk in front of house', '2020-06-03 10:22:10', NULL, 'https://images.unsplash.com/photo-1456834292851-cd2d74fb491e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2689&q=80', 80014);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (4, 1, 1, 'Accident on I-70', 'Accident reported on I-70 near Golden exit. Use caution when approaching.', '2020-06-04 10:22:10', NULL, 'https://images.unsplash.com/photo-1575987446487-56eba08666cf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 80907);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (5, 1, 2, 'Missing Cat', 'White body with brown and white striped tail. Named Darin but to nothing. Very sarcastic cat. If found... keep him.', '2020-06-05 10:22:10', NULL, 'https://images.unsplash.com/photo-1530391277665-49655f7b0b90?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 80907);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (6, 1, 3, 'Medic in Your Area Looking For Work', 'Prior Combat Medic Veteran in search of employment as a nurse, EMT or other medical response jobs', '2020-06-06 10:22:10', NULL, 'https://www.ncaa.com/sites/default/files/public/styles/large_16x9/public-s3/media/ucf-military-fb-110816.jpg?itok=ynyQvaUa', 80907);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (7, 1, 1, 'Traffic Light Outage ', 'Traffic Light outge reported on Duncan St and Borden Dr', '2020-06-07 10:22:10', NULL, 'https://images.unsplash.com/photo-1588482674530-ef4a81db0cad?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 80521);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (8, 1, 2, 'Missing Horse', 'Majestic Mustang named \"Jacob\" missing for past week. If found send screenshot via Slack to SD26 homework channel.', '2020-06-08 10:22:10', NULL, 'https://images.unsplash.com/flagged/photo-1557296126-ae91316e5746?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 89521);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (9, 1, 3, 'Motivational Speaking Event', 'Motivational Speaking and Healthy Living Seminar held at Community center featuring Jeff Lopez', '2020-06-09 10:22:10', NULL, 'https://images.unsplash.com/photo-1528605105345-5344ea20e269?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', 80521);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (10, 1, 1, 'Flooding Reported', 'Flooding reported in Denver Area along Cherry Creek. Avoid low areas and when possible. ', '2020-06-10 10:22:10', NULL, 'https://images.unsplash.com/photo-1541770188093-e6d1c87a59b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1900&q=80', 80014);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (11, 1, 2, 'Missing Parrot', 'Green Amazon parrot escaped from home. Name is \"MD\" and babbles on about cooking knives and the Pacific North West. If found be sure to feed only gourmet cuisine.', '2020-06-11 10:22:10', NULL, 'https://images.unsplash.com/flagged/photo-1557296126-ae91316e5746?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80', NULL);
INSERT INTO `alert` (`id`, `user_id`, `category_id`, `subject`, `content`, `created_at`, `expired`, `img_url`, `zipcode`) VALUES (12, 1, 3, 'Community Donation', 'Looking for assistance and financial donations to GoFundMe for the \"ShaneNeedsBetterInternet\" Campaign', '2020-06-12 10:22:10', NULL, 'https://images.unsplash.com/photo-1574607383476-f517f260d30b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1867&q=80', NULL);

COMMIT;


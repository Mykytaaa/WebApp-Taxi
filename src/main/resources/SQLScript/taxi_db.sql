-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema taxi_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema taxi_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taxi_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `taxi_db` ;

-- -----------------------------------------------------
-- Table `taxi_db`.`t_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi_db`.`t_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `time_of_order` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `number_of_passengers` TINYINT NOT NULL,
  `price` DOUBLE NOT NULL,
  `departure` VARCHAR(255) NOT NULL,
  `arrival` VARCHAR(255) NOT NULL,
  `distance` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `t_order_id_uindex` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `taxi_db`.`taxi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi_db`.`taxi` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` ENUM('available', 'not_available', 'on_trip') NOT NULL,
  `capacity` INT NOT NULL,
  `car_type` ENUM('sedan', 'minivan') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `taxi_id_uindex` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `taxi_db`.`check_car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi_db`.`check_car` (
  `order_id` BIGINT NOT NULL,
  `taxi_id` BIGINT NOT NULL,
  INDEX `check_car_t_order_id_fk` (`order_id` ASC) VISIBLE,
  INDEX `check_car_taxi_id_fk` (`taxi_id` ASC) VISIBLE,
  CONSTRAINT `check_car_t_order_id_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `taxi_db`.`t_order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `check_car_taxi_id_fk`
    FOREIGN KEY (`taxi_id`)
    REFERENCES `taxi_db`.`taxi` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `taxi_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi_db`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  `role` ENUM('user', 'admin') NOT NULL,
  `status` ENUM('active', 'not_active') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_email_uindex` (`email` ASC) VISIBLE,
  UNIQUE INDEX `user_id_uindex` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `taxi_db`.`user_has_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi_db`.`user_has_order` (
  `user_id` BIGINT NOT NULL,
  `order_id` BIGINT NOT NULL,
  INDEX `user_has_order_t_order_id_fk` (`order_id` ASC) VISIBLE,
  INDEX `user_has_order_user_id_fk` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_has_order_t_order_id_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `taxi_db`.`t_order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_has_order_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `taxi_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

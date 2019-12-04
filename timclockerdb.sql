-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema timeclocker
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema timeclocker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `timeclocker` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema timeclocker
-- -----------------------------------------------------
USE `timeclocker` ;

-- -----------------------------------------------------
-- Table `timeclocker`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeclocker`.`role` (
  `RoleId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(20) NULL,
  PRIMARY KEY (`RoleId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `timeclocker`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeclocker`.`user` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `F_name` VARCHAR(45) NULL,
  `L_name` VARCHAR(45) NULL,
  `Username` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  `Title` VARCHAR(45) NULL,
  `RoleId` INT NULL,
  PRIMARY KEY (`UserId`),
  INDEX `fk_user_role_idx` (`RoleId` ASC) VISIBLE,
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`RoleId`)
    REFERENCES `timeclocker`.`role` (`RoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `timeclocker`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeclocker`.`status` (
  `StatusId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(20) NULL,
  PRIMARY KEY (`StatusId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `timeclocker`.`Timesheet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeclocker`.`Timesheet` (
  `TimesheetId` INT NOT NULL AUTO_INCREMENT,
  `Monday` FLOAT NULL DEFAULT 0.0,
  `Tuesday` FLOAT NULL DEFAULT 0.0,
  `Wednesday` FLOAT NULL DEFAULT 0.0,
  `Thursday` FLOAT NULL DEFAULT 0.0,
  `Friday` FLOAT NULL DEFAULT 0.0,
  `Saturday` FLOAT NULL DEFAULT 0.0,
  `Sunday` FLOAT NULL DEFAULT 0.0,
  `WeekEndDate` DATE NULL,
  `StatusId` INT NULL,
  `UserId` INT NULL,
  PRIMARY KEY (`TimesheetId`),
  INDEX `fk_Timesheet_status1_idx` (`StatusId` ASC) VISIBLE,
  INDEX `fk_Timesheet_user1_idx` (`UserId` ASC) VISIBLE,
  CONSTRAINT `fk_Timesheet_status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `timeclocker`.`status` (`StatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Timesheet_user1`
    FOREIGN KEY (`UserId`)
    REFERENCES `timeclocker`.`user` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;






-- End of initialization script

-- -----------------------------------------------------
-- Data for table `timeclocker`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `timeclocker`;
INSERT INTO `timeclocker`.`role` (`RoleId`, `Name`) VALUES (1, 'Employee');
INSERT INTO `timeclocker`.`role` (`RoleId`, `Name`) VALUES (2, 'Manager');

COMMIT;


-- -----------------------------------------------------
-- Data for table `timeclocker`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `timeclocker`;
INSERT INTO `timeclocker`.`user` (`UserId`, `F_name`, `L_name`, `Username`, `Password`, `Title`, `RoleId`) VALUES (1, 'Marc', 'Marti', 'marc.marti', 'password', 'Java Developer', 1);
INSERT INTO `timeclocker`.`user` (`UserId`, `F_name`, `L_name`, `Username`, `Password`, `Title`, `RoleId`) VALUES (2, 'Patrick', 'Walsh', 'patrick.walsh', 'password', 'Instructor Manager', 2);
INSERT INTO `timeclocker`.`user` (`UserId`, `F_name`, `L_name`, `Username`, `Password`, `Title`, `RoleId`) VALUES (3, 'Bob', 'Smith', 'bob.smith', 'password', 'Sales', 1);
INSERT INTO `timeclocker`.`user` (`UserId`, `F_name`, `L_name`, `Username`, `Password`, `Title`, `RoleId`) VALUES (4, 'Sally', 'Johnson', 'sally.johnson', 'password', 'Sales Manager', 2);
INSERT INTO `timeclocker`.`user` (`UserId`, `F_name`, `L_name`, `Username`, `Password`, `Title`, `RoleId`) VALUES (5, 'John', 'Doe', 'john.doe', 'password', 'Web Developer', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `timeclocker`.`status`
-- -----------------------------------------------------
START TRANSACTION;
USE `timeclocker`;
INSERT INTO `timeclocker`.`status` (`StatusId`, `Name`) VALUES (1, 'saved');
INSERT INTO `timeclocker`.`status` (`StatusId`, `Name`) VALUES (2, 'submitted');
INSERT INTO `timeclocker`.`status` (`StatusId`, `Name`) VALUES (3, 'approved');
INSERT INTO `timeclocker`.`status` (`StatusId`, `Name`) VALUES (4, 'denied');
COMMIT;


-- -----------------------------------------------------
-- Data for table `timeclocker`.`Timesheet`
-- -----------------------------------------------------
START TRANSACTION;
USE `timeclocker`;
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (1, 8, 8, 8, 8, 8, 0, 0, '2019-11-16', 1, 1);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (2, 8, 8, 8, 8, 8, 0, 8, '2019-11-09', 2, 1);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (3, 8, 8, 8, 8, 8, 8, 0, '2019-11-02', 2, 1);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (4, 5.5, 8, 8, 4.25, 8, 0, 0, '2019-10-26', 2, 1);

INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (5, 8, 8, 8, 8, 8, 0, 0, '2019-11-16', 1, 2);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (6, 8, 8, 8, 8, 8, 0, 8, '2019-11-09', 2, 2);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (7, 8, 8, 8, 8, 8, 8, 0, '2019-11-02', 2, 2);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (8, 5.5, 8, 8, 4.25, 8, 0, 0, '2019-10-26', 2, 2);

INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (9, 8, 8, 8, 8, 8, 0, 0, '2019-11-16', 1, 3);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (10, 8, 8, 8, 8, 8, 0, 8, '2019-11-09', 2, 3);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (11, 8, 8, 8, 8, 8, 8, 0, '2019-11-02', 2, 3);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (12, 5.5, 8, 8, 4.25, 8, 0, 0, '2019-10-26', 2, 3);

INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (13, 8, 8, 8, 8, 8, 0, 0, '2019-11-16', 1, 4);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (14, 8, 8, 8, 8, 8, 0, 8, '2019-11-09', 2, 4);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (15, 8, 8, 8, 8, 8, 8, 0, '2019-11-02', 2, 4);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (16, 5.5, 8, 8, 4.25, 8, 0, 0, '2019-10-26', 2, 4);

INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (17, 8, 8, 8, 8, 8, 0, 0, '2019-11-16', 1, 5);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (18, 8, 8, 8, 8, 8, 0, 8, '2019-11-09', 2, 5);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (55, 8, 8, 8, 8, 8, 8, 0, '2019-11-02', 3, 1);
INSERT INTO `timeclocker`.`Timesheet` (`TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId`) VALUES (60, 5.5, 8, 8, 4.25, 8, 0, 0, '2019-10-26', 4, 1);

COMMIT;
--
--select * from user where username = 'marc.marti' and password = 'password';
--select * from timesheet;
--
--select F_name, L_name, timesheetid, monday, tuesday, wednesday, thursday, friday, saturday, sunday, weekenddate, statusid
--from timesheet inner join user on timesheet.UserId = user.UserId inner join role on user.roleid = role.roleid where role.roleid = 1 order by user.userid asc, TimesheetId asc;
--
--select * from user;
--select * from role;
--select * from status;
--select * from timesheet where userid = 2;
--select * from role where roleid = 2;
--select * from timesheet where userid = 1;
--select * from timesheet;
--delete from timesheet where timesheetid = 21;
--
--INSERT INTO TIMESHEET values (null, 0, 0, 9, 0, 5, 5, 5, '2019-11-16', 1, 5);
--
--INSERT INTO role(name) VALUES('test');
--delete from role where roleid = 3;
--
--select f_name, l_name, timesheetid, monday, tuesday, wednesday, thursday, friday, saturday, sunday, weekenddate, statusid from timesheet inner join user on timesheet.UserId = user.UserId 
--					inner join role on user.roleid = role.roleid 
--							where role.roleid = 1 order by user.userid asc, TimesheetId asc;
--
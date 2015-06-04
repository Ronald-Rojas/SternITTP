
/*
 * login into mysql username root by default password
 * should be blank (empty string) 
 */

CREATE DATABASE STERNITTS;

#create login table
CREATE TABLE `STERNITTS`.`login` (
  `userid` INT NOT NULL,
  `pass` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE INDEX `userid_UNIQUE` (`userid` ASC));
  
#create time record database
CREATE TABLE `STERNITTS`.`time_login` (
  `id` INT NOT NULL,
  `userid` VARCHAR(10) NOT NULL,
  `time_in` TIMESTAMP NULL,
  `time_out` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `userid_UNIQUE` (`id` ASC));
  
  /*
   *create users using mysql workbench or in terminal 
   *check code for tmp passwords to modify/use
   */
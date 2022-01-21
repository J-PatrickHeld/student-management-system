SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `instructor` VARCHAR(50) NOT NULL,
  
  PRIMARY KEY (`id`)
  
) engine=innodb;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `email` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`email`)
  
)ENGINE=innodb;

SET FOREIGN_KEY_CHECKS = 1;

CREATE USER student IDENTIFIED BY 'student';

GRANT ALL PRIVILEGES ON sms.* TO 'student';
FLUSH PRIVILEGES;
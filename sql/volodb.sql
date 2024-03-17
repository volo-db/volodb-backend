DROP DATABASE  IF EXISTS `volo_db`;
CREATE DATABASE `volo_db`;
USE `volo_db`;

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gender` INT NOT NULL,
  `lastname` VARCHAR(50) NOT NULL,
  `firstname` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `gender`;
CREATE TABLE `gender` (
  `id` INT  NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person` INT NOT NULL,
  `status` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `careof` VARCHAR(50),
  `country` INT NOT NULL,
  `state` VARCHAR(50),
  `street` VARCHAR(50) NOT NULL,
  `postal_code` VARCHAR(10) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person` INT NOT NULL,
  `type` INT NOT NULL,
  `val` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contact_type`;
CREATE TABLE `contact_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` VARCHAR(50) NOT NULL,
  `person` INT NOT NULL,
  `secret` VARCHAR(100) NOT NULL,
  `organisational_role` VARCHAR(50),
  PRIMARY KEY (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user_role_mapping`;
CREATE TABLE `user_role_mapping` (
  `user` VARCHAR(50) NOT NULL,
  `user_role` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `legal_guardian`;
CREATE TABLE `legal_guardian` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `volunteer`;
CREATE TABLE `volunteer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organisational_id` VARCHAR(50) NOT NULL,
  `person` INT NOT NULL,
  `created` DATETIME NOT NULL,
  `status` INT NOT NULL,
  `birthday` DATE,
  `birthplace` VARCHAR(50),
  `nationality` INT, 
  `social_insurance_number` VARCHAR(50),
  `health_insurance` INT,
  `tax_number` VARCHAR(50),
  `religion` INT,
  `bank_name` VARCHAR(50),
  `iban` VARCHAR(22),
  `bic` VARCHAR(11),
  `account_holder` VARCHAR(50),
  `level_of_school_edu` INT,
  `level_of_vocational_edu` INT,
  `ongoing_legal_proceedings` BOOLEAN,
  `avatar` TEXT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `editing_history`;
CREATE TABLE `editing_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `editing_type` INT NOT NULL,
  `timestamp` DATETIME NOT NULL,
  `user` VARCHAR(50) NOT NULL,
  `volunteer` INT NOT NULL,
  `field` VARCHAR(50) NOT NULL,
  `former_value` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `editing_type`;
CREATE TABLE `editing_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `volunteer_status`;
CREATE TABLE `volunteer_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `healt_insurance`;
CREATE TABLE `healt_insurance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `religion`;
CREATE TABLE `religion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `shorthand` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `school_edu`;
CREATE TABLE `school_edu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vocational_edu`;
CREATE TABLE `vocational_edu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `Contract`;
CREATE TABLE `Contract` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `timestamp` DATETIME NOT NULL,
  `volunteer` INT NOT NULL,
  `program` INT NOT NULL,
  `project` INT NOT NULL,
  `contact_person_of_project` INT,
  `start` DATE NOT NULL,
  `end` DATE NOT NULL,
  `visa_necessary` BOOLEAN NOT NULL,
  `incoming_volunteer` BOOLEAN NOT NULL,
  `salary` INT NOT NULL,
  `holiday` INT NOT NULL,
  `metadata` JSON,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contract_modification`;
CREATE TABLE `contract_modification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `timestamp` DATETIME NOT NULL,
  `status` INT NOT NULL,
  `contract` INT NOT NULL,
  `type` INT NOT NULL,
  `into_force_from` DATE NOT NULL,
  `former_val` JSON NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contract_modification_status`;
CREATE TABLE `contract_modification_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contract_modification_type`;
CREATE TABLE `contract_modification_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(250),
  `pocket_money` DOUBLE NOT NULL,
  `free_of_charge_service` DOUBLE NOT NULL,
  `food_allowance` DOUBLE NOT NULL,
  `free_of_charge_food` DOUBLE NOT NULL,
  `accommodation_allowance` DOUBLE NOT NULL,
  `free_of_charge_accommodation` DOUBLE NOT NULL,
  `insurance_contributions` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `shorthand` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `country`;
CREATE TABLE country (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(50) COMMENT 'Full country name.',
  `iso2` VARCHAR(2) NOT NULL COMMENT 'ISO 3166-1 alpha-2 code.',
  `iso3` VARCHAR(3) COMMENT 'ISO 3166-1 alpha-3 code.',
  `local_name` VARCHAR(50) COMMENT 'Local variation of the name.',
  `continent` ENUM('Africa', 'Antarctica', 'Asia', 'Europe', 'Oceania', 'North America', 'South America'),
  `nationality` VARCHAR(50) COMMENT 'Nationality in german language.'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COMMENT='Full list of countries.';

DROP TABLE IF EXISTS `health_insurance`;
CREATE TABLE health_insurance (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL COMMENT 'Full name of Insurance.',
  `web` VARCHAR(255) COMMENT 'Webside of Insurance.',
  `fee` DOUBLE NOT NULL COMMENT 'Zusatzbeitrag in Prozent',
  `additional_info` VARCHAR(512) COMMENT 'Geöffnet in'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COMMENT='Full list of german statutory health insurance companies.';

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organisational_id` VARCHAR(50),
  `name` VARCHAR(50) NOT NULL,
  `shorthand` VARCHAR(32) NOT NULL,
  `description` TEXT(1000),
  `capacity` INT NOT NULL,
  `careof` VARCHAR(50),
  `country` INT NOT NULL,
  `state` VARCHAR(50),
  `street` VARCHAR(50) NOT NULL,
  `postal_code` VARCHAR(10) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contact_person`;
CREATE TABLE `contact_person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person` INT NOT NULL,
  `project` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `project_role`;
CREATE TABLE `project_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `project_role_mapping`;
CREATE TABLE `project_role_mapping` (
  `contact_person` INT NOT NULL,
  `project_role` INT NOT NULL
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `volunteer_note`;
CREATE TABLE `volunteer_note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `timestamp` DATETIME NOT NULL,
  `volunteer` INT NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `note` TEXT NOT NULL,
  `user` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


ALTER TABLE `user` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `user_role_mapping` ADD FOREIGN KEY (`user`) REFERENCES `user` (`username`);

ALTER TABLE `user_role_mapping` ADD FOREIGN KEY (`user_role`) REFERENCES `user_role` (`id`);

ALTER TABLE `person` ADD FOREIGN KEY (`gender`) REFERENCES `gender` (`id`);

ALTER TABLE `address` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `address` ADD FOREIGN KEY (`country`) REFERENCES `country` (`id`);

ALTER TABLE `contact` ADD FOREIGN KEY (`type`) REFERENCES `contact_type` (`id`);

ALTER TABLE `contact` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `legal_guardian` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`status`) REFERENCES `volunteer_status` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`nationality`) REFERENCES `country` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`level_of_school_edu`) REFERENCES `school_edu` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`level_of_vocational_edu`) REFERENCES `vocational_edu` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`health_insurance`) REFERENCES `health_insurance` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`religion`) REFERENCES `religion` (`id`);

ALTER TABLE `editing_history` ADD FOREIGN KEY (`volunteer`) REFERENCES `volunteer` (`id`);

ALTER TABLE `editing_history` ADD FOREIGN KEY (`user`) REFERENCES `user` (`username`);

ALTER TABLE `editing_history` ADD FOREIGN KEY (`editing_type`) REFERENCES `editing_type` (`id`);

ALTER TABLE `contract` ADD FOREIGN KEY (`volunteer`) REFERENCES `volunteer` (`id`);

ALTER TABLE `contract` ADD FOREIGN KEY (`program`) REFERENCES `program` (`id`);

ALTER TABLE `contract` ADD FOREIGN KEY (`project`) REFERENCES `project` (`id`);

ALTER TABLE `contract` ADD FOREIGN KEY (`salary`) REFERENCES `salary` (`id`);

ALTER TABLE `contract` ADD FOREIGN KEY (`contact_person_of_project`) REFERENCES `contact_person` (`id`);

ALTER TABLE `contract_modification` ADD FOREIGN KEY (`type`) REFERENCES `contract_modification_type` (`id`);

ALTER TABLE `contract_modification` ADD FOREIGN KEY (`contract`) REFERENCES `contract` (`id`);

ALTER TABLE `contact_person` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `contact_person` ADD FOREIGN KEY (`project`) REFERENCES `project` (`id`);

ALTER TABLE `project_role_mapping` ADD FOREIGN KEY (`contact_person`) REFERENCES `contact_person` (`id`);

ALTER TABLE `project_role_mapping` ADD FOREIGN KEY (`project_role`) REFERENCES `project_role` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`country`) REFERENCES `country` (`id`);

ALTER TABLE `contract_modification` ADD FOREIGN KEY (`status`) REFERENCES `contract_modification_status` (`id`);

ALTER TABLE `volunteer_note` ADD FOREIGN KEY (`volunteer`) REFERENCES `volunteer` (`id`);

ALTER TABLE `volunteer_note` ADD FOREIGN KEY (`user`) REFERENCES `user` (`username`);
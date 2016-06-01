drop table if exists `contacts`;

CREATE TABLE `contactschema`.`contacts` (
  `id` BIGINT(64) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

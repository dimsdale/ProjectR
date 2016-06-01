CREATE SCHEMA `contactschema` ;

CREATE TABLE `contactschema`.`contacts` (
  `id` BIGINT(64) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `contactschema`.`contacts` (`id`, `name`) VALUES ('1', 'Dmitriy');
INSERT INTO `contactschema`.`contacts` (`id`, `name`) VALUES ('2', 'Aleksey');
INSERT INTO `contactschema`.`contacts` (`id`, `name`) VALUES ('3', 'Zina');
INSERT INTO `contactschema`.`contacts` (`id`, `name`) VALUES ('4', 'Stepan');
INSERT INTO `contactschema`.`contacts` (`id`, `name`) VALUES ('5', 'Artem');
INSERT INTO `contactschema`.`contacts` (`id`, `name`) VALUES ('6', 'Nataliya');
INSERT INTO `contactschema`.`contacts` (`id`, `name`) VALUES ('7', 'Mykhaylo');
INSERT INTO `contactschema`.`contacts` (`id`, `name`) VALUES ('8', 'Maxim');
INSERT INTO `contactschema`.`contacts` (`id`, `name`) VALUES ('9', 'Ivan');
INSERT INTO `contactschema`.`contacts` (`id`, `name`) VALUES ('10', 'Alyona');


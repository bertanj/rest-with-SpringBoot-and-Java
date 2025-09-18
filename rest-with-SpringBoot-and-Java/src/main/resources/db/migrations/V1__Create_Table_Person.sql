CREATE TABLE IF NOT EXISTS `person` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `first_name` varchar(80) NOT NULL,
                          `last_name` varchar(88) NOT NULL,
                           `phone_number` varchar(15),
                            `birth_date` date NOT NULL,
                          `address` varchar(150) NOT NULL,
                          `gender` varchar(6) NOT NULL,
                          PRIMARY KEY (`id`)
);
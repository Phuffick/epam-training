USE `hospital_db` ;

CREATE TABLE `hospital_db`.`patient` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `hospital_db`.`medical_card` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `admission_date` DATE NOT NULL,
  `discharge_date` DATE NULL,
  `patient_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_medical_card_patient_idx` (`patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_medical_card_patient`
    FOREIGN KEY (`patient_id`)
    REFERENCES `hospital_db`.`patient` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `hospital_db`.`user` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(128) UNIQUE NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `role` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `hospital_db`.`doctor` (
  `name` VARCHAR(128) NOT NULL,
  `id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_doctor_user_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_doctor_user`
    FOREIGN KEY (`id`)
    REFERENCES `hospital_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `hospital_db`.`diagnosis` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `date` DATE NOT NULL,
  `medical_card_id` BIGINT UNSIGNED NOT NULL,
  `doctor_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_diagnosis_medical_card_idx` (`medical_card_id` ASC) VISIBLE,
  INDEX `fk_diagnosis_doctor_idx` (`doctor_id` ASC) VISIBLE,
  CONSTRAINT `fk_diagnosis_medical_card`
    FOREIGN KEY (`medical_card_id`)
    REFERENCES `hospital_db`.`medical_card` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_diagnosis_doctor`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `hospital_db`.`doctor` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `hospital_db`.`procedure` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `required_count` SMALLINT UNSIGNED NOT NULL,
  `required_count_consumed` SMALLINT UNSIGNED NOT NULL,
  `date` DATE NOT NULL,
  `medical_card_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_procedure_medical_card_idx` (`medical_card_id` ASC) VISIBLE,
  CONSTRAINT `fk_procedure_medical_card`
    FOREIGN KEY (`medical_card_id`)
    REFERENCES `hospital_db`.`medical_card` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `hospital_db`.`surgery` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `planing_date` DATE NOT NULL,
  `is_done` BOOLEAN NOT NULL,
  `medical_card_id` BIGINT UNSIGNED NOT NULL,
  `doctor_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_surgery_medical_card_idx` (`medical_card_id` ASC) VISIBLE,
  INDEX `fk_surgery_doctor_idx` (`doctor_id` ASC) VISIBLE,
  CONSTRAINT `fk_surgery_medical_card`
    FOREIGN KEY (`medical_card_id`)
    REFERENCES `hospital_db`.`medical_card` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_surgery_doctor`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `hospital_db`.`doctor` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `hospital_db`.`medications` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `is_done` BOOLEAN NOT NULL,
  `required_count` SMALLINT UNSIGNED NOT NULL,
  `amount_per_day` SMALLINT UNSIGNED NOT NULL,
  `date` DATE NOT NULL,
  `medical_card_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_medications_medical_card_idx` (`medical_card_id` ASC) VISIBLE,
  CONSTRAINT `fk_medications_medical_card`
    FOREIGN KEY (`medical_card_id`)
    REFERENCES `hospital_db`.`medical_card` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

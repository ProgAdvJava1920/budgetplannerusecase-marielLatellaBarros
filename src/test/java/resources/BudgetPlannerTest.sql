
-- -----------------------------------------------------
-- Table `Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `IBAN` VARCHAR(34) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Label`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Label` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL DEFAULT NULL,
  `amount` FLOAT NULL DEFAULT NULL,
  `currency` VARCHAR(45) NULL DEFAULT NULL,
  `detail` VARCHAR(255) NULL DEFAULT NULL,
  `accountId` INT NOT NULL,
  `counterAccountId` INT NOT NULL,
  `labelId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Payment_Account_idx` (`accountId` ASC),
  INDEX `fk_Payment_Counter_Account_idx` (`counterAccountId` ASC),
  INDEX `fk_Payment_Label1_idx` (`labelId` ASC),
  CONSTRAINT `fk_Payment_Account_Id`
    FOREIGN KEY (`accountId`)
    REFERENCES `Account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Payment_CounterAccount_Id`
    FOREIGN KEY (`counterAccountId`)
    REFERENCES `Account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Payment_Label1`
    FOREIGN KEY (`labelId`)
    REFERENCES `Label` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

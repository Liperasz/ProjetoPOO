-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cliente` (
  `ID_Cliente` INT NOT NULL,
  `Nome_Cliente` VARCHAR(45) NULL,
  `Nascimento_Cliente` DATE NULL,
  `Email_Cliente` VARCHAR(45) NULL,
  `Sexo_Cliente` VARCHAR(45) NULL,
  `CPF_Cliente` VARCHAR(15) NULL,
  `Telefone_Cliente` VARCHAR(15) NULL,
  `Senha_Cliente` VARCHAR(60) NULL,
  PRIMARY KEY (`ID_Cliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Administrador` (
  `ID_Administrador` INT NOT NULL,
  `Nome_Administrador` VARCHAR(45) NULL,
  `Nascimento_Administrador` DATE NULL,
  `Email_Administrador` VARCHAR(45) NULL,
  `Sexo_Administrador` VARCHAR(45) NULL,
  `CPF_Administrador` VARCHAR(15) NULL,
  `Telefone_Administrador` VARCHAR(15) NULL,
  `Senha_Administrador` VARCHAR(60) NULL,
  PRIMARY KEY (`ID_Administrador`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Atendente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Atendente` (
  `ID_Atendente` INT NOT NULL,
  `Nome_Atendente` VARCHAR(45) NULL,
  `Nascimento_Atendente` DATE NULL,
  `Email_Atendente` VARCHAR(45) NULL,
  `Sexo_Atendente` VARCHAR(45) NULL,
  `CPF_Atendente` VARCHAR(15) NULL,
  `Telefone_Atendente` VARCHAR(15) NULL,
  `Senha_Atendente` VARCHAR(60) NULL,
  `Status_Atendente` VARCHAR(45) NULL,
  PRIMARY KEY (`ID_Atendente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ingrediente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ingrediente` (
  `ID_Ingrediente` INT NOT NULL,
  `Nome_Ingrediente` VARCHAR(45) NULL,
  PRIMARY KEY (`ID_Ingrediente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Estoque` (
  `ID_Estoque` INT NOT NULL,
  `Validade` DATE NULL,
  `Quantidade` INT NULL,
  `Lote` INT NULL,
  `Ingrediente_ID_Ingrediente` INT NOT NULL,
  PRIMARY KEY (`ID_Estoque`),
  INDEX `fk_Estoque_Ingrediente1_idx` (`Ingrediente_ID_Ingrediente` ASC) VISIBLE,
  CONSTRAINT `fk_Estoque_Ingrediente1`
    FOREIGN KEY (`Ingrediente_ID_Ingrediente`)
    REFERENCES `mydb`.`Ingrediente` (`ID_Ingrediente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Comida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Comida` (
  `ID_Comida` INT NOT NULL,
  `Preco` DOUBLE NULL,
  `Descricao` VARCHAR(150) NULL,
  PRIMARY KEY (`ID_Comida`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pedido` (
  `ID_Pedido` INT NOT NULL,
  `Valor` DOUBLE NULL,
  `Entregue` TINYINT NULL,
  `Horario_Pedido` DATETIME NULL,
  `Pago` TINYINT NULL,
  `Cliente_ID_Cliente` INT NOT NULL,
  `Atendente_ID_Atendente` INT NOT NULL,
  PRIMARY KEY (`ID_Pedido`),
  INDEX `fk_Pedido_Cliente_idx` (`Cliente_ID_Cliente` ASC) VISIBLE,
  INDEX `fk_Pedido_Atendente1_idx` (`Atendente_ID_Atendente` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Cliente`
    FOREIGN KEY (`Cliente_ID_Cliente`)
    REFERENCES `mydb`.`Cliente` (`ID_Cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Atendente1`
    FOREIGN KEY (`Atendente_ID_Atendente`)
    REFERENCES `mydb`.`Atendente` (`ID_Atendente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pedido_has_Comida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pedido_has_Comida` (
  `Pedido_ID_Pedido` INT NOT NULL,
  `Comida_ID_Comida` INT NOT NULL,
  `Quantidade` INT NULL,
  PRIMARY KEY (`Pedido_ID_Pedido`, `Comida_ID_Comida`),
  INDEX `fk_Pedido_has_Comida_Comida1_idx` (`Comida_ID_Comida` ASC) VISIBLE,
  INDEX `fk_Pedido_has_Comida_Pedido1_idx` (`Pedido_ID_Pedido` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_has_Comida_Pedido1`
    FOREIGN KEY (`Pedido_ID_Pedido`)
    REFERENCES `mydb`.`Pedido` (`ID_Pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_has_Comida_Comida1`
    FOREIGN KEY (`Comida_ID_Comida`)
    REFERENCES `mydb`.`Comida` (`ID_Comida`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Comida_has_Ingrediente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Comida_has_Ingrediente` (
  `Comida_ID_Comida` INT NOT NULL,
  `Ingrediente_ID_Ingrediente` INT NOT NULL,
  `Quantidade` INT NULL,
  PRIMARY KEY (`Comida_ID_Comida`, `Ingrediente_ID_Ingrediente`),
  INDEX `fk_Comida_has_Ingrediente_Ingrediente1_idx` (`Ingrediente_ID_Ingrediente` ASC) VISIBLE,
  INDEX `fk_Comida_has_Ingrediente_Comida1_idx` (`Comida_ID_Comida` ASC) VISIBLE,
  CONSTRAINT `fk_Comida_has_Ingrediente_Comida1`
    FOREIGN KEY (`Comida_ID_Comida`)
    REFERENCES `mydb`.`Comida` (`ID_Comida`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comida_has_Ingrediente_Ingrediente1`
    FOREIGN KEY (`Ingrediente_ID_Ingrediente`)
    REFERENCES `mydb`.`Ingrediente` (`ID_Ingrediente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Feedback` (
  `ID_Feedback` INT NOT NULL,
  `Feedback` VARCHAR(200) NULL,
  PRIMARY KEY (`ID_Feedback`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

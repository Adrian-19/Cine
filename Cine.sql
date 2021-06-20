-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Cine
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Cine
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Cine` DEFAULT CHARACTER SET utf8 ;
USE `Cine` ;

-- -----------------------------------------------------
-- Table `Cine`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Cine`.`Usuario` (
  `cedula` VARCHAR(45) NOT NULL,
  `clave` VARCHAR(45) NOT NULL,
  `tipo` INT NOT NULL,
  PRIMARY KEY (`cedula`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Cine`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Cine`.`Cliente` (
  `cedula` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `idUsuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cedula`),
  INDEX `fk_Cliente_Usuario_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `Cine`.`Usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Cine`.`Pelicula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Cine`.`Pelicula` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `precio` INT NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Cine`.`Sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Cine`.`Sala` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numeroSala` VARCHAR(45) NOT NULL,
  `cantidadFilas` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Cine`.`Proyeccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Cine`.`Proyeccion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` VARCHAR(45) NOT NULL,
  `hora` VARCHAR(20) NOT NULL,
  `idPelicula` INT NOT NULL,
  `idSala` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Proyeccion_Pelicula1_idx` (`idPelicula` ASC) VISIBLE,
  INDEX `fk_Proyeccion_Sala1_idx` (`idSala` ASC) VISIBLE,
  CONSTRAINT `fk_Proyeccion_Pelicula1`
    FOREIGN KEY (`idPelicula`)
    REFERENCES `Cine`.`Pelicula` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Proyeccion_Sala1`
    FOREIGN KEY (`idSala`)
    REFERENCES `Cine`.`Sala` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Cine`.`Compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Cine`.`Compra` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cedula` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `idCliente` VARCHAR(45) NOT NULL,
  `idProyeccion` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Compra_Cliente1_idx` (`idCliente` ASC) VISIBLE,
  INDEX `fk_Compra_Proyeccion1_idx` (`idProyeccion` ASC) VISIBLE,
  CONSTRAINT `fk_Compra_Cliente1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Cine`.`Cliente` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_Proyeccion1`
    FOREIGN KEY (`idProyeccion`)
    REFERENCES `Cine`.`Proyeccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Cine`.`Tiquete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Cine`.`Tiquete` (
  `numero` INT NOT NULL,
  `numeroButaca` INT NULL,
  `idCompra` INT NOT NULL,
  PRIMARY KEY (`numero`),
  INDEX `fk_Tiquete_Compra1_idx` (`idCompra` ASC) VISIBLE,
  CONSTRAINT `fk_Tiquete_Compra1`
    FOREIGN KEY (`idCompra`)
    REFERENCES `Cine`.`Compra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Cine`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Cine`.`Administrador` (
  `cedula` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `idUsuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cedula`),
  INDEX `fk_Administrador_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Administrador_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `Cine`.`Usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

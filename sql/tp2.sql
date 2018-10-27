-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tp2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tp2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tp2` DEFAULT CHARACTER SET utf8 ;
USE `tp2` ;

-- -----------------------------------------------------
-- Table `tp2`.`Provincia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`Provincia` (
  `idProvincia` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(60) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`idProvincia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`Localidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`Localidad` (
  `idLocalidad` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(60) CHARACTER SET 'utf8' NOT NULL,
  `Provincia_idProvincia` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`idLocalidad`, `Provincia_idProvincia`),
  INDEX `fk_Localidad_Provincia1_idx` (`Provincia_idProvincia` ASC),
  CONSTRAINT `fk_Localidad_Provincia1`
    FOREIGN KEY (`Provincia_idProvincia`)
    REFERENCES `tp2`.`Provincia` (`idProvincia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`Domicilio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`Domicilio` (
  `idDomicilio` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Calle` VARCHAR(60) CHARACTER SET 'utf8' NOT NULL,
  `AlturaDomicilio` SMALLINT UNSIGNED NULL,
  `Piso` TINYINT UNSIGNED NULL,
  `Departamento` TINYINT UNSIGNED NULL,
  `CodigoPostal` SMALLINT UNSIGNED NOT NULL,
  `Localidad_idLocalidad` INT UNSIGNED NOT NULL,
  `Localidad_Provincia_idProvincia` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`idDomicilio`, `Localidad_idLocalidad`, `Localidad_Provincia_idProvincia`),
  INDEX `fk_Domicilio_Localidad1_idx` (`Localidad_idLocalidad` ASC, `Localidad_Provincia_idProvincia` ASC),
  CONSTRAINT `fk_Domicilio_Localidad1`
    FOREIGN KEY (`Localidad_idLocalidad` , `Localidad_Provincia_idProvincia`)
    REFERENCES `tp2`.`Localidad` (`idLocalidad` , `Provincia_idProvincia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`Cliente` (
  `DNI` INT UNSIGNED NOT NULL,
  `Nombre` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `Apellido` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `Domicilio_idDomicilio` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`DNI`, `Domicilio_idDomicilio`),
  INDEX `fk_Cliente_Domicilio1_idx` (`Domicilio_idDomicilio` ASC),
  CONSTRAINT `fk_Cliente_Domicilio1`
    FOREIGN KEY (`Domicilio_idDomicilio`)
    REFERENCES `tp2`.`Domicilio` (`idDomicilio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`TipoTelefono`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`TipoTelefono` (
  `idTipoTelefono` TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `TipoDeTelefono` VARCHAR(30) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`idTipoTelefono`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`TipoTelefono_Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`TipoTelefono_Cliente` (
  `Tipo_Telefono_idTipo_Telefono` TINYINT UNSIGNED NOT NULL,
  `Cliente_DNI` INT UNSIGNED NOT NULL,
  `Telefono` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`Tipo_Telefono_idTipo_Telefono`, `Cliente_DNI`),
  INDEX `fk_Tipo_Telefono_has_Cliente_Cliente1_idx` (`Cliente_DNI` ASC),
  INDEX `fk_Tipo_Telefono_has_Cliente_Tipo_Telefono1_idx` (`Tipo_Telefono_idTipo_Telefono` ASC),
  CONSTRAINT `fk_Tipo_Telefono_has_Cliente_Tipo_Telefono1`
    FOREIGN KEY (`Tipo_Telefono_idTipo_Telefono`)
    REFERENCES `tp2`.`TipoTelefono` (`idTipoTelefono`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tipo_Telefono_has_Cliente_Cliente1`
    FOREIGN KEY (`Cliente_DNI`)
    REFERENCES `tp2`.`Cliente` (`DNI`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`UnidadDeMedida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`UnidadDeMedida` (
  `idUnidadDeMedida` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `TipoUnidadDeMedida` VARCHAR(20) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`idUnidadDeMedida`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`CategoriaProducto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`CategoriaProducto` (
  `idCategoriaProducto` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `CategoriaProducto` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`idCategoriaProducto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`Producto` (
  `Codigo` BIGINT UNSIGNED NOT NULL,
  `Nombre` VARCHAR(50) CHARACTER SET 'utf8' NULL,
  `Descripcion` TEXT CHARACTER SET 'utf8' NULL,
  `PrecioPorUnidad` DECIMAL(12,3) UNSIGNED NULL,
  `UnidadDeMedida_idUnidadDeMedida` SMALLINT UNSIGNED NOT NULL,
  `CategoriaProducto_idCategoriaProducto` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`Codigo`, `UnidadDeMedida_idUnidadDeMedida`, `CategoriaProducto_idCategoriaProducto`),
  INDEX `fk_Producto_UnidadDeMedida1_idx` (`UnidadDeMedida_idUnidadDeMedida` ASC),
  INDEX `fk_Producto_CategoriaProducto1_idx` (`CategoriaProducto_idCategoriaProducto` ASC),
  CONSTRAINT `fk_Producto_UnidadDeMedida1`
    FOREIGN KEY (`UnidadDeMedida_idUnidadDeMedida`)
    REFERENCES `tp2`.`UnidadDeMedida` (`idUnidadDeMedida`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_CategoriaProducto1`
    FOREIGN KEY (`CategoriaProducto_idCategoriaProducto`)
    REFERENCES `tp2`.`CategoriaProducto` (`idCategoriaProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`Fecha`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`Fecha` (
  `idFecha` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Dia` TINYINT UNSIGNED NOT NULL,
  `Mes` TINYINT UNSIGNED NOT NULL,
  `Anio` YEAR NOT NULL,
  `NombreDia` VARCHAR(10) CHARACTER SET 'utf8' NULL,
  PRIMARY KEY (`idFecha`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`FormaDePago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`FormaDePago` (
  `idFormaDePago` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `FormaDePago` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`idFormaDePago`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`EstadoPedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`EstadoPedido` (
  `idEstadoPedido` SMALLINT NOT NULL AUTO_INCREMENT,
  `EstadoPedido` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEstadoPedido`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`FranjaHoraria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`FranjaHoraria` (
  `idFranjaHoraria` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `FranjaHoraria` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`idFranjaHoraria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`Pedido` (
  `idPedido` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Hora` TIME NOT NULL,
  `ImporteTotal` DECIMAL(12,3) NOT NULL,
  `Cliente_DNI` INT UNSIGNED NOT NULL,
  `DomicilioEntrega_idDomicilio` INT UNSIGNED NOT NULL,
  `Fecha_idFecha` INT UNSIGNED NOT NULL,
  `FormaDePago_idFormaDePago` SMALLINT UNSIGNED NOT NULL,
  `EstadoPedido_idEstadoPedido` SMALLINT NOT NULL,
  `FranjaHoraria_idFranjaHoraria` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`idPedido`, `Cliente_DNI`, `DomicilioEntrega_idDomicilio`, `Fecha_idFecha`, `FormaDePago_idFormaDePago`, `EstadoPedido_idEstadoPedido`, `FranjaHoraria_idFranjaHoraria`),
  INDEX `fk_Pedido_Cliente1_idx` (`Cliente_DNI` ASC),
  INDEX `fk_Pedido_DomicilioEntrega1_idx` (`DomicilioEntrega_idDomicilio` ASC),
  INDEX `fk_Pedido_Fecha1_idx` (`Fecha_idFecha` ASC),
  INDEX `fk_Pedido_FormaDePago1_idx` (`FormaDePago_idFormaDePago` ASC),
  INDEX `fk_Pedido_EstadoPedido1_idx` (`EstadoPedido_idEstadoPedido` ASC),
  INDEX `fk_Pedido_FranjaHoraria1_idx` (`FranjaHoraria_idFranjaHoraria` ASC),
  CONSTRAINT `fk_Pedido_Cliente1`
    FOREIGN KEY (`Cliente_DNI`)
    REFERENCES `tp2`.`Cliente` (`DNI`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_DomicilioEntrega1`
    FOREIGN KEY (`DomicilioEntrega_idDomicilio`)
    REFERENCES `tp2`.`Domicilio` (`idDomicilio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Fecha1`
    FOREIGN KEY (`Fecha_idFecha`)
    REFERENCES `tp2`.`Fecha` (`idFecha`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_FormaDePago1`
    FOREIGN KEY (`FormaDePago_idFormaDePago`)
    REFERENCES `tp2`.`FormaDePago` (`idFormaDePago`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_EstadoPedido1`
    FOREIGN KEY (`EstadoPedido_idEstadoPedido`)
    REFERENCES `tp2`.`EstadoPedido` (`idEstadoPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_FranjaHoraria1`
    FOREIGN KEY (`FranjaHoraria_idFranjaHoraria`)
    REFERENCES `tp2`.`FranjaHoraria` (`idFranjaHoraria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp2`.`Producto_Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp2`.`Producto_Pedido` (
  `Producto_Codigo` BIGINT UNSIGNED NOT NULL,
  `Pedido_idPedido` BIGINT UNSIGNED NOT NULL,
  `PrecioProducto` DECIMAL(12,3) NOT NULL,
  `CantidadProducto` INT NOT NULL,
  PRIMARY KEY (`Producto_Codigo`, `Pedido_idPedido`),
  INDEX `fk_Producto_has_Pedido_Pedido1_idx` (`Pedido_idPedido` ASC),
  INDEX `fk_Producto_has_Pedido_Producto1_idx` (`Producto_Codigo` ASC),
  CONSTRAINT `fk_Producto_has_Pedido_Producto1`
    FOREIGN KEY (`Producto_Codigo`)
    REFERENCES `tp2`.`Producto` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_has_Pedido_Pedido1`
    FOREIGN KEY (`Pedido_idPedido`)
    REFERENCES `tp2`.`Pedido` (`idPedido`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Insertando Entidades en el Sistema
-- -----------------------------------------------------
USE tp2;
/*Provincias*/
INSERT INTO Provincia(Nombre)VALUES('Buenos Aires');
INSERT INTO Provincia(Nombre)VALUES('Catamarca');
INSERT INTO Provincia(Nombre)VALUES('Chaco');
INSERT INTO Provincia(Nombre)VALUES('Chubut');
INSERT INTO Provincia(Nombre)VALUES('Cordoba');
INSERT INTO Provincia(Nombre)VALUES('Corrientes');
INSERT INTO Provincia(Nombre)VALUES('Entre Rios');
INSERT INTO Provincia(Nombre)VALUES('Formosa');
INSERT INTO Provincia(Nombre)VALUES('Jujuy');
INSERT INTO Provincia(Nombre)VALUES('La Pampa');
INSERT INTO Provincia(Nombre)VALUES('La Rioja');
INSERT INTO Provincia(Nombre)VALUES('Mendoza');
INSERT INTO Provincia(Nombre)VALUES('Misiones');
INSERT INTO Provincia(Nombre)VALUES('Neuquen');
INSERT INTO Provincia(Nombre)VALUES('Rio Negro');
INSERT INTO Provincia(Nombre)VALUES('Salta');
INSERT INTO Provincia(Nombre)VALUES('San Juan');
INSERT INTO Provincia(Nombre)VALUES('San Luis');
INSERT INTO Provincia(Nombre)VALUES('Santa Cruz');
INSERT INTO Provincia(Nombre)VALUES('Santa Fe');
INSERT INTO Provincia(Nombre)VALUES('Santiago del Estero');
INSERT INTO Provincia(Nombre)VALUES('Tierra del Fuego');
INSERT INTO Provincia(Nombre)VALUES('Tucuman');
/*Tipos de Telefonos*/
INSERT INTO TipoTelefono(idTipoTelefono,TipoDeTelefono)VALUES(1,'Telefono Domicilio');
INSERT INTO TipoTelefono(idTipoTelefono,TipoDeTelefono)VALUES(2,'Telefono Movil');
/*Unidades de Medida*/
INSERT INTO UnidadDeMedida(idUnidadDeMedida,TipoUnidadDeMedida)VALUES(1,'Unidad');
INSERT INTO UnidadDeMedida(idUnidadDeMedida,TipoUnidadDeMedida)VALUES(2,'Kilo');
/*Categorias de Producto*/
INSERT INTO CategoriaProducto(CategoriaProducto) VALUES ('Alimentos Perecederos');
INSERT INTO CategoriaProducto(CategoriaProducto) VALUES ('Alimentos No Perecederos');
INSERT INTO CategoriaProducto(CategoriaProducto) VALUES ('Lacteos');
INSERT INTO CategoriaProducto(CategoriaProducto) VALUES ('Fiambres');
INSERT INTO CategoriaProducto(CategoriaProducto) VALUES ('Limpieza');
/*Franjas Horarias*/
INSERT INTO FranjaHoraria(idFranjaHoraria,FranjaHoraria)VALUES(1,'Mañana (9:00-12:00 hs.)');
INSERT INTO FranjaHoraria(idFranjaHoraria,FranjaHoraria)VALUES(2,'Tarde (14:00-17:00 hs.)');
/*Formas de Pago*/
INSERT INTO FormaDePago(idFormaDePago,FormaDePago)VALUES(1,'Efectivo');
INSERT INTO FormaDePago(idFormaDePago,FormaDePago)VALUES(2,'Tarjeta De Credito');
INSERT INTO FormaDePago(idFormaDePago,FormaDePago)VALUES(3,'Tarjeta De Debito');
/*Estados de Pedidos*/
INSERT INTO EstadoPedido (EstadoPedido) VALUES('En Edicion');
INSERT INTO EstadoPedido (EstadoPedido) VALUES('Ingresado');
INSERT INTO EstadoPedido (EstadoPedido) VALUES('Procesando');
INSERT INTO EstadoPedido (EstadoPedido) VALUES('Preparado');
INSERT INTO EstadoPedido (EstadoPedido) VALUES('En Viaje');
INSERT INTO EstadoPedido (EstadoPedido) VALUES('Entregado');

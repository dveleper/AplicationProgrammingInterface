CREATE DATABASE IF NOT EXISTS accounts;
USE accounts;

DROP TABLE IF EXISTS movimiento;
DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS persona;

--
-- Table structure for table cliente
--

CREATE TABLE cliente (
                         cliente_id varchar(36) NOT NULL,
                         contrasena varchar(10) NOT NULL,
                         estado varchar(10) NOT NULL,
                         identificacion varchar(20) NOT NULL,
                         nombre varchar(45) NOT NULL,
                         genero varchar(10) NOT NULL,
                         edad int NOT NULL,
                         direccion varchar(60) NOT NULL,
                         telefono varchar(20) NOT NULL,
                         PRIMARY KEY (cliente_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table cuenta
--

CREATE TABLE cuenta (
                        cuenta_id varchar(36) NOT NULL,
                        numero varchar(20) NOT NULL,
                        tipo varchar(10) NOT NULL,
                        saldo_inicial bigint NOT NULL DEFAULT '0',
                        estado varchar(10) NOT NULL DEFAULT 'true',
                        cliente_id varchar(36) DEFAULT NULL,
                        PRIMARY KEY (cuenta_id),
                        KEY cliente_id_fk_idx (cliente_id),
                        CONSTRAINT cliente_id_fk FOREIGN KEY (cliente_id) REFERENCES cliente (cliente_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table movimiento
--

CREATE TABLE movimiento (
                            movimiento_id varchar(36) NOT NULL,
                            fecha date NOT NULL,
                            tipo_movimiento varchar(15) NOT NULL,
                            valor bigint NOT NULL,
                            saldo bigint NOT NULL,
                            cuenta_id varchar(36) NOT NULL,
                            PRIMARY KEY (movimiento_id),
                            KEY cuenta_fk_idx (cuenta_id),
                            CONSTRAINT cuenta_fk FOREIGN KEY (cuenta_id) REFERENCES cuenta (cuenta_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



insert into accounts.cliente (cliente_id, contrasena, estado, identificacion, nombre, genero, edad, direccion, telefono)
values ("a2720191-1cc6-11eb-9a2c-107d1a24f935", "1234", "true", "123654", "Jose Lema", "masculino", 32, "Otavalo sn y principal", "098254785");

insert into accounts.cliente (cliente_id, contrasena, estado, identificacion, nombre, genero, edad, direccion, telefono)
values ("a2720191-1cc6-11eb-9a2c-107d1a24f936", "5678", "true", "123655", "Marianela Montalvo", "femenino", 30, "Amazonas y NNUU", "097548965");

insert into accounts.cliente (cliente_id, contrasena, estado, identificacion, nombre, genero, edad, direccion, telefono)
values ("a2720191-1cc6-11eb-9a2c-107d1a24f937", "1245", "true", "123656", "Juan Osorio", "masculino", 36, "13 junio y Equinoccial", "098874587");

insert into accounts.cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f955", "478758", "Ahorro", 2000, "true", "a2720191-1cc6-11eb-9a2c-107d1a24f935");

insert into accounts.cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f956", "225487", "Corriente", 100, "true", "a2720191-1cc6-11eb-9a2c-107d1a24f936");

insert into accounts.cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f957", "495878", "Ahorros", 0, "true", "a2720191-1cc6-11eb-9a2c-107d1a24f937");

insert into accounts.cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f958", "496825", "Ahorros", 540, "true", "a2720191-1cc6-11eb-9a2c-107d1a24f936");

insert into accounts.cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f959", "585545", "Corriente", 1000, "true", "a2720191-1cc6-11eb-9a2c-107d1a24f935");

insert into accounts.movimiento (movimiento_id, fecha, tipo_movimiento, valor, saldo, cuenta_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f956", SYSDATE(), "CREDITO", 200, 200, "a2720191-1cc6-22eb-9a2c-107d1a24f955");

commit;
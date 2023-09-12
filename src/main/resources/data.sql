insert into persona (identificacion, nombre, genero, edad, direccion, telefono)
values ("123654", "Jose Lema", "masculino", 32, "Otavalo sn y principal", "098254785");

insert into cliente (cliente_id, contrasena, estado, identificacion)
values ("a2720191-1cc6-11eb-9a2c-107d1a24f935", "1234", "true", "123654");

insert into persona (identificacion, nombre, genero, edad, direccion, telefono)
values ( "123655", "Marianela Montalvo", "femenino", 30, "Amazonas y NNUU", "097548965");

insert into cliente (cliente_id, contrasena, estado, identificacion)
values ("a2720191-1cc6-11eb-9a2c-107d1a24f936", "5678", "true", "123655");

insert into persona (identificacion, nombre, genero, edad, direccion, telefono)
values ( "123656", "Juan Osorio", "masculino", 36, "13 junio y Equinoccial", "098874587");

insert into cliente (cliente_id, contrasena, estado, identificacion)
values ("a2720191-1cc6-11eb-9a2c-107d1a24f937", "1245", "true", "123656");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f955", "478758", "Ahorro", 2000, "true", "123654");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f956", "225487", "Corriente", 100, "true", "123655");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f957", "495878", "Ahorros", 0, "true", "123656");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f958", "496825", "Ahorros", 540, "true", "123655");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f959", "585545", "Corriente", 1000, "true", "123654");

insert into movimiento (movimiento_id, fecha, tipo_movimiento, valor, saldo, cuenta_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f956", SYSDATE(), "DEBITO", -575, 2000, "a2720191-1cc6-22eb-9a2c-107d1a24f955");

update cuenta set saldo_inicial = 1425 where cuenta_id = "a2720191-1cc6-22eb-9a2c-107d1a24f955";
--
insert into movimiento (movimiento_id, fecha, tipo_movimiento, valor, saldo, cuenta_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f957", SYSDATE(), "CREDITO", 600, 100, "a2720191-1cc6-22eb-9a2c-107d1a24f956");

update cuenta set saldo_inicial = 700 where cuenta_id = "a2720191-1cc6-22eb-9a2c-107d1a24f956";
--
insert into movimiento (movimiento_id, fecha, tipo_movimiento, valor, saldo, cuenta_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f958", SYSDATE(), "CREDITO", 150, 0, "a2720191-1cc6-22eb-9a2c-107d1a24f957");

update cuenta set saldo_inicial = 150 where cuenta_id = "a2720191-1cc6-22eb-9a2c-107d1a24f957";
--
insert into movimiento (movimiento_id, fecha, tipo_movimiento, valor, saldo, cuenta_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f959", SYSDATE(), "DEBITO", -540, 540, "a2720191-1cc6-22eb-9a2c-107d1a24f958");

update cuenta set saldo_inicial = 0 where cuenta_id = "a2720191-1cc6-22eb-9a2c-107d1a24f958";
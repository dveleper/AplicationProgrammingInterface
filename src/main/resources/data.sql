insert into cliente (cliente_id, contrasena, estado, identificacion, nombre, genero, edad, direccion, telefono)
values ("a2720191-1cc6-11eb-9a2c-107d1a24f935", "1234", "true", "123654", "Jose Lema", "masculino", 32, "Otavalo sn y principal", "098254785");

insert into cliente (cliente_id, contrasena, estado, identificacion, nombre, genero, edad, direccion, telefono)
values ("a2720191-1cc6-11eb-9a2c-107d1a24f936", "5678", "true", "123654", "Marianela Montalvo", "femenino", 30, "Amazonas y NNUU", "097548965");

insert into cliente (cliente_id, contrasena, estado, identificacion, nombre, genero, edad, direccion, telefono)
values ("a2720191-1cc6-11eb-9a2c-107d1a24f937", "1245", "true", "123654", "Juan Osorio", "masculino", 36, "13 junio y Equinoccial", "098874587");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f955", "478758", "Ahorro", 2000, "true", "a2720191-1cc6-11eb-9a2c-107d1a24f935");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f956", "225487", "Corriente", 100, "true", "a2720191-1cc6-11eb-9a2c-107d1a24f936");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f957", "495878", "Ahorros", 0, "true", "a2720191-1cc6-11eb-9a2c-107d1a24f937");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f958", "496825", "Ahorros", 540, "true", "a2720191-1cc6-11eb-9a2c-107d1a24f936");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f959", "585545", "Corriente", 1000, "true", "a2720191-1cc6-11eb-9a2c-107d1a24f935");

insert into movimiento (movimiento_id, fecha, tipo_movimiento, valor, saldo, cuenta_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f956", SYSDATE(), "CREDITO", 200, 200, "a2720191-1cc6-22eb-9a2c-107d1a24f955");
insert into cliente (cliente_id, contrasena, estado, identificacion, nombre, genero, edad, direccion, telefono)
values ("a2720191-1cc6-11eb-9a2c-107d1a24f935", "123", "activo", "123654", "pedro paramo", "masculino", 32, "calle 170 cra 10", "55555");

insert into cuenta (cuenta_id, numero, tipo, saldo_inicial, estado, cliente_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f955", "51100003138", "AHORROS", 0, "activa", "a2720191-1cc6-11eb-9a2c-107d1a24f935");

insert into movimiento (movimiento_id, fecha, tipo_movimiento, valor, saldo, cuenta_id)
values ("a2720191-1cc6-22eb-9a2c-107d1a24f956", SYSDATE(), "CREDITO", 200, 200, "a2720191-1cc6-22eb-9a2c-107d1a24f955");
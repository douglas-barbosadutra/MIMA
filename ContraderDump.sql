insert into contrader.users (username, password, firstname, lastname, dateofbirth, fiscalcode, businessname, vat, municipality, post, city, address, telephone, role)
values ('user','user','Lello','Rossi','08/12/1978','123','','666','Vallo','111','Salerno','via Roma','123456','user');
insert into contrader.users (username, password, firstname, lastname, dateofbirth, fiscalcode, businessname, vat, municipality, post, city, address, telephone, role)
values ('admin', 'admin','Mario','Saponara','05/08/1985','123456','','222','Roccadaspide','84050','Salerno','via Fonte','0828943076','admin');

insert into contrader.gomme (model, manufacturer, price, width, height, diameter, weight, speed, season, typevehicle, quantity)
values ('Trelleborg-B50','Trelleborg',200.50,20,20,20,20,'M','Invernali','moto',20);
insert into contrader.gomme (model, manufacturer, price, width, height, diameter, weight, speed, season, typevehicle, quantity)
values ('Trelleborg-B60','Trelleborg',100.50,30,30,30,30,'M','Estivi','auto',30);
insert into contrader.gomme (model, manufacturer, price, width, height, diameter, weight, speed, season, typevehicle, quantity)
values ('Trelleborg-B70','Trelleborg',300.50,50,50,50,50,'W','Quattro Stagioni','commerciale',40);
insert into contrader.gomme (model, manufacturer, price, width, height, diameter, weight, speed, season, typevehicle, quantity)
values ('Pirelli Hard','Pirelli',50.50,60,60,60,60,'W','Invernali','commerciale',50);
insert into contrader.gomme (model, manufacturer, price, width, height, diameter, weight, speed, season, typevehicle, quantity)
values ('Pirelli Soft','Pirelli',150.50,30,30,30,30,'W','Invernali','moto',60);
insert into contrader.gomme (model, manufacturer, price, width, height, diameter, weight, speed, season, typevehicle, quantity)
values ('Pirelli Ultra-Soft','Pirelli',134.50,50,50,50,50,'M','Estivi','auto',70);

insert into contrader.vehicle (brand, model, fuel, version, capacity)
values ('Fiat','Punto','Diesel','Punto Evo','1.3 JTD');
insert into contrader.vehicle (brand, model, fuel, version, capacity)
values ('Bugatti','EB 110','Benzina','EB 110','3.5 360');
insert into contrader.vehicle (brand, model, fuel, version, capacity)
values ('Ferrari','California','Benzina','California','4.3 V8 460');
insert into contrader.vehicle (brand, model, fuel, version, capacity)
values ('Mercedes','Classe A','Diesel','A 160 CDI','1.6 CDI 160');
insert into contrader.vehicle (brand, model, fuel, version, capacity)
values ('Honda','Logo','Benzina','Logo','1.3 83');

insert into contrader.compatibility (idVehicle, idGomme)
values (1,2);
insert into contrader.compatibility (idVehicle, idGomme)
values (1,6);
insert into contrader.compatibility (idVehicle, idGomme)
values (2,6);
insert into contrader.compatibility (idVehicle, idGomme)
values (3,2);
insert into contrader.compatibility (idVehicle, idGomme)
values (4,6);
insert into contrader.compatibility (idVehicle, idGomme)
values (4,2);
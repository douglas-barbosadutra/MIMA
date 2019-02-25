drop database contrader;
create database contrader;

create table contrader.users(
  idUser int(20) NOT NULL AUTO_INCREMENT,
  username varchar(50),
  password varchar(50),
  firstname varchar(50),
  lastname varchar(50),
  dateofbirth varchar(50),
  fiscalcode varchar(50),
  businessname varchar(50),
  vat varchar(50),
  municipality varchar(50),
  post varchar(10),
  city varchar(10),
  address varchar(20),
  telephone varchar(20),
  role varchar(20),
  PRIMARY KEY (idUser)
);

create table contrader.gomme(
  idGomme int(20) NOT NULL AUTO_INCREMENT,
  model varchar(50),
  manufacturer varchar(50),
  price float,
  width float,
  height float,
  diameter float,
  weight float,
  speed varchar(3),
  season varchar(50),
  typeVehicle varchar(50),
  quantity varchar(10),
  PRIMARY KEY (idGomme)
);

create table contrader.vehicle(
  idVehicle int(20) NOT NULL AUTO_INCREMENT,
  brand varchar(50),
  model varchar(50),
  fuel varchar(20),
  version varchar(50),
  capacity varchar(50),
  PRIMARY KEY (`idVehicle`)
);

create table contrader.compatibility(
	idVehicle int(20),
	idGomme int(20),
	foreign key (idVehicle) references contrader.vehicle(idVehicle),
	foreign key (idGomme) references contrader.gomme(idGomme)
);
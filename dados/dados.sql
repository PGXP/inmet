DROP TABLE public.estacoes;
CREATE TABLE public.estacoes
(
  codigo integer,
  nome character varying(64),
  latitude double precision,
  longitude double precision,
  altitude integer,
  id serial,
  CONSTRAINT estacoes_pkey PRIMARY KEY (id)
);

DROP TABLE public.clima;
CREATE TABLE public.clima
(
  id serial,
  estacao integer,
  dia date,
  hora varchar(4),
  TempBulboSeco double precision,
  TempBulboUmido double precision,
  UmidadeRelativa double precision,
  PressaoAtmEstacao double precision,
  DirecaoVento double precision,
  VelocidadeVento double precision,
  Nebulosidade double precision,
  CONSTRAINT clima_pkey PRIMARY KEY (id)
);

\COPY public.estacoes (codigo, nome, latitude, longitude, altitude) FROM '/opt/dados/estacoes.csv' WITH CSV HEADER QUOTE AS '"';

-- psql -h 10.79.60.47 -p 5432 -U postgres -d geo -f /home/70744416353/NetBeansProjects/github/serieclima/dados/dados.sql
-- sudo -su postgres psql -h 10.79.60.47 -d geo -c "Copy (Select * From geocep) To STDOUT With CSV HEADER DELIMITER ',';" > geocep.csv


select count(*) from clima;
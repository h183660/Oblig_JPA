-- Skript for å opprette databasen og legge inn litt data
    -- Skjema = hello_jpa
        -- Tabell(er) = person 

-- MERK!!! DROP SCHEMA ... CASCADE sletter alt !!!
DROP SCHEMA IF EXISTS oblig_jpa CASCADE;

CREATE SCHEMA oblig_jpa;
SET search_path TO oblig_jpa;

CREATE TABLE Avdeling
(
    avdelingsid SERIAL UNIQUE NOT NULL ,
    navn VARCHAR(45) UNIQUE NOT NULL,
    sjef INTEGER NOT NULL,
    CONSTRAINT avdeling_pk PRIMARY KEY (avdelingsid)

);

CREATE TABLE Ansatt
(
    ansattID SERIAL UNIQUE NOT NULL,
    brukerNavn VARCHAR(4) UNIQUE NOT NULL,
    forNavn VARCHAR(45) NOT NULL,
    etterNavn VARCHAR(45) NOT NULL,
    ansattDato DATE NOT NULL,
    stilling VARCHAR(45) NOT NULL,
    manedslonn INTEGER NOT NULL,
    avdeling INTEGER NOT NULL,
    CONSTRAINT ansatt_pk PRIMARY KEY (ansattID)  ,
    CONSTRAINT ansatt_fk FOREIGN KEY (avdeling) REFERENCES Avdeling(avdelingsid)  ON DELETE RESTRICT
);

INSERT INTO Avdeling(avdelingsid, navn, sjef)
VALUES
       (1,'Ingeniør-fag', 1),
       (2,'Helse-Fag',4),
       (3,'Ledelse',3)
;


INSERT INTO Ansatt(brukernavn, forNavn, etterNavn, ansattDato, stilling, manedslonn, avdeling)
VALUES
       ('lpa', 'Lars-Petter', 'Helland', '2021-05-30', 'JPA-Lærer', 65000, 1),
       ('ga', 'Geir', 'Avheli', '2020-05-30', 'Matte-Lærer', 55000, 1),
       ('lge', 'Leif-Geir', 'Eile', '2021-02-26', 'Rektor', 70000, 3),
       ('aa', 'Ashley', 'Abbott', '2020-02-01', 'Helse-Lærer', 65000, 2),
       ('te', 'Todd', 'Edmonds', '2019-03-09', 'Proggramerings-Lærer', 50000, 1),
       ('ms', 'Marjorie', 'Sands', '2021-01-04', 'Psykologi-Lærer', 55000, 2),
       ('jw', 'Justin ', 'Williams', '2017-09-11', 'SQL-Lærer', 55000, 1),
       ('af', 'Amanda', 'Fulton', '2016-02-08', 'Veileder', 55000, 3),
       ('va', 'Van ', 'Amendola', '2018-02-09', 'Fysikk-Lærer', 55000, 1),
       ('mb', 'Matthew ', 'Boykin', '2020-09-20', 'Omsorgs-Lærer', 55000, 2)
;

ALTER TABLE Avdeling
ADD CONSTRAINT avdeling_fk FOREIGN KEY (sjef) REFERENCES ansatt(ansattid) ON DELETE RESTRICT
;


CREATE TABLE Prosjekt
(
    prosjektid SERIAL UNIQUE NOT NULL,
    navn VARCHAR(45),
    beskrivelse VARCHAR(200),
    CONSTRAINT prosjekt_pk PRIMARY KEY (prosjektid)
)
;

CREATE TABLE ProsjektDeltagelse
(
    prosjektid INTEGER,
    ansattid INTEGER,
    rolle VARCHAR(45),
    timer INTEGER,
CONSTRAINT prosjektdeltagelse_pk PRIMARY KEY (prosjektid, ansattid),
CONSTRAINT prosjektdeltagelse_prosjektid_fk FOREIGN KEY (prosjektid) REFERENCES prosjekt(prosjektid),
CONSTRAINT prosjektdeltagelse_ansattid_fl FOREIGN KEY (ansattid) REFERENCES ansatt(ansattid)
)
;

INSERT INTO Prosjekt(navn,beskrivelse)
VALUES
       ('Facebook 2', 'Programmere ny sosial medie app'),
       ('Mental Helse','Prosjekt fokusert på å forbrede mental helse i stuudentmiljøet.'),
       ('Newton5','Finne newtons femte lov')
;

INSERT INTO ProsjektDeltagelse (prosjektid, ansattid, rolle, timer)
VALUES
       (1,1,'Database Ansvarlig', 37),
       (1,2,'Prosjektleder', 261),
       (2,3,'Prosjektleder', 21),
       (2,4,'Psykolog-Assistent', 15),
       (1,5,'Fron-End ansvarlig', 91),
       (2,6,'Psykolog-Konsulent', 73),
       (1,7,'Database-Konsulent', 83),
       (2,8,'Veileder', 31),
       (3,9,'Newton reincarnated', 666),
       (2,10,'Psykisk hjelp når proggramererene blir stuck', 176)
;
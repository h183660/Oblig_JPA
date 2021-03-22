-- Skript for å opprette databasen og legge inn litt data
    -- Skjema = hello_jpa
        -- Tabell(er) = person 

-- MERK!!! DROP SCHEMA ... CASCADE sletter alt !!!
DROP SCHEMA IF EXISTS oblig_jpa CASCADE;

CREATE SCHEMA oblig_jpa;
SET search_path TO oblig_jpa;

CREATE TABLE ansatt
(
    ansattid SERIAL UNIQUE NOT NULL,
    brukernavn VARCHAR(4) UNIQUE NOT NULL,
    forNavn VARCHAR(45) NOT NULL,
    etterNavn VARCHAR(45) NOT NULL,
    ansattDato DATE NOT NULL,
    stilling VARCHAR(45) NOT NULL,
    monedsloon INTEGER NOT NULL,
    /*avdeling INTEGER NOT NULL,*/
    CONSTRAINT ansatt_pk PRIMARY KEY (ansattid)  /*,
    CONSTRAINT ansatt_fk FOREIGN KEY (avdeling) REFERENCES Avdeling(avdelingsid)*/
);
/*
CREATE TABLE avdeling
(
    avdelingsid SERIAL UNIQUE NOT NULL ,
    navn VARCHAR(45) NOT NULL,
    sjef INTEGER NOT NULL,
    CONSTRAINT avdeling_pk PRIMARY KEY (avdelingsid),
    CONSTRAINT avdeling_fk FOREIGN KEY (sjef) REFERENCES ansatt(ansattid)
);

CREATE TABLE prosjekt
(
    prosjektid SERIAL UNIQUE NOT NULL,
    navn VARCHAR(45),
    beskrivelse VARCHAR(200),
    CONSTRAINT prosjekt_pk PRIMARY KEY (prosjektid)
)

CREATE TABLE prosjektdeltagelse
(
    prosjektid INTEGER,
    ansattid INTEGER,
    rolle VARCHAR(45),
    timer INTEGER,
CONSTRAINT prosjektdeltagelse_pk PRIMARY KEY (prosjektid, ansattid)
)
*/

INSERT INTO ansatt(brukernavn, forNavn, etterNavn, ansattDato, stilling, monedsloon)
VALUES
       ('lpa', 'Lars-Petter', 'Helland', '2021-05-30', 'JPA-Lærer', 650000),
       ('ga', 'Geir', 'Avheli', '2020-05-30', 'Matte-Lærer', 550000),
       ('lge', 'Lars-Geir', 'Eile', '2021-02-26', 'Rektor', 700000);

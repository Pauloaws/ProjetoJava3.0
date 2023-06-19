CREATE DATABASE atelierDB;

\connect atelierDB;

CREATE TABLE produtos (
    Id SERIAL PRIMARY KEY,
    Nome VARCHAR(255),
    Preco NUMERIC(10, 2)
);
DROP TABLE IF EXISTS public."Asignaturas";

CREATE TABLE IF NOT EXISTS public."Asignaturas"
(
    nombre character varying(50) COLLATE pg_catalog."default",
    anyo integer,
    codigo SERIAL PRIMARY KEY
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Asignaturas"
    OWNER to postgres;

INSERT INTO "Asignaturas"(nombre, anyo) VALUES 

('ACCESO A DATOS', 2),
('ENTORNOS DE DESARROLLO', 1),
('SISTEMAS DE GESTIÓN DE BASES DE DATOS', 1),
('SERVICIOS Y PROCESOS', 2);
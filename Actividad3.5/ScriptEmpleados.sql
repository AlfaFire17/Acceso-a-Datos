-- Crea la tabla departamento
CREATE TABLE departamento (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  presupuesto DOUBLE PRECISION NOT NULL,
  gastos DOUBLE PRECISION NOT NULL
);

-- Crea la tabla empleado
CREATE TABLE empleado (
  id SERIAL PRIMARY KEY,
  nif VARCHAR(9) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  apellido1 VARCHAR(100) NOT NULL,
  apellido2 VARCHAR(100),
  id_departamento INT,
  FOREIGN KEY (id_departamento) REFERENCES departamento(id)
);
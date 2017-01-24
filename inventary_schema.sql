-- Tabla que va almacenar las UBICACIONES de todos los que participen en el
-- inventario
CREATE TABLE places (
    place_id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    place_object VARCHAR NOT NULL 
);

-- Tabla para representar una casa
CREATE TABLE homes (
    home_id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    home_name VARCHAR(100) NOT NULL,
    home_place_id INTEGER UNSIGNED  
);

-- Table para representar las habitaciones de una casa
CREATE TABLE rooms (
    room_id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    room_home_id INTEGER UNSIGNED,
    room_place_id INTEGER UNSIGNED,
    room_name VARCHAR(100)
);

-- Table que representa los objetos que tiene cualquier habitacion de una 
-- casa, almacena si ese objeto tiene padre y si tiene hijo que por defecto
-- tienes el valor 0 es decir no tienen cuando se crean por primera vez
CREATE TABLE elements (
    element_id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    element_room_id INTEGER UNSIGNED,
    element_level INTEGER UNSIGNED,
    element_parent INTEGER UNSIGNED DEFAULT 0,
    element_has_child TINYINT(1) NOT NULL DEFAULT 0
);

-- Esta tabla va almacenar la verdadera informacion de los elementos que tiene
-- toda habitacion
CREATE TABLE data (
    data_id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    data_place_id INTEGER UNSIGNED,
    data_element_id INTEGER UNSIGNED,
    data_name VARCHAR(100) NOT NULL,
    data_description VARCHAR(200) NOT NULL,
    data_storage DATE NOT NULL,
    data_expiration DATE NOT NULL
);

-- Tabla que representa a los usuarios del sistema.
-- column user_active -> todos los usuarios por defecto estan activos.
CREATE TABLE users (
    user_id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_nickname VARCHAR(20) NOT NULL UNIQUE,
    user_name VARCHAR(50) NOT NULL,
    user_lastname VARCHAR(100) NOT NULL,
    user_password VARCHAR(64) NOT NULL,
    user_active TINYINT(1) NOT NULL DEFAULT 1
);

-- Tabla para representar los diferentes tipos de usuarios en el sistema.
-- column profile_active -> todos los perfiles estan activos.
CREATE TABLE profiles (
    profile_id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    profile_name VARCHAR(20) NOT NULL, 
    profile_active TINYINT(1) NOT NULL DEFAULT 1
);

-- Tabla para los modulos del sistema, estos agrupan los roles.
CREATE TABLE modules (
    module_id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    module_name VARCHAR(20) NOT NULL,
    module_description TEXT
);

-- Tabla que representa los roles del sistema, estos estan agrupados en un modulo.
CREATE TABLE licenses (
    license_id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    module_id INTEGER UNSIGNED,
    license_action VARCHAR(10) NOT NULL,
    license_description TEXT
); 

CREATE TABLE profile_user(
    _id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    profile_id INTEGER UNSIGNED,
    user_id INTEGER UNSIGNED
); 

CREATE TABLE module_profile(
    _id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    profile_id INTEGER UNSIGNED,
    module_id INTEGER UNSIGNED
);

CREATE TABLE license_user(
    _id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    license_id INTEGER UNSIGNED,
    user_id INTEGER UNSIGNED
);

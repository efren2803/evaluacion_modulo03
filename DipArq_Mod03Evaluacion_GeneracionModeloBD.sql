CREATE SCHEMA `diparq_modulo03_evaluacion` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;

-----

USE `diparq_modulo03_evaluacion`;

-- Creación de la tabla Cotizacion (necesaria para integridad referencial)
CREATE TABLE IF NOT EXISTS Cotizacion (
    idCotizacion INT PRIMARY KEY AUTO_INCREMENT,
    fechaCreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cliente VARCHAR(255),  -- Campo sugerido para contexto adicional
    total DECIMAL(12,2) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Creación de la tabla Componente (incluye PCs como tipo especial)
CREATE TABLE IF NOT EXISTS Componente (
    idComponente INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    marca VARCHAR(100),
    modelo VARCHAR(100),
    costo DECIMAL(12,2) NOT NULL,      -- Aumenté la precisión para valores altos
    precioBase DECIMAL(12,2) NOT NULL,
    tipo VARCHAR(50) NOT NULL,         -- Campo obligatorio para diferenciar PCs
    memoriaTarjetaVideo VARCHAR(50),
    capacidadAlmacenamiento VARCHAR(50),
    sku VARCHAR(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla para detalles de cotización
CREATE TABLE IF NOT EXISTS DetalleCotizacion (
    idDetalleCotizacion INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    cantidad INT NOT NULL CHECK (cantidad > 0),
    importeCotizado DECIMAL(12,2) NOT NULL,
    precioBase DECIMAL(12,2) NOT NULL,
    idCotizacion INT NOT NULL,
    idComponente INT NOT NULL,
    sku VARCHAR(45),
    FOREIGN KEY (idCotizacion) REFERENCES Cotizacion(idCotizacion),
    FOREIGN KEY (idComponente) REFERENCES Componente(idComponente),
    INDEX idx_cotizacion (idCotizacion)  -- Optimización para búsquedas
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla para componentes dentro de una PC (usando idComponente como referencia)
CREATE TABLE IF NOT EXISTS ComponentePC (
    idComponentePc INT PRIMARY KEY AUTO_INCREMENT,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    idComponente INT NOT NULL,  -- Componente individual (ej: tarjeta gráfica)
    idPC INT NOT NULL,          -- Referencia al componente de tipo "PC"
    FOREIGN KEY (idComponente) REFERENCES Componente(idComponente),
    FOREIGN KEY (idPC) REFERENCES Componente(idComponente),
    UNIQUE KEY unique_ensamble (idPC, idComponente)  -- Evita duplicados
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
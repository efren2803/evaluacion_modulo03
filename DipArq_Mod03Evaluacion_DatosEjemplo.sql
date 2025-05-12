-- ------------------------------------------------------
-- Insertar 30 componentes de diferentes tipos
-- ------------------------------------------------------
INSERT INTO Componente (description, marca, modelo, costo, precioBase, tipo, memoriaTarjetaVideo, capacidadAlmacenamiento) VALUES
-- Monitores (5)
('Monitor Gaming 24"', 'ASUS', 'TUF VG249Q', 150.00, 299.99, 'Monitor', NULL, NULL),
('Monitor 4K 27"', 'LG', '27UN850-W', 300.00, 499.99, 'Monitor', NULL, NULL),
('Monitor Curvo 32"', 'Samsung', 'C32JG56', 200.00, 349.99, 'Monitor', NULL, NULL),
('Monitor Profesional 27"', 'Dell', 'UltraSharp U2722D', 250.00, 429.99, 'Monitor', NULL, NULL),
('Monitor Portátil 15.6"', 'AOC', 'E1659FWU', 75.00, 129.99, 'Monitor', NULL, NULL),

-- Tarjetas de Video (5)
('RTX 3080 Ti', 'NVIDIA', 'Founders Edition', 800.00, 1199.99, 'Tarjeta de video', '12GB GDDR6X', NULL),
('RX 6800 XT', 'AMD', 'Radeon', 650.00, 999.99, 'Tarjeta de video', '16GB GDDR6', NULL),
('GTX 1660 Super', 'MSI', 'Ventus XS', 200.00, 299.99, 'Tarjeta de video', '6GB GDDR5', NULL),
('RTX 4090', 'Gigabyte', 'AORUS Xtreme', 1500.00, 1999.99, 'Tarjeta de video', '24GB GDDR6X', NULL),
('Arc A770', 'Intel', 'Limited Edition', 300.00, 349.99, 'Tarjeta de video', '16GB GDDR6', NULL),

-- Discos Duros (4)
('SSD 1TB NVMe', 'Samsung', '970 EVO Plus', 80.00, 129.99, 'Disco duro', NULL, '1TB'),
('HDD 4TB', 'Seagate', 'BarraCuda', 60.00, 89.99, 'Disco duro', NULL, '4TB'),
('SSD 2TB SATA', 'Crucial', 'MX500', 120.00, 169.99, 'Disco duro', NULL, '2TB'),
('NVMe 500GB', 'WD', 'Black SN770', 50.00, 79.99, 'Disco duro', NULL, '500GB'),

-- PSUs (3)
('PSU 750W Gold', 'Corsair', 'RM750x', 90.00, 129.99, 'PSU', NULL, NULL),
('PSU 850W Platinum', 'EVGA', 'SuperNOVA P2', 130.00, 199.99, 'PSU', NULL, NULL),
('PSU 550W Bronze', 'Thermaltake', 'Smart BX1', 40.00, 59.99, 'PSU', NULL, NULL),

-- Procesadores (4)
('Ryzen 9 7950X', 'AMD', 'Ryzen 9', 450.00, 699.99, 'Procesador', NULL, NULL),
('Core i9-13900K', 'Intel', 'Core i9', 500.00, 749.99, 'Procesador', NULL, NULL),
('Ryzen 5 5600X', 'AMD', 'Ryzen 5', 150.00, 199.99, 'Procesador', NULL, NULL),
('Core i5-12400F', 'Intel', 'Core i5', 120.00, 149.99, 'Procesador', NULL, NULL),

-- Memorias RAM (4)
('RAM 32GB DDR5', 'G.Skill', 'Trident Z5', 120.00, 179.99, 'Memoria RAM', NULL, NULL),
('RAM 16GB DDR4', 'Corsair', 'Vengeance LPX', 50.00, 79.99, 'Memoria RAM', NULL, NULL),
('RAM 64GB DDR5', 'Kingston', 'Fury Beast', 250.00, 349.99, 'Memoria RAM', NULL, NULL),
('RAM 8GB DDR4', 'Crucial', 'Ballistix', 30.00, 49.99, 'Memoria RAM', NULL, NULL),

-- Tarjetas Madre (3)
('Motherboard Z790', 'ASUS', 'ROG Strix', 250.00, 399.99, 'Tarjeta Madre', NULL, NULL),
('Motherboard B550', 'MSI', 'Tomahawk', 120.00, 169.99, 'Tarjeta Madre', NULL, NULL),
('Motherboard H610', 'Gigabyte', 'UD AC', 80.00, 109.99, 'Tarjeta Madre', NULL, NULL),

-- Gabinetes (2)
('Gabinete Mid Tower', 'NZXT', 'H510', 70.00, 99.99, 'Gabinete', NULL, NULL),
('Gabinete Full Tower', 'Lian Li', 'PC-O11', 150.00, 229.99, 'Gabinete', NULL, NULL);

-- ------------------------------------------------------
-- Insertar 5 PCs (tipo "PC")
-- ------------------------------------------------------
INSERT INTO Componente (description, costo, precioBase, tipo) VALUES
('PC Gamer Extreme', 1200.00, 1999.99, 'PC'),
('PC Oficina', 400.00, 699.99, 'PC'),
('PC Streaming', 900.00, 1499.99, 'PC'),
('PC Workstation', 1800.00, 2999.99, 'PC'),
('PC Económica', 300.00, 499.99, 'PC');

-- ------------------------------------------------------
-- Asociar componentes a las PCs (7 componentes por PC)
-- ------------------------------------------------------
INSERT INTO ComponentePC (idPC, idComponente, cantidad) VALUES
-- PC Gamer Extreme (idComponente = 31)
(31, 6, 1),   -- RTX 3080 Ti
(31, 17, 1),  -- Ryzen 9 7950X
(31, 20, 2),  -- RAM 32GB DDR5
(31, 24, 1),  -- Motherboard Z790
(31, 10, 1),  -- SSD 1TB NVMe
(31, 13, 1),  -- PSU 750W Gold
(31, 29, 1),  -- Gabinete Full Tower

-- PC Oficina (idComponente = 32)
(32, 8, 1),   -- GTX 1660 Super
(32, 19, 1),  -- Core i5-12400F
(32, 21, 1),  -- RAM 16GB DDR4
(32, 25, 1),  -- Motherboard B550
(32, 12, 1),  -- HDD 4TB
(32, 15, 1),  -- PSU 550W Bronze
(32, 28, 1),  -- Gabinete Mid Tower

-- PC Streaming (idComponente = 33)
(33, 7, 1),   -- RX 6800 XT
(33, 18, 1),  -- Core i9-13900K
(33, 22, 4),  -- RAM 64GB DDR5
(33, 24, 1),  -- Motherboard Z790
(33, 9, 2),   -- NVMe 500GB (2 unidades)
(33, 14, 1),  -- PSU 850W Platinum
(33, 29, 1),  -- Gabinete Full Tower

-- PC Workstation (idComponente = 34)
(34, 10, 1),  -- RTX 4090
(34, 17, 1),  -- Ryzen 9 7950X
(34, 22, 4),  -- RAM 64GB DDR5
(34, 24, 1),  -- Motherboard Z790
(34, 11, 2),  -- SSD 2TB SATA (2 unidades)
(34, 14, 1),  -- PSU 850W Platinum
(34, 29, 1),  -- Gabinete Full Tower

-- PC Económica (idComponente = 35)
(35, 9, 1),   -- Arc A770
(35, 20, 1),  -- Ryzen 5 5600X
(35, 23, 1),  -- RAM 8GB DDR4
(35, 25, 1),  -- Motherboard B550
(35, 12, 1),  -- HDD 4TB
(35, 15, 1),  -- PSU 550W Bronze
(35, 28, 1);  -- Gabinete Mid Tower

-- ------------------------------------------------------
-- Generar 7 cotizaciones con 5 detalles cada una
-- ------------------------------------------------------
INSERT INTO Cotizacion (fechaCreacion, cliente) VALUES
(NOW(), 'Cliente Corporativo ABC'),
(NOW(), 'Tienda Tech Solutions'),
(NOW(), 'Universidad XYZ'),
(NOW(), 'Freelancer Design'),
(NOW(), 'Gamer Pro'),
(NOW(), 'Oficina Contable'),
(NOW(), 'Estudio de Edición');

-- Detalles de Cotización (5 items por cotización)
INSERT INTO DetalleCotizacion (idCotizacion, idComponente, cantidad, importeCotizado, precioBase) VALUES
-- Cotización 1
(1, 31, 1, 1999.99, 1999.99),  -- PC Gamer Extreme
(1, 2, 3, 1499.97, 499.99),     -- Monitores 4K
(1, 6, 2, 2399.98, 1199.99),    -- RTX 3080 Ti
(1, 17, 1, 699.99, 699.99),     -- Ryzen 9 7950X
(1, 20, 4, 719.96, 179.99),     -- RAM 32GB DDR5

-- Cotización 2
(2, 32, 5, 3499.95, 699.99),    -- PC Oficina
(2, 28, 5, 499.95, 99.99),     -- Gabinete Mid Tower
(2, 15, 5, 299.95, 59.99),     -- PSU 550W
(2, 12, 5, 449.95, 89.99),      -- HDD 4TB
(2, 21, 10, 799.90, 79.99),     -- RAM 16GB DDR4

-- Cotización 3
(3, 33, 2, 2999.98, 1499.99),  -- PC Streaming
(3, 7, 2, 1999.98, 999.99),     -- RX 6800 XT
(3, 14, 3, 599.97, 199.99),    -- PSU 850W
(3, 22, 8, 2799.92, 349.99),   -- RAM 64GB DDR5
(3, 9, 4, 319.96, 79.99),       -- NVMe 500GB

-- Cotización 4
(4, 34, 1, 2999.99, 2999.99),  -- PC Workstation
(4, 10, 3, 2999.97, 999.99),    -- RTX 4090
(4, 24, 2, 799.98, 399.99),     -- Motherboard Z790
(4, 11, 4, 679.96, 169.99),     -- SSD 2TB
(4, 29, 2, 459.98, 229.99),     -- Gabinete Full Tower

-- Cotización 5
(5, 35, 10, 4999.90, 499.99),  -- PC Económica
(5, 8, 5, 1499.95, 299.99),     -- GTX 1660 Super
(5, 25, 5, 849.95, 169.99),     -- Motherboard B550
(5, 19, 5, 749.95, 149.99),     -- Core i5-12400F
(5, 23, 10, 499.90, 49.99),     -- RAM 8GB DDR4

-- Cotización 6
(6, 3, 8, 2799.92, 349.99),     -- Monitor Curvo 32"
(6, 16, 5, 649.95, 129.99),     -- PSU 750W
(6, 18, 3, 2249.97, 749.99),    -- Core i9-13900K
(6, 26, 5, 199.95, 39.99),      -- RAM 8GB DDR4
(6, 27, 2, 219.98, 109.99),     -- Motherboard H610

-- Cotización 7
(7, 5, 6, 779.94, 129.99),      -- Monitor Portátil
(7, 4, 4, 1719.96, 429.99),     -- Monitor Profesional
(7, 13, 3, 389.97, 129.99),     -- PSU 750W Gold
(7, 30, 2, 199.98, 99.99),      -- Gabinete Mid Tower
(7, 20, 6, 1079.94, 179.99);    -- RAM 32GB DDR5
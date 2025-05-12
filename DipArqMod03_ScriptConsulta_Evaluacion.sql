-- Ver PCs y sus componentes
SELECT c.description AS PC, COUNT(cp.idComponente) AS Componentes
FROM Componente c
JOIN ComponentePC cp ON c.idComponente = cp.idPC
WHERE c.tipo = 'PC'
GROUP BY c.idComponente;

-- Ver detalles de una cotización
SELECT d.idCotizacion, c.description, d.cantidad, d.importeCotizado 
FROM DetalleCotizacion d
JOIN Componente c ON d.idComponente = c.idComponente
WHERE idCotizacion = 1;
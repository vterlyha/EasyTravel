select clients.firstName, clients.lastName, count(visas.id) 
FROM clients
JOIN clientsvisas
ON (clients.id = clientsvisas.clientsId)
JOIN visas
ON (visas.id = clientsvisas.visasId)
WHERE clients.id = 3
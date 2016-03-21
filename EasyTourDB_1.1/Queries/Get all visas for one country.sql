select countries.name, count(visas.id) 
FROM countries
JOIN visas
ON (countries.id = visas.countryId)
JOIN clientsvisas
ON (visas.id = clientsvisas.visasId)
WHERE countries.id = 2
use tour_firm;
select 
	clients.firstName,
    clients.lastName,
    visas.countryId,
    countries.name
from clients
join clientsvisas
on clientsvisas.clientsId = clients.id
join visas 
on visas.id = clientsvisas.visasId
join countries
on countries.id = visas.countryId
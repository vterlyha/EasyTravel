use tour_firm;
select 
	cities.name
from cities
join countries on countries.id = cities.countryId
where countries.id = 2;

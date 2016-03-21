SELECT hotels.name FROM hotels
INNER JOIN cities
ON (cities.id = hotels.cityId)
WHERE cities.name = 'Rhodes'
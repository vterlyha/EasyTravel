use tour_firm;
select 
	hotels.name,
    cities.name,
    count(clients.id)
from hotels
join bookings
on bookings.hotelId = hotels.id
join cities
on cities.id = hotels.cityId
join clients
on clients.id = bookings.client_id
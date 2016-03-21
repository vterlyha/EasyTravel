use tour_firm;
select 
	hotels.name
from hotels
join cities
on cities.id = hotels.cityId
join bookings
on  bookings.hotelId = hotels.id
where bookings.dateFrom = '2016-04-15' 
	and bookings.dateTo = '2016-04-21'
    and cities.id = 2
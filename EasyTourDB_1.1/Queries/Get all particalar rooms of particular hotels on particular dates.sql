use tour_firm;
select
	roomcategoryhotel.quantity
from roomcategoryhotel
join roomcategory
on roomcategory.id = roomcategoryhotel.categoryId
join hotels
on hotels.id = roomcategoryhotel.hotelId
join bookings
on bookings.hotelId = hotels.id
where bookings.dateFrom = '2016-04-15' 
	and bookings.dateTo = '2016-04-21'
    and hotels.id = 5
    and roomcategory.id = 2;

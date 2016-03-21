function DisplayCities() {
    document.getElementById("CitySelect").style.visibility = "visible";
}
function DisplayHotels() {
    var e = document.getElementById("CitySelect");
    if ( e.selectedIndex == 0 ) {
        document.getElementById("HotelPatayaSelect").style.visibility = "hidden";
        document.getElementById("HotelBangkockSelect").style.visibility = "visible";
    }
    if ( e.selectedIndex == 1 ) {
        document.getElementById("HotelBangkockSelect").style.visibility = "hidden";
        document.getElementById("HotelPatayaSelect").style.visibility = "visible";
    }  
    document.getElementById("RoomSelect").style.visibility = "visible";
    document.getElementById("RoomAvailabilityLabel").style.visibility = "visible";
}
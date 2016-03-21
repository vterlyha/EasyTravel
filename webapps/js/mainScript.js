var items = ["images/egypt1.jpg", "images/greece1.jpg",
             "images/dubai1.jpg", "images/dubai2.jpg",
             "images/florence1.jpg", "images/thailand1.jpg",
             "images/thailand2.jpg", "images/venice1.jpg",];
var step = 0;
function slideImages() {
    document.getElementById("SlideShowImages").src = items[step];
    if (step < 7)
        step++;
    else step = 0;
    setTimeout("slideImages()",2500);
}
slideImages();

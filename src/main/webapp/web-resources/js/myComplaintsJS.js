
var changeTab = function (evt, cityName) {
    var i, x, tablinks;
    x = document.getElementsByClassName("tab");

    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablink");

    for (i = 0; i < x.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" w3-theme-d2", "");
    }

    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " w3-theme-d2";
};



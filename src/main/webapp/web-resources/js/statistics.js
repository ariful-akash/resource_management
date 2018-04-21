/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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



var getToggleValue = function () {
    var toggle = document.getElementById("toggle");

};


window.onload = function () {

    var options = {
        animationEnabled: true,
        title: {
            text: "Human Resources"
        },
        axisY: {
            title: "Number",
            suffix: "%",
            includeZero: false
        },
        axisX: {
            title: "Types"
        },
        data: [{
                type: "column",
                yValueFormatString: "#,##0.0#" % "",
                dataPoints: [
                    {label: "Iraq", y: 90.0},
                    {label: "Turks & Caicos Islands", y: 9.40},
                    {label: "Nauru", y: 80.50},
                    {label: "Ethiopia", y: 70.96},
                    {label: "Uzbekistan", y: 7.80},
                    {label: "Nepal", y: 7.56},
                    {label: "Iceland", y: 7.20},
                    {label: "India", y: 7.1}

                ]
            }]
    };
    $("#chartContainer").CanvasJSChart(options);

};
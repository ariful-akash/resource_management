var allHr;
var hrArray = [];
/**
 *
 * @returns {undefined}
 */
var loadAllHr = function () {

    if (allHr == undefined || allHr == null) {

        var url = "/office_resource_management/api/service/office/hr/hrtype";
        var method = "GET";

        fetchUsersAJAX(url, method, null);
    }
};

/**
 *
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var fetchUsersAJAX = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            allHr = JSON.parse(this.responseText);
            makeHRArray();
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);
};

/**
 *
 * @returns {undefined}
 */
var makeHRArray = function () {

    for (var i = 0; i < allHr.length; i++) {

        var point = {};

        point.label = allHr[i].resourceName;
        point.y = allHr[i].humanResources.length;
        point.users = "";

        for (var j = 0; j < allHr[i].humanResources.length; j++) {

            point.users += (j + 1) + "." + allHr[i].humanResources[j].firstName + " "
                    + allHr[i].humanResources[j].lastName + "<br>";
        }

        hrArray.push(point);
    }

    var style = document.getElementById('graphStyle').value;

    if (style == "Pie Chart") {

        placeHRGraph("pie");
    } else {

        placeHRGraph("column");
    }

    placeHRTable();
};


var changeGraph = function (element) {

    var index = element.selectedIndex;

    if (index == 0) {

        placeHRGraph("pie");
    } else if (index == 1) {

        placeHRGraph("column");
    }
};

/**
 *
 * @returns {undefined}
 */
var placeHRTable = function () {

    var tBody = document.getElementById('hrListBody');

    for (var i = 0; i < hrArray.length; i++) {

        var row = document.createElement("tr");
        row.className = "w3-hover-theme";

        var cell1 = row.insertCell(0);
        cell1.innerHTML = hrArray[i].label;

        var cell2 = row.insertCell(1);
        cell2.innerHTML = hrArray[i].y;

        tBody.appendChild(row);
    }
};

/**
 *
 * @param {type} type
 * @returns {undefined}
 */
var placeHRGraph = function (type) {

    var options;

    if (type == "pie") {

        options = {
            animationEnabled: true,
            backgroundColor: "#dfe5e8",
            title: {
                text: "Human Resources"
            },
            axisY: {
                title: "Number",
                includeZero: false
            },
            axisX: {
                title: "Types"
            },
            data: [{
                    type: type,
                    startAngle: 30,
                    indexLabel: "{label} - {y}",
                    toolTipContent: "{users}",
                    showInLegend: true,
                    legendText: "{label}",
                    indexLabelFontSize: 20,
                    dataPoints: hrArray

                }]
        };
    } else if (type == "column") {

        options = {
            animationEnabled: true,
            backgroundColor: "#dfe5e8",
            title: {
                text: "Human Resources"
            },
            axisY: {
                title: "Number",
                includeZero: false
            },
            axisX: {
                title: "Types"
            },
            data: [{
                    type: type,
                    startAngle: 30,
                    toolTipContent: "{users}",
                    showInLegend: true,
                    legendText: "HR types by number",
                    indexLabelFontSize: 20,
                    dataPoints: hrArray

                }]
        };
    }
    $("#hrChartContainer").CanvasJSChart(options);

    document.getElementsByClassName('canvasjs-chart-credit')[0].style.display = "none";
};


var changeViewStyle = function (element) {

    var view = element.firstChild.data;
    if (view == "See List") {

        element.firstChild.data = "See Graph";
        document.getElementById('hrChartContainer').style.display = "none";
        document.getElementById('hrListContainer').style.display = "block";
        document.getElementById('graphStyle').style.display = "none";
    } else {

        element.firstChild.data = "See List";
        document.getElementById('hrListContainer').style.display = "none";
        document.getElementById('hrChartContainer').style.display = "block";
        document.getElementById('graphStyle').style.display = "inline";
    }
};
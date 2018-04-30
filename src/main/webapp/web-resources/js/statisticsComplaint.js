var complaint_number_json;
var complaintGraphArray = [];
var years_json;
var months =
        ["January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"];

var getYears = function () {

    if (years_json == undefined || years_json == null) {

        var url = "/office_resource_management/api/service/office/complaint/admin/years";
        var method = "GET";

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {

            console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

            if (this.readyState == 4 && this.status == 200) {

                years_json = JSON.parse(this.responseText);
                placeYears();
            }
        };

        xhttp.open(method, url, true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send();
    }

};

/**
 *
 * @returns {undefined}
 */
var placeYears = function () {

    var comGraphYear = document.getElementById('comGraphYear');

    comGraphYear.innerHTML = "<option>All</option>";
    for (var i in years_json) {

        comGraphYear.innerHTML += "<option>" + years_json[i] + "</optoin>";
    }

    placeMonths();
    getComplaintsNumber();
};

/**
 *
 * @returns {undefined}
 */
var placeMonths = function () {

    var comGraphMonth = document.getElementById('comGraphMonth');
    var comGraphYear = document.getElementById('comGraphYear').value;

    comGraphMonth.innerHTML = "<option>All</option>";

    if (comGraphYear != "All") {

        for (var i = 0; i < months.length; i++) {

            comGraphMonth.innerHTML += "<option>" + months[i] + "</optoin>";
        }
    }
};

/**
 *
 * @returns {undefined}
 */
var getComplaintsNumber = function () {

    var comGraphMonth = document.getElementById('comGraphMonth').value;
    var comGraphYear = document.getElementById('comGraphYear').value;

    var url =
            "/office_resource_management/api/service/office/complaint/admin/"
            + comGraphYear + "/"
            + comGraphMonth;
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            complaint_number_json = JSON.parse(this.responseText);

            makeComplaintGraphArray();
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();
};

/**
 *
 * @returns {undefined}
 */
var makeComplaintGraphArray = function () {

    complaintGraphArray = [];

    for (var i = 0; i < complaint_number_json.length; i++) {

        var point = {};

        point.label = complaint_number_json[i].type;
        point.y = complaint_number_json[i].solved + complaint_number_json[i].unsolved;
        point.tip = "<h4><b>Type : </b>" + complaint_number_json[i].type
                + "<br><b>Solved : </b>" + complaint_number_json[i].solved
                + "<br><b>Unsolved : </b>" + complaint_number_json[i].unsolved + "</h4>";

        complaintGraphArray.push(point);
    }

    placeComplaintGraph();
    placeComplaintTable();
};

/**
 *
 * @returns {undefined}
 */
var placeComplaintGraph = function () {

    var complaintGraphStyle = document.getElementById('comGraphStyle').value;
    var options;

    if (complaintGraphStyle == "Pie Chart") {

        options = {
            animationEnabled: true,
            backgroundColor: "#dfe5e8",
            title: {
                text: "Complaints"
            },
            axisY: {
                title: "Number",
                includeZero: false
            },
            axisX: {
                title: "Types"
            },
            data: [{
                    type: "pie",
                    startAngle: 30,
                    indexLabel: "{label} - {y}",
                    toolTipContent: "{tip}",
                    indexLabelFontSize: 20,
                    dataPoints: complaintGraphArray

                }]
        };
    } else {

        options = {
            animationEnabled: true,
            backgroundColor: "#dfe5e8",
            title: {
                text: "Complaints"
            },
            culture: "es",
            axisY: {
                title: "Number",
                includeZero: false
            },
            axisX: {
                title: "Types",
                interval: 1,
                labelAngle: -30
            },
            data: [{
                    type: "column",
                    startAngle: 30,
                    toolTipContent: "{tip}",
                    showInLegend: true,
                    legendText: "Complaints by number",
                    indexLabelFontSize: 20,
                    dataPoints: complaintGraphArray

                }]
        };
    }

    $("#comChartContainer").CanvasJSChart(options);

    document.getElementsByClassName('canvasjs-chart-credit')[0].style.display = "none";
};

/**
 *
 * @param {type} myNode
 * @returns {undefined}
 */
var removeComplaintTableChild = function (myNode) {

    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
};

/**
 *
 * @returns {undefined}
 */
var placeComplaintTable = function () {

    var tBody = document.getElementById('comListBody');

    removeTableChild(tBody);

    for (var i in complaint_number_json) {

        var row = document.createElement("tr");
        row.className = "w3-hover-theme";
        row.style.width = "100%";

        var cell1 = row.insertCell(0);
        cell1.innerHTML = complaint_number_json[i].type;
        cell1.style.width = "100%";

        var cell2 = row.insertCell(1);
        cell2.innerHTML = complaint_number_json[i].solved + complaint_number_json[i].unsolved;

        if (complaint_number_json[i].unsolved == 0) {

            row.className += " w3-green";
        } else {

            row.style.backgroundColor = "#ff9999";
        }

        cell1.title = "Solved : " + complaint_number_json[i].solved
                + ", Unsolved : " + complaint_number_json[i].unsolved;

        tBody.appendChild(row);
    }
};

/**
 *
 * @returns {undefined}
 */
var changeComplaintGraphList = function () {

    var view = document.getElementById('comlistOrGraph');
    if (view.firstChild.data == "See List") {

        view.firstChild.data = "See Graph";
        document.getElementById('comGraphStyle').style.display = "none";
        document.getElementById('comChartContainer').style.display = "none";
        document.getElementById('comListContainer').style.display = "block";
    } else {

        view.firstChild.data = "See List";
        document.getElementById('comGraphStyle').style.display = "inline";
        document.getElementById('comListContainer').style.display = "none";
        document.getElementById('comChartContainer').style.display = "block";
    }
};
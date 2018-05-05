var requisition_number_json;
var requisitionGraphArray = [];
var requisition_years_json;
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

var getRequisitionYears = function () {

    if (requisition_years_json == undefined || requisition_years_json == null) {

        var url = "/office_resource_management/api/service/office/requisition/admin/years";
        var method = "GET";

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {

            console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

            if (this.readyState == 4 && this.status == 200) {

                requisition_years_json = JSON.parse(this.responseText);
                placeRequisitionYears();
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
var placeRequisitionYears = function () {

    var reqGraphYear = document.getElementById('reqGraphYear');

    reqGraphYear.innerHTML = "<option>All</option>";
    for (var i in requisition_years_json) {

        reqGraphYear.innerHTML += "<option>" + requisition_years_json[i] + "</optoin>";
    }

    placeRequisitionMonths();
    getRequisitionsNumber();
};

/**
 *
 * @returns {undefined}
 */
var placeRequisitionMonths = function () {

    var reqGraphMonth = document.getElementById('reqGraphMonth');
    var reqGraphYear = document.getElementById('reqGraphYear').value;

    reqGraphMonth.innerHTML = "<option>All</option>";

    if (reqGraphYear != "All") {

        for (var i = 0; i < months.length; i++) {

            reqGraphMonth.innerHTML += "<option>" + months[i] + "</optoin>";
        }
    }

};

/**
 *
 * @returns {undefined}
 */
var getRequisitionsNumber = function () {

    var reqGraphMonth = document.getElementById('reqGraphMonth').value;
    var reqGraphYear = document.getElementById('reqGraphYear').value;

    var url =
            "/office_resource_management/api/service/office/requisition/admin/"
            + reqGraphYear + "/"
            + reqGraphMonth;
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            requisition_number_json = JSON.parse(this.responseText);

            makeRequisitionGraphArray();
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
var makeRequisitionGraphArray = function () {

    requisitionGraphArray = [];

    for (var i = 0; i < requisition_number_json.length; i++) {

        var point = {};

        point.label = requisition_number_json[i].type;
        point.y = requisition_number_json[i].solved + requisition_number_json[i].unsolved;
        point.tip = "<h4><b>Type : </b>" + requisition_number_json[i].type
                + "<br><b>Solved : </b>" + requisition_number_json[i].solved
                + "<br><b>Unsolved : </b>" + requisition_number_json[i].unsolved + "</h4>";

        requisitionGraphArray.push(point);
    }

    placeRequisitionGraph();
    placeRequisitionTable();
};

/**
 *
 * @returns {undefined}
 */
var placeRequisitionGraph = function () {

    var requisitionGraphStyle = document.getElementById('reqGraphStyle').value;
    var options;

    if (requisitionGraphStyle == "Pie Chart") {

        options = {
            animationEnabled: true,
            backgroundColor: "#dfe5e8",
            title: {
                text: "Requisitions"
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
                    dataPoints: requisitionGraphArray

                }]
        };
    } else {

        options = {
            animationEnabled: true,
            backgroundColor: "#dfe5e8",
            title: {
                text: "requisitions"
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
                    legendText: "Requisitions by number",
                    indexLabelFontSize: 20,
                    dataPoints: requisitionGraphArray

                }]
        };
    }

    $("#reqChartContainer").CanvasJSChart(options);

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
var placeRequisitionTable = function () {

    var tBody = document.getElementById('reqListBody');

    removeTableChild(tBody);

    for (var i in requisition_number_json) {

        var row = document.createElement("tr");
        row.className = "w3-hover-theme";
        row.style.width = "100%";

        var cell1 = row.insertCell(0);
        cell1.innerHTML = requisition_number_json[i].type;
        cell1.style.width = "100%";

        var cell2 = row.insertCell(1);
        cell2.innerHTML = requisition_number_json[i].solved + requisition_number_json[i].unsolved;

        if (requisition_number_json[i].unsolved == 0) {

            row.className += " w3-green";
        } else {

            row.style.backgroundColor = "#ff9999";
        }

        cell1.title = "Solved : " + requisition_number_json[i].solved
                + ", Unsolved : " + requisition_number_json[i].unsolved;

        tBody.appendChild(row);
    }
};

/**
 *
 * @returns {undefined}
 */
var changeRequisitionGraphList = function () {

    var view = document.getElementById('reqlistOrGraph');
    if (view.firstChild.data == "See List") {

        view.firstChild.data = "See Graph";
        document.getElementById('reqGraphStyle').style.display = "none";
        document.getElementById('reqChartContainer').style.display = "none";
        document.getElementById('reqListContainer').style.display = "block";
    } else {

        view.firstChild.data = "See List";
        document.getElementById('reqGraphStyle').style.display = "inline";
        document.getElementById('reqListContainer').style.display = "none";
        document.getElementById('reqChartContainer').style.display = "block";
    }
};
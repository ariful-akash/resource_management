var floor_room_json;
var floorArray = [];
var floorRoomsArray = [];
var resource_number_json;

var orArray = [];

var getResourceNumber = function () {

    var floor = document.getElementById('orGraphFloor').value;
    var room = document.getElementById('orGraphRoom').value;

    var url = "/office_resource_management/api/service/or/admin/" + floor + "/" + room;
    var method = "GET";

    fetchResourceNumberAJAX(url, method);
};


/**
 *
 * @returns {undefined}
 */
var fetchResourceNumberAJAX = function (url, method) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            resource_number_json = JSON.parse(this.responseText);
            makeORArray();
            placeORList();
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
var getFloorGraphAJAX = function () {

    if (floor_room_json == undefined || floor_room_json == null) {

        var url = "/office_resource_management/api/service/office/floor/room/all";
        var method = "GET";

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {

            console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

            if (this.readyState == 4 && this.status == 200) {

                floor_room_json = JSON.parse(this.responseText);
                makeFloorRoomArray();
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
var makeFloorRoomArray = function () {

    for (var i in floor_room_json) {

        floorArray[i] = floor_room_json[i].floor;
        floorRoomsArray[floorArray[i]] = [];

        for (var j in floor_room_json[i].rooms) {

            floorRoomsArray[floorArray[i]][j] = floor_room_json[i].rooms[j].room;
        }
    }

    placeFloorGraph();
};

/**
 *
 * @returns {undefined}
 */
var placeFloorGraph = function () {

    var floorOption = document.getElementById('orGraphFloor');

    floorOption.innerHTML = "<option>All</option>";
    for (var i in floor_room_json) {

        floorOption.innerHTML += "<option>" + floor_room_json[i].floor + "</option>";
    }

    placeRoomGraph();

    getResourceNumber();
};

/**
 *
 * @returns {undefined}
 */
var placeRoomGraph = function () {

    var roomOption = document.getElementById('orGraphRoom');
    var floor = document.getElementById('orGraphFloor').value;

    roomOption.innerHTML = "<option>All</option>";

    for (var i in floorRoomsArray[floor]) {

        roomOption.innerHTML += "<option>" + floorRoomsArray[floor][i] + "</option>";
    }
};

/**
 *
 * @param {type} element
 * @returns {undefined}
 */
var changeORGraphList = function (element) {

    var view = element.firstChild.data;
    if (view == "See List") {

        element.firstChild.data = "See Graph";
        document.getElementById('orGraphStyle').style.display = "none";
        document.getElementById('orChartContainer').style.display = "none";
        document.getElementById('orListContainer').style.display = "block";
    } else {

        element.firstChild.data = "See List";
        document.getElementById('orGraphStyle').style.display = "inline";
        document.getElementById('orListContainer').style.display = "none";
        document.getElementById('orChartContainer').style.display = "block";
    }
};

/**
 *
 * @param {type} myNode
 * @returns {undefined}
 */
var removeTableChild = function (myNode) {

    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
};

/**
 *
 * @returns {undefined}
 */
var placeORList = function () {

    var tBody = document.getElementById('orListBody');

    removeTableChild(tBody);

    for (var i in resource_number_json) {

        var row = document.createElement("tr");
        row.className = "w3-hover-theme";
        row.style.width = "100%";

        var cell1 = row.insertCell(0);
        cell1.innerHTML = resource_number_json[i].type;
        cell1.style.width = "100%";

        var cell2 = row.insertCell(1);
        cell2.innerHTML = resource_number_json[i].quantity;

        if (resource_number_json[i].quantity == 0) {

            row.className = "w3-yellow";
        }

        tBody.appendChild(row);
    }
};

/**
 *
 * @returns {undefined}
 */
var makeORArray = function () {

    orArray = [];

    for (var i = 0; i < resource_number_json.length; i++) {

        var point = {};

        point.label = resource_number_json[i].type;
        point.y = resource_number_json[i].quantity;
        point.tip = "<b>Resource Type : </b>" + resource_number_json[i].type
                + "<br><b>Quantity : </b>" + resource_number_json[i].quantity;

        orArray.push(point);
    }

    placeORGraph();
};

/**
 *
 * @returns {undefined}
 */
var placeORGraph = function () {

    var orGraphStyle = document.getElementById('orGraphStyle').value;

    var options;

    if (orGraphStyle == "Pie Chart") {

        options = {
            animationEnabled: true,
            backgroundColor: "#dfe5e8",
            title: {
                text: "Office Resources"
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
                    dataPoints: orArray

                }]
        };
    } else {

        options = {
            animationEnabled: true,
            backgroundColor: "#dfe5e8",
            title: {
                text: "Office Resources"
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
                    legendText: "Office Resources by number",
                    indexLabelFontSize: 20,
                    dataPoints: orArray

                }]
        };
    }

    $("#orChartContainer").CanvasJSChart(options);

    document.getElementsByClassName('canvasjs-chart-credit')[0].style.display = "none";
};
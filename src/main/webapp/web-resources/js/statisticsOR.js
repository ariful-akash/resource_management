var floor_room_json;
var floorArray = [];
var floorRoomsArray = [];

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
        document.getElementById('graphItem').style.display = "none";
        document.getElementById('orChartContainer').style.display = "none";
        document.getElementById('orListContainer').style.display = "block";
    } else {

        element.firstChild.data = "See List";
        document.getElementById('graphItem').style.display = "inline";
        document.getElementById('orListContainer').style.display = "none";
        document.getElementById('orChartContainer').style.display = "block";
    }
};
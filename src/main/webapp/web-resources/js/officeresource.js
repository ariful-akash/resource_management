var byType = [];
var byFloor = [];
var floorRoomsArray = [];

var floorArray = [];
var roomArray = [];

var floor_room_json;
var office_resource_json;
var office_resource_type_json;

/**
 * This method calls the ajax method to prepare all variable to show
 * @returns {undefined}
 */
var placeOfficeResourceData = function () {

    getFloorRoomsAJAX();
};

/**
 *
 * @returns {undefined}
 */
var getFloorRoomsAJAX = function () {

    var url = "/office_resource_management/api/service/office/floor/room/all";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            floor_room_json = JSON.parse(this.responseText);
            getOfficeResourceTypesAJAX();
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
var getOfficeResourceTypesAJAX = function () {

    var url = "/office_resource_management/api/service/office/officeresourcetype";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            office_resource_type_json = JSON.parse(this.responseText);
            makeResourceTypeArray();
            placeTypeOption();
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
var getOfficeResourcesAJAX = function () {

    var url = "/office_resource_management/api/service/office/officeresource/types/all";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            office_resource_json = JSON.parse(this.responseText);
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();
};


//var makeFloorRoomArray = function () {
//
//
//};

var makeResourceTypeArray = function () {

    for (var i in floor_room_json) {

        floorArray[i] = floor_room_json[i].floor;
        floorRoomsArray[floorArray[i]] = [];

        for (var j in floor_room_json[i].rooms) {

            floorRoomsArray[floorArray[i]][j] = floor_room_json[i].rooms[j].room;
        }
    }

    for (var i in office_resource_type_json) {

        byType[office_resource_type_json[i].resourceType] = [];


        for (var j in floor_room_json) {

            byType[office_resource_type_json[i].resourceType]
            [floor_room_json[j].floor] = [];

            for (var k in floor_room_json[j].rooms) {

                byType[office_resource_type_json[i].resourceType]
                [floor_room_json[j].floor]
                [floor_room_json[j].rooms[k].room] = 0;
            }

            byType[office_resource_type_json[i].resourceType]
            [floor_room_json[j].floor]['total'] = 0;
        }

        byType[office_resource_type_json[i].resourceType]['total'] = 0;
    }
};

/**
 * This method places all the option for type
 *
 * @returns {undefined}
 */
var placeTypeOption = function () {

    var typeOption = document.getElementById('typeOption');

    for (var i in office_resource_type_json) {

        typeOption.innerHTML += "<option>" + office_resource_type_json[i].resourceType + "</option>";
    }

    placeFloorOption();
};

/**
 * This method place floor option
 *
 * @returns {undefined}
 */
var placeFloorOption = function () {

    var floorOption = document.getElementById('floorOption');

    for (var i in floor_room_json) {

        floorOption.innerHTML += "<option>" + floor_room_json[i].floor + "</option>";
    }

    placeRoomOption();
};

/**
 * This method places rooms according to floor
 *
 * @returns {undefined}
 */
var placeRoomOption = function () {

    var roomOption = document.getElementById('roomOption');
    var floor = document.getElementById('floorOption').value;

    roomOption.innerHTML = "";

    for (var i in floorRoomsArray[floor]) {

        roomOption.innerHTML += "<option>" + floorRoomsArray[floor][i] + "</option>";
    }
};

/**
 * This method adds reqource in database
 *
 * @returns {undefined}
 */
var addResource = function () {

    var type = document.getElementById('typeOption').value;
    var floor = document.getElementById('floorOption').value;
    var room = document.getElementById('roomOption').value;
    var quantity = document.getElementById('typeQuantity').value;

    if (type != null && type != ''
            && floor != null && floor != ''
            && room != null && room != '') {


        var url = "/office_resource_management/api/service/office/officeresource";
        var method = "POST";
        var params = "type=" + type
                + "&floor=" + floor
                + "&room=" + room
                + "&quantity=" + quantity;

        addUpdateResourceAJAX(url, method, params);
    }
};

/**
 * Ajax method to add or update resource
 *
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var addUpdateResourceAJAX = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var reply = JSON.parse(this.responseText);

            if (reply.add == "true") {

                alert('Resource added successfully!');
            } else {

                alert('Problem in resource adding!!');
            }
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);
};

/**
 * This method changes the add view and table view
 * @param {type} id
 * @returns {undefined}
 */
var changeDisplay = function (id) {

    var addOffice = document.getElementById('addResourceDiv');
    var viewOffice = document.getElementById('viewResourceDiv');

    if (id == "addOffice") {

        viewOffice.style.display = "none";
        addOffice.style.display = "block";
    } else {

        addOffice.style.display = "none";
        viewOffice.style.display = "block";
    }
};

var placeOfficeResourceTable = function () {

    console.log("hello");
};
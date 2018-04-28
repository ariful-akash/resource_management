var byType = [];
var byFloor = [];
var floorRoomsArray = [];

var floorArray = [];
var roomArray = [];

var roomQuantity = {};
var roomQuantityCopy = {};
var total = 0;

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

    var url = "/office_resource_management/api/service/office/officeresource";
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

/**
 *
 * @returns {undefined}
 */
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

            byType[office_resource_type_json[i].resourceType]
            [floor_room_json[j].floor]['total'] = 0;
        }

        byType[office_resource_type_json[i].resourceType]['total'] = 0;
    }

    for (var m in office_resource_json) {

        byType[office_resource_json[m].type.resourceType]
        [office_resource_json[m].floor]['total'] += office_resource_json[m].quantity;
    }

    fetchSpecificResources();
};

/**
 * This method places all the option for type
 *
 * @returns {undefined}
 */
var placeTypeOption = function () {

    var typeOption = document.getElementById('typeOption');
    var viewTypeOption = document.getElementById('viewTypeOption');

    for (var i in office_resource_type_json) {

        typeOption.innerHTML += "<option>" + office_resource_type_json[i].resourceType + "</option>";
        viewTypeOption.innerHTML += "<option>" + office_resource_type_json[i].resourceType + "</option>";
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
    var viewFloorOption = document.getElementById('viewFloorOption');

    for (var i in floor_room_json) {

        floorOption.innerHTML += "<option>" + floor_room_json[i].floor + "</option>";
        viewFloorOption.innerHTML += "<option>" + floor_room_json[i].floor + "</option>";
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
    var viewRoomOption = document.getElementById('viewRoomOption');

    var floor = document.getElementById('floorOption').value;
    var viewFloor = document.getElementById('viewFloorOption').value;

    roomOption.innerHTML = "";
    viewRoomOption.innerHTML = "";

    viewRoomOption.innerHTML = "<option>All</option>";

    for (var i in floorRoomsArray[floor]) {

        roomOption.innerHTML += "<option>" + floorRoomsArray[floor][i] + "</option>";
    }

    for (var i in floorRoomsArray[viewFloor]) {

        viewRoomOption.innerHTML += "<option>" + floorRoomsArray[viewFloor][i] + "</option>";
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


/**
 *
 * @returns {undefined}
 */
var fetchSpecificResources = function () {

    var viewTypeOption = document.getElementById('viewTypeOption').value;
    var viewFloorOption = document.getElementById('viewFloorOption').value;
    var viewRoomOption = document.getElementById('viewRoomOption').value;

    if (viewTypeOption == '') {

        viewTypeOption = office_resource_type_json[0].resourceType;
        viewFloorOption = floor_room_json[0].floor;
        viewRoomOption = "All";
    }

    console.log(viewTypeOption + " " + viewFloorOption + " " + viewRoomOption);

    var url = "/office_resource_management/api/service/office/officeresource/specific";
    var method = "GET";
    url += "?type=" + viewTypeOption
            + "&floor=" + viewFloorOption
            + "&room=" + viewRoomOption;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            office_resource_json = JSON.parse(this.responseText);
            placeTableData();
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();
};

/**
 *
 * @param {type} myNode
 * @returns {undefined}
 */
var removeChild = function (myNode) {

    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
};

/**
 *
 * @returns {undefined}
 */
var placeTableData = function () {

    var byTypeTableBody = document.getElementById('byTypeTableBody');
    var floorTotal = document.getElementById('floorTotal');

    var viewTypeOption = document.getElementById('viewTypeOption').value;
    var viewFloorOption = document.getElementById('viewFloorOption').value;
    var viewRoomOption = document.getElementById('viewRoomOption').value;

    var caption = document.getElementById('caption');
    caption.innerHTML = viewTypeOption;


    roomArray = floorRoomsArray[viewFloorOption];

    for (var i in roomArray) {

        roomQuantity[roomArray[i]] = 0;
        roomQuantityCopy[roomArray[i]] = 0;
    }

    if (office_resource_json[0] != null) {

        var rooms = office_resource_json[0].rooms;
        for (var i in rooms) {

            roomQuantity[rooms[i].room] = rooms[i].quantity;

            roomQuantityCopy[rooms[i].room] = rooms[i].quantity;
            total += rooms[i].quantity;
        }
    }

    removeChild(byTypeTableBody);

    if (viewRoomOption == "All") {

        for (var i in roomArray) {

            var c = 0;
            var row = document.createElement("tr");

            if (i == 0) {

                var floorCell = row.insertCell(c++);
                floorCell.rowSpan = roomArray.length;
                floorCell.innerHTML = viewFloorOption;
            }

            var roomCell = row.insertCell(c++);
            roomCell.innerHTML = roomArray[i];

            var quantityCell = row.insertCell(c++);
            quantityCell.className = "w3-hover-theme";
            quantityCell.contentEditable = "true";
            quantityCell.innerHTML = roomQuantity[roomArray[i]];

            quantityCell.onkeyup = deleteEnter;
            quantityCell.onblur = saveToDB;

            var idAttr = document.createAttribute("id");
            idAttr.value = roomArray[i].toString();

            quantityCell.setAttributeNode(idAttr);

            if (i == 0) {

                var totalCell = row.insertCell(c++);
                totalCell.rowSpan = roomArray.length;
                totalCell.innerHTML = total;
            }

            byTypeTableBody.appendChild(row);
        }

    } else {

        var row = byTypeTableBody.insertRow(0);

        var floorCell = row.insertCell(0);
        floorCell.innerHTML = viewFloorOption;

        var roomCell = row.insertCell(1);
        roomCell.innerHTML = viewRoomOption;

        var quantityCell = row.insertCell(2);
        quantityCell.className = "w3-hover-theme";
        quantityCell.contentEditable = "true";

        quantityCell.onkeyup = deleteEnter;
        quantityCell.onblur = saveToDB;

        var idAttr = document.createAttribute("id");
        idAttr.value = viewRoomOption.toString();

        quantityCell.setAttributeNode(idAttr);

        if (office_resource_json[0] == null) {

            quantityCell.innerHTML = 0;
        } else {

            quantityCell.innerHTML = office_resource_json[0].rooms[0].quantity;
        }

        var totalFloorCell = row.insertCell(3);
        totalFloorCell.innerHTML = "N/A";
    }
};

/**
 *
 * @param {type} event
 * @returns {undefined}
 */
var deleteEnter = function (event) {

    var key = event.keyCode || event.which;
    var target = event.target || event.srcElement;

    if (key == 8 || (key >= 48 && key <= 57)) {

        if (target.firstChild.data == undefined) {

            if (roomQuantity[target.id] != 0) {

                roomQuantity[target.id] = 0;
            }

        } else {

            roomQuantity[target.id] = Number(target.firstChild.data);// need to change the string value to int value
        }

    } else {

        target.innerHTML = roomQuantity[target.id];
    }
};

/**
 *
 * @returns {undefined}
 */
var updateResource = function (type, floor, room, quantity) {



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
 *
 * @param {type} event
 * @returns {undefined}
 */
var saveToDB = function (event) {

    var target = event.target || event.srcElement;

    var viewTypeOption = document.getElementById('viewTypeOption').value;
    var viewFloorOption = document.getElementById('viewFloorOption').value;


    if (roomQuantityCopy[target.id].valueOf() != roomQuantity[target.id].valueOf()) {

        var yes = confirm("Update:\n\tType: " + viewTypeOption
                + "\n\tFloor: " + viewFloorOption
                + "\n\tRoom: " + target.id
                + "\n\tQuantity: " + roomQuantity[target.id]);

        if (yes) {

            roomQuantityCopy[target.id] = roomQuantity[target.id];
            updateResource(viewTypeOption, viewFloorOption, target.id, roomQuantity[target.id]);
        }
    }

};
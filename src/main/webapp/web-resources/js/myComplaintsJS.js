/*Globa variables start*/

var pendingComlaints, solvedCoplaints, allComplaints;
var hPendingComplaints, hSolvedComplaints;
var pendingRequisition, solvedRequisition;
var hPendingRequisition, hSolvedRequisition;


var data;
var currentUser;
var type;
var comORreq;
var own = true;
/*Gobal variables end*/



var changeOwn = function (value) {

    own = value;
};

var getComplaints = function () {

    if (type == 'pending') {

        getPandingComplaints();
    } else {

        getSolvedComplaints();
    }
};

var getRequisition = function () {

    if (type == 'pending') {

        getPandingRequisitions();
    } else {

        getSolvedRequisitions();
    }
};

var getPandingComplaints = function () {

    if (own) {

        getOwnPendingComplaints();
    } else {

        getIncomingComplaints();
    }
};

var getSolvedComplaints = function () {

    if (own) {

        getOwnSolvedComplaints();
    } else {

        getIncomingSolvedComplaints();
    }
};


var getPandingRequisitions = function () {

    if (own) {

        getOwnPendingRequisition();
    } else {

        getIncomingRequisition();
    }
};

var getSolvedRequisitions = function () {

    if (own) {

        getOwnSolvedRequisition();
    } else {

        getIncomingSolvedRequisition();
    }
};

/**
 * @returns {undefined}
 */
var getOwnPendingComplaints = function () {

    var url = "/office_resource_management/api/service/office/complaint/own/false";
    var method = "GET";
    type = 'pending';
    comORreq = 'complaint';

    fetchData(url, method, null);
};

/**
 *
 * @returns {undefined}
 */
var getOwnSolvedComplaints = function () {

    var url = "/office_resource_management/api/service/office/complaint/own/true";
    var method = "GET";
    type = 'solved';
    comORreq = 'complaint';

    fetchData(url, method, null);

};

/**
 *
 * @returns {undefined}
 */
var getIncomingComplaints = function () {

    var url = "/office_resource_management/api/service/office/complaint/incoming/false";
    var method = "GET";
    type = 'pending';
    comORreq = 'complaint';

    fetchData(url, method, null);
};

/**
 *
 * @returns {undefined}
 */
var getIncomingSolvedComplaints = function () {

    var url = "/office_resource_management/api/service/office/hr/current";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            currentUser = JSON.parse(this.responseText);

            url = "/office_resource_management/api/service/office/complaint/solver/" + currentUser.id;
            method = "GET";
            type = 'solved';
            comORreq = 'complaint';

            fetchData(url, method, null);
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(null);
};

/**
 *
 * @returns {undefined}
 */
var getOwnPendingRequisition = function () {

    var url = "/office_resource_management//api/service/office/requisition/own/false";
    var method = "GET";
    type = 'pending';
    comORreq = 'requisition';

    fetchData(url, method, null);
};

/**
 *
 * @returns {undefined}
 */
var getOwnSolvedRequisition = function () {

    var url = "/office_resource_management/api/service/office/requisition/own/true";
    var method = "GET";
    type = 'solved';
    comORreq = 'requisition';

    fetchData(url, method, null);
};

/**
 *
 * @returns {undefined}
 */
var getIncomingRequisition = function () {

    var url = "/office_resource_management/api/service/office/requisition/incoming";
    var method = "GET";
    type = 'pending';
    comORreq = 'requisition';

    fetchData(url, method, null);
};

/**
 * Call this method to solve a complaint or requisition
 *
 * @param {type} e
 * @returns {undefined}
 */
var solveComplaintRequisition = function (e) {

    var target = e.currentTarget || e.srcElement;

    var url;
    var method = "POST";
    var params = "id=" + target.id;

    if (comORreq == 'complaint') {

        url = "/office_resource_management/api/service/office/complaint/update";
    } else if (comORreq == 'requisition') {

        url = "/office_resource_management/api/service/office/requisition/update";
    }

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var result = JSON.parse(this.responseText);

            if (result.update == 'true') {

                alert('Your pending ' + comORreq + ' is solved');
            } else {

                alert('Your pending ' + comORreq + ' is not solved');
            }

            if (comORreq == 'complaint') {

                getIncomingComplaints();
            } else {

                getIncomingRequisition();
            }
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
var getIncomingSolvedRequisition = function () {

    var url = "/office_resource_management/api/service/office/hr/current";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            currentUser = JSON.parse(this.responseText);

            console.log(currentUser);

            url = "/office_resource_management/api/service/office/requisition/solver/" + currentUser.id;
            method = "GET";
            type = 'solved';
            comORreq = 'requisition';
            own = false;

            fetchData(url, method, null);
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(null);
};


var removeChild = function (myNode) {

    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
};

/**
 *
 * @returns {undefined}
 */
var placeAllComplaints = function () {


    var pendingTab = document.getElementById(type);

    removeChild(pendingTab);

    var loading = document.getElementById('loading');
    loading.style.display = "none";

    for (var i = 0; i < data.length; i++) {
        /*
         * Main container div after tab
         */
        var div1 = document.createElement("div");
        div1.className = "w3-row w3-card  w3-margin-top w3-margin-bottom";
        pendingTab.appendChild(div1);

        /*
         * Creating image node
         */
        var img = document.createElement("img");
        img.src = "${img}";
        img.className = "w3-circle";
        img.style.width = "30px";
        img.style.height = "30px";

        var imgDiv = document.createElement("div");
        imgDiv.className = "w3-col";
        imgDiv.style.width = "5%";
        imgDiv.style.padding = "1% 0% 0% 1%";

        //image to image div
        imgDiv.appendChild(img);

        //image div to div1
        div1.appendChild(imgDiv);

        /*
         * Creating Name node
         */
        var labelNode = document.createElement("label");
        labelNode.className = "w3-large w3-text-dark-gray";
        labelNode.style.fontWeight = "bold";

        labelNode.textContent = data[i].creator.firstName + " " + data[i].creator.lastName;

        var nameDiv = document.createElement("div");
        nameDiv.className = "w3-col";
        nameDiv.style.width = "92%";
        nameDiv.style.marginTop = "1.5%";

        //adding name label to name div
        nameDiv.appendChild(labelNode);

        //adding name div to div1
        div1.appendChild(nameDiv);

        /*
         * Creating solve button div
         */
        var solveDiv = document.createElement("div");

        if (type == 'pending' && !own) {

            var solveSpan = document.createElement("span");
            solveSpan.className = "w3-btn w3-theme-l3 w3-round w3-text-white w3-medium w3-right";
            solveSpan.style.marginRight = "5%";
            solveSpan.textContent = "Solve";

            solveSpan.onclick = solveComplaintRequisition;

            var idAttr = document.createAttribute("id");
            idAttr.value = data[i].id;

            solveSpan.setAttributeNode(idAttr);

            //adding solve span to solve div
            solveDiv.appendChild(solveSpan);
        } else if (type == 'solved') {

            var solveLabel = document.createElement("label");
            solveLabel.className = "w3-text-dark-gray w3-large w3-right";
            solveLabel.style.marginRight = "5%";
            solveLabel.innerHTML = "&#x2714; Solved";

            //adding solved lavel to solve div
            solveDiv.appendChild(solveLabel);
        }

        //adding solveDiv to div1
        div1.appendChild(solveDiv);

        /*
         * Creating table contents
         */
        var tableDiv = document.createElement("div");

        if (type == 'pending') {

            tableDiv.style.margin = "4% 5% 1% 5%";
        } else if (type == 'solved') {

            tableDiv.style.margin = "6% 5% 1% 5%";
        }

        //attaching tableDiv div1
        div1.appendChild(tableDiv);

        var table = document.createElement("table");
        table.border = "1";
        table.className = "w3-table w3-striped";

        //attaching table to table div
        tableDiv.appendChild(table);

        var tableRowNo = 0;

        //creating row
        var placingDateRow = table.insertRow(tableRowNo++);
        var placeCell1 = placingDateRow.insertCell(0);
        placeCell1.innerHTML = "Placing Date";

        var placeCell2 = placingDateRow.insertCell(1);
        if (comORreq == 'complaint') {

            placeCell2.innerHTML = data[i].complaintPlacingDate;
        } else if (comORreq = 'requisition') {

            placeCell2.innerHTML = data[i].requisitionPlacingDate;
        }

        //adding row to table
        table.appendChild(placingDateRow);


        /*
         * Creating row
         */

        if (type == 'solved') {

            var solvedDateRow = table.insertRow(tableRowNo++);
            var solveCell1 = solvedDateRow.insertCell(0);
            solveCell1.innerHTML = "Solved Date";

            var solveCell2 = solvedDateRow.insertCell(1);

            if (comORreq == 'complaint') {

                solveCell2.innerHTML = data[i].complaintSolvedDate;
            } else if (comORreq == 'requisition') {

                solveCell2.innerHTML = data[i].requisitionSolvedDate;
            }
        }

        if (comORreq == 'requisition') {

            var needDateRow = table.insertRow(tableRowNo++);
            var needCell1 = needDateRow.insertCell(0);
            needCell1.innerHTML = "Need Date";

            var needCell2 = needDateRow.insertCell(1);
            needCell2.innerHTML = data[i].requisitionNeedDate;
        }

        var descriptionRow = table.insertRow(tableRowNo++);
        var descriptionCell1 = descriptionRow.insertCell(0);
        descriptionCell1.innerHTML = "Description";

        var descriptionCell2 = descriptionRow.insertCell(1);

        if (comORreq == 'complaint') {

            descriptionCell2.innerHTML = data[i].description;
        } else if (comORreq == 'requisition') {

            descriptionCell2.innerHTML = data[i].purpose;
        }

        if (comORreq == 'requisition') {

            var quantityRow = table.insertRow(tableRowNo++);
            var quantityCell1 = quantityRow.insertCell(0);
            quantityCell1.innerHTML = "Quantity";

            var quantityCell2 = quantityRow.insertCell(1);
            quantityCell2.innerHTML = data[i].quantity;
        }

        var remarksRow = table.insertRow(tableRowNo++);
        var remarksCell1 = remarksRow.insertCell(0);
        remarksCell1.innerHTML = "Remarks";

        var remarksCell2 = remarksRow.insertCell(1);

        if (data[i].remarks == null) {

            remarksCell2.innerHTML = "N/A";
        } else {

            remarksCell2.innerHTML = data[i].remarks;
        }

        if (type == 'solved') {

            var solvedByRow = table.insertRow(tableRowNo++);
            var solvedByCell1 = solvedByRow.insertCell(0);
            solvedByCell1.innerHTML = "Solved By";

            var solvedByCell2 = solvedByRow.insertCell(1);
            solvedByCell2.innerHTML = data[i].solver.firstName + " " + data[i].solver.lastName;
        }

        var typeRow = table.insertRow(tableRowNo++);
        var typeCell1 = typeRow.insertCell(0);
        typeCell1.innerHTML = "Type";

        var typeCell2 = typeRow.insertCell(1);
        typeCell2.innerHTML = data[i].type.type;
    }
};

/**
 *
 * @param {type} evt
 * @param {type} cityName
 * @returns {undefined}
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

/**
 *
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var fetchData = function (url, method, params) {

    var mainDiv1 = document.getElementById('pending');
    var mainDiv2 = document.getElementById('solved');

    removeChild(mainDiv1);
    removeChild(mainDiv2);

    var loading = document.getElementById('loading');
    loading.style.display = "block";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            data = JSON.parse(this.responseText);
            placeAllComplaints();
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);
};

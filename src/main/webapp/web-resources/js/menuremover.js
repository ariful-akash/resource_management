var user;
var accessId = [];

var setMenu = function () {

    var url = "/office_resource_management/api/service/office/hr/current";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            user = JSON.parse(this.responseText);
            setAccessId();
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();
};

var setAccessId = function () {

    for (var i in user.access) {

        accessId.push(user.access[i].id);
    }

    removeMenu();
};

var removeMenu = function () {

    var statistics = document.getElementById('statistics');
    var manageHr = document.getElementById('manageHr');
    var manageOffice = document.getElementById('manageOffice');
    var incomingComplaints = document.getElementById('incomingComplaints');
    var incomingRequisitions = document.getElementById('incomingRequisitions');

    if (accessId.indexOf(19) == -1) {

        statistics.parentNode.removeChild(statistics);
    }

    if (accessId.indexOf(1) == -1
            && accessId.indexOf(2) == -1
            && accessId.indexOf(3) == -1) {

        manageHr.parentNode.removeChild(manageHr);
    }

    if (accessId.indexOf(4) == -1 || accessId.indexOf(18) == -1) {

        manageOffice.parentNode.removeChild(manageOffice);
    }

    if (incomingComplaints != null
            && accessId.indexOf(14) == -1
            && accessId.indexOf(15) == -1
            && accessId.indexOf(16) == -1
            && accessId.indexOf(17) == -1) {

        incomingComplaints.parentNode.removeChild(incomingComplaints);
    }

    if (incomingRequisitions != null
            && accessId.indexOf(8) == -1
            && accessId.indexOf(9) == -1
            && accessId.indexOf(10) == -1
            && accessId.indexOf(11) == -1
            && accessId.indexOf(12) == -1) {

        incomingRequisitions.parentNode.removeChild(incomingRequisitions);
    }
};
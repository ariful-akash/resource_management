/**
 * Count total pending complaints to be solved
 *
 * @returns {undefined}
 */
var countComplaint = function () {

    var url = "/office_resource_management/api/service/office/complaint/incoming/false";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var data = JSON.parse(this.responseText);
            placeComplaintNotifcation(data.length);
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(null);
};
/**
 * Count total pending requisitions to be solved
 *
 * @returns {undefined}
 */
var countRequisition = function () {

    var url = "/office_resource_management/api/service/office/requisition/incoming";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var data = JSON.parse(this.responseText);
            placeRequisitionNotifcation(data.length);
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(null);
};
/**
 * Count total pending leave application to be approved/declined
 *
 * @returns {undefined}
 */
var countLeave = function () {

};
/**
 *
 * @returns {undefined}
 */
var placeComplaintNotifcation = function (total) {

    var notification = document.getElementById('complaintNotification');
    if (total > 0) {

        notification.innerHTML = total;
        notification.style.display = "inline";
    } else {

        notification.style.display = "none";
    }
};
/**
 *
 * @returns {undefined}
 */
var placeRequisitionNotifcation = function (total) {

    var notification = document.getElementById('requisitionNotification');
    if (total > 0) {

        notification.innerHTML = total;
        notification.style.display = "inline";
    } else {

        notification.style.display = "none";
    }
};
/**
 *
 * @returns {undefined}
 */
var placeLeaveNotification = function () {

};
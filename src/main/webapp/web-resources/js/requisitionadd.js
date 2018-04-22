var reqTagList;

/**
 * This method call AJAX to method to fetch complaint tags
 *
 * @returns {undefined}
 */
var getTags = function () {

    if (reqTagList == null || reqTagList == undefined) {

        var url = "/office_resource_management/api/service/office/requisitiontype";
        var method = "GET";

        fetchRequisitionTags(url, method, null);
    }
};

/**
 * This method show date and time input if project and car is selected
 *
 * @returns {undefined}
 */
var showDateTime = function () {

    var selector = document.getElementById('tagList');
    var dateTimeDiv = document.getElementById('reqDateTimeDiv');

    var type = selector.value;

    if (type == 'car' || type == 'projector') {

        dateTimeDiv.style.display = 'block';
    } else {

        dateTimeDiv.style.display = 'none';
    }
};

/**
 * Fetch complaint tags from server
 *
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var fetchRequisitionTags = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            reqTagList = JSON.parse(this.responseText);
            placeRequisitionTags();
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
var placeRequisitionTags = function () {

    var select = document.getElementById('tagList');

    for (var i in reqTagList) {

        var option = document.createElement("option");
        option.className = "w3-large";
        option.innerHTML = reqTagList[i].type;

        select.appendChild(option);
    }
};


var addRequisition = function () {

    var selector = document.getElementById('tagList');
    var date = document.getElementById('reqDate');
    var time = document.getElementById('reqTime');
    var content = document.getElementById('reqContent');
    var remarks = document.getElementById('remarks');
    var quantity = document.getElementById('quantity');
    var type_id = reqTagList[selector.selectedIndex].id;

    if (selector.value == 'projector' || selector.value == 'car') {

        var need_date = new Date(date.value + "T" + time.value).getTime();
    } else {

        var need_date = 0;
    }

    if (content.value != null && content.value.trim() != '') {

        var requisition = {};

        requisition.quantity = quantity.value;
        requisition.purpose = content.value;

        if (remarks.value != null && remarks.value != '') {

            requisition.remarks = remarks.value;
        }

        var url = "/office_resource_management/api/service/office/requisition";
        var method = "POST";
        var params = "requisition=" + JSON.stringify(requisition) + "&type_id=" + type_id + "&need_date=" + need_date;

        addRequisitionAJAX(url, method, params);
    }
};


var addRequisitionAJAX = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            result = JSON.parse(this.responseText);

            if (result.add == 'true') {

                alert("Your requisition is added!");
                document.getElementById('reqContent').value = '';
                document.getElementById('remarks').value = '';
            } else {

                alert("Your requisition can not be added!");
            }
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);
};
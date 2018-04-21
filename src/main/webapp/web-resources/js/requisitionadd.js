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
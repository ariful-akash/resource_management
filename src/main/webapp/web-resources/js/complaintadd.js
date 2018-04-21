var comTagList;

/**
 * This method call AJAX to method to fetch complaint tags
 *
 * @returns {undefined}
 */
var getTags = function () {

    if (comTagList == null || comTagList == undefined) {

        var url = "/office_resource_management/api/service/office/complainttype";
        var method = "GET";

        fetchComplaintTags(url, method, null);
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
var fetchComplaintTags = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            comTagList = JSON.parse(this.responseText);
            placeComplaintTags();
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
var placeComplaintTags = function () {

    var select = document.getElementById('tagList');

    for (var i in comTagList) {

        var option = document.createElement("option");
        option.innerHTML = comTagList[i].type;

        select.appendChild(option);
    }
};


var addComplaint = function () {

    var selector = document.getElementById('tagList');
    var desc = document.getElementById('complaintContent');
    var remarks = document.getElementById('remarks');

    var complaintJson = {};

    complaintJson.description = desc.value;
    complaintJson.remarks = remarks.value;

    if (desc.value != null && desc.value != '') {

        var url = "/office_resource_management/api/service/office/complaint";
        var method = "POST";

        addComplaintAJAX(
                url,
                method,
                "complaint=" + JSON.stringify(complaintJson) +
                "&type_id=" + comTagList[selector.selectedIndex].id);

        document.getElementById('id01').style.display = 'none';
    }
};


var addComplaintAJAX = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            result = JSON.parse(this.responseText);

            if (result.add == 'true') {

                alert("Your complaint is added!");
                document.getElementById('complaintContent').value = '';
                document.getElementById('remarks').value = '';
            } else {

                alert("Your complaint can not be added!");
            }
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);
};
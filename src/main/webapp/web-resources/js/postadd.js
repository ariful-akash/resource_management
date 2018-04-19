var newPost = {};

var allTags;


/**
 * HTML caller function to get all tags
 *
 * @returns {undefined}
 */
var getAllTags = function () {

    var url = "/office_resource_management/api/service/forum/tag";
    var method = "GET";

    fetchTagsAJAX(url, method, null);
};

/**
 * AJAX method to fetch all tags from server
 *
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var fetchTagsAJAX = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            allTags = JSON.parse(this.responseText);

            console.log(allTags);
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);
};
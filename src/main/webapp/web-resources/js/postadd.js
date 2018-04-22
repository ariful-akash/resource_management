var newPost = {};
var tags = [];

var allTags = [];
var shortTags = [];


/**
 * HTML caller function to get all tags
 *
 * @returns {undefined}
 */
var getAllTags = function () {

    if (allTags == null || allTags.length == 0) {

        var url = "/office_resource_management/api/service/forum/tag";
        var method = "GET";

        fetchTagsAJAX(url, method, null);
    }
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

            for (var i in allTags) {

                shortTags[i] = allTags[i].tag;
            }
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);
};


/**
 * tag suggesstion jQuery style
 *
 * @returns {undefined}
 */
$(function () {

    $("#postTags, #tagSearch").autocomplete(
            {
                source: shortTags
            }
    );
}
);


var addToTagList = function () {


};
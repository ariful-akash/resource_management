var newPost = {};
var postTags = [];
var searchTags = [];

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

/**
 *
 * @param {type} event
 * @returns {undefined}
 */
var removeSearchTag = function (event) {

    var label = event.target.parentNode || event.srcElement.parentNode;
    var tag = label.firstChild.data.trim();

    searchTags.splice(searchTags.indexOf(tag), 1);

    label.parentNode.removeChild(label);

    if (searchTags.length > 0) {

        getSearchedPost();
    }
};

/**
 *
 * @returns {undefined}
 */
var removePostTag = function () {

    var label = event.target.parentNode;
    var tag = label.firstChild.data.trim().length;

    label.parentNode.removeChild(label);
    postTags.splice(postTags.indexOf(tag), 1);
};


var addToTagList = function (event, id) {

    var value = event.keyCode || event.which;
    var tag = document.getElementById(id).value;
    var parent = document.getElementById(id).parentNode;

    if (value == 13 && tag != undefined && tag != '') {

        document.getElementById(id).value = "";
        if (id == 'tagSearch' && searchTags.indexOf(tag) == -1) {

            searchTags.push(tag);

            var label = document.createElement("label");
            label.className = "w3-theme-l3 w3-margin-top w3-margin-left w3-text-black";

            var text = document.createTextNode(tag + " ");

            label.appendChild(text);

            var anchor = document.createElement("a");
            anchor.innerHTML = "&#x2715;";
            anchor.href = "#";
            anchor.className = "w3-small w3-light-blue";
            anchor.style.textDecoration = "none";
            anchor.onclick = removeSearchTag;

            label.appendChild(anchor);

            parent.appendChild(label);

            getSearchedPost();

        } else if (id == 'postTags' && postTags.indexOf(tag) == -1) {

            postTags.push(tag);

            var label = document.createElement("label");
            label.className = "w3-theme-l3 w3-margin-top w3-margin-left w3-text-black";

            var text = document.createTextNode(tag + " ");
            label.appendChild(text);

            var anchor = document.createElement("a");
            anchor.innerHTML = "&#x2715;";
            anchor.href = "#";
            anchor.className = "w3-small w3-light-blue";
            anchor.style.textDecoration = "none";
            anchor.onclick = removePostTag;

            label.appendChild(anchor);

            parent.appendChild(label);
        }
    }
};


var getSearchedPost = function () {

    var tagsJson = JSON.stringify(searchTags);

    var url = "/office_resource_management/api/service/forum/post/search/tag?tags=" + tagsJson;
    var method = "GET";

    postFetchAJAX(url, method, null);
};
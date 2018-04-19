var token;
var posts;
var post;

/**
 * Set the token at initial time
 *
 * @param {type} value
 * @returns {undefined}
 */
var setToken = function (value) {

    console.log(value);

    token = value;

    var url = "/office_resource_management/api/service/forum/post";
    var method = "GET";

    postFetchAJAX(url, method, null);
};

/**
 * Communicate with server through AJAX
 *
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var postFetchAJAX = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            posts = JSON.parse(this.responseText);
            placePost();
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
var placePost = function () {

    /*
     * all post div
     */
    var allPostDiv = document.getElementById('allPostDiv');

    for (var i = 0; i < posts.length; i++) {

        /*
         * Single post div
         */
        var postDiv = document.createElement("div");
        postDiv.className = "w3-row w3-card w3-margin";

        /*
         * Attaching post div to all post div
         */
        allPostDiv.appendChild(postDiv);

        /**************************
         * Image div contains image
         *
         */
        var imgDiv = document.createElement("div");
        imgDiv.className = "w3-col";
        imgDiv.style.width = "5%";
        imgDiv.style.marginRight = "3%";
        imgDiv.style.padding = "1% 0% 0% 1%";

        //image tag

        var image = document.createElement("img");
        image.src = "";
        image.className = "w3-circle";
        image.style.height = "30px";
        image.style.width = "30px";

        /*
         * Attatching image to image div
         */
        imgDiv.appendChild(image);


        /********************
         * Name placing div
         *
         */
        var nameDiv = document.createElement("div");
        nameDiv.className = "w3-col";
        nameDiv.style.width = "92%";

        //name label tag

        var nameLabel = document.createElement("label");
        nameLabel.className = "w3-small w3-text-dark-gray";
        nameLabel.textContent = posts[i].poster.firstName + " " + posts[i].poster.lastName;
        nameLabel.fontWeight = "bold";

        //date label tag

        var dateLabel = document.createElement("label");
        dateLabel.className = "w3-tiny w3-text-dark-gray";
        dateLabel.textContent = posts[i].postTime;

        /*
         * Attaching labels to name div
         */
        nameDiv.appendChild(nameLabel);
        nameDiv.appendChild(dateLabel);

        /************************************
         * Post main contant div
         */
        var contentDiv = document.createElement("div");
        contentDiv.style.margin = "8% 5% 1% 5%";

        //contant span tag
        var contentSpan = document.createElement("span");
        contentSpan.textContent = posts[i].content;

        //see more anchore tag
        var seeMore = document.createElement("a");
        seeMore.className = "w3-text-white";
        seeMore.href = "";
        seeMore.textContent = "See More";

        /*
         * Attaching post content and see more anchore to div
         */
        contentDiv.appendChild(contentSpan);
        contentDiv.appendChild(seeMore);


        /*
         * Attaching all the divs to post div
         *
         */
        postDiv.appendChild(imgDiv);
        postDiv.appendChild(nameDiv);
        postDiv.appendChild(contentDiv);
    }
};
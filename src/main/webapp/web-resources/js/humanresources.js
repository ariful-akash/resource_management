var allUsers;


var getAllUsers = function () {
    var url = "/office_resource_management/api/service/office/hr";
    var method = "GET";

    userFetchAJAX(url, method, null);
};



/**
 * get all hr AJAX
 *
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var userFetchAJAX = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            allUsers = JSON.parse(this.responseText);

            placeUsers();
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);

};




/*
 * place all the users from database to right div
 */

var placeUsers = function () {
    /*
     * all users div
     */
    var allHr = document.getElementById('allHr');


    for (var i = 0; i < allUsers.length; i++) {

        /**************************
         * User div contains image
         *
         */
        var userDiv = document.createElement("div");
        userDiv.className = "w3-card w3-large w3-hover-light-green";
        userDiv.style.width = "60%";
        userDiv.style.margin = "0% 3% 2% 3%";
        userDiv.style.padding = "1% 0% 2% 1%";


        //attaching userDiv to allHr DIv

        allHr.appendChild(userDiv);



        /*
         * img tag
         */
        var image = document.createElement("img");


        image.className = "w3-circle";
        image.style.height = "30px";
        image.style.width = "30px";
        image.style.marginRight = "1%";

        /*
         *
         * attaching image to userDiv
         *
         */
        userDiv.appendChild(image);



        //name label tag

        var nameLabel = document.createElement("label");
        nameLabel.class = "w3-text-dark-gray";
        nameLabel.textContent = allUsers[i].firstName + " " + allUsers[i].lastName;


        /**
         * attaching Name to userDiv
         * 
         */
        userDiv.appendChild(nameLabel);

    }



};

var allUsers;
var index;


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
    var curUser = getUser();


    for (var i = 0; i < allUsers.length; i++) {

        if (curUser.id != allUsers[i].id) {

            var div = document.createElement("div");

            allHr.appendChild(div);

            div.className = "w3-card w3-round-small w3-large w3-hover-blue-gray";
            div.style.margin = "0% 0% 4% 9%";
            div.style.padding = "1% 0% 2% 1%";
            div.style.height = "50px";
            div.onclick = placeUserDetails;

            var userIdAttr = document.createAttribute("id");
            userIdAttr.value = i;

            div.setAttributeNode(userIdAttr);

            //image
            var img = document.createElement("img");
            img.className = "w3-circle";
            img.style.width = "30px";
            img.style.height = "30px";
            img.style.marginRight = "4%";
            img.alt = "#";

            if (allUsers[i].image == null) {

                img.src = "/office_resource_management/web-resources/images/dummy.jpg";
            } else {

                img.src = "data:image;base64," + allUsers[i].image;
            }

            //name label
            var label = document.createElement("label");
            label.className = "w3-text-dark-gray";
            label.innerHTML = allUsers[i].firstName + " " + allUsers[i].lastName;

            //break tag
            var br = document.createElement("br");

            //adding image & label & br to div
            div.appendChild(img);
            div.appendChild(label);
            div.appendChild(br);

        }
    }
};


var placeUserDetails = function (event) {

    var element = event.srcElement || event.target;
    index = element.id;

    if (index == '') {

        element = event.currentTarget;
        index = element.id;
    }

    singleUserInfo(index);
    placeRoleAndAccess(index);
};

var singleUserInfo = function (index) {

    document.getElementById('details').style.display = "block";

    var image = document.getElementById('singleUserImg');
    var name = document.getElementById('singleUserName');
    var email = document.getElementById('singleUserEmail');
    var dep = document.getElementById('singleUserDep');
    var deg = document.getElementById('singleUserDeg');

    var clickedUser = allUsers[index];

    if (clickedUser.image == null) {

        image.src = "/office_resource_management/web-resources/images/dummy.jpg";
    } else {

        image.src = "data:image;base64," + clickedUser.image;
    }

    name.innerHTML = clickedUser.firstName + " " + clickedUser.lastName;
    email.innerHTML = clickedUser.email;
    dep.innerHTML = clickedUser.department;
    deg.innerHTML = clickedUser.designation;
};

var placeRoleAndAccess = function () {

};
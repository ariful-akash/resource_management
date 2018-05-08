var allUsers;
var allDBUsers;

var designations;

var userNameArray = [];

var index;
var roles;
var access;
var user;
var clickedRole;

var accessArray = {};


var getAllUsers = function () {
    var url = "/office_resource_management/api/service/office/hr";
    var method = "GET";

    userFetchAJAX(url, method, null);
    fetchRoleAccessAJAX();
};

/**
 *
 * @returns {undefined}
 */
var fetchHrTypes = function () {

    if (designations == undefined || designations == null) {

        var url = "/office_resource_management/api/service/office/hr/hrtype";
        var method = "GET";

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {

            console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

            if (this.readyState == 4 && this.status == 200) {

                designations = JSON.parse(this.responseText);
                placeDesignations();
            }
        };

        xhttp.open(method, url, true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send();
    }
};
/**
 * Search user jquery style
 *
 * @returns {undefined}
 */
$(function () {

    $("#userSearch").autocomplete(
            {
                source: userNameArray
            }
    );
}
);

/**
 *
 * @returns {undefined}
 */
var placeDesignations = function () {

    var designation = document.getElementById('addDesignation');

    for (var i = 0; i < designations.length; i++) {

        var option = document.createElement("option");
        option.innerHTML = designations[i].resourceName;

        designation.appendChild(option);
    }
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

            allDBUsers = JSON.parse(this.responseText);
            allUsers = allDBUsers;
            placeUsers();
            makeUserNameArray();
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
var fetchRoleAccessAJAX = function () {

    var url = "/office_resource_management/api/service/user/role";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            roles = JSON.parse(this.responseText);

            fetchAllAccessAJAX();
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();
};

/**
 *
 * @returns {undefined}
 */
var fetchAllAccessAJAX = function () {

    var url = "/office_resource_management/api/service/user/access";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            access = JSON.parse(this.responseText);
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();
};


/**
 *
 * @returns {undefined}
 */
var makeAccessArray = function () {

    for (var i in access) {

        var obj = {};
        obj.value = access[i].description;
        obj.checked = false;

        accessArray[access[i].id] = obj;
    }

};

/*
 * place all the users from database to right div
 */
var placeUsers = function () {
    /*
     * all users div
     */
    var allHr = document.getElementById('allHr');

    removeChild(allHr);


    for (var i = 0; i < allUsers.length; i++) {


        var div = document.createElement("div");

        allHr.appendChild(div);

        div.className = "w3-card w3-round-small w3-large w3-hover-blue-gray item";
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
};


var placeUserDetails = function (event) {

    var items = document.getElementsByClassName('item');

    for (var i = 0; i < items.length; i++) {

        items[i].classList.remove("w3-blue-gray");
    }

    var element = event.srcElement || event.target;
    index = element.id;

    if (index == '') {

        element = event.currentTarget;
        index = element.id;
    }

    element.classList.add("w3-blue-gray");

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

    var clickedUser = allDBUsers[index];

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

/**
 *
 * @param {type} myNode
 * @returns {undefined}
 */
var removeChild = function (myNode) {

    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
};

/**
 *
 * @param {type} index
 * @returns {undefined}
 */
var placeRoleAndAccess = function (index) {

    makeAccessArray();

    user = allDBUsers[index];
    var roleDiv = document.getElementById('userRole');
    var accessDiv = document.getElementById('userAccess');

    removeChild(roleDiv);
    removeChild(accessDiv);

    for (var i in roles) {

        var radio = document.createElement("input");
        radio.type = "radio";
        radio.name = "role";
        radio.onchange = changeRole;
        radio.value = roles[i].id;

        if (user.roleId == roles[i].id) {

            radio.checked = true;
        }

        var label = document.createElement("label");
        label.innerHTML = roles[i].role;

        var br = document.createElement("br");

        roleDiv.appendChild(radio);
        roleDiv.appendChild(label);
        roleDiv.appendChild(br);
    }

    for (var i in user.access) {

        accessArray[user.access[i].id].checked = true;
    }

    for (var i in access) {

        var checkBox = document.createElement("input");
        checkBox.type = "checkbox";
        checkBox.name = "access";
        checkBox.checked = accessArray[access[i].id].checked;
        checkBox.value = access[i].id;
        checkBox.onchange = updateAccess;

        var label = document.createElement("label");
        label.innerHTML = accessArray[access[i].id].value;

        var br = document.createElement("br");

        accessDiv.appendChild(checkBox);
        accessDiv.appendChild(label);
        accessDiv.appendChild(br);
    }
};

/**
 *
 * @param {type} event
 * @returns {undefined}
 */
var changeRole = function (event) {

    var element = event.srcElement || event.target;

    clickedRole = element.value;

    var url = "/office_resource_management/api/service/office/hr/role/change";
    var method = "POST";
    var params = "hr_id=" + user.id + "&role_id=" + element.value;

    updateRoleAJAX(url, method, params);
};

/**
 *
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var updateRoleAJAX = function (url, method, params) {

    var msgDiv = document.getElementById('messageDiv');
    var msg = document.getElementById('msg');

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var response = JSON.parse(this.responseText);

            if (response.change == "true") {

                msgDiv.style.backgroundColor = "#42f48f";
                msg.innerHTML = "Role is updated successfully";
                msgDiv.style.display = "block";

                var url = "/office_resource_management/api/service/office/hr";
                var method = "GET";

                userFetchAJAX(url, method, null);

                placeDefaultAccess();

            } else if (response.change == "false") {

                msgDiv.style.backgroundColor = "#f44646";
                msg.innerHTML = "Role cannot be updated successfully";
                msgDiv.style.display = "block";
            }

            setTimeout(removeMessage, 3000);

        } else if (this.readyState == 4 && this.status != 200) {

            msgDiv.style.backgroundColor = "#f44646";
            msg.innerHTML = "Role cannot be updated successfully";
            msgDiv.style.display = "block";

            setTimeout(removeMessage, 3000);
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
var removeMessage = function () {

    document.getElementById('messageDiv').style.display = "none";
};

/**
 *
 * @returns {undefined}
 */
var placeDefaultAccess = function () {

    makeAccessArray();

    var accessDiv = document.getElementById('userAccess');
    removeChild(accessDiv);

    for (var v in roles) {

        if (roles[v].id == clickedRole) {

            break;
        }
    }

    for (var i in roles[v].accessTypes) {

        accessArray[roles[v].accessTypes[i].id].checked = true;
    }

    for (var i in access) {

        var checkBox = document.createElement("input");
        checkBox.type = "checkbox";
        checkBox.name = "access";
        checkBox.checked = accessArray[access[i].id].checked;
        checkBox.value = access[i].id;
        checkBox.onchange = updateAccess;

        var label = document.createElement("label");
        label.innerHTML = accessArray[access[i].id].value;

        var br = document.createElement("br");

        accessDiv.appendChild(checkBox);
        accessDiv.appendChild(label);
        accessDiv.appendChild(br);
    }
};

/**
 *
 * @returns {undefined}
 */
var updateAccess = function (event) {

    var element = event.srcElement || event.target;

    var msgDiv = document.getElementById('messageDiv');
    var msg = document.getElementById('msg');

    var url;
    if (element.checked) {

        url = "/office_resource_management/api/service/office/hr/access/add";
    } else {

        url = "/office_resource_management/api/service/office/hr/access/remove";
    }

    var method = "POST";
    var params = "hr_id=" + user.id + "&access_id=" + element.value;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var response = JSON.parse(this.responseText);

            if (response.change == "true") {

                msgDiv.style.backgroundColor = "#42f48f";
                if (element.checked) {

                    msg.innerHTML = "Access is added successfully";
                } else {

                    msg.innerHTML = "Access is removed successfully";
                }
                msgDiv.style.display = "block";

                var url = "/office_resource_management/api/service/office/hr";
                var method = "GET";

                userFetchAJAX(url, method, null);

            } else if (response.change == "false") {

                msgDiv.style.backgroundColor = "#f44646";
                msg.innerHTML = "Access cannot be updated successfully";
                msgDiv.style.display = "block";
            }

            setTimeout(removeMessage, 3000);

        } else if (this.readyState == 4 && this.status != 200) {

            msgDiv.style.backgroundColor = "#f44646";
            msg.innerHTML = "Access cannot be updated successfully";
            msgDiv.style.display = "block";

            setTimeout(removeMessage, 3000);
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
var makeUserNameArray = function () {

    for (var i in allDBUsers) {

        userNameArray[i] = allDBUsers[i].firstName + " " + allDBUsers[i].lastName;
    }
};

/**
 *
 * @returns {undefined}
 */
var placeSearchedUser = function () {

    var key = document.getElementById('userSearch').value;
    var uIndex = userNameArray.indexOf(key);

    if (uIndex >= 0) {

        allUsers = [];
        allUsers[0] = allDBUsers[uIndex];



        placeUsers();
        singleUserInfo(uIndex);
        placeRoleAndAccess(uIndex);
    }
};

/**
 *
 * @returns {undefined}
 */
var clearSearch = function () {

    var value = document.getElementById('userSearch').value;

    if (value == '') {

        allUsers = allDBUsers;
        placeUsers();
        singleUserInfo(0);
        placeRoleAndAccess(0);
    }
};


/**
 *
 * @returns {undefined}
 */
var addHumanResource = function () {

    var firstName = document.getElementById('addFirstname');
    var lastName = document.getElementById('addLastname');
    var email = document.getElementById('addEmail');
    var phone = document.getElementById('addPhone');
    var department = document.getElementById('addDepartment').value;
    var index = document.getElementById('addDesignation').selectedIndex;

    if (firstName.value != ''
            && lastName.value != ''
            && email.value != ''
            && phone.value != '') {

        //todo code to insert user

        var user = {};

        user.firstName = firstName.value;
        user.lastName = lastName.value;
        user.email = email.value;
        user.phone = phone.value;
        user.department = department;

        var url = "/office_resource_management/api/service/office/hr";
        var method = "POST";
        var params = "user=" + JSON.stringify(user) + "&type_id=" + designations[index].id;

        addUserAJAX(url, method, params);

    } else {

        if (firstName.value == '') {

            firstName.classList.remove("w3-theme-l4");
            firstName.style.backgroundColor = "#f44646";
        }

        if (lastName.value == '') {

            lastName.classList.remove("w3-theme-l4");
            lastName.style.backgroundColor = "#f44646";
        }

        if (email.value == '') {

            email.classList.remove("w3-theme-l4");
            email.style.backgroundColor = "#f44646";
        }

        if (phone.value == '') {

            phone.classList.remove("w3-theme-l4");
            phone.style.backgroundColor = "#f44646";
        }
    }
};


/**
 *
 * @returns {undefined}
 */
var checkField = function (element) {

    if (element.value == '') {

        element.classList.remove("w3-theme-l4");
        element.style.backgroundColor = "#f44646";
    } else {

        if (element.id != "addEmail") {

            element.style.backgroundColor = "";
            element.classList.add("w3-theme-l4");
        } else if (element.value.indexOf("@") == -1) {

            element.classList.remove("w3-theme-l4");
            element.style.backgroundColor = "#f44646";
        } else {

            element.style.backgroundColor = "";
            element.classList.add("w3-theme-l4");
        }
    }
};


/**
 *
 * @returns {undefined}
 */
var addUserAJAX = function (url, method, params) {

    document.getElementById('id01').style.display = "none";

    var msgDiv = document.getElementById('messageDiv');
    var msg = document.getElementById('msg');

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var response = JSON.parse(this.responseText);

            if (response.add == "true") {

                msgDiv.style.backgroundColor = "#42f48f";
                msg.innerHTML = "User added successfully";
                msgDiv.style.display = "block";

                var url = "/office_resource_management/api/service/office/hr";
                var method = "GET";

                userFetchAJAX(url, method, null);

            } else if (response.add == "false") {

                msgDiv.style.backgroundColor = "#f44646";
                msg.innerHTML = "User cannot be added successfully";
                msgDiv.style.display = "block";
            }

            setTimeout(removeMessage, 3000);

        } else if (this.readyState == 4 && this.status != 200) {

            msgDiv.style.backgroundColor = "#f44646";
            msg.innerHTML = "User cannot be added successfully";
            msgDiv.style.display = "block";

            setTimeout(removeMessage, 3000);
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);
};
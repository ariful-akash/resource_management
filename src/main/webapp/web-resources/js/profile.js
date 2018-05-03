var currentUser;
var preValue = [];
var eventTrigger = [];

/**
 *
 * @returns {undefined}
 */
var getUserInfo = function () {

    var url = "/office_resource_management/api/service/office/hr/current";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            currentUser = JSON.parse(this.responseText);

            placeuserInfo();
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
var placeuserInfo = function () {

    document.getElementById('firstname').value = currentUser.firstName;
    document.getElementById('lastname').value = currentUser.lastName;
    document.getElementById('email').value = currentUser.email;
    document.getElementById('phone').value = currentUser.phone;
    document.getElementById('department').value = currentUser.department;

    var ul = document.getElementById('access');

    for (var i in currentUser.access) {

        var li = document.createElement("li");
        li.innerHTML = "&#x2713; " + currentUser.access[i].description;

        ul.appendChild(li);
    }
};

/**
 *
 * @param {type} id
 * @returns {undefined}
 */
var makeEditable = function (id) {

    var element = document.getElementById(id);
    preValue[id] = element.value;

    if (element.disabled == true) {

        element.disabled = false;
        element.classList.remove("w3-theme-l4");
        element.classList.add("w3-light-green");
        element.classList.add("w3-card-4");

    } else {

        element.disabled = true;
        element.classList.remove("w3-card-4");
        element.classList.remove("w3-light-green");
        element.classList.add("w3-theme-l4");
    }
};

/**
 *
 * @returns {undefined}
 */
var updateUserinfo = function (id) {


    var element = document.getElementById(id);
    var value = preValue[id];

    if (value != element.value) {

        var go = confirm("Update you info:"
                + "\n\nPrevious " + id + " : " + value + "\n"
                + "Updated " + id + " : " + element.value);

        if (!go) {

            element.value = value;
            makeEditable(id);
        } else {


            var url = "/office_resource_management/api/service/office/hr/change/" + id;
            var method = "POST";
            var params = "value=" + element.value;

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {

                console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

                if (this.readyState == 4 && this.status == 200) {

                    var response = JSON.parse(this.responseText);
                    makeEditable(id);

                    var msgDiv = document.getElementById('messageDiv');
                    var msg = document.getElementById('msg');

                    if (response.update == "true") {

                        msgDiv.style.backgroundColor = "#42f48f";
                        msg.innerHTML = id + " is updated successfully";
                        msgDiv.style.display = "block";
                    } else {

                        msgDiv.style.backgroundColor = "#f44646";
                        msg.innerHTML = id + " is not updated successfully!!";
                        msgDiv.style.display = "block";
                        element.value = value;
                    }

                    setTimeout(removeMessage, 3000);
                }
            };

            xhttp.open(method, url, true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send(params);
        }

    } else {

        makeEditable(id);
    }
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
 * @param {type} event
 * @returns {undefined}
 */
var updateByEnter = function (event) {

    var code = event.keyCode || event.which;
    var element = event.srcElement || event.target;

    if (code == 13) {

        updateUserinfo(element.id);
    }
};

/**
 *
 * @returns {undefined}
 */
var changeConfBack = function () {

    var newpass = document.getElementById('newpass');
    var confpass = document.getElementById('confpass');

    if (newpass.value != confpass.value || confpass.value == '') {

        //red
        confpass.style.backgroundColor = "#d85656";
    } else {

        //green
        confpass.style.backgroundColor = "#8fd385";
    }
};
/**
 *
 * @returns {undefined}
 */
var changePassword = function () {

    var oldpass = document.getElementById('oldpass');
    var newpass = document.getElementById('newpass');
    var confpass = document.getElementById('confpass');

    if (oldpass != null && oldpass.value != ''
            && newpass != null && newpass.value != ''
            && confpass != null && confpass.value != ''
            && confpass.value == newpass.value) {

        document.getElementById('id01').style.display = 'none';

        var url = "/office_resource_management/api/service/office/hr/change/password";
        var method = "POST";
        var params = "oldpass=" + oldpass.value + "&newpass=" + newpass.value;

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {

            console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

            if (this.readyState == 4 && this.status == 200) {

                var response = JSON.parse(this.responseText);

                var msgDiv = document.getElementById('messageDiv');
                var msg = document.getElementById('msg');

                if (response.change == "true") {

                    msgDiv.style.backgroundColor = "#42f48f";
                    msg.innerHTML = "Password is updated successfully";
                    msgDiv.style.display = "block";
                } else {

                    msgDiv.style.backgroundColor = "#f44646";
                    msg.innerHTML = "Password is not updated successfully!!";
                    msgDiv.style.display = "block";
                }

                console.log(response);

                setTimeout(removeMessage, 3000);
            }
        };

        xhttp.open(method, url, true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(params);
    }
};
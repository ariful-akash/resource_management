var user;
var accessId = [];

var setMenu = function () {

    var url = "/office_resource_management/api/service/office/hr/current";
    var method = "GET";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            user = JSON.parse(this.responseText);
            setAccessId();
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();
};

var setAccessId = function () {

    var img = document.getElementById('menuImage');

    if (user.image == null) {

        img.src = "/office_resource_management/web-resources/images/dummy.jpg";
    } else {

        img.src = "data:image;base64," + user.image;
    }

    for (var i in user.access) {

        accessId.push(user.access[i].id);
    }

    removeMenu();
};

var removeMenu = function () {

    var statistics = document.getElementById('statistics');
    var manageHr = document.getElementById('manageHr');
    var manageOffice = document.getElementById('manageOffice');
    var incomingComplaints = document.getElementById('incomingComplaints');
    var incomingRequisitions = document.getElementById('incomingRequisitions');

    if (accessId.indexOf(19) == -1) {

        statistics.parentNode.removeChild(statistics);
    }

    if (accessId.indexOf(1) == -1
            && accessId.indexOf(2) == -1
            && accessId.indexOf(3) == -1) {

        manageHr.parentNode.removeChild(manageHr);
    }

    if (accessId.indexOf(4) == -1 || accessId.indexOf(18) == -1) {

        manageOffice.parentNode.removeChild(manageOffice);
    }

    if (incomingComplaints != null
            && accessId.indexOf(14) == -1
            && accessId.indexOf(15) == -1
            && accessId.indexOf(16) == -1
            && accessId.indexOf(17) == -1) {

        incomingComplaints.parentNode.removeChild(incomingComplaints);
    }

    if (incomingRequisitions != null
            && accessId.indexOf(8) == -1
            && accessId.indexOf(9) == -1
            && accessId.indexOf(10) == -1
            && accessId.indexOf(11) == -1
            && accessId.indexOf(12) == -1) {

        incomingRequisitions.parentNode.removeChild(incomingRequisitions);
    }
};

/**
 * Encode a string to a byte array
 *
 * @param {type} str
 * @returns {Array|toUTF8Array.utf8}
 */
var toUTF8Array = function (str) {
    var utf8 = [];
    for (var i = 0; i < str.length; i++) {
        var charcode = str.charCodeAt(i);
        if (charcode < 0x80)
            utf8.push(charcode);
        else if (charcode < 0x800) {
            utf8.push(0xc0 | (charcode >> 6),
                    0x80 | (charcode & 0x3f));
        } else if (charcode < 0xd800 || charcode >= 0xe000) {
            utf8.push(0xe0 | (charcode >> 12),
                    0x80 | ((charcode >> 6) & 0x3f),
                    0x80 | (charcode & 0x3f));
        }
        // surrogate pair
        else {
            i++;
            // UTF-16 encodes 0x10000-0x10FFFF by
            // subtracting 0x10000 and splitting the
            // 20 bits of 0x0-0xFFFFF into two halves
            charcode = 0x10000 + (((charcode & 0x3ff) << 10)
                    | (str.charCodeAt(i) & 0x3ff));
            utf8.push(0xf0 | (charcode >> 18),
                    0x80 | ((charcode >> 12) & 0x3f),
                    0x80 | ((charcode >> 6) & 0x3f),
                    0x80 | (charcode & 0x3f));
        }
    }
    return utf8;
};
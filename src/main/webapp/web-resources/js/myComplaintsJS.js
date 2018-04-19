/*Globa variables start*/

var pendingComlaints, solvedCoplaints, allComplaints;

/*Gobal variables end*/

/**
 *
 * @returns {undefined}
 */
var showAllComplaints = function () {

    if (allComplaints === undefined) {

        var url = "/office_resource_management/api/service/office/complaint";
        var method = "GET";

        fetchData(url, method, null);
    }

};

/**
 *
 * @returns {undefined}
 */
var placeAllComplaints = function (type) {

    if (allComplaints !== null && allComplaints !== undefined) {

        var pendingTab = document.getElementById(type);

        for (var i in allComplaints) {

            console.log(allComplaints.length);

            /*
             * Main container div after tab
             */
            var div1 = document.createElement("div");
            div1.className = "w3-row w3-card  w3-margin-top w3-margin-bottom";
            pendingTab.appendChild(div1);

            /*
             * Creating image node
             */
            var img = document.createElement("img");
            img.src = "${img}";
            img.className = "w3-circle";
            img.style.width = "30px";
            img.style.height = "30px";

            var imgDiv = document.createElement("div");
            imgDiv.className = "w3-col";
            imgDiv.style.width = "5%";
            imgDiv.style.padding = "1% 0% 0% 1%";
            imgDiv.appendChild(img);

            /*
             * Creating Name node
             */
            var labelNode = document.createElement("label");
            labelNode.className = "w3-small w3-text-dark-gray";
            labelNode.style.fontWeight = "bold";

            labelNode.textContent = allComplaints[i].creator.firstName + " " + allComplaints[i].creator.lastName;

            var nameDiv = document.createElement("div");
            nameDiv.className = "w3-col";
            nameDiv.style.width = "92%";
            nameDiv.style.marginTop = "1.5%";

            nameDiv.appendChild(labelNode);

            /*
             * Creating solve button div
             */
            var solveDiv = document.createElement("div");

            var solveSpan = document.createElement("span");
            solveSpan.className = "w3-btn w3-theme-l3 w3-round w3-text-white w3-medium w3-right";
            solveSpan.style.marginRight = "5%";
            solveSpan.textContent = "Solve";

            solveDiv.appendChild(solveSpan);

            /*
             * Creating table contents
             */

            var tableDiv = document.createElement("div");
            tableDiv.style.margin = "4% 5% 1% 5%";

            var table = document.createElement("table");

            var tableRowNo = 0;

            var placingDateRow = table.insertRow(tableRowNo++);
            var descriptionRow = table.insertRow(tableRowNo++);
            var creatorRow = table.insertRow(tableRowNo++);
            var solvedRow = table.insertRow(tableRowNo++);

            if (type == 'solved') {

                var solverRow = table.insertRow(tableRowNo++);
                var solvedDateRow = table.insertRow(tableRowNo++);
            }



            /*
             * Attaching all the child to the parent child
             */
            div1.appendChild(imgDiv);
            div1.appendChild(nameDiv);
            div1.appendChild(solveDiv);
            div1.appendChild(tableDiv);


        }
    }
};

/**
 *
 * @param {type} evt
 * @param {type} cityName
 * @returns {undefined}
 */
var changeTab = function (evt, cityName) {
    var i, x, tablinks;
    x = document.getElementsByClassName("tab");

    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablink");

    for (i = 0; i < x.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" w3-theme-d2", "");
    }

    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " w3-theme-d2";
};

/**
 *
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var fetchData = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            allComplaints = JSON.parse(this.responseText);
            placeAllComplaints('pending');
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);
};
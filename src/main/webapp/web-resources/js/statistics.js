/*Global variables*/

var graphOrList = {
    "hr": "graph",
    "or": "graph",
    "com": "graph",
    "req": "graph"
};

var month = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
];

var day = [
    "Saturday",
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednessday",
    "Thursday",
    "Friday"
];
/*End*/

/**
 *
 * @param {type} evt
 * @param {type} cityName
 * @returns {undefined}
 */
var changeStatTab = function (evt, cityName) {
    var i, x, tablinks;
    x = document.getElementsByClassName("tab");

    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablink");

    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" w3-theme-d2", "");
    }

    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " w3-theme-d2";
};

/**
 *
 * @param {type} areaType
 * @returns {undefined}
 */
var changePrintArea = function (areaType) {

    if (graphOrList[areaType] == "graph") {

        graphOrList[areaType] = "list";
    } else if (graphOrList[areaType] == "list") {

        graphOrList[areaType] = "graph";
    }
};

/***************************/
/* PDF printing code region*/
/***************************/

var doc = new jsPDF();
var specialElementHandlers = {
    '#editor': function (element, renderer) {
        return true;
    }
};

/**
 *
 * @param {type} type
 * @returns {undefined}
 */
var generateReport = function (type) {

    var user = getUser();
    var date = new Date();

    if (graphOrList[type] == "graph") {

        //Code for hr pdf
        if (type == "hr") {

            var canvas = $("#hrChartContainer .canvasjs-chart-canvas").get(0);
            var dataURL = canvas.toDataURL();

            var pdf = new jsPDF('p', 'pt', 'a4');

            //Report Name
            pdf.text("Report Title: Human Resource Report", 30, 40);

            //Who created repot
            pdf.setFontSize(10);
            pdf.text("Printed by:   "
                    + user.firstName + " "
                    + user.lastName, 30, 60);



            //when the report is created
            pdf.text("Printed on:   "
                    + date.getDate() + " " + month[date.getMonth()] + " " + date.getFullYear() + ", "
                    + day[date.getDay()] + ", "
                    + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), 30, 80);

            //Report graph image
            pdf.addImage(dataURL, 'JPEG', 30, 140, 550, 350);

            pdf.save("hr_graph_report.pdf");

        } else if (type == "or") {


            var floor = document.getElementById('orGraphFloor').value;
            var room = document.getElementById('orGraphRoom').value;

            var canvas = $("#orChartContainer .canvasjs-chart-canvas").get(0);
            var dataURL = canvas.toDataURL();

            var pdf = new jsPDF('p', 'pt', 'a4');

            //Report Name
            pdf.text("Report Title : Office Resource Report", 30, 40);
            pdf.text("Floor : " + floor, 30, 60);
            pdf.text("Room  : " + room, 30, 80);

            //Who created repot
            pdf.setFontSize(10);
            pdf.text("Printed by:   "
                    + user.firstName + " "
                    + user.lastName, 30, 100);



            //when the report is created
            pdf.text("Printed on:   "
                    + date.getDate() + " " + month[date.getMonth()] + " " + date.getFullYear() + ", "
                    + day[date.getDay()] + ", "
                    + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), 30, 120);

            //Report graph image
            pdf.addImage(dataURL, 'JPEG', 30, 180, 550, 350);

            pdf.save("or_graph_report.pdf");

        } else if (type == "com") {

            var year = document.getElementById('comGraphYear').value;
            var mon = document.getElementById('comGraphMonth').value;

            var canvas = $("#comChartContainer .canvasjs-chart-canvas").get(0);
            var dataURL = canvas.toDataURL();

            var pdf = new jsPDF('p', 'pt', 'a4');

            //Report Name
            pdf.text("Report Title : Complaints Report", 30, 40);
            pdf.text("Year : " + year, 30, 60);
            pdf.text("Month  : " + mon, 30, 80);

            //Who created repot
            pdf.setFontSize(10);
            pdf.text("Printed by:   "
                    + user.firstName + " "
                    + user.lastName, 30, 100);



            //when the report is created
            pdf.text("Printed on:   "
                    + date.getDate() + " " + month[date.getMonth()] + " " + date.getFullYear() + ", "
                    + day[date.getDay()] + ", "
                    + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), 30, 120);

            //Report graph image
            pdf.addImage(dataURL, 'JPEG', 30, 180, 550, 350);

            pdf.save("complaint_graph_report.pdf");

        } else if (type == "req") {

            var year = document.getElementById('reqGraphYear').value;
            var mon = document.getElementById('reqGraphMonth').value;

            var canvas = $("#reqChartContainer .canvasjs-chart-canvas").get(0);
            var dataURL = canvas.toDataURL();

            var pdf = new jsPDF('p', 'pt', 'a4');

            //Report Name
            pdf.text("Report Title : Requisitions Report", 30, 40);
            pdf.text("Year : " + year, 30, 60);
            pdf.text("Month  : " + mon, 30, 80);

            //Who created repot
            pdf.setFontSize(10);
            pdf.text("Printed by:   "
                    + user.firstName + " "
                    + user.lastName, 30, 100);



            //when the report is created
            pdf.text("Printed on:   "
                    + date.getDate() + " " + month[date.getMonth()] + " " + date.getFullYear() + ", "
                    + day[date.getDay()] + ", "
                    + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), 30, 120);

            //Report graph image
            pdf.addImage(dataURL, 'JPEG', 30, 180, 550, 350);

            pdf.save("requisition_graph_report.pdf");

        }
    } else if (graphOrList[type] == "list") {

        if (type == "hr") {

            var pdf = new jsPDF('p', 'pt', 'a4');

            //Report Name
            pdf.text("Report Title: Human Resource Report", 40, 40);

            //Who created repot
            pdf.setFontSize(10);
            pdf.text("Printed by:   "
                    + user.firstName + " "
                    + user.lastName, 40, 60);



            //when the report is created
            pdf.text("Printed on:   "
                    + date.getDate() + " " + month[date.getMonth()] + " " + date.getFullYear() + ", "
                    + day[date.getDay()] + ", "
                    + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), 40, 80);

            source = $('#hrListContainer')[0];
            specialElementHandlers = {
                '#bypassme': function (element, renderer) {
                    return true;
                }
            };
            margins = {
                top: 100,
                bottom: 60,
                left: 40,
                width: 522
            };
            pdf.fromHTML(
                    source, // HTML string or DOM elem ref.
                    margins.left, // x coord
                    margins.top, {// y coord
                        'width': margins.width, // max width of content on PDF
                        'elementHandlers': specialElementHandlers
                    });

            pdf.save("hr_list_report.pdf");

        } else if (type == "or") {

            var floor = document.getElementById('orGraphFloor').value;
            var room = document.getElementById('orGraphRoom').value;

            var pdf = new jsPDF('p', 'pt', 'a4');
            //Report Name
            pdf.text("Report Title: Office Resource Report", 40, 40);
            pdf.text("Floor : " + floor, 40, 60);
            pdf.text("Room  : " + room, 40, 80);

            //Who created repot
            pdf.setFontSize(10);
            pdf.text("Printed by:   "
                    + user.firstName + " "
                    + user.lastName, 40, 100);



            //when the report is created
            pdf.text("Printed on:   "
                    + date.getDate() + " " + month[date.getMonth()] + " " + date.getFullYear() + ", "
                    + day[date.getDay()] + ", "
                    + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), 40, 120);

            source = $('#orListContainer')[0];
            specialElementHandlers = {
                '#bypassme': function (element, renderer) {
                    return true;
                }
            };
            margins = {
                top: 140,
                bottom: 60,
                left: 40,
                width: 522
            };
            pdf.fromHTML(
                    source, // HTML string or DOM elem ref.
                    margins.left, // x coord
                    margins.top, {// y coord
                        'width': margins.width, // max width of content on PDF
                        'elementHandlers': specialElementHandlers
                    });

            pdf.save("or_list_report.pdf");

        } else if (type == "com") {

            var year = document.getElementById('comGraphYear').value;
            var mon = document.getElementById('comGraphMonth').value;

            var pdf = new jsPDF('p', 'pt', 'a4');
            //Report Name
            pdf.text("Report Title: Complaints Report", 40, 40);
            pdf.text("Year : " + year, 40, 60);
            pdf.text("Month  : " + mon, 40, 80);

            //Who created repot
            pdf.setFontSize(10);
            pdf.text("Printed by:   "
                    + user.firstName + " "
                    + user.lastName, 40, 100);



            //when the report is created
            pdf.text("Printed on:   "
                    + date.getDate() + " " + month[date.getMonth()] + " " + date.getFullYear() + ", "
                    + day[date.getDay()] + ", "
                    + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), 40, 120);

            source = $('#comListContainer')[0];
            specialElementHandlers = {
                '#bypassme': function (element, renderer) {
                    return true;
                }
            };
            margins = {
                top: 140,
                bottom: 60,
                left: 40,
                width: 522
            };
            pdf.fromHTML(
                    source, // HTML string or DOM elem ref.
                    margins.left, // x coord
                    margins.top, {// y coord
                        'width': margins.width, // max width of content on PDF
                        'elementHandlers': specialElementHandlers
                    });

            pdf.save("complaint_list_report.pdf");

        } else if (type == "req") {

            var year = document.getElementById('reqGraphYear').value;
            var mon = document.getElementById('reqGraphMonth').value;

            var pdf = new jsPDF('p', 'pt', 'a4');
            //Report Name
            pdf.text("Report Title: Requisitions Report", 40, 40);
            pdf.text("Year : " + year, 40, 60);
            pdf.text("Month  : " + mon, 40, 80);

            //Who created repot
            pdf.setFontSize(10);
            pdf.text("Printed by:   "
                    + user.firstName + " "
                    + user.lastName, 40, 100);



            //when the report is created
            pdf.text("Printed on:   "
                    + date.getDate() + " " + month[date.getMonth()] + " " + date.getFullYear() + ", "
                    + day[date.getDay()] + ", "
                    + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), 40, 120);

            source = $('#reqListContainer')[0];
            specialElementHandlers = {
                '#bypassme': function (element, renderer) {
                    return true;
                }
            };
            margins = {
                top: 140,
                bottom: 60,
                left: 40,
                width: 522
            };
            pdf.fromHTML(
                    source, // HTML string or DOM elem ref.
                    margins.left, // x coord
                    margins.top, {// y coord
                        'width': margins.width, // max width of content on PDF
                        'elementHandlers': specialElementHandlers
                    });

            pdf.save("requisition_list_report.pdf");
        }
    }
};

/**
 *
 * @param {type} areaId
 * @returns {undefined}
 */
var htmlToPDF = function (areaId) {

    var doc = new jsPDF();


    doc.fromHTML($('#' + areaId).html(), 15, 15, {
        'width': 170
    });

    doc.save('sample-file.pdf');
};
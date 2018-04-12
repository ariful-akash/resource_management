/**
 * Testing method to insert post by service
 * @returns {undefined}
 */
var insertPost = function () {

    var post = {};
    var tags = [];

    post.content = "hello its a test post";
    post.postTime = new Date();

    tags[0] = "food";
    tags[1] = "canteen";

    var postJson = JSON.stringify(post);
    var tagsJson = JSON.stringify(tags);
    var posterId = 105;


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var result = JSON.parse(this.responseText);

            console.log(result);

        }
    };

    xhttp.open("POST", "/office_resource_management/api/service/forum/post/add", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("post=" + postJson + "&poster=" + posterId + "&tags=" + tagsJson);
};

/**
 * Post updating method
 * @returns {undefined}
 */
var editPost = function () {

    var content = "Changed Content";
    var postid = 2002;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var result = JSON.parse(this.responseText);

            console.log(result);

        }
    };

    xhttp.open("POST", "/office_resource_management/api/service/forum/post/edit", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("content=" + content + "&postid=" + postid);
};


/**
 *
 * @returns {undefined}
 */
var insertComment = function () {

    var comment = {};

    comment.content = "This is a test comment insert!!!";
    comment.commentTime = new Date();
    comment.edited = false;
    comment.commenterId = 140;
    comment.postId = 1200;

    var commentJson = JSON.stringify(comment);

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var result = JSON.parse(this.responseText);

            console.log(result);

        }
    };

    xhttp.open("POST", "/office_resource_management/api/service/forum/comment/add", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("comment=" + commentJson);
};


/**
 *
 * @returns {undefined}
 */
var editComment = function () {

    var commentid = 12004;
    var content = "This is changed content";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var result = JSON.parse(this.responseText);

            console.log(result);

        }
    };

    xhttp.open("POST", "/office_resource_management/api/service/forum/comment/edit", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("commentid=" + commentid + "&content=" + content);
};


/**
 *
 * @returns {undefined}
 */
var addCommentReply = function () {

    var reply = {};

    reply.reply = "This is a test comment reply insertion";
    reply.dateTime = new Date();
    reply.edited = false;
    reply.replierId = 130;
    reply.commentId = 166;

    var replyJson = JSON.stringify(reply);

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var result = JSON.parse(this.responseText);

            console.log(result);

        }
    };

    xhttp.open("POST", "/office_resource_management/api/service/forum/reply/add", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("reply=" + replyJson);
};

/**
 *
 * @returns {undefined}
 */
var editCommentReply = function () {

    var replyid = 36004;
    var reply = "This is modified comment reply!! ping!!!";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var result = JSON.parse(this.responseText);

            console.log(result);

        }
    };

    xhttp.open("POST", "/office_resource_management/api/service/forum/reply/edit", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("replyid=" + replyid + "&reply=" + reply);
};

var insertComplaint = function () {

    var complaint = {};
    var type = {};
    var remarks = {};

    remarks.urgent = true;
    remarks.quantity = 6;
    remarks.funny = "haha, LOL";

    var remarksJson = JSON.stringify(remarks);

    type.id = 12;
    type.type = "computer lab table";

    complaint.isSolved = false;
    complaint.complaintPlacingDate = new Date();
    complaint.complaintSolvedDate = new Date();
    complaint.description = "This is a dummy complaint";
    complaint.creatorId = 135;

    complaint.remarks = remarksJson;

    complaint.type = type;

    var complaintJson = JSON.stringify(complaint);

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var result = JSON.parse(this.responseText);

            console.log(result);

        }
    };

    xhttp.open("POST", "/office_resource_management/api/service/office/complaint", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("complaint=" + complaintJson);
};
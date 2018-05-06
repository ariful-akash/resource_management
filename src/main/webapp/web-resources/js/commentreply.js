/**
 *
 * @param {type} event
 * @returns {undefined}
 */
var addComment = function (event) {

    var comment = {};

    var item = event.srcElement || event.target;
    var value = document.getElementById('comment').value;

    if (value != '') {

        comment.postId = item.id;
        comment.content = value;

        var url = "/office_resource_management/api/service/forum/comment/add";
        var method = "POST";
        var params = "comment=" + JSON.stringify(comment);

        insertCommentAJAX(url, method, params, item.id);
    }
};

/**
 *
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var insertCommentAJAX = function (url, method, params, postId) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            var reply = JSON.parse(this.responseText);

            if (reply.insert == "true") {

                var loading = document.getElementById('loading');
                var mainDiv = document.getElementById('allPostDiv');

                removeChild(mainDiv);
                loading.style.display = "block";
                document.getElementById('noContent').style.display = "none";

                var url = "/office_resource_management/api/service/forum/post/comment/reply/" + postId;
                var method = "GET";

                postCommentFetchAJAX(url, method, null);
            }
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);
};

/**
 *
 * @param {type} event
 * @returns {undefined}
 */
var addReply = function (event) {

    var reply = {};

    var item = event.srcElement || event.target;
    var replyContent = document.getElementById('reply' + item.id).value;

    var postId = item.parentNode.parentNode.parentNode.parentNode.parentNode.id;

    if (replyContent != '') {


        reply.commentId = item.id;
        reply.reply = replyContent;

        var url = "/office_resource_management/api/service/forum/reply/add";
        var method = "POST";
        var params = "reply=" + JSON.stringify(reply);

        insertCommentAJAX(url, method, params, postId);
    }
};
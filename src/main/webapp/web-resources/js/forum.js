var token;
var posts;
var post;
var postComment;
var allUsers;
var shortUsers;

/**
 *
 * @returns {undefined}
 */
var getRecentPosts = function () {

    var url = "/office_resource_management/api/service/forum/post";
    var method = "GET";

    postFetchAJAX(url, method, null);
};

var getOwnPosts = function () {

    var url = "/office_resource_management/api/service/forum/post/own";
    var method = "GET";

    postFetchAJAX(url, method, null);
};

var getUserPosts = function (e) {

    var target = e.currentTarget || e.srcElement;

    var url = "/office_resource_management/api/service/forum/post/user/" + target.id;
    var method = "GET";

    postFetchAJAX(url, method, null);
};

var getAllUsers = function () {
    var url = "/office_resource_management/api/service/office/hr";
    var method = "GET";

    userFetchAJAX(url, method, null);
};

var getPostComments = function (event) {

    var seemore = event.currentTarget || event.srcElement;

    console.log(seemore);

    var url = "/office_resource_management/api/service/forum/post/comment/reply/" + seemore.id;
    var method = "GET";

    postCommentFetchAJAX(url, method, null);
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
            shortUsers = allUsers;

            placeUsers();
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);

};

/*
 * 
 * @param {type} url
 * @param {type} method
 * @param {type} params
 * @returns {undefined}
 */
var postCommentFetchAJAX = function (url, method, params) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        console.log("Ready state : " + this.readyState + "\nStatus : " + this.status);

        if (this.readyState == 4 && this.status == 200) {

            postComment = JSON.parse(this.responseText);

            console.log(postComment);

            placePostComment();
        }
    };

    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params);

};

var removeChild = function (myNode) {

    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
};

/**
 * place the posts from json object, posts
 * @returns {undefined}
 */

var placePost = function () {

    /*
     * all post div
     */
    var allPostDiv = document.getElementById('allPostDiv');

// remove all contents from post div
    removeChild(allPostDiv);

    for (var i = 0; i < posts.length; i++) {

        /*
         * Single post div
         */
        var postDiv = document.createElement("div");
        postDiv.className = "w3-row w3-card w3-margin w3-padding";

        if (posts.length == 1) {

            //creating id attr
            var singlePostIdAttr = document.createAttribute("id");
            singlePostIdAttr.value = "postCommentDiv";

            //attaching id attr
            postDiv.setAttributeNode(singlePostIdAttr);
        }


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
        nameLabel.style.fontWeight = "900";
        nameLabel.textContent = posts[i].poster.firstName + " " + posts[i].poster.lastName;


        //break tag
        var breakTag = document.createElement("br");

        //date label tag

        var dateLabel = document.createElement("label");
        dateLabel.className = "w3-tiny w3-text-dark-gray";
        dateLabel.textContent = posts[i].postTime;

        /*
         * Attaching labels to name div
         */
        nameDiv.appendChild(nameLabel);
        nameDiv.appendChild(breakTag);
        nameDiv.appendChild(dateLabel);

        /************************************
         * Post main contant div
         */
        var contentDiv = document.createElement("div");
        contentDiv.style.margin = "8% 5% 1% 5%";

        //contant span tag
        var contentSpan = document.createElement("span");
        contentSpan.textContent = posts[i].content;


        if (posts.length > 1) {


            //see more anchore tag
            var seeMore = document.createElement("a");
            seeMore.className = "w3-text-white";
            seeMore.href = "#";
            seeMore.textContent = "See More";
            seeMore.onclick = getPostComments;

            //creating id attribute for see more
            var idAttr = document.createAttribute("id");
            idAttr.value = posts[i].id;

            //attaching id attr to see more
            seeMore.setAttributeNode(idAttr);

        }

        //break tag
        var breakTag2 = document.createElement("br");

        /*
         * Attaching post content and see more anchore to div
         */
        contentDiv.appendChild(contentSpan);
        contentDiv.appendChild(breakTag2);

        if (posts.length > 1) {

            contentDiv.appendChild(seeMore);
        }


        var tagDiv = document.createElement("div");
        for (var j = 0; j < posts[i].tags.length; j++) {
            var tagsLabel = document.createElement("label");
            tagsLabel.className = "w3-small w3-text-dark-gray w3-theme-l3 w3-padding-small";
            tagsLabel.style.marginLeft = "2%";
            tagsLabel.textContent = posts[i].tags[j].tag;

            tagDiv.appendChild(tagsLabel);
        }

        /*
         * Attaching all the divs to post div
         *
         */
        postDiv.appendChild(imgDiv);
        postDiv.appendChild(nameDiv);
        postDiv.appendChild(contentDiv);
        postDiv.appendChild(tagDiv);
    }
};

/*
 * place all the users from database to right div
 */

var placeUsers = function () {
    /*
     * all users div
     */
    var allusersDiv = document.getElementById('allUsersDiv');

    // remove all contents from post div
    removeChild(allusersDiv);


    for (var i = 0; i < shortUsers.length; i++) {

        /*
         * Single user div
         */
        var userDiv = document.createElement("div");
        userDiv.className = "w3-row w3-padding w3-round-small w3-hover-blue-grey";
        userDiv.onclick = getUserPosts;

        /*
         * Creating id attribute
         */
        var idAttr = document.createAttribute("id");
        idAttr.value = shortUsers[i].id.toString();

        //adding id attribute to single user div
        userDiv.setAttributeNode(idAttr);

        /**************************
         * Image div contains image
         *
         */
        var imgDiv = document.createElement("div");
        imgDiv.className = "w3-col";
        imgDiv.style.width = "10%";
        imgDiv.style.marginRight = "15%";

        //image tag

        var image = document.createElement("img");
        image.src = "";
        image.className = "w3-circle";
        image.style.height = "30px";
        image.style.width = "30px";

        /*
         *
         * attaching image to imgDiv
         *
         */

        imgDiv.appendChild(image);

        /********************
         * Name and designation placing div
         *
         */
        var nameDiv = document.createElement("div");
        nameDiv.className = "w3-col";
        nameDiv.style.width = "75%";

        //name label tag

        var nameLabel = document.createElement("label");
        nameLabel.textContent = shortUsers[i].firstName + " " + shortUsers[i].lastName;

        //break tag
        var breakTag = document.createElement("br");

        //designation label tag

        var designationLabel = document.createElement("label");
        designationLabel.className = "w3-small";
        designationLabel.textContent = shortUsers[i].designation;


        /*
         * attaching name and designation to nameDiv
         */
        nameDiv.appendChild(nameLabel);
        nameDiv.appendChild(breakTag);
        nameDiv.appendChild(designationLabel);

        /*
         * Attaching all the divs
         *
         */
        userDiv.appendChild(imgDiv);
        userDiv.appendChild(nameDiv);


        /*
         * Attaching user div to all users div
         */
        allusersDiv.appendChild(userDiv);

    }

};


var placePostComment = function () {

    posts = postComment;
    placePost();

    /*
     * all div
     */
    var postCommentDiv = document.getElementById("postCommentDiv");

    /*
     * all comments div
     */

    var allCommentDiv = document.createElement("div");

    // attaching comment div to postCommentDiv
    postCommentDiv.appendChild(allCommentDiv);

    /*
     * place Comment Div
     */

    var placeCommentDiv = document.createElement("div");
    placeCommentDiv.className = "w3-margin";
    placeCommentDiv.style.paddingLeft = "3%";

    /*
     * create input field for comment
     */
    var inputField = document.createElement("input");
    inputField.className = "w3-input w3-round-large";
    inputField.style.width = "60%";
    inputField.style.display = "inline";
    inputField.style.paddingLeft = "2%";
    inputField.type = "text";
    inputField.placeholder = "Write a Comment...";


    var inputButton = document.createElement("input");
    inputButton.className = "w3-btn w3-round w3-theme-d1";
    inputButton.style.display = "inline";
    inputButton.style.marginLeft = "1%";
    inputButton.value = "Comment";

    /*
     * append inputField && inputButton to placeCommentDiv
     */

    placeCommentDiv.appendChild(inputField);
    placeCommentDiv.appendChild(inputButton);

    // attaching place comment div to comment div

    allCommentDiv.appendChild(placeCommentDiv);

    for (var i = 0; i < postComment[0].comments.length; i++) {

        /*
         * single comment div
         */
        var commentDiv = document.createElement("div");
        commentDiv.className = "w3-row w3-margin";

        /**************************
         * Image div contains image
         *
         */
        var imgDiv = document.createElement("div");
        imgDiv.className = "w3-col";
        imgDiv.style.width = "4%";
        imgDiv.style.marginRight = "3%";
        imgDiv.style.padding = "1% 0% 0% 1%";

        //image tag

        var image = document.createElement("img");
        image.src = "";
        image.className = "w3-circle";
        image.style.height = "30px";
        image.style.width = "30px";

        /*
         *
         * attaching image to imgDiv
         *
         */

        imgDiv.appendChild(image);


        // attaching image div to comment div

        commentDiv.appendChild(imgDiv);

        /********************
         * Name and designation placing div
         *
         */
        var nameDiv = document.createElement("div");
        nameDiv.className = "w3-col";
        nameDiv.style.width = "92%";

        //name label tag

        var nameLabel = document.createElement("label");
        nameLabel.textContent = postComment[0].comments[i].commenter.firstName +
                " " + postComment[0].comments[i].commenter.lastName;
        nameLabel.className = "w3-small w3-text-dark-gray";
        nameLabel.style.fontWeight = "bold";

        //break tag
        var breakTag = document.createElement("br");

        // date label tag
        var dateLabel = document.createElement("label");
        dateLabel.textContent = postComment[0].comments[i].commentTime;
        dateLabel.className = "w3-tiny w3-text-dark-gray";

        // appending name and date to name div

        nameDiv.appendChild(nameLabel);
        nameDiv.appendChild(breakTag);
        nameDiv.appendChild(dateLabel);

        // appending name div  to comment div
        commentDiv.appendChild(nameDiv);

        /******
         * 
         * show all comments div and replys
         */
        var showCommentsDiv = document.createElement("div");
        showCommentsDiv.style.margin = "7% 5% 1% 3%";

        //attaching showCommentsDiv to commentDiv
        commentDiv.appendChild(showCommentsDiv);

        // showtext comment div
        var showTextCommentDiv = document.createElement("div");
        showTextCommentDiv.className = "w3-white w3-round-large w3-padding";

        //attaching showTextCommentDiv to showCommentsDiv
        showCommentsDiv.appendChild(showTextCommentDiv);

        // text span
        var textSpan = document.createElement("span");
        textSpan.textContent = postComment[0].comments[i].content;

        // appending textSpan to showTextCommentsDiv
        showTextCommentDiv.appendChild(textSpan);

        // comment replay div
        var commentReplayDiv = document.createElement("div");
        commentReplayDiv.style.marginLeft = "3%";

        // appending commentReplayDiv to showCommentsDiv
        showCommentsDiv.appendChild(commentReplayDiv);

        for (var j = 0; j < postComment[0].comments[i].commentReplys.length; j++) {

            //single reply div

            var singleCommentReplyDiv = document.createElement("div");
            singleCommentReplyDiv.className = "w3-row";

            // appending singleCommentReplyDiv to commentReplayDiv
            commentReplayDiv.appendChild(singleCommentReplyDiv);

            // reply img div

            var replyImgDiv = document.createElement("div");
            replyImgDiv.className = "w3-col";
            replyImgDiv.style.width = "3%";
            replyImgDiv.style.marginRight = "4%";
            replyImgDiv.style.padding = "1% 1% 0% 1%";

            //appending reply img div to single reply div
            singleCommentReplyDiv.appendChild(replyImgDiv);

            // img tag
            var replyImgTag = document.createElement("img");
            replyImgTag.src = "";
            replyImgTag.className = "w3-circle";
            replyImgTag.style.height = "30px";
            replyImgTag.style.width = "30px";


            // appending img tag to reply img div
            replyImgDiv.appendChild(replyImgTag);

            //reply name div
            var replyNameDiv = document.createElement("div");
            replyNameDiv.className = "w3-col";
            replyNameDiv.style.width = "92%";

            //appending reply name div to single reply div
            singleCommentReplyDiv.appendChild(replyNameDiv);

            // name label
            var replyNameLabel = document.createElement("label");
            replyNameLabel.className = "w3-small w3-text-dark-gray";
            replyNameLabel.textContent = postComment[0].comments[i].commentReplys[j].replier.firstName + " " +
                    postComment[0].comments[i].commentReplys[j].replier.lastName;
            replyNameLabel.style.fontWeight = "bold";

            // appending name label to reply name div 
            replyNameDiv.appendChild(replyNameLabel);

            //break tag
            var breakTag1 = document.createElement("br");

            //appending
            replyNameDiv.appendChild(breakTag1);

            // date label
            var replyDateLabel = document.createElement("label");
            replyDateLabel.className = "w3-tiny w3-text-dark-gray";
            replyDateLabel.textContent = postComment[0].comments[i].commentReplys[j].dateTime;

            // appending date label to reply name div 
            replyNameDiv.appendChild(replyDateLabel);


            //reply content div
            var replyContentDiv = document.createElement("div");
            replyContentDiv.className = "w3-white w3-round-large w3-padding";
            replyContentDiv.style.margin = "8% 2% 1% 2%";

            // appanding reply content div to single reply div
            singleCommentReplyDiv.appendChild(replyContentDiv);

            // reply content span
            var replyContentSpan = document.createElement("span");
            replyContentSpan.textContent = postComment[0].comments[i].commentReplys[j].reply;

            // appending reply content span to reply content div
            replyContentDiv.appendChild(replyContentSpan);

        }

        // reply input field div
        var replyInputFieldDiv = document.createElement("div");
        replyInputFieldDiv.className = "w3-margin";
        replyInputFieldDiv.style.paddingLeft = "2%";

        // appending reply input field div to Comment replay div
        commentReplayDiv.appendChild(replyInputFieldDiv);

        // input field
        var replyInputField = document.createElement("input");
        replyInputField.className = "w3-input w3-round-large";
        replyInputField.style.width = "60%";
        replyInputField.style.display = "inline";
        replyInputField.style.paddingLeft = "2%";
        replyInputField.type = "text";
        replyInputField.placeholder = "Write a reply...";

        // appending input field to reply input field div
        replyInputFieldDiv.appendChild(replyInputField);

        // input button
        var replyInputButton = document.createElement("input");
        replyInputButton.className = "w3-btn w3-round w3-theme-d1";
        replyInputButton.style.width = "100px";
        replyInputButton.style.display = "inline";
        replyInputButton.style.marginLeft = "1%";
        replyInputButton.value = "Comment";

        // appending input button to reply input field div
        replyInputFieldDiv.appendChild(replyInputButton);


        // appending comment div to allcomment div
        allCommentDiv.appendChild(commentDiv);
    }
};

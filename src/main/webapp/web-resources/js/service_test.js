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

    xhttp.open("POST", "/office_resource_management/forum/post/add", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("post=" + postJson + "&poster=" + posterId + "&tags=" + tagsJson);
};
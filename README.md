#URL List#

##Post Service##
Common url: /forum/post

*Inset a new post
-url(/add)
-POST method
-param(Post, Tags Array, posterId)
-in return there will be a json string confirming the insertion. 
i.e. {"insert" : "true"} or {"insert" : "false"}
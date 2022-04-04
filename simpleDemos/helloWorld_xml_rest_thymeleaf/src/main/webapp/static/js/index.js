$(document).ready(function () {
    $("#postAjax").click(function (event) {
        event.preventDefault();
        let url = this.href;
        $.post(url ,"hello lucia",function (user) {
            $("#result").html(user.username+":"+user.age+":"+user.message);
        });
    });

});
$(function() {
    var token = null;
    var userId = null;
    //alert("Please use this form to login");
    $("#getcars").hide();
    $("#carRow").hide();
    $("#signin").click(function (e) {
        e.preventDefault();
        jQuery.ajax ({
            url:  "/api/sessions",
            type: "POST",
            data: JSON.stringify({emailAddress:$("#inputEmail").val(), password: $("#inputPassword").val()}),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        })
            .done(function(data){
                $("#greeting").text("Hello " + data.content.firstName);
                $("#getcars").show();
                $("#carTable").find(".cloned").remove();
                token = data.content.token;
                userId = data.content.userId;
            })
            .fail(function(data){
                $("#greeting").text("You might want to try it again");
                $("#getcars").hide();
            })
    });

    $("#getcars").click(function (e) {

        e.preventDefault();
        console.log("you clicked get car");
        jQuery.ajax ({
            url:  "/api/drivers/" + userId + "/cars",
            type: "GET",
            beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", token);
            }
        })
            .done(function(data){
                data.content.forEach(function(item){
                    $( "#carRow" ).clone().prop("id",item.id).appendTo( "#carTable" );
                    $("#"+item.id).find("#make").text(item.make);
                    $("#"+item.id).find("#model").text(item.model);
                    $("#"+item.id).find("#year").text(item.year);
                    $("#"+item.id).prop("class","cloned");
                    $("#"+item.id).show();
                });
            })
            .fail(function(data){
                $("#carlist").text("Sorry no cars");
            })
    });
})



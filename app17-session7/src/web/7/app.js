$(function() {
    var token = null;
    var userId = null;
    var offset = 0;
    var count = 20;
    var total = -1;
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
        }).done(function(data){
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
        loadCars();
    });

    $("#next").click(function(e){
        e.preventDefault();
        if (offset+count < total) {
            offset = offset+count;
            loadCars();
        }
    })

    $("#previous").click(function(e){
        e.preventDefault();
        console.log("Cliked")
        if (offset-count >= 0) {
            offset = offset-count;
            loadCars();

        }
    })
    function loadCars() {
        jQuery.ajax ({
            url:  "/api/drivers/" + userId + "/cars?offset=" + offset + "&count="  + count,
            type: "GET",
            beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", token);
            }
        })
            .done(function(data){
                total = data.metadata.total;
                $("#page").text("Page " + Math.floor(offset/count+1) + " of " + (Math.ceil(total/count)));
                $("#carTable").find(".cloned").remove();
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

    }
})



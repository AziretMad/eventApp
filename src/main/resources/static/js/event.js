var prefix = "http://localhost:8080/event/"

var getSoon = function () {

    $.ajax({
        type: 'GET',
        url: prefix + 'soon',
        success: function (result) {
            console.log(result);
            var id = 1;
            $.each(result, function (index, value) {
                str = ".event" + "" + id;
                str1 = "#img" + "" + id;
                str2 = "http://localhost/img/" + "" +value.picture;
                $(str).html(value.name);
                $(str1).attr("src", str2);
                id+=1;
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("ERROR: ");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    })
};

var getSoon2 = function () {

    $.ajax({
        type: 'GET',
        url: prefix + 'soon',
        success: function (result) {
            console.log(result);
            var id = 1;
            $.each(result, function (index, value) {
                str = ".event" + "" + id;
                str1 = "#img" + "" + id;
                str2 = "" + value.picture;
                $(str).html(value.name);
                $(str1).attr("src", str2);
                id+=1;
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("ERROR: ");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    })
};


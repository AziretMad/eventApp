var prefix = "http://localhost:8080/user/"

var RestPost = function () {
    var firstName = $("input#first-name-value").val();
    var lastName = $("input#last-name-value").val();
    var email = $("input#email-value").val();
    var password = $("input#password-value").val();
    var login = firstName + ' ' + lastName;
    var jsonObject = {
        login: login,
        email: email,
        password: password
    };
    $.ajax({
        type: 'POST',
        url: prefix +'signup',
        dataType: 'json',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify(jsonObject),
        success: function (result) {
            console.log(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("ERROR: ");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
};

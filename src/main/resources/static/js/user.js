var prefix = "http://localhost:8080/user/"

var SignUp = function () {
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
var prefix1 = 'http://localhost:8080/auth/'
var SignIn = function () {
    var login = $("input#login-value").val();
    var password = $("input#password-value").val();
    var jsonObject= {
        login: login,
        password: password
    };
    $.ajax({
        type: 'POST',
        url: prefix1 +'signin',
        dataType: 'json',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify(jsonObject),
        success: function (result) {
            localStorage.setItem("Token", result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("ERROR: ");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    })
}

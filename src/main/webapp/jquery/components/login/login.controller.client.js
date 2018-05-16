(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;

    var userService = new UserServiceClient();

    $(main);

    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');

        $loginBtn = $('#loginBtn');
        $loginBtn.click(login);
    }

    function login () {
        console.log("login here");
        var user = {
            username: $usernameFld.val(),
            password: $passwordFld.val()
        };
        userService
            .login(user)
            .then(success)
    }

    function success(response) {
        console.log("success here");
        if (response == null) {
            alert('unable to login')
        } else {
            alert("success");
            goToProfile();
        }
    }

    function goToProfile() {
        var url = "../profile/profile.template.client.html";
        window.location.href = url;
    }



})();
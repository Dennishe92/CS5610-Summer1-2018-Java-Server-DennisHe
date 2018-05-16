(function() {
    $(init);

    var userService = new UserServiceClient();

    var $usernameFld
    var $phoneFld;
    var $emailFld;
    var $roleFld;
    var $dateOfBirthFld;

    var $updateBtn;
    var $logoutBtn;

    function init() {

        $usernameFld = $('#usernameFld');
        $phoneFld = $('#phoneFld');
        $emailFld = $('#emailFld');
        $roleFld = $('#roleFld');
        $dateOfBirthFld = $('#dateOfBirthFld');

        $updateBtn = $('#updateBtn');
        $updateBtn.click(updateProfile);

        $logoutBtn = $('#logoutBtn');
        $logoutBtn.click(logout);

        userService
            .getProfile()
            .then(fetchProfile);
    }

    function fetchProfile(user) {
        if(user != null) {
            $('#usernameFld').val(user.username);
            $('#phoneFld').val(user.phone);
            $('#emailFld').val(user.email);
            $('#roleFld').val(user.role);
            $("#dateOfBirthFld").val(parseISOString(user.dateOfBirth));
        }
    }

    function parseISOString(string) {
        var index = string.indexOf("T");
        return string.substring(0, index);
    }



    function updateProfile() {
        var date = new Date($dateOfBirthFld.val()).toISOString();
        // var date = $dateOfBirthFld.val();
        // date = data.substring(0, date.indexOf('T'));

        var user = {
            phone: $phoneFld.val(),
            email: $emailFld.val(),
            role: $roleFld.val(),
            dateOfBirth: date
        };

        userService
            .updateProfile(user)
            .then(success);
    }

    function success(response) {
        if (response === null) {
            alert('unable to update')
        } else {
            alert('Profile successfully saved')
        }
    }

    // function findUserById(userId) {
    //     userService
    //         .findUserById(userId)
    //         .then(renderUser);
    // }

    // function renderUser(user) {
    //     console.log(user);
    //     $usernameFld.val(user.username);
    //     $phoneFld.val(user.phone);
    //     $emailFld.val(user.email);
    //     $roleFld.val(user.role);
    //     $dateOfBirthFld.val(user.dateOfBirth);
    // }

    function logout() {
        console.log("logging out");

        userService
            .logout();

        var url = "../login/login.template.client.html";
        window.location.href = url;
    }

})();
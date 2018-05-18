(function() {
    $(init);

    var userService = new UserServiceClient();

    var $usernameFld;
    var $passwordFld;
    var $firstNameFld;
    var $lastNameFld;
    var $phoneFld;
    var $emailFld;
    var $roleFld;
    var $dateOfBirthFld;

    var $updateBtn;
    var $logoutBtn;

    function init() {

        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
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
            $('#passwordFld').val(user.password);
            $('#firstNameFld').val(user.firstName);
            $('#lastNameFld').val(user.lastName);
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
            password: $passwordFld.val(),
            firstName: $firstNameFld.val(),
            lastName: $lastNameFld.val(),
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
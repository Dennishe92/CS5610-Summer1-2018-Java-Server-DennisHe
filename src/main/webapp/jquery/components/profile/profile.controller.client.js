(function() {
    $(init);

    var userService = new UserServiceClient();

    var $staticEmail;
    var $firstName;
    var $lastName;
    var $updateBtn;

    function init() {
        $staticEmail = $('#staticEmail');
        $firstName = $('#firstName');
        $lastName = $('#lastName');
        $updateBtn = $('#updateBtn')
            .click(updateUser);

        findUserById(682);
    }

    function updateUser() {
        var user = {
            firstName: $firstName.val(),
            lastName: $lastName.val()
        };

        userService
            .updateUser(682, user)
            .then(success);
    }

    function success(response) {
        if (response === null) {
            alert('unable to update')
        } else {
            alert('success')
        }
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function renderUser(user) {
        console.log(user);
        $staticEmail.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);

    }

})();
(function () {

    $(main);

    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn, $finishBtn;
    var $firstNameFld, $lastNameFld;
    var $roleFld;
    var $userRowTemplate, $tbody;
    var userService = new UserServiceClient();

    function main() {
        $tbody = $('tbody');
        $userRowTemplate = $('.template');
        $createBtn = $('#createUser');
        $createBtn.click(createUser);

        findAllUsers();
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function createUser() {
        console.log('createUser');

        $usernameFld = $('#usernameFld').val();
        $passwordFld = $('#passwordFld').val();
        $firstNameFld = $('#firstNameFld').val();
        $lastNameFld = $('#lastNameFld').val();
        $roleFld = $('#roleFld').val();

        var user = {
            username: $usernameFld,
            password: $passwordFld,
            firstName: $firstNameFld,
            lastName: $lastNameFld,
            role: $roleFld
        };

        userService
            .createUser(user)
            .then(findAllUsers);

        $('#usernameFld').val("");
        $('#passwordFld').val("");
        $('#firstNameFld').val("");
        $('#lastNameFld').val("");
        $('#roleFld').val("");
    }

    function renderUsers(users) {
        console.log("rendering")
        $tbody.empty();

        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = $userRowTemplate.clone();

            clone.attr('id', user.id);

            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(updateUser);

            clone.find('.username').html(user.username);
            //clone.find('.password').html(user.password);
            clone.find('.firstName').html(user.firstName);
            clone.find('.lastName').html(user.lastName);
            clone.find('.role').html(user.role);

            $tbody.append(clone);
        }
    }

    function deleteUser(event) {
        console.log('deleteUser');
        console.log(event);
        $removeBtn = $(event.currentTarget);
        var userId = $removeBtn
            .parent()
            .parent()
            .parent()
            .attr('id');
        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function updateUser(event) {
        console.log('editUser');
        console.log(event);
        $editBtn = $(event.currentTarget);
        var userId = $editBtn
            .parent()
            .parent()
            .parent()
            .attr('id');
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function renderUser(user) {
        console.log("renderUser");
        // disable create while in edit mode
        $('#usernameFld').val(user.username);
        $('#passwordFld').val(user.password);
        $('#firstNameFld').val(user.firstName);
        $('#lastNameFld').val(user.lastName);
        $('#roleFld').val(user.role);

        $finishBtn = $('#editUser');
        $finishBtn.click(function () {
            console.log("editing user");
            $usernameFld = $('#usernameFld').val();
            $passwordFld = $('#passwordFld').val();
            $firstNameFld = $('#firstNameFld').val();
            $lastNameFld = $('#lastNameFld').val();
            $roleFld = $('#roleFld').val();

            var newInfo = {
                username: $usernameFld,
                password: $passwordFld,
                firstName: $firstNameFld,
                lastName: $lastNameFld,
                role: $roleFld
            };

            userService
                .updateUser(user.id, newInfo)
                .then(findAllUsers);

            $('#usernameFld').val("");
            $('#passwordFld').val("");
            $('#firstNameFld').val("");
            $('#lastNameFld').val("");
            $('#roleFld').val("");
        });
    }


})();
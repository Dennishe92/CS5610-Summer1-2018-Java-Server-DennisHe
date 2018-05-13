(function () {

    $(main);

    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
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
        var promise = fetch('http://localhost:8080/api/user');
        promise.then(function (response) {
            return response.json();
        }).then(renderUsers);
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
    }

    function renderUsers(users) {
        $tbody.empty();

        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = $userRowTemplate.clone();

            clone.attr('id', user.id);

            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(editUser);

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

    function editUser(event) {
        console.log('editUser');
        console.log(event);
    }

})();
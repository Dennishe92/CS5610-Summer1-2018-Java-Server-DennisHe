(function () {
   var $usernameFld, $passwordFld, $verifyPasswordFld;
   var $registerBtn;
   var userService = new UserServiceClient();

   $(main);

   function main() {

       $usernameFld = $('#usernameFld');
       $passwordFld = $('#passwordFld');
       $verifyPasswordFld = $('#verifyPasswordFld').val();

       $registerBtn = $('#registerBtn');
       $registerBtn.click(register);
   }

   function register() {
       console.log('registerUser');

       var user = {
           username: $usernameFld.val(),
           password: $passwordFld.val()
       }

       userService
           .register(user)
           .then(success);
   }

    function success(response) {
        if (response == null) {
            alert('username already exists')
        } else {
            alert('account created')
        }
    }
}) ();
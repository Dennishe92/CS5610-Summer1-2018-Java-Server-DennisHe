(function () {
   var $usernameFld, $passwordFld, $verifyPasswordFld;
   var $registerBtn;
   var userService = new UserServiceClient();

   $(main);

   function main() {

       $usernameFld = $('#usernameFld');
       $passwordFld = $('#passwordFld');
       $verifyPasswordFld = $('#verifyPasswordFld');

       $registerBtn = $('#registerBtn');
       $registerBtn.click(register);
   }

   function register() {
       if($passwordFld.val() != $verifyPasswordFld.val()) {
           alert("Please make sure your passwords match");
           $('#passwordFld').val("");
           $('#verifyPasswordFld').val("");
       } else {
           var user = {
               username: $usernameFld.val(),
               password: $passwordFld.val()
           }

           userService
               .register(user)
               .then(success);
       }
   }

    function success(response) {
        if (response == null) {
            alert('Username already exists. Please use another username.')
        } else {
            alert('Account created, please click login.')
        }
        $('#usernameFld').val("");
        $('#passwordFld').val("");
        $('#verifyPasswordFld').val("");
    }
}) ();
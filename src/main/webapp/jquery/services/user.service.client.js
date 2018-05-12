// "Service" responsibility is to communicate with the data base.
// Old ways of creating class and the functions that this class has.
function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;

    this.findUserById = findUserById;
    this.updateUser = updateUser;

    this.login = login;

    this.url =
        'http://localhost:8080/api/user';
    this.LOGIN_URL =
        'http://localhost:8080/api/login';

    var self = this;



    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
        });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        });
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response) {
                return response.json();
            });
    }

    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type' : 'application/json'
            }
        })
            .then(function(response) {
                if (response.bodyUsed) {
                    return reponse.json();
                } else {
                    return null;
                }
            });
    }

    function login(username, password) {
        return fetch(self.LOGIN_URL, {
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        });
    }


    //RESTFUL API
    // 'put' to update
    // 'post' to create
    // 'get' to read
    // 'delete' for removal



}
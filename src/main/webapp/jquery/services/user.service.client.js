// "Service" responsibility is to communicate with the data base.
// Old ways of creating class and the functions that this class has.
function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;

    this.findUserById = findUserById;
    this.updateUser = updateUser;

    this.register = register;

    this.login = login;

    this.updateProfile = updateProfile;
    this.getProfile = getProfile;
    this.logout = logout;


    this.url =
        'http://localhost:8080/api/user';
    this.REGISTER_URL =
        'http://localhost:8080/api/register';
    this.LOGIN_URL =
        'http://localhost:8080/api/login';
    this.PROFILE_URL =
        'http://localhost:8080/api/profile';



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
                if (response.status === 409) {
                    return null;
                } else {
                    return response.json();
                }
            });
    }

    function register(user) {
        return fetch(self.REGISTER_URL, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function(response) {
                if(response.status === 409) {
                    return null;
                } else {
                    return response.json();
                }
            });
    }

    function login(user) {
        return fetch(self.LOGIN_URL, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function(response) {
                if(response.status === 409) {
                    return null;
                } else {
                    //console.log(response.json());
                    return response.json();
                }
            });
    }


    function updateProfile(user) {
        return fetch(self.PROFILE_URL, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type' : 'application/json'
            }
        })
            .then(function(response) {
                if(response.status === 409) {
                    return null;
                } else {
                    return response.json();
                }
            });
    }

    function getProfile() {
        return fetch(self.PROFILE_URL)
            .then(function(response) {
                if(response.status === 409) {
                    return null;
                } else {
                    return response.json();
                }
        });
    }

    function logout() {
        return fetch(self.PROFILE_URL, {
            method: 'delete'
        });
    }

    //RESTFUL API
    // 'put' to update
    // 'post' to create
    // 'get' to read
    // 'delete' for removal

}
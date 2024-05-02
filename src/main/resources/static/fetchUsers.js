//Fetch data and update users
function fetchUsers() {
    fetch('/api/v1/Users')
    .then(response => response.json())
    .then(users => {
        //Handle the response data for users
        const userListElements = document.querySelectorAll('.userList');
        userListElements.forEach(userListElement => {
            userListElement.innerHTML = '';
            users.forEach(user => {
                const listUserItem = document.createElement('li');
                listUserItem.textContent = `ID: ${user.id}, Username: ${user.username}, Email: ${user.email}`;
                userListElement.appendChild(listUserItem);
            });
        });
    })
    .catch(error => console.error('Error fetching users:', error));
}
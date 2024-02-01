// Call the dataTables jQuery plugin
$(document).ready(function() {
});



async function registerUser(){
    let data = {};
    data.name = document.getElementById('txtName').value;
    data.lastName = document.getElementById('txtLastName').value;
    data.email = document.getElementById('txtEmail').value;
    data.phone = document.getElementById('txtPhone').value;
    data.password = document.getElementById('txtPassword').value;
    let passwordCorrect = document.getElementById('txtRepeatPassword').value;

    if(data.password != passwordCorrect){
        alert('Error password');
        return;
    }

    const request = await fetch('api/user', {
        method: 'POST',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'Application/json'
        },
        body: JSON.stringify(data)
    });
    alert("the Account was created successfully");
    window.location.href = 'login.html'
}
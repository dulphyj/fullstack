// Call the dataTables jQuery plugin
$(document).ready(function() {
   loadUsers();
  $('#users').DataTable();
  updateEmailUser();
});

function updateEmailUser(){
    document.getElementById('txt-user-email').outerHTML = localStorage.email;
}

async function loadUsers(){
    const request = await fetch('api/users',{
        method: 'GET',
        headers:getHeaders()
    });
    const users = await request.json();
    let listHtml = '';

    for(let user of users){
        let deleteButton ='<a href="#" onclick="deleteUser('+user.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
        let phone = user.phone == null ? '-' : user.phone
        let userHtml = '<tr><td>'+user.id+'</td><td>'+user.name
        +' '+user.lastName+' </td><td>'+user.email+'</td><td>'+phone
        +'</td><td>'+deleteButton+'</td></tr>';
        listHtml += userHtml;
    }
    console.log(users);
    document.querySelector('#users tbody').outerHTML = listHtml;
}

async function deleteUser(id){
    if(confirm('Delete this User?')){
        const request = await fetch('api/user/'+id,{
            method: 'DELETE',
            headers: getHeaders()
        });
        location.reload();
    }
}

function getHeaders(){
    return {
        'Accept': 'application/json',
        'Content-Type': 'Application/json',
        'Authorization': localStorage.token
    };
}
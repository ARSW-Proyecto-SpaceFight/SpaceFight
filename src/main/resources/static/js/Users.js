var host = "http://localhost:8080/spacefight/users"
//var host = "https://spacefightarsw.herokuapp.com/spacefight/users"

async function addUser(){
    //console.log(document.getElementById("username").value);
    await Promise.resolve(axios.post(host+"sign-up",{
        "username":document.getElementById("username").value,
        "password":document.getElementById("password").value
    }));
    window.location.href='login';
}
var host = "http://localhost:8080/spacefight/"
//var host = "https://spacefightarsw.herokuapp.com/spacefight/"


async function getRooms(){  
    var agregar = "";
    await Promise.resolve(axios.get(host)
	.then(async function(response){
                var data = response.data;
                var texto = document.getElementById("all");
                var agregar = "";
                texto.innerHTML = '';
		for(var i = 0; i<data.length;i++){
                    agregar += "<button \
                                style='color:white; font-size:1.3em; background-image: url(images/button.jpg)'\
                                onclick=openRoom(" + data[i] + ")>" + data[i] + "</button>";                   
                }
                texto.innerHTML = agregar;                
            }));           
}

async function createRoom(){
    var room = prompt("¿Cual será el numero de esa sala?")
    await Promise.resolve(axios.post(host+"?room="+room));
    await getRooms();
}

function openRoom(room){    
    sessionStorage.setItem('room', room)
    window.location.href='juego.html'
}



/*
*Modulo que contiene la informacion de las naves, meteoritos y la propia
*/
var ship = (function(){
        var username = prompt("¿Cual es su usuario?")
	var team = Math.floor((Math.random() * 2) + 1);	
	var allShips;
        var room = sessionStorage.room
	return{
		setUsername : function(usernameS){
			username = usernameS;
		},
		getUsername : function(){
			return username;
		},
                setTeam : function(teamS){
                        team = teamS;
                },
                getTeam : function(){
                        return team;
                },
                getRoom : function(){
                        return room;
                },
		setAllShips : function(ships){
			allShips = ships;
		},
		getAllShips : function(){
			return allShips;
		},               
		getJSON : function(){
			var jsonString = {
                                            "username" : username,
                                            "x" : (team === 1) ? 0 : (830-50),
                                            "y" : Math.floor((Math.random() *470) + 1),
                                            "team" : team
                                            }
			return jsonString;
		}
	}
})();


/*
*Comienza todo y pregunta constantemente el estado del juego
*/
async function start(){
        
	//if(await existsRooms() == false){
	//	await createNewRoom(1);
	//}
        await connectAndSubscribe();
	//while(true){		
	//	await pintar();
	//	await(2000);
	//}        
}

/*
*Accion cuando se presiona las teclas
*/
window.onkeydown = function(e) {
	var key = e.keyCode ? e.keyCode : e.which;
	if(key==32){
	    shoot();
	}
	else{
	    move(key);
	}

	//console.log(key);
}

/*
*Pinta el estado de juego
*/
async function pintar(){
	await getAllShips();
	var agregar = "";
	for(var i = 0; i < ship.getAllShips().length; i++ ){
            pintarNave(ship.getAllShips()[i]);
        }
	document.getElementById("all").innerHTML += agregar;
}

function moverNave(body){    
    nave = document.getElementById("ship"+body.username);   
    nave.style.top = body.y + "px"
    nave.style.left = body.x + "px"
    if(body.direction == "U"){
			if(body.team != ship.getTeam()){
				nave.src = "images/shipPlayer.png";
			}else{
				nave.src = "images/ship.png";
			}
		}else if(body.direction == "R"){
			if(body.team != ship.getTeam()){
				nave.src = "images/rightPlayer.png";
			}else{
				nave.src = "images/right.png";
			}			
		}else if(body.direction == "L"){
			if(body.team != ship.getTeam()){
				nave.src = "images/leftPlayer.png";
			}else{
				nave.src = "images/left.png";
			}				
		}else{
			if(body.team != ship.getTeam()){
				nave.src = "images/downPlayer.png";
			}else{
				nave.src = "images/down.png";
			}
			
		}
    nombreNave = document.getElementById("user"+body.username);  
    nombreNave.style.top = (body.y + body.shipSize) + "px"
    nombreNave.style.left = body.x + "px"
    vidaNave = document.getElementById("vida"+body.username);  
    vidaNave.style.top = (body.y) + "px"
    vidaNave.style.left = body.x + "px"
}

function pintarNave(body){
    agregar = ""
    agregar += "<img id='ship"+body.username+"' style='position:absolute; width:"+body.shipSize+"px; height:"+body.shipSize+"px; top:"+body.y+"px; left:"+body.x+"px'";
		if(body.direction == "U"){
			if(body.team != ship.getTeam()){
				agregar += " src='images/shipPlayer.png' ></img>";
			}else{
				agregar += " src='images/ship.png' ></img>";
			}
		}else if(body.direction == "R"){
			if(body.team != ship.getTeam()){
				agregar += " src='images/rightPlayer.png' ></img>";
			}else{
				agregar += " src='images/right.png' ></img>";
			}			
		}else if(body.direction == "L"){
			if(body.team != ship.getTeam()){
				agregar += " src='images/leftPlayer.png' ></img>";
			}else{
				agregar += " src='images/left.png' ></img>";
			}				
		}else{
			if(body.team != ship.getTeam()){
				agregar += " src='images/downPlayer.png' ></img>";
			}else{
				agregar += " src='images/down.png' ></img>";
			}
			
		}
    agregar += "<label id = 'user"+ body.username +"' style='position:absolute; top:"+(body.y + body.shipSize)+"px; left:"+body.x+"px'>"+body.username+"</label>"
    agregar += "<label id = 'vida"+ body.username +"' style='position:absolute; top:"+(body.y)+"px; left:"+body.x+"px'>"+body.health+"</label>"
    document.getElementById("all").innerHTML += agregar;
}

function eliminarNave(username){
    eliminarElemento("user"+username);
    eliminarElemento("vida"+username);
    eliminarElemento("ship"+username);
}

function eliminarElemento(elementId) {    
    var element = document.getElementById(elementId);
    element.parentNode.removeChild(element);
}
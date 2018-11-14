/*
*Modulo que contiene la informacion de las naves y la propia
*/
var ship = (function(){
        var username = prompt("¿Cual es su usuario?")
	var team = Math.floor((Math.random() * 2) + 1);	
	var allShips;
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
		setAllShips : function(ships){
			allShips = ships;
		},
		getAllShips : function(){
			return allShips;
		},
		getJSON : function(){
			var jsonString = {
                                            "username" : username,
                                            "x" : 0,
                                            "y" : 0,
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
	if(await existsRooms() == false){
		await createNewRoom(1);
	}
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
	move(key);   
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
			if(body.username == ship.getUsername()){
				nave.src = "images/shipPlayer.png";
			}else{
				nave.src = "images/ship.png";
			}
		}else if(body.direction == "R"){
			if(body.username == ship.getUsername()){
				nave.src = "images/rightPlayer.png";
			}else{
				nave.src = "images/right.png";
			}			
		}else if(body.direction == "L"){
			if(body.username == ship.getUsername()){
				nave.src = "images/leftPlayer.png";
			}else{
				nave.src = "images/left.png";
			}				
		}else{
			if(body.username == ship.getUsername()){
				nave.src = "images/downPlayer.png";
			}else{
				nave.src = "images/down.png";
			}
			
		}
}

function pintarNave(body){
    agregar = ""
    agregar += "<img id='ship"+body.username+"' style='position:absolute; width:"+body.shipSize+"px; height:"+body.shipSize+"px; top:"+body.y+"px; left:"+body.x+"px'";
		if(body.direction == "U"){
			if(body.username == ship.getUsername()){
				agregar += " src='images/shipPlayer.png' ></img>";
			}else{
				agregar += " src='images/ship.png' ></img>";
			}
		}else if(body.direction == "R"){
			if(body.username == ship.getUsername()){
				agregar += " src='images/rightPlayer.png' ></img>";
			}else{
				agregar += " src='images/right.png' ></img>";
			}			
		}else if(body.direction == "L"){
			if(body.username == ship.getUsername()){
				agregar += " src='images/leftPlayer.png' ></img>";
			}else{
				agregar += " src='images/left.png' ></img>";
			}				
		}else{
			if(body.username == ship.getUsername()){
				agregar += " src='images/downPlayer.png' ></img>";
			}else{
				agregar += " src='images/down.png' ></img>";
			}
			
		}
    document.getElementById("all").innerHTML += agregar;
}


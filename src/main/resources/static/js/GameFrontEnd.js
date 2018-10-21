/*
*Modulo que contiene la informacion de las naves y la propia
*/
var ship = (function(){
	var id;	
	var allShips;
	return{
		setID : function(idS){
			id = idS;
		},
		getID : function(){
			return id;
		},
		setAllShips : function(ships){
			allShips = ships;
		},
		getAllShips : function(){
			return allShips;
		},
		getJSON : function(){
			var jsonString = {
								"id" : id,
								"x" : 0,
								"y" : 0,
								"health" : 100
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
	await newShip();
	while(true){		
		await pintar();
		await(2000);
	}
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
		agregar += "<img id='ship"+ship.getAllShips()[i].id+"' style='position:absolute; width:"+ship.getAllShips()[i].shipSize+"px; height:"+ship.getAllShips()[i].shipSize+"px; top:"+ship.getAllShips()[i].y+"px; left:"+ship.getAllShips()[i].x+"px'";
		if(ship.getAllShips()[i].direction == "U"){
			if(ship.getAllShips()[i].id == ship.getID()){
				agregar += " src='images/shipPlayer.png' ></img>";
			}else{
				agregar += " src='images/ship.png' ></img>";
			}
		}else if(ship.getAllShips()[i].direction == "R"){
			if(ship.getAllShips()[i].id == ship.getID()){
				agregar += " src='images/rightPlayer.png' ></img>";
			}else{
				agregar += " src='images/right.png' ></img>";
			}			
		}else if(ship.getAllShips()[i].direction == "L"){
			if(ship.getAllShips()[i].id == ship.getID()){
				agregar += " src='images/leftPlayer.png' ></img>";
			}else{
				agregar += " src='images/left.png' ></img>";
			}				
		}else{
			if(ship.getAllShips()[i].id == ship.getID()){
				agregar += " src='images/downPlayer.png' ></img>";
			}else{
				agregar += " src='images/down.png' ></img>";
			}
			
		}
	}
	document.getElementById("all").innerHTML = agregar;
}

/*
*Modulo que contiene la informacion de las naves y la propia
*/
var ship = (function(){
	var id = Math.floor((Math.random() * 100) + 1);	
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
    nave = document.getElementById("ship"+body.id);    
    nave.style.top = body.y + "px"
    nave.style.left = body.x + "px"
    if(body.direction == "U"){
			if(body.id == ship.getID()){
				nave.src = "images/shipPlayer.png";
			}else{
				nave.src = "images/ship.png";
			}
		}else if(body.direction == "R"){
			if(body.id == ship.getID()){
				nave.src = "images/rightPlayer.png";
			}else{
				nave.src = "images/right.png";
			}			
		}else if(body.direction == "L"){
			if(body.id == ship.getID()){
				nave.src = "images/leftPlayer.png";
			}else{
				nave.src = "images/left.png";
			}				
		}else{
			if(body.id == ship.getID()){
				nave.src = "images/downPlayer.png";
			}else{
				nave.src = "images/down.png";
			}
			
		}
}

function pintarNave(body){
    agregar = ""
    agregar += "<img id='ship"+body.id+"' style='position:absolute; width:"+body.shipSize+"px; height:"+body.shipSize+"px; top:"+body.y+"px; left:"+body.x+"px'";
		if(body.direction == "U"){
			if(body.id == ship.getID()){
				agregar += " src='images/shipPlayer.png' ></img>";
			}else{
				agregar += " src='images/ship.png' ></img>";
			}
		}else if(body.direction == "R"){
			if(body.id == ship.getID()){
				agregar += " src='images/rightPlayer.png' ></img>";
			}else{
				agregar += " src='images/right.png' ></img>";
			}			
		}else if(body.direction == "L"){
			if(body.id == ship.getID()){
				agregar += " src='images/leftPlayer.png' ></img>";
			}else{
				agregar += " src='images/left.png' ></img>";
			}				
		}else{
			if(body.id == ship.getID()){
				agregar += " src='images/downPlayer.png' ></img>";
			}else{
				agregar += " src='images/down.png' ></img>";
			}
			
		}
    document.getElementById("all").innerHTML += agregar;
}


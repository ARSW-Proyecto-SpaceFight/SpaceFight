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
        await getAllMeteorites();

        await getAllBases();

        await getAllFlags();

        await getAllLifeOrbs();

        drawStrings();


}

/*
*Accion cuando se presiona las teclas
*/
window.onkeydown = function(e) {
	var key = e.keyCode ? e.keyCode : e.which;
	if(key===32){
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


async function pintarmeteorites(body){
    agregar = ""
    var i;
    for(i = 0; i< body.length; i++ ){
             agregar += "<img id='meteorite"+body[i].idItem+"' style='position:absolute; width:"+body[i].meteoriteSize+"px; height:"+body[i].meteoriteSize+"px; top:"+body[i].ypos+"px; left:"+body[i].xpos+"px'";
             agregar += " src='images/meteorite.png' ></img>";
        }
     document.getElementById("all").innerHTML += agregar;

}

async function pintarorbesdevida(body){
     agregar = ""
     var i;
     for(i = 0; i< body.length; i++ ){
         agregar += "<img id='lifeorb"+body[i].idItem+"' style='position:absolute; width:"+body[i].lifeOrbSize+"px; height:"+body[i].lifeOrbSize+"px; top:"+body[i].ypos+"px; left:"+body[i].xpos+"px'";
         agregar += " src='images/lifeorb.png' ></img>";
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

function  eliminarBala(body) {
	eliminarElemento("bala"+body.id);
}

function eliminarOrbe(body){
    eliminarElemento("lifeorb"+body.idItem);

}

function danarNave(body){
    document.getElementById("vida"+body.username).innerText = body.health;
}



function eliminarElemento(elementId) {    
    var element = document.getElementById(elementId);
    element.parentNode.removeChild(element);
}

function pintarBala(body){    
    if(document.getElementById("bala"+body.id) == null){
        agregar = ""
        agregar += "<img id='bala"+body.id+"' style='position:absolute; width: 20px; height:20px; top:"+body.Ypos+"px; left:"+body.Xpos+"px' src='images/shot.png'></img>";
        console.log(agregar)
        document.getElementById("all").innerHTML += agregar;
    }else{
        moverBala(body)
    }

}

function moverBala(body){
    bala = document.getElementById("bala"+body.id);
    bala.style.top = body.Ypos + "px"
    bala.style.left = body.Xpos + "px"
}

function drawFlag(body){
    if(document.getElementById("flag"+body.id) == null){
        agregar = ""
        agregar += "<img id='flag"+body.id+"' style='position:absolute; width: "+body.size+"px; height:"+body.size+"px; top:"+body.Ypos+"px; left:"+body.Xpos+"px' src='images/flag"+body.id+".png'></img>";
        console.log(agregar)
        document.getElementById("all").innerHTML += agregar;
    }else{
        moveFlag(body)
    }

}

function drawBase(body){
    if(document.getElementById("base"+body.id) == null){
        agregar = ""
        agregar += "<img id='base"+body.id+"' style='position:absolute; width: "+body.size+"px; height:"+body.size+"px; top:"+body.Ypos+"px; left:"+body.Xpos+"px' src='images/base"+body.id+".png'></img>";
        console.log(agregar)
        document.getElementById("all").innerHTML += agregar;
    }else{
        moveFlag(body)
    }

}

function moveFlag(body){
    flag = document.getElementById("flag"+body.id);
    flag.style.top = body.Ypos + "px"
    flag.style.left = body.Xpos + "px"
}

async function drawFlags(body){
    agregar = ""
    var i;
    for(i = 0; i< body.length; i++ ){
    	//console.log("number:"+body[i].id+"size:"+body[i].size);
        agregar += "<img id='flag"+body[i].id+"' style='position:absolute; width:"+body[i].size+"px; height:"+body[i].size+"px; top:"+body[i].Ypos+"px; left:"+body[i].Xpos+"px' src='images/flag"+body[i].id+".png'></img>";

    }
    document.getElementById("all").innerHTML += agregar;

}

async function drawBases(body){
    agregar = ""
    var i;
    for(i = 0; i< body.length; i++ ){
        //console.log("number:"+body[i].id+"size:"+body[i].size);
        agregar += "<img id='base"+body[i].id+"' style='position:absolute; width:80px; height:80px; top:"+body[i].ypos+"px; left:"+body[i].xpos+"px' src='images/base"+body[i].id+".png'></img>";

    }
    document.getElementById("all").innerHTML += agregar;

}

async function gameOver(){


}

function drawStrings(){
        agregar = "";
        agregar += "<label id = 'team1' style='position:absolute; top:0px; left:0px'>Score Team 1</label>";
        agregar += "<label id = 'scoreteam1' style='position:absolute; top:10px; left:0px'>0</label>";
        agregar += "<label id = 'team2' style='position:absolute; top:0px; left:850px'>Score Team 2</label>";
        agregar += "<label id = 'scoreteam2' style='position:absolute; top:20px; left:850px'>0</label>";
        document.getElementById("all").innerHTML += agregar;
     }

function drawScore(body){
      document.getElementById("scoreteam1").innerText = body.scoreTeam1;
      document.getElementById("scoreteam2").innerText = body.scoreTeam2;
}
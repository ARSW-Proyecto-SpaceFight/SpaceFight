//var host = "http://localhost:8080/spacefight/"
var host = "https://spacefightarsw.herokuapp.com/spacefight/"

/*
*Le dice al servidor que se movio la nave
*/
async function move(key){ 
	await Promise.resolve(axios.put(host+"1?id="+ship.getID()+"&key="+key)
	.then(async function(response){
		var i = 0;
		}));
}

/*
*Crea una nueva nave
*/
async function newShip(){
	var id = Math.floor((Math.random() * 100) + 1);
	ship.setID(id);
	await Promise.resolve(axios.put(host+"1/players",ship.getJSON())
	.then(async function(response){
		console.log(id)
	}));
}

/*
*Le pregunta al servidor el estado actual del juego
*/
async function getAllShips(room){
	await Promise.resolve(axios.get(host+"1/players")
	.then(async function(response){
		ship.setAllShips(response.data);
	}));
}

/*
*Crea una nueva sala
*/
async function createNewRoom(room){
	await Promise.resolve(axios.post(host+"?room="+room));
}

/*
*Mira si existe los cuartos
*/
async function existsRooms(){
	var exists = false;
	await Promise.resolve(axios.get(host)
	.then(async function(response){
		if(response.data>0){
			exists = true;
		}
	}));
	return exists;
}
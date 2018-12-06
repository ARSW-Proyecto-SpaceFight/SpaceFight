//var host = "http://localhost:8080/spacefight/"
var host = "https://spacefightarsw.herokuapp.com/spacefight/"

var stompClient = null;


/*
*Le dice al servidor que se movio la nave
*/
async function move(key){ 
        stompClient.send("/app/move."+ship.getRoom(), {}, JSON.stringify({'username': ship.getUsername(), 'key' : key, 'team': ship.getTeam()}));
	//await Promise.resolve(axios.put(host+"1?id="+ship.getID()+"&key="+key)
	//.then(async function(response){
		//var i = 0;
		//}));
}
async function shoot(){
    await Promise.resolve(axios.put(host+ship.getRoom()+"/shoot?username="+ship.getUsername()))

}

/*
*Crea una nueva nave
*/
async function newShip(){
	//var id = Math.floor((Math.random() * 100) + 1);
	//ship.setID(id);
	//await Promise.resolve(axios.put(host+"1/players",ship.getJSON()));
        console.log(ship.getJSON())
        stompClient.send("/app/new."+ship.getRoom(), {}, JSON.stringify(ship.getJSON()));
}

/*
*Le pregunta al servidor el estado actual del juego
*/
async function getAllShips(room){
	await Promise.resolve(axios.get(host+ship.getRoom()+"/players?player="+ship.getUsername())
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

async function connectAndSubscribe() {
        var socket = new SockJS('/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        await stompClient.connect({}, async function (frame) {
        //setConnected(true);
        console.log('Connected: ' + frame);
        
            await stompClient.subscribe('/topic/move.'+ship.getRoom(), function (message) {
                //console.log(message.body)
                moverNave(JSON.parse(message.body));
                //showGreeting(JSON.parse(greeting.body).content);
            });
            await stompClient.subscribe('/topic/new.'+ship.getRoom(), function (message){
                pintarNave(JSON.parse(message.body));
            });
            await stompClient.subscribe('/topic/delete.'+ship.getRoom(), function (message){
                eliminarNave(message.body);
            });
            await stompClient.subscribe('/topic/conectado.'+ship.getRoom(), function (message){
                if(message.body==ship.getUsername()) stompClient.send("/app/conectado."+ship.getRoom(), {priority: 9}, ship.getUsername());
            });
            await stompClient.subscribe('/topic/shoot.'+ship.getRoom(), function (message){
                pintarBala(JSON.parse(message.body));
            });
            await stompClient.subscribe('/topic/deleteshoot.'+ship.getRoom(), function (message){
                eliminarBala(JSON.parse(message.body));
            });
            await stompClient.subscribe('/topic/damage.'+ship.getRoom(), function (message){
                danarNave(JSON.parse(message.body))
            });
        await pintar();
        await newShip();         	               
        });
        
    }

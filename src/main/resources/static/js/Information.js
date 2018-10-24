var info = {
    "informacion_general":[{
              "version":1.0,
              "controles":"Para que el personaje se mueva se utilizan las teclas de movimiento establecidas (arriba,abajo,derecha e izquierda) que se encuentran en la parte posterior derecha del teclado, las teclas para disparos y demas funciones se estaran mostrando proximamente",
              "personajes_elementos":"Cada jugador controla una nave, cuando el jugador ingrese a la partida se podra identificar visualmente la nave correspondiente, los elementos adicionales se estaran mostrando proximamente. ",
              "historia":"En la dimension X-266 los recursos de la tierra X-266 se estan acabando,la unica alternativa es viajar a la dimension X-1(la nuestra) para invadirla y adquirir los recursos necesarios para la supervivencia ,pero como el honor es importante nuestra tierra es retada a un duelo espacial, donde el equipo que capture la bandera del otro ganara el derecho de sobrevivir",
              "informacion_adicional":[{"Participantes":"David Rodriguez,Martin Cantor,OscarPinto,Alejandra Gomez",
                                        "GitHub":"https://github.com/ARSW-Proyecto-SpaceFight/SpaceFight"
                                      }]
              }]
  
}

getInformation = function(){
    document.getElementById("controles").innerHTML = info.informacion_general[0].controles;
    document.getElementById("elementos").innerHTML = info.informacion_general[0].personajes_elementos;
}

getGameInfo = function(){
    document.getElementById("historia").innerHTML = info.informacion_general[0].historia;
    document.getElementById("adicional").innerHTML = info.informacion_general[0].informacion_adicional[0].Participantes;
}





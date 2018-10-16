var ship = (function(){
	var id;
	var x = 0;
	var y = 0;
	var health;
	var imageElement;
	return{
		setX : function(xPos){
			x = xPos;
			imageElement.style.left=xPos.toString() + "px";
		},
		setY : function(yPos){
			y = yPos;
			imageElement.style.top=yPos.toString() + "px";
		},
		moveOnX : function(units){
			x += units;
			imageElement.style.left=x.toString() + "px";
		},
		moveOnY : function(units){			
			y += units;				
			imageElement.style.top=y.toString() + "px";
		},
		setHealth : function(newHealth){
			health = newHealth;
		},
		setImageElement : function(imageE){
			imageElement = imageE;
		}
	}
})();

function start(){	
	var img = document.getElementById("ship");
	ship.setImageElement(img);
	//img.style.top = "300px";
	//img.style.left = "300px";
}

window.onkeydown = function(e) {
	var key = e.keyCode ? e.keyCode : e.which;
	move(key);   
}


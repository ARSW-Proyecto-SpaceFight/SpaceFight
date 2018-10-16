function move(key){
	if (key == 38) {
       ship.moveOnY(-10);
   }else if (key == 40) {       
	   ship.moveOnY(10);
   }else if(key == 39){
	   ship.moveOnX(10);
   }else if(key == 37){
	   ship.moveOnX(-10);
   }
}
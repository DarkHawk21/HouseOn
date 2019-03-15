var five = require("johnny-five"),board, led;


board = new five.Board();
var RGBLed;
var RGBcolor = 'ffffff';
var bañoLed;
var rec1Led;
var rec2Led;

module.exports = (io) => {
    board.on("ready", function() {
        RGBLed = new five.Led.RGB({
            pins: {
              red: 11,
              green: 9,
              blue: 10
            },
            isAnode: true
        });
        bañoLed = new five.Led(6)
        rec1Led = new five.Led(5);
        rec2Led = new five.Led(3); 
        
        console.log("board ready");
        io.on('connection', function (socket){ //Se ejecuta con un socket.connect();
            console.log("User Connected :)");
            socket.emit('onConnection', {isRGBOn: RGBLed.isOn, color: RGBcolor, 
                isBañoOn: bañoLed.isOn,
                isRec1On: rec1Led.isOn,
                isRec2On: rec2Led.isOn
            });
            
            socket.on('onToggleRGB', function(data){
                RGBLed.toggle();
                io.emit('toggledRGB', {isRGBOn: RGBLed.isOn, color: RGBcolor});
            });
            
            socket.on('onColorChanged', function(data){
                RGBLed.color(data);
                RGBcolor = data;
                io.emit('newColor', {isRGBOn: RGBLed.isOn, color: RGBcolor});
            });

            socket.on('onIntensityChanged', function(data){
                RGBLed.intensity(data);
                io.emit('newColor', {isRGBOn: RGBLed.isOn, color: RGBcolor});

            });

            socket.on('onBrightnessChanged', function(data){
                switch(data['hab']){
                    case 1: 
                        bañoLed.brightness(data['brightness']);
                       
                        io.emit('toggledBaño', {isBañoOn: bañoLed.isOn});
                

                    break;
                    case 2:
                        rec1Led.brightness(data['brightness']);
                        
                            io.emit('toggledRecamara1', {isRecamaraOn: rec1Led.isOn});
                        

                        break;
                    case 3:
                        rec2Led.brightness(data['brightness']);
                        
                            io.emit('toggledRecamara2', {isRecamaraOn: rec2Led.isOn});
                        

                        break;
                    default:
                    break;
                }
            });

            socket.on('getBrightness', function(data){
                switch(data){
                    case 1: 
                        socket.emit("getBrightnessResponse", {brightness: bañoLed.value});
                    break;
                    case 2:
                    socket.emit("getBrightnessResponse", {brightness: rec1Led.value});
                        break;
                    case 3:
                    socket.emit("getBrightnessResponse", {brightness: rec2Led.value});
                        break;
                    default:
                    break;
                }
            });

            socket.on('onToggleBaño', function(data){
                bañoLed.toggle();
                io.emit('toggledBaño', {isBañoOn: bañoLed.isOn});
            });

            socket.on('onToggleRecamara1', function(data){
                rec1Led.toggle();
                io.emit('toggledRecamara1', {isRecamaraOn: rec1Led.isOn});
            });
            
            socket.on('onToggleRecamara2', function(data){
                rec2Led.toggle();
                io.emit('toggledRecamara2', {isRecamaraOn: rec2Led.isOn});
            });

            socket.on('onToggleVentilacion', function(data){
                //Aqui va lo del ventilador xd
            });

            socket.on('disconnect', function(data){
                console.log('User disconnected :( ');
            });
        });

    });

    board.on("exit", function(){
        RGBLed.off();
    });
}

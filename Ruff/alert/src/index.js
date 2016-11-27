'use strict';

$.ready(function (error) {
    if (error) {
        console.log(error);
        return;
    }
    console.log('Welcome!!!');
        setInterval(
            function() {
            $('#luminature').getTemperature(function (error, temperature) {
                if(error) {
                    console.error(error);
                    return;
                }
                console.log('Temperature: ', temperature);
            });
            }, 1000)

        setInterval(
            function() {
            $('#luminature').getRelativeHumidity(function (error, humidity) {
                if(error) {
                    console.error(error);
                    return;
                }
                console.log('Humidity: ', humidity);
            });
            }, 1000)

        setInterval(
            function() {
            $('#light').getIlluminance(function (error, illuminance) {
                if (error) {
                    console.error(error);
                    return;
                }
                if ( illuminance < 20) {
                    $('#RGB').setRGB(255, 0, 255);
                    beep();
                }

                else {
                    $('#RGB').setRGB(0, 0, 0);
                    stopBeep();
                }
                console.log('Illuminance: ', illuminance);
            });
            }, 1000)

$('#button').on('push', function() {
    beep();
    console.log('Button pushed.');
});

$('#button').on('release', function(){
    stopBeep();
    console.log('Button released.');
})
});

function beep() {
    $('#buzzer').turnOn(function (error) {
        if (error) {
            console.error(error);
            return;
        }});
    console.log('Beeping.');
}

function stopBeep() {
    $('#buzzer').turnOff(function (error) {
        if (error) {
            console.error(error);
            return;
        }});
    console.log('Stop beeping.');
}

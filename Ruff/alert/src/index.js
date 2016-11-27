'use strict';

$.ready(function (error) {
    if (error) {
        console.log(error);
        return;
    }
    console.log('Welcome!');
        setInterval(
            function() {
            $('luminature').getTemperature(function (error, temperature) {
                if(error) {
                    console.error(error);
                    return;
                }
                currentTemp = temperature;
                console.log('Temperature: ', temperature);
            });
            }, 1000);

        setInterval(
            function() {
            $('luminature').getRelavitiveHumidity(function (error, humidity) {
                if(error) {
                    console.error(error);
                    return;
                }
                console.log('Humidity: ', humidity);
            });
            }, 1000);

        setInterval(
            function() {
            $('light').getIlluminance(function (error, illuminance) {
                if (error) {
                    console.error(error);
                    return;
                }
                if ( illuminance < 50)
                    $('RGB'.setRGB(80, 0, 0));
                console.log('Illuminance: ', illuminance);
            });
            }, 1000);

$('button').on('push', function() {
    beep();
    console.log('Button pushed.');
});

$('button').on('release', function(){
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

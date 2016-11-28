Protege Nos - Comprehensive Life Improvement for the Blinds
===================

Protege Nos is a comprehensive life improvement system using Android, Arduino and Ruff Internet of Things kit as hardware base and Microsoft Cognitive Service as the significant software API to gather environment data and provide audio feedback to the blind.


Hardwares Specifications
------------
Protege Nos concept uses the following hardware:

- DFRobot Arduino UNO, a microcontroller board based on the ATmega328 with both digital I/O pins and analog I/O pins. Product link: <http://www.arduino.org/products/boards/arduino-uno>
- Ruff Internet of Things kit, a JavaScript-based IoT kit which is very user-friendly to newbies. Ruff is a JavaScript runtime specialized in IoT development. Ruff makes embedded coding easy and more efficient by providing HAL, asynchronous I/O, and event-driven programming. Product link: <http://www.ruff.io>

External APIs
-------------

This project uses external APIs for demonstration purposes. The list of APIs used is shown below:

- Arduino SDK: <http://www.arduino.org/downloads>
- Microsoft Cognitive Service - Image Recognition API: <https://www.azure.cn/cognitive-services>
- OpenWeatherMap API: <http://openweathermap.com/api>
- Ruff Development SDK: <https://ruff.io/api/adc.html>


API Keys
-------------

To use this demo, please signup or login to <https://www.azure.cn/cognitive-services> and <http://openweathermap.com/api> create an application and obtain an API AppID Key. In the Android Application, locate to src/string.xml and replace
```
subscription_key = 'Enter your Microsoft Cognitive Service API key here...'
weather_dummy_api = 'Enter your OpenWeatherMap API key here...'
```

to your API key obtained from Microsoft and OpenWeatherMap.

Misc.
-------------------

This demo iseveloped by Shaolin Zhang, Yiluo Li, Yuan Li and Yong Mao as a project for PentaHackathon 2016 hosted by Microsoft China and its affiliated Student Clubs on November 26-27, 2016.

**This Project is the final 10 project out of 40 total competing teams.**

License
-------------------

This project is licensed under MIT License.

Copyright (c) 2016 Shaolin Zhang, Yiluo Li, Yuan Li and Yong Mao.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

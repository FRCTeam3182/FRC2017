/*
 Fade

 This example shows how to fade an LED on pin 9
 using the analogWrite() function.

 The analogWrite() function uses PWM, so if
 you want to change the pin you're using, be
 sure to use another PWM capable pin. On most
 Arduino, the PWM pins are identified with 
 a "~" sign, like ~3, ~5, ~6, ~9, ~10 and ~11.

 This example code is in the public domain.
 */


int pinRed = 9;
int pinGreen = 10;
int pinBlue = 11;
// the PWM pin the LED is attached to

int brightness = 254;    // how bright the LED is
int fadeAmount = 1;    // how many points to fade the LED by

// the setup routine runs once when you press reset:
void setup() {
  // declare pin 9 to be an output:
  pinMode(pinRed, OUTPUT);
  pinMode(pinGreen, OUTPUT);
  pinMode(pinBlue, OUTPUT);
}

void climb(){
  //lights displayed when climbing(yellow-green?)
  analogWrite(pinRed, brightness);
  analogWrite(pinGreen, 255);
  brightness = brightness - fadeAmount;
  if (brightness <= 0 || brightness >= 255) {
    fadeAmount = -fadeAmount ;
  }
}

void stationary(){
  //lights displayed when stationary(red-purple?)
  analogWrite(pinRed, 255);
  analogWrite(pinBlue, brightness);
  brightness = brightness - fadeAmount;
  if (brightness == 0 || brightness == 255) {
    fadeAmount = -fadeAmount ;
  }
}

void collect(){
  //lights displayed when collecting(orange?)
  analogWrite(pinRed, 255);
  analogWrite(pinGreen, 127);
}

void arm(){
  //lights displayed when using arm(pink?)
  analogWrite(pinRed, 255);
  analogWrite(pinBlue, 100);
}
void gearCollected() {
  // lights displayed when a gear is collected
  analogWrite(pinRed, 255);
  
}
// the loop routine runs over and over again forever:
void loop() {
 
  stationary();
    
  
  
  // set the brightness of pin 9:
  //analogWrite(led, brightness);

  // change the brightness for next time through the loop:
  //brightness = brightness + fadeAmount;

  // reverse the direction of the fading at the ends of the fade:
  //if (brightness == 0 || brightness == 255) {
   // fadeAmount = -fadeAmount ;
  //}
  // wait for 200 milliseconds to see the dimming effect
  delay(5);
}

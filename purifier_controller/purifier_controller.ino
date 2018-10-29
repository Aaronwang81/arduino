#define controlPin 3
#define out1 7
#define out2 8

const byte CommandLength = 4;

const byte Command_Action = 0x10;
const byte Command_GetInfo = 0x20;
const byte Command_UpdateFireware = 0x30;
const byte Command_Reset = 0x40;
const byte Command_Report = 0x50;
const byte Command_Purifier = 0x60;

const byte Command_GetType = 0x01;

const byte Type_Purifier = 0x02;

const byte Purifier_TurnOn = 'O';
const byte Purifier_TurnOff = 'F';
const byte Purifier_SpeedUp = 'U';
const byte Purifier_SpeedDown = 'D';
const byte Purifer_SetSpeed = 'S';

int serialSize = 0;
byte CB0 = 0;
byte CB1 = 0;
byte CB2 = 0;
byte CB3 = 0;

//L298N 模块不适合用PWM控制电机转速。改用mos管模块尝试
//mos管也不能解决，需要加一个（组）滤波电容。

void setPwmFrequency(int pin, int divisor) {
  byte mode;
  if (pin == 5 || pin == 6 || pin == 9 || pin == 10) {
    switch (divisor) {
      case 1: mode = 0x01; break;
      case 8: mode = 0x02; break;
      case 64: mode = 0x03; break;
      case 256: mode = 0x04; break;
      case 1024: mode = 0x05; break;
      default: return;
    }
    if (pin == 5 || pin == 6) {
      TCCR0B = TCCR0B & 0b11111000 | mode;
    } else {
      TCCR1B = TCCR1B & 0b11111000 | mode;
    }
  } else if (pin == 3 || pin == 11) {
    switch (divisor) {
      case 1: mode = 0x01; break;
      case 8: mode = 0x02; break;
      case 32: mode = 0x03; break;
      case 64: mode = 0x04; break;
      case 128: mode = 0x05; break;
      case 256: mode = 0x06; break;
      case 1024: mode = 0x07; break;
      default: return;
    }
    TCCR2B = TCCR2B & 0b11111000 | mode;
  }
}

void processCommand() {
  if (Command_Purifier != CB0) {
    switch (CB1) {
      case Purifier_TurnOn:
        digitalWrite(controlPin, HIGH);
        break;
      case Purifier_TurnOff:
        digitalWrite(controlPin, LOW);
        break;
      case Purifier_SpeedUp:
        break;
      case Purifier_SpeedDown:
        break;
      case Purifer_SetSpeed:
        break;
    }
  }

  if(Command_GetInfo != CB0){
    switch(CB1){
      case Command_GetType:
      break;
    }
  }


}

void setup() {
  // put your setup code here, to run once:

  Serial.begin (19200);
  pinMode(controlPin, OUTPUT);
  //pinMode(out1, OUTPUT);
  //pinMode(out2, OUTPUT);
  //setPwmFrequency(controlPin, 8);
}

void loop() {
  // put your main code here, to run repeatedly:

  serialSize = Serial.available();
  if (serialSize > 0) {
    CB0 = Serial.read();
    CB1 = Serial.read();
    CB2 = Serial.read();
    CB3 = Serial.read();
    processCommand();
  } else {
    //没命令时延时0.5秒
    delay(500);
  }

}

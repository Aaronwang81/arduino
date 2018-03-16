#define controlPin 6
#define out1 7
#define out2 8

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

void setup() {
  // put your setup code here, to run once:

  Serial.begin (9600);
  pinMode(controlPin, OUTPUT);
  //pinMode(out1, OUTPUT);
  //pinMode(out2, OUTPUT);
  //setPwmFrequency(controlPin, 8);
}

void loop() {
  // put your main code here, to run repeatedly:

  //digitalWrite(out1, HIGH);
  //digitalWrite(out2, LOW);
  analogWrite(controlPin, 255);

  //  for(int rate = 1; rate <= 10; ++rate)
  //  {
  //    analogWrite(controlPin, rate * 25.5);
  //  //Serial.write("192");
  //  delay((10 - rate - 1) * 1000);
  //  }

  //digitalWrite(out1, LOW);
  //digitalWrite(out2, LOW);

}

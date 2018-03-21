//LingShun Lab

int left1 = 4; // 定义uno的pin 5 向 left1 输出
int left2 = 5; // 定义uno的pin 6 向 left2 输出
int right1 = 6; // 定义uno的pin 9 向 right1 输出
int right2 = 7; // 定义uno的pin 10 向 right2 输出

const byte CommandLength = 1;

const byte Command_Action = 0x10;
const byte Command_GetInfo = 0x20;
const byte Command_UpdateFireware = 0x30;
const byte Command_Reset = 0x40;

const byte Action_Forward = 'W';
const byte Action_Left = 'A';
const byte Action_Right = 'D';
const byte Action_Back = 'S';

int serialSize = 0;
byte Command = -1;
bool isSynergy = false;

void forward()
{
  //forward 向前转
  digitalWrite(left1, HIGH); //给高电平
  digitalWrite(left2, LOW); //给低电平
  
  digitalWrite(right1, LOW); //给高电平
  digitalWrite(right2, HIGH); //给低电平
}


void standby()
{
  digitalWrite(left1, LOW);
  digitalWrite(left2, LOW);
  digitalWrite(right1, LOW);
  digitalWrite(right2, LOW);
}

void back()
{
  //back 向后转
  digitalWrite(left1, LOW);
  digitalWrite(left2, HIGH);
  //右电机要反向
  digitalWrite(right1, HIGH);
  digitalWrite(right2, LOW);
}

void left(bool synergy)
{
  //右电机进
  digitalWrite(right1, LOW); //给高电平
  digitalWrite(right2, HIGH); //给低电平

  //协同转弯时，左轮同时后退
  if (synergy)
  {
    digitalWrite(left1, LOW);
    digitalWrite(left2, HIGH);
  }
  else
  {
    digitalWrite(left1, LOW);
    digitalWrite(left2, LOW);
  }
}

void right(bool synergy)
{
  digitalWrite(left1, HIGH); //给高电平
  digitalWrite(left2, LOW); //给低电平

  //协同转弯时，右轮同时后退
  if (synergy)
  {
    digitalWrite(right1, HIGH);
    digitalWrite(right2, LOW);
  }
  else
  {
    digitalWrite(right1, LOW);
    digitalWrite(right2, LOW);
  }
}

void processCommand()
{
  //Serial.print("Start processCommand, Command is: 0x");
  Serial.print("0x");
  Serial.print(Command, HEX);
  switch(Command){
    case Action_Forward:
    processForward();
    break;
    case Action_Left:
    processLeft();
    break;
    case Action_Right:
    processRight();
    break;
    case Action_Back:
    porcessBack();
    break;
  }
}

void processForward()
{
  forward();
  delay(1000);
  standby();
}

void porcessBack()
{
  back();
  delay(1000);
  standby();
}

void processLeft()
{
  left(true);
  delay(500);
  standby();
}

void processRight()
{
  right(true);
  delay(500);
  standby();
}

void setup() {
  Serial.begin (19200);
  //初始化各IO,模式为OUTPUT 输出模式
  pinMode(left1, OUTPUT);
  pinMode(left2, OUTPUT);
  pinMode(right1, OUTPUT);
  pinMode(right2, OUTPUT);

}

void loop() {

  serialSize = Serial.available();
  if(serialSize > 0){
    Command = Serial.read();
    processCommand();
    //forward();
    delay(500);
  }else{
    //没命令时延时1秒
    //back();
    delay(500);
  }

}

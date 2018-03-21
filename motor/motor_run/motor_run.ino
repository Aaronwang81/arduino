

const int g_left1 = 4; // 定义uno的pin 5 向 g_left1 输出
const int g_left2 = 5; // 定义uno的pin 6 向 g_left2 输出
const int g_right1 = 6; // 定义uno的pin 9 向 g_right1 输出
const int g_right2 = 7; // 定义uno的pin 10 向 g_right2 输出

const int g_trigPin = 8;//雷达触发pin
const int g_echoPin = 9;//雷达回显pin


const byte CommandLength = 4;

const byte Command_Action = 0x10;
const byte Command_GetInfo = 0x20;
const byte Command_UpdateFireware = 0x30;
const byte Command_Reset = 0x40;

const byte Action_Forward = 'W';
const byte Action_Left = 'A';
const byte Action_Right = 'D';
const byte Action_Back = 'S';

int serialSize = 0;
byte CB0 = 0;
byte CB1 = 0;
byte CB2 = 0;
byte CB3 = 0;
bool isSynergy = false;

void forward()
{
  //forward 向前转
  digitalWrite(g_left1, HIGH); //给高电平
  digitalWrite(g_left2, LOW); //给低电平
  
  digitalWrite(g_right1, LOW); //给高电平
  digitalWrite(g_right2, HIGH); //给低电平
}


void standby()
{
  digitalWrite(g_left1, LOW);
  digitalWrite(g_left2, LOW);
  digitalWrite(g_right1, LOW);
  digitalWrite(g_right2, LOW);
}

void back()
{
  //back 向后转
  digitalWrite(g_left1, LOW);
  digitalWrite(g_left2, HIGH);
  //右电机要反向
  digitalWrite(g_right1, HIGH);
  digitalWrite(g_right2, LOW);
}

void left(bool synergy)
{
  //右电机进
  digitalWrite(g_right1, LOW); //给高电平
  digitalWrite(g_right2, HIGH); //给低电平

  //协同转弯时，左轮同时后退
  if (synergy)
  {
    digitalWrite(g_left1, LOW);
    digitalWrite(g_left2, HIGH);
  }
  else
  {
    digitalWrite(g_left1, LOW);
    digitalWrite(g_left2, LOW);
  }
}

void right(bool synergy)
{
  digitalWrite(g_left1, HIGH); //给高电平
  digitalWrite(g_left2, LOW); //给低电平

  //协同转弯时，右轮同时后退
  if (synergy)
  {
    digitalWrite(g_right1, HIGH);
    digitalWrite(g_right2, LOW);
  }
  else
  {
    digitalWrite(g_right1, LOW);
    digitalWrite(g_right2, LOW);
  }
}

void processCommand()
{
  //Serial.print("Start processCommand, Command is: 0x");
  if(Command_Action != CB0)
  {
    Serial.print("Not support.");
  }
  switch(CB1){
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
  Serial.println("processForward");
  while( getDistance( g_trigPin, g_echoPin ) > 20 ){
    forward();
    delay(CB2 * 100);
  }
  standby();
}

void porcessBack()
{
  Serial.println("processBack");
  back();
  delay(CB2 * 100);
  standby();
}

void processLeft()
{
  Serial.println("processLeft");
  left(1 == CB3 ? true : false);
  delay(CB2);
  standby();
}

void processRight()
{
  Serial.println("processRight");
  right(1 == CB3 ? true : false);
  delay(CB2);
  standby();
}

float getDistance(int trigPin, int echoPin)
{
  //给Trig发送一个低高低的短时间脉冲,触发测距  
  digitalWrite(trigPin, LOW); //给Trig发送一个低电平  
  delayMicroseconds(2);    //等待 2微妙  
  digitalWrite(trigPin,HIGH); //给Trig发送一个高电平  
  delayMicroseconds(10);    //等待 10微妙  
  digitalWrite(trigPin, LOW); //给Trig发送一个低电平  
    
  float temp = float(pulseIn(echoPin, HIGH)); //存储回波等待时间,  
  //pulseIn函数会等待引脚变为HIGH,开始计算时间,再等待变为LOW并停止计时  
  //返回脉冲的长度  
    
  //声速是:340m/1s 换算成 34000cm / 1000000μs => 34 / 1000  
  //因为发送到接收,实际是相同距离走了2回,所以要除以2  
  //距离(厘米)  =  (回波时间 * (34 / 1000)) / 2  
  //简化后的计算公式为 (回波时间 * 17)/ 1000  
    
  float distance = (temp * 17 )/1000; //把回波时间换算成cm  
  
  Serial.print("Echo =");  
  Serial.print(temp);//串口输出等待时间的原始数据  
  Serial.print(" | | Distance = ");  
  Serial.print(distance);//串口输出距离换算成cm的结果  
  Serial.println(" cm"); 
  return distance;
}

void setup() {
  Serial.begin (19200);
  //初始化各IO,模式为OUTPUT 输出模式
  pinMode(g_left1, OUTPUT);
  pinMode(g_left2, OUTPUT);
  pinMode(g_right1, OUTPUT);
  pinMode(g_right2, OUTPUT);

  pinMode(g_trigPin, OUTPUT);
  pinMode(g_echoPin, INPUT);

}

void loop() {

  serialSize = Serial.available();
  if(serialSize > 0){
    CB0 = Serial.read();
    CB1 = Serial.read();
    CB2 = Serial.read();
    CB3 = Serial.read();
    processCommand();
    //forward();
  }else{
    //没命令时延时1秒
    //back();
    delay(500);
  }

}

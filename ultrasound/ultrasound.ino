#define Trig 4
#define Echo 5


float distance;
float temp;
float dis1;
float dis2;
float dis3;

float getDistance(int trigPin, int echoPin)
{
  //给Trig发送一个低高低的短时间脉冲,触发测距  
  digitalWrite(trigPin, LOW); //给Trig发送一个低电平  
  delayMicroseconds(2);    //等待 2微妙  
  digitalWrite(trigPin,HIGH); //给Trig发送一个高电平  
  delayMicroseconds(10);    //等待 10微妙  
  digitalWrite(trigPin, LOW); //给Trig发送一个低电平  
    
  temp = float(pulseIn(echoPin, HIGH)); //存储回波等待时间,  
  //pulseIn函数会等待引脚变为HIGH,开始计算时间,再等待变为LOW并停止计时  
  //返回脉冲的长度  
    
  //声速是:340m/1s 换算成 34000cm / 1000000μs => 34 / 1000  
  //因为发送到接收,实际是相同距离走了2回,所以要除以2  
  //距离(厘米)  =  (回波时间 * (34 / 1000)) / 2  
  //简化后的计算公式为 (回波时间 * 17)/ 1000  
    
  distance = (temp * 17 )/1000; //把回波时间换算成cm  
  
  Serial.print("Echo =");  
  Serial.print(temp);//串口输出等待时间的原始数据  
  Serial.print(" | | Distance = ");  
  Serial.print(distance);//串口输出距离换算成cm的结果  
  Serial.println(" cm"); 
  return distance;
}


void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(Trig, OUTPUT);
  pinMode(Echo, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  dis1 = getDistance(Trig, Echo);
  dis2 = getDistance(Trig, Echo);
  dis3 = getDistance(Trig, Echo);

  distance = (dis1 + dis2 + dis3) / 3;

  Serial.print("Average Distance = ");  
  Serial.print(distance);//串口输出距离换算成cm的结果  
  Serial.println(" cm"); 
 
  delay(100); 
}

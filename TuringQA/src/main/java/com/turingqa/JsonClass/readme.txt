https://www.kancloud.cn/turing/web_api/522992

编码方式
UTF-8（调用图灵API的各个环节的编码方式均为UTF-8）

接口地址
http://openapi.tuling123.com/openapi/api/v2

请求方式
HTTP	POST

请求参数
请求参数格式为 json
请求示例：

{
	"reqType":0,
    "perception": {
        "inputText": {
            "text": "附近的酒店"
        },
        "inputImage": {
            "url": "imageUrl"
        },
        "selfInfo": {
            "location": {
                "city": "北京",
                "province": "北京",
                "street": "信息路"
            }
        }
    },
    "userInfo": {
        "apiKey": "",
        "userId": ""
    }
}

如果是聊天，会返回如下内容
{"emotion":{"robotEmotion":{"a":0,"d":0,"emotionId":0,"p":0},"userEmotion":{"a":0,"d":0,"emotionId":21500,"p":0}},"intent":{"actionName":"","code":10004,"intentName":""},"results":[{"groupType":0,"resultType":"text","values":{"text":"还不错，见到你更好啦。"}}]}
但查询天气时，返回如下内容
{"intent":{"actionName":"","code":10008,"intentName":"","parameters":{"date":"","city":"广州"}},"results":[{"groupType":1,"resultType":"text","values":{"text":"广州:周五 03月16日,多云转阴 无持续风向微风,最低气温18度，最高气温26度"}}]}
成语接龙，返回如下内容
{"intent":{"actionName":"","code":10011,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"进入成语接龙模式：富贵安乐"}}]}
附近酒店，返回
{"intent":{"actionName":"","code":10005,"intentName":"","parameters":{"lon":"","checkout_date":"2018-03-18","star":"0","city":"广州","days":"1","order":"","price_range":"","nearby_place":"酒店","brand":"","checkin_date":"2018-03-17","place":"","lat":"","needgeo":"0"}},"results":[{"groupType":1,"resultType":"url","values":{"url":"http://m.elong.com/hotel/2001/nlist/#indate=2018-03-17&outdate=2018-03-18&keywords="}},{"groupType":1,"resultType":"text","values":{"text":"亲，已帮你找到相关酒店信息"}}]}
鱼香肉丝，返回
{"intent":{"actionName":"","code":10015,"intentName":"","parameters":{"recipe":"鱼香肉丝"}},"results":[{"groupType":1,"resultType":"text","values":{"text":"亲，已帮您找到菜谱信息"}},{"groupType":1,"resultType":"news","values":{"news":[{"name":"鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/f3067a6e886111e6b87c0242ac110003_640w_640h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分8.3 17984人下厨","detailurl":"http://m.xiachufang.com/recipe/100352761/?ref=tuling"},{"name":"史上最详尽经典川菜【鱼香肉丝】","icon":"http://s2.cdn.xiachufang.com/f85bc60287be11e6a9a10242ac110002_1500w_1004h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分8.6 4764人下厨","detailurl":"http://m.xiachufang.com/recipe/1102796/?ref=tuling"},{"name":"鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/a09b8a0a890911e6b87c0242ac110003_650w_650h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分8.2 525人下厨","detailurl":"http://m.xiachufang.com/recipe/100489447/?ref=tuling"},{"name":"鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/1a974eba874911e6a9a10242ac110002_600w_450h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分8.4 13347人下厨","detailurl":"http://m.xiachufang.com/recipe/1010097/?ref=tuling"},{"name":"鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/a79c6cf2fb2311e6bc9d0242ac110002_1280w_1280h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分8.3 148人下厨","detailurl":"http://m.xiachufang.com/recipe/102214342/?ref=tuling"},{"name":"简易版鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/9092ba1a873411e6b87c0242ac110003_426w_640h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分8.2 292人下厨","detailurl":"http://m.xiachufang.com/recipe/263164/?ref=tuling"},{"name":"鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/b426fc9a8b7911e6a9a10242ac110002_1215w_1620h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分8.0 20人下厨","detailurl":"http://m.xiachufang.com/recipe/100536947/?ref=tuling"},{"name":"鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/3b4fd10681f211e6b87c0242ac110003_800w_533h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分7.5 416人下厨","detailurl":"http://m.xiachufang.com/recipe/100218709/?ref=tuling"},{"name":"鱼香肉丝","icon":"http://s1.cdn.xiachufang.com/2738ecbc86f111e6b87c0242ac110003_450w_675h.jpg@2o_50sh_1pr_1l_280w_190h_1c_1e_90q_1wh","info":"评分8.3 333人下厨","detailurl":"http://m.xiachufang.com/recipe/17762/?ref=tuling"},{"name":"鱼香肉丝（家常简易版）","icon":"http://s2.cdn.xiachufang.com/1f26e656ae1411e6bc9d0242ac110002_6016w_4000h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分8.1 52人下厨","detailurl":"http://m.xiachufang.com/recipe/102149863/?ref=tuling"},{"name":"鱼香肉丝Yuxiang Shredded Pork","icon":"http://s2.cdn.xiachufang.com/b1686074890911e6a9a10242ac110002_800w_533h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分8.3 418人下厨","detailurl":"http://m.xiachufang.com/recipe/100489495/?ref=tuling"},{"name":"鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/ccae4340896c11e6b87c0242ac110003_690w_919h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分8.3 40人下厨","detailurl":"http://m.xiachufang.com/recipe/100553600/?ref=tuling"},{"name":"鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/7b74719c88af11e6a9a10242ac110002_650w_650h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分20人下厨","detailurl":"http://m.xiachufang.com/recipe/100410514/?ref=tuling"},{"name":"鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/21fec6428b5911e6b87c0242ac110003_800w_533h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分7.9 27人下厨","detailurl":"http://m.xiachufang.com/recipe/101853005/?ref=tuling"},{"name":"【美善品】鱼香肉丝","icon":"http://s2.cdn.xiachufang.com/0f62116a356811e7bc9d0242ac110002_1536w_1229h.jpg?imageView2/1/w/280/h/190/interlace/1/q/90","info":"评分1人下厨","detailurl":"http://m.xiachufang.com/recipe/102284322/?ref=tuling"}]}}]}
图灵的简介，返回
{"intent":{"actionName":"","code":10013,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"艾伦麦席森图灵（Alan Mathison Turing 1912年6月23日 --- 1954年6月7日），英国数学家、逻辑学家，他被视为“计算机之父”。是计算机逻辑的奠基者，提出了“图灵机”和“图灵测试”等重要概念。人们为纪念其在计算机领域的卓越贡献而设立“图灵奖”。"}}]}
玉米的图片，返回
{"intent":{"actionName":"","code":10014,"intentName":""},"results":[{"groupType":1,"resultType":"url","values":{"url":"http://m.image.so.com/i?q=%E7%8E%89%E7%B1%B3"}},{"groupType":1,"resultType":"text","values":{"text":"亲，已帮你找到图片"}}]}
2的开方，返回
{"intent":{"actionName":"","code":10009,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"2的开方等于1.414"}}]}
翻译我爱你，返回
{"intent":{"actionName":"","code":10020,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"I love you"}}]}
讲个笑话，返回
{"intent":{"actionName":"","code":10006,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"老爸 你好阴险。"}},{"groupType":1,"resultType":"image","values":{"silentState":"c16f2d29-36c9-4ed6-a975-747ddbab2cb4","image":"http://file.tuling123.com/upload/image/xiaohua/20170726/1501079692004.jpg"}}]}
讲个故事，返回
{"intent":{"actionName":"","code":10010,"intentName":"","parameters":{"story_random":"文字故事"}},"results":[{"groupType":1,"resultType":"text","values":{"text":"    一头公牛竭尽全力要挤过一条小路，到牛栏里去。这时，一头小牛犊走了过来，争着要先走，并告诉公牛如何才能通过这条小路。公牛说：“不用劳驾你了，你还没出世前，我就早已知道那办法了。”\n    这是说，年青人千万不要在老人面前逞能。"}}]}
热点新闻，返回
{"intent":{"actionName":"","code":10003,"intentName":"","parameters":{"name_output":"热点新闻","news_code":"news_hot"}},"results":[{"groupType":1,"resultType":"text","values":{"text":"亲，已帮您找到相关新闻"}},{"groupType":1,"resultType":"news","values":{"news":[{"name":"火箭军今起换发新军服 衬衣为国际经典色","info":"新浪新闻","icon":"","detailurl":"http://news.163.com/16/0701/03/BQS0TOKV00014AED.html"},{"name":"身份证现可异地办理 微信发红包需经认证","info":"新浪新闻","icon":"","detailurl":"http://news.163.com/16/0701/00/BQRLGKPI00014JB6.html"},{"name":"传军队师职干部工资将达3万 国防部澄清","info":"新浪新闻","icon":"","detailurl":"http://news.163.com/16/0630/17/BQQT6KID0001124J.html"},{"name":"","info":"新浪新闻","icon":"","detailurl":"http://news.163.com/16/0909/18/C0HTTQAT00015AFV.html#163interesting?xstt"},{"name":"","info":"新浪新闻","icon":"","detailurl":"http://news.163.com/16/0908/10/C0EFFNB300014TUH.html#163interesting?xstt"},{"name":"","info":"新浪新闻","icon":"","detailurl":"http://news.163.com/16/0908/10/C0EEV8DO00014TUH.html#163interesting?xstt"},{"name":"","info":"新浪新闻","icon":"","detailurl":"http://news.163.com/16/0908/10/C0EELUMM000155K8.html#163interesting?xstt"},{"name":"","info":"新浪新闻","icon":"","detailurl":"http://news.163.com/16/0908/10/C0EE5IVE00014TUH.html#163interesting?xstt"},{"name":"","info":"新浪新闻","icon":"","detailurl":"http://news.163.com/16/0908/10/C0EE38HQ000155PV.html#163interesting?xstt"}]}}]}
双鱼座运势，返回
{"intent":{"actionName":"","code":10041,"intentName":"","parameters":{"cache_data":{"responsecode":1,"showtext":"综合运势：今天在各个场合走动是轻松自如，跟人相处如鱼得水，口才了得，待人处事方面做的很到位，总是能够顾及对方的脸面。在社交场合易有好运，吸引到异性仰慕者，单身者可得抓紧机会了。爱情运势：感情生活比较舒心，单身者得朋友帮助会脱单。事业学业：工作上偶尔会流言蜚语，只要少理会就好。财富运势：以前借出去的钱财今天有机会能够收回来。健康运势：早晨比较凉，要注意保暖，别要风度不要温度。","apptype":30000}}},"results":[{"groupType":1,"resultType":"text","values":{"text":"综合运势：今天在各个场合走动是轻松自如，跟人相处如鱼得水，口才了得，待人处事方面做的很到位，总是能够顾及对方的脸面。在社交场合易有好运，吸引到异性仰慕者，单身者可得抓紧机会了。"}}]}
脑筋急转弯，返回
{"intent":{"actionName":"","code":10022,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"问：鸡鹅百米赛跑,鸡比鹅跑得快,为什么却后到终点站?答：鸡跑错了方向"}}]}
歇后语，返回
{"intent":{"actionName":"","code":10030,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"夜壶摆在床底下――见不得人"}}]}
绕口令，返回
{"intent":{"actionName":"","code":10031,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"尖塔尖，尖杆尖，杆尖尖似塔尖尖，塔尖尖似杆尖尖。有人说杆尖比塔尖尖，有人说塔尖比杆尖尖。不知到底是杆尖比塔尖尖，还是塔尖比杆尖尖。"}}]}
顺口溜，返回
{"intent":{"actionName":"","code":10032,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"天生我才必有用，唯独是情弄不懂。请教乌鸭也没用，只好归去睡家中。梦见王帝有提示，明时八点起床后，吃完蛋糕就会懂。"}}]}
藏头诗，返回
{"intent":{"actionName":"","code":10044,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"头头，头头，头大了，不知道怎么说了"}}]}
快递查询，返回
{"intent":{"actionName":"","code":10016,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"您好,您的快递单号是多少？"}}]}
快递查询EMS1036378914627，返回
{"intent":{"actionName":"","code":10016,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"2018-02-27 15:53:00\n广东省邮政速递物流有限公司广州市番禺洛溪营业部已收件（揽投员姓名：何博谦,联系电话:）\n\n2018-02-27 20:42:19\n已离开广东省邮政速递物流有限公司广州市番禺洛溪营业部，发往广州航站\n\n2018-02-27 21:56:00\n到达  广州航站 处理中心\n\n2018-03-02 00:10:00\n离开广州市 发往南京市\n\n2018-03-02 02:59:00\n到达EMS航空集散中心（南京）处理中心（经转）\n\n2018-03-02 09:22:00\n离开南京市 发往武汉市（经转）\n\n2018-03-02 11:15:00\n到达武汉航站处理中心（经转）\n\n2018-03-02 12:13:00\n离开武汉市 发往下一目的地（经转）\n\n2018-03-03 09:27:25\n离开武汉市 发往信阳市（经转）\n\n2018-03-03 12:46:07\n到达  信阳市 处理中心\n\n2018-03-03 16:53:00\n到达  信阳火车转运站 处理中心\n\n2018-03-04 11:04:00\n已离开固始县，发往河南省邮政速递物流有限公司信阳市分公司固始揽投部\n\n2018-03-04 18:10:07\n河南省邮政速递物流有限公司信阳市分公司固始揽投部安排投递，预计23:59:00前投递（投递员姓名：钱爱琼;联系电话：17633760227）\n\n2018-03-04 18:30:35\n投递并签收，签收人：本人收"}}]}
广州到深圳的车票，返回
{"intent":{"actionName":"","code":10018,"intentName":"","parameters":{"end_city":"深圳","start_city":"广州","depart_time":"全天","depart_date":"2018-03-18","train_type":"全部列车"}},"results":[{"groupType":1,"resultType":"url","values":{"url":"http://touch.qunar.com/h5/train/trainList?startStation=%E5%B9%BF%E5%B7%9E&endStation=%E6%B7%B1%E5%9C%B3&searchType=stasta&date=2018-03-18&sort=3&filterTrainType=1&filterTrainType=2&filterTrainType=3&filterTrainType=4&filterTrainType=5&filterTrainType=6&filterTrainType=7&filterDeptTimeRange=1&filterDeptTimeRange=2&filterDeptTimeRange=3&filterDeptTimeRange=4"}},{"groupType":1,"resultType":"text","values":{"text":"亲，已帮你找到列车信息"}}]}
2009年的重阳节是哪天，返回
{"intent":{"actionName":"","code":10019,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"2018年3月18日"}}]}
YY的股价，返回
{"intent":{"actionName":"","code":10026,"intentName":"","parameters":{"name_output":"yy股价","stock_code":"YY","stock_type":"1","stock_name":"yy"}},"results":[{"groupType":1,"resultType":"url","values":{"url":"http://stock1.sina.cn/dpool/stock_new/v2/nasdaq_single.php?code=YY&gsid="}},{"groupType":1,"resultType":"text","values":{"text":"亲，已帮你找到yy股价美股信息"}}]}
家居风水，返回
{"intent":{"actionName":"","code":10013,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"家居风水是风水研究的一部分，它的研究对象是人与环境。家居风水的研究正是使其二者相融、相宜、相合。它可以对家居的外部环境和室内环境进行细致分析、观形察势。以人为核心，结合时间和地运综合全面的评测后得出结果，并根据出现的问题设计出相应的解决方案。家居风水贯穿家居派的选择环境、优化环境、改造环境的过程中，已形成一套完整的体系"}}]}
暂时认为只有聊天时会返回emotion
code与类型对应关系
10003   新闻
10004   聊天
10005   附近酒店
10006   笑话
10008   天气
10009   数学计算
10010   讲故事
10011   成语接龙
10013   百度百科
10014   图片
10015   菜谱
10016   快递查询
10018   列车、票务
10019   日期、节日查询（限当年？？）
10020   中英互译
10022   脑筋急转弯
10026   股票
10030   歇后语
10031   绕口令
10032   顺口溜
10041   星座运势
10044   藏头诗
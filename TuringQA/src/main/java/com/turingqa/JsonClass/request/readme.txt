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
暂时认为只有聊天时会返回emotion
code与类型对应关系
10005   附近酒店
10008   天气
10011   成语接龙
10015   菜谱

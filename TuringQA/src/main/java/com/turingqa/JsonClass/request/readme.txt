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
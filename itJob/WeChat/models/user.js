import { HTTP } from '../utils/http.js'
class User extends HTTP {
  loginByPassword(username,password,type,sCallback) {
    this.request({
      url: '/user/loginByPassword',
      method: 'get',
      data: {
        username: username,
        password: password,
        type: type,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  loginByWechat(type,sCallback){
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          wx.getUserInfo({
            success: function (res_user) {
              wx.request({
                url: 'http://localhost:8085/user/loginByWeChat', //这里是本地请求路径,可以写你自己的本地路径,也可以写线上环境
                data: {
                  code: res.code,//获取openid的话 需要向后台传递code,利用code请求api获取openid
                  name: res_user.userInfo.nickName, //获取昵称
                  sex: res_user.userInfo.gender,//获取性别
                  icon: res_user.userInfo.avatarUrl, //获取头像
                  type: type,
                },
                success: (res) => {
                  sCallback(res)
                }
              })
            }
          })
        }
      }
    })
  }
  register(username, password, type, sCallback){
    this.request({
      url: '/user/register',
      method: 'get',
      data: {
        username: username,
        password: password,
        type: type,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
}
export { User }
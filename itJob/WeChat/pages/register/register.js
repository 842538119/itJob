import { User } from '../../models/user.js'
let user = new User()
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: '',
    password: '',
    type: '',
    items: [
      { name: '求职者', checked: false },
      { name: '企业', checked: false }
    ],
  },
  toLogin:function(){
    wx.redirectTo({
      url: '../login/login'
    })
  },
  bindtap1: function (e) {
    var items = this.data.items;
    for (var i = 0; i < items.length; i++) {
      if (items[i].name == this.data.type) {
        for (var j = 0; j < items.length; j++) {
          if (items[j].checked && j != i) {
            items[j].checked = false;
          }
        }
        items[i].checked = !(items[i].checked);
      }
    }
    this.setData({
      items: items,
    })
  },
  /**
  * 获得单选框的值
  */
  radioChange: function (e) {
    this.data.type = e.detail.value;
    console.log(this.data)
  },
  /**
   * 获得账号
   */
  getUsername: function (e) {
    this.data.username = e.detail.value;
  },
  /**
    * 获得密码
    */
  getPassword: function (e) {
    this.data.password = e.detail.value;
  },
  /**
   * 注册
   */
  register: function () {
    var username = this.data.username;
    var password = this.data.password;
    var type = this.data.type;
    if (username == '' || password == '' || type == '') {
      wx.showToast({
        title: "请输入完整信息!",
        icon: 'none',
        duration: 2000
      })
    }
    else if (username != '' || password != '' || type == "企业"){
      //如果是企业用户注册，还需补充相关注册信息以便管理员审核
      var user = new Object();
      user.username=username;
      user.password=password;
      user.type=type;
      wx.setStorageSync("user", user)
      wx.navigateTo({
        url: '../register-information/register-information',
      })
    }
    else if (username != '' || password != '' || type == "求职者")
    //如果是求职者用户注册，直接调用注册接口
    user.register(username, password, type, (res) => {
    if(res.success){
      wx.navigateTo({
        url: '../login/login',
      })
    }
    else{
      wx.showToast({
        title: res.message,
        icon: 'none',
        duration: 1000
      })
    }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    return false;
  },
})
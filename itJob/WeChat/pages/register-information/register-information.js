import { Enterprise } from '../../models/enterprise.js'
import { User } from '../../models/user.js'
let user = new User()
let enterprise = new Enterprise()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userid:"",
    faceImg:"",
    name:"",
    type:0,
    financing:"",
    phone:"",
    address:"",
    email:"",
    introduction:"",
    addressLen: 0, //地址的字数
    selfLen:0, //公司介绍的字数
    typelist: ['有限责任公司', '股份有限公司', '集团公司', '一人制公司']  //公司类型数组
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showToast({
      title: "请补充公司信息，以便管理员审核!",
      icon: 'none',
      duration: 2000
    })
  },
  //上传图片
  uploadImgTap: function(){
    var that = this;
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths
        //调用图片上传接口
        wx.uploadFile({
        url: 'http://localhost:8085/upload/faceImg',
        filePath: tempFilePaths[0],
        name: 'file',
        success: (res) => {
          that.setData({
            faceImg:res.data,
          })
        },
      })
      }
    })
  },
  //姓名
  nameTap: function(e){
    var eValue = e.detail.value;
    this.setData({
      name: eValue
    })
  },
  //公司类型
  bindPickerChangeType: function(e){
    this.setData({
      type: e.detail.value
    })
  },
  //融资情况
  financingTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      financing: eValue
    })
  },
  //电话
  phoneTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      phone: eValue
    })
  },
  //邮箱
  emailTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      email: eValue
    })
  },
  //地址
  addressTap: function (e) {
    var eValueLen = e.detail.value.length;
    var eValue = e.detail.value;
    this.setData({
      addressLen: eValueLen,
      address: eValue
    })
  },
  //公司介绍
  introductionTap: function (e) {
    var eValueLen = e.detail.value.length,
        eValue = e.detail.value;
    this.setData({
      selfLen: eValueLen,
      introduction: eValue
    })
  },
  //提交注册信息
  submitTap: function(){
    var that = this;
    var person = new Object();
    var userInfo=wx.getStorageSync('user');
    person.icon = this.data.faceImg;
    person.type=this.data.type;
    person.name = this.data.name;
    person.phone = this.data.phone;
    person.email = this.data.email;
    person.financing = this.data.financing;
    person.address = this.data.address;
    person.introduction = this.data.introduction;

    //调用新增用户接口并获得新增的用户ID
    user.register(userInfo.username, userInfo.password, userInfo.type, (res) => {
      that.setData({
        userid:res.id
      })
      person.userid = that.data.userid
      //调用新增企业接口
      enterprise.insertEnterprise(person, (res) => {
        wx.removeStorageSync('user')
        wx.navigateTo({
          url: '../login/login',
        })

      });
    })
  }
})
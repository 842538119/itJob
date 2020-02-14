import { Applicant } from '../../models/applicant.js'
import { Enterprise } from '../../models/enterprise.js'
import { Position } from '../../models/position.js'
import { Resume } from '../../models/resume.js'
let resume = new Resume()
let position = new Position()
let enterprise = new Enterprise()
let applicant = new Applicant()
var app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        isShow: false,     //是否拿到用户信息，否则显示默认头像
        //myself: ''
        faceImg: "/images/face.png",// 头像
        name:"" ,     // 名字
        person:"",      //个人信息
        user:"",
        hasUser: false,   // 是否显示注册/登录或者用户名
        usersetting:true,     //    退出当前账户
    },

    onLoad: function () {
      //----到时这个放在index页面上，切记！！！！！！！！获得岗位类别并设置到全局变量
      position.getPositionTypeList( (res) =>{
        wx.setStorageSync('positionType', res);
      })
      var that=this; 
      var user=wx.getStorageSync('user');
      var person = wx.getStorageSync('person');
      if(person!="" && person !=null){
        //从缓存获取个人信息数据
        that.setData({
          person: person,
          faceImg:person.icon,
          name:person.name,
          user:user,
          hasUser: true,      //已登录，显示用户名
          usersetting: false  //已登录，显示退出当前按钮字眼
        });
      }
      else if (user != "" && user != null){
         //缓存无数据，再调用接口获取个人信息数据
        if(user.type=="求职者"){
          applicant.getApplicantByUserId(user.id, (res) => {
            wx.setStorageSync('person', res);
            that.setData({
              person: res,
              faceImg: res.icon,
              name: res.name,
              user: user,
              hasUser: true,      //已登录，显示用户名
              usersetting: false  //已登录，显示退出当前按钮字眼
            });
            //获得简历列表
            resume.getResumeByApplicantId(that.data.person.id, (res) => {
              wx.setStorageSync('resumeList', res);
            })
          })
        }
        else{
         enterprise.getEnterpriseByUserId(user.id, (res) => {
            wx.setStorageSync('person', res);
           that.setData({
              person: res,
              faceImg: res.icon,
              name: res.name,
              user: user,
              hasUser: true,      //已登录，显示用户名
              usersetting: false  //已登录，显示退出当前按钮字眼
            });
          })
        } 
      }
      else{
        that.setData({
          hasUser: false,      //未登录，显示注册登录字眼
          usersetting: true    //未登录，隐藏退出当前按钮字眼
        });
      } 
    },

    //编辑个人信息
    editInfoTap: function () {
      if(this.data.user == ""){
        wx.navigateTo({
          url: '/pages/login/login',
        })
      }
      else{
        if (this.data.user.type=='求职者'){
          wx.navigateTo({
            url: '/pages/edit-my-app/edit-my-app',
          })
        }
        else if (this.data.user.type == '企业'){
          wx.navigateTo({
            url: '/pages/edit-my-ent/edit-my-ent',
          })
        }
      }
    },

    //简历
    resumeTap: function () {
      wx.navigateTo({
         url: '/pages/resume/my-resume-list/my-resume-list'
      });
          
    },
    //发布职位
    addPositionTap: function () {
    wx.navigateTo({
      url: '/pages/ent-position-add/ent-position-add'
    });
    },
    //编辑职位
    editPositionTap: function () {
      wx.navigateTo({
        url: '/pages/ent-position-list/ent-position-list'
      });
    },
    //我的投递
    myDeliveryTap: function () {
      wx.navigateTo({
        url: '/pages/app-apply-list/app-apply-list'
      });
    },
    //投递列表
    appliesTap: function () {
    wx.navigateTo({
      url: '/pages/ent-apply-list/ent-apply-list'
    });
    },
    //收藏列表
  collectTap: function () {
    wx.navigateTo({
      url: '/pages/app-collect-list/app-collect-list'
    });
  },
    //编辑头像
    imgTap: function(){
        var that = this;
        wx.chooseImage({
            count: 1, // 默认9
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            success: function (res) {
                // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
                var tempFilePaths = res.tempFilePaths

                app.globalData.userInfo.avatarUrl = tempFilePaths;
                wx.setStorageSync('userInfo', app.globalData.userInfo);
                that.setData({
                    userInfo: app.globalData.userInfo
                })

            }
        })
    },
    deliveryComments:function(){
      wx.navigateTo({
        url: '../opinion/opinion',
      })
    },
    login:function(){
      wx.navigateTo({
        url: '../login/login',
      })
    },
    // 退出账户登录
    signOut:function(){
      var _this = this ;

      //恢复到没登录的样式
      try {
        //清除缓存的用户数据和个人信息数据
        wx.removeStorageSync('user')
        wx.removeStorageSync('person')
      } catch (e) {}
      if (true){
        wx.showModal({
          title: '提示',
          content: '退出登录成功',
          success: function (res) {
            if (res.confirm) {
              _this.setData({
                faceImg: "/images/face.png",      
                person: "",
                user:"",
                hasUser: false,
                usersetting: true
              });
            } else if (res.cancel) {
              // console.log('用户点击取消')
            }
          }
        })
      }   
    }
})
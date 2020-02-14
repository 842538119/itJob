import { Position } from '../../models/position.js'
let position = new Position()
var app = getApp()
var WxSearch = require('../../components/wxSearch/wxSearch.js');
Page({
  /**
 * 页面的初始数据
 */
  data: {
    name:"",
    city:"不限",
    positionList:[],
    pageNum:1,
    pageSize:5,
    viewHeight: 600,
    hasMoreData:true,
    editList: false, //此属性用于标识编辑职位or预览职位
    loadingHidden:true,
    loadingText:"加载中...",
    searchValue:"",
    wxSearchData: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //初始化的时候渲染wxSearchdata
    WxSearch.init(this, 48, ['运营', '赛事', '商务', '实习生']);
    WxSearch.initMindKeys(['腾讯体育', '乐视体育', '阿里体育']);
    //初始化职位列表
    this.getPositionList();
    //得到当前城市
  },
  /**
   * 获得职位列表
   */
  getPositionList(){
    var that=this;
    var city1=this.data.city
    if(city1=="不限"){
      city1=""
    }
    position.getPositionList(this.data.pageNum, this.data.pageSize, this.data.name, city1,(res) => {
      var contentlistTem = that.data.positionList;
      if(that.data.pageNum==1){
        that.setData({
          positionList: res
        })
        if(res.length<that.data.pageSize){
          that.setData({
            hasMoreData: false,
            loadingHidden: false,
            loadingText:'暂无更多...'
          })
        }
      }
      else{
        if (res.length < that.data.pageSize) {
          that.setData({
            positionList: contentlistTem.concat(res),
            hasMoreData: false,
            loadingText: '暂无更多...'
          })
        }
        else{
          that.setData({
            positionList: contentlistTem.concat(res),
            hasMoreData: true,
            loadingHidden: true
          })
        }
      }
    })
  },
  //点击城市按钮
  wxChangeCity:function(){
    wx.navigateTo({
      url: '../change-city/change-city?name='+this.data.name,
    })
  },
  //点击搜索按钮
  wxSearchFn: function (e) {
    var that = this;
    WxSearch.wxSearchAddHisKey(that);
     this.setData({
       pageNum:1,
       pageSize:5,
       positionList: [],
       hasMoreData: true,
       loadingHidden: true
     })
    this.getPositionList();
  },
  //搜索条件改变
  wxSearchInput: function (e) {
    var that = this
    WxSearch.wxSearchInput(e, that);

    if (e.detail.value == '' || e.detail.value == undefined) {
      that.setData({
        name: ""
      })
    } else {
      that.setData({
        name: e.detail.value
      })
    }
  },
  //点击热门搜索标签
   wxSearchKeyTap: function (e) {
        var that = this;
        console.log(e.detail.value)
        WxSearch.wxSearchKeyTap(e, that);
        if (that.data.wxSearchData.value) {
          this.setData({
            name: that.data.wxSearchData.value,
            pageNum: 1,
            pageSize: 5,
            positionList: [],
            hasMoreData: true,
            loadingHidden: true
          })
          this.getPositionList();
        }
    },
  //获取搜索输入框焦点
  wxSearchFocus: function (e) {
    var that = this;
    this.data.wxSearchData.view.isShow = true;
    WxSearch.wxSearchFocus(e, that);
    that.setData({
      wxSearchData: this.data.wxSearchData
    });
  },
  wxSearchDeleteKey: function (e) {
    var that = this
    WxSearch.wxSearchDeleteKey(e, that);
  },
  wxSearchDeleteAll: function (e) {
    var that = this;
    WxSearch.wxSearchDeleteAll(that);
  },
  wxSearchTap: function (e) {
    var that = this
    WxSearch.wxSearchHiddenPancel(that);
  },
  /**
    * 页面上拉触底事件的处理函数
    */
  onReachBottom: function () {
    if (this.data.hasMoreData) {
      var pageNum=this.data.pageNum+1;
      this.setData({
        pageNum: pageNum,
        loadingHidden:false
      })
      this.getPositionList();
    }
  },    
})
// pages/demo/demo.js
import { Position } from '../../models/position.js'
let position = new Position()
let City = require('../../utils/allcity.js');

Page({

  data: {
    name:"",
    city: [],
    config: {
      horizontal: true, // 第一个选项是否横排显示（一般第一个数据选项为 热门城市，常用城市之类 ，开启看需求）
      animation: true, // 过渡动画是否开启
      search: true, // 是否开启搜索
      searchHeight: 45, // 搜索条高度
      suctionTop: true // 是否开启标题吸顶
    }
  },
  onLoad:function(options) {
    // // 模拟服务器请求异步加载数据
    this.setData({
      city: City,
      name:options.name
    })

  },
  bindtap(e) {
    var city = e.detail.name;
    if(city=="不限"){
      city="";
    }
      position.getPositionList(1, 5, this.data.name, city, (res) => {
      var pages = getCurrentPages();
      var curPage = pages[pages.length - 2];
      //更新上一级页面的数据
      curPage.setData({
          positionList: res,
          pageNum: 1,
          pageSize: 5,
          city: e.detail.name,
          hasMoreData: true,
          loadingHidden: true,
      });
      if (res.length < 5) {
          curPage.setData({
            hasMoreData: false,
            loadingHidden: false,
            loadingText: '暂无更多...'
          })
      }  
      //返回上一个页面
      setTimeout(function () {
        wx.navigateBack({
        })
      }, 1000);
    })
  },
})
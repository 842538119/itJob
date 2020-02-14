var that
Page({
  data: {
    content: '',
    formats: {}, // 样式
    placeholder: '开始输入...',
  },
  onLoad() {
    that = this;
  },
  // 初始化编辑器
  onEditorReady() {
    wx.createSelectorQuery().select('#editor').context(function (res) {
      that.editorCtx = res.context
      if (wx.getStorageSync("content")) { // 设置~历史值
        that.editorCtx.setContents({html:wx.getStorageSync("content")}) // 注意：插入的是对象
      }

    }).exec()
  },
  // 返回选区已设置的样式
  onStatusChange(e) {
    // console.log(e.detail)
    const formats = e.detail
    this.setData({
      formats
    })
  },
  // 内容发生改变
  onContentChange(e) {
    // console.log("内容改变")
    // console.log(e.detail)
    // that.setData({
    //   content: e.detail
    // })
    // wx.setStorageSync("content", e.detail)
  },
  // 失去焦点
  onNoFocus(e) {
    // console.log("失去焦点")
    // console.log(e.detail)
    // that.setData({
    //   content: e.detail
    // })
    // wx.setStorageSync("content", e.detail)
  },
  // 保存内容
  clickLogText(e) {
    that.editorCtx.getContents({
      success: function (res) {
        var a=res.html.replace(/wx:nodeid="\d+"/g, '');
        wx.setStorageSync("content", a); // 缓存本地
        //更新上一级页面的职位描述
        var pages = getCurrentPages();
        var curPage = pages[pages.length - 2];
        curPage.setData({
          duties: a, //此处为设置职位详情
          introduction: a, //此处为设置个人介绍详情
          content: a, //此处为设置简历详情
        });
        //返回上一个页面
        setTimeout(function () {
          wx.navigateBack({
          })
        }, 1000);
      }
    })
  },
  // 清空所有
  clear() {
    this.editorCtx.clear({
      success: function (res) {
        console.log("清空成功")
      }
    })
  },
  // 清除样式
  removeFormat() {
    this.editorCtx.removeFormat()
  },
  // 记录样式
  format(e) {
    let {
      name,
      value
    } = e.target.dataset
    if (!name) return
    this.editorCtx.format(name, value)
  },
})
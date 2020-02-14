// components/resume-project/resume-project.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    project:{
      type:Object
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    delIcon: "images/del.png"
  },

  /**
   * 组件的方法列表
   */
  methods: {
    onTap: function () {
      var projectId = this.properties.project.id;
      wx.navigateTo({
        url: '/pages/resume/edit-resume-project/edit-resume-project?projectId=' + projectId,
      })
    },
    delTap: function () {
      var projectId = this.properties.project.id;
      var that = this;
      wx.showModal({
        title: '确定删除该信息吗？',
        success(res) {
          if (res.confirm) {
            that.triggerEvent('delProject', { id: projectId }, {})
          }
        }
      })
    }
  }
})

Component({
  /**
  * 组件的属性列表
  */
  properties: {
    position:{
      type:Object
    },
    enterprise:{
      type:Object
    },
    flag:{
      type:Boolean
    },
    status:{
      type:Number
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    statusList: ['未审核', '已发布', '已撤销'],
    educationList: ['不限', '中专', '大专', '本科', '硕士', '博士'],
    experienceList: ['不限', '应届毕业', '1年以下', '1-3年', '3-5年', '5年以上'],
    editImg: './images/edit.png',
    delImg: './images/delete.png'
  },

  /**
   * 组件的方法列表
   */
  methods: {
    onTap(event){
      var positionId = this.properties.position.id;
      wx.navigateTo({
        url: '/pages/position-detail/position-detail?positionId=' + positionId,
      })
    },
    editTap(event){
      wx.setStorageSync('position', this.properties.position);
      wx.navigateTo({
        url: '/pages/ent-position-edit/ent-position-edit',
      })
    },
    revokeTap(event) {
      var positionId = this.properties.position.id;
      var that=this;
      wx.showModal({
        title: '确定撤销该职位吗',
        success(res) {
          if (res.confirm) {
            that.triggerEvent('revoke', { id: positionId }, {})
          } 
        }
      })    
    },
  }
});
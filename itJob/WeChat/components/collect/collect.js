// components/collect/collect.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    isHaveCollect:{
      type:Boolean
    },
  },

  /**
   * 组件的初始数据
   */
  data: {
    noSrc: "images/collect-no.png",
    yesSrc: "images/collect-yes.png"
  },

  /**
   * 组件的方法列表
   */
  methods: {
    onTap:function(){
      this.triggerEvent('collect', {}, {})
    }
  }
})

import {config} from '../config.js'
class HTTP{
  request(params){
     wx.request({
       url: config.api_base_url+params.url,
       method:params.method,
       data:params.data,
       header:{
         'content-type':'application/json',
       },
       success:(res)=>{
         let flag=res.data.success
         if (!flag){
           wx.showToast({
             title: res.data.message,
             icon:'none',
             duration:2000
           })
         }
         else if (flag){
           if (res.data.message != null){
             wx.showToast({
               title: res.data.message,
               icon: 'success',
               duration: 2000
             })
           }
           if(res.data.data == null){
             res.data.data=true;
           }    
           params.success(res.data.data)
         }
       }
     })
  }
}
export {HTTP}
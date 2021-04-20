// pages/square/square.js
var util = require('../../utils/util.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
      userid: '',
      info: [],
      photoWidth: wx.getSystemInfoSync().windowWidth / 4,
      praise: 14
  },
  delete: function(e){
      var name = e.currentTarget.dataset.name;
      var text = e.currentTarget.dataset.text == ""?
      "null":e.currentTarget.dataset.text;
      wx.request({
        url: 'http://localhost:8888/test/square/deletemsg/'
        +name+'/'+text,
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function(e){
          console.log(e)
          if(e.data == true){
            wx.showModal({
              title: '删除朋友圈成功',
              content: '即将刷新',
              success(res){
                if(res.confirm){
                  wx.reLaunch({
                    url: '../square/square',
                  })
                }else{
                  wx.reLaunch({
                    url: '../square/square',
                  })
                }
              }
            });
          }
        }
      })

  },
  //跳转
  addmsg: function(){
    var that = this;
    if(that.data.userid == ''){
      wx.showModal({
        title: '未登录',
        content: '请登录',
        success(res){
          if(res.confirm){
            wx.switchTab({
              url: '/pages/personal/personal'
            });
          }else{
            wx.switchTab({
              url: '/pages/personal/personal'
            });
          }
        }
      });
    }else{
      wx.navigateTo({
        url: 'addmsg/addmsg'
      })
    }
  },
  //查看大图
  LookPhoto: function(e){
    var urls;
    console.log(e.currentTarget.dataset.photurl.imgurl)
    wx.previewImage({
      urls: ['http://localhost:8888'+e.currentTarget.dataset.photurl.imgurl],
    })
    console.log(urls)
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    var that = this;
    that.setData({
      userid: wx.getStorageSync('userid')
    });
      wx.request({
        url: 'http://localhost:8888/test/square/allmsg',
        method:'POST',
        data:{
          userid: this.data.userid,
          info: this.data.info
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded' 
        },
        success: (res)=>{
          var arr = []
          for (var i = 0; i < res.data.length; i++){
            arr.push({
              name:res.data[i].name,
              imgurl: res.data[i].imgurl,
              text: res.data[i].text,
              date: new Date(res.data[i].date).toLocaleDateString()
            })
          }
          that.setData({
            info: arr.reverse()
          })
        }
      })
  },
})
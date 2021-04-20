// pages/personal/login/findpwd/findpwd.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: ''
  },

  nameInput: function(e){
    var that = this;
    that.setData({
      name: e.detail.value
    })
  },

  submit: function(){
    var that = this;
    var name = that.data.name;
    wx.request({
      url: 'http://localhost:8888/test/resign/check/'+name,
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function(res){
        if(res.data == true){
          wx.request({
            url: 'http://localhost:8888/test/resign/findpwd/'+name,
            method: 'get',
            header: {
              'content-type': 'application/x-www-form-urlencoded'
            },
            success: function(res){
              wx.setStorageSync('findpwd', name);
              wx.navigateTo({
                url: 'code/code',
              })
            }
        })
        }else{
          wx.showModal({
            title: 'Error',
            content: '查询的用户不存在',
            success(res){
              if(res.confirm){
                wx.navigateTo({
                  url: '/pages/personal/login/findpwd/findpwd'
                });
              }else{
                wx.navigateTo({
                  url: '/pages/personal/login/findpwd/findpwd'
                });
              }
            }
          });
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    wx.reLaunch({
      url: '/pages/personal/login/login',
    })
  }
})
// pages/personal/changeimg/changeimg.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    userid: '',
    images: '',
    mail: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
      userid: wx.getStorageSync('userid'),
      images: wx.getStorageSync('images')
    })
    wx.request({
      url: 'http://localhost:8888/test/mail/msg/'+that.data.userid,
      method: 'post',
      header:{
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function(res){
        that.setData({
          mail: res.data
        })
        wx.setStorageSync('mail', that.data.mail);
      }
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this;
    that.setData({
      userid: wx.getStorageSync('userid'),
      images: wx.getStorageSync('images'),
      mail: wx.getStorageSync('mail')
    })
    wx.setStorageSync('mail', that.data.mail);
  },
  //上传图片
  fileUploadTap: function(){
    var that = this;
    wx.chooseImage({
      success(res) {
        const tempFilePaths = res.tempFilePaths;
        var radon = Math.random().toString(36).substring(2);
        wx.uploadFile({
          url: 'http://localhost:8888/test/upuserimage/'+that.data.userid+"/" + radon,
          filePath: tempFilePaths[0],
          name: 'file',
          formData: {
            description: '图片'
          },
          success(res) {
            var imaurl = '';
            if(res.data == "success"){
              imaurl = 'http://localhost:8888/test/upimage/'+that.data.userid+"/ts=${new Date().getTime()}";
              wx.clearStorage();
              wx.setStorageSync('userid', that.data.userid);
              wx.setStorageSync('images', imaurl);
              wx.showModal({
                title: '更换图片成功',
                content: '即将返回首页',
                success(res){
                  if(res.confirm){
                    wx.switchTab({
                      url: '/pages/index/index'
                    });
                  }else{
                    wx.switchTab({
                      url: '/pages/index/index'
                    });
                  }
                }
              });
            }else{
              wx.showModal({
                title: '更换图片失败',
                content: '请重试',
                success(res){
                  if(res.confirm){
                    wx.navigateTo({
                      url: '/pages/personal/changeimg/changeimg'
                    });
                  }else{
                    wx.navigateTo({
                      url: '/pages/personal/changeimg/changeimg'
                    });
                  }
                }
              });
            }
          }
        })
      }
    })
  },

})
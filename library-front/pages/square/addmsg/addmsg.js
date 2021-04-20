Page({
  data: {
    userid: '',
    text: '',
    tempFilePaths: '',
    imgsrc: '',
  },
  onLoad: function (options) {
    this.setData({
      userid: wx.getStorageSync('userid'),
      imgsrc: 'static/upimg.png'
    })
  },
  inputtext:function(e){
    this.setData({
      text:e.detail.value
    })
  },
  submit: function(){
    var that = this;
    var radon = Math.random().toString(36).substring(2);

    if(that.data.text == "" && that.data.tempFilePaths == ""){
      wx.showToast({
        title: '发布内容为空',
        icon: 'none',
        duration: 2000
      })
    }else{
      if(that.data.text == ""){
        that.setData({
          text: "null"
        })
      }
      if(that.data.tempFilePaths == ""){
        wx.request({
          url: 'http://localhost:8888/test/upsquaretext/'+that.data.userid+"/"+that.data.text,
          method: 'POST',
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          success(res){
            console.log(res.data)
            wx.showModal({
              title: '发布朋友圈成功',
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
          }
        })
      }else{
        wx.uploadFile({
          url: 'http://localhost:8888/test/upsquareimg/'+that.data.userid+"/"+that.data.text+"/" + radon,
          filePath: that.data.tempFilePaths != ""?that.data.tempFilePaths[0]:null,
          name: 'file',
          formData: {
            description: '图片'
          },
          success(res){
            console.log(res.data)
            wx.showModal({
              title: '发布朋友圈成功',
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
          }
        })
      }
    }
  },

  upimg: function(){
    var that = this;
    wx.chooseImage({
      success(res) {
        that.setData({
          tempFilePaths: res.tempFilePaths,
          imgsrc: res.tempFilePaths
        })
      }
    })
  }

})
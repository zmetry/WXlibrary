const app = getApp()
var userid = wx.getStorageSync("userid");
Page({
  data: {
    hasUserImage:false,
    userid: "",
    images:""
  },
  getData: function(){
      var uid = this.data.userid;
      console.log("uid" + uid);
  },
  loginpage: function(){
    wx.navigateTo({
      url: '../personal/login/login'
    })
  },
  // 事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onShow: function() {
    var that = this;
    that.setData({
      userid: wx.getStorageSync("userid"),
      images: wx.getStorageSync('images')
    })
    if(userid != ''){
      console.log("获取图片")
      that.getimg()
    }
    if(that.data.images == ''){
      that.setData({
        images:'/images/person.png'
      })
    }
    this.getData();
    console.log("userid:", userid);
  },
  onLoad: function(){
    var that = this;
    that.setData({
      userid: wx.getStorageSync("userid"),
      images: wx.getStorageSync('images')
    })
    this.getData();
  },

  getimg: function(){
    var that = this;
    wx.request({
      url: 'http://localhost:8888/test/userimage/'+that.data.userid,
      method: 'get',
      data: {
        username: that.data.username,
        password: that.data.password
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      success(res){
      var imaurl = '';
      if(res.statusCode == '200'){
        imaurl = 'http://localhost:8888/test/userimage/'+that.data.userid;
      }else{
        imaurl = 'http://localhost:8888/test/userimage/user.jpg';
      }
      //去请求相应的用户图片
      wx.setStorageSync('image', imaurl)
          that.setData({
          image: imaurl
      })
    }
    })
  }
})









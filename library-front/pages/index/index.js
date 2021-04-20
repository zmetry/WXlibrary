var getData = require('../../data/data.js');

Page({
  /**
   * 页面的初始数据,可以为空
   */
  data: {
    question: '',
    //轮播图配置
    autoplay: true,
    interval: 3000,
    duration: 1200,
    imgUrl: [],
    leassonList: [],
    lessonId: '',
  },
  // 查询搜索的接口方法
  a: function () {
   
  },

  onLoad: function () {
    var that = this; 
    var data = {
      "datas": [
        {
          "id": 1,
          "imgurl": "static/1.png"
        },
        {
          "id": 2,
          "imgurl": "static/2.png"
        },
        {
          "id": 3,
          "imgurl": "static/3.png"
        },
        {
          "id": 4,
          "imgurl": "static/4.png"
        }
      ]
    }; 
    console.log(getData);
    var arr = []
    for (var i = 0; i < getData.detailList.length; i++) {
      console.log(getData.detailList[i].leassonType)
      arr.push({
        id: getData.detailList[i].id,
        imgUrl: getData.detailList[i].imgUrl,
        title: getData.detailList[i].title,
        leassonType: getData.detailList[i].leassonType,
      })
    };

    console.log(that.data.imgUrl)
    console.log(arr)
    that.setData({
      lunboData: data.datas,
      imgUrls: getData.bannerList,
      leassonList: arr.reverse()
    })
  },

  searchInput: function(e){
      this.setData({
        question: e.detail.value
      })
  },

  toPage: function(e){
    console.log(e.currentTarget.dataset.id);
    this.setData({
      lessonId: e.currentTarget.dataset.id
    })
    var url = '';
    console.log(this.data.lessonId)
    switch(this.data.lessonId){
      case 1:
        url = 'http://ln.sina.com.cn/news/2020-05-13/detail-iircuyvi2866364.shtml';
        break;
      case 2:
        url = 'https://news.sina.com.cn/w/2021-04-03/doc-ikmxzfmk2593425.shtml';
        break;
      case 3:
        url = 'https://news.sina.com.cn/c/2021-04-02/doc-ikmyaawa4363685.shtml';
        break;
      case 4:
        url = 'https://news.sina.com.cn/w/2021-04-03/doc-ikmyaawa5327737.shtml';
        break;
      default:
        console.log(1)
        break;
    }
    wx.setStorageSync('web', url);
    wx.navigateTo({
      url: 'web-view/web-view'
    })
  },

  search: function(){
    var that = this;
    if(that.data.question != ''){
      wx.request({
        url: 'https://www.baidu.com/s?ie=UTF-8&wd='+that.data.question,
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        data: {
          question: that.data.question
        },
        success(res){
          if(res.statusCode == 200){
            wx.setStorageSync('web', 'https://www.baidu.com/s?ie=UTF-8&wd='+that.data.question);
            wx.navigateTo({
              url: 'web-view/web-view'
            })
          }
        }
      })
    }
  }
})

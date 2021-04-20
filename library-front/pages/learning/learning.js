Page({
  data: {
    userid: '',
    seats :[],
    col: '',
    row: '',
    exist:'',
    id: '',
    sum: 0,
    free: 0,
    //添加一个重新登陆时的状态获取
    studying: 'false'
  },

  connect: function(){
    var that = this;
    var id = that.data.id;
    console.log(that.data);
    if(that.data.userid == ''){
      wx.showModal({
        title: '错误',
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
      wx.request({
        url: 'http://localhost:8888/test/libseat/reserve/'+id,
        method: 'POST',
        header:{
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function(res){
          if(res.data == true){
            var date = new Date();
            var curdate = date.getFullYear()+"年"+
            (date.getMonth()+1)+"月"+date.getDate()+"日";
            var BfDate = (date.getHours() * 60) + date.getMinutes();
            wx.setStorageSync('BfDate', BfDate);
            var msg = curdate + "       图书馆第"+that.data.col+"行"+
            "第"+that.data.row+"列学习";
            wx.request({
              url: 'http://localhost:8888/test/usermessage/setmessage/'+
              that.data.userid+'/'+msg,
              method: 'get',
              header: {
                'content-type': 'application/x-www-form-urlencoded'
              },
              success: function(res){
                if(res.data == true){
                  that.setData({
                    studying: 'true'
                  })
                  //写入信息到数据库
                  wx.request({
                    url: 'http://localhost:8888/test/UserStduy/InsMsg/'+that.data.userid+"/"+that.data.col + "/" + that.data.row+ "/"+ BfDate + "/"+ that.data.id,
                    method: 'post',
                    header: {
                    'content-type': 'application/x-www-form-urlencoded'
                    }
                  })
                  wx.showModal({
                    title: '预定成功',
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
              }
            })
          }
        }
      })
    }
  },

  check: function(e){
    //点击事件
    var that = this;
    if(that.data.userid == ''){
      wx.showModal({
        title: '错误',
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
      if(that.data.studying === "true"){
        var msg = "第"+that.data.col+"列，" +"第"+that.data.row+"行"
        wx.showModal({
          title: '错误',
          content: '您当前在\n\r'+msg+'\n\r学习',
          success(res){
            if(res.confirm){
              wx.navigateTo({
                url: 'study/study'
              })
            }else{
              wx.navigateTo({
                url: 'study/study'
              })
            }
          }
        });
      }else{
        if(e.currentTarget.dataset.exist == 1){
          wx.showModal({
            title: '错误',
            content: '当前座位已经被选上',
            success(res){
              if(res.confirm){
                wx.switchTab({
                  url: '/pages/learning/learning'
                });
              }else{
                wx.switchTab({
                  url: '/pages/learning/learning'
                });
              }
            }
          });
        }else{
          //设置当前获取的值
          //需要修改：重复点击之后图标消失，文字不消失
          var id = e.currentTarget.dataset.id;
          var arr = this.data.seats;
          if(id == this.data.id){
            for(let i = 1;i <= arr.length;i++){
              if(i == id){
                arr[i - 1].visiB = 'block';
                arr[i - 1].visiC = 'none';
                break;
              }
            }
            that.setData({
              seats: arr,
              col: '',
              row: '',
              id: ''
            })
          }else{
            that.setData({
              col: e.currentTarget.dataset.col,
              row: e.currentTarget.dataset.row,
              id: e.currentTarget.dataset.id
            })
            for(let i = 1;i <= arr.length;i++){
              if(i == id){
                arr[i - 1].visiB = 'none';
                arr[i - 1].visiC = 'block';
              }else{
                arr[i - 1].visiC = 'none';
                arr[i - 1].visiB = 'block';
              }
            }
            that.setData({
              seats: arr
            })
          }
        }
      }
    }
  },

  onLoad(){
    var that = this
    that.setData({
      userid: wx.getStorageSync('userid')
    })
  },

  onShow() {
    var that = this;
    console.log(that.data)
    that.setData({
      userid: wx.getStorageSync('userid'),
      id: '',
      col: '',
      row: ''
    })
    wx.request({
      url: 'http://localhost:8888/test/UserStudy/userexist/'+that.data.userid,
      method: 'post',
      header:{
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function(res){
        if(res.data == true){
          wx.request({
            url: 'http://localhost:8888/test/UserStudy/getMsg/'+that.data.userid,
            method: 'post',
            header:{
              'content-type': 'application/x-www-form-urlencoded'
            },
            success: function(res){
              console.log(res.data)
              that.setData({
                studying: 'true',
                col: res.data.cols,
                row: res.data.ros
              })
            }
          })
        }else{
          that.setData({
            studying: 'false'
          })
        }
      }
    })
    if(that.data.studying === "true"){
      //已经在学习了
      wx.navigateTo({
        url: 'study/study'
      })
    }
    wx.request({
      url: 'http://localhost:8888/test/libseat/allmessage',
      method: 'POST',
      header:{
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function(res){
        var arr = []
        that.setData({
          sum: 0,
          free: 0
        })
          for (var i = 0; i < res.data.length; i++){
            arr.push({
              col:res.data[i].cols,
              row: res.data[i].ros,
              exist: res.data[i].exist,
              id: res.data[i].id ,
              visiB: 'block',
              visiC: 'none'
            })
            that.setData({
              sum : that.data.sum + 1
            })
            if(res.data[i].exist == 0){
              that.setData({
                free : that.data.free + 1
              })
            }
          }
          that.setData({
            seats: arr
          })
      }
    })
  }
})
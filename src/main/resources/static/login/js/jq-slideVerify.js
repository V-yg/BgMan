(function (global, factory) {
	typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory() :
	typeof define === 'function' && define.amd ? define(factory) :
	(window.slideVerifyPlug = factory());
}(this, (function () { 'use strict';
    
    var SlideVerify = function (ele,opt) {
        this.$ele = $(ele);
        //默认参数
        this.defaults = {
            getSucessState:function(){
            	
            }
        }

        this.settings = $.extend({}, this.defaults, opt);
        this.touchX = 0 ;
        this.slideFinishState = false;
        this.init();
    }
    SlideVerify.prototype = {
    	constructor: SlideVerify,
        init:function () {
            var _this = this;
            // _this.initStyle();
            _this.initEle();
            _this._mousedown();
            _this._mouseup();
            _this._touchstart();
            _this._touchmove();
            _this._touchend();
        },
        initEle:function(){
        	this.slideBtn = this.$ele.find('.dragBtn');
	        this.slideProEle = this.$ele.find('.dragProgress');
	        this.slideSucMsgEle = this.$ele.find('.sucMsg');
	        this.slideFixTipsEle = this.$ele.find('.fixTips');
	        this.maxSlideWid = this.calSlideWidth();
        },
        _mousedown:function(){
        	var _this = this;
        	
        	_this.slideBtn.on('mousedown',function(e){
        		var distenceX = e.pageX;
        		e.preventDefault();
        		if(_this.ifSlideRight() || _this.ifAnimated()){
					return false;
				}
        		$(document).mousemove(function(e){
					var curX = e.pageX - distenceX;
					if(curX >= _this.maxSlideWid){
						_this.setDragBtnSty(_this.maxSlideWid);
						_this.setDragProgressSty(_this.maxSlideWid);
						_this.cancelMouseMove();
						_this.slideFinishState = true;
						if(_this.settings.getSucessState){
		                    _this.settings.getSucessState(_this.slideFinishState);
		                }
						_this.successSty();
					}else if(curX <= 0){
						_this.setDragBtnSty('0');
						_this.setDragProgressSty('0');
					}else{
						_this.setDragBtnSty(curX);
						_this.setDragProgressSty(curX);
					}
				})
				$(document).mouseup(function(){
			      	_this.cancelMouseMove();
			   });
        	})
        	
        },
        _mouseup:function(){
        	var _this = this;
        	$(document).on('mouseup',function(){
        		if(_this.ifSlideRight()){
					_this.cancelMouseMove();
					return false;
				}else{
					_this.failAnimate();
				}
        	})
        },
        _touchstart:function(){
        	var _this = this;
        	_this.slideBtn.on('touchstart',function(e){
        		_this.touchX = e.originalEvent.targetTouches[0].pageX;
				if(_this.ifSlideRight() || _this.ifAnimated()){
//					_this.cancelTouchmove();
					return false;
				}
        	})
        },
        _touchmove:function(){
        	var _this = this;
        	_this.slideBtn.on('touchmove',function(e){
        		e.preventDefault();
        		var curX = e.originalEvent.targetTouches[0].pageX - _this.touchX;
        		if(curX >= _this.maxSlideWid){
					_this.setDragBtnSty(_this.maxSlideWid);
					_this.setDragProgressSty(_this.maxSlideWid);
					_this.cancelTouchmove();
					_this.successSty();
					_this.slideFinishState = true;
					if(_this.settings.getSucessState){
	                    _this.settings.getSucessState(_this.slideFinishState);
	                }
					_this.slideFinishState = true;
				}else if(curX <= 0){
					_this.setDragBtnSty('0');
					_this.setDragProgressSty('0');
				}else{
					_this.setDragBtnSty(curX);
					_this.setDragProgressSty(curX);
				}
        	})
        },
        _touchend:function(){
        	var _this = this;
        	_this.slideBtn.on('touchend',function(){
        		if(_this.ifSlideRight()){
					_this.cancelTouchmove();
					return false;
				}else{
					_this.failAnimate();
				}
        	})
        },
        getDragBtnWid:function(){//获取滑块的宽度，
        	return parseFloat(this.slideBtn.outerWidth());
        },
        getDragWrapWid:function(){//获取  本容器的的宽度，以防万一
        	return parseFloat(this.$ele.outerWidth());
        },
        calSlideWidth:function(){
        	var _this = this;
        	return _this.getDragWrapWid() - _this.getDragBtnWid()
        },
        ifAnimated:function(){//判断 是否动画状态
        	return this.slideBtn.is(":animated")
        },
        getDragBtnLeft:function(){ //判断当前 按钮 离左侧的距离
        	return parseInt(this.slideBtn.css('left'));
        },
        ifSlideRight:function(){
        	var _this = this;
        	if(_this.getDragBtnLeft() == _this.calSlideWidth()){
        		return true;
        	}else{
        		return false;
        	}
        },
        setDragBtnSty:function(left){
        	this.slideBtn.css({
				'left':left
			})
        },
        setDragProgressSty:function(wid){
        	this.slideProEle.css({
				'width':wid
			})
        },
        cancelMouseMove:function(){
        	$(document).off('mousemove');
        },
        cancelTouchmove:function(){
        	this.slideBtn.off('touchmove');
        },
        successSty:function(){
        	this.slideSucMsgEle.show();
			this.slideBtn.addClass('suc-drag-btn');
        },
        failAnimate:function(){
        	this.slideBtn.animate({
				'left':'-1px'
			},200);
			this.slideProEle.animate({
				'width':0
			},200)
       },
       resetVerify:function(){
       		this.slideSucMsgEle.hide();
			this.slideBtn.removeClass('suc-drag-btn');
			this.slideFinishState = false;
			this.slideProEle.css({
				'width':0
			});
			this.slideBtn.css({
				'left':'-1px'
			})
			this._touchmove();
       },
    }

	var slideVerify = window.slideVerifyPlug || SlideVerify;

	return slideVerify;

})));
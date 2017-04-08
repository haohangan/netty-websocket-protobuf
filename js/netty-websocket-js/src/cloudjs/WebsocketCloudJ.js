require('./WSMessage_pb');
var $ = require('jquery');
/**
 * 创建websocket连接
 */
var host = "localhost";//ws://127.0.0.1:81/app1?uname=
var port = 80;
var basePath = "/app1";
var websocket;
var uid;
var reqtime = 0;

function conn(uid,url){
	websocket = new WebSocket(url+uid+'&pwd=pwd',"zookeeperWS");
	websocket.onopen = function(){
		console.info("打开websocket.");
		$('#Open_Button').attr("disabled","disabled");
		$('#Send_Button').attr("disabled",null);
		$('#SendBin_Button').attr("disabled",null);
		$('#Close_Button').attr("disabled",null);
		$('#Reg_Button').attr("disabled",null);
		$('#Send_Group_Button').attr("disabled",null);
	};
	websocket.onclose = function(){
		console.info("关闭websocket.");
		$('#Open_Button').attr("disabled",null);
		$('#Send_Button').attr("disabled","disabled");
		$('#SendBin_Button').attr("disabled","disabled");
		$('#Close_Button').attr("disabled","disabled");
		$('#Reg_Button').attr("disabled","disabled");
		$('#Send_Group_Button').attr("disabled","disabled");
	};
	websocket.onerror =  function(){
		console.info("websocket error");
	};
	websocket.onmessage = function(e){
		var data = e.data;
		var type = typeof data;
		if(type==='string'){
			///alert(data);
			//$('#server').prepend(data+'<br /> ');
		}else if(type==='object'){
			if(data instanceof Blob){
				var reader = new FileReader();
				reader.addEventListener("loadend", function() {
				   // reader.result contains the contents of blob as a typed array
				   var bytes = reader.result;
				   var message = proto.WSCMessage.deserializeBinary(bytes);
				   $('#server').prepend(message.getUid()+'-->'+message.getTxt()+'-->'+message.getTId()+'<br /> ');
				});
				reader.readAsArrayBuffer(data);
			}else if(data instanceof ArrayBuffer){
				console.info('??');
			}
		}
	};

}

function send(message){
	reqtime = reqtime +1;
	websocket.send(message);
}

function sendBin(userid,txt){
	reqtime = reqtime +1;
	var message = new proto.WSCMessage();
	message.setType(proto.WSCMessage.MsgType.MSG);
	message.setMid(reqtime);
	message.setUid(uid);
	message.setTId(userid);
	message.setTxt(txt);
	message.setBroadcast(true);
	bytes = message.serializeBinary();
	websocket.send(bytes);
}

function reg(groupName){
	reqtime = reqtime +1;
	var message = new proto.WSCMessage();
	message.setType(proto.WSCMessage.MsgType.REG);
	message.setMid(reqtime);
	message.setUid(uid);
	message.setTId(groupName);
	message.setBroadcast(false);
	bytes = message.serializeBinary();
	websocket.send(bytes);
}

function broadCast(groupName,txt){
	reqtime = reqtime +1;
	var message = new proto.WSCMessage();
	message.setType(proto.WSCMessage.MsgType.GROUP);
	message.setMid(reqtime);
	message.setUid(uid);
	message.setTId(groupName);
	message.setTxt(txt);
	message.setBroadcast(true);
	bytes = message.serializeBinary();
	websocket.send(bytes);
}

function closeWebsocket(){
    websocket.close();
}

   $("#Send_Button").click(function(){
	var text = $("#send").val();
	if(text === undefined || text === null){
		text = "nihao";
	}
	send(text);
	$("#send").val('');
   });
   
   $('#SendBin_Button').click(function(){
	   var tid = $("#tid").val();
		var txt = $("#txt").val();
		sendBin(tid,txt);
		$("#txt").val('');
   });
   
    $('#Reg_Button').click(function(){
	   var groupName = $("#reg").val();
		reg(groupName);
		$("#reg").val('');
   });
   
    $('#Send_Group_Button').click(function(){
	    var group = $("#group").val();
		var group_txt = $("#group_txt").val();
		broadCast(group,group_txt);
		$("#group").val('');
		$("#group_txt").val('');
   });
   
   $('#Clear_Button').click(function(){
	    $('#server').html('');
   });
   
   $('#Open_Button').click(function(){
	   uid = $('#uid').val();
	   var url = $('#url').val();
	   conn(uid,url);
   });
   
   $('#Close_Button').click(function(){
	   alert('关闭');
       closeWebsocket();
   });

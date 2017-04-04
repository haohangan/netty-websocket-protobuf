require('../src/js/WSMessage_pb');
console.info("开始");
var message = new proto.WSMessage();

message.setTId('userid1111');
message.setTxt('this is very huge Object');

bytes = message.serializeBinary();

var message2 = proto.WSMessage.deserializeBinary(bytes);

console.info('TID:'+message2.getTId());
console.info('TXT:'+message2.getTxt());

console.info("结束");
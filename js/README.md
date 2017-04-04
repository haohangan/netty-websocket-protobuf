# netty-websocket-protobuf
websocket protobuf  js
protoc.exe的使用
1:创建.protobuf文件
2:生成java js文件（https://github.com/google/protobuf/tree/master/js）
protoc.exe --java_out=./ WSMessage.proto
protoc.exe --js_out=import_style=commonjs,binary:. WSMessage.proto


3:java文件转移到项目中，js文件转移到js项目中
4:js项目中  修改下WSMessage_pb.js，并编写测试文件（test文件夹），测试可否使用
5:初始化js项目  npm init -y
6:npm install --save-dev jquery webpack   创建webpack.config.js文件  并复制内容
7:使用命令 webpack --config webpack.config.js --watch  测试（https://webpack.js.org/guides/get-started/）

var http = require('http')

http.createServer(function(request,response){

    response.writeHead(200,{
      'Content-Type':'application/json',
      'Access-Control-Allow-Origin':'*'}
    );
    json = [
            { id: 1, name: "iPhone X", quantity: 20 },
            { id: 2, name: "华为 Mate20", quantity: 0 },
            { id: 3, name: "小米 Mix3", quantity: 50 }
    ];
    response.end(JSON.stringify(json));
}
).listen(8000);

console.log('Server running at http://127.0.0.1:8000/');

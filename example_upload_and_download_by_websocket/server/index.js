const WebSocket = require("ws");
const fs = require("fs");
const { promisify } = require("util");

const wss = new WebSocket.Server({ port: 8081 });

wss.on("connection", (ws) => {
  ws.on("message", (data) => {
    wss.clients.forEach((it) => it.send(data));
  });
});

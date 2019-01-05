//dependencias
const express = require('express');
const bodyParser = require('body-parser');
const routes = require('./routes');

//declarações
const app = express();
const PORT = 80;

//middlewares
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

//rotas
routes(app);

//server
app.listen(PORT, () => {
    console.log(`${PORT}`);
});

import * as http from 'http';
import Api from './api/api';
const config = require('./config/env/config')();
const models = require('./models');

models.sequelize.sync().then(() => {
	const server = http.createServer(Api);
	server.listen(config.serverPort);
	server.on('listening', () => console.log(`Server rodando na porta ${config.serverPort}`));
	server.on('error', (erro: NodeJS.ErrnoException) => console.log(`API error: ${erro}`));
});

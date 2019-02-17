import * as express from 'express';
import * as HTTPStatus from 'http-status';

export default (response: express.Response, err: any) => {

	console.error(`Erro: ${err}`);
	response.status(HTTPStatus.INTERNAL_SERVER_ERROR).json({
		err: 'ERR-01',
		message: 'Erro ao cadastrar usuario'
	});

}

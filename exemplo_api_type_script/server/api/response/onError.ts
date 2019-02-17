import * as express from 'express';
import * as HTTPStatus from 'http-status';

export default (response: express.Response, mensagem: string, err: any) => {

	console.error(`Erro: ${err}`);
	response.status(HTTPStatus.INTERNAL_SERVER_ERROR).send(mensagem);

}

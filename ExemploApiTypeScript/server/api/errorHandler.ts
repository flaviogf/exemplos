import { Request, Response, ErrorRequestHandler, NextFunction } from 'express';

export function errorHandler(err: ErrorRequestHandler, req: Request, res: Response, next: NextFunction) {

	console.error(`API Error: ${err}`);

	res.status(500).json({
		erro: 'ERR-001',
		message: 'Erro Interno do Servidor'
	});

}

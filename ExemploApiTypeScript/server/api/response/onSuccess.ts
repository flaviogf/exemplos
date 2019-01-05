import * as express from 'express';
import * as HTTPStatus from 'http-status';

export default (response: express.Response, data: any) => response.status(HTTPStatus.OK).json({ payload: data });

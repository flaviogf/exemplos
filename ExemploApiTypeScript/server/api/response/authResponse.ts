import { Response } from 'express';
import * as jwtSimple from 'jwt-simple';
import * as HTTPStatus from 'http-status';
const config = require('../../config/env/config')();

export default function authResponse(res: Response, credentials: any, data: any) {

	const isMatch = (credentials.password == data.password);

	if (isMatch) {

		const payload = { id: data.id }

		res.json({
			token: jwtSimple.encode(payload, config.secret)
		});

	} else {

		res.sendStatus(HTTPStatus.UNAUTHORIZED);

	}

};

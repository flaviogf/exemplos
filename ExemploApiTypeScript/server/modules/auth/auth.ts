import { Request, Response } from 'express';
import User from '../Users/service';
import * as _ from 'lodash';
import authResponse from '../../api/response/authResponse';
import authFail from '../../api/response/authFail';

const UserService = new User();

class TokenRoute {

	auth(req: Request, res: Response) {

		const credentials = {
			email: req.body.email,
			password: req.body.password
		};

		if (credentials.hasOwnProperty('email') && credentials.hasOwnProperty('password')) {

			UserService.getByEmail(credentials.email)
				.then(_.partial(authResponse, res, credentials))
				.catch(_.partial(authFail, req, res));

		}

	}

}

export default TokenRoute;
